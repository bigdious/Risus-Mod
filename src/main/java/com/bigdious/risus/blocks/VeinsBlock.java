package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.plantblocks.RisusGrowingPlantBodyBlock;
import com.bigdious.risus.blocks.plantblocks.RisusGrowingPlantHeadBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VeinsBlock extends RisusGrowingPlantBodyBlock implements SimpleMultiloggedBlock{
	public static final MapCodec<VeinsBlock> CODEC = simpleCodec(VeinsBlock::new);
	public static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	@Override
	public MapCodec<VeinsBlock> codec() {
		return CODEC;
	}

	public VeinsBlock(BlockBehaviour.Properties p_154975_) {
		super(p_154975_, Direction.DOWN, SHAPE, false);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected RisusGrowingPlantHeadBlock getHeadBlock() {
		return RisusBlocks.VEINS_END.get();
	}
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(this.growthDirection));
		return !blockstate.is(this.getHeadBlock()) && !blockstate.is(this.getBodyBlock())
			?this.getStateForPlacement(context.getLevel())
			:this.getBodyBlock().defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
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

		RisusGrowingPlantHeadBlock growingplantheadblock = this.getHeadBlock();
		if (direction == this.growthDirection && !neighborState.is(this) && !neighborState.is(growingplantheadblock)) {
			return this.updateHeadAfterConvertedFromBody(state, growingplantheadblock.getStateForPlacement(accessor).setValue(FLUIDLOGGED, state.getValue(FLUIDLOGGED)));
		} else {
			if (this.scheduleFluidTicks && state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
				accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
			}
		}

		return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
	}

}
