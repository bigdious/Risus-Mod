package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RisusHangingSignBlockEntity extends HangingSignBlockEntity {
	public RisusHangingSignBlockEntity(BlockPos p_250603_, BlockState p_251674_) {
		super(p_250603_, p_251674_);
	}

	@Override
	public BlockEntityType<?> getType() {
		return RisusBlockEntities.RISUS_HANGING_SIGN.get();
	}
}
