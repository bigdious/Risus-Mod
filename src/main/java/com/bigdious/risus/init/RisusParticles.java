package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusParticles {

	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Risus.MODID);

	public static final RegistryObject<SimpleParticleType> DRIPPING_JOY = PARTICLES.register("dripping_joy", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FALLING_JOY = PARTICLES.register("falling_joy", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LANDING_JOY = PARTICLES.register("landing_joy", () -> new SimpleParticleType(false));

	public static final RegistryObject<ParticleType<AlterationParticleOptions>> ALTERATION = PARTICLES.register("alteration", () -> new ParticleType<>(false, AlterationParticleOptions.DESERIALIZER) {
		@Override
		public Codec<AlterationParticleOptions> codec() {
			return AlterationParticleOptions.CODEC;
		}
	});
	public static final RegistryObject<SimpleParticleType> ALTERATION_FINISHED = PARTICLES.register("alteration_finished", () -> new SimpleParticleType(false));

}
