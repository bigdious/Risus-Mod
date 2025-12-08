package com.bigdious.risus.loot;

import com.bigdious.risus.Risus;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

public class RisusLootTables {
	private static final Set<ResourceKey<LootTable>> RISUS_LOOT_TABLES = Sets.newHashSet();

	// Chest loot
	public static final ResourceKey<LootTable> FAMILY_TREE = register("family_tree_1");
	public static final ResourceKey<LootTable> ANGEL_ALTAR = register("angel_altar_1");
	public static final ResourceKey<LootTable> GREAT_BODY = register("great_body_1");
	public static final ResourceKey<LootTable> HOLDER_ROOM = register("holder_room");
	public static final ResourceKey<LootTable> CENTER_ROOM = register("center_room");
	public static final ResourceKey<LootTable> SINGER_ROOM = register("singer_room");
	public static final ResourceKey<LootTable> STALKER_ROOM = register("stalker_room");
	public static final ResourceKey<LootTable> LICKER_ROOM = register("licker_room");
	public static final ResourceKey<LootTable> ZOMBIE_ROOM = register("zombie_room");
	public static final ResourceKey<LootTable> REACTOR = register("reactor");
	public static final ResourceKey<LootTable> STAIRWELL_FLESH = register("stairwell_flesh");
	public static final ResourceKey<LootTable> STAIRWELL_REMAINS = register("stairwell_remains");
	public static final ResourceKey<LootTable> STORAGE_ROOM = register("storage_room");
	public static final ResourceKey<LootTable> CHURCH_HANGING = register("church_hanging");
	public static final ResourceKey<LootTable> CHURCH_TOMB = register("church_tomb");
	public static final ResourceKey<LootTable> CHURCH_LITTER = register("church_litter");

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

