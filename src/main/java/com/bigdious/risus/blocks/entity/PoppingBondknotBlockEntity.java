package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.CrystallizedBondsBlock;
import com.bigdious.risus.blocks.PoppingBondknotBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PoppingBondknotBlockEntity extends BlockEntity {

	private int popTimer = 0;
	private boolean popping = false;
	public final Direction popDir;
	private int secondTicker;

	public PoppingBondknotBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.POPPING_BONDKNOT.get(), pos, state);
		this.popDir = state.getValue(PoppingBondknotBlock.POP_SIDE);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, PoppingBondknotBlockEntity te) {
		if (te.popDir != null) {
			if (!te.popping && te.secondTicker++ >= 20) {
				if (level.getRandom().nextInt(500) == 0) {
					te.popping = true;
				}
				te.secondTicker = 0;
			}

			if (level.getBlockState(pos.relative(te.popDir)).canBeReplaced() && te.popping) {
				te.particles((ServerLevel) level);
				te.popTimer++;
			}

			if (level.getBlockState(pos.relative(te.popDir)).canBeReplaced() && te.popTimer > 5) {
				te.createCrystal(level);
				te.popping = false;
				te.popTimer = 0;
			}
		}
	}

	private void particles(ServerLevel level) {
		if (this.popDir != null) {
			BlockPos crystal = this.getBlockPos().relative(this.popDir);
			level.sendParticles(DustParticleOptions.REDSTONE,
					crystal.getX() - (this.popDir.getStepX() * 0.5F) + 0.5F,
					crystal.getY() - (this.popDir.getStepY() * 0.5F) + 0.5F,
					crystal.getZ() - (this.popDir.getStepZ() * 0.5F) + 0.5F, 5,
					0.1D, 0.1D, 0.1D, 0);
		}
	}

	private void createCrystal(Level level) {
		if (this.popDir != null) {
			level.setBlockAndUpdate(this.getBlockPos().relative(this.popDir), RisusBlocks.CRYSTALLIZED_BONDS.get().defaultBlockState().setValue(CrystallizedBondsBlock.FACING, this.popDir));
			level.playSound(null, this.getBlockPos().relative(this.popDir), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0f);
		}
	}
}
