package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
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

public class RisusCeilingHangingSignBlock extends CeilingHangingSignBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusCeilingHangingSignBlock(Properties pProperties, WoodType pType) {
		super(pType, pProperties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(ROTATION, 0)
				.setValue(ATTACHED, false)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ROTATION, ATTACHED, WATERLOGGED, FLUIDLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		FluidState fluidstate = level.getFluidState(context.getClickedPos());
		BlockPos blockpos = context.getClickedPos().above();
		BlockState blockstate = level.getBlockState(blockpos);
		boolean flag = blockstate.is(BlockTags.ALL_HANGING_SIGNS);
		Direction direction = Direction.fromYRot(context.getRotation());
		boolean flag1 = !Block.isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.DOWN) || context.isSecondaryUseActive();
		if (flag && !context.isSecondaryUseActive()) {
			if (blockstate.hasProperty(WallHangingSignBlock.FACING)) {
				Direction direction1 = blockstate.getValue(WallHangingSignBlock.FACING);
				if (direction1.getAxis().test(direction)) {
					flag1 = false;
				}
			} else if (blockstate.hasProperty(ROTATION)) {
				Optional<Direction> optional = RotationSegment.convertToDirection(blockstate.getValue(ROTATION));
				if (optional.isPresent() && optional.get().getAxis().test(direction)) {
					flag1 = false;
				}
			}
		}

		int i = !flag1 ? RotationSegment.convertToSegment(direction.getOpposite()) : RotationSegment.convertToSegment(context.getRotation() + 180.0F);
		return this.defaultBlockState().setValue(ATTACHED, flag1)
				.setValue(ROTATION, i)
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
		return direction == Direction.UP && !this.canSurvive(state, accessor, pos) ? Blocks.AIR.defaultBlockState() : state;
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
