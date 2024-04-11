package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusParticles {

	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Risus.MODID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_JOY = PARTICLES.register("dripping_joy", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_JOY = PARTICLES.register("falling_joy", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_JOY = PARTICLES.register("landing_joy", () -> new SimpleParticleType(false));

	public static final DeferredHolder<ParticleType<?>, ParticleType<AlterationParticleOptions>> ALTERATION = PARTICLES.register("alteration", () -> new ParticleType<>(false, AlterationParticleOptions.DESERIALIZER) {
		@Override
		public Codec<AlterationParticleOptions> codec() {
			return AlterationParticleOptions.CODEC;
		}
	});
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ALTERATION_FINISHED = PARTICLES.register("alteration_finished", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TOOTHICAL = PARTICLES.register("toothical", () -> new SimpleParticleType(false));

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> JOYFLAME = PARTICLES.register("joyflame", () -> new SimpleParticleType(false));

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD = PARTICLES.register("blood_particle", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD_BIT = PARTICLES.register("blood_bit_particle", () -> new SimpleParticleType(true));
}
