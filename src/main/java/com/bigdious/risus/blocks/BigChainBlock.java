package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BigChainBlock extends RotatedPillarBlock implements SimpleMultiloggedBlock {

	protected static final VoxelShape Y_AXIS_AABB = Block.box(4.5D, 0.0D, 4.5D, 11.5D, 16.0D, 11.5D);
	protected static final VoxelShape Z_AXIS_AABB = Block.box(4.5D, 4.5D, 0.0D, 11.5D, 11.5D, 16.0D);
	protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 4.5D, 4.5D, 16.0D, 11.5D, 11.5D);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public BigChainBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(AXIS, Direction.Axis.Y).setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
		}

		return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
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
