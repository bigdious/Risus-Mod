package com.bigdious.risus.blocks.plantblocks;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class RisusVines {

	public static boolean isValidGrowthState(BlockState pState) {
		return pState.isAir();
	}

	public static int getBlocksToGrowWhenOrganicMattered(RandomSource pRandom) {
		double d0 = 1.0;

		int i;
		for (i = 0; pRandom.nextDouble() < d0; i++) {
			d0 *= 0.826;
		}

		return i;
	}
}
