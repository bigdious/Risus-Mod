package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.world.biomes.BiomeGrassColors;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.bigdious.risus.world.biomes.BiomeHelper.*;

public class RisusBiomes {
	public static final ResourceKey<Biome> COALIFICATION = create("coalification");

	private static ResourceKey<Biome> create(String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(Risus.MODID, name));
	}

	public static void bootstrap(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(COALIFICATION, biomeWithDefaults(defaultAmbientBuilder().grassColorOverride(0xC45123).foliageColorOverride(0xFF8501).waterColor(0xBC8857).grassColorModifier(BiomeGrassColors.COALIFICATION), defaultMobSpawning(), defaultGenSettingBuilder(featureGetter, carverGetter)).temperature(0.5F).downfall(1).build());
	}
}
