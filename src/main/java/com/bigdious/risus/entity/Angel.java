package com.bigdious.risus.entity;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Angel extends Monster {
	public Angel(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}
	private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(Angel.class, EntityDataSerializers.BOOLEAN);
	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1024.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.0D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	public void setCharging(boolean charging) {
		this.entityData.set(DATA_IS_CHARGING, charging);
	}
	public boolean isCharging() {
		return this.entityData.get(DATA_IS_CHARGING);
	}
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_IS_CHARGING, false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 50.0F));
		this.goalSelector.addGoal(7, new Angel.AngelLightningAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (entity) -> Math.abs(entity.getY() - this.getY()) <= 4.0D));

	}
	static class AngelLightningAttackGoal extends Goal {
		private final Angel angel;
		public int chargeTime;
		private Level level;

		public AngelLightningAttackGoal(Angel angel) {
			this.angel = angel;
		}

		public boolean canUse() {
			return this.angel.getTarget() != null;
		}

		public void start() {
			this.chargeTime = 0;
		}

		public void stop() {
			this.angel.setCharging(false);
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			LivingEntity livingentity = this.angel.getTarget();
			if (livingentity != null) {
				if (livingentity.distanceToSqr(this.angel) < 4096.0D && this.angel.hasLineOfSight(livingentity)) {
					++this.chargeTime;
					if (this.chargeTime == 20) {
						Level level = this.angel.level();
						Risus.LOGGER.info("attempting to summon lightning bolt");
						LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
						lightning.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
						level.addFreshEntity(lightning);
						this.chargeTime = -40;
						}
					}
				} else if (this.chargeTime > 0) {
					--this.chargeTime;
				}

				this.angel.setCharging(this.chargeTime > 10);
			}
		}
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getEntity() instanceof LivingEntity living && living.getItemInHand(living.getUsedItemHand()).is(RisusItems.UNAWAKENED_VESSEL.get())) {
			return super.hurt(source, Float.MAX_VALUE);
		}
		if (source.getEntity() instanceof LivingEntity living && living.getItemInHand(living.getUsedItemHand()).is(RisusItems.CRESCENT_DISASTER.get())) {
			return super.hurt(source, Float.MAX_VALUE);
		}
		return false;
	}
	@Override
	public boolean removeWhenFarAway(double dist) {
		return false;
	}
}
