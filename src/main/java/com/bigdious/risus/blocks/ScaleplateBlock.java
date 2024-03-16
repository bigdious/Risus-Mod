package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class ScaleplateBlock extends BaseRotatableBlock implements SimpleMultiloggedBlock{

	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D),
			Direction.SOUTH, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D),
			Direction.EAST, Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D),
			Direction.WEST, Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)));

//start multilog
	public ScaleplateBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
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
			.add(WATERLOGGED)
			.add(LAVALOGGED);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		return this.defaultBlockState()
			.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
			.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER))
			.setValue(FACING, pContext.getHorizontalDirection().getOpposite());
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

		return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
	}
	//stop
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}
}
