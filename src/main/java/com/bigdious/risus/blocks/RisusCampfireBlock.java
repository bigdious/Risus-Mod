package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusCampfireBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RisusCampfireBlock extends CampfireBlock {
	public RisusCampfireBlock(boolean fireDamage, int amount, Properties properties) {
		super(fireDamage, amount, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RisusCampfireBlockEntity(pos, state);
	}
}
