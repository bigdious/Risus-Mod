package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Angel extends Monster {
	public Angel(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1024.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.0D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 50.0F));

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
