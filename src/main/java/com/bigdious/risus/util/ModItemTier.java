package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

import java.util.List;

public class ModItemTier {
	public static final Tier CRESCENT = new SimpleTier(BlockTags.create(Risus.prefix("needs_crescent_tier")), 2031, 9.0F, 0.0F, 5, () -> Ingredient.of(RisusItems.GLUTTONY_SCALES.get()));
}
