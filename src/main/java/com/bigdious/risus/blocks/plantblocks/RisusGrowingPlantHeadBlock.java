package com.bigdious.risus.blocks.plantblocks;

import com.bigdious.risus.blocks.OrganicMatterableBlock;
import com.bigdious.risus.init.RisusFluids;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class RisusGrowingPlantHeadBlock extends RisusGrowingPlantBlock implements OrganicMatterableBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_25;
	public static final int MAX_AGE = 25;
	private final double growPerTickProbability;

	protected RisusGrowingPlantHeadBlock(BlockBehaviour.Properties pProperties, Direction pGrowthDirection, VoxelShape pShape, boolean pScheduleFluidTicks, double pGrowPerTickProbability) {
		super(pProperties, pGrowthDirection, pShape, pScheduleFluidTicks);
		this.growPerTickProbability = pGrowPerTickProbability;
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
	}

	@Override
	protected abstract MapCodec<? extends RisusGrowingPlantHeadBlock> codec();

	@Override
	public BlockState getStateForPlacement(LevelAccessor pLevel) {
		return this.defaultBlockState().setValue(AGE, Integer.valueOf(pLevel.getRandom().nextInt(25)));
	}

	@Override
	protected boolean isRandomlyTicking(BlockState pState) {
		return pState.getValue(AGE) < 25;
	}

	@Override
	protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		if (pState.getValue(AGE) < 25 && net.neoforged.neoforge.common.CommonHooks.canCropGrow(pLevel, pPos.relative(this.growthDirection), pState, pRandom.nextDouble() < this.growPerTickProbability)) {
			BlockPos blockpos = pPos.relative(this.growthDirection);
			if (this.canGrowInto(pLevel.getBlockState(blockpos))) {
				pLevel.setBlockAndUpdate(blockpos, this.getGrowIntoState(pState, pLevel.random));
				net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(pLevel, blockpos, pLevel.getBlockState(blockpos));
			}
		}
	}

	protected BlockState getGrowIntoState(BlockState pState, RandomSource pRandom) {
		return pState.cycle(AGE);
	}

	public BlockState getMaxAgeState(BlockState pState) {
		return pState.setValue(AGE, Integer.valueOf(25));
	}

	public boolean isMaxAge(BlockState pState) {
		return pState.getValue(AGE) == 25;
	}

	protected BlockState updateBodyAfterConvertedFromHead(BlockState pHead, BlockState pBody) {
		return pBody;
	}
	@Override
	protected BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		if (pFacing == this.growthDirection.getOpposite() && !pState.canSurvive(pLevel, pCurrentPos)) {
			pLevel.scheduleTick(pCurrentPos, this, 1);
		}

		if (pFacing != this.growthDirection || !pFacingState.is(this) && !pFacingState.is(this.getBodyBlock())) {
			if (this.scheduleFluidTicks) {
				pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
				pLevel.scheduleTick(pCurrentPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
				pLevel.scheduleTick(pCurrentPos, RisusFluids.SOURCE_BLOOD.get(), RisusFluids.SOURCE_BLOOD.get().getTickDelay(pLevel));
			}

			return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
		} else {
			return this.updateBodyAfterConvertedFromHead(pState, this.getBodyBlock().defaultBlockState());
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(AGE);
	}

	@Override
	public boolean isValidOrganicMatterTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
		return this.canGrowInto(pLevel.getBlockState(pPos.relative(this.growthDirection)));
	}

	@Override
	public boolean isOrganicMatterSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		return true;
	}

	@Override
	public void performOrganicMatter(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		BlockPos blockpos = pPos.relative(this.growthDirection);
		int i = Math.min(pState.getValue(AGE) + 1, 25);
		int j = this.getBlocksToGrowWhenOrganicMattered(pRandom);

		for (int k = 0; k < j && this.canGrowInto(pLevel.getBlockState(blockpos)); k++) {
			pLevel.setBlockAndUpdate(blockpos, pState.setValue(AGE, Integer.valueOf(i)));
			blockpos = blockpos.relative(this.growthDirection);
			i = Math.min(i + 1, 25);
		}
	}

	protected abstract int getBlocksToGrowWhenOrganicMattered(RandomSource pRandom);

	protected abstract boolean canGrowInto(BlockState pState);

	@Override
	protected RisusGrowingPlantHeadBlock getHeadBlock() {
		return this;
	}
}

