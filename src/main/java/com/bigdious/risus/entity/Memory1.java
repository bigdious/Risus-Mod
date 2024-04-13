package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Memory1 extends Monster {

	public Memory1(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 0;
	}


	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.0D)
				.add(Attributes.ATTACK_DAMAGE, 0.0D);
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}

	@Override
	public boolean canBeSeenAsEnemy() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public void knockback(double x, double y, double z) {
	}

	@Override
	public boolean removeWhenFarAway(double dist) {
		return false;
	}

	@Override
	public boolean shouldDropExperience() {
		return false;
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
}
