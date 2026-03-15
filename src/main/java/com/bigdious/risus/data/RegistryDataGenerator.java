package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.*;
import com.bigdious.risus.worldgen.RisusBiomeModifiers;
import com.bigdious.risus.worldgen.RisusConfiguredFeatures;
import com.bigdious.risus.worldgen.RisusPlacedFeatures;
import com.bigdious.risus.worldgen.structures.RisusStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {
	private static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
		.add(Registries.DAMAGE_TYPE, RisusDamageTypes::bootstrap)
		.add(Registries.BIOME, RisusBiomes::bootstrap)
		.add(Registries.PROCESSOR_LIST, RisusStructures::bootstrapProcessors)
		.add(Registries.STRUCTURE,RisusStructures::bootstrapStructures)
		.add(Registries.STRUCTURE_SET,RisusStructures::bootstrapSets)
		.add(Registries.TRIM_MATERIAL, RisusTrimMaterials::bootstrap)
		.add(Registries.BANNER_PATTERN, RisusBannerPatterns::bootstrap)
		.add(Registries.JUKEBOX_SONG, RisusJukeboxSongs::bootstrap)
		.add(Registries.TEMPLATE_POOL,RisusStructures::bootstrapPools)
		.add(Registries.ENCHANTMENT, Execrations::bootstrap)
		.add(Registries.CONFIGURED_FEATURE, RisusConfiguredFeatures::bootstrap)
		.add(Registries.PLACED_FEATURE, RisusPlacedFeatures::bootstrap)
		.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, RisusBiomeModifiers::bootstrap);

	public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, REGISTRIES, Set.of("minecraft", Risus.MODID));
	}
}
