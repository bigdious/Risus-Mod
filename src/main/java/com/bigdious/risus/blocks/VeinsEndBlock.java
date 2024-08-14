package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.plantblocks.RisusGrowingPlantHeadBlock;
import com.bigdious.risus.blocks.plantblocks.RisusVines;
import com.bigdious.risus.init.RisusBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VeinsEndBlock extends RisusGrowingPlantHeadBlock implements SimpleMultiloggedBlock {
	public static final MapCodec<VeinsEndBlock> CODEC = simpleCodec(VeinsEndBlock::new);
	protected static final VoxelShape SHAPE = Block.box(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	@Override
	public MapCodec<VeinsEndBlock> codec() {
		return CODEC;
	}

	public VeinsEndBlock(BlockBehaviour.Properties p_154966_) {
		super(p_154966_, Direction.DOWN, SHAPE, true, 0.1);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected int getBlocksToGrowWhenOrganicMattered(RandomSource p_222680_) {
		return RisusVines.getBlocksToGrowWhenOrganicMattered(p_222680_);
	}

	@Override
	protected Block getBodyBlock() {
		return RisusBlocks.VEINS.get();
	}

	@Override
	protected boolean canGrowInto(BlockState p_154971_) {
		return RisusVines.isValidGrowthState(p_154971_);
	}
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
		if (direction == this.growthDirection.getOpposite() && !state.canSurvive(accessor, pos)) {
			accessor.scheduleTick(pos, this, 1);
		}

		if (direction != this.growthDirection || !neighborState.is(this) && !neighborState.is(this.getBodyBlock())) {
			if (this.scheduleFluidTicks && state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
				accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
			}

			return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
		} else {
			return this.updateBodyAfterConvertedFromHead(state, this.getBodyBlock().defaultBlockState().setValue(FLUIDLOGGED, state.getValue(FLUIDLOGGED)));
		}
	}
}
