package com.bigdious.risus.blocks.fluid;

import com.bigdious.risus.data.FluidTagGenerator;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class BloodFluid extends ForgeFlowingFluid {
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

	protected void spreadTo(LevelAccessor pLevel, BlockPos pPos, BlockState pBlockState, Direction pDirection, FluidState pFluidState) {
		if (pDirection == Direction.DOWN) {
			FluidState fluidstate = pLevel.getFluidState(pPos);
			if (this.is(FluidTagGenerator.BLOODFLUID) && fluidstate.is(FluidTags.WATER)) {
				if (pBlockState.getBlock() instanceof LiquidBlock) {
					pLevel.setBlock(pPos, RisusBlocks.COAGULATED_BLOOD_BLOCK.get().defaultBlockState(), 3);
				}
				return;
			}
		}


		super.spreadTo(pLevel, pPos, pBlockState, pDirection, pFluidState);
	}
	protected boolean canConvertToSource(Level pLevel) {
		//TODO look into gamerules
		return pLevel.getGameRules().getBoolean(GameRules.RULE_WATER_SOURCE_CONVERSION);
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
	}
}

