package com.bigdious.risus.fluid;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.joml.Vector3f;

public class RisusFluidTypes {
	public static final ResourceLocation BLOOD_STILL_RL = new ResourceLocation(Risus.MODID, "block/blood_still");
	public static final ResourceLocation BLOOD_FLOWING_RL = new ResourceLocation(Risus.MODID,"block/blood_flow");
	public static final ResourceLocation BLOOD_OVERLAY_RL = new ResourceLocation(Risus.MODID, "block/blood_flow");
	public static final ResourceLocation BLOOD_OVERLAY_RENDER = new ResourceLocation(Risus.MODID, "textures/misc/blood_fluid_block");

	public static final DeferredRegister<FluidType> FLUID_TYPES =
		DeferredRegister.create(BuiltInRegistries.FLUID.getDefaultKey(), Risus.MODID);

	public static final DeferredHolder<FluidType, FluidType> BLOOD_FLUID_TYPE = register("blood_fluid",
		FluidType.Properties.create().canHydrate(true).canDrown(false).canSwim(true).density(10).viscosity(15).canPushEntity(true).sound(SoundAction.get("drink"),
			SoundEvents.HONEY_DRINK));
	private static DeferredHolder<FluidType, FluidType> register(String name, FluidType.Properties properties){
		return FLUID_TYPES.register(name, () -> new RisusBaseFluidType(BLOOD_STILL_RL, BLOOD_FLOWING_RL, BLOOD_OVERLAY_RL,BLOOD_OVERLAY_RENDER,  0xffe60e07, new Vector3f(54f / 255f, 4f / 255f, 4f / 255f), properties));
	}


	public static void register(IEventBus eventBus){
		FLUID_TYPES.register(eventBus);
	}
}
