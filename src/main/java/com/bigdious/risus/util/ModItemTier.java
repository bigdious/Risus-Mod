package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModItemTier {
    public static final Tier JOY = TierSortingRegistry.registerTier(
            new ForgeTier(2, 666, 4.0F, 1.0F, 10, BlockTags.create(Risus.prefix("needs_joy_tier")), Ingredient::of),
            Risus.prefix("joy"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
}
