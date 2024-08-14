package com.bigdious.risus.blocks.plantblocks;

import com.bigdious.risus.blocks.OrganicMatterableBlock;
import com.bigdious.risus.init.RisusFluids;
import com.mojang.serialization.MapCodec;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public abstract class RisusGrowingPlantBodyBlock extends RisusGrowingPlantBlock implements OrganicMatterableBlock {
	protected RisusGrowingPlantBodyBlock(BlockBehaviour.Properties pProperties, Direction pGrowthDirection, VoxelShape pShape, boolean pScheduleFluidTicks) {
		super(pProperties, pGrowthDirection, pShape, pScheduleFluidTicks);
	}

	@Override
	protected abstract MapCodec<? extends RisusGrowingPlantBodyBlock> codec();

	protected BlockState updateHeadAfterConvertedFromBody(BlockState pHead, BlockState pBody) {
		return pBody;
	}

	@Override
	protected BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		if (pFacing == this.growthDirection.getOpposite() && !pState.canSurvive(pLevel, pCurrentPos)) {
			pLevel.scheduleTick(pCurrentPos, this, 1);
		}

		RisusGrowingPlantHeadBlock growingplantheadblock = this.getHeadBlock();
		if (pFacing == this.growthDirection && !pFacingState.is(this) && !pFacingState.is(growingplantheadblock)) {
			return this.updateHeadAfterConvertedFromBody(pState, growingplantheadblock.getStateForPlacement(pLevel));
		} else {
			if (this.scheduleFluidTicks) {
				pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
				pLevel.scheduleTick(pCurrentPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
				pLevel.scheduleTick(pCurrentPos, RisusFluids.SOURCE_BLOOD.get(), RisusFluids.SOURCE_BLOOD.get().getTickDelay(pLevel));
			}

			return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
		}
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
		return new ItemStack(this.getHeadBlock());
	}

	@Override
	public boolean isValidOrganicMatterTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
		Optional<BlockPos> optional = this.getHeadPos(pLevel, pPos, pState.getBlock());
		return optional.isPresent() && this.getHeadBlock().canGrowInto(pLevel.getBlockState(optional.get().relative(this.growthDirection)));
	}

	@Override
	public boolean isOrganicMatterSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		return true;
	}

	@Override
	public void performOrganicMatter(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		Optional<BlockPos> optional = this.getHeadPos(pLevel, pPos, pState.getBlock());
		if (optional.isPresent()) {
			BlockState blockstate = pLevel.getBlockState(optional.get());
			((RisusGrowingPlantHeadBlock)blockstate.getBlock()).performOrganicMatter(pLevel, pRandom, optional.get(), blockstate);
		}
	}

	private Optional<BlockPos> getHeadPos(BlockGetter pLevel, BlockPos pPos, Block pBlock) {
		return BlockUtil.getTopConnectedBlock(pLevel, pPos, pBlock, this.growthDirection, this.getHeadBlock());
	}

	@Override
	protected boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
		boolean flag = super.canBeReplaced(pState, pUseContext);
		return flag && pUseContext.getItemInHand().is(this.getHeadBlock().asItem()) ? false : flag;
	}

	@Override
	protected Block getBodyBlock() {
		return this;
	}
}

