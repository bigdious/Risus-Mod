package com.bigdious.risus.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

//oh god, this will be a 6 dimensional fence
//nvm it's just chorus fruit
public class BloodweaveBlock extends PipeBlock implements SimpleMultiloggedBlock {

	public static final MapCodec<BloodweaveBlock> CODEC = simpleCodec(BloodweaveBlock::new);
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public BloodweaveBlock(Properties properties) {
		super(0.3125F, properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(NORTH, Boolean.FALSE)
				.setValue(EAST, Boolean.FALSE)
				.setValue(SOUTH, Boolean.FALSE)
				.setValue(WEST, Boolean.FALSE)
				.setValue(UP, Boolean.FALSE)
				.setValue(DOWN, Boolean.FALSE)
				.setValue(AXIS, Direction.Axis.Y)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected MapCodec<? extends PipeBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, AXIS, FLUIDLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		return this.defaultBlockState().setValue(AXIS, pContext.getClickedFace().getAxis()).setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
		boolean flag = neighborState.is(this);
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
		}

		return state.setValue(PROPERTY_BY_DIRECTION.get(direction), flag);

	}

	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vec3(0.65D, 0.75F, 0.65D));
	}

}



