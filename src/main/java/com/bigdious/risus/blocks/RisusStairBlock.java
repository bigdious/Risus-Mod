package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.Optional;

public class RisusStairBlock extends StairBlock implements SimpleMultiloggedBlock {
	public RisusStairBlock(BlockState pBaseState, Properties pProperties) {
		super(pBaseState, pProperties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM)
			.setValue(SHAPE, StairsShape.STRAIGHT)
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
	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {return Fluids.LAVA.getSource().defaultFluidState();}
		if (pState.getValue(BLOODLOGGED)) {return RisusFluids.SOURCE_BLOOD.get().getSource().defaultFluidState();}
		if (pState.getValue(WATERLOGGED)) {return Fluids.WATER.getSource().defaultFluidState();}
		else return  super.getFluidState(pState);
	}
	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
		if (pState.getValue(BLOODLOGGED)) {
			pLevel.scheduleTick(pPos, RisusFluids.SOURCE_BLOOD.get(), 5);
		}
		if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		return pDirection.getAxis().isHorizontal() ? pState.setValue(SHAPE, getStairsShape(pState, pLevel, pPos)) : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
	}
	private static StairsShape getStairsShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		Direction direction = pState.getValue(FACING);
		BlockState blockstate = pLevel.getBlockState(pPos.relative(direction));
		if (isStairs(blockstate) && pState.getValue(HALF) == blockstate.getValue(HALF)) {
			Direction direction1 = blockstate.getValue(FACING);
			if (direction1.getAxis() != pState.getValue(FACING).getAxis() && canTakeShape(pState, pLevel, pPos, direction1.getOpposite())) {
				if (direction1 == direction.getCounterClockWise()) {
					return StairsShape.OUTER_LEFT;
				}

				return StairsShape.OUTER_RIGHT;
			}
		}

		BlockState blockstate1 = pLevel.getBlockState(pPos.relative(direction.getOpposite()));
		if (isStairs(blockstate1) && pState.getValue(HALF) == blockstate1.getValue(HALF)) {
			Direction direction2 = blockstate1.getValue(FACING);
			if (direction2.getAxis() != pState.getValue(FACING).getAxis() && canTakeShape(pState, pLevel, pPos, direction2)) {
				if (direction2 == direction.getCounterClockWise()) {
					return StairsShape.INNER_LEFT;
				}

				return StairsShape.INNER_RIGHT;
			}
		}

		return StairsShape.STRAIGHT;
	}
	private static boolean canTakeShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pFace) {
		BlockState blockstate = pLevel.getBlockState(pPos.relative(pFace));
		return !isStairs(blockstate) || blockstate.getValue(FACING) != pState.getValue(FACING) || blockstate.getValue(HALF) != pState.getValue(HALF);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		Direction direction = pContext.getClickedFace();
		BlockPos blockpos = pContext.getClickedPos();
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		BlockState blockstate = this.defaultBlockState()
			.setValue(FACING, pContext.getHorizontalDirection())
			.setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(pContext.getClickLocation().y - (double) blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP)
			.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
			.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
		return blockstate.setValue(SHAPE, getStairsShape(blockstate, pContext.getLevel(), blockpos));
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
	public  boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
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
