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
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class Hex extends Vex {


	@Nullable
	Mob owner;

	public Hex(EntityType<? extends Vex> entityType, Level level) {
		super(entityType, level);
		this.moveControl = new HexMoveControl(this);
		this.xpReward = 3;
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 14.0D)
			.add(Attributes.ATTACK_DAMAGE, 0.0D);
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

	public void aiStep() {
		super.aiStep();
		if (this.level().isClientSide && this.tickCount % 15 == 0) {
			this.level().addParticle(RisusParticles.FALLING_BLOOD.get(), this.getRandomX(0.5F), this.getY() + 0.2, this.getRandomZ(0.5F), 0.0F, 0.0F, 0.0F);
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
			return livingentity != null && livingentity.isAlive() && Hex.this.random.nextInt(reducedTickDelay(7)) == 0 ? Hex.this.distanceToSqr(livingentity) > (double)25.0F : false;
		}

		public boolean canContinueToUse() {
			return Hex.this.getMoveControl().hasWanted() && Hex.this.isCharging() && Hex.this.getTarget() != null && Hex.this.getTarget().isAlive();
		}

		public void start() {
			LivingEntity livingentity = Hex.this.getTarget();
			if (livingentity != null) {
				Vec3 vec3 = livingentity.getEyePosition();
				Hex.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0F);
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
				if (Hex.this.getBoundingBox().inflate(0.8).intersects(livingentity.getBoundingBox())) {
					Hex.this.doHurtTarget(livingentity);
					Hex.this.setIsCharging(false);
				} else {
					double d0 = Hex.this.distanceToSqr(livingentity);
					if (d0 < (double)1.0F) {
						Vec3 vec3 = livingentity.getEyePosition();
						Hex.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0F);
					}
				}
			}

		}
	}

	public class HexMoveControl extends MoveControl {
		public HexMoveControl(Hex hex) {
			super(hex);
		}

		public void tick() {
			if (this.operation == Operation.MOVE_TO) {
				Vec3 vec3 = new Vec3(this.wantedX - Hex.this.getX(), this.wantedY - Hex.this.getY(), this.wantedZ - Hex.this.getZ());
				double d0 = vec3.length();
				if (d0 < Hex.this.getBoundingBox().getSize()) {
					this.operation = Operation.WAIT;
					Hex.this.setDeltaMovement(Hex.this.getDeltaMovement().scale(0.5F));
				} else {
					Hex.this.setDeltaMovement(Hex.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05 / d0)));
					if (Hex.this.getTarget() == null) {
						Vec3 vec31 = Hex.this.getDeltaMovement();
						Hex.this.setYRot(-((float)Mth.atan2(vec31.x, vec31.z)) * (180F / (float)Math.PI));
						Hex.this.yBodyRot = Hex.this.getYRot();
					} else {
						double d2 = Hex.this.getTarget().getX() - Hex.this.getX();
						double d1 = Hex.this.getTarget().getZ() - Hex.this.getZ();
						Hex.this.setYRot(-((float)Mth.atan2(d2, d1)) * (180F / (float)Math.PI));
						Hex.this.yBodyRot = Hex.this.getYRot();
					}
				}
			}

		}
	}

	//dumping the entire super tick here to jump over Vexes having no physics.
	//I did try using Mixin, but it fucking broke when in Multiplayer, so fuck mixins. Why can't I just directly call Monster's tick
	@Override
	public void tick() {
		this.setNoGravity(true);
		if (!this.level().isClientSide && this.tickCount % 5 == 0) {
			this.updateControlFlags();
		}

		if (this.canUseSlot(EquipmentSlot.BODY)) {
			ItemStack stack = this.getBodyArmorItem();
			if (this.isBodyArmorItem(stack)) {
				stack.onAnimalArmorTick(this.level(), this);
			}
		}
		this.updatingUsingItem();
		this.updateSwimAmount();
		if (!this.level().isClientSide) {
			int i = this.getArrowCount();
			if (i > 0) {
				if (this.removeArrowTime <= 0) {
					this.removeArrowTime = 20 * (30 - i);
				}

				--this.removeArrowTime;
				if (this.removeArrowTime <= 0) {
					this.setArrowCount(i - 1);
				}
			}

			int j = this.getStingerCount();
			if (j > 0) {
				if (this.removeStingerTime <= 0) {
					this.removeStingerTime = 20 * (30 - j);
				}

				--this.removeStingerTime;
				if (this.removeStingerTime <= 0) {
					this.setStingerCount(j - 1);
				}
			}

			this.detectEquipmentUpdates();
			if (this.tickCount % 20 == 0) {
				this.getCombatTracker().recheckStatus();
			}

			if (this.isSleeping() && !this.checkBedExists()) {
				this.stopSleeping();
			}
		}

		if (!this.isRemoved()) {
			this.aiStep();
		}

		double d1 = this.getX() - this.xo;
		double d0 = this.getZ() - this.zo;
		float f = (float)(d1 * d1 + d0 * d0);
		float f1 = this.yBodyRot;
		float f2 = 0.0F;
		this.oRun = this.run;
		float f3 = 0.0F;
		if (f > 0.0025000002F) {
			f3 = 1.0F;
			f2 = (float)Math.sqrt((double)f) * 3.0F;
			float f4 = (float)Mth.atan2(d0, d1) * (180F / (float)Math.PI) - 90.0F;
			float f5 = Mth.abs(Mth.wrapDegrees(this.getYRot()) - f4);
			if (95.0F < f5 && f5 < 265.0F) {
				f1 = f4 - 180.0F;
			} else {
				f1 = f4;
			}
		}

		if (this.attackAnim > 0.0F) {
			f1 = this.getYRot();
		}

		if (!this.onGround()) {
			f3 = 0.0F;
		}

		this.run += (f3 - this.run) * 0.3F;
		this.level().getProfiler().push("headTurn");
		f2 = this.tickHeadTurn(f1, f2);
		this.level().getProfiler().pop();
		this.level().getProfiler().push("rangeChecks");

		while(this.getYRot() - this.yRotO < -180.0F) {
			this.yRotO -= 360.0F;
		}

		while(this.getYRot() - this.yRotO >= 180.0F) {
			this.yRotO += 360.0F;
		}

		while(this.yBodyRot - this.yBodyRotO < -180.0F) {
			this.yBodyRotO -= 360.0F;
		}

		while(this.yBodyRot - this.yBodyRotO >= 180.0F) {
			this.yBodyRotO += 360.0F;
		}

		while(this.getXRot() - this.xRotO < -180.0F) {
			this.xRotO -= 360.0F;
		}

		while(this.getXRot() - this.xRotO >= 180.0F) {
			this.xRotO += 360.0F;
		}

		while(this.yHeadRot - this.yHeadRotO < -180.0F) {
			this.yHeadRotO -= 360.0F;
		}

		while(this.yHeadRot - this.yHeadRotO >= 180.0F) {
			this.yHeadRotO += 360.0F;
		}

		this.level().getProfiler().pop();
		this.animStep += f2;
		if (this.isFallFlying()) {
			++this.fallFlyTicks;
		} else {
			this.fallFlyTicks = 0;
		}

		if (this.isSleeping()) {
			this.setXRot(0.0F);
		}

		this.refreshDirtyAttributes();
		float f6 = this.getScale();
		if (f6 != this.appliedScale) {
			this.appliedScale = f6;
			this.refreshDimensions();
		}


		this.baseTick();

	}
}
