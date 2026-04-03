package com.bigdious.risus.data.loottables;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import com.bigdious.risus.loot.RisusLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record ContainerLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> register) {
		register.accept(RisusLootTables.FAMILY_TREE,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(2, 3))
					.add(LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//50/50 heart
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(RisusBlocks.HEART_TRANSPLANT)))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2))))
					.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,4))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3)))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.TREE_PATTERN)))
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_MORK)))

		);
		register.accept(RisusLootTables.ANGEL_ALTAR,
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
					//common material drops
					.setRolls(UniformGenerator.between(3, 6))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.LINEAR_RITUAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.GRIMSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.JOYFLAME_LANTERN))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(2.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,5))))
					.add(LootItem.lootTableItem(Items.DIAMOND))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES))
					.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,5))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,5))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 20))))
					.add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING))
					.add(LootItem.lootTableItem(RisusItems.ETERNAL_YOUTH))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES)))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.DIVINITY_PATTERN)))
		);
		register.accept(RisusLootTables.GREAT_BODY,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 6))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.FULL_BONE_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.NEURON_HEAD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.BLOODWEAVE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
					.add(LootItem.lootTableItem(RisusBlocks.FULL_FOSSIL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
					.add(LootItem.lootTableItem(Items.DIAMOND))
					.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15)))))
		);
		register.accept(RisusLootTables.GREAT_BODY_HEART,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 6))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.FULL_BONE_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.NEURON_HEAD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.BLOODWEAVE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
					.add(LootItem.lootTableItem(Items.DIAMOND))
					.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15)))))
				//core roll
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(RisusItems.MEMORY_CORE))
					.add(LootItem.lootTableItem(RisusItems.CONCENTRATION_CORE)))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
		);

		register.accept(RisusLootTables.STALKER_ROOM,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 9))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 20))))
					.add(LootItem.lootTableItem(RisusBlocks.BURNT_HYPHAE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusItems.STALKER_EYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 10))))
					.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
					.add(LootItem.lootTableItem(Items.NAME_TAG))
					.add(LootItem.lootTableItem(Items.SADDLE))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_REGN)))
		);
		register.accept(RisusLootTables.SINGER_ROOM,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 9))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 20))))
					.add(LootItem.lootTableItem(RisusBlocks.BURNT_HYPHAE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 10))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.ECHO_PEARL)))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
					.add(LootItem.lootTableItem(Items.NAME_TAG))
					.add(LootItem.lootTableItem(Items.SADDLE))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_REGN)))
		);
		register.accept(RisusLootTables.HOLDER_ROOM,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 9))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 20))))
					.add(LootItem.lootTableItem(RisusBlocks.BURNT_HYPHAE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 10))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.GOLD_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
					.add(LootItem.lootTableItem(Items.NAME_TAG))
					.add(LootItem.lootTableItem(Items.SADDLE))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_REGN)))
				//rare hand
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(5))
					.add(LootItem.lootTableItem(RisusItems.HAND_OF_GREED)))
		);
		register.accept(RisusLootTables.HEX_ROOM,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 9))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 20))))
					.add(LootItem.lootTableItem(RisusBlocks.BURNT_HYPHAE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 10))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.TOTEM_OF_UNYIELDING)))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
					.add(LootItem.lootTableItem(Items.NAME_TAG))
					.add(LootItem.lootTableItem(Items.SADDLE))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_REGN)))
				//rare killjoy
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(5))
					.add(LootItem.lootTableItem(RisusItems.KILLJOY)))
		);
		register.accept(RisusLootTables.LICKER_ROOM,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 9))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 20))))
					.add(LootItem.lootTableItem(RisusBlocks.BURNT_HYPHAE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.FERMENTED_SPIDER_EYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COBWEB).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 10))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.EGG_SAC).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 6)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
					.add(LootItem.lootTableItem(Items.NAME_TAG))
					.add(LootItem.lootTableItem(Items.SADDLE))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_REGN)))
		);
		register.accept(RisusLootTables.CENTER_ROOM,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 9))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 20))))
					.add(LootItem.lootTableItem(RisusBlocks.BURNT_HYPHAE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusItems.LOVER_CREAM).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 10))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 10))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
					.add(LootItem.lootTableItem(Items.NAME_TAG))
					.add(LootItem.lootTableItem(Items.SADDLE))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_REGN)))
		);
		register.accept(RisusLootTables.STAIRWELL_FLESH,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(6, 12))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(RisusBlocks.GRIMSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.EGG_SAC).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.FERMENTED_SPIDER_EYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES))
					.add(LootItem.lootTableItem(Items.DIAMOND)))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_FEIGR)))
		);
		register.accept(RisusLootTables.STAIRWELL_REMAINS,
			LootTable.lootTable()
				//common material drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(6, 12))
					.add(LootItem.lootTableItem(RisusBlocks.SMILING_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.ASHEN_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.SPREADING_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(RisusBlocks.GRIMSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.GOLD_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusBlocks.BLOODWEAVE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES))
					.add(LootItem.lootTableItem(Items.DIAMOND)))
				//core roll
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(RisusItems.MEMORY_CORE))
					.add(LootItem.lootTableItem(RisusItems.CONCENTRATION_CORE)))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//very rare collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(10))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_FEIGR)))
		);
		register.accept(RisusLootTables.REACTOR,
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(6, 12))
					//common material drops
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.SMILING_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.ASHEN_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.SPREADING_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.SKIN).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusItems.HAIR_FOLLICLES).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(Items.BONE_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusBlocks.BURNT_HYPHAE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusItems.VEINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 10))))
					.add(LootItem.lootTableItem(RisusItems.STALKER_EYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.EGG_SAC).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusBlocks.EYE_BLOODSHOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(RisusBlocks.FLATTENED_SCALES_BLOCK))
					.add(LootItem.lootTableItem(RisusBlocks.ORGANIC_MATTER_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.DIAMOND_BLOCK)))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//higher chance collectibles
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(3))
					.add(LootItem.lootTableItem(RisusItems.MUSIC_DISC_FEIGR))
					.add(LootItem.lootTableItem(RisusItems.SMILE_PATTERN)))
		);
		register.accept(RisusLootTables.STORAGE_ROOM,
			LootTable.lootTable()
				//fill up chests with relatively okey loot
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(1, 6))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusBlocks.SMILING_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusBlocks.ASHEN_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusBlocks.SPREADING_REMAINS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusBlocks.SKIN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.HAIR_FOLLICLES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.BONE_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.ORGANIC_MATTER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.BOWL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
					.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.COBBLESTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.COBBLED_DEEPSLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.DEEPSLATE_BRICKS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))))
				//this one's on the house. It's a rare structure after all
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(3))
					.add(LootItem.lootTableItem(RisusItems.GUILTY_APPLE))
					.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES))
					.add(LootItem.lootTableItem(Items.DIAMOND)))

		);
		register.accept(RisusLootTables.CHURCH_HANGING,
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 6))
					.add(LootItem.lootTableItem(RisusBlocks.TISSUE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.LINEAR_RITUAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.GRIMSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.GRIMSTONE_PILLAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.BONE_PILLAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.BIG_CHAIN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.RED_MOSAIC_LAMP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.RED_MOSAIC_GLASS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))))
				//one roll of valuable drops
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING))
					.add(LootItem.lootTableItem(Items.EMERALD_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
					.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
				//rare-ish collectible
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(5))
					.add(LootItem.lootTableItem(RisusItems.DIVINITY_PATTERN)))
		);

		register.accept(RisusLootTables.CHURCH_TOMB,
			LootTable.lootTable()
				//common drops
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(3, 6))
					.add(LootItem.lootTableItem(RisusBlocks.EERIE_FENCE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.LINEAR_RITUAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.GRIMSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.GRIMSTONE_PILLAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.BONE_PILLAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.BLOODWEAVE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.RED_MOSAIC_LAMP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
					.add(LootItem.lootTableItem(RisusBlocks.RED_MOSAIC_GLASS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))))
				//core & sponge roll
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(RisusItems.MEMORY_CORE))
					.add(LootItem.lootTableItem(RisusBlocks.BLOODY_SPONGE))
					.add(LootItem.lootTableItem(RisusItems.CONCENTRATION_CORE)))
				//50/50 execration
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(Items.BOOK).apply(randomApplicableExecration(this.registries))))
		);

		register.accept(RisusLootTables.CHURCH_LITTER,
			LootTable.lootTable()
				//give them lights
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(10, 15))
					.add(LootItem.lootTableItem(Blocks.GLOWSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(Blocks.SHROOMLIGHT).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(Blocks.JACK_O_LANTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(Blocks.SEA_LANTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(Blocks.OCHRE_FROGLIGHT).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(Blocks.PEARLESCENT_FROGLIGHT).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(Blocks.VERDANT_FROGLIGHT).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(Blocks.CRYING_OBSIDIAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(RisusBlocks.LAUGHING_OBSIDIAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
					.add(LootItem.lootTableItem(RisusBlocks.WHITE_MOSAIC_LAMP).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15)))))
				//let's be nice to them
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(RisusItems.CONCENTRATION_CORE)))
		);



	}
	public static EnchantRandomlyFunction.Builder randomApplicableExecration(HolderLookup.Provider registries) {
		return new EnchantRandomlyFunction.Builder().withOneOf(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(RisusTags.Enchantments.EXECRATIONS));
	}

}
