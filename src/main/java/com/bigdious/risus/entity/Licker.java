package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Licker extends Monster {
	private int attackTimer;

	public Licker(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 32.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.25F)
			.add(Attributes.FOLLOW_RANGE, 24)
			.add(Attributes.ATTACK_DAMAGE, 1D);
	}
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1F, true));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
	}
	@Override
	public void aiStep() {
		super.aiStep();

		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}
	@Override
	public boolean doHurtTarget(Entity entity) {
		if (entity instanceof LivingEntity living) {
			this.attackTimer = 10;
			this.level().broadcastEntityEvent(this, (byte) 4);
			int i = 10;
			if (this.level().getDifficulty() == Difficulty.NORMAL) {
				i = 15;
			} else if (this.level().getDifficulty() == Difficulty.HARD) {
				i = 20;
			}
			living.addEffect(new MobEffectInstance(MobEffects.WEAVING, i * 20, 0), this);
			living.addEffect(new MobEffectInstance(RisusMobEffects.PLEASURE, i * 2, 0), this);
		}
		return super.doHurtTarget(entity);
	}
	public int getAttackTimer() {
		return this.attackTimer;
	}
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		} else {
			super.handleEntityEvent(id);
		}
	}
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SPIDER_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_33814_) {
		return SoundEvents.SPIDER_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.SPIDER_DEATH;
	}

	protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}
	public void makeStuckInBlock(BlockState p_33796_, Vec3 p_33797_) {
		if (!p_33796_.is(Blocks.COBWEB)) {
			super.makeStuckInBlock(p_33796_, p_33797_);
		}

	}
	public boolean canBeAffected(MobEffectInstance p_33809_) {
		return !p_33809_.is(MobEffects.POISON) && super.canBeAffected(p_33809_);
	}

}
