package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusParticles;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class OrganicMatterBlock extends Block {
	public static final MapCodec<OrganicMatterBlock> CODEC = simpleCodec(OrganicMatterBlock::new);

	@Override
	public MapCodec<OrganicMatterBlock> codec() {
		return CODEC;
	}

	public OrganicMatterBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		ParticleUtils.spawnParticles(level, pos, 4, 0.5, 1, false, RisusParticles.BLOCK_ORGANIC_PARTICLE.get());
	}
}
