package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class RisusSlabBlock extends Block implements SimpleMultiloggedBlock{
	//vanillacopy for multilogging
	public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
	protected static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	protected static final VoxelShape TOP_AABB = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public RisusSlabBlock(BlockBehaviour.Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.defaultBlockState()
			.setValue(TYPE, SlabType.BOTTOM)
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
	}

	public boolean useShapeForLightOcclusion(BlockState pState) {
		return pState.getValue(TYPE) != SlabType.DOUBLE;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(TYPE)
			.add(BLOODLOGGED)
			.add(WATERLOGGED)
			.add(LAVALOGGED);
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		SlabType slabtype = pState.getValue(TYPE);
		switch (slabtype) {
			case DOUBLE:
				return Shapes.block();
			case TOP:
				return TOP_AABB;
			default:
				return BOTTOM_AABB;
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		BlockPos blockpos = pContext.getClickedPos();
		BlockState blockstate = pContext.getLevel().getBlockState(blockpos);
		if (blockstate.is(this)) {
			return blockstate.setValue(TYPE, SlabType.DOUBLE)
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(BLOODLOGGED, Boolean.valueOf(false))
				.setValue(LAVALOGGED, Boolean.valueOf(false));
		} else {
			FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);
			BlockState blockstate1 = this.defaultBlockState()
				.setValue(TYPE, SlabType.BOTTOM)
				.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
				.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
			Direction direction = pContext.getClickedFace();
			return direction != Direction.DOWN && (direction == Direction.UP || !(pContext.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? blockstate1 : blockstate1.setValue(TYPE, SlabType.TOP);
		}
	}

	public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
		ItemStack itemstack = pUseContext.getItemInHand();
		SlabType slabtype = pState.getValue(TYPE);
		if (slabtype != SlabType.DOUBLE && itemstack.is(this.asItem())) {
			if (pUseContext.replacingClickedOnBlock()) {
				boolean flag = pUseContext.getClickLocation().y - (double)pUseContext.getClickedPos().getY() > 0.5D;
				Direction direction = pUseContext.getClickedFace();
				if (slabtype == SlabType.BOTTOM) {
					return direction == Direction.UP || flag && direction.getAxis().isHorizontal();
				} else {
					return direction == Direction.DOWN || !flag && direction.getAxis().isHorizontal();
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {
			return Fluids.LAVA.getSource().defaultFluidState();
		}
		if (pState.getValue(BLOODLOGGED)) {
			return RisusFluids.SOURCE_BLOOD.get().getSource().defaultFluidState();
		}
		if (pState.getValue(WATERLOGGED)) {
			return Fluids.WATER.getSource().defaultFluidState();
		} else return super.getFluidState(pState);
	}

	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		return pState.getValue(TYPE) != SlabType.DOUBLE ? SimpleMultiloggedBlock.super.placeLiquid(pLevel, pPos, pState, pFluidState) : false;
	}

	public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
		return pState.getValue(TYPE) != SlabType.DOUBLE ? SimpleMultiloggedBlock.super.canPlaceLiquid(pLevel, pPos, pState, pFluid) : false;
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

		return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
	}

	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		switch (pType) {
			case LAND:
				return false;
			case WATER:
				return pLevel.getFluidState(pPos).is(FluidTags.WATER);
			case AIR:
				return false;
			default:
				return false;
		}
	}
}
