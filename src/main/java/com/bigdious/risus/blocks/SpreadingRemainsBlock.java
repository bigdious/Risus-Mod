package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;


public class SpreadingRemainsBlock extends MultifaceBlock implements SimpleMultiloggedBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);
//start multilog
    public SpreadingRemainsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
    }
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        super.createBlockStateDefinition(state);
        state.add(FACING)
			.add(BLOODLOGGED)
			.add(WATERLOGGED)
			.add(LAVALOGGED);;
    }
	public BlockState getStateForPlacement(BlockState pCurrentState, BlockGetter pLevel, BlockPos pPos, Direction pLookingDirection) {

		if (!this.isValidStateForPlacement(pLevel, pCurrentState, pPos, pLookingDirection)) {
			return null;
		} else {
			BlockState blockstate;
			if (pCurrentState.is(this)) {
				blockstate = pCurrentState;
			} else if (pCurrentState.getFluidState().isSourceOfType(Fluids.WATER)) {
				blockstate = this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(true));
			} else if (pCurrentState.getFluidState().isSourceOfType(Fluids.LAVA)) {
				blockstate = this.defaultBlockState().setValue(LAVALOGGED, Boolean.valueOf(true));
			} else if (pCurrentState.getFluidState().isSourceOfType(RisusFluids.SOURCE_BLOOD.get())) {
				blockstate = this.defaultBlockState().setValue(BLOODLOGGED, Boolean.valueOf(true));
			} else {
				blockstate = this.defaultBlockState();
			}

			return blockstate.setValue(getFaceProperty(pLookingDirection), Boolean.valueOf(true));
		}
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
		else if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		else if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
	}
	//stop multilogging necessities

    public boolean canBeReplaced(BlockState state, BlockPlaceContext blockplace) {
        return !blockplace.getItemInHand().is(RisusItems.SPREADING_REMAINS.get()) || super.canBeReplaced(state, blockplace);
    }


    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }
}
