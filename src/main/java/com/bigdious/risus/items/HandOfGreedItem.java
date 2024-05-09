package com.bigdious.risus.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.NeoForgeMod;

public class HandOfGreedItem extends Item implements ReachItem {
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	public HandOfGreedItem(Item.Properties properties) {
		super(properties);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(NeoForgeMod.BLOCK_REACH.value(), new AttributeModifier(REACH_MODIFIER, "Reach modifier", 3, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}
}
