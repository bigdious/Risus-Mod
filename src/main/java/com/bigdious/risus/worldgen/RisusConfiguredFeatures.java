package com.bigdious.risus.worldgen;

import com.bigdious.risus.Risus;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class RisusConfiguredFeatures {

	public static final ResourceKey<ConfiguredFeature<?, ?>> GRASSY_GORGER = create("grassy_gorger");
	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHERY_GORGER = create("nethery_gorger");



	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

		RuleTest grassBlockReplacable = new BlockMatchTest(Blocks.GRASS_BLOCK);

		context.register(GRASSY_GORGER, new ConfiguredFeature<>(RisusFeatures.GRASSY_GORGER.get(), FeatureConfiguration.NONE));
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}
}
