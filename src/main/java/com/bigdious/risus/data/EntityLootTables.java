package com.bigdious.risus.data;

import com.bigdious.risus.entity.RisusEntities;
import com.bigdious.risus.items.RisusItems;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.KilledByPlayer;

import java.util.HashSet;
import java.util.Set;

public class EntityLootTables extends net.minecraft.data.loot.EntityLootTables {

	private final Set<EntityType<?>> knownEntities = new HashSet<>();

	@Override
	protected void registerLootTable(EntityType<?> entity, LootTable.Builder builder) {
		super.registerLootTable(entity, builder);
		knownEntities.add(entity);
	}

	@Override
	protected void addTables() {
		registerLootTable(RisusEntities.ANGEL,
				LootTable.builder()
						.addLootPool(LootPool.builder()
								.rolls(ConstantRange.of(1))
								.addEntry(ItemLootEntry.builder(RisusItems.BLOOD_FEATHER.get()))
								.acceptCondition(KilledByPlayer.builder())));
	}

	@Override
	public Set<EntityType<?>> getKnownEntities() {
		return knownEntities;
	}
}
