package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModItemTier {
    public static final Tier CRESCENT = TierSortingRegistry.registerTier(
            new ForgeTier(4, 2031, 9.0F, 4.0F, 15, BlockTags.create(Risus.prefix("needs_crescent_tier")), () -> Ingredient.of(RisusItems.GLUTTONY_SCALES.get())),
            Risus.prefix("crescent"), List.of(Tiers.NETHERITE), List.of());
}
