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

	public ScytheItem(Tier material, Properties properties) {
		super(material, properties);
	}

	public static ItemAttributeModifiers createScytheAttributes(Tier tier, int damage, float speed) {
		return SwordItem.createAttributes(tier, damage, speed)
			.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Risus.prefix("range_modifier"), 2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO, new AttributeModifier(Risus.prefix("range_modifier"), damage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
	}
	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return material.is(RisusItems.GLUTTONY_SCALES);
	}
}

