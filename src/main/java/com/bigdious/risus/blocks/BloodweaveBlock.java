package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

//oh god, this will be a 6 dimensional fence
//nvm it's just chorus fruit

public class BloodweaveBlock extends PipeBlock {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public BloodweaveBlock(Properties properties) {
		super(0.3125F, properties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(NORTH, Boolean.valueOf(false))
			.setValue(EAST, Boolean.valueOf(false))
			.setValue(SOUTH, Boolean.valueOf(false))
			.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
			.setValue(DOWN, Boolean.valueOf(false)));
		this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
	}


	public BlockState updateShape(BlockState state, Direction dir, BlockState otherstate, LevelAccessor levelacc, BlockPos pos, BlockPos pos2) {
		boolean flag = otherstate.is(this);
		return state.setValue(PROPERTY_BY_DIRECTION.get(dir), Boolean.valueOf(flag));

	}
	public BlockState getStateForPlacement(BlockPlaceContext p_55928_) {
		return this.defaultBlockState().setValue(AXIS, p_55928_.getClickedFace().getAxis());
	}


	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51735_) {
		p_51735_.add(NORTH, EAST, SOUTH, WEST, UP, DOWN,AXIS);
	}
	public void entityInside(BlockState p_58180_, Level p_58181_, BlockPos p_58182_, Entity p_58183_) {
		p_58183_.makeStuckInBlock(p_58180_, new Vec3(0.65D, (double)0.75F, 0.65D));
	}
}



