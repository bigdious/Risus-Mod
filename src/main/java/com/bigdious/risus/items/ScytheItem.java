package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.NeoForgeMod;

public class ScytheItem extends SwordItem {
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	public ScytheItem(Tier tier, Item.Properties properties) {
		super(tier, properties);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ENTITY_INTERACTION_RANGE.value(), new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", 2, AttributeModifier.Operation.ADD_VALUE));
		this.defaultModifiers = builder.build();
	}
	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return material.is(RisusItems.GLUTTONY_SCALES) || super.isValidRepairItem(stack, material);
	}
}

