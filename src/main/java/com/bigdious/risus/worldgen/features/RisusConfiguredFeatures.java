package com.bigdious.risus.worldgen.features;

import com.bigdious.risus.Risus;
import com.bigdious.risus.worldgen.features.RisusFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class RisusConfiguredFeatures {

	public static final ResourceKey<ConfiguredFeature<?, ?>> GRASSY_GORGER = create("grassy_gorger");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SANDY_GORGER = create("sandy_gorger");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ENDY_GORGER = create("endy_gorger");
	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHERY_GORGER = create("nethery_gorger");



	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		context.register(GRASSY_GORGER, new ConfiguredFeature<>(RisusFeatures.GRASSY_GORGER.get(), FeatureConfiguration.NONE));
		context.register(SANDY_GORGER, new ConfiguredFeature<>(RisusFeatures.SANDY_GORGER.get(), FeatureConfiguration.NONE));
		context.register(ENDY_GORGER, new ConfiguredFeature<>(RisusFeatures.ENDY_GORGER.get(), FeatureConfiguration.NONE));
		context.register(NETHERY_GORGER, new ConfiguredFeature<>(RisusFeatures.NETHERY_GORGER.get(), FeatureConfiguration.NONE));
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}
}
