package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.world.gen.structure.BiggerJigsawStructures;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
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

public class RisusStructures {
	//based off of Undergarden's UGStructures class
	public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Risus.MODID);
	public static final DeferredHolder<StructureType<?>, StructureType<BiggerJigsawStructures>> BIGGER_JIGSAW = STRUCTURES.register("bigger_jigsaw", () -> () -> BiggerJigsawStructures.CODEC);
	public static final ResourceKey<Structure> ALTERATION_SITE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site"));
	public static final ResourceKey<StructureSet> ALTERATION_SITE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site"));
	public static final ResourceKey<StructureTemplatePool> ALTERATION_SITE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site"));
	public static final ResourceKey<StructureProcessorList> ALTERATION_SITE_DEGRADATION = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "alteration_site_degradation"));
	public static final ResourceKey<Structure> GRASSY_MAW = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_maw"));
	public static final ResourceKey<StructureSet> GRASSY_MAW_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_maw"));
	public static final ResourceKey<StructureTemplatePool> GRASSY_MAW_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "grassy_maw"));

//	public static final ResourceKey<Structure> SANDY_MAW = registerKey("sandy_maw");
//	public static final ResourceKey<Structure> ENDY_MAW = registerKey("endy_maw");
//	public static final ResourceKey<Structure> FAMILY_TREE = registerKey("family_tree");
//
//	public static final ResourceKey<Structure> FLOWER_FIELD = registerKey("flower_field");

	public static void bootstrapStructures(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(ALTERATION_SITE, new BiggerJigsawStructures(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_ALTERATION_SITE),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.BEARD_THIN
			),
			pools.getOrThrow(ALTERATION_SITE_POOL),
			Optional.empty(),
			5,
			UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.TOP),
			Optional.empty(),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(GRASSY_MAW, new BiggerJigsawStructures(
			new Structure.StructureSettings(
				biomes.getOrThrow(RisusTags.Biomes.HAS_GRASSY_MAW),
				Map.of(),
				GenerationStep.Decoration.SURFACE_STRUCTURES,
				TerrainAdjustment.NONE
			),
			pools.getOrThrow(GRASSY_MAW_POOL),
			Optional.empty(),
			5,
			UniformHeight.of(VerticalAnchor.belowTop(0), VerticalAnchor.BOTTOM),
			Optional.empty(),
			80,
			List.of(),
			DimensionPadding.ZERO,
			LiquidSettings.APPLY_WATERLOGGING
		));
	}
	public static void bootstrapSets(BootstrapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		context.register(ALTERATION_SITE_SET, new StructureSet(structures.getOrThrow(ALTERATION_SITE),
			new RandomSpreadStructurePlacement(30, 10, RandomSpreadType.TRIANGULAR, 1029231764)));

		context.register(GRASSY_MAW_SET, new StructureSet(structures.getOrThrow(GRASSY_MAW),
			new RandomSpreadStructurePlacement(10, 8, RandomSpreadType.TRIANGULAR, 29134693)));
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
	}


	private static String name(String name) {
		return ResourceLocation.fromNamespaceAndPath(Risus.MODID, name).toString();
	}
}
