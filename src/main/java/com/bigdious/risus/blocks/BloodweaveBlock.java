package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

//oh god, this will be a 6 dimensional fence
//nvm it's just chorus fruit

public class BloodweaveBlock extends PipeBlock {
	public BloodweaveBlock(Properties properties) {
		super(0.3125F, properties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(NORTH, Boolean.valueOf(false))
			.setValue(EAST, Boolean.valueOf(false))
			.setValue(SOUTH, Boolean.valueOf(false))
			.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
			.setValue(DOWN, Boolean.valueOf(false)));
	}

	public BlockState updateShape(BlockState state, Direction dir, BlockState otherstate, LevelAccessor levelacc, BlockPos pos, BlockPos pos2) {
		boolean flag = otherstate.is(this);
		return state.setValue(PROPERTY_BY_DIRECTION.get(dir), Boolean.valueOf(flag));

	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51735_) {
		p_51735_.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}
}


