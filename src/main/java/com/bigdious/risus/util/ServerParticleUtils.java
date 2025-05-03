package com.bigdious.risus.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;


public class ServerParticleUtils {
	public static void spawnParticlesOnBlockFaces(Level level, BlockPos pos, ParticleOptions particle, IntProvider count) {
		for (Direction direction : Direction.values()) {
			spawnParticlesOnBlockFace(level, pos, particle, count, direction, () -> getRandomSpeedRanges(level.random), 0.55);
		}
	}

	public static void spawnParticlesOnBlockFace(
		Level level, BlockPos pos, ParticleOptions particle, IntProvider count, Direction direction, Supplier<Vec3> speedSupplier, double spread
	) {
		int i = count.sample(level.random);

		for (int j = 0; j < i; j++) {
			spawnParticleOnFace(level, pos, direction, particle, speedSupplier.get(), spread);
		}
	}

	private static Vec3 getRandomSpeedRanges(RandomSource random) {
		return new Vec3(Mth.nextDouble(random, -0.5, 0.5), Mth.nextDouble(random, -0.5, 0.5), Mth.nextDouble(random, -0.5, 0.5));
	}

	public static void spawnParticlesAlongAxis(Direction.Axis axis, Level level, BlockPos pos, double spread, ParticleOptions particle, UniformInt count) {
		if (level instanceof ServerLevel serverLevel) {
			Vec3 vec3 = Vec3.atCenterOf(pos);
			boolean flag = axis == Direction.Axis.X;
			boolean flag1 = axis == Direction.Axis.Y;
			boolean flag2 = axis == Direction.Axis.Z;
			int i = count.sample(level.random);

			for (int j = 0; j < i; j++) {
				double d0 = vec3.x + Mth.nextDouble(level.random, -1.0, 1.0) * (flag ? 0.5 : spread);
				double d1 = vec3.y + Mth.nextDouble(level.random, -1.0, 1.0) * (flag1 ? 0.5 : spread);
				double d2 = vec3.z + Mth.nextDouble(level.random, -1.0, 1.0) * (flag2 ? 0.5 : spread);
				double d3 = flag ? Mth.nextDouble(level.random, -1.0, 1.0) : 0.0;
				double d4 = flag1 ? Mth.nextDouble(level.random, -1.0, 1.0) : 0.0;
				double d5 = flag2 ? Mth.nextDouble(level.random, -1.0, 1.0) : 0.0;
				serverLevel.sendParticles(particle, d0, d1, d2, 1, 0, 0, 0 , (d3+d4+d5)/3);
			}
		}
	}

	public static void spawnParticleOnFace(Level level, BlockPos pos, Direction direction, ParticleOptions particle, Vec3 speed, double spread) {
		if (level instanceof ServerLevel serverLevel) {
			Vec3 vec3 = Vec3.atCenterOf(pos);
			int i = direction.getStepX();
			int j = direction.getStepY();
			int k = direction.getStepZ();
			double d0 = vec3.x + (i == 0 ? Mth.nextDouble(level.random, -0.5, 0.5) : (double) i * spread);
			double d1 = vec3.y + (j == 0 ? Mth.nextDouble(level.random, -0.5, 0.5) : (double) j * spread);
			double d2 = vec3.z + (k == 0 ? Mth.nextDouble(level.random, -0.5, 0.5) : (double) k * spread);
			double d3 = i == 0 ? speed.x() : 0.0;
			double d4 = j == 0 ? speed.y() : 0.0;
			double d5 = k == 0 ? speed.z() : 0.0;
			serverLevel.sendParticles(particle, d0, d1, d2, 1, 0, 0, 0 , (d3+d4+d5)/3);
		}
	}

	public static void spawnParticleBelow(Level level, BlockPos pos, RandomSource random, ParticleOptions particle) {
		if (level instanceof ServerLevel serverLevel) {
			double d0 = (double) pos.getX() + random.nextDouble();
			double d1 = (double) pos.getY() - 0.05;
			double d2 = (double) pos.getZ() + random.nextDouble();
			serverLevel.sendParticles(particle, d0, d1, d2, 1, 0, 0, 0, 0);
		}
	}

	public static void spawnParticleInBlock(LevelAccessor level, BlockPos pos, int count, ParticleOptions particle) {
		double d0 = 0.5;
		BlockState blockstate = level.getBlockState(pos);
		double d1 = blockstate.isAir() ? 1.0 : blockstate.getShape(level, pos).max(Direction.Axis.Y);
		spawnParticles(level, pos, count, 0.5, d1, true, particle);
	}

	public static void spawnParticles(LevelAccessor level, BlockPos pos, int count, double xzSpread, double ySpread, boolean allowInAir, ParticleOptions particle) {
		if (level instanceof ServerLevel serverLevel) {
			RandomSource randomsource = level.getRandom();

			for (int i = 0; i < count; i++) {
				double d0 = randomsource.nextGaussian() * 0.02;
				double d1 = randomsource.nextGaussian() * 0.02;
				double d2 = randomsource.nextGaussian() * 0.02;
				double d3 = 0.5 - xzSpread;
				double d4 = (double) pos.getX() + d3 + randomsource.nextDouble() * xzSpread * 2.0;
				double d5 = (double) pos.getY() + randomsource.nextDouble() * ySpread;
				double d6 = (double) pos.getZ() + d3 + randomsource.nextDouble() * xzSpread * 2.0;
				if (allowInAir || !level.getBlockState(BlockPos.containing(d4, d5, d6).below()).isAir()) {
					serverLevel.sendParticles(particle, d4, d5, d6, 1,0,0, 0, d0);
				}
			}

		}
	}

	public static void spawnSmashAttackParticles(LevelAccessor level, BlockPos pos, int power) {
		if (level instanceof ServerLevel serverLevel) {
			Vec3 vec3 = pos.getCenter().add(0.0, 0.5, 0.0);
			BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.DUST_PILLAR, level.getBlockState(pos));

			for (int i = 0; (float) i < (float) power / 3.0F; i++) {
				double d0 = vec3.x + level.getRandom().nextGaussian() / 2.0;
				double d1 = vec3.y;
				double d2 = vec3.z + level.getRandom().nextGaussian() / 2.0;
				double d3 = level.getRandom().nextGaussian() * 0.2F;
				double d4 = level.getRandom().nextGaussian() * 0.2F;
				double d5 = level.getRandom().nextGaussian() * 0.2F;
				serverLevel.sendParticles(blockparticleoption, d0, d1, d2, 1, 0, 0, 0, (d3 + d4 + d5) / 3);
			}

			for (int j = 0; (float) j < (float) power / 1.5F; j++) {
				double d6 = vec3.x + 3.5 * Math.cos(j) + level.getRandom().nextGaussian() / 2.0;
				double d7 = vec3.y;
				double d8 = vec3.z + 3.5 * Math.sin(j) + level.getRandom().nextGaussian() / 2.0;
				double d9 = level.getRandom().nextGaussian() * 0.05F;
				double d10 = level.getRandom().nextGaussian() * 0.05F;
				double d11 = level.getRandom().nextGaussian() * 0.05F;
				serverLevel.sendParticles(blockparticleoption, d6, d7, d8, 1, 0, 0, 0, (d9 + d10 + d11) / 3);
			}
		}
	}
}
