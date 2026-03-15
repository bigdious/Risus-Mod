package com.bigdious.risus.worldgen;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class RisusBiomeModifiers {

	public static final ResourceKey<BiomeModifier> ADD_GRASSY_GORGER = create("add_grassy_gorger");


	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		var biomes = context.lookup(Registries.BIOME);
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);


   		context.register(ADD_GRASSY_GORGER, new BiomeModifiers.AddFeaturesBiomeModifier(
			   biomes.getOrThrow(RisusTags.Biomes.HAS_GRASSY_MAW),
			HolderSet.direct(placedFeatures.getOrThrow(RisusPlacedFeatures.GRASSY_GORGER)),
			GenerationStep.Decoration.SURFACE_STRUCTURES
		));
	}

	public static ResourceKey<BiomeModifier> create(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}

}
