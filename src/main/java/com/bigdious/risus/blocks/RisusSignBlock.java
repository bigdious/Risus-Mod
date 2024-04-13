package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RisusSignBlock extends StandingSignBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusSignBlock(Properties properties, WoodType type) {
		super(type, properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(ROTATION, 0)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ROTATION, WATERLOGGED, FLUIDLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		return this.defaultBlockState().setValue(ROTATION, RotationSegment.convertToSegment(pContext.getRotation() + 180.0F))
				.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
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

		return direction == Direction.DOWN && !this.canSurvive(state, accessor, pos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RisusSignBlockEntity(pos, state);
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
