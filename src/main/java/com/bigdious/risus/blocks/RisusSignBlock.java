package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusSignBlockEntity;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RisusSignBlock extends StandingSignBlock implements SimpleMultiloggedBlock{

	public RisusSignBlock(Properties properties, WoodType type) {
		super(type, properties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(ROTATION, Integer.valueOf(0))
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
	}
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder
			.add(BLOODLOGGED)
			.add(LAVALOGGED);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		return this.defaultBlockState().setValue(ROTATION, Integer.valueOf(RotationSegment.convertToSegment(pContext.getRotation() + 180.0F)))
			.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
			.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}
	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {return Fluids.LAVA.getSource().defaultFluidState();}
		if (pState.getValue(BLOODLOGGED)) {return RisusFluids.SOURCE_BLOOD.get().getSource().defaultFluidState();}
		if (pState.getValue(WATERLOGGED)) {return Fluids.WATER.getSource().defaultFluidState();}
		else return  super.getFluidState(pState);
	}
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		if (pState.getValue(BLOODLOGGED)) {
			pLevel.scheduleTick(pCurrentPos, RisusFluids.SOURCE_BLOOD.get(), 5);
		}
		if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pCurrentPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		return pFacing == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RisusSignBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> GameEventListener getListener(ServerLevel pLevel, T pBlockEntity) {
		return super.getListener(pLevel, pBlockEntity);
	}

	@Override
	public boolean isEnabled(FeatureFlagSet pEnabledFeatures) {
		return super.isEnabled(pEnabledFeatures);
	}

	@Override
	public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
		return pFluid == RisusFluids.SOURCE_BLOOD.get() || pFluid == Fluids.LAVA || pFluid == Fluids.WATER;
	}

	@Override
	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		//blood
		if (!pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED) && pFluidState.getType() == RisusFluids.SOURCE_BLOOD.get()) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
						.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(true))
						.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		//lava
		if (!pState.getValue(SimpleMultiloggedBlock.LAVALOGGED) && pFluidState.getType() == Fluids.LAVA) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
						.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(true))
						.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		//water
		if (!pState.getValue(SimpleMultiloggedBlock.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(true))
						.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ItemStack pickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		//blood
		if (pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(RisusItems.BLOOD_BUCKET.get());
		}
		//lava
		if (pState.getValue(SimpleMultiloggedBlock.LAVALOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(Items.LAVA_BUCKET);
		}
		//water
		if (pState.getValue(SimpleMultiloggedBlock.WATERLOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(Items.WATER_BUCKET);
		} else
		{
			return ItemStack.EMPTY;
		}
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}
}
