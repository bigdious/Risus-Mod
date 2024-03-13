package com.bigdious.risus.blocks.fluid;

import com.bigdious.risus.data.FluidTagGenerator;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.Random;

public class BloodFluid extends ForgeFlowingFluid {
	//Credit to JoeFoxe and his mod Hexerei for this beautiful blood fluid and particles
	protected BloodFluid(Properties properties) {
		super(properties);
	}


	@Override
	public boolean isSource(FluidState pState) {
		return false;
	}

	@Override
	public int getAmount(FluidState pState) {
		return 0;
	}


	protected boolean canConvertToSource(Level pLevel) {
		//TODO look into gamerules
		return pLevel.getGameRules().getBoolean(GameRules.RULE_WATER_SOURCE_CONVERSION);
	}

	@Override
	protected void randomTick(Level world, BlockPos pos, FluidState state, RandomSource random) {
		super.randomTick(world, pos, state, random);
	}

	@Override
	public void tick(Level worldIn, BlockPos pos, FluidState state) {

		super.tick(worldIn, pos, state);
	}
	@Override
	public int getTickDelay(LevelReader p_205569_1_) {
		return 5;
	}


	public static class Flowing extends BloodFluid {
		public Flowing(Properties properties) {
			super(properties);
			this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 7));
		}
		@Override
		protected boolean isRandomlyTicking() {
			return true;
		}

		@Override
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		@Override
		public boolean isSource(FluidState state) {
			return false;
		}
		@OnlyIn(Dist.CLIENT)
		public void animateTick(Level worldIn, BlockPos pos, FluidState state, RandomSource random) {
			if (!state.isSource() && !state.getValue(FALLING)) {
				if (random.nextInt(64) == 0) {
					worldIn.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F);
				}
			} else if (random.nextInt(12) == 0) {
				if (random.nextInt(3) == 0)
					worldIn.addParticle(RisusParticles.BLOOD.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + (random.nextDouble() * (state.getValue(LEVEL) / 8)), (double)pos.getZ() + random.nextDouble(), -0.01D+(random.nextDouble()*0.02), -0.02D+(random.nextDouble()*0.02), -0.01D+(random.nextDouble()*0.02));
				worldIn.addParticle(RisusParticles.BLOOD_BIT.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + (random.nextDouble() * (state.getValue(LEVEL) / 8)), (double)pos.getZ() + random.nextDouble(), -0.01D+(random.nextDouble()*0.02), -0.01D+(random.nextDouble()*0.02), -0.01D+(random.nextDouble()*0.02));

			}
		}
	}

	public static class Source extends BloodFluid {
		public Source(Properties properties) {
			super(properties);
		}

		@Override
		public int getAmount(FluidState state) {
			return 8;
		}

		@Override
		public boolean isSource(FluidState state) {
			return true;
		}
		@Override
		protected boolean isRandomlyTicking() {
			return true;
		}

		@OnlyIn(Dist.CLIENT)
		public void animateTick(Level worldIn, BlockPos pos, FluidState state, RandomSource random) {
			if (!state.isSource() && !state.getValue(FALLING)) {
				if (random.nextInt(64) == 0) {
					worldIn.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F);
				}
			} else if (random.nextInt(14) == 0) {
				if (random.nextInt(2) == 0)
					worldIn.addParticle(RisusParticles.BLOOD.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + (random.nextDouble()), (double)pos.getZ() + random.nextDouble(), -0.01D+(random.nextDouble()*0.02), -0.01D+(random.nextDouble()*0.02), -0.01D+(random.nextDouble()*0.02));
				worldIn.addParticle(RisusParticles.BLOOD_BIT.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + (random.nextDouble()), (double)pos.getZ() + random.nextDouble(), -0.01D+(random.nextDouble()*0.02), -0.01D+(random.nextDouble()*0.02), -0.01D+(random.nextDouble()*0.02));

			}
		}

	}

}

