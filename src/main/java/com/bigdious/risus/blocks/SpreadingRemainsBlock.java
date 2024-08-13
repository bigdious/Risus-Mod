package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;

public class SpreadingRemainsBlock extends MultifaceBlock implements SimpleMultiloggedBlock,OrganicMatterableBlock {

	public static final MapCodec<SpreadingRemainsBlock> CODEC = simpleCodec(SpreadingRemainsBlock::new);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	private final MultifaceSpreader spreader = new MultifaceSpreader(this);

	public SpreadingRemainsBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected MapCodec<? extends MultifaceBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		super.createBlockStateDefinition(state);
		state.add( FLUIDLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
		if (!this.isValidStateForPlacement(getter, state, pos, direction)) {
			return null;
		} else {
			BlockState blockstate;
			if (state.is(this)) {
				blockstate = state;
			} else if (!state.getFluidState().isEmpty()) {
				blockstate = this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(state.getFluidState().getType()));
			} else {
				blockstate = this.defaultBlockState();
			}

			return blockstate.setValue(getFaceProperty(direction), true);
		}
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
	public boolean canBeReplaced(BlockState state, BlockPlaceContext blockplace) {
		return !blockplace.getItemInHand().is(RisusItems.SPREADING_REMAINS.get()) || super.canBeReplaced(state, blockplace);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public MultifaceSpreader getSpreader() {
		return this.spreader;
	}

	@Override
	public boolean isValidOrganicMatterTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
		return Direction.stream().anyMatch(p_153316_ -> this.spreader.canSpreadInAnyDirection(pState, pLevel, pPos, p_153316_.getOpposite()));
	}
	@Override
	public boolean isOrganicMatterSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		return true;
	}

	@Override
	public void performOrganicMatter(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		this.spreader.spreadFromRandomFaceTowardRandomDirection(pState, pLevel, pPos, pRandom);
	}
}
