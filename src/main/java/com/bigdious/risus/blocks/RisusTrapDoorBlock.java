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
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;
import java.util.Optional;

public class RisusTrapDoorBlock extends TrapDoorBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusTrapDoorBlock(BlockSetType type, Properties properties) {
		super(type, properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(OPEN, false)
				.setValue(HALF, Half.BOTTOM)
				.setValue(POWERED, false)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, OPEN, HALF, POWERED, WATERLOGGED, FLUIDLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		BlockState blockstate = this.defaultBlockState();
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		Direction direction = pContext.getClickedFace();
		if (!pContext.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
			blockstate = blockstate.setValue(FACING, direction).setValue(HALF, pContext.getClickLocation().y - (double) pContext.getClickedPos().getY() > 0.5D ? Half.TOP : Half.BOTTOM);
		} else {
			blockstate = blockstate.setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(HALF, direction == Direction.UP ? Half.BOTTOM : Half.TOP);
		}

		if (pContext.getLevel().hasNeighborSignal(pContext.getClickedPos())) {
			blockstate = blockstate.setValue(OPEN, true).setValue(POWERED, true);
		}

		return blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
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

		return state;
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
