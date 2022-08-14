package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AlterationMonolithBlockEntity extends BlockEntity {
	public AlterationMonolithBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.ALTERATION_MONOLITH.get(), pos, state);
	}

	public boolean shouldRenderFace(Direction direction) {
		return Block.shouldRenderFace(this.getBlockState(), this.getLevel(), this.getBlockPos(), direction, this.getBlockPos().relative(direction));
	}
}
