package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidType;

public class Angel extends Monster {

	private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(Angel.class, EntityDataSerializers.BOOLEAN);

	public Angel(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 0;
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 100.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.0D)
			.add(Attributes.ATTACK_DAMAGE, 5.0D)
			.add(Attributes.FOLLOW_RANGE, 40.0D);
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
		super.registerGoals();
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 50.0F));
		this.goalSelector.addGoal(7, new Angel.AngelLightningAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false,
			entity -> Math.abs(entity.getY() - this.getY()) <= 50.0D && !entity.isInvulnerable() &&
				!(entity instanceof ArmorStand)
				&& !(entity.getType().is(RisusTags.Entities.OFFSPRING))
				&& !(entity.getType().is(RisusTags.Entities.BELOVED))
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
			if (livingentity != null && livingentity.distanceToSqr(this.angel) < 4096.0D && this.angel.hasLineOfSight(livingentity) && level.canSeeSky(livingentity.blockPosition())) {
				++this.chargeTime;
				if (this.chargeTime == 20) {
					LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
					lightning.setPos(livingentity.getX(), livingentity.getEyeY(), livingentity.getZ());
					level.addFreshEntity(lightning);
					this.chargeTime = -40;
				}
			} else if (this.chargeTime > 0) {
				--this.chargeTime;
			}

			this.angel.setCharging(this.chargeTime > 10);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		//keep semi-hardcode until tag issue is confirmed fixed
		if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || (source.getWeaponItem() != null &&
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
}
