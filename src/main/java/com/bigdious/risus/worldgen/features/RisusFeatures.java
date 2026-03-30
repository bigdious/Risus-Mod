package com.bigdious.risus.worldgen.features;

import com.bigdious.risus.Risus;
import com.bigdious.risus.worldgen.features.templates.GorgerFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Risus.MODID);

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GRASSY_GORGER = FEATURES.register("grassy_gorger", () -> new GorgerFeature(NoneFeatureConfiguration.CODEC, ResourceLocation.fromNamespaceAndPath(Risus.MODID,"feature/gorger/grassy")));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SANDY_GORGER = FEATURES.register("sandy_gorger", () -> new GorgerFeature(NoneFeatureConfiguration.CODEC, ResourceLocation.fromNamespaceAndPath(Risus.MODID,"feature/gorger/sandy")));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ENDY_GORGER = FEATURES.register("endy_gorger", () -> new GorgerFeature(NoneFeatureConfiguration.CODEC, ResourceLocation.fromNamespaceAndPath(Risus.MODID,"feature/gorger/endy")));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> NETHERY_GORGER = FEATURES.register("nethery_gorger", () -> new GorgerFeature(NoneFeatureConfiguration.CODEC, ResourceLocation.fromNamespaceAndPath(Risus.MODID,"feature/gorger/nethery")));

}
