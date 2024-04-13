package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ScaleplateBlock extends MultiDirectionalBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	private static final Map<FrontAndTop, VoxelShape> AABBS = ImmutableMap.<FrontAndTop, VoxelShape>builder()
			.put(FrontAndTop.NORTH_UP, Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D))
			.put(FrontAndTop.EAST_UP, Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D))
			.put(FrontAndTop.WEST_UP, Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D))
			.put(FrontAndTop.SOUTH_UP, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D))
			.put(FrontAndTop.UP_NORTH, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D))
			.put(FrontAndTop.UP_EAST, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D))
			.put(FrontAndTop.UP_WEST, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D))
			.put(FrontAndTop.UP_SOUTH, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D))
			.put(FrontAndTop.DOWN_NORTH, Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D))
			.put(FrontAndTop.DOWN_EAST, Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D))
			.put(FrontAndTop.DOWN_WEST, Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D))
			.put(FrontAndTop.DOWN_SOUTH, Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D)).build();

	public ScaleplateBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(ORIENTATION, FrontAndTop.NORTH_UP).setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		Direction direction = context.getNearestLookingDirection().getOpposite();
		Direction direction1 = switch (direction) {
			case DOWN -> context.getHorizontalDirection().getOpposite();
			case UP -> context.getHorizontalDirection();
			case NORTH, SOUTH, WEST, EAST -> Direction.UP;
		};

		return this.defaultBlockState()
				.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()))
				.setValue(ORIENTATION, FrontAndTop.fromFrontAndTop(direction, direction1));
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
		return AABBS.get(state.getValue(ORIENTATION));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}
}
