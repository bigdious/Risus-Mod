package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.enums.FenceHeight;
import com.bigdious.risus.blocks.enums.FenceSide;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringRepresentable;
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

public class SuperFenceBlock extends Block implements SimpleMultiloggedBlock {
	//this is an upside down super wall disguised as iron bars

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final BooleanProperty DOWN;
	public static final EnumProperty<FenceSide> EAST_WALL;
	public static final EnumProperty<FenceSide> NORTH_WALL;
	public static final EnumProperty<FenceSide> SOUTH_WALL;
	public static final EnumProperty<FenceSide> WEST_WALL;
	private final Map<BlockState, VoxelShape> shapeByIndex;
	private final Map<BlockState, VoxelShape> collisionShapeByIndex;
	private static final VoxelShape POST_TEST;
	private static final VoxelShape NORTH_TEST;
	private static final VoxelShape SOUTH_TEST;
	private static final VoxelShape WEST_TEST;
	private static final VoxelShape EAST_TEST;

	public SuperFenceBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(DOWN, false)
			.setValue(NORTH_WALL, FenceSide.NONE)
			.setValue(EAST_WALL, FenceSide.NONE)
			.setValue(SOUTH_WALL, FenceSide.NONE)
			.setValue(WEST_WALL, FenceSide.NONE)
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
		this.shapeByIndex = this.makeShapes(1.0F, 0.5F, 16.0F, 0.0F, 16.0F, 16.0F);
		this.collisionShapeByIndex = this.makeShapes(1.0F, 0.5F, 24.0F, 0.0F, 24.0F, 24.0F);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, NORTH_WALL, EAST_WALL, WEST_WALL, SOUTH_WALL, FLUIDLOGGED);
	}

	public static VoxelShape applyFenceShape(VoxelShape baseShape, FenceSide height, VoxelShape lowShape, VoxelShape tallShape, VoxelShape downShape, VoxelShape downTallShape) {
		if (height == FenceSide.TALL) {
			return Shapes.or(baseShape, tallShape);
		} else if (height == FenceSide.DOWN_LOW) {
			return Shapes.or(baseShape, downShape);
		} else if (height == FenceSide.DOWN_TALL) {
			return Shapes.or(baseShape, downTallShape);
		} else {
			return height == FenceSide.LOW ? Shapes.or(baseShape, lowShape) : baseShape;
		}
	}

	public Map<BlockState, VoxelShape> makeShapes(float pWidth, float pDepth, float pWallPostHeight, float pWallMinY, float pWallLowHeight, float pWallTallHeight) {
		float f = 8.0F - pWidth;
		float f1 = 8.0F + pWidth;
		float f2 = 8.0F - pDepth;
		float f3 = 8.0F + pDepth;
		VoxelShape voxelshape = Block.box(f, 0.0D, f, f1, pWallPostHeight, f1);
		VoxelShape voxelshape_down = Block.box(f, -8.0D, f, f1, pWallPostHeight, f1);
		VoxelShape voxelshape1 = Block.box(f2, pWallMinY, 0.0D, f3, pWallLowHeight-1, f3);
		VoxelShape voxelshape2 = Block.box(f2, pWallMinY, f2, f3, pWallLowHeight-1, 16.0D);
		VoxelShape voxelshape3 = Block.box(0.0D, pWallMinY, f2, f3, pWallLowHeight-1, f3);
		VoxelShape voxelshape4 = Block.box(f2, pWallMinY, f2, 16.0D, pWallLowHeight-1, f3);
		VoxelShape voxelshape5 = Block.box(f2, pWallMinY, 0.0D, f3, pWallTallHeight, f3);
		VoxelShape voxelshape6 = Block.box(f2, pWallMinY, f2, f3, pWallTallHeight, 16.0D);
		VoxelShape voxelshape7 = Block.box(0.0D, pWallMinY, f2, f3, pWallTallHeight, f3);
		VoxelShape voxelshape8 = Block.box(f2, pWallMinY, f2, 16.0D, pWallTallHeight, f3);
		VoxelShape voxelshape10 = Block.box(f2, pWallMinY- 8.0D, 0.0D, f3, pWallLowHeight-1, f3);
		VoxelShape voxelshape11 = Block.box(f2, pWallMinY- 8.0D, f2, f3, pWallLowHeight-1, 16.0D);
		VoxelShape voxelshape12 = Block.box(0.0D, pWallMinY- 8.0D, f2, f3, pWallLowHeight-1, f3);
		VoxelShape voxelshape13 = Block.box(f2, pWallMinY- 8.0D, f2, 16.0D, pWallLowHeight-1, f3);
		VoxelShape voxelshape14 = Block.box(f2, pWallMinY - 8.0D, 0.0D, f3, pWallTallHeight, f3);
		VoxelShape voxelshape15 = Block.box(f2, pWallMinY - 8.0D, f2, f3, pWallTallHeight, 16.0D);
		VoxelShape voxelshape16 = Block.box(0.0D, pWallMinY - 8.0D, f2, f3, pWallTallHeight, f3);
		VoxelShape voxelshape17 = Block.box(f2, pWallMinY - 8.0D, f2, 16.0D, pWallTallHeight, f3);
		ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();
		for (Boolean height : DOWN.getPossibleValues()) {
			for (FenceSide wallside : EAST_WALL.getPossibleValues()) {
				for (FenceSide wallside1 : NORTH_WALL.getPossibleValues()) {
					for (FenceSide wallside2 : WEST_WALL.getPossibleValues()) {
						for (FenceSide wallside3 : SOUTH_WALL.getPossibleValues()) {
							VoxelShape voxelshape9 = Shapes.empty();
							voxelshape9 = applyFenceShape(voxelshape9, wallside, voxelshape4, voxelshape8, voxelshape13, voxelshape17);
							voxelshape9 = applyFenceShape(voxelshape9, wallside2, voxelshape3, voxelshape7, voxelshape12, voxelshape16);
							voxelshape9 = applyFenceShape(voxelshape9, wallside1, voxelshape1, voxelshape5, voxelshape10, voxelshape14);
							voxelshape9 = applyFenceShape(voxelshape9, wallside3, voxelshape2, voxelshape6, voxelshape11, voxelshape15);
							if (height) {
								voxelshape9 = Shapes.or(voxelshape9, voxelshape_down);
							} else {
								voxelshape9 = Shapes.or(voxelshape9, voxelshape);
							}
							BlockState blockstate = this.defaultBlockState().setValue(DOWN, height).setValue(EAST_WALL, wallside).setValue(WEST_WALL, wallside2).setValue(NORTH_WALL, wallside1).setValue(SOUTH_WALL, wallside3);
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

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.shapeByIndex.get(state);
	}

	@Override
	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.collisionShapeByIndex.get(state);
	}

	public boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

	private boolean connectsTo(BlockState state, boolean sideSolid, Direction direction) {
		Block block = state.getBlock();
		boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
		boolean flag1 = block instanceof RisusGateBlock && RisusGateBlock.connectsToDirection(state, direction);
		return (state.is(BlockTags.WOODEN_FENCES) && !(block instanceof RisusGateBlock)) || (state.is(BlockTags.WALLS) && !(block instanceof RisusGateBlock)) || state.getBlock() instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM || !isExceptionForConnection(state) && sideSolid || block instanceof IronBarsBlock || flag || flag1;
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
		return this.updateShape(levelreader, blockstate5, blockpos5, blockstate4, flag, flag1, flag2, flag3, blockpos.below());
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
		return direction == Direction.UP ? this.topUpdate(accessor, state, neighborPos, neighborState, pos.below()) : this.sideUpdate(accessor, pos, state, neighborPos, neighborState, direction, pos.below());

	}

	private static boolean isConnected(BlockState state, Property<FenceSide> heightProperty) {
		return state.getValue(heightProperty) != FenceSide.NONE;
	}

	private static boolean isCovered(VoxelShape firstShape, VoxelShape secondShape) {
		return !Shapes.joinIsNotEmpty(secondShape, firstShape, BooleanOp.ONLY_FIRST);
	}

	public BlockState topUpdate(LevelReader level, BlockState state, BlockPos pos, BlockState secondState, BlockPos below) {
		boolean flag = isConnected(state, NORTH_WALL);
		boolean flag1 = isConnected(state, EAST_WALL);
		boolean flag2 = isConnected(state, SOUTH_WALL);
		boolean flag3 = isConnected(state, WEST_WALL);
		return this.updateShape(level, state, pos, secondState, flag, flag1, flag2, flag3, below);
	}

	public BlockState sideUpdate(LevelReader level, BlockPos firstPos, BlockState firstState, BlockPos secondPos, BlockState secondState, Direction dir, BlockPos below) {
		Direction direction = dir.getOpposite();
		boolean flag = dir == Direction.NORTH ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, NORTH_WALL);
		boolean flag1 = dir == Direction.EAST ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, EAST_WALL);
		boolean flag2 = dir == Direction.SOUTH ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, SOUTH_WALL);
		boolean flag3 = dir == Direction.WEST ? this.connectsTo(secondState, secondState.isFaceSturdy(level, secondPos, direction), direction) : isConnected(firstState, WEST_WALL);
		BlockPos blockpos = firstPos.above();
		BlockState blockstate = level.getBlockState(blockpos);
		return this.updateShape(level, firstState, blockpos, blockstate, flag, flag1, flag2, flag3, below);
	}

	public BlockState updateShape(LevelReader level, BlockState state, BlockPos pos, BlockState neighbour, boolean northConnection, boolean eastConnection, boolean southConnection, boolean westConnection, BlockPos below) {
		VoxelShape voxelshape = neighbour.getCollisionShape(level, pos).getFaceShape(Direction.DOWN);
		BlockState blockstate = this.updateSides(this.shouldGoDown(level, below) ,state, northConnection, eastConnection, southConnection, westConnection, voxelshape);
		return blockstate.setValue(DOWN, this.shouldGoDown(level, below));
	}

	private boolean shouldGoDown(LevelReader level, BlockPos pos) {
		return level.getBlockState(pos).getBlock() instanceof SlabBlock && level.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.BOTTOM;
	}

	private BlockState updateSides(boolean shouldDown, BlockState state, boolean northConnection, boolean eastConnection, boolean southConnection, boolean westConnection, VoxelShape wallShape) {
		return (((state.setValue(NORTH_WALL, this.makeWallState(shouldDown, northConnection, wallShape, NORTH_TEST)))
			.setValue(EAST_WALL, this.makeWallState(shouldDown, eastConnection, wallShape, EAST_TEST)))
			.setValue(SOUTH_WALL, this.makeWallState(shouldDown, southConnection, wallShape, SOUTH_TEST)))
			.setValue(WEST_WALL, this.makeWallState(shouldDown, westConnection, wallShape, WEST_TEST));
	}

	private FenceSide makeWallState(boolean shouldDown, boolean allowConnection, VoxelShape shape, VoxelShape neighbourShape) {
		if (allowConnection) {
			if (isCovered(shape, neighbourShape)) {
				return shouldDown ? FenceSide.DOWN_TALL : FenceSide.TALL;
			} else {
				return shouldDown ? FenceSide.DOWN_LOW : FenceSide.LOW;
			}
		} else {
			return FenceSide.NONE;
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
		DOWN = BlockStateProperties.DOWN;
		EAST_WALL = EnumProperty.create("east", FenceSide.class);
		NORTH_WALL = EnumProperty.create("north", FenceSide.class);
		SOUTH_WALL = EnumProperty.create("south", FenceSide.class);
		WEST_WALL = EnumProperty.create("west", FenceSide.class);
		POST_TEST = Block.box(7.0F, 0.0F, 7.0F, 9F, 16.0F, 9F);
		NORTH_TEST = Block.box(7.5F, 0.0F, 0.0F, 8.5F, 16.0F, 8.5F);
		SOUTH_TEST = Block.box(7.5F, 0.0F, 7.5F, 8.5F, 16.0F, 16.0F);
		WEST_TEST = Block.box(0.0F, 0.0F, 7.5F, 8.5F, 16.0F, 8.5F);
		EAST_TEST = Block.box(7.5F, 0.0F, 7.5F, 16.0F, 16.0F, 8.5F);
	}


}
