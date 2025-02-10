package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BloodySpongeBlock extends Block {

	public BloodySpongeBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, world, pos, oldState, isMoving);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState();
	}

	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		Direction direction = Direction.getRandom(random);
		if (direction != Direction.UP) {
			BlockPos blockpos = pos.relative(direction);
			BlockState blockstate = level.getBlockState(blockpos);
			if (!state.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, direction.getOpposite())) {
				double d0 = (double)pos.getX();
				double d1 = (double)pos.getY();
				double d2 = (double)pos.getZ();
				if (direction == Direction.DOWN) {
					d1 -= 0.05;
					d0 += random.nextDouble();
					d2 += random.nextDouble();
				} else {
					d1 += random.nextDouble() * 0.8;
					if (direction.getAxis() == Direction.Axis.X) {
						d2 += random.nextDouble();
						if (direction == Direction.EAST) {
							++d0;
						} else {
							d0 += 0.05;
						}
					} else {
						d0 += random.nextDouble();
						if (direction == Direction.SOUTH) {
							++d2;
						} else {
							d2 += 0.05;
						}
					}
				}

				level.addParticle(RisusParticles.BLOOD_BIT.get(), d0, d1, d2, 0.0, 0.0, 0.0);
			}
		}

	}


}
