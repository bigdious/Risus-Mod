package com.bigdious.risus.entity.creatures;

import com.bigdious.risus.init.*;
import com.bigdious.risus.util.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class Hex extends Vex {

	@Nullable
	Mob owner;

	public Hex(EntityType<? extends Vex> entityType, Level level) {
		super(entityType, level);
		this.moveControl = new VexMoveControl(this);
		this.xpReward = 3;
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 14.0D)
			.add(Attributes.ATTACK_DAMAGE, 4.0D);
	}

	@Override
	public void tick() {
		this.noPhysics = false;
		super.tick();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(4, new HexChargeAttackGoal());
		this.goalSelector.addGoal(8, new VexRandomMoveGoal());
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new VexCopyOwnerTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public void aiStep(){
		super.aiStep();
		if (this.level().isClientSide && this.tickCount % 15 == 0) {
			this.level().addParticle(RisusParticles.FALLING_BLOOD.get(), this.getRandomX(0.5F), this.getY()+0.2, this.getRandomZ(0.5F), 0.0F, 0.0F, 0.0F);
		}
	}


	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(RisusItems.KILLJOY.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, 0.05F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return RisusSoundEvents.HEX_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return RisusSoundEvents.HEX_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return RisusSoundEvents.HEX_HURT.get();
	}

	public class HexChargeAttackGoal extends Goal {
		public HexChargeAttackGoal() {
			this.setFlags(EnumSet.of(Flag.MOVE));
		}

		public boolean canUse() {
			LivingEntity livingentity = Hex.this.getTarget();
			return livingentity != null && livingentity.isAlive() && !Hex.this.getMoveControl().hasWanted() && Hex.this.random.nextInt(reducedTickDelay(7)) == 0;
		}

		public boolean canContinueToUse() {
			return Hex.this.getMoveControl().hasWanted() && Hex.this.isCharging() && Hex.this.getTarget() != null && Hex.this.getTarget().isAlive();
		}

		public void start() {
			LivingEntity livingentity = Hex.this.getTarget();
			if (livingentity != null) {
				Vec3 vec3 = livingentity.getEyePosition();
				Hex.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, (double)1.0F);
			}

			Hex.this.setIsCharging(true);
			Hex.this.playSound(RisusSoundEvents.HEX_CHARGE.get(), 1.0F, 1.0F);
		}

		public void stop() {
			Hex.this.setIsCharging(false);
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			LivingEntity livingentity = Hex.this.getTarget();
			if (livingentity != null) {
				if (Hex.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
					Hex.this.doHurtTarget(livingentity);
					Hex.this.setIsCharging(false);
				}
			}

		}
	}

}
