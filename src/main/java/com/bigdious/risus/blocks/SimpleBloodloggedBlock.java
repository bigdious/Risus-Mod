package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusBaseFluidType;
import com.bigdious.risus.fluid.RisusFluidTypes;
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

public interface SimpleBloodloggedBlock extends BucketPickup, LiquidBlockContainer {
	BooleanProperty BLOODLOGGED = BooleanProperty.create("waterlogged");
	default boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
		return pFluid == RisusFluids.SOURCE_BLOOD.get();
	}
	default boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		if (!pState.getValue(BLOODLOGGED) && pFluidState.getType() == RisusFluids.SOURCE_BLOOD.get()) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState.setValue(BLOODLOGGED, Boolean.valueOf(true)), 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}

			return true;
		} else {
			return false;
		}
	}

	default ItemStack pickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		if (pState.getValue(BlockStateProperties.WATERLOGGED)) {
			pLevel.setBlock(pPos, pState.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);
			if (!pState.canSurvive(pLevel, pPos)) {
				pLevel.destroyBlock(pPos, true);
			}

			return new ItemStack(RisusItems.BLOOD_BUCKET.get());
		} else {
			return ItemStack.EMPTY;
		}
	}

	default Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}
}

