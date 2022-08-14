package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.AlterationMonolithBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AlterationMonolithBlock extends BaseEntityBlock {

	public AlterationMonolithBlock(Properties properties) {
		super(properties);
	}

	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AlterationMonolithBlockEntity(pos, state);
	}
}
