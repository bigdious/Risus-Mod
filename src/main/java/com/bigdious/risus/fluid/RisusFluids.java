package com.bigdious.risus.fluid;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.fluid.BloodFluid;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusFluids {
	public static final DeferredRegister<Fluid> FLUIDS =
		DeferredRegister.create(ForgeRegistries.FLUIDS, Risus.MODID);
	public static final RegistryObject<FlowingFluid> SOURCE_BLOOD = FLUIDS.register("blood_fluid",
		() -> new BloodFluid.Source(RisusFluids.BLOOD_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood_fluid",
		() -> new BloodFluid.Flowing(RisusFluids.BLOOD_FLUID_PROPERTIES));


	public static final	 ForgeFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
		RisusFluidTypes.BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD).
		slopeFindDistance(2).levelDecreasePerBlock(2).block(RisusBlocks.BLOOD_FLUID_BLOCK).bucket(RisusItems.BLOOD_BUCKET);

	//thanks to Jann for help with this agony
	public static void registerFluidInteractions() {
		FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
			SOURCE_BLOOD.get().getFluidType(),
			fluidState -> {
				if (!fluidState.isSource()) {
					return RisusBlocks.SCAB.get().defaultBlockState();
				} else {
					return RisusBlocks.LAUGHING_OBSIDIAN.get().defaultBlockState();
				}
			}
		));
		FluidInteractionRegistry.addInteraction(ForgeMod.WATER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
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
		FLUIDS.register(eventBus);
	}
}
