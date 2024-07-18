package com.bigdious.risus.items;

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

public class HandOfGreedItem extends TieredItem {
	public HandOfGreedItem(Tier tier,Item.Properties properties) {
		super(tier ,properties);
	}

	public static ItemAttributeModifiers createHandOfGreedAttributes(Tier tier) {
		return ItemAttributeModifiers.builder()
			.add(Attributes.BLOCK_INTERACTION_RANGE,
				new AttributeModifier(
					"range_modifier",
					3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND).build();

	}


}
