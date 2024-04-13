package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("deprecation")
public class CrystallizedBondsBlock extends DirectionalBlock implements SimpleMultiloggedBlock {
	public static final MapCodec<CrystallizedBondsBlock> CODEC = simpleCodec(CrystallizedBondsBlock::new);
	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.UP, Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D),
			Direction.DOWN, Block.box(4.0D, 8.0D, 4.0D, 12.0D, 16.0D, 12.0D),
			Direction.EAST, Block.box(0.0D, 4.0D, 4.0D, 8.0D, 12.0D, 12.0D),
			Direction.WEST, Block.box(8.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D),
			Direction.NORTH, Block.box(4.0D, 4.0D, 8.0D, 12.0D, 12.0D, 16.0D),
			Direction.SOUTH, Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 8.0D)
	));

	public CrystallizedBondsBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
				.setValue(FACING, Direction.UP));
	}

	@Override
	protected MapCodec<? extends DirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, FLUIDLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction clicked = context.getClickedFace();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		BlockState state = defaultBlockState().setValue(FACING, clicked).setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
		if (this.canSurvive(state, context.getLevel(), context.getClickedPos())) {
			return state;
		}

		for (Direction dir : context.getNearestLookingDirections()) {
			state = this.defaultBlockState().setValue(FACING, dir.getOpposite());
			if (this.canSurvive(state, context.getLevel(), context.getClickedPos())) {
				return state;
			}
		}
		return null;
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

	//stop
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
