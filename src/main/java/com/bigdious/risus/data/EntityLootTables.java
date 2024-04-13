package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class EntityLootTables extends EntityLootSubProvider {

	protected EntityLootTables() {
		super(FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	public void generate() {
		add(RisusEntities.ANGEL.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(RisusItems.BLOOD_FEATHER.get()))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())));
		add(RisusEntities.MEMORY1.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(RisusItems.MEMORY1_ITEM.get()))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())));

		add(RisusEntities.HOLDER.get(),
				LootTable.lootTable().withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER.get()))
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(RisusItems.HAND_OF_GREED.get()))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())
								.when((LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.03F, 0.03F)))));

		add(RisusEntities.MAW.get(), LootTable.lootTable());
		add(RisusEntities.LOVER.get(), LootTable.lootTable());
		add(RisusEntities.STALKER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(Items.GUNPOWDER)).
						apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))));
		add(RisusEntities.QUESTION_MARK.get(), LootTable.lootTable());
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
	public Stream<EntityType<?>> getKnownEntityTypes() {
		return RisusEntities.ENTITIES.getEntries().stream().map(Supplier::get);
	}
}
