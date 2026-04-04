package com.bigdious.risus.worldgen.structures;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusTags;
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
	public static final ResourceKey<Structure> GRASSY_SITE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_site"));
	public static final ResourceKey<StructureSet> GRASSY_SITE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_site"));
	public static final ResourceKey<StructureTemplatePool> GRASSY_SITE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_site"));
	public static final ResourceKey<Structure> BURRIED_SITE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "burried_site"));
	public static final ResourceKey<StructureSet> BURRIED_SITE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "burried_site"));
	public static final ResourceKey<StructureTemplatePool> BURRIED_SITE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "burried_site"));
	public static final ResourceKey<Structure> BEDROCK_HAND = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bedrock_hand"));
	public static final ResourceKey<StructureSet> BEDROCK_HAND_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bedrock_hand"));
	public static final ResourceKey<StructureTemplatePool> BEDROCK_HAND_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bedrock_hand"));

	public static final ResourceKey<Structure> FAMILY_TREE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree"));
	public static final ResourceKey<StructureSet> FAMILY_TREE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree"));
	public static final ResourceKey<StructureTemplatePool> FAMILY_TREE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree"));
	public static final ResourceKey<StructureProcessorList> FAMILY_TREE_POPPING = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "family_tree_popping"));

	public static final ResourceKey<Structure> ANGEL_ALTAR = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "angel_altar"));
	public static final ResourceKey<StructureSet> ANGEL_ALTAR_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "angel_altar"));
	public static final ResourceKey<StructureTemplatePool> ANGEL_ALTAR_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "angel_altar"));

	public static final ResourceKey<Structure> CHURCH = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "church"));
	public static final ResourceKey<StructureSet> CHURCH_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "church"));
	public static final ResourceKey<StructureTemplatePool> CHURCH_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "church"));

	public static final ResourceKey<Structure> SKULL_FOSSIL = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "skull_fossil"));
	public static final ResourceKey<StructureSet> SKULL_FOSSIL_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "skull_fossil"));
	public static final ResourceKey<StructureTemplatePool> SKULL_FOSSIL_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "skull_fossil"));

	public static final ResourceKey<Structure> RIBS_FOSSIL = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "ribs_fossil"));
	public static final ResourceKey<StructureSet> RIBS_FOSSIL_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "ribs_fossil"));
	public static final ResourceKey<StructureTemplatePool> RIBS_FOSSIL_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "ribs_fossil"));

	public static final ResourceKey<StructureProcessorList> FOSSIL_FRAGMENTATION = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "fossil_fragmentation"));

	public static final ResourceKey<Structure> GREAT_BODY = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "great_body"));
	public static final ResourceKey<StructureSet> GREAT_BODY_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "great_body"));
	public static final ResourceKey<StructureTemplatePool> GREAT_BODY_A_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "great_body_a"));
	public static final ResourceKey<StructureTemplatePool> GREAT_BODY_B_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "great_body_b"));
	public static final ResourceKey<StructureProcessorList> GREAT_BODY_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "great_body_degradation"));

	public static final ResourceKey<Structure> FLOWER_FIELD = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field"));
	public static final ResourceKey<StructureSet> FLOWER_FIELD_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field"));
	public static final ResourceKey<StructureTemplatePool> FLOWER_FIELD_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field"));
	public static final ResourceKey<StructureProcessorList> FLOWER_FIELD_WITHERING = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "flower_field_withering"));
	public static final ResourceKey<Structure> HEART_CHAMBER = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "heart_chamber"));
	public static final ResourceKey<StructureSet> HEART_CHAMBER_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "heart_chamber"));
	public static final ResourceKey<StructureTemplatePool> HEART_CHAMBER_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "heart_chamber"));
	public static final ResourceKey<StructureProcessorList> HEART_CHAMBER_BLENDING = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "heart_chamber_blending"));
	public static final ResourceKey<Structure> BLOOD_WELL = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "blood_well"));
	public static final ResourceKey<StructureSet> BLOOD_WELL_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "blood_well"));
	public static final ResourceKey<StructureTemplatePool> BLOOD_WELL_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "blood_well"));
	public static final ResourceKey<Structure> LAB_START = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "lab_start"));
	public static final ResourceKey<StructureSet> LAB_START_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "lab_start"));
	public static final ResourceKey<StructureTemplatePool> LAB_START_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "lab_start"));
	public static final ResourceKey<Structure> DRAXOLOTL_REMAINS = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "draxolotl_remains"));
	public static final ResourceKey<StructureSet> DRAXOLOTL_REMAINS_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "draxolotl_remains"));
	public static final ResourceKey<StructureTemplatePool> DRAXOLOTL_REMAINS_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "draxolotl_remains"));
	public static final ResourceKey<StructureProcessorList> DRAXOLOTL_REMAINS_BLENDING = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "draxolotl_remains_blending"));

	public static final ResourceKey<StructureTemplatePool> SPREADER = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "spreader"));
	public static final ResourceKey<StructureTemplatePool> HEART_CHAMBER_ROOMS = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "heart_chamber_rooms"));
	public static final ResourceKey<StructureTemplatePool> SPAWNER = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "spawner"));
	public static final ResourceKey<StructureTemplatePool> LAB = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "lab"));
	public static final ResourceKey<StructureTemplatePool> CHURCH_STUFF = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "church_stuff"));
	public static final ResourceKey<StructureTemplatePool> RIGID_STUFF = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "rigid_stuff"));

	public static final ResourceKey<StructureProcessorList> LAB_SPREADING = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "lab_spreading"));
	public static final ResourceKey<StructureProcessorList> CHURCH_REPLACER = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "church_replacer"));

	public static void bootstrapStructures(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(CHURCH, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_CHURCH),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(CHURCH_POOL),
			Optional.empty(),
			10,
			ConstantHeight.of(VerticalAnchor.absolute(-21)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			100,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));

		context.register(SKULL_FOSSIL, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_FOSSILS),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(SKULL_FOSSIL_POOL),
			Optional.empty(),
			10,
			ConstantHeight.of(VerticalAnchor.absolute(-6)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			50,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));

		context.register(RIBS_FOSSIL, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_FOSSILS),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.BEARD_THIN
			),
			pools.getOrThrow(RIBS_FOSSIL_POOL),
			Optional.empty(),
			10,
			ConstantHeight.of(VerticalAnchor.absolute(0)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			50,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

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

		context.register(GRASSY_SITE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_GRASSY_SITE),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.BEARD_THIN
			),
			pools.getOrThrow(GRASSY_SITE_POOL),
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

		context.register(BURRIED_SITE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_GRASSY_SITE),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(BURRIED_SITE_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-8)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(BEDROCK_HAND, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_HEART_CHAMBER),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.UNDERGROUND_DECORATION,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(BEDROCK_HAND_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-64)),
			false,
			Optional.empty(),
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
				TerrainAdjustment.BEARD_THIN
			),
			pools.getOrThrow(ANGEL_ALTAR_POOL),
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

		context.register(GREAT_BODY, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_GREAT_BODY),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(GREAT_BODY_A_POOL),
			Optional.empty(),
			5,
			ConstantHeight.of(VerticalAnchor.absolute(-33)),
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
			true,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));

		context.register(HEART_CHAMBER, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_HEART_CHAMBER),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.ENCAPSULATE
			),
			pools.getOrThrow(HEART_CHAMBER_POOL),
			Optional.empty(),
			5,
			UniformHeight.of(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(-20)),
			false,
			Optional.empty(),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));

		context.register(BLOOD_WELL, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_BLOOD_WELL),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(BLOOD_WELL_POOL),
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
		context.register(LAB_START, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_LAB),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(LAB_START_POOL),
			Optional.empty(),
			6,
			ConstantHeight.of(VerticalAnchor.absolute(-3)),
			false,
			Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));
		context.register(DRAXOLOTL_REMAINS, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_DRAXOLOTL_REMAINS),
				Map.of(
					MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
						new MobSpawnSettings.SpawnerData(RisusEntities.HOLDER.get(), 10, 1, 2)
					))
				),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(DRAXOLOTL_REMAINS_POOL),
			Optional.empty(),
			6,
			ConstantHeight.of(VerticalAnchor.absolute(28)),
			false,
			Optional.empty(),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.IGNORE_WATERLOGGING
		));
	}
	public static void bootstrapSets(BootstrapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		context.register(CHURCH_SET, new StructureSet(structures.getOrThrow(CHURCH),
			new RandomSpreadStructurePlacement(180, 0, RandomSpreadType.TRIANGULAR, 867534873)));

		context.register(SKULL_FOSSIL_SET, new StructureSet(structures.getOrThrow(SKULL_FOSSIL),
			new RandomSpreadStructurePlacement(183, 0, RandomSpreadType.LINEAR, 3425687)));

		context.register(RIBS_FOSSIL_SET, new StructureSet(structures.getOrThrow(RIBS_FOSSIL),
			new RandomSpreadStructurePlacement(183, 0, RandomSpreadType.TRIANGULAR, 836475)));

		context.register(ALTERATION_SITE_SET, new StructureSet(structures.getOrThrow(ALTERATION_SITE),
			new RandomSpreadStructurePlacement(39, 0, RandomSpreadType.LINEAR, 1024321764)));

		context.register(GRASSY_SITE_SET, new StructureSet(structures.getOrThrow(GRASSY_SITE),
			new RandomSpreadStructurePlacement(136, 0, RandomSpreadType.LINEAR, 985328795)));

		context.register(BURRIED_SITE_SET, new StructureSet(structures.getOrThrow(BURRIED_SITE),
			new RandomSpreadStructurePlacement(204, 0, RandomSpreadType.LINEAR, 548753487)));

		context.register(BEDROCK_HAND_SET, new StructureSet(structures.getOrThrow(BEDROCK_HAND),
			new RandomSpreadStructurePlacement(51, 0, RandomSpreadType.TRIANGULAR, 364875348)));

		context.register(FAMILY_TREE_SET, new StructureSet(structures.getOrThrow(FAMILY_TREE),
			new RandomSpreadStructurePlacement(34, 0, RandomSpreadType.LINEAR, 5297)));

		context.register(ANGEL_ALTAR_SET, new StructureSet(structures.getOrThrow(ANGEL_ALTAR),
			new RandomSpreadStructurePlacement(37, 4, RandomSpreadType.LINEAR, 13414354)));

		context.register(GREAT_BODY_SET, new StructureSet(structures.getOrThrow(GREAT_BODY),
			new RandomSpreadStructurePlacement(47, 0, RandomSpreadType.LINEAR, 838347612)));

		context.register(FLOWER_FIELD_SET, new StructureSet(structures.getOrThrow(FLOWER_FIELD),
			new RandomSpreadStructurePlacement(53, 0, RandomSpreadType.LINEAR, 29213393)));

		context.register(HEART_CHAMBER_SET, new StructureSet(structures.getOrThrow(HEART_CHAMBER),
			new RandomSpreadStructurePlacement(34, 0, RandomSpreadType.LINEAR, 938752732)));

		context.register(BLOOD_WELL_SET, new StructureSet(structures.getOrThrow(BLOOD_WELL),
			new RandomSpreadStructurePlacement(19, 0, RandomSpreadType.LINEAR, 894328793)));

		context.register(LAB_START_SET, new StructureSet(structures.getOrThrow(LAB_START),
			new RandomSpreadStructurePlacement(180, 0, RandomSpreadType.LINEAR, 523141287)));

		context.register(DRAXOLOTL_REMAINS_SET, new StructureSet(structures.getOrThrow(DRAXOLOTL_REMAINS),
			new RandomSpreadStructurePlacement(225, 0, RandomSpreadType.LINEAR, 729472497)));
	}
	public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
		Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
		HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

		context.register(CHURCH_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("church_front"), processors.getOrThrow(CHURCH_REPLACER)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(SKULL_FOSSIL_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("skull_fossil"), processors.getOrThrow(FOSSIL_FRAGMENTATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(RIBS_FOSSIL_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("ribs_fossil"), processors.getOrThrow(FOSSIL_FRAGMENTATION)), 1),
			Pair.of(StructurePoolElement.single(name("ribs_fossil_tail"), processors.getOrThrow(FOSSIL_FRAGMENTATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(ALTERATION_SITE_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("alteration_site/0"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("alteration_site/1"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("alteration_site/2"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("alteration_site/3"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("alteration_site/4"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("alteration_site/5"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(BURRIED_SITE_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("burried_site/0"), processors.getOrThrow(ALTERATION_SITE_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(BEDROCK_HAND_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("bedrock_hand")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(GRASSY_SITE_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("grassy_site/0")), 1),
			Pair.of(StructurePoolElement.single(name("grassy_site/1")), 2)
		), StructureTemplatePool.Projection.RIGID));


		context.register(FAMILY_TREE_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("family_tree/0"), processors.getOrThrow(FAMILY_TREE_POPPING)), 5),
			Pair.of(StructurePoolElement.single(name("family_tree/1"), processors.getOrThrow(FAMILY_TREE_POPPING)), 3),
			Pair.of(StructurePoolElement.single(name("family_tree/2"), processors.getOrThrow(FAMILY_TREE_POPPING)), 3),
			Pair.of(StructurePoolElement.single(name("family_tree/3"), processors.getOrThrow(FAMILY_TREE_POPPING)), 2),
			Pair.of(StructurePoolElement.single(name("family_tree/4"), processors.getOrThrow(FAMILY_TREE_POPPING)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(ANGEL_ALTAR_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("angel_altar/0"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("angel_altar/1"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("angel_altar/2"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("angel_altar/3"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 2)
		), StructureTemplatePool.Projection.RIGID));

		context.register(GREAT_BODY_A_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("great_body_a/0"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("great_body_a/1"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("great_body_a/2"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("great_body_a/3"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(GREAT_BODY_B_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("great_body_b/0"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("great_body_b/0_connector")), 1),
			Pair.of(StructurePoolElement.single(name("great_body_b/1"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("great_body_b/1_connector")), 1),
			Pair.of(StructurePoolElement.single(name("great_body_b/2"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("great_body_b/2_connector")), 1),
			Pair.of(StructurePoolElement.single(name("great_body_b/3"), processors.getOrThrow(GREAT_BODY_DEGRADATION)), 1),
			Pair.of(StructurePoolElement.single(name("great_body_b/3_connector")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(FLOWER_FIELD_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("flower_field/field"),processors.getOrThrow(FLOWER_FIELD_WITHERING)), 1)
		), StructureTemplatePool.Projection.TERRAIN_MATCHING));


		context.register(HEART_CHAMBER_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("heart_chamber"),processors.getOrThrow(HEART_CHAMBER_BLENDING)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(BLOOD_WELL_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("blood_well")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(LAB_START_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("lab_entrance")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(DRAXOLOTL_REMAINS_POOL, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("draxolotl_remains"), processors.getOrThrow(DRAXOLOTL_REMAINS_BLENDING)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(SPREADER, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("spreader/default")), 1),
			Pair.of(StructurePoolElement.single(name("spreader/thirty")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(HEART_CHAMBER_ROOMS, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/holder_room"),processors.getOrThrow(HEART_CHAMBER_BLENDING)), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/weaver_room"),processors.getOrThrow(HEART_CHAMBER_BLENDING)), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/stalker_room"),processors.getOrThrow(HEART_CHAMBER_BLENDING)), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/singer_room"),processors.getOrThrow(HEART_CHAMBER_BLENDING)), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/hex_room"),processors.getOrThrow(HEART_CHAMBER_BLENDING)), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/licker_room"),processors.getOrThrow(HEART_CHAMBER_BLENDING)), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/regen_heart")), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/empty_heart")), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/wither_heart")), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/poison_heart")), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/absorption_heart")), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/health_boost_heart")), 1),
			Pair.of(StructurePoolElement.single(name("heart_chamber_rooms/bloodclogged_heart")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(LAB, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("lab/lab_containment"), processors.getOrThrow(LAB_SPREADING)), 1),
			Pair.of(StructurePoolElement.single(name("lab/lab_exit_hallway"), processors.getOrThrow(LAB_SPREADING)), 1),
			Pair.of(StructurePoolElement.single(name("lab/lab_stairwell"), processors.getOrThrow(LAB_SPREADING)), 1),
			Pair.of(StructurePoolElement.single(name("lab/pipe")), 1),
			Pair.of(StructurePoolElement.single(name("lab/lab_main"), processors.getOrThrow(LAB_SPREADING)), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(CHURCH_STUFF, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("church_stuff/back"), processors.getOrThrow(CHURCH_REPLACER)), 1),
			Pair.of(StructurePoolElement.single(name("church_stuff/entrance"), processors.getOrThrow(CHURCH_REPLACER)), 1),
			Pair.of(StructurePoolElement.single(name("church_stuff/main"), processors.getOrThrow(CHURCH_REPLACER)), 1),
			Pair.of(StructurePoolElement.single(name("church_stuff/ritual_0")), 1),
			Pair.of(StructurePoolElement.single(name("church_stuff/ritual_1")), 1),
			Pair.of(StructurePoolElement.single(name("church_stuff/ritual_2")), 1)

		), StructureTemplatePool.Projection.RIGID));

		context.register(RIGID_STUFF, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("rigid_stuff/statue")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(SPAWNER, new StructureTemplatePool(emptyPool, List.of(
			Pair.of(StructurePoolElement.single(name("spawner/holder")),  1),
			Pair.of(StructurePoolElement.single(name("spawner/weaver")), 1),
			Pair.of(StructurePoolElement.single(name("spawner/lover")), 1),
			Pair.of(StructurePoolElement.single(name("spawner/creeper")), 1),
			Pair.of(StructurePoolElement.single(name("spawner/spider")), 1),
			Pair.of(StructurePoolElement.single(name("spawner/hex")), 1),
			Pair.of(StructurePoolElement.single(name("spawner/enderman")), 1)
		), StructureTemplatePool.Projection.RIGID));
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
					new RandomBlockStateMatchTest(Blocks.POLISHED_BASALT.defaultBlockState().setValue(AXIS, Direction.Axis.Y), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ENGRAVED_BASALT.get().defaultBlockState().setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockStateMatchTest(Blocks.POLISHED_BASALT.defaultBlockState().setValue(AXIS, Direction.Axis.X), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ENGRAVED_BASALT.get().defaultBlockState().setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockStateMatchTest(Blocks.POLISHED_BASALT.defaultBlockState().setValue(AXIS, Direction.Axis.Z), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ENGRAVED_BASALT.get().defaultBlockState().setValue(AXIS, Direction.Axis.Z)
				)

			))
		)));

		context.register(CHURCH_REPLACER, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.GRIMSTONE.get(), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ACTIVE_GRIMSTONE.get().defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockStateMatchTest(Blocks.POLISHED_BASALT.defaultBlockState().setValue(AXIS, Direction.Axis.Y), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ENGRAVED_BASALT.get().defaultBlockState().setValue(AXIS, Direction.Axis.Y)
				),
				new ProcessorRule(
					new RandomBlockStateMatchTest(Blocks.POLISHED_BASALT.defaultBlockState().setValue(AXIS, Direction.Axis.X), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ENGRAVED_BASALT.get().defaultBlockState().setValue(AXIS, Direction.Axis.X)
				),
				new ProcessorRule(
					new RandomBlockStateMatchTest(Blocks.POLISHED_BASALT.defaultBlockState().setValue(AXIS, Direction.Axis.Z), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.ENGRAVED_BASALT.get().defaultBlockState().setValue(AXIS, Direction.Axis.Z)
				)

			))
		)));
		context.register(FOSSIL_FRAGMENTATION, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.FULL_FOSSIL.get(), 0.1F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.FOSSIL_FRAGMENTED.get().defaultBlockState()
				)
			)),
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.FOSSIL.get(), 0.1F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.FOSSIL_FRAGMENTED.get().defaultBlockState()
				)
			))
		)));

		context.register(GREAT_BODY_DEGRADATION, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
					new ProcessorRule(
						new RandomBlockMatchTest(RisusBlocks.SKIN.get(), 0.1F),
						AlwaysTrueTest.INSTANCE,
						RisusBlocks.HAIRY_SKIN.get().defaultBlockState()
					)
			)),
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.FULL_FOSSIL.get(), 0.2F),
					AlwaysTrueTest.INSTANCE,
					Blocks.STONE.defaultBlockState()
				)
			)),
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.FULL_FOSSIL.get(), 0.05F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.FOSSIL_FRAGMENTED.get().defaultBlockState()
				)
			))
		)));

		context.register(FLOWER_FIELD_WITHERING, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.REGEN_ROSE.get(), 0.9F),
					AlwaysTrueTest.INSTANCE,
					Blocks.AIR.defaultBlockState()
				)
			))
		)));

		context.register(HEART_CHAMBER_BLENDING, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
//				new ProcessorRule(
//					new RandomBlockMatchTest(RisusBlocks.LIVING_TISSUE.get(), 0.02F),
//					AlwaysTrueTest.INSTANCE,
//					RisusBlocks.ACTIVE_GRIMSTONE.get().defaultBlockState()
//				),
				new ProcessorRule(
				new RandomBlockMatchTest(RisusBlocks.LIVING_TISSUE.get(), 0.60F),
				AlwaysTrueTest.INSTANCE,
				Blocks.DEEPSLATE.defaultBlockState()
				)
			))
		)));

		context.register(LAB_SPREADING, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(Blocks.DEEPSLATE_BRICKS, 0.29F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.GRIMSTONE_BRICKS.get().defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockMatchTest(Blocks.DEEPSLATE_BRICKS, 0.01F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get().defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockMatchTest(Blocks.SMOOTH_STONE, 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.FULL_BONE_BLOCK.get().defaultBlockState()
				),
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.LIVING_TISSUE.get(), 0.3F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.TISSUE.get().defaultBlockState()
				)

			))
		)));

		context.register(DRAXOLOTL_REMAINS_BLENDING, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.BONE_WALL.get(), 0.6F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.TISSUE.get().defaultBlockState()
				)
			))
		)));

		context.register(FAMILY_TREE_POPPING, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				//I am guilty of warcrimes right here
				//Tissue Transform
				new ProcessorRule(
					new RandomBlockMatchTest(RisusBlocks.LIVING_TISSUE.get(), 0.2F),
					AlwaysTrueTest.INSTANCE,
					RisusBlocks.TISSUE.get().defaultBlockState()
				),

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
