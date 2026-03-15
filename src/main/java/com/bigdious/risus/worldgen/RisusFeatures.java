package com.bigdious.risus.worldgen;

import com.bigdious.risus.Risus;
import com.bigdious.risus.worldgen.features.templates.GrassyGorgerFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Risus.MODID);

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GRASSY_GORGER = FEATURES.register("grassy_gorger", () -> new GrassyGorgerFeature(NoneFeatureConfiguration.CODEC));

}
