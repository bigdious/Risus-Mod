package com.bigdious.risus.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class GoldRushEffect extends MobEffect {
	public GoldRushEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	public void onMobRemoved(LivingEntity livingEntity, int amplifier, Entity.RemovalReason reason) {
		Level level = livingEntity.level();
		if (reason == Entity.RemovalReason.KILLED) {
			if (level.getRandom().nextFloat() <= 0.25) {
				ItemEntity gold = EntityType.ITEM.create(level);
				if (gold != null) {
					gold.setItem(Items.GOLD_NUGGET.getDefaultInstance());
					gold.moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
					level.addFreshEntity(gold);
				}
			} else if (level.getRandom().nextFloat() <= 0.05) {
				ItemEntity goldIngot = EntityType.ITEM.create(level);
				if (goldIngot != null) {
					goldIngot.setItem(Items.GOLD_INGOT.getDefaultInstance());
					goldIngot.moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
					level.addFreshEntity(goldIngot);
				}
			}

		}
	}
}
