package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class RisusPillarBlock extends RotatedPillarBlock implements SimpleMultiloggedBlock {
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");

	protected static final VoxelShape Y_NONE_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape Y_BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	protected static final VoxelShape Y_TOP_SHAPE = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape Y_NO_TOP_SHAPE = Shapes.or(Y_NONE_SHAPE, Y_BOTTOM_SHAPE);
	protected static final VoxelShape Y_NO_BOTTOM_SHAPE = Shapes.or(Y_NONE_SHAPE, Y_TOP_SHAPE);
	protected static final VoxelShape Y_BASE_SHAPE = Shapes.or(Y_NONE_SHAPE, Y_TOP_SHAPE, Y_BOTTOM_SHAPE);
	protected static final VoxelShape X_NONE_SHAPE = Block.box(0.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);
	protected static final VoxelShape X_BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
	protected static final VoxelShape X_TOP_SHAPE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape X_NO_TOP_SHAPE = Shapes.or(X_NONE_SHAPE, X_BOTTOM_SHAPE);
	protected static final VoxelShape X_NO_BOTTOM_SHAPE = Shapes.or(X_NONE_SHAPE, X_TOP_SHAPE);
	protected static final VoxelShape X_BASE_SHAPE = Shapes.or(X_NONE_SHAPE, X_TOP_SHAPE, X_BOTTOM_SHAPE);
	protected static final VoxelShape Z_NONE_SHAPE = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D);
	protected static final VoxelShape Z_BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape Z_TOP_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
	protected static final VoxelShape Z_NO_TOP_SHAPE = Shapes.or(Z_NONE_SHAPE, Z_BOTTOM_SHAPE);
	protected static final VoxelShape Z_NO_BOTTOM_SHAPE = Shapes.or(Z_NONE_SHAPE, Z_TOP_SHAPE);
	protected static final VoxelShape Z_BASE_SHAPE = Shapes.or(Z_NONE_SHAPE, Z_TOP_SHAPE, Z_BOTTOM_SHAPE);



	public RisusPillarBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
			.setValue(TOP, false)
			.setValue(BOTTOM, false)
			.setValue(AXIS, Direction.Axis.Y));
	}

	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			level.scheduleTick(currentPos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(level));
		}
		return state.setValue(TOP, switch (state.getValue(AXIS)) {
				case X -> facing == Direction.EAST ? canConnectTo(state, facingState) : state.getValue(TOP);
				case Y -> facing == Direction.UP ? canConnectTo(state, facingState) : state.getValue(TOP);
				case Z -> facing == Direction.NORTH ? canConnectTo(state, facingState) : state.getValue(TOP);
			})
			.setValue(BOTTOM, switch (state.getValue(AXIS)) {
				case X -> facing == Direction.WEST ? canConnectTo(state, facingState) : state.getValue(BOTTOM);
				case Y -> facing == Direction.DOWN ? canConnectTo(state, facingState) : state.getValue(BOTTOM);
				case Z -> facing == Direction.SOUTH ? canConnectTo(state, facingState) : state.getValue(BOTTOM);
			});
	}

	public boolean canConnectTo(BlockState target,  BlockState facingState) {
		return facingState.getBlock() instanceof RisusPillarBlock && facingState.getBlock() == target.getBlock() && target.getValue(AXIS) == facingState.getValue(AXIS);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		boolean top = state.getValue(TOP);
		boolean bot = state.getValue(BOTTOM);
		return switch (state.getValue(AXIS)) {
			case X -> top && bot ? X_NONE_SHAPE : top ? X_NO_TOP_SHAPE : bot ? X_NO_BOTTOM_SHAPE : X_BASE_SHAPE;
			case Z -> top && bot ? Z_NONE_SHAPE : top ? Z_NO_TOP_SHAPE : bot ? Z_NO_BOTTOM_SHAPE : Z_BASE_SHAPE;
			default ->  top && bot ? Y_NONE_SHAPE : top ? Y_NO_TOP_SHAPE : bot ? Y_NO_BOTTOM_SHAPE : Y_BASE_SHAPE;
		};
	}


	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		Direction.Axis axis = context.getClickedFace().getAxis();
		BlockPos pos = context.getClickedPos();
		BlockState state = context.getLevel().getBlockState(pos);
		return this.defaultBlockState().setValue(AXIS, axis)
			.setValue(TOP, switch (axis) {
				case X -> canConnectTo(state, context.getLevel().getBlockState(pos.west()));
				case Y -> canConnectTo(state, context.getLevel().getBlockState(pos.above()));
				case Z -> canConnectTo(state, context.getLevel().getBlockState(pos.north()));
			})
			.setValue(BOTTOM, switch (axis) {
				case X -> canConnectTo(state, context.getLevel().getBlockState(pos.east()));
				case Y -> canConnectTo(state, context.getLevel().getBlockState(pos.below()));
				case Z -> canConnectTo(state, context.getLevel().getBlockState(pos.south()));
			})
			.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return SimpleMultiloggedBlock.super.canPlaceLiquid(player, getter, pos, state, fluid);
	}

	@Override
	public ItemStack pickupBlock(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		return SimpleMultiloggedBlock.super.pickupBlock(pPlayer, pLevel, pPos, pState);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS, TOP, BOTTOM, FLUIDLOGGED);
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getValue(MultiloggingEnum.FLUIDLOGGED) != MultiloggingEnum.WATER;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.getValue(MultiloggingEnum.FLUIDLOGGED) == MultiloggingEnum.LAVA ? 15 : 0;
	}


}
