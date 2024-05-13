package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.NeoForgeMod;

public class ScytheItem extends SwordItem implements Vanishable,ReachItem {

	public ScytheItem(Tier tier, Item.Properties properties) {
		super(tier, 7, -3.5F, properties);
	}
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.builder();
		attributeBuilder.putAll(super.getDefaultAttributeModifiers(slot));
		attributeBuilder.put(NeoForgeMod.ENTITY_REACH.value(), new AttributeModifier(RANGE_MODIFIER, "Range modifier", 2, AttributeModifier.Operation.ADDITION));
		return slot == EquipmentSlot.MAINHAND ? attributeBuilder.build() : super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return material.is(RisusItems.GLUTTONY_SCALES) || super.isValidRepairItem(stack, material);
	}
}

