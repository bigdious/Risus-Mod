package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class RisusToolMaterials {
	public static final Tier GLUTTONY = new SimpleTier(BlockTags.create(Risus.prefix("needs_gluttony_tier")), 2031, 9.0F, 0.0F, 5, () -> Ingredient.of(RisusItems.GLUTTONY_SCALES.get()));
	public static final Tier BLOODMETAL = new SimpleTier(BlockTags.create(Risus.prefix("needs_bloodmetal_tier")), 1000, 9.0F, 0.0F, 5, () -> Ingredient.of(RisusBlocks.COAGULATED_BLOOD_BLOCK.get()));
}
