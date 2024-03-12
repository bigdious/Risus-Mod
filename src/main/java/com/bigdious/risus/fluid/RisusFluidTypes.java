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
	public static final ResourceLocation BLOOD_STILL_RL = new ResourceLocation("block/blood_still");
	public static final ResourceLocation BLOOD_FLOWING_RL = new ResourceLocation("block/blood_flow");
	public static final ResourceLocation BLOOD_OVERLAY_RL = new ResourceLocation(Risus.MODID, "misc/in_blood");

	public static final DeferredRegister<FluidType> FLUID_TYPES =
		DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Risus.MODID);

	public static final RegistryObject<FluidType> BLOOD_FLUID_TYPE = register("blood_fluid",
		FluidType.Properties.create().canHydrate(true).canDrown(false).canSwim(true).density(30).viscosity(5).canPushEntity(true).sound(SoundAction.get("drink"),
			SoundEvents.HONEY_DRINK));
	private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){
		return FLUID_TYPES.register(name, () -> new RisusBaseFluidType(BLOOD_STILL_RL, BLOOD_FLOWING_RL, BLOOD_OVERLAY_RL, 0x9B0202, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
	}


	public static void register(IEventBus eventBus){
		FLUID_TYPES.register(eventBus);
	}
}
