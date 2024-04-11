package com.bigdious.risus.fluid;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.fluid.BloodFluid;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusFluids {
	public static final DeferredRegister<Fluid> FLUID =
		DeferredRegister.create(Registries.FLUID, Risus.MODID);
	public static final DeferredHolder<FlowingFluid, FlowingFluid> SOURCE_BLOOD = FLUID.register("blood_fluid",
		() -> new BloodFluid.Source(RisusFluids.BLOOD_FLUID_PROPERTIES));
	public static final DeferredHolder<FlowingFluid, FlowingFluid> FLOWING_BLOOD = FLUID.register("flowing_blood_fluid",
		() -> new BloodFluid.Flowing(RisusFluids.BLOOD_FLUID_PROPERTIES));


	public static final	 FlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new FlowingFluid.Properties(
		RisusFluidTypes.BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD).levelDecreasePerBlock(2).block(RisusBlocks.BLOOD_FLUID_BLOCK).bucket(RisusItems.BLOOD_BUCKET);

	//thanks to Jann for help with this agony
	public static void registerFluidInteractions() {
		FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
			SOURCE_BLOOD.get().getFluidType(),
			fluidState -> {
				if (!fluidState.isSource()) {
					return RisusBlocks.SCAB.get().defaultBlockState();
				} else {
					return RisusBlocks.LAUGHING_OBSIDIAN.get().defaultBlockState();
				}
			}
		));
		FluidInteractionRegistry.addInteraction(NeoForgeMod.WATER_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
			SOURCE_BLOOD.get().getFluidType(),
			fluidState -> {
				if (fluidState.isSource()) {
					return RisusBlocks.COAGULATED_BLOOD_BLOCK.get().defaultBlockState();
				} else {
					return RisusBlocks.COAGULATED_BLOOD_BLOCK.get().defaultBlockState();
				}
			}
		));
	}


	public static void register(IEventBus eventBus) {
		FLUID.register(eventBus);
	}
}
