package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class RisusWallBlock extends Block implements SimpleMultiloggedBlock {
	//this has to be copy of WallBlock to get rid of waterlog
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final BooleanProperty UP;
	public static final EnumProperty<WallSide> EAST_WALL;
	public static final EnumProperty<WallSide> NORTH_WALL;
	public static final EnumProperty<WallSide> SOUTH_WALL;
	public static final EnumProperty<WallSide> WEST_WALL;
	private final Map<BlockState, VoxelShape> shapeByIndex;
	private final Map<BlockState, VoxelShape> collisionShapeByIndex;
	private static final VoxelShape POST_TEST;
	private static final VoxelShape NORTH_TEST;
	private static final VoxelShape SOUTH_TEST;
	private static final VoxelShape WEST_TEST;
	private static final VoxelShape EAST_TEST;

	public RisusWallBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(UP, true)
				.setValue(NORTH_WALL, WallSide.NONE)
				.setValue(EAST_WALL, WallSide.NONE)
				.setValue(SOUTH_WALL, WallSide.NONE)
				.setValue(WEST_WALL, WallSide.NONE)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
		this.shapeByIndex = this.makeShapes(4.0F, 3.0F, 16.0F, 0.0F, 14.0F, 16.0F);
		this.collisionShapeByIndex = this.makeShapes(4.0F, 3.0F, 24.0F, 0.0F, 24.0F, 24.0F);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(UP, NORTH_WALL, EAST_WALL, WEST_WALL, SOUTH_WALL, FLUIDLOGGED);
	}

	public static VoxelShape applyWallShape(VoxelShape baseShape, WallSide height, VoxelShape lowShape, VoxelShape tallShape) {
		if (height == WallSide.TALL) {
			return Shapes.or(baseShape, tallShape);
		} else {
			return height == WallSide.LOW ? Shapes.or(baseShape, lowShape) : baseShape;
		}
	}

	public Map<BlockState, VoxelShape> makeShapes(float pWidth, float pDepth, float pWallPostHeight, float pWallMinY, float pWallLowHeight, float pWallTallHeight) {
		float f = 8.0F - pWidth;
		float f1 = 8.0F + pWidth;
		float f2 = 8.0F - pDepth;
		float f3 = 8.0F + pDepth;
		VoxelShape voxelshape = Block.box(f, 0.0D, f, f1, pWallPostHeight, f1);
		VoxelShape voxelshape1 = Block.box(f2, pWallMinY, 0.0D, f3, pWallLowHeight, f3);
		VoxelShape voxelshape2 = Block.box(f2, pWallMinY, f2, f3, pWallLowHeight, 16.0D);
		VoxelShape voxelshape3 = Block.box(0.0D, pWallMinY, f2, f3, pWallLowHeight, f3);
		VoxelShape voxelshape4 = Block.box(f2, pWallMinY, f2, 16.0D, pWallLowHeight, f3);
		VoxelShape voxelshape5 = Block.box(f2, pWallMinY, 0.0D, f3, pWallTallHeight, f3);
		VoxelShape voxelshape6 = Block.box(f2, pWallMinY, f2, f3, pWallTallHeight, 16.0D);
		VoxelShape voxelshape7 = Block.box(0.0D, pWallMinY, f2, f3, pWallTallHeight, f3);
		VoxelShape voxelshape8 = Block.box(f2, pWallMinY, f2, 16.0D, pWallTallHeight, f3);
		ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

		for (Boolean obool : UP.getPossibleValues()) {
			for (WallSide wallside : EAST_WALL.getPossibleValues()) {
				for (WallSide wallside1 : NORTH_WALL.getPossibleValues()) {
					for (WallSide wallside2 : WEST_WALL.getPossibleValues()) {
						for (WallSide wallside3 : SOUTH_WALL.getPossibleValues()) {
							VoxelShape voxelshape9 = Shapes.empty();
							voxelshape9 = applyWallShape(voxelshape9, wallside, voxelshape4, voxelshape8);
							voxelshape9 = applyWallShape(voxelshape9, wallside2, voxelshape3, voxelshape7);
							voxelshape9 = applyWallShape(voxelshape9, wallside1, voxelshape1, voxelshape5);
							voxelshape9 = applyWallShape(voxelshape9, wallside3, voxelshape2, voxelshape6);
							if (obool) {
								voxelshape9 = Shapes.or(voxelshape9, voxelshape);
							}
							BlockState blockstate = this.defaultBlockState().setValue(UP, obool).setValue(EAST_WALL, wallside).setValue(WEST_WALL, wallside2).setValue(NORTH_WALL, wallside1).setValue(SOUTH_WALL, wallside3);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.LAVA), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.WATER), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.BLOOD), voxelshape9);
						}
					}
				}
			}
		}

		return builder.build();
	}

	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.shapeByIndex.get(state);
	}

	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.collisionShapeByIndex.get(state);
	}

	public boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

	private boolean connectsTo(BlockState state, boolean sideSolid, Direction direction) {
		Block block = state.getBlock();
		boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
		return state.is(BlockTags.WALLS) || !isExceptionForConnection(state) && sideSolid || block instanceof IronBarsBlock || flag;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		LevelReader levelreader = pContext.getLevel();
		BlockPos blockpos = pContext.getClickedPos();
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		BlockPos blockpos1 = blockpos.north();
		BlockPos blockpos2 = blockpos.east();
		BlockPos blockpos3 = blockpos.south();
		BlockPos blockpos4 = blockpos.west();
		BlockPos blockpos5 = blockpos.above();
		BlockState blockstate = levelreader.getBlockState(blockpos1);
		BlockState blockstate1 = levelreader.getBlockState(blockpos2);
		BlockState blockstate2 = levelreader.getBlockState(blockpos3);
		BlockState blockstate3 = levelreader.getBlockState(blockpos4);
		BlockState blockstate4 = levelreader.getBlockState(blockpos5);
		boolean flag = this.connectsTo(blockstate, blockstate.isFaceSturdy(levelreader, blockpos1, Direction.SOUTH), Direction.SOUTH);
		boolean flag1 = this.connectsTo(blockstate1, blockstate1.isFaceSturdy(levelreader, blockpos2, Direction.WEST), Direction.WEST);
		boolean flag2 = this.connectsTo(blockstate2, blockstate2.isFaceSturdy(levelreader, blockpos3, Direction.NORTH), Direction.NORTH);
		boolean flag3 = this.connectsTo(blockstate3, blockstate3.isFaceSturdy(levelreader, blockpos4, Direction.EAST), Direction.EAST);
		BlockState blockstate5 = this.defaultBlockState()
				.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
		return this.updateShape(levelreader, blockstate5, blockpos5, blockstate4, flag, flag1, flag2, flag3);
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

		if (direction == Direction.DOWN) {
			return state;
		} else {
			return direction == Direction.UP ? this.topUpdate(accessor, state, neighborPos, neighborState) : this.sideUpdate(accessor, pos, state, neighborPos, neighborState, direction);
		}
	}

	private static boolean isConnected(BlockState state, Property<WallSide> heightProperty) {
		return state.getValue(heightProperty) != WallSide.NONE;
	}

	private static boolean isCovered(VoxelShape firstShape, VoxelShape secondShape) {
		return !Shapes.joinIsNotEmpty(secondShape, firstShape, BooleanOp.ONLY_FIRST);
	}

	public BlockState topUpdate(LevelReader level, BlockState state, BlockPos pos, BlockState secondState) {
		boolean flag = isConnected(state, NORTH_WALL);
		boolean flag1 = isConnected(state, EAST_WALL);
		boolean flag2 = isConnected(state, SOUTH_WALL);
		boolean flag3 = isConnected(state, WEST_WALL);
		return this.updateShape(level, state, pos, secondState, flag, flag1, flag2, flag3);
	}

	public BlockState sideUpdate(LevelReader level, BlockPos firstPos, BlockState firstState, BlockPos secondPos, BlockState secondState, Direction dir) {
		Direction direction = dir.getOpposite();
		boolean flag = dir == Direction.NORTH ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, NORTH_WALL);
		boolean flag1 = dir == Direction.EAST ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, EAST_WALL);
		boolean flag2 = dir == Direction.SOUTH ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, SOUTH_WALL);
		boolean flag3 = dir == Direction.WEST ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, WEST_WALL);
		BlockPos blockpos = firstPos.above();
		BlockState blockstate = level.getBlockState(blockpos);
		return this.updateShape(level, firstState, blockpos, blockstate, flag, flag1, flag2, flag3);
	}

	public BlockState updateShape(LevelReader level, BlockState state, BlockPos pos, BlockState neighbour, boolean northConnection, boolean eastConnection, boolean southConnection, boolean westConnection) {
		VoxelShape voxelshape = neighbour.getCollisionShape(level, pos).getFaceShape(Direction.DOWN);
		BlockState blockstate = this.updateSides(state, northConnection, eastConnection, southConnection, westConnection, voxelshape);
		return blockstate.setValue(UP, this.shouldRaisePost(blockstate, neighbour, voxelshape));
	}

	private boolean shouldRaisePost(BlockState state, BlockState neighbour, VoxelShape shape) {
		boolean flag = neighbour.getBlock() instanceof WallBlock && neighbour.getValue(UP);
		if (flag) {
			return true;
		} else {
			WallSide wallside = state.getValue(NORTH_WALL);
			WallSide wallside1 = state.getValue(SOUTH_WALL);
			WallSide wallside2 = state.getValue(EAST_WALL);
			WallSide wallside3 = state.getValue(WEST_WALL);
			boolean flag1 = wallside1 == WallSide.NONE;
			boolean flag2 = wallside3 == WallSide.NONE;
			boolean flag3 = wallside2 == WallSide.NONE;
			boolean flag4 = wallside == WallSide.NONE;
			boolean flag5 = flag4 && flag1 && flag2 && flag3 || flag4 != flag1 || flag2 != flag3;
			if (flag5) {
				return true;
			} else {
				boolean flag6 = wallside == WallSide.TALL && wallside1 == WallSide.TALL || wallside2 == WallSide.TALL && wallside3 == WallSide.TALL;
				return !flag6 && (neighbour.is(BlockTags.WALL_POST_OVERRIDE) || isCovered(shape, POST_TEST));
			}
		}
	}

	private BlockState updateSides(BlockState state, boolean northConnection, boolean eastConnection, boolean southConnection, boolean westConnection, VoxelShape wallShape) {
		return (((state.setValue(NORTH_WALL, this.makeWallState(northConnection, wallShape, NORTH_TEST))).setValue(EAST_WALL, this.makeWallState(eastConnection, wallShape, EAST_TEST))).setValue(SOUTH_WALL, this.makeWallState(southConnection, wallShape, SOUTH_TEST))).setValue(WEST_WALL, this.makeWallState(westConnection, wallShape, WEST_TEST));
	}

	private WallSide makeWallState(boolean allowConnection, VoxelShape shape, VoxelShape neighbourShape) {
		if (allowConnection) {
			return isCovered(shape, neighbourShape) ? WallSide.TALL : WallSide.LOW;
		} else {
			return WallSide.NONE;
		}
	}

	protected BlockState rotate(BlockState state, Rotation rotation) {
		switch (rotation) {
			case CLOCKWISE_180 -> {
				return (((state.setValue(NORTH_WALL, state.getValue(SOUTH_WALL))).setValue(EAST_WALL, state.getValue(WEST_WALL))).setValue(SOUTH_WALL, state.getValue(NORTH_WALL))).setValue(WEST_WALL, state.getValue(EAST_WALL));
			}
			case COUNTERCLOCKWISE_90 -> {
				return (((state.setValue(NORTH_WALL, state.getValue(EAST_WALL))).setValue(EAST_WALL, state.getValue(SOUTH_WALL))).setValue(SOUTH_WALL, state.getValue(WEST_WALL))).setValue(WEST_WALL, state.getValue(NORTH_WALL));
			}
			case CLOCKWISE_90 -> {
				return (((state.setValue(NORTH_WALL, state.getValue(WEST_WALL))).setValue(EAST_WALL, state.getValue(NORTH_WALL))).setValue(SOUTH_WALL, state.getValue(EAST_WALL))).setValue(WEST_WALL, state.getValue(SOUTH_WALL));
			}
			default -> {
				return state;
			}
		}
	}

	protected BlockState mirror(BlockState state, Mirror mirror) {
		switch (mirror) {
			case LEFT_RIGHT -> {
				return (state.setValue(NORTH_WALL, state.getValue(SOUTH_WALL))).setValue(SOUTH_WALL, state.getValue(NORTH_WALL));
			}
			case FRONT_BACK -> {
				return (state.setValue(EAST_WALL, state.getValue(WEST_WALL))).setValue(WEST_WALL, state.getValue(EAST_WALL));
			}
			default -> {
				return super.mirror(state, mirror);
			}
		}
	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return pState.getValue(FLUIDLOGGED) == MultiloggingEnum.EMPTY;
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return SimpleMultiloggedBlock.super.canPlaceLiquid(player, getter, pos, state, fluid);
	}

	@Override
	public ItemStack pickupBlock(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		return SimpleMultiloggedBlock.super.pickupBlock(pPlayer, pLevel, pPos, pState);
	}

	@Override
	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		return SimpleMultiloggedBlock.super.placeLiquid(pLevel, pPos, pState, pFluidState);
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.getValue(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED) == SimpleMultiloggedBlock.MultiloggingEnum.LAVA ? 15 : 0;
	}

	static {
		UP = BlockStateProperties.UP;
		EAST_WALL = BlockStateProperties.EAST_WALL;
		NORTH_WALL = BlockStateProperties.NORTH_WALL;
		SOUTH_WALL = BlockStateProperties.SOUTH_WALL;
		WEST_WALL = BlockStateProperties.WEST_WALL;
		POST_TEST = Block.box(7.0F, 0.0F, 7.0F, 9.0F, 16.0F, 9.0F);
		NORTH_TEST = Block.box(7.0F, 0.0F, 0.0F, 9.0F, 16.0F, 9.0F);
		SOUTH_TEST = Block.box(7.0F, 0.0F, 7.0F, 9.0F, 16.0F, 16.0F);
		WEST_TEST = Block.box(0.0F, 0.0F, 7.0F, 9.0F, 16.0F, 9.0F);
		EAST_TEST = Block.box(7.0F, 0.0F, 7.0F, 16.0F, 16.0F, 9.0F);
	}
}

