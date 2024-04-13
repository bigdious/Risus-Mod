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
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;
import java.util.Optional;

public class RisusSlabBlock extends SlabBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusSlabBlock(BlockBehaviour.Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(TYPE, SlabType.BOTTOM)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED, FLUIDLOGGED);
	}


	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		BlockPos blockpos = pContext.getClickedPos();
		BlockState blockstate = pContext.getLevel().getBlockState(blockpos);
		if (blockstate.is(this)) {
			return blockstate.setValue(TYPE, SlabType.DOUBLE)
					.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY);
		} else {
			FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);
			BlockState blockstate1 = this.defaultBlockState()
					.setValue(TYPE, SlabType.BOTTOM)
					.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
			Direction direction = pContext.getClickedFace();
			return direction != Direction.DOWN && (direction == Direction.UP || !(pContext.getClickLocation().y - (double) blockpos.getY() > 0.5D)) ? blockstate1 : blockstate1.setValue(TYPE, SlabType.TOP);
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

		return state;
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return state.getValue(TYPE) != SlabType.DOUBLE && SimpleMultiloggedBlock.super.canPlaceLiquid(player, getter, pos, state, fluid);
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
