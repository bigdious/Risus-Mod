package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {
	private static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
		.add(Registries.DAMAGE_TYPE, RisusDamageTypes::bootstrap)
		.add(Registries.BIOME, RisusBiomes::bootstrap)
		.add(Registries.PROCESSOR_LIST, RisusStructures::bootstrapProcessors)
		.add(Registries.STRUCTURE,RisusStructures::bootstrapStructures)
		.add(Registries.STRUCTURE_SET,RisusStructures::bootstrapSets)
		.add(Registries.TEMPLATE_POOL,RisusStructures::bootstrapPools);

	public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, REGISTRIES, Set.of("minecraft", Risus.MODID));
	}
}
