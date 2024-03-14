package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.Optional;

public interface SimpleMultiloggedBlock extends BucketPickup, LiquidBlockContainer {
	BooleanProperty BLOODLOGGED = BooleanProperty.create("bloodlogged");
	BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");
	BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");
	default boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
		return pFluid == RisusFluids.SOURCE_BLOOD.get() || pFluid == Fluids.LAVA || pFluid == Fluids.WATER;
	}
	default boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		//blood
		if (!pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED) && pFluidState.getType() == RisusFluids.SOURCE_BLOOD.get()) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
					.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(true))
					.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		//lava
		if (!pState.getValue(SimpleMultiloggedBlock.LAVALOGGED) && pFluidState.getType() == Fluids.LAVA) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
					.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(true))
					.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
					.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		//water
		if (!pState.getValue(SimpleMultiloggedBlock.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
					.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(true))
					.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
					.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
					, 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		else {
			return false;
		}
	}

	default ItemStack pickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		//blood
		if (pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(RisusItems.BLOOD_BUCKET.get());
		}
			//lava
			if (pState.getValue(SimpleMultiloggedBlock.LAVALOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(Items.LAVA_BUCKET);
		}
			//water
				if (pState.getValue(SimpleMultiloggedBlock.WATERLOGGED)) {
					pLevel.setBlock(pPos, pState.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false)), 3);
					if (!pState.canSurvive(pLevel, pPos)) {
						pLevel.destroyBlock(pPos, true);
					}

					return new ItemStack(Items.WATER_BUCKET);
				} else
				{
			return ItemStack.EMPTY;
		}
	}

	default Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}
}

