package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModdedTorchBlock extends TorchBlock {
	public ModdedTorchBlock(Properties properties) {
		super(ParticleTypes.FLAME, properties);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;
		level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		level.addParticle(RisusParticles.JOYFLAME.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}
