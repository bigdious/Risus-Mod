package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.AlterationParticleOptions;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;


public class RisusBiomes {
	public static final ResourceKey<Biome> COALIFICATION = create("coalification");

	private static ResourceKey<Biome> create(String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(Risus.MODID, name));
	}

	public static void bootstrap(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(COALIFICATION, new Biome.BiomeBuilder()
			.generationSettings( new BiomeGenerationSettings.Builder(featureGetter, carverGetter).build())
			.mobSpawnSettings(new MobSpawnSettings.Builder().build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.specialEffects(generateColors(new BiomeSpecialEffects.Builder(), 526343, 1842204)
				.ambientParticle(new AmbientParticleSettings(AlterationParticleOptions.LETTERS_APPEAR, 0.005F))
				.build())
			.build());
	}

	private static BiomeSpecialEffects.Builder generateColors(BiomeSpecialEffects.Builder builder, int skyFog, int grass) {
		return builder
			.skyColor(526343)
			.fogColor(skyFog)
			.waterColor(526343)
			.waterFogColor(526343)
			.grassColorOverride(grass)
			.foliageColorOverride(grass);
	}
}
