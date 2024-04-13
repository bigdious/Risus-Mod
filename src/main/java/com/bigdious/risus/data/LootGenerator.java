package com.bigdious.risus.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Set;

public class LootGenerator extends LootTableProvider {

	public LootGenerator(PackOutput output) {
		super(output, Set.of(), ImmutableList.of(
				new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
				new SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)
		));
	}
}
