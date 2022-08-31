package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BigChainBlock extends ChainBlock {

	protected static final VoxelShape Y_AXIS_AABB = Block.box(4.5D, 0.0D, 4.5D, 11.5D, 16.0D, 11.5D);
	protected static final VoxelShape Z_AXIS_AABB = Block.box(4.5D, 4.5D, 0.0D, 11.5D, 11.5D, 16.0D);
	protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 4.5D, 4.5D, 16.0D, 11.5D, 11.5D);

	public BigChainBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(AXIS)) {
			case X -> X_AXIS_AABB;
			case Z -> Z_AXIS_AABB;
			case Y -> Y_AXIS_AABB;
		};
	}
}
