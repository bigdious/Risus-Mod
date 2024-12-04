package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;


public class RisusGlassBlock extends Block implements SimpleMultiloggedBlock {
		public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusGlassBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
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
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
		}

		return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
	}
	protected boolean skipRendering(BlockState blockState1, BlockState blockState2, Direction direction) {
		return blockState2.is(this) ? true : super.skipRendering(blockState1, blockState2, direction);
	}

	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 1.0F;
	}
}
