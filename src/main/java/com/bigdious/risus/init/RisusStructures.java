package com.bigdious.risus.init;

import com.bigdious.risus.Risus;


import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.bigdious.risus.blocks.PoppingBondknotBlock.POP_SIDE;
import static net.minecraft.world.level.block.RotatedPillarBlock.AXIS;

public class RisusStructures {
	//based off of Undergarden's UGStructures class
	public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Risus.MODID);
	public static final ResourceKey<Structure> ALTERATION_SITE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site"));
	public static final ResourceKey<StructureSet> ALTERATION_SITE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site"));
	public static final ResourceKey<StructureTemplatePool> ALTERATION_SITE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site"));
	public static final ResourceKey<StructureProcessorList> ALTERATION_SITE_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site_degradation"));
	public static final ResourceKey<Structure> GRASSY_MAW = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_maw"));
	public static final ResourceKey<StructureSet> GRASSY_MAW_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_maw"));
	public static final ResourceKey<StructureTemplatePool> GRASSY_MAW_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_maw"));

	public static final ResourceKey<Structure> SANDY_MAW = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "sandy_maw"));
	public static final ResourceKey<StructureSet> SANDY_MAW_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "sandy_maw"));
	public static final ResourceKey<StructureTemplatePool> SANDY_MAW_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "sandy_maw"));

	public static final ResourceKey<Structure> ENDY_MAW = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "endy_maw"));
	public static final ResourceKey<StructureSet> ENDY_MAW_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "endy_maw"));
	public static final ResourceKey<StructureTemplatePool> ENDY_MAW_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "endy_maw"));

	public static final ResourceKey<Structure> FAMILY_TREE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree"));
	public static final ResourceKey<StructureSet> FAMILY_TREE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree"));
	public static final ResourceKey<StructureTemplatePool> FAMILY_TREE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree"));
	public static final ResourceKey<StructureProcessorList> FAMILY_TREE_POPPING = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree_popping"));

	public static final ResourceKey<Structure> ANGEL_ALTAR = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "angel_altar"));
	public static final ResourceKey<StructureSet> ANGEL_ALTAR_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "angel_altar"));
	public static final ResourceKey<StructureTemplatePool> ANGEL_ALTAR_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "angel_altar"));

	public static final ResourceKey<Structure> FLOWER_FIELD = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field"));
	public static final ResourceKey<StructureSet> FLOWER_FIELD_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field"));
	public static final ResourceKey<StructureTemplatePool> FLOWER_FIELD_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field"));
	public static final ResourceKey<StructureProcessorList> FLOWER_FIELD_WITHERING = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field_withering"));

	public static void bootstrapStructures(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(ALTERATION_SITE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_ALTERATION_SITE),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.BEARD_THIN
			),
			pools.getOrThrow(ALTERATION_SITE_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(0)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(GRASSY_MAW, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_GRASSY_MAW),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(GRASSY_MAW_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-9)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(SANDY_MAW, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_SANDY_MAW),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(SANDY_MAW_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-9)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(ENDY_MAW, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_ENDY_MAW),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(ENDY_MAW_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-9)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(FAMILY_TREE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_FAMILY_TREE),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.BEARD_THIN
			),
			pools.getOrThrow(FAMILY_TREE_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-2)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(ANGEL_ALTAR, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_ANGEL_ALTAR),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(ANGEL_ALTAR_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-2)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));

		context.register(FLOWER_FIELD, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_FLOWER_FIELD),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.BEARD_THIN
			),
			pools.getOrThrow(FLOWER_FIELD_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-1)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));
	}
	public static void bootstrapSets(BootstrapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		context.register(ALTERATION_SITE_SET, new StructureSet(structures.getOrThrow(ALTERATION_SITE),
			new RandomSpreadStructurePlacement(20, 10, RandomSpreadType.TRIANGULAR, 1024321764)));

		context.register(GRASSY_MAW_SET, new StructureSet(structures.getOrThrow(GRASSY_MAW),
			new RandomSpreadStructurePlacement(10, 8, RandomSpreadType.TRIANGULAR, 22123393)));

		context.register(SANDY_MAW_SET, new StructureSet(structures.getOrThrow(SANDY_MAW),
			new RandomSpreadStructurePlacement(10, 8, RandomSpreadType.TRIANGULAR, 34523493)));

		context.register(ENDY_MAW_SET, new StructureSet(structures.getOrThrow(ENDY_MAW),
			new RandomSpreadStructurePlacement(10, 8, RandomSpreadType.TRIANGULAR, 52445123)));

		context.register(FAMILY_TREE_SET, new StructureSet(structures.getOrThrow(FAMILY_TREE),
			new RandomSpreadStructurePlacement(25, 4, RandomSpreadType.LINEAR, 1029739264)));

		context.register(ANGEL_ALTAR_SET, new StructureSet(structures.getOrThrow(ANGEL_ALTAR),
			new RandomSpreadStructurePlacement(20, 10, RandomSpreadType.TRIANGULAR, 1341435524)));

		context.register(FLOWER_FIELD_SET, new StructureSet(structures.getOrThrow(FLOWER_FIELD),
			new RandomSpreadStructurePlacement(21, 20, RandomSpreadType.TRIANGULAR, 29213393)));
	}
	public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
		Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
		HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

		context.register(ALTERATION_SITE_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("alteration_site/0"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(GRASSY_MAW_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("grassy_maw")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(SANDY_MAW_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("sandy_maw")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(ENDY_MAW_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("endy_maw")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(FAMILY_TREE_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("family_tree/0"), processors.getOrThrow(FAMILY_TREE_POPPING)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(ANGEL_ALTAR_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("angel_altar/0")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(FLOWER_FIELD_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("flower_field"),processors.getOrThrow(FLOWER_FIELD_WITHERING)), 1)
		), StructureTemplatePool.Projection.TERRAIN_MATCHING));
	}
	public static void bootstrapProcessors(BootstrapContext<StructureProcessorList> context) {
		context.register(ALTERATION_SITE_DEGRADATION, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.SPREADING_REMAINS.get(), 0.5F),
					AlwaysTrueTest.INSTANCE,
					Blocks.AIR.defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockMatchTest(Blocks.POLISHED_BASALT, 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ENGRAVED_BASALT.get().defaultBlockState()
				)
			))
		)));

		context.register(FLOWER_FIELD_WITHERING, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.REGEN_ROSE.get(), 0.3F),
					AlwaysTrueTest.INSTANCE,
					Blocks.AIR.defaultBlockState()
				)
			))
		)));

		context.register(FAMILY_TREE_POPPING, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				//I am guilty of warcrimes right here

				//Wood transform
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.NORTH).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.SOUTH).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.WEST).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.EAST).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.UP).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.DOWN).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.NORTH).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.SOUTH).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.WEST).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.EAST).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.UP).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.DOWN).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.NORTH).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.SOUTH).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.WEST).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.EAST).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.UP).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_WOOD.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_WOOD.get().defaultBlockState().setValue(POP_SIDE, Direction.DOWN).setValue(AXIS, Direction.Axis.Z)
				),

				//now logs
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.NORTH).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.SOUTH).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.WEST).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.EAST).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.UP).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.DOWN).setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.NORTH).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.SOUTH).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.WEST).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.EAST).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.UP).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.DOWN).setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.NORTH).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.SOUTH).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.WEST).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.EAST).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.UP).setValue(AXIS, Direction.Axis.Z)
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONDKNOT_LOG.get(), 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.POPPING_BONDKNOT_LOG.get().defaultBlockState().setValue(POP_SIDE, Direction.DOWN).setValue(AXIS, Direction.Axis.Z)
				)
				//I am not sorry, I am unskilled
			))

		)));
	}


	private static String name(String name) {
		return ResourceLocation.fromNamespaceAndPath(Risus.MODID, name).toString();
	}
}
