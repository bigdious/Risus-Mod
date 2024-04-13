package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RisusWallHangingSignBlock extends WallHangingSignBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusWallHangingSignBlock(Properties pProperties, WoodType pType) {
		super(pType, pProperties.sound(pType.hangingSignSoundType()));
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(FACING, Direction.NORTH)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, FLUIDLOGGED);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		BlockState blockstate = this.defaultBlockState();
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		LevelReader levelreader = pContext.getLevel();
		BlockPos blockpos = pContext.getClickedPos();

		for (Direction direction : pContext.getNearestLookingDirections()) {
			if (direction.getAxis().isHorizontal() && !direction.getAxis().test(pContext.getClickedFace())) {
				Direction direction1 = direction.getOpposite();
				blockstate = blockstate.setValue(FACING, direction1);
				if (blockstate.canSurvive(levelreader, blockpos) && this.canPlace(blockstate, levelreader, blockpos)) {
					return blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
				}
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
		return direction.getAxis() == state.getValue(FACING).getClockWise().getAxis() && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RisusHangingSignBlockEntity(pos, state);
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
