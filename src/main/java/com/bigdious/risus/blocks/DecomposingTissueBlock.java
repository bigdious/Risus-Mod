package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecomposingTissueBlock extends WallBlock implements DecomposingBlock {

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

	@Override
	public boolean connectsTo(BlockState state, boolean sturdy, Direction direction) {
		return this.getAge() != DecomposeState.NONE && super.connectsTo(state, sturdy, direction);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		this.onRandomTick(state, level, pos, random);
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
