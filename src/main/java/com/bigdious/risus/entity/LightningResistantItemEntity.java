package com.bigdious.risus.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LightningResistantItemEntity extends ItemEntity {

	public LightningResistantItemEntity(Level level, double posX, double posY, double posZ, ItemStack itemStack) {
		super(level, posX, posY, posZ, itemStack);
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source.is(DamageTypes.LIGHTNING_BOLT) || super.isInvulnerableTo(source);
	}
}
