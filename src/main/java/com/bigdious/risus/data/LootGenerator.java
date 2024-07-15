package com.bigdious.risus.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootGenerator extends LootTableProvider {

	public LootGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, Set.of(), List.of(
				new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)
		), provider);
	}
}
