package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.stream.Collectors;

public class BlockLootTables extends BlockLoot {

	private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));

	@Override
	protected void addTables() {
		add(RisusBlocks.ALTERATION_CATALYST.get(), LootTable.lootTable());
		dropSelf(RisusBlocks.ASHEN_REMAINS.get());
		dropSelf(RisusBlocks.SMILING_REMAINS.get());
		dropSelf(RisusBlocks.BLOODWEAVE.get());
		add(RisusBlocks.CRYSTALLIZED_BONDS.get(), createSilkTouchDispatchTable(RisusBlocks.CRYSTALLIZED_BONDS.get(), LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
		dropSelf(RisusBlocks.LAUGHING_OBSIDIAN.get());
		dropSelf(RisusBlocks.ENGRAVED_BASALT.get());
		dropSelf(RisusBlocks.MAW_GUTS.get());
		add(RisusBlocks.MIRAGE_GRASS_BLOCK.get(), LootTable.lootTable());
		add(RisusBlocks.MIRAGE_SAND.get(), LootTable.lootTable());
		add(RisusBlocks.MIRAGE_NETHERRACK.get(), LootTable.lootTable());
		add(RisusBlocks.MIRAGE_END_STONE.get(), LootTable.lootTable());
		add(RisusBlocks.GLUTTONY_SCALEPLATE.get(), createSilkTouchDispatchTable(RisusBlocks.GLUTTONY_SCALEPLATE.get(), LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		add(RisusBlocks.BABY_RIBCAGE.get(), createSilkTouchDispatchTable(RisusBlocks.BABY_RIBCAGE.get(), LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(HAS_SILK_TOUCH.invert()))));
		add(RisusBlocks.RIBCAGE.get(), createRibcageDrops(RisusBlocks.RIBCAGE.get()));
		dropOther(RisusBlocks.BLOODWYRM_HEAD.get(), RisusItems.BLOODWYRM_HEAD.get());
		dropOther(RisusBlocks.BLOODWYRM_WALL_HEAD.get(), RisusItems.BLOODWYRM_HEAD.get());

		dropSelf(RisusBlocks.BONDKNOT_LOG.get());
		dropSelf(RisusBlocks.BONDKNOT_WOOD.get());
		dropOther(RisusBlocks.POPPING_BONDKNOT_LOG.get(), RisusBlocks.BONDKNOT_LOG.get());
		dropOther(RisusBlocks.POPPING_BONDKNOT_WOOD.get(), RisusBlocks.BONDKNOT_WOOD.get());
		dropSelf(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
		dropSelf(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
		dropSelf(RisusBlocks.BONDKNOT_PLANKS.get());
		dropSelf(RisusBlocks.BONDKNOT_STAIRS.get());
		dropSelf(RisusBlocks.BONDKNOT_SLAB.get());
		dropSelf(RisusBlocks.BONDKNOT_BUTTON.get());
		dropSelf(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		dropSelf(RisusBlocks.BONDKNOT_FENCE.get());
		dropSelf(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
		dropSelf(RisusBlocks.BONDKNOT_TRAPDOOR.get());
		add(RisusBlocks.BONDKNOT_DOOR.get(), createSinglePropConditionTable(RisusBlocks.BONDKNOT_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropOther(RisusBlocks.BONDKNOT_SIGN.get(), RisusBlocks.BONDKNOT_SIGN.get().asItem());
		dropOther(RisusBlocks.BONDKNOT_WALL_SIGN.get(), RisusBlocks.BONDKNOT_SIGN.get().asItem());

		dropSelf(RisusBlocks.GRIMSTONE.get());
		dropSelf(RisusBlocks.GRIMSTONE_BRICKS.get());
		dropSelf(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		dropSelf(RisusBlocks.GRIMSTONE_STAIRS.get());
		dropSelf(RisusBlocks.GRIMSTONE_SLAB.get());
		dropSelf(RisusBlocks.GRIMSTONE_WALL.get());
		dropSelf(RisusBlocks.CHISELED_GRIMSTONE.get());
		dropSelf(RisusBlocks.POLISHED_GRIMSTONE.get());

		add(RisusBlocks.JOYFLAME_FIRE.get(), LootTable.lootTable());
		dropSelf(RisusBlocks.BIG_CHAIN.get());
		dropSelf(RisusBlocks.HEART_TRANSPLANT.get());
		dropSelf(RisusBlocks.REGEN_ROSE.get());
		add(RisusBlocks.POTTED_HEART_TRANSPLANT.get(), createPotFlowerItemTable(RisusItems.HEART_TRANSPLANT.get()));
		dropSelf(RisusBlocks.BONE_FENCE.get());
		dropSelf(RisusBlocks.BONE_WALL.get());
		dropSelf(RisusBlocks.TISSUE.get());
		dropSelf(RisusBlocks.ROTTING_TISSUE.get());
		dropSelf(RisusBlocks.DECOMPOSING_TISSUE.get());
		dropSelf(RisusBlocks.DECAYING_TISSUE.get());
		dropSelf(RisusBlocks.LIVING_TISSUE.get());
		dropSelf(RisusBlocks.ROTTED_TISSUE.get());
		dropSelf(RisusBlocks.DECOMPOSED_TISSUE.get());
		dropSelf(RisusBlocks.DECAYED_TISSUE.get());
		dropSelf(RisusBlocks.BURNT_HYPHAE.get());
		dropSelf(RisusBlocks.JOYFLAME_LANTERN.get());
		dropOther(RisusBlocks.JOYFLAME_TORCH.get(), RisusItems.JOYFLAME_TORCH.get());
		dropOther(RisusBlocks.JOYFLAME_WALL_TORCH.get(), RisusItems.JOYFLAME_TORCH.get());
		add(RisusBlocks.JOYFLAME_CAMPFIRE.get(), createSilkTouchDispatchTable(RisusBlocks.JOYFLAME_CAMPFIRE.get(), LootItem.lootTableItem(RisusItems.ASHEN_REMAINS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		dropWhenSilkTouch(RisusBlocks.ZIT.get());
		dropOther(RisusBlocks.NEURON_STEM.get(), RisusItems.NEURON_STEM.get());
		dropOther(RisusBlocks.NEURON_HEAD.get(), RisusItems.NEURON_STEM.get());
		add(RisusBlocks.WEAVER_NEST.get(), LootTable.lootTable());
	}

	protected static LootTable.Builder createRibcageDrops(Block block) {
		LootPoolEntryContainer.Builder<?> boneBuilder = LootItem.lootTableItem(block)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
				.when(HAS_SILK_TOUCH)
				.otherwise(applyExplosionCondition(block, LootItem.lootTableItem(Items.BONE)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 6)))
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
		LootPoolEntryContainer.Builder<?> bondBuilder = LootItem.lootTableItem(block)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
				.when(HAS_SILK_TOUCH)
				.otherwise(applyExplosionCondition(block, LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND.get())
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
		return LootTable.lootTable()
				.withPool(LootPool.lootPool().add(boneBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()),
								new BlockPos(0, 1, 0))))
				.withPool(LootPool.lootPool().add(bondBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()),
								new BlockPos(0, 1, 0))))
				.withPool(LootPool.lootPool().add(boneBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()),
								new BlockPos(0, -1, 0))))
				.withPool(LootPool.lootPool().add(bondBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()),
								new BlockPos(0, -1, 0))));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace().equals(Risus.MODID)).collect(Collectors.toList());
	}
}
