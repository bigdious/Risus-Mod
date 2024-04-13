package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("deprecation")
public class RibcageBlock extends BaseRotatableBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(2.0D, 0.0D, 5.0D, 14.0D, 16.0D, 14.5D),
			Direction.SOUTH, Block.box(2.0D, 0.0D, 1.5D, 14.0D, 16.0D, 11.0D),
			Direction.WEST, Block.box(5.0D, 0.0D, 2.0D, 14.5D, 16.0D, 14.0D),
			Direction.EAST, Block.box(1.5D, 0.0D, 2.0D, 11.0D, 16.0D, 14.0D)));

	public RibcageBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(HALF, DoubleBlockHalf.LOWER).setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HALF, FLUIDLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		BlockPos blockpos = pContext.getClickedPos();
		Level level = pContext.getLevel();
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		if (level.getBlockState(blockpos.below()).canBeReplaced() && blockpos.getY() > level.getMinBuildHeight() + 1) {
			return level.getBlockState(blockpos.above()).isSolidRender(pContext.getLevel(), blockpos.above()) ?
					super.getStateForPlacement(pContext)
							.setValue(FACING, pContext.getHorizontalDirection())
							.setValue(HALF, DoubleBlockHalf.UPPER)
							.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()))
					: null;
		} else if (level.getBlockState(blockpos.above()).canBeReplaced() && blockpos.getY() < level.getMaxBuildHeight() - 1) {
			return level.getBlockState(blockpos.above().above()).isSolidRender(pContext.getLevel(), blockpos.above().above()) ?
					super.getStateForPlacement(pContext)
							.setValue(FACING, pContext.getHorizontalDirection())
							.setValue(HALF, DoubleBlockHalf.LOWER)
							.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()))
					: null;
		}
		return null;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
		DoubleBlockHalf half = state.getValue(HALF);
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
		}
		if (direction.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.is(this) && neighborState.getValue(HALF) != half) {
			return half == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		if (state.getValue(HALF) != DoubleBlockHalf.LOWER) {
			BlockPos blockpos = pos.above();
			return reader.getBlockState(blockpos).isSolidRender(reader, pos.above());
		} else {
			BlockState blockstate = reader.getBlockState(pos.above());
			return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER;
		}
	}


	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		boolean top = state.getValue(HALF) == DoubleBlockHalf.UPPER;
		BlockPos blockpos = top ? pos.below() : pos.above();
		level.setBlock(blockpos, copyFluidLoggingFrom(level, blockpos, this.defaultBlockState().setValue(HALF, top ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER).setValue(FACING, state.getValue(FACING))), 3);
	}

	public static BlockState copyFluidLoggingFrom(LevelReader reader, BlockPos pos, BlockState state) {
		return state.hasProperty(FLUIDLOGGED) ? state.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(reader.getFluidState(pos).getType())) : state;
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide()) {
			if (player.isCreative()) {
				preventCreativeDropFromBottomPart(level, pos, state, player);
			} else {
				dropResources(state, level, pos, null, player, player.getMainHandItem());
			}
		}

		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack);
	}

	protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			BlockPos blockpos = pos.below();
			BlockState blockstate = level.getBlockState(blockpos);
			if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
				level.setBlock(blockpos, blockstate1, 35);
				level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			}
		}
	}

	@Override
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}
}
