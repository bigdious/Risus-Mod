package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TesseractBlockEntity extends BlockEntity {
	public TesseractBlockEntity(BlockPos pos, BlockState blockState) {
		super(RisusBlockEntities.TESSERACT.get(), pos, blockState);
	}
}
