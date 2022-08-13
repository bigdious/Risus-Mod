package com.bigdious.risus.data;

import net.minecraft.world.entity.EntityType;

import java.util.Set;

public class EntityLootTables extends net.minecraft.data.loot.EntityLoot {

	@Override
	protected void addTables() {
//		add(RisusEntities.ANGEL,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool()
//								.setRolls(ConstantValue.exactly(1))
//								.add(LootItem.lootTableItem(RisusItems.BLOOD_FEATHER.get()))
//								.when(LootItemKilledByPlayerCondition.killedByPlayer())));
	}

	@Override
	public Set<EntityType<?>> getKnownEntities() {
		return Set.of();
	}
}
