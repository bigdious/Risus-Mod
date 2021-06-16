package com.bigdious.risus.block;


import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class CrystalizedBondsBlock extends DirectionalBlock implements IWaterLoggable {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape UP_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D));
	protected static final VoxelShape DOWN_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D));
	protected static final VoxelShape EAST_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D));
	protected static final VoxelShape WEST_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D));
	protected static final VoxelShape NORTH_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D));
	protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D));

	public CrystalizedBondsBlock(Properties properties) {
		super(properties);
		this.setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.UP).with(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
			case DOWN:
				return DOWN_SHAPE;
			case UP:
			default:
				return UP_SHAPE;
			case NORTH:
				return NORTH_SHAPE;
			case SOUTH:
				return SOUTH_SHAPE;
			case WEST:
				return WEST_SHAPE;
			case EAST:
				return EAST_SHAPE;
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction clicked = context.getFace();
		FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
		BlockState state = getDefaultState().with(FACING, clicked).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);

		if (isValidPosition(state, context.getWorld(), context.getPos())) {
			return state;
		}

		for (Direction dir : context.getNearestLookingDirections()) {
			state = getDefaultState().with(FACING, dir.getOpposite());
			if (isValidPosition(state, context.getWorld(), context.getPos())) {
				return state;
			}
		}
		return null;
	}

	@Override
	@Deprecated
	public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		if (!isValidPosition(state, world, pos)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return super.updatePostPlacement(state, direction, neighborState, world, pos, neighborPos);
		}
	}

	@Override
	@Deprecated
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		Direction facing = state.get(DirectionalBlock.FACING);
		BlockPos restingPos = pos.offset(facing.getOpposite());
		return hasEnoughSolidSide(world, restingPos, facing);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING, WATERLOGGED);
	}
}
