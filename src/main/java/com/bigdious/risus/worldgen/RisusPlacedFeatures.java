package com.bigdious.risus.worldgen;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class RisusPlacedFeatures {

	public static final ResourceKey<PlacedFeature> GRASSY_GORGER = create("grassy_gorger_placed");
	public static final ResourceKey<PlacedFeature> SANDY_GORGER = create("sandy_gorger_placed");
	public static final ResourceKey<PlacedFeature> ENDY_GORGER = create("endy_gorger_placed");
	public static final ResourceKey<PlacedFeature> NETHERY_GORGER = create("nethery_gorger_placed");

	public static ResourceKey<PlacedFeature> create(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);


		context.register(GRASSY_GORGER, new PlacedFeature(features.getOrThrow(RisusConfiguredFeatures.GRASSY_GORGER), gorger(Blocks.GRASS_BLOCK).build()));
		context.register(SANDY_GORGER, new PlacedFeature(features.getOrThrow(RisusConfiguredFeatures.SANDY_GORGER), gorger(Blocks.SAND).build()));
		context.register(ENDY_GORGER, new PlacedFeature(features.getOrThrow(RisusConfiguredFeatures.ENDY_GORGER), gorger(Blocks.END_STONE).build()));
		context.register(NETHERY_GORGER, new PlacedFeature(features.getOrThrow(RisusConfiguredFeatures.NETHERY_GORGER), netherGorger(Blocks.NETHERRACK).build()));


	}
	private static ImmutableList.Builder<PlacementModifier> gorger(Block block) {
		return ImmutableList.<PlacementModifier>builder().add(RarityFilter.onAverageOnceEvery(140), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO.atY(-1), block), BlockPredicate.ONLY_IN_AIR_PREDICATE)), BiomeFilter.biome());
	}

	private static ImmutableList.Builder<PlacementModifier> netherGorger(Block block) {
		return ImmutableList.<PlacementModifier>builder().add(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO.atY(-1), block), BlockPredicate.ONLY_IN_AIR_PREDICATE)), BiomeFilter.biome());
	}
}
