package com.bigdious.risus.worldgen.features.templates;

import com.bigdious.risus.Risus;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.ArrayList;

public class GrassyGorgerFeature extends Feature<NoneFeatureConfiguration> {
	private static final ResourceLocation GRASSY = ResourceLocation.fromNamespaceAndPath(Risus.MODID,"feature/gorger/grassy");

	public GrassyGorgerFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
		WorldGenLevel world = ctx.level();
		BlockPos pos = ctx.origin();
		RandomSource random = ctx.random();

		StructureTemplateManager templatemanager = world.getLevel().getServer().getStructureManager();
		StructureTemplate grassy = templatemanager.getOrCreate(GRASSY);
		if (grassy == null)
			return false;
		BlockPos placementPos = pos.offset(-1, -10, -1);

		grassy.placeInWorld(world, placementPos, placementPos, new StructurePlaceSettings(), random, Block.UPDATE_CLIENTS);

		return true;
	}
}
