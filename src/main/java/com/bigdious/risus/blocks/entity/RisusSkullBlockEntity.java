package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RisusSkullBlockEntity extends SkullBlockEntity {
	private int mouthTickCount;
	private boolean isMovingMouth;

	public RisusSkullBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, RisusSkullBlockEntity te) {
		if (level.isClientSide()) {
			if (level.hasNeighborSignal(pos)) {
				te.isMovingMouth = true;
				++te.mouthTickCount;
			} else {
				te.isMovingMouth = false;
			}
		}
	}

	@Override
	public float getAnimation(float p_184295_1_) {
		return this.isMovingMouth ? (float) this.mouthTickCount + p_184295_1_ : (float) this.mouthTickCount;
	}

	@Override
	public BlockEntityType<?> getType() {
		return RisusBlockEntities.RISUS_SKULL.get();
	}
}
