package com.bigdious.risus.client.particles;

import com.bigdious.risus.Risus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Risus.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RisusParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Risus.ID);

	public static final RegistryObject<BasicParticleType> DRIPPING_JOY = PARTICLES.register("dripping_joy", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> FALLING_JOY = PARTICLES.register("falling_joy", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> LANDING_JOY = PARTICLES.register("landing_joy", () -> new BasicParticleType(false));

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerFactories(ParticleFactoryRegisterEvent event) {
		ParticleManager particles = Minecraft.getInstance().particles;

		particles.registerFactory(DRIPPING_JOY.get(), JoyParticle.DrippingJoyFactory::new);
		particles.registerFactory(FALLING_JOY.get(), JoyParticle.FallingJoyFactory::new);
		particles.registerFactory(LANDING_JOY.get(), JoyParticle.LandingJoyFactory::new);
	}
}
