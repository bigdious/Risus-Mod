package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class RisusWallBlock extends WallBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusWallBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(UP, true)
				.setValue(NORTH_WALL, WallSide.NONE)
				.setValue(EAST_WALL, WallSide.NONE)
				.setValue(SOUTH_WALL, WallSide.NONE)
				.setValue(WEST_WALL, WallSide.NONE)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
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
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY).setValue(WATERLOGGED, false), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.LAVA).setValue(WATERLOGGED, false), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.WATER).setValue(WATERLOGGED, false), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.BLOOD).setValue(WATERLOGGED, false), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY).setValue(WATERLOGGED, true), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.LAVA).setValue(WATERLOGGED, true), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.WATER).setValue(WATERLOGGED, true), voxelshape9);
							builder.put(blockstate.setValue(FLUIDLOGGED, MultiloggingEnum.BLOOD).setValue(WATERLOGGED, true), voxelshape9);
						}
					}
				}
			}
		}

		return builder.build();
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
	private boolean connectsTo(BlockState p_58021_, boolean p_58022_, Direction p_58023_) {
		Block block = p_58021_.getBlock();
		boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(p_58021_, p_58023_);
		return p_58021_.is(BlockTags.WALLS) || !isExceptionForConnection(p_58021_) && p_58022_ || block instanceof IronBarsBlock || flag;
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

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return pState.getValue(FLUIDLOGGED) == MultiloggingEnum.EMPTY;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(UP, NORTH_WALL, EAST_WALL, WEST_WALL, SOUTH_WALL, WATERLOGGED, FLUIDLOGGED);
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
	public Optional<SoundEvent> getPickupSound() {
		return Optional.empty();
	}

	@Override
	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		return SimpleMultiloggedBlock.super.placeLiquid(pLevel, pPos, pState, pFluidState);
	}
}

