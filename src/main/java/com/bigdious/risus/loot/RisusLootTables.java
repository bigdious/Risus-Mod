package com.bigdious.risus.loot;

import com.bigdious.risus.Risus;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.Set;

public class RisusLootTables {
	private static final Set<ResourceKey<LootTable>> RISUS_LOOT_TABLES = Sets.newHashSet();
	private static final Set<ResourceKey<LootTable>> RISUS_IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(RISUS_LOOT_TABLES);
	public static final int DEFAULT_PLACE_FLAG = 2;

	// Chest loot
	public static final ResourceKey<LootTable> FAMILY_TREE = register("family_tree_1");
	public static final ResourceKey<LootTable> ANGEL_ALTAR = register("angel_altar_1");

	private static ResourceKey<LootTable> register(String id) {
		return register(ResourceKey.create(Registries.LOOT_TABLE, Risus.prefix(id)));
	}

	private static ResourceKey<LootTable> register(ResourceKey<LootTable> id) {
		if (RISUS_LOOT_TABLES.add(id)) {
			return id;
		} else {
			throw new IllegalArgumentException(id + " is already a registered built-in loot table");
		}
	}
}

