package com.bigdious.risus.worldgen.features.templates;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class GorgerFeature extends Feature<NoneFeatureConfiguration> {
	private final ResourceLocation type;

	public GorgerFeature(Codec<NoneFeatureConfiguration> config, ResourceLocation type) {
		super(config);
		this.type = type;
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
		WorldGenLevel world = ctx.level();
		BlockPos pos = ctx.origin();
		RandomSource random = ctx.random();

		StructureTemplateManager templatemanager = world.getLevel().getServer().getStructureManager();
		StructureTemplate gorger = templatemanager.getOrCreate(this.type);
		if (gorger == null)
			return false;
		BlockPos placementPos = pos.offset(-3, -11, -3);

		gorger.placeInWorld(world, placementPos, placementPos, new StructurePlaceSettings(), random, Block.UPDATE_CLIENTS);

		return true;
	}
}
