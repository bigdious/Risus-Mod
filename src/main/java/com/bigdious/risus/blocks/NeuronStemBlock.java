package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NeuronStemBlock extends GrowingPlantBodyBlock implements SimpleMultiloggedBlock{
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	//start multilog
    public NeuronStemBlock(BlockBehaviour.Properties p_154873_) {
        super(p_154873_, Direction.UP, SHAPE, false);
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
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}
	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {return Fluids.LAVA.getSource().defaultFluidState();}
		if (pState.getValue(BLOODLOGGED)) {return RisusFluids.SOURCE_BLOOD.get().getSource().defaultFluidState();}
		if (pState.getValue(WATERLOGGED)) {return Fluids.WATER.getSource().defaultFluidState();}
		else return  super.getFluidState(pState);
	}

	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
		if (pDirection == this.growthDirection.getOpposite() && !pState.canSurvive(pLevel, pPos)) {
			pLevel.scheduleTick(pPos, this, 1);
		}

		GrowingPlantHeadBlock growingplantheadblock = this.getHeadBlock();
		if (pDirection == this.growthDirection && !pNeighborState.is(this) && !pNeighborState.is(growingplantheadblock)) {
			return this.updateHeadAfterConvertedFromBody(pState, growingplantheadblock.getStateForPlacement(pLevel)
				.setValue(SimpleMultiloggedBlock.WATERLOGGED, pState.getValue(SimpleMultiloggedBlock.WATERLOGGED))
				.setValue(SimpleMultiloggedBlock.BLOODLOGGED, pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED))
				.setValue(SimpleMultiloggedBlock.LAVALOGGED, pState.getValue(SimpleMultiloggedBlock.LAVALOGGED)));
		}
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
	//stop multilog

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock)RisusBlocks.NEURON_HEAD.get();
    }
}
