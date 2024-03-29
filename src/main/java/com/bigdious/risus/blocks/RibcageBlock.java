package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("deprecation")
public class RibcageBlock extends BaseRotatableBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
		Direction.NORTH, Block.box(2.0D, 0.0D, 5.0D, 14.0D, 16.0D, 14.5D),
		Direction.SOUTH, Block.box(2.0D, 0.0D, 1.5D, 14.0D, 16.0D, 11.0D),
		Direction.WEST, Block.box(5.0D, 0.0D, 2.0D, 14.5D, 16.0D, 14.0D),
		Direction.EAST, Block.box(1.5D, 0.0D, 2.0D, 11.0D, 16.0D, 14.0D)));
	//start multilogging necessities
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
	public RibcageBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(HALF, DoubleBlockHalf.LOWER)
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
	}
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HALF)
			.add(BLOODLOGGED)
			.add(WATERLOGGED)
			.add(LAVALOGGED);
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
					.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
					.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
					.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER))
				: null;
		} else if (level.getBlockState(blockpos.above()).canBeReplaced() && blockpos.getY() < level.getMaxBuildHeight() - 1) {
			return level.getBlockState(blockpos.above().above()).isSolidRender(pContext.getLevel(), blockpos.above().above()) ?
				super.getStateForPlacement(pContext)
					.setValue(FACING, pContext.getHorizontalDirection())
					.setValue(HALF, DoubleBlockHalf.LOWER)
					.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
					.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
					.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER))
				: null;
		}
		return null;
	}
	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {return Fluids.LAVA.getSource().defaultFluidState();}
		if (pState.getValue(BLOODLOGGED)) {return RisusFluids.SOURCE_BLOOD.get().getSource().defaultFluidState();}
		if (pState.getValue(WATERLOGGED)) {return Fluids.WATER.getSource().defaultFluidState();}
		else return  super.getFluidState(pState);
	}
	@Override
	public BlockState updateShape(BlockState pState, Direction direction, BlockState neighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos neighborPos) {
		DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
		if (pState.getValue(BLOODLOGGED)) {
			pLevel.scheduleTick(pPos, RisusFluids.SOURCE_BLOOD.get(), 5);
		}
		if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}
		if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.is(this) && neighborState.getValue(HALF) != doubleblockhalf) {
			return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !pState.canSurvive(pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, direction, neighborState, pLevel, pPos, neighborPos);
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}
	//stop
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
		level.setBlock(blockpos, copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(HALF, top ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER).setValue(FACING, state.getValue(FACING))), 3);
	}

	public static void placeAt(LevelAccessor accessor, BlockState state, BlockPos pos, int flag) {
		BlockPos blockpos = pos.above();
		accessor.setBlock(pos, copyWaterloggedFrom(accessor, pos, state.setValue(HALF, DoubleBlockHalf.UPPER)), flag);
		accessor.setBlock(blockpos, copyWaterloggedFrom(accessor, blockpos, state.setValue(HALF, DoubleBlockHalf.LOWER)), flag);
	}

	public static BlockState copyWaterloggedFrom(LevelReader reader, BlockPos pos, BlockState state) {
		return state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, reader.isWaterAt(pos)) : state;
	}

	@Override
	public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide()) {
			if (player.isCreative()) {
				preventCreativeDropFromBottomPart(level, pos, state, player);
			} else {
				dropResources(state, level, pos, null, player, player.getMainHandItem());
			}
		}

		super.playerWillDestroy(level, pos, state, player);
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
