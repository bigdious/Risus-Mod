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

	public static ResourceKey<PlacedFeature> create(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);


		context.register(GRASSY_GORGER, new PlacedFeature(features.getOrThrow(RisusConfiguredFeatures.GRASSY_GORGER), gorger(Blocks.GRASS_BLOCK).build()));


	}
	private static ImmutableList.Builder<PlacementModifier> gorger(Block block) {
		return ImmutableList.<PlacementModifier>builder().add(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(BlockPos.ZERO.atY(-1), block)), BiomeFilter.biome());

//		return ImmutableList.of(RarityFilter.onAverageOnceEvery(3), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
	}
}
