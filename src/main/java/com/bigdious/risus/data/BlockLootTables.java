package com.bigdious.risus.data;

import com.bigdious.risus.block.RisusBlocks;
import com.bigdious.risus.items.RisusItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;

import java.util.HashSet;
import java.util.Set;

public class BlockLootTables extends net.minecraft.data.loot.BlockLootTables {

	private final Set<Block> knownBlocks = new HashSet<>();

	@Override
	protected void registerLootTable(Block block, LootTable.Builder builder) {
		super.registerLootTable(block, builder);
		knownBlocks.add(block);
	}

	@Override
	protected void addTables() {
		registerDropSelfLootTable(RisusBlocks.ASHEN_REMAINS.get());
		registerDropSelfLootTable(RisusBlocks.SMILING_REMAINS.get());
		registerDropSelfLootTable(RisusBlocks.BLOODWEAVE.get());
		registerSilkTouch(RisusBlocks.CRYSTALIZED_BONDS.get());
		registerDropSelfLootTable(RisusBlocks.LAUGHING_OBSIDIAN.get());
		registerDropSelfLootTable(RisusBlocks.ENGRAVED_BASALT.get());
		registerDropSelfLootTable(RisusBlocks.MAW_GUTS.get());
		registerLootTable(RisusBlocks.BABY_RIBCAGE.get(), droppingWithSilkTouch(RisusBlocks.BABY_RIBCAGE.get(), ItemLootEntry.builder(Items.BONE).acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
		registerLootTable(RisusBlocks.RIBCAGE.get(), droppingWithSilkTouch(RisusBlocks.RIBCAGE.get(), ItemLootEntry.builder(Items.BONE).acceptFunction(SetCount.builder(RandomValueRange.of(3, 6))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
		registerDropping(RisusBlocks.BLOODWYRM_HEAD.get(), RisusItems.BLOODWYRM_HEAD.get());
		registerDropping(RisusBlocks.BLOODWYRM_WALL_HEAD.get(), RisusItems.BLOODWYRM_HEAD.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_LOG.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_WOOD.get());
		registerDropSelfLootTable(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
		registerDropSelfLootTable(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_PLANKS.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_STAIRS.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_SLAB.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_BUTTON.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_FENCE.get());
		registerDropSelfLootTable(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
		registerDropSelfLootTable(RisusBlocks.GRIMSTONE.get());
		registerDropSelfLootTable(RisusBlocks.GRIMSTONE_BRICKS.get());
		registerDropSelfLootTable(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		registerDropSelfLootTable(RisusBlocks.GRIMSTONE_STAIRS.get());
		registerDropSelfLootTable(RisusBlocks.GRIMSTONE_SLAB.get());
		registerDropSelfLootTable(RisusBlocks.GRIMSTONE_WALL.get());
		registerDropSelfLootTable(RisusBlocks.CHISELED_GRIMSTONE.get());
		registerDropSelfLootTable(RisusBlocks.POLISHED_GRIMSTONE.get());


	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return knownBlocks;
	}
}
