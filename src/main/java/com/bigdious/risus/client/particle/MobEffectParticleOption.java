package com.bigdious.risus.client.particle;

import com.bigdious.risus.init.RisusParticles;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;

public class MobEffectParticleOption implements ParticleOptions {
	public static final Codec<MobEffectInstance> MOB_EFFECT_CODEC;
	public final ParticleType<MobEffectParticleOption> type;
	public final MobEffectInstance mobEffect;

	public static MapCodec<MobEffectParticleOption> codec(ParticleType<MobEffectParticleOption> particleType) {
		return MOB_EFFECT_CODEC.xmap((mobEffect) -> new MobEffectParticleOption(particleType, mobEffect), (mobEffectParticleOption) -> mobEffectParticleOption.mobEffect).fieldOf("mob_effect");
	}

	public static StreamCodec<? super RegistryFriendlyByteBuf, MobEffectParticleOption> streamCodec(ParticleType<MobEffectParticleOption> particleType) {
		return MobEffectInstance.STREAM_CODEC.map((mobEffect) -> new MobEffectParticleOption(particleType,  mobEffect), (mobEffectParticleOption) -> mobEffectParticleOption.mobEffect);
	}

	public MobEffectParticleOption(ParticleType<MobEffectParticleOption> type, MobEffectInstance mobEffect) {
		if (mobEffect.getEffect() == null) {
			throw new IllegalArgumentException("Empty stacks are not allowed");
		} else {
			this.type = type;
			this.mobEffect = mobEffect;
		}
	}

	public ParticleType<MobEffectParticleOption> getType() {
		return RisusParticles.MOB_EFFECT_ICON.get();
	}

	public MobEffectInstance getEffect() {
		return this.mobEffect;
	}

	static {
		MOB_EFFECT_CODEC = MobEffectInstance.CODEC;
	}
}
