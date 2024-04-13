package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public interface SimpleMultiloggedBlock extends BucketPickup, LiquidBlockContainer {

	@Override
	default boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return state.hasProperty(MultiloggingEnum.FLUIDLOGGED) && Ref.FLUIDS.containsKey(fluid) && !fluid.equals(state.getValue(MultiloggingEnum.FLUIDLOGGED).getFluid()) && state.getValue(MultiloggingEnum.FLUIDLOGGED) == MultiloggingEnum.EMPTY;
	}

	@Override
	default boolean placeLiquid(LevelAccessor accessor, BlockPos pos, BlockState state, FluidState fluidState) {
		Fluid stateFluid = state.getValue(MultiloggingEnum.FLUIDLOGGED).getFluid();

		if (stateFluid != fluidState.getType() && Ref.FLUIDS.containsKey(fluidState.getType())) {
			if (!accessor.isClientSide()) {
				BlockState newState = state.setValue(MultiloggingEnum.FLUIDLOGGED, Ref.FLUIDS.get(fluidState.getType()));
				if (state.hasProperty(BlockStateProperties.WATERLOGGED) && fluidState.is(Fluids.WATER)) {
					newState = newState.setValue(BlockStateProperties.WATERLOGGED, true);
				}
				accessor.setBlock(pos, newState, 3);
				accessor.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(accessor));
			}
			return true;
		}
		return false;
	}

	@Override
	default ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
		Fluid stateFluid = state.getValue(MultiloggingEnum.FLUIDLOGGED).getFluid();

		if (stateFluid != Fluids.EMPTY) {
			BlockState newState = state.setValue(MultiloggingEnum.FLUIDLOGGED, MultiloggingEnum.EMPTY);
			if (state.hasProperty(BlockStateProperties.WATERLOGGED) && stateFluid == Fluids.WATER) {
				newState = newState.setValue(BlockStateProperties.WATERLOGGED, false);
			}
			level.setBlock(pos, newState, 3);
		}

		return new ItemStack(stateFluid.getBucket());
	}

	@Override
	default Optional<SoundEvent> getPickupSound() {
		return Optional.empty();
	}

	@Override
	default Optional<SoundEvent> getPickupSound(BlockState state) {
		return state.getValue(MultiloggingEnum.FLUIDLOGGED).getFluid().getPickupSound();
	}

	enum MultiloggingEnum implements StringRepresentable {
		EMPTY(() -> Blocks.AIR, () -> Fluids.EMPTY, false),
		WATER(() -> Blocks.WATER, () -> Fluids.WATER, true),
		LAVA(() -> Blocks.LAVA, () -> Fluids.LAVA, false),
		BLOOD(RisusBlocks.BLOOD_FLUID_BLOCK::get, RisusFluids.SOURCE_BLOOD::get, true);

		private final Supplier<Block> fluidBlock;
		private final Supplier<Fluid> fluid;
		private final boolean isExtinguishingFluid;

		public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = EnumProperty.create("fluid", MultiloggingEnum.class);

		MultiloggingEnum(Supplier<Block> fluidBlock, Supplier<Fluid> fluid, boolean isExtinguishingFluid) {
			this.fluidBlock = fluidBlock;
			this.fluid = fluid;
			this.isExtinguishingFluid = isExtinguishingFluid;
			Ref.FLUIDS.put(fluid.get(), this);
		}

		public static MultiloggingEnum getFromFluid(Fluid fluid) {
			return Ref.FLUIDS.getOrDefault(fluid, EMPTY);
		}

		@Override
		public String getSerializedName() {
			return this.name().toLowerCase(Locale.ROOT);
		}

		public Block getFluidBlock() {
			return this.fluidBlock.get();
		}

		public Fluid getFluid() {
			return this.fluid.get();
		}

		public boolean isExtinguishingFluid() {
			return this.isExtinguishingFluid;
		}
	}

	class Ref {
		private final static HashMap<Fluid, MultiloggingEnum> FLUIDS = new HashMap<>();
	}
}

