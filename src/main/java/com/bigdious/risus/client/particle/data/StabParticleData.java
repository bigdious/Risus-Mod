package com.bigdious.risus.client.particle.data;

import com.bigdious.risus.init.RisusParticles;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import javax.annotation.Nonnull;

public record StabParticleData(float y_rot) implements ParticleOptions {
	public static MapCodec<StabParticleData> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		Codec.FLOAT.fieldOf("y_rot").forGetter((obj) -> obj.y_rot)
	).apply(instance, StabParticleData::new));

	public static StreamCodec<? super RegistryFriendlyByteBuf, StabParticleData> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.FLOAT, p -> p.y_rot,
		StabParticleData::new
	);

	@Nonnull
	@Override
	public ParticleType<?> getType() {
		return RisusParticles.STAB.get();
	}
}
