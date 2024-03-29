package com.bigdious.risus.fluid;

import com.bigdious.risus.Risus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class RisusFluidTypes {
	public static final ResourceLocation BLOOD_STILL_RL = new ResourceLocation(Risus.MODID, "block/blood_still");
	public static final ResourceLocation BLOOD_FLOWING_RL = new ResourceLocation(Risus.MODID,"block/blood_flow");
	public static final ResourceLocation BLOOD_OVERLAY_RL = new ResourceLocation(Risus.MODID, "block/blood_flow");
	public static final ResourceLocation BLOOD_OVERLAY_RENDER = new ResourceLocation(Risus.MODID, "textures/misc/blood_fluid_block");

	public static final DeferredRegister<FluidType> FLUID_TYPES =
		DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Risus.MODID);

	public static final RegistryObject<FluidType> BLOOD_FLUID_TYPE = register("blood_fluid",
		FluidType.Properties.create().canHydrate(true).canDrown(false).canSwim(true).density(10).viscosity(15).canPushEntity(true).sound(SoundAction.get("drink"),
			SoundEvents.HONEY_DRINK));
	private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){
		return FLUID_TYPES.register(name, () -> new RisusBaseFluidType(BLOOD_STILL_RL, BLOOD_FLOWING_RL, BLOOD_OVERLAY_RL,BLOOD_OVERLAY_RENDER,  0xffe60e07, new Vector3f(54f / 255f, 4f / 255f, 4f / 255f), properties));
	}


	public static void register(IEventBus eventBus){
		FLUID_TYPES.register(eventBus);
	}
}
