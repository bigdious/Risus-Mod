package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.fluid.BloodFluid;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class RisusFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Risus.MODID);
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Risus.MODID);

	public static final DeferredHolder<FluidType, FluidType> BLOOD_FLUID_TYPE = FLUID_TYPES.register("blood_fluid", () -> new FluidType(FluidType.Properties.create()
		.canHydrate(true)
		.canDrown(false)
		.canSwim(true)
		.density(10)
		.viscosity(15)
		.canPushEntity(true)) {

	});

	public static final DeferredHolder<Fluid, BloodFluid.Source> SOURCE_BLOOD = FLUIDS.register("blood_fluid", () -> new BloodFluid.Source(RisusFluids.BLOOD_FLUID_PROPERTIES));
	public static final DeferredHolder<Fluid, BloodFluid.Flowing> FLOWING_BLOOD = FLUIDS.register("flowing_blood_fluid", () -> new BloodFluid.Flowing(RisusFluids.BLOOD_FLUID_PROPERTIES));

	public static final BaseFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD).levelDecreasePerBlock(2).block(RisusBlocks.BLOOD_FLUID_BLOCK).bucket(RisusItems.BLOOD_BUCKET);
}
