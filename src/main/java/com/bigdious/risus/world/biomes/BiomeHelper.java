package com.bigdious.risus.world.biomes;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public abstract class BiomeHelper {
	//copy from Twilight Forest
	// Defaults
	public static BiomeSpecialEffects.Builder defaultAmbientBuilder() {
		return new BiomeSpecialEffects.Builder()
			.fogColor(0xC0FFD8)
			.waterColor(0x3F76E4)
			.waterFogColor(0x050533)
			.skyColor(0x20224A)
			.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS); // We should probably change it
	}


	public static Biome.BiomeBuilder biomeWithDefaults(BiomeSpecialEffects.Builder biomeAmbience, MobSpawnSettings.Builder mobSpawnInfo, BiomeGenerationSettings.Builder biomeGenerationSettings) {
		return new Biome.BiomeBuilder()
			.hasPrecipitation(true)
			.temperature(0.5F)
			.downfall(0.5F)
			.specialEffects(biomeAmbience.build())
			.mobSpawnSettings(mobSpawnInfo.build())
			.generationSettings(biomeGenerationSettings.build())
			.temperatureAdjustment(Biome.TemperatureModifier.NONE);
	}
	public static MobSpawnSettings.Builder defaultMobSpawning() {
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();
		spawnInfo.creatureGenerationProbability(0.15f);
		return spawnInfo;
	}
	public static BiomeGenerationSettings.Builder defaultGenSettingBuilder(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BiomeGenerationSettings.Builder biome = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);

		BiomeDefaultFeatures.addDefaultSoftDisks(biome);
		BiomeDefaultFeatures.addSavannaGrass(biome);
		BiomeDefaultFeatures.addDefaultGrass(biome);
		BiomeDefaultFeatures.addSavannaExtraGrass(biome);
		biome.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
		BiomeDefaultFeatures.addSurfaceFreezing(biome);
		return biome;
	}


}
