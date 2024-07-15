package com.bigdious.risus.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class ActuallyUseableDirectionalBlock extends DirectionalBlock {
	MapCodec<ActuallyUseableDirectionalBlock> CODEC = simpleCodec(ActuallyUseableDirectionalBlock::new);

	public ActuallyUseableDirectionalBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.UP));
	}

	@Override
	protected MapCodec<? extends ActuallyUseableDirectionalBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction clicked = context.getClickedFace();
		BlockState state = defaultBlockState().setValue(FACING, clicked);
		if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
			return state;
		}
		for (Direction dir : context.getNearestLookingDirections()) {
			state = defaultBlockState().setValue(FACING, dir.getOpposite());
			if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
				return state;
			}
		}
		return null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}
}
