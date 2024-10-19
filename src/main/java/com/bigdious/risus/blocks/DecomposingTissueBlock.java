package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecomposingTissueBlock extends RisusWallBlock implements DecomposingBlock {

	private final DecomposeState state;

	public DecomposingTissueBlock(DecomposeState state, Properties properties) {
		super(properties);
		this.state = state;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return this.getAge() == DecomposeState.NONE ? Shapes.block() : super.getShape(state, getter, pos, context);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return this.getAge() == DecomposeState.NONE ? Shapes.block() : super.getCollisionShape(state, getter, pos, context);
	}


	public boolean connectsTo(BlockState state, boolean sturdy, Direction direction) {
		Block block = state.getBlock();
		boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
		return this.getAge() != DecomposeState.NONE && state.is(BlockTags.WALLS) || !isExceptionForConnection(state) && sturdy || block instanceof IronBarsBlock || flag;
	}


	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		this.changeOverTime(state, level, pos, random);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return DecomposingBlock.getNext(state.getBlock()).isPresent();
	}

	@Override
	public DecomposeState getAge() {
		return this.state;
	}
}
