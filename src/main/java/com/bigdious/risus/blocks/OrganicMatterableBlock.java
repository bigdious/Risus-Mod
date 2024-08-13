package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface OrganicMatterableBlock {
	boolean isValidOrganicMatterTarget(LevelReader pLevel, BlockPos pPos, BlockState pState);

	boolean isOrganicMatterSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState);

	void performOrganicMatter(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState);

	default BlockPos getOMBParticlePos(BlockPos pPos) {
		return switch (this.getOMBType()) {
			case NEIGHBOR_SPREADER -> pPos.above();
			case GROWER -> pPos;
		};
	}

	default OrganicMatterableBlock.Type getOMBType() {
		return OrganicMatterableBlock.Type.GROWER;
	}

	public static enum Type {
		NEIGHBOR_SPREADER,
		GROWER;
	}
}
