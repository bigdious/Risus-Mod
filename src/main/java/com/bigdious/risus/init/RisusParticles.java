package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.bigdious.risus.client.particle.MobEffectParticleOption;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusParticles {

	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Risus.MODID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_JOY = PARTICLES.register("dripping_joy", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_JOY = PARTICLES.register("falling_joy", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_JOY = PARTICLES.register("landing_joy", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_BLOOD = PARTICLES.register("dripping_blood", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_BLOOD = PARTICLES.register("falling_blood", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_BLOOD = PARTICLES.register("landing_blood", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_CREAM = PARTICLES.register("dripping_cream", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_CREAM = PARTICLES.register("falling_cream", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_CREAM = PARTICLES.register("landing_cream", () -> new SimpleParticleType(false));


	public static final DeferredHolder<ParticleType<?>, ParticleType<AlterationParticleOptions>> ALTERATION = PARTICLES.register("alteration", () -> new ParticleType<>(false) {
		@Override
		public MapCodec<AlterationParticleOptions> codec() {
			return AlterationParticleOptions.CODEC;
		}

		@Override
		public StreamCodec<? super RegistryFriendlyByteBuf, AlterationParticleOptions> streamCodec() {
			return AlterationParticleOptions.STREAM_CODEC;
		}
	});

	public static final DeferredHolder<ParticleType<?>, ParticleType<MobEffectParticleOption>> MOB_EFFECT_ICON = PARTICLES.register("mob_effect_icon", () -> new ParticleType<>(false) {

		@Override
		public MapCodec<MobEffectParticleOption> codec() {
			return MobEffectParticleOption.codec(RisusParticles.MOB_EFFECT_ICON.get());
		}

		@Override
		public StreamCodec<? super RegistryFriendlyByteBuf, MobEffectParticleOption> streamCodec() {
			return MobEffectParticleOption.streamCodec(RisusParticles.MOB_EFFECT_ICON.get());
		}
	});

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ALTERATION_FINISHED = PARTICLES.register("alteration_finished", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TOOTHICAL = PARTICLES.register("toothical", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RISUS_SOUL_PARTICLE = PARTICLES.register("risus_soul", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOODSLASH_TRAIL = PARTICLES.register("bloodslash_trail", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FIERY_ORGANIC_PARTICLE = PARTICLES.register("fiery_organic", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOCK_ORGANIC_PARTICLE = PARTICLES.register("block_organic", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> JOYFLAME = PARTICLES.register("joyflame", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD = PARTICLES.register("blood_particle", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD_BIT = PARTICLES.register("blood_bit_particle", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DESTINED_DEATH_PARTICLE = PARTICLES.register("destined_death_particle", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RISING_SMILE = PARTICLES.register("rising_smile", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SLEEPY = PARTICLES.register("sleepy", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> STARS = PARTICLES.register("stars", () -> new SimpleParticleType(true));
}
