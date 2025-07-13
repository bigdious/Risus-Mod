package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.fluid.BloodFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class RisusFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Risus.MODID);
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Risus.MODID);

	public static final DeferredHolder<FluidType, FluidType> BLOOD_FLUID_TYPE = FLUID_TYPES.register("blood_fluid", () -> new FluidType(FluidType.Properties.create()
		.canHydrate(false)
		.motionScale(0.00116666666)
		.canDrown(false)
		.canSwim(true)
		.supportsBoating(true)
		.canConvertToSource(true)
		.canPushEntity(true)
		.density(1500)
		.viscosity(2000)));

	public static final DeferredHolder<Fluid, BloodFluid.Source> SOURCE_BLOOD = FLUIDS.register("blood_fluid", () -> new BloodFluid.Source(RisusFluids.BLOOD_FLUID_PROPERTIES));
	public static final DeferredHolder<Fluid, BloodFluid.Flowing> FLOWING_BLOOD = FLUIDS.register("flowing_blood_fluid", () -> new BloodFluid.Flowing(RisusFluids.BLOOD_FLUID_PROPERTIES));

	public static final BaseFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD).levelDecreasePerBlock(2).block(RisusBlocks.BLOOD_FLUID_BLOCK).bucket(RisusItems.BLOOD_BUCKET);
}
