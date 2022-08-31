package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.stream.Collectors;

public class EntityLootTables extends EntityLoot {

	@Override
	protected void addTables() {
		add(RisusEntities.ANGEL.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(RisusItems.BLOOD_FEATHER.get()))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())));

		add(RisusEntities.HOLDER.get(),
				LootTable.lootTable().withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER.get()))
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))));

		add(RisusEntities.MAW.get(), LootTable.lootTable());

		add(RisusEntities.WEAVER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(Items.BONE)).
						apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(RisusItems.MEMORY_CORE.get()))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when((LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.03F, 0.03F)))));
	}

	@Override
	public Set<EntityType<?>> getKnownEntities() {
		return ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(entities -> ForgeRegistries.ENTITY_TYPES.getKey(entities).getNamespace().equals(Risus.MODID)).collect(Collectors.toSet());
	}
}
