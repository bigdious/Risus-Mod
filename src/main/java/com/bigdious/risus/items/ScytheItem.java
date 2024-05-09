package com.bigdious.risus.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.NeoForgeMod;

public class ScytheItem extends SwordItem implements Vanishable,ReachItem {
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	public ScytheItem(Tier tier, int damage, float speed, Item.Properties properties) {
		super(tier, damage, speed, properties);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(NeoForgeMod.ENTITY_REACH.value(), new AttributeModifier(RANGE_MODIFIER, "Range modifier", 1, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}
}
