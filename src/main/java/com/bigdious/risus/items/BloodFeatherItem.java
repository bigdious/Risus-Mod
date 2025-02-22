package com.bigdious.risus.items;

import com.bigdious.risus.entity.LightningResistantItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BloodFeatherItem extends Item {

	public BloodFeatherItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public @Nullable Entity createEntity(Level level, Entity location, ItemStack stack) {
		var entity = new LightningResistantItemEntity(level, location.getX(), location.getY(), location.getZ(), stack);
		entity.setDeltaMovement(location.getDeltaMovement());
		entity.setDefaultPickUpDelay();
		return entity;
	}
}
