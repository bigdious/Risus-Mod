package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

public class RisusStairBlock extends StairBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusStairBlock(Supplier<BlockState> baseState, Properties properties) {
		super(baseState, properties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM)
				.setValue(SHAPE, StairsShape.STRAIGHT)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF, SHAPE, WATERLOGGED, FLUIDLOGGED);
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

		return direction.getAxis().isHorizontal() ? state.setValue(SHAPE, getStairsShape(state, accessor, pos)) : state;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		Direction direction = pContext.getClickedFace();
		BlockPos blockpos = pContext.getClickedPos();
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		BlockState blockstate = this.defaultBlockState()
				.setValue(FACING, pContext.getHorizontalDirection())
				.setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(pContext.getClickLocation().y - (double) blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP)
				.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
		return blockstate.setValue(SHAPE, getStairsShape(blockstate, pContext.getLevel(), blockpos));
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return SimpleMultiloggedBlock.super.canPlaceLiquid(player, getter, pos, state, fluid);
	}

	@Override
	public ItemStack pickupBlock(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		return SimpleMultiloggedBlock.super.pickupBlock(pPlayer, pLevel, pPos, pState);
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Optional.empty();
	}

	@Override
	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		return SimpleMultiloggedBlock.super.placeLiquid(pLevel, pPos, pState, pFluidState);
	}
}
