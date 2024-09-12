package com.bigdious.risus.data;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.loot.RisusLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record ContainerLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> register) {
		register.accept(RisusLootTables.FAMILY_TREE,
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(2,3))
					.add(LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.HEART_TRANSPLANT)))
				);
		register.accept(RisusLootTables.ANGEL_ALTAR,
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3,6))
					.add(LootItem.lootTableItem(RisusItems.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusItems.LINEAR_RITUAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusItems.GRIMSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusItems.JOYFLAME_LANTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(2))
					.add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES)))
		);

	}
}
