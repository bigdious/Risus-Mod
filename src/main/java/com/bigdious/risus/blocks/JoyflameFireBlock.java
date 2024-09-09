package com.bigdious.risus.blocks;

import com.bigdious.risus.data.BlockTagGenerator;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class JoyflameFireBlock extends SoulFireBlock {

	public JoyflameFireBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return canSurviveOnBlock(context.getLevel().getBlockState(context.getClickedPos().below())) ? RisusBlocks.JOYFLAME_FIRE.get().defaultBlockState() : Blocks.AIR.defaultBlockState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
		return this.canSurvive(state, accessor, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
	}
	@Override
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
		if (pRandom.nextInt(24) == 0) {
			pLevel.playLocalSound(
				(double)pPos.getX() + 0.5,
				(double)pPos.getY() + 0.5,
				(double)pPos.getZ() + 0.5,
				SoundEvents.FIRE_AMBIENT,
				SoundSource.BLOCKS,
				1.0F + pRandom.nextFloat(),
				pRandom.nextFloat() * 0.7F + 0.3F,
				false
			);
		}

		BlockPos blockpos = pPos.below();
		BlockState blockstate = pLevel.getBlockState(blockpos);
		if (!this.canBurn(blockstate) && !blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP)) {
			if (this.canBurn(pLevel.getBlockState(pPos.west()))) {
				for (int j = 0; j < 2; j++) {
					double d3 = (double)pPos.getX() + pRandom.nextDouble() * 0.1F;
					double d8 = (double)pPos.getY() + pRandom.nextDouble();
					double d13 = (double)pPos.getZ() + pRandom.nextDouble();
					pLevel.addParticle(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), d3, d8, d13, 0.0, 0.0, 0.0);
				}
			}

			if (this.canBurn(pLevel.getBlockState(pPos.east()))) {
				for (int k = 0; k < 2; k++) {
					double d4 = (double)(pPos.getX() + 1) - pRandom.nextDouble() * 0.1F;
					double d9 = (double)pPos.getY() + pRandom.nextDouble();
					double d14 = (double)pPos.getZ() + pRandom.nextDouble();
					pLevel.addParticle(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), d4, d9, d14, 0.0, 0.0, 0.0);
				}
			}

			if (this.canBurn(pLevel.getBlockState(pPos.north()))) {
				for (int l = 0; l < 2; l++) {
					double d5 = (double)pPos.getX() + pRandom.nextDouble();
					double d10 = (double)pPos.getY() + pRandom.nextDouble();
					double d15 = (double)pPos.getZ() + pRandom.nextDouble() * 0.1F;
					pLevel.addParticle(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), d5, d10, d15, 0.0, 0.0, 0.0);
				}
			}

			if (this.canBurn(pLevel.getBlockState(pPos.south()))) {
				for (int i1 = 0; i1 < 2; i1++) {
					double d6 = (double)pPos.getX() + pRandom.nextDouble();
					double d11 = (double)pPos.getY() + pRandom.nextDouble();
					double d16 = (double)(pPos.getZ() + 1) - pRandom.nextDouble() * 0.1F;
					pLevel.addParticle(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), d6, d11, d16, 0.0, 0.0, 0.0);
				}
			}

			if (this.canBurn(pLevel.getBlockState(pPos.above()))) {
				for (int j1 = 0; j1 < 2; j1++) {
					double d7 = (double)pPos.getX() + pRandom.nextDouble();
					double d12 = (double)(pPos.getY() + 1) - pRandom.nextDouble() * 0.1F;
					double d17 = (double)pPos.getZ() + pRandom.nextDouble();
					pLevel.addParticle(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), d7, d12, d17, 0.0, 0.0, 0.0);
				}
			}
		} else {
			for (int i = 0; i < 3; i++) {
				double d0 = (double)pPos.getX() + pRandom.nextDouble();
				double d1 = (double)pPos.getY() + pRandom.nextDouble() * 0.5 + 0.5;
				double d2 = (double)pPos.getZ() + pRandom.nextDouble();
				pLevel.addParticle(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), d0, d1, d2, 0.0, 0.0, 0.0);
			}
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return canSurviveOnBlock(reader.getBlockState(pos.below()));
	}

	public static boolean canSurviveOnBlock(BlockState state) {
		return state.is(BlockTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS);
	}

	@Override
	protected boolean canBurn(BlockState state) {
		return true;
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!level.isClientSide() && entity instanceof LivingEntity living && living.isAlive() && (living.tickCount % 100 == 0 || !living.hasEffect(RisusMobEffects.EXBURN))) {
			living.addEffect(new MobEffectInstance(RisusMobEffects.EXBURN, 600));
		}
	}
}

