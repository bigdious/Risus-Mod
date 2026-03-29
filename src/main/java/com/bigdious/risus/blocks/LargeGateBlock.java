package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

//this is an abomination of a fence door
public class LargeGateBlock extends HorizontalDirectionalBlock implements SimpleMultiloggedBlock {
	public static final MapCodec<LargeGateBlock> CODEC = simpleCodec(LargeGateBlock::new);

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final BooleanProperty OPEN;
	public static final BooleanProperty REVERSE_OPENING = BooleanProperty.create("reverse_opening");
	public static final BooleanProperty POWERED;
	public static final EnumProperty<DoubleBlockHalf> HALF;
	protected static final VoxelShape SOUTH_AABB;
	protected static final VoxelShape SOUTH_OPEN_AABB;
	protected static final VoxelShape SOUTH_OPEN_REVERSE_AABB;
	protected static final VoxelShape NORTH_AABB;
	protected static final VoxelShape NORTH_OPEN_AABB;
	protected static final VoxelShape NORTH_OPEN_REVERSE_AABB;
	protected static final VoxelShape WEST_AABB;
	protected static final VoxelShape WEST_OPEN_AABB;
	protected static final VoxelShape WEST_OPEN_REVERSE_AABB;
	protected static final VoxelShape EAST_AABB;
	protected static final VoxelShape EAST_OPEN_AABB;
	protected static final VoxelShape EAST_OPEN_REVERSE_AABB;

	public MapCodec<LargeGateBlock> codec() {
		return CODEC;
	}

	public LargeGateBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(FACING, Direction.NORTH)
			.setValue(OPEN, false)
			.setValue(POWERED, false)
			.setValue(REVERSE_OPENING, false)
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
			.setValue(HALF, DoubleBlockHalf.LOWER));

	}

	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(FACING);
		Boolean open = state.getValue(OPEN);
		Boolean reversed = state.getValue(REVERSE_OPENING);
		VoxelShape shape;
		switch (direction) {
			case SOUTH -> shape = reversed ? SOUTH_OPEN_REVERSE_AABB : open ? SOUTH_OPEN_AABB : SOUTH_AABB;
			case WEST -> shape = reversed ? WEST_OPEN_REVERSE_AABB : open ? WEST_OPEN_AABB : WEST_AABB;
			case NORTH -> shape = reversed ? NORTH_OPEN_REVERSE_AABB : open ? NORTH_OPEN_AABB : NORTH_AABB;
			default -> shape = reversed ? EAST_OPEN_REVERSE_AABB : open ? EAST_OPEN_AABB : EAST_AABB;
		}

		return shape;
	}

	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			level.scheduleTick(currentPos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(level));
		}
		if (facingState.is(this.asBlock()) && facingState.getValue(HALF) == state.getValue(HALF) && facingState.getValue(FACING) == state.getValue(FACING).getOpposite()) {
			return state.setValue(OPEN, facingState.getValue(OPEN)).setValue(REVERSE_OPENING, facingState.getValue(OPEN) && !facingState.getValue(REVERSE_OPENING));
		}
		if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
			return facingState.getBlock() instanceof LargeGateBlock && facingState.getValue(HALF) != doubleblockhalf ? facingState.setValue(HALF, doubleblockhalf) : Blocks.AIR.defaultBlockState();
		} else {
			return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide && (player.isCreative() || !player.hasCorrectToolForDrops(state, level, pos))) {
			RibcageBlock.preventCreativeDropFromBottomPart(level, pos, state, player);
		}
		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		boolean var10000;
		switch (pathComputationType) {
			case LAND:
			case AIR:
				var10000 = state.getValue(OPEN);
				break;
			case WATER:
				var10000 = false;
				break;
			default:
				throw new MatchException(null, null);
		}

		return var10000;
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(blockpos);
		Direction direction = context.getHorizontalDirection();
		boolean flag = level.hasNeighborSignal(blockpos) || level.hasNeighborSignal(blockpos.above());
		if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context)) {

		return this.defaultBlockState().setValue(FACING, direction)
			.setValue(OPEN, flag)
			.setValue(POWERED, flag)
			.setValue(HALF, DoubleBlockHalf.LOWER)
			.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
		} else {
			return null;
		}
	}

	@Override
	protected long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.getValue(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED) == SimpleMultiloggedBlock.MultiloggingEnum.LAVA ? 15 : 0;
	}


	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		level.setBlock(pos.above(), copyFluidLoggingFrom(level, pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(FACING, state.getValue(FACING))), 3);
	}

	public static BlockState copyFluidLoggingFrom(LevelReader reader, BlockPos pos, BlockState state) {
		return state.hasProperty(FLUIDLOGGED) ? state.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(reader.getFluidState(pos).getType())) : state;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (state.getValue(OPEN)) {
			state = state.setValue(OPEN, false).setValue(REVERSE_OPENING, false);
			level.setBlock(pos, state, 10);
		} else {
			Direction direction = player.getDirection();
			if (state.getValue(FACING) == direction.getClockWise()) {
				state = state.setValue(REVERSE_OPENING, true);
			}

			state = state.setValue(OPEN, true);
			level.setBlock(pos, state, 10);
		}
			this.playSound(player, level, pos, state.getValue(OPEN));
			level.gameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
			return InteractionResult.sidedSuccess(level.isClientSide);
	}


	public boolean isOpen(BlockState state) {
		return state.getValue(OPEN);
	}

	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		boolean flag = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.relative(state.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
		if (!this.defaultBlockState().is(block) && flag != state.getValue(POWERED)) {
			if (flag != state.getValue(OPEN)) {
				this.playSound(null, level, pos, flag);
				level.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
			}

			level.setBlock(pos, state.setValue(POWERED, flag).setValue(OPEN, flag).setValue(REVERSE_OPENING, (!state.getValue(POWERED) || !state.getValue(OPEN)) && state.getValue(REVERSE_OPENING)), 2);
		}

	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState blockstate = level.getBlockState(blockpos);
		return state.getValue(HALF) == DoubleBlockHalf.LOWER || blockstate.is(this);
	}


	private void playSound(@Nullable Entity source, Level level, BlockPos pos, boolean isOpening) {
		level.playSound(source, pos, isOpening ? SoundEvents.IRON_DOOR_OPEN : SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, FACING, OPEN, POWERED, FLUIDLOGGED, REVERSE_OPENING);
	}

	static {
		OPEN = BlockStateProperties.OPEN;
		HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
		POWERED = BlockStateProperties.POWERED;
		SOUTH_AABB = Block.box(7.0F, 0.0F, 0.0F, 9.0F, 16.0F, 16.0F);
		SOUTH_OPEN_AABB = Block.box(-5.5F, 0.0F, 0.0F, 9F, 16.0F, 3.0F);
		SOUTH_OPEN_REVERSE_AABB = Block.box(7.0F, 0.0F, 0.0F, 21.5F, 16.0F, 3.0F);
		NORTH_AABB = Block.box(7.0F, 0.0F, 0.0F, 9.0F, 16.0F, 16.0F);
		NORTH_OPEN_AABB = Block.box(7.0F, 0.0F, 13.0F, 21.5F, 16.0F, 16.0F);
		NORTH_OPEN_REVERSE_AABB = Block.box(-5.5F, 0.0F, 13.0F, 9.0F, 16.0F, 16.0F);
		WEST_AABB = Block.box(0.0F, 0.0F, 7.0F, 16.0F, 16.0F, 9.0F);
		WEST_OPEN_AABB = Block.box(13.0F, 0.0F, -5.5F, 16.0F, 16.0F, 9.0F);
		WEST_OPEN_REVERSE_AABB = Block.box(13.0F, 0.0F, 7.0F, 16.0F, 16.0F, 21.5F);
		EAST_AABB = Block.box(0.0F, 0.0F, 7.0F, 16.0F, 16.0F, 9.0F);
		EAST_OPEN_AABB = Block.box(0.0F, 0.0F, 7.0F, 3.0F, 16.0F, 21.5F);
		EAST_OPEN_REVERSE_AABB = Block.box(0.0F, 0.0F, -5.5F, 3.0F, 16.0F, 9.0F);
	}
}
