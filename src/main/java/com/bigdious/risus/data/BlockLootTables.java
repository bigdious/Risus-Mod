package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlockLootTables extends BlockLootSubProvider {

	protected BlockLootTables(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void generate() {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		add(RisusBlocks.ALTERATION_CATALYST.get(), LootTable.lootTable());
		add(RisusBlocks.WEAVER_NEST.get(), LootTable.lootTable());
		add(RisusBlocks.ANGEL_ALTAR.get(), LootTable.lootTable());
		dropSelf(RisusBlocks.DEPTH_VASE.get());
		dropSelf(RisusBlocks.DISPLAY_NOTCH.get());
		dropSelf(RisusBlocks.DISPLAY_NOTCH_STAND.get());
		dropSelf(RisusBlocks.LAUGHING_STALK.get());
		dropSelf(RisusBlocks.ASHEN_REMAINS.get());
		dropSelf(RisusBlocks.SMILING_REMAINS.get());
		dropSelf(RisusBlocks.ORGANIC_MATTER_BLOCK.get());
		dropSelf(RisusBlocks.BLOODWEAVE.get());
		add(RisusBlocks.CRYSTALLIZED_BONDS.get(), createSilkTouchDispatchTable(RisusBlocks.CRYSTALLIZED_BONDS.get(), LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
		dropSelf(RisusBlocks.LAUGHING_OBSIDIAN.get());
		dropSelf(RisusBlocks.ENGRAVED_BASALT.get());
		dropSelf(RisusBlocks.MAW_GUTS.get());
		add(RisusBlocks.MIRAGE_GRASS_BLOCK.get(), createSilkTouchDispatchTable(RisusBlocks.MIRAGE_GRASS_BLOCK.get(), LootItem.lootTableItem(Blocks.DIRT).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		add(RisusBlocks.MIRAGE_SAND.get(), createSilkTouchDispatchTable(RisusBlocks.MIRAGE_SAND.get(), LootItem.lootTableItem(Blocks.SAND).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		add(RisusBlocks.MIRAGE_NETHERRACK.get(), createSilkTouchDispatchTable(RisusBlocks.MIRAGE_NETHERRACK.get(), LootItem.lootTableItem(Blocks.NETHERRACK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		add(RisusBlocks.MIRAGE_END_STONE.get(), createSilkTouchDispatchTable(RisusBlocks.MIRAGE_END_STONE.get(), LootItem.lootTableItem(Blocks.END_STONE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		add(RisusBlocks.GLUTTONY_SCALEPLATE.get(), createSilkTouchDispatchTable(RisusBlocks.GLUTTONY_SCALEPLATE.get(), LootItem.lootTableItem(RisusItems.GLUTTONY_SCALES.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		dropSelf(RisusBlocks.IMITATION_SCALEPLATE.get());
		dropSelf(RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get());
		dropSelf(RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get());
		dropSelf(RisusBlocks.FLATTENED_SCALES_BLOCK.get());
		dropSelf(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get());
		add(RisusBlocks.BABY_RIBCAGE.get(), createSilkTouchDispatchTable(RisusBlocks.BABY_RIBCAGE.get(), LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
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
		dropOther(RisusBlocks.BONDKNOT_HANGING_SIGN.get(), RisusBlocks.BONDKNOT_HANGING_SIGN.get().asItem());
		dropOther(RisusBlocks.BONDKNOT_WALL_HANGING_SIGN.get(), RisusBlocks.BONDKNOT_HANGING_SIGN.get().asItem());

		dropSelf(RisusBlocks.GRIMSTONE.get());
		dropSelf(RisusBlocks.GRIMSTONE_BRICKS.get());
		dropSelf(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		dropSelf(RisusBlocks.GRIMSTONE_STAIRS.get());
		dropSelf(RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get());
		dropSelf(RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get());
		dropSelf(RisusBlocks.GRIMSTONE_SLAB.get());
		dropSelf(RisusBlocks.GRIMSTONE_BRICKS_SLAB.get());
		dropSelf(RisusBlocks.POLISHED_GRIMSTONE_SLAB.get());
		dropSelf(RisusBlocks.GRIMSTONE_WALL.get());
		dropSelf(RisusBlocks.GRIMSTONE_BRICKS_WALL.get());
		dropSelf(RisusBlocks.POLISHED_GRIMSTONE_WALL.get());
		dropSelf(RisusBlocks.CHISELED_GRIMSTONE.get());
		dropSelf(RisusBlocks.POLISHED_GRIMSTONE.get());
		dropSelf(RisusBlocks.CURVED_RITUAL_BLOCK.get());
		dropSelf(RisusBlocks.LINEAR_RITUAL_BLOCK.get());


		add(RisusBlocks.JOYFLAME_FIRE.get(), LootTable.lootTable());
		dropSelf(RisusBlocks.BIG_CHAIN.get());
		dropSelf(RisusBlocks.HEART_TRANSPLANT.get());
		dropSelf(RisusBlocks.REGEN_ROSE.get());
		add(RisusBlocks.POTTED_HEART_TRANSPLANT.get(), createPotFlowerItemTable(RisusItems.HEART_TRANSPLANT.get()));
		add(RisusBlocks.POTTED_REGEN_ROSE.get(), createPotFlowerItemTable(RisusItems.REGEN_ROSE.get()));
		dropSelf(RisusBlocks.BONE_WALL.get());
		dropSelf(RisusBlocks.BONE_STAIRS.get());
		dropSelf(RisusBlocks.BONE_SLAB.get());
		dropSelf(RisusBlocks.BURNT_HYPHAE.get());
		dropSelf(RisusBlocks.SCAB.get());
		dropSelf(RisusBlocks.COAGULATED_BLOOD_BLOCK.get());

		dropSelf(RisusBlocks.SKIN.get());
		dropSelf(RisusBlocks.FLESHY_SKIN.get());
		dropSelf(RisusBlocks.CURVED_FLESHY_SKIN.get());
		dropSelf(RisusBlocks.HAIRY_SKIN.get());
		dropSelf(RisusBlocks.HAIRY_FLESHY_SKIN.get());
		dropSelf(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get());
		add(RisusBlocks.TALL_HAIR.get(), createShearsOnlyDrop(RisusItems.TALL_HAIR.get()));
		dropSelf(RisusBlocks.BUNDLE_OF_HAIR.get());

		dropSelf(RisusBlocks.TISSUE.get());
		dropSelf(RisusBlocks.ROTTING_TISSUE.get());
		dropSelf(RisusBlocks.DECOMPOSING_TISSUE.get());
		dropSelf(RisusBlocks.DECAYING_TISSUE.get());
		dropSelf(RisusBlocks.LIVING_TISSUE.get());
		dropSelf(RisusBlocks.ROTTED_TISSUE.get());
		dropSelf(RisusBlocks.DECOMPOSED_TISSUE.get());
		dropSelf(RisusBlocks.DECAYED_TISSUE.get());
		dropSelf(RisusBlocks.TISSUE_STAIRS.get());
		dropSelf(RisusBlocks.TISSUE_SLAB.get());
		dropSelf(RisusBlocks.BONE_STAIRS.get());
		dropSelf(RisusBlocks.BONE_SLAB.get());
		dropSelf(RisusBlocks.BONE_WALL.get());
		dropSelf(RisusBlocks.FULL_BONE_BLOCK.get());
		dropSelf(RisusBlocks.FULL_BONE_SLAB.get());
		dropSelf(RisusBlocks.FULL_BONE_STAIRS.get());

		dropSelf(RisusBlocks.EYE_ENDER.get());
		dropSelf(RisusBlocks.EYE_BLEACHED.get());
		dropSelf(RisusBlocks.EYE_BLOODSHOT.get());
		dropSelf(RisusBlocks.EYE_GOLDEN.get());
		dropSelf(RisusBlocks.EYE_EMERALD.get());


		dropSelf(RisusBlocks.JOYFLAME_LANTERN.get());
		dropOther(RisusBlocks.JOYFLAME_TORCH.get(), RisusItems.JOYFLAME_TORCH.get());
		dropOther(RisusBlocks.JOYFLAME_WALL_TORCH.get(), RisusItems.JOYFLAME_TORCH.get());
		add(RisusBlocks.JOYFLAME_CAMPFIRE.get(), createSilkTouchDispatchTable(RisusBlocks.JOYFLAME_CAMPFIRE.get(), LootItem.lootTableItem(RisusItems.ASHEN_REMAINS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))));
		dropSelf(RisusBlocks.ZIT.get());
		dropOther(RisusBlocks.NEURON_STEM.get(), RisusItems.NEURON_STEM.get());
		dropOther(RisusBlocks.NEURON_HEAD.get(), RisusItems.NEURON_STEM.get());
		dropOther(RisusBlocks.VEINS.get(), RisusItems.VEINS.get());
		dropOther(RisusBlocks.VEINS_END.get(), RisusItems.VEINS.get());
		add(RisusBlocks.SPREADING_REMAINS.get(), createMultifaceBlockDrops(RisusBlocks.SPREADING_REMAINS.get()));
		add(RisusBlocks.TEETH.get(), createMultifaceBlockDrops(RisusBlocks.TEETH.get()));
	}

	// Copied from BlockLootSubProvider#createMultifaceBlockDrops with removed LootItemCondition.Builder param
	protected LootTable.Builder createMultifaceBlockDrops(MultifaceBlock block) {
		LootPoolSingletonContainer.Builder<?> builder = LootItem.lootTableItem(block)
				.apply(Direction.values(), direction -> SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(direction), true))))
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true));
		return LootTable.lootTable().withPool(LootPool.lootPool().add(this.applyExplosionDecay(block, builder)));
	}

	protected LootTable.Builder createRibcageDrops(Block block) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

		LootPoolEntryContainer.Builder<?> boneBuilder = LootItem.lootTableItem(block)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
				.when(hasSilkTouch())
				.otherwise(applyExplosionCondition(block, LootItem.lootTableItem(Items.BONE)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 6)))
						.apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
		LootPoolEntryContainer.Builder<?> bondBuilder = LootItem.lootTableItem(block)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
				.when(hasSilkTouch())
				.otherwise(applyExplosionCondition(block, LootItem.lootTableItem(RisusItems.CRYSTALLIZED_BOND.get())
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
						.apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
		return LootTable.lootTable()
				.withPool(LootPool.lootPool().add(boneBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))),
								new BlockPos(0, 1, 0))))
				.withPool(LootPool.lootPool().add(bondBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))),
								new BlockPos(0, 1, 0))))
				.withPool(LootPool.lootPool().add(boneBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))),
								new BlockPos(0, -1, 0))))
				.withPool(LootPool.lootPool().add(bondBuilder)
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
						.when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
										.setBlock(BlockPredicate.Builder.block().of(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))),
								new BlockPos(0, -1, 0))));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return RisusBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
	}
}
