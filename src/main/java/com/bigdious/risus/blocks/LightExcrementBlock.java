package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class LightExcrementBlock extends ActuallyUseableDirectionalBlock implements SimpleMultiloggedBlock {
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	//facing happens with top not bottom, so all shapes are reversed
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
		Direction.UP, Block.box(5.0, 0.0, 5.0, 11.0, 2.0, 11.0),
		Direction.DOWN, Block.box(5.0, 14.0, 5.0, 11.0, 16.0, 11.0),
		Direction.EAST, Block.box(0.0, 5.0, 5.0, 2.0, 11.0, 11.0),
		Direction.WEST, Block.box(14.0, 5.0, 5.0, 16.0, 11.0, 11.0),
		Direction.NORTH, Block.box(5.0, 5.0, 14.0, 11.0, 11.0, 16.0),
		Direction.SOUTH, Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 2.0)
	));

	public LightExcrementBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
			.setValue(FACING, Direction.UP));
	}
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		var state = super.getStateForPlacement(context);
		if (state != null) {
			FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
			state = state.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
		}
		return state;
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

		if (!this.canSurvive(state, accessor, pos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}
	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		Direction facing = state.getValue(DirectionalBlock.FACING);
		BlockPos restingPos = pos.relative(facing.getOpposite());
		return canSupportCenter(world, restingPos, facing);
	}
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}
}
