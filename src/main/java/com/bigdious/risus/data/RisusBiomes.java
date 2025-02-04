package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.registries.DeferredHolder;


public class RisusBiomes {
	public static final ResourceKey<Biome> COALIFICATION = create("coalification");
	public static final ResourceKey<Biome> COALIFICATION_MORK = create("coalification_mork");
	public static final ResourceKey<Biome> COALIFICATION_FEIGR = create("coalification_feigr");

	private static ResourceKey<Biome> create(String name) {
		return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}

	public static void bootstrap(BootstrapContext<Biome> context) {
		HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(COALIFICATION, new Biome.BiomeBuilder()
			.generationSettings( new BiomeGenerationSettings.Builder(featureGetter, carverGetter).build())
			.mobSpawnSettings(new MobSpawnSettings.Builder().build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.specialEffects(addMusic(generateColors(new BiomeSpecialEffects.Builder(), 0x650404, 1842204), RisusSoundEvents.MUSIC_DISC_RAK)
				.ambientParticle(new AmbientParticleSettings(RisusParticles.RISUS_SOUL_PARTICLE.get(), 0.0001F)).build())
			.build());
		context.register(COALIFICATION_MORK, new Biome.BiomeBuilder()
			.generationSettings( new BiomeGenerationSettings.Builder(featureGetter, carverGetter).build())
			.mobSpawnSettings(new MobSpawnSettings.Builder().build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.specialEffects(addBackgroundLoop(generateColors(new BiomeSpecialEffects.Builder(), 0x650404, 1842204), RisusSoundEvents.AMBIENT_MORK)
				.ambientParticle(new AmbientParticleSettings(RisusParticles.RISUS_SOUL_PARTICLE.get(), 0.0001F)).build())
			.build());
		context.register(COALIFICATION_FEIGR, new Biome.BiomeBuilder()
			.generationSettings( new BiomeGenerationSettings.Builder(featureGetter, carverGetter).build())
			.mobSpawnSettings(new MobSpawnSettings.Builder().build())
			.hasPrecipitation(false)
			.downfall(0.0F)
			.temperature(0.8F)
			.specialEffects(addBackgroundLoop(generateColors(new BiomeSpecialEffects.Builder(), 0x650404, 1842204), RisusSoundEvents.AMBIENT_FEIGR)
				.ambientParticle(new AmbientParticleSettings(RisusParticles.RISUS_SOUL_PARTICLE.get(), 0.0001F)).build())
			.build());
	}

	private static BiomeSpecialEffects.Builder generateColors(BiomeSpecialEffects.Builder builder, int skyFog, int grass) {
		return builder
			.skyColor(0x000000)
			.fogColor(skyFog)
			.waterColor(526343)
			.waterFogColor(526343)
			.grassColorOverride(grass)
			.foliageColorOverride(grass);
	}
	private static BiomeSpecialEffects.Builder addMusic(BiomeSpecialEffects.Builder builder,  DeferredHolder<SoundEvent, SoundEvent> music) {
	return builder
		.backgroundMusic(new Music(music, 3000, 6000, true));
	}
	private static BiomeSpecialEffects.Builder addBackgroundLoop(BiomeSpecialEffects.Builder builder,  DeferredHolder<SoundEvent, SoundEvent> music) {
		return builder
			.ambientLoopSound(music);
	}
}
