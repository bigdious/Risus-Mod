package com.bigdious.risus.worldgen.features;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusTags;
import com.bigdious.risus.worldgen.features.RisusPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class RisusBiomeModifiers {

	public static final ResourceKey<BiomeModifier> ADD_GRASSY_GORGER = create("add_grassy_gorger");
	public static final ResourceKey<BiomeModifier> ADD_SANDY_GORGER = create("add_sandy_gorger");
	public static final ResourceKey<BiomeModifier> ADD_ENDY_GORGER = create("add_endy_gorger");
	public static final ResourceKey<BiomeModifier> ADD_NETHERY_GORGER = create("add_nethery_gorger");


	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		var biomes = context.lookup(Registries.BIOME);
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

   		context.register(ADD_GRASSY_GORGER, new BiomeModifiers.AddFeaturesBiomeModifier(
			   biomes.getOrThrow(RisusTags.Biomes.HAS_GRASSY_MAW),
			HolderSet.direct(placedFeatures.getOrThrow(RisusPlacedFeatures.GRASSY_GORGER)),
			GenerationStep.Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(ADD_SANDY_GORGER, new BiomeModifiers.AddFeaturesBiomeModifier(
			biomes.getOrThrow(RisusTags.Biomes.HAS_SANDY_MAW),
			HolderSet.direct(placedFeatures.getOrThrow(RisusPlacedFeatures.SANDY_GORGER)),
			GenerationStep.Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(ADD_ENDY_GORGER, new BiomeModifiers.AddFeaturesBiomeModifier(
			biomes.getOrThrow(RisusTags.Biomes.HAS_ENDY_MAW),
			HolderSet.direct(placedFeatures.getOrThrow(RisusPlacedFeatures.ENDY_GORGER)),
			GenerationStep.Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(ADD_NETHERY_GORGER, new BiomeModifiers.AddFeaturesBiomeModifier(
			biomes.getOrThrow(RisusTags.Biomes.HAS_NETHERY_MAW),
			HolderSet.direct(placedFeatures.getOrThrow(RisusPlacedFeatures.NETHERY_GORGER)),
			GenerationStep.Decoration.TOP_LAYER_MODIFICATION
		));
	}

	public static ResourceKey<BiomeModifier> create(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}

}
