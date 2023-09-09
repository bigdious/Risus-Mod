package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class LootGenerator extends LootTableProvider {
	private static final List<SubProviderEntry> tables = ImmutableList.of(
			new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
			new SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)
	);

	private static final Set<ResourceLocation> BUILTIN_TABLES = Set.of(
			Risus.prefix("blocks/spreading_remains"),
			Risus.prefix("blocks/teeth")
	);

	public LootGenerator(PackOutput packOutput) {
		super(packOutput, BUILTIN_TABLES, tables);
	}
}
