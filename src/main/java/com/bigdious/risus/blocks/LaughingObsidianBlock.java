package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusParticles;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CryingObsidianBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LaughingObsidianBlock extends Block {
	public static final MapCodec<LaughingObsidianBlock> CODEC = simpleCodec(LaughingObsidianBlock::new);

	@Override
	public MapCodec<LaughingObsidianBlock> codec() {
		return CODEC;
	}
	public LaughingObsidianBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (random.nextInt(5) == 0) {
			Direction direction = Direction.getRandom(random);
			if (direction != Direction.UP) {
				BlockPos blockpos = pos.relative(direction);
				BlockState blockstate = level.getBlockState(blockpos);
				if (!state.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, direction.getOpposite())) {
					double d0 = direction.getStepX() == 0 ? random.nextDouble() : 0.5D + (double) direction.getStepX() * 0.6D;
					double d1 = direction.getStepY() == 0 ? random.nextDouble() : 0.5D + (double) direction.getStepY() * 0.6D;
					double d2 = direction.getStepZ() == 0 ? random.nextDouble() : 0.5D + (double) direction.getStepZ() * 0.6D;
					level.addParticle(RisusParticles.DRIPPING_JOY.get(),
						(double) pos.getX() + d0,
						(double) pos.getY() + d1,
						(double) pos.getZ() + d2,
						0.0D,
						0.0D,
						0.0D);
				}
			}
		}
	}
}
