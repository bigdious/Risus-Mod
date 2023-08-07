package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class BabyRibcageBlock extends HorizontalDirectionalBlock {

	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(3.5D, 1.0D, 8.5D, 12.5D, 16.0D, 16.0D),
			Direction.SOUTH, Block.box(3.5D, 1.0D, 0.0D, 12.5D, 16.0D, 7.5D),
			Direction.WEST, Block.box(8.5D, 1.0D, 3.5D, 16.0D, 16.0D, 12.5D),
			Direction.EAST, Block.box(0.0D, 1.0D, 3.5D, 7.5D, 16.0D, 12.5D)));

	public BabyRibcageBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
		return accessor.getBlockState(pos.above()).isSolidRender(accessor, pos.above()) ? super.updateShape(state, direction, newState, accessor, pos, newPos) : Blocks.AIR.defaultBlockState();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return context.getLevel().getBlockState(context.getClickedPos().above()).isSolidRender(context.getLevel(), context.getClickedPos().above()) ? this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()) : null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 1.F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}
}
