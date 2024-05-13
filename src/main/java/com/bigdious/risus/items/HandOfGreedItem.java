package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.NeoForgeMod;

public class HandOfGreedItem extends Item implements ReachItem {
	public HandOfGreedItem(Item.Properties properties) {
		super(properties);
	}
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.builder();
		attributeBuilder.putAll(super.getDefaultAttributeModifiers(slot));
		attributeBuilder.put(NeoForgeMod.BLOCK_REACH.value(), new AttributeModifier(REACH_MODIFIER, "Reach modifier", 3, AttributeModifier.Operation.ADDITION));
		return slot == EquipmentSlot.OFFHAND ? attributeBuilder.build() : super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return material.is(RisusItems.GLUTTONY_SCALES) || super.isValidRepairItem(stack, material);
	}
}
