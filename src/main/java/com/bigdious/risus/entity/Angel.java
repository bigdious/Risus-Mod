package com.bigdious.risus.entity;

import com.bigdious.risus.entity.goals.YIncludedNearestAttackableTargetGoal;
import com.bigdious.risus.init.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.EnumSet;

public class Angel extends Monster {

	private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(Angel.class, EntityDataSerializers.BOOLEAN);

	public Angel(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 0;
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 100.0F)
			.add(Attributes.MOVEMENT_SPEED, 0.0F)
			.add(Attributes.ATTACK_DAMAGE, 5.0F)
			.add(Attributes.FOLLOW_RANGE, 60.0F);
	}

	public void setCharging(boolean charging) {
		this.entityData.set(DATA_IS_CHARGING, charging);
	}

	public boolean isCharging() {
		return this.entityData.get(DATA_IS_CHARGING);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_IS_CHARGING, false);
	}

	@Override
	public boolean canSwimInFluidType(FluidType type) {
		if (type == RisusFluids.BLOOD_FLUID_TYPE.get()) {
			return false;
		} else {
			return super.canSwimInFluidType(type);
		}
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.goalSelector.addGoal(7, new AngelLookGoal(this));
		this.goalSelector.addGoal(7, new Angel.AngelLightningAttackGoal(this));
		this.targetSelector.addGoal(1, new YIncludedNearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false,
			entity -> !entity.isInvulnerable() &&
				!(entity instanceof ArmorStand) &&
				!(entity.getType().is(RisusTags.Entities.OFFSPRING)) &&
				!(entity.getType().is(RisusTags.Entities.BELOVED))
		));
	}

	static class AngelLightningAttackGoal extends Goal {
		private final Angel angel;
		public int chargeTime;

		public AngelLightningAttackGoal(Angel angel) {
			this.angel = angel;
		}

		@Override
		public boolean canUse() {
			return this.angel.getTarget() != null && this.angel.level().getDifficulty() != Difficulty.PEACEFUL;
		}

		@Override
		public void start() {
			this.chargeTime = 0;
		}

		@Override
		public void stop() {
			this.angel.setCharging(false);
		}

		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}

		@Override
		public void tick() {
			LivingEntity livingentity = this.angel.getTarget();
			Level level = this.angel.level();
			if (livingentity != null && this.angel.hasLineOfSight(livingentity) && level.canSeeSky(livingentity.blockPosition())) {
				if (this.chargeTime == 0) {
					level.playSound(null, livingentity.getOnPos().above(2), RisusSoundEvents.TOLLING_BELL.get() ,SoundSource.HOSTILE, 3, 1);
				}
				++this.chargeTime;
				if (this.chargeTime == 30) {
					LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
					lightning.setPos(livingentity.getX(), livingentity.getEyeY(), livingentity.getZ());
					level.addFreshEntity(lightning);
					if (livingentity instanceof ServerPlayer sp){
						RisusAdvancements.HOLY_GROUNDS.get().trigger(sp);
					}
					this.chargeTime = -40;
				}
			} else if (this.chargeTime > 0) {
				--this.chargeTime;
			}

			this.angel.setCharging(this.chargeTime > 10);
		}
	}
	static class AngelLookGoal extends Goal {
		private final Angel ophanim;

		public AngelLookGoal(Angel ophanim) {
			this.ophanim = ophanim;
			this.setFlags(EnumSet.of(Flag.LOOK));
		}

		public boolean canUse() {
			return true;
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			if (this.ophanim.getTarget() != null) {
				LivingEntity livingentity = this.ophanim.getTarget();
					double d1 = livingentity.getX() - this.ophanim.getX();
					double d2 = livingentity.getZ() - this.ophanim.getZ();
					this.ophanim.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
					this.ophanim.yBodyRot = this.ophanim.getYRot();
			}

		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		//keep semi-hardcode until tag issue is confirmed fixed
		if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || source.is(RisusDamageTypes.AXED) || (source.getWeaponItem() != null &&
			(source.getWeaponItem().is(RisusTags.Items.WILLFUL_WEAPON)
				|| source.getWeaponItem().is(RisusItems.SCYTHE) ||
				source.getWeaponItem().is(RisusItems.FIRE_SCYTHE) ||
				source.getWeaponItem().is(RisusItems.SOUL_SCYTHE) ||
				source.getWeaponItem().is(RisusItems.CINDERGLEE_SCYTHE) ||
				source.getWeaponItem().is(RisusItems.UNAWAKENED_VESSEL) ||
				source.getWeaponItem().is(RisusItems.CRESCENT_DISASTER) ||
				source.getWeaponItem().is(RisusItems.THOUSAND_BLADE)

			))) {
			return super.hurt(source, Float.MAX_VALUE);
		}
		return false;
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double dist) {
		return false;
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}
}
