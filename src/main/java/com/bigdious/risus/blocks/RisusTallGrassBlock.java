package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class RisusTallGrassBlock extends CrystallizedBondsBlock {
	public RisusTallGrassBlock(Properties properties) {
		super(properties);
	}

	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.UP, Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D),
			Direction.DOWN, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 16.0D, 14.0D),
			Direction.EAST, Block.box(0.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D),
			Direction.WEST, Block.box(2.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D),
			Direction.NORTH, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 16.0D),
			Direction.SOUTH, Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 14.0D)
	));

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}
}
