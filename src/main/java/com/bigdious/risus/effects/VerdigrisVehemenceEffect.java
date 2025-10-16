package com.bigdious.risus.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;


public class VerdigrisVehemenceEffect extends MobEffect {
	public VerdigrisVehemenceEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	public void onMobRemoved(LivingEntity livingEntity, int amplifier, Entity.RemovalReason reason) {
		Level level = livingEntity.level();
		if (reason == Entity.RemovalReason.KILLED) {
			if (level.getRandom().nextFloat() <= 0.25) {
				ItemEntity copper = EntityType.ITEM.create(level);
				if (copper != null) {
					copper.setItem(Items.COPPER_INGOT.getDefaultInstance());
					copper.moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
					level.addFreshEntity(copper);
				}
			} else if (level.getRandom().nextFloat() <= 0.05) {
				ItemEntity copperBlock = EntityType.ITEM.create(level);
				if (copperBlock != null) {
					copperBlock.setItem(Blocks.OXIDIZED_COPPER.asItem().getDefaultInstance());
					copperBlock.moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
					level.addFreshEntity(copperBlock);
				}
			}

		}
	}
}
