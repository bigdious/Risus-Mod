package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class InactiveHolderBlock extends BaseRotatableBlock implements SimpleMultiloggedBlock {
	protected static final VoxelShape BOUNDING_BOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D);
	public InactiveHolderBlock(Properties properties) {
		super(properties);
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return BOUNDING_BOX;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

}
