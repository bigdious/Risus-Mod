package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;
import java.util.Optional;

public class RisusWallBlock extends WallBlock implements SimpleMultiloggedBlock{
	//vanillacopy for multilogging
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final EnumProperty<WallSide> EAST_WALL = BlockStateProperties.EAST_WALL;
	public static final EnumProperty<WallSide> NORTH_WALL = BlockStateProperties.NORTH_WALL;
	public static final EnumProperty<WallSide> SOUTH_WALL = BlockStateProperties.SOUTH_WALL;
	public static final EnumProperty<WallSide> WEST_WALL = BlockStateProperties.WEST_WALL;
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
	private final Map<BlockState, VoxelShape> shapeByIndex;
	private final Map<BlockState, VoxelShape> collisionShapeByIndex;
	private static final int WALL_WIDTH = 3;
	private static final int WALL_HEIGHT = 14;
	private static final int POST_WIDTH = 4;
	private static final int POST_COVER_WIDTH = 1;
	private static final int WALL_COVER_START = 7;
	private static final int WALL_COVER_END = 9;
	private static final VoxelShape POST_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
	private static final VoxelShape NORTH_TEST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
	private static final VoxelShape SOUTH_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
	private static final VoxelShape WEST_TEST = Block.box(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
	private static final VoxelShape EAST_TEST = Block.box(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);

	public RisusWallBlock(BlockBehaviour.Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(UP, Boolean.valueOf(true))
			.setValue(NORTH_WALL, WallSide.NONE)
			.setValue(EAST_WALL, WallSide.NONE)
			.setValue(SOUTH_WALL, WallSide.NONE)
			.setValue(WEST_WALL, WallSide.NONE)
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
		this.shapeByIndex = this.makeShapes(4.0F, 3.0F, 16.0F, 0.0F, 14.0F, 16.0F);
		this.collisionShapeByIndex = this.makeShapes(4.0F, 3.0F, 24.0F, 0.0F, 24.0F, 24.0F);
	}

	private static VoxelShape applyWallShape(VoxelShape pBaseShape, WallSide pHeight, VoxelShape pLowShape, VoxelShape pTallShape) {
		if (pHeight == WallSide.TALL) {
			return Shapes.or(pBaseShape, pTallShape);
		} else {
			return pHeight == WallSide.LOW ? Shapes.or(pBaseShape, pLowShape) : pBaseShape;
		}
	}

	private Map<BlockState, VoxelShape> makeShapes(float pWidth, float pDepth, float pWallPostHeight, float pWallMinY, float pWallLowHeight, float pWallTallHeight) {
		float f = 8.0F - pWidth;
		float f1 = 8.0F + pWidth;
		float f2 = 8.0F - pDepth;
		float f3 = 8.0F + pDepth;
		VoxelShape voxelshape = Block.box((double)f, 0.0D, (double)f, (double)f1, (double)pWallPostHeight, (double)f1);
		VoxelShape voxelshape1 = Block.box((double)f2, (double)pWallMinY, 0.0D, (double)f3, (double)pWallLowHeight, (double)f3);
		VoxelShape voxelshape2 = Block.box((double)f2, (double)pWallMinY, (double)f2, (double)f3, (double)pWallLowHeight, 16.0D);
		VoxelShape voxelshape3 = Block.box(0.0D, (double)pWallMinY, (double)f2, (double)f3, (double)pWallLowHeight, (double)f3);
		VoxelShape voxelshape4 = Block.box((double)f2, (double)pWallMinY, (double)f2, 16.0D, (double)pWallLowHeight, (double)f3);
		VoxelShape voxelshape5 = Block.box((double)f2, (double)pWallMinY, 0.0D, (double)f3, (double)pWallTallHeight, (double)f3);
		VoxelShape voxelshape6 = Block.box((double)f2, (double)pWallMinY, (double)f2, (double)f3, (double)pWallTallHeight, 16.0D);
		VoxelShape voxelshape7 = Block.box(0.0D, (double)pWallMinY, (double)f2, (double)f3, (double)pWallTallHeight, (double)f3);
		VoxelShape voxelshape8 = Block.box((double)f2, (double)pWallMinY, (double)f2, 16.0D, (double)pWallTallHeight, (double)f3);
		ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

		for(Boolean obool : UP.getPossibleValues()) {
			for(WallSide wallside : EAST_WALL.getPossibleValues()) {
				for(WallSide wallside1 : NORTH_WALL.getPossibleValues()) {
					for(WallSide wallside2 : WEST_WALL.getPossibleValues()) {
						for(WallSide wallside3 : SOUTH_WALL.getPossibleValues()) {
							VoxelShape voxelshape9 = Shapes.empty();
							voxelshape9 = applyWallShape(voxelshape9, wallside, voxelshape4, voxelshape8);
							voxelshape9 = applyWallShape(voxelshape9, wallside2, voxelshape3, voxelshape7);
							voxelshape9 = applyWallShape(voxelshape9, wallside1, voxelshape1, voxelshape5);
							voxelshape9 = applyWallShape(voxelshape9, wallside3, voxelshape2, voxelshape6);
							if (obool) {
								voxelshape9 = Shapes.or(voxelshape9, voxelshape);
							}
//I'm sorry, but why the fuck do I need to specify all possible fluidstates if I don't want a nullpointerexception
							BlockState blockstate = this.defaultBlockState().setValue(UP, obool).setValue(EAST_WALL, wallside).setValue(WEST_WALL, wallside2).setValue(NORTH_WALL, wallside1).setValue(SOUTH_WALL, wallside3);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(false))
								.setValue(LAVALOGGED, Boolean.valueOf(false))
								.setValue(BLOODLOGGED, Boolean.valueOf(false)), voxelshape9);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(true))
								.setValue(LAVALOGGED, Boolean.valueOf(false))
								.setValue(BLOODLOGGED, Boolean.valueOf(false)), voxelshape9);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(false))
								.setValue(LAVALOGGED, Boolean.valueOf(true))
								.setValue(BLOODLOGGED, Boolean.valueOf(false)), voxelshape9);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(false))
								.setValue(LAVALOGGED, Boolean.valueOf(false))
								.setValue(BLOODLOGGED, Boolean.valueOf(true)), voxelshape9);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(true))
								.setValue(LAVALOGGED, Boolean.valueOf(true))
								.setValue(BLOODLOGGED, Boolean.valueOf(true)), voxelshape9);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(false))
								.setValue(LAVALOGGED, Boolean.valueOf(true))
								.setValue(BLOODLOGGED, Boolean.valueOf(true)), voxelshape9);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(true))
								.setValue(LAVALOGGED, Boolean.valueOf(true))
								.setValue(BLOODLOGGED, Boolean.valueOf(false)), voxelshape9);
							builder.put(blockstate
								.setValue(WATERLOGGED, Boolean.valueOf(true))
								.setValue(LAVALOGGED, Boolean.valueOf(false))
								.setValue(BLOODLOGGED, Boolean.valueOf(true)), voxelshape9);

						}
					}
				}
			}
		}

		return builder.build();
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return this.shapeByIndex.get(pState);
	}

	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return this.collisionShapeByIndex.get(pState);
	}

	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		return false;
	}

	public boolean connectsTo(BlockState pState, boolean pSideSolid, Direction pDirection) {
		Block block = pState.getBlock();
		boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(pState, pDirection);
		return pState.is(BlockTags.WALLS) || !isExceptionForConnection(pState) && pSideSolid || block instanceof IronBarsBlock || flag;
	}

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
			.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
			.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
		return this.updateShape(levelreader, blockstate5, blockpos5, blockstate4, flag, flag1, flag2, flag3);
	}

	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		if (pState.getValue(BLOODLOGGED)) {
			pLevel.scheduleTick(pCurrentPos, RisusFluids.SOURCE_BLOOD.get(), 5);
		}
		if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pCurrentPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		if (pFacing == Direction.DOWN) {
			return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
		} else {
			return pFacing == Direction.UP ? this.topUpdate(pLevel, pState, pFacingPos, pFacingState) : this.sideUpdate(pLevel, pCurrentPos, pState, pFacingPos, pFacingState, pFacing);
		}
	}

	private static boolean isConnected(BlockState pState, Property<WallSide> pHeightProperty) {
		return pState.getValue(pHeightProperty) != WallSide.NONE;
	}

	private static boolean isCovered(VoxelShape pFirstShape, VoxelShape pSecondShape) {
		return !Shapes.joinIsNotEmpty(pSecondShape, pFirstShape, BooleanOp.ONLY_FIRST);
	}

	private BlockState topUpdate(LevelReader pLevel, BlockState pState, BlockPos pPos, BlockState pSecondState) {
		boolean flag = isConnected(pState, NORTH_WALL);
		boolean flag1 = isConnected(pState, EAST_WALL);
		boolean flag2 = isConnected(pState, SOUTH_WALL);
		boolean flag3 = isConnected(pState, WEST_WALL);
		return this.updateShape(pLevel, pState, pPos, pSecondState, flag, flag1, flag2, flag3);
	}

	private BlockState sideUpdate(LevelReader pLevel, BlockPos pFirstPos, BlockState pFirstState, BlockPos pSecondPos, BlockState pSecondState, Direction pDir) {
		Direction direction = pDir.getOpposite();
		boolean flag = pDir == Direction.NORTH ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, NORTH_WALL);
		boolean flag1 = pDir == Direction.EAST ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, EAST_WALL);
		boolean flag2 = pDir == Direction.SOUTH ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, SOUTH_WALL);
		boolean flag3 = pDir == Direction.WEST ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, WEST_WALL);
		BlockPos blockpos = pFirstPos.above();
		BlockState blockstate = pLevel.getBlockState(blockpos);
		return this.updateShape(pLevel, pFirstState, blockpos, blockstate, flag, flag1, flag2, flag3);
	}

	private BlockState updateShape(LevelReader pLevel, BlockState pState, BlockPos pPos, BlockState pNeighbour, boolean pNorthConnection, boolean pEastConnection, boolean pSouthConnection, boolean pWestConnection) {
		VoxelShape voxelshape = pNeighbour.getCollisionShape(pLevel, pPos).getFaceShape(Direction.DOWN);
		BlockState blockstate = this.updateSides(pState, pNorthConnection, pEastConnection, pSouthConnection, pWestConnection, voxelshape);
		return blockstate.setValue(UP, Boolean.valueOf(this.shouldRaisePost(blockstate, pNeighbour, voxelshape)));
	}

	private boolean shouldRaisePost(BlockState pState, BlockState pNeighbour, VoxelShape pShape) {
		boolean flag = pNeighbour.getBlock() instanceof WallBlock && pNeighbour.getValue(UP);
		if (flag) {
			return true;
		} else {
			WallSide wallside = pState.getValue(NORTH_WALL);
			WallSide wallside1 = pState.getValue(SOUTH_WALL);
			WallSide wallside2 = pState.getValue(EAST_WALL);
			WallSide wallside3 = pState.getValue(WEST_WALL);
			boolean flag1 = wallside1 == WallSide.NONE;
			boolean flag2 = wallside3 == WallSide.NONE;
			boolean flag3 = wallside2 == WallSide.NONE;
			boolean flag4 = wallside == WallSide.NONE;
			boolean flag5 = flag4 && flag1 && flag2 && flag3 || flag4 != flag1 || flag2 != flag3;
			if (flag5) {
				return true;
			} else {
				boolean flag6 = wallside == WallSide.TALL && wallside1 == WallSide.TALL || wallside2 == WallSide.TALL && wallside3 == WallSide.TALL;
				if (flag6) {
					return false;
				} else {
					return pNeighbour.is(BlockTags.WALL_POST_OVERRIDE) || isCovered(pShape, POST_TEST);
				}
			}
		}
	}

	private BlockState updateSides(BlockState pState, boolean pNorthConnection, boolean pEastConnection, boolean pSouthConnection, boolean pWestConnection, VoxelShape pWallShape) {
		return pState.setValue(NORTH_WALL, this.makeWallState(pNorthConnection, pWallShape, NORTH_TEST)).setValue(EAST_WALL, this.makeWallState(pEastConnection, pWallShape, EAST_TEST)).setValue(SOUTH_WALL, this.makeWallState(pSouthConnection, pWallShape, SOUTH_TEST)).setValue(WEST_WALL, this.makeWallState(pWestConnection, pWallShape, WEST_TEST));
	}

	private WallSide makeWallState(boolean pAllowConnection, VoxelShape pShape, VoxelShape pNeighbourShape) {
		if (pAllowConnection) {
			return isCovered(pShape, pNeighbourShape) ? WallSide.TALL : WallSide.LOW;
		} else {
			return WallSide.NONE;
		}
	}

	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {return Fluids.LAVA.getSource(false);}
		if (pState.getValue(BLOODLOGGED)) {return RisusFluids.SOURCE_BLOOD.get().getSource(false);}
		if (pState.getValue(WATERLOGGED)) {return Fluids.WATER.getSource(false);}
		else return  super.getFluidState(pState);
	}

	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return !pState.getValue(WATERLOGGED) && !pState.getValue(BLOODLOGGED) && !pState.getValue(LAVALOGGED);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(UP, NORTH_WALL, EAST_WALL, WEST_WALL, SOUTH_WALL, WATERLOGGED, LAVALOGGED, BLOODLOGGED);
	}

@Deprecated
	public BlockState rotate(BlockState pState, Rotation pRotation) {
		switch (pRotation) {
			case CLOCKWISE_180:
				return pState.setValue(NORTH_WALL, pState.getValue(SOUTH_WALL)).setValue(EAST_WALL, pState.getValue(WEST_WALL)).setValue(SOUTH_WALL, pState.getValue(NORTH_WALL)).setValue(WEST_WALL, pState.getValue(EAST_WALL));
			case COUNTERCLOCKWISE_90:
				return pState.setValue(NORTH_WALL, pState.getValue(EAST_WALL)).setValue(EAST_WALL, pState.getValue(SOUTH_WALL)).setValue(SOUTH_WALL, pState.getValue(WEST_WALL)).setValue(WEST_WALL, pState.getValue(NORTH_WALL));
			case CLOCKWISE_90:
				return pState.setValue(NORTH_WALL, pState.getValue(WEST_WALL)).setValue(EAST_WALL, pState.getValue(NORTH_WALL)).setValue(SOUTH_WALL, pState.getValue(EAST_WALL)).setValue(WEST_WALL, pState.getValue(SOUTH_WALL));
			default:
				return pState;
		}
	}

	@Deprecated
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		switch (pMirror) {
			case LEFT_RIGHT:
				return pState.setValue(NORTH_WALL, pState.getValue(SOUTH_WALL)).setValue(SOUTH_WALL, pState.getValue(NORTH_WALL));
			case FRONT_BACK:
				return pState.setValue(EAST_WALL, pState.getValue(WEST_WALL)).setValue(WEST_WALL, pState.getValue(EAST_WALL));
			default:
				return super.mirror(pState, pMirror);
		}
	}

	@Override
	public boolean isEnabled(FeatureFlagSet pEnabledFeatures) {
		return super.isEnabled(pEnabledFeatures);
	}

	@Override
	public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
		return pFluid == RisusFluids.SOURCE_BLOOD.get() || pFluid == Fluids.LAVA || pFluid == Fluids.WATER;
	}

	@Override
	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		//blood
		if (!pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED) && pFluidState.getType() == RisusFluids.SOURCE_BLOOD.get()) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
						.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(true))
						.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		//lava
		if (!pState.getValue(SimpleMultiloggedBlock.LAVALOGGED) && pFluidState.getType() == Fluids.LAVA) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
						.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(true))
						.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		//water
		if (!pState.getValue(SimpleMultiloggedBlock.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(true))
						.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ItemStack pickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		//blood
		if (pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(RisusItems.BLOOD_BUCKET.get());
		}
		//lava
		if (pState.getValue(SimpleMultiloggedBlock.LAVALOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(Items.LAVA_BUCKET);
		}
		//water
		if (pState.getValue(SimpleMultiloggedBlock.WATERLOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(Items.WATER_BUCKET);
		} else
		{
			return ItemStack.EMPTY;
		}
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}
}

