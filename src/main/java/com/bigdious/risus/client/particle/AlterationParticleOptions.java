package com.bigdious.risus.client.particle;

import com.bigdious.risus.init.RisusParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class AlterationParticleOptions extends ScalableParticleOptionsBase {


    //deserializer broke
	public static final MapCodec<AlterationParticleOptions> CODEC = RecordCodecBuilder.mapCodec(
		p_341564_ -> p_341564_.group(
				ExtraCodecs.VECTOR3F.fieldOf("from_color").forGetter(p_341563_ -> p_341563_.fromColor),
				ExtraCodecs.VECTOR3F.fieldOf("to_color").forGetter(p_253367_ -> p_253367_.toColor),
				SCALE.fieldOf("scale").forGetter(ScalableParticleOptionsBase::getScale)
			)
			.apply(p_341564_, AlterationParticleOptions::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, AlterationParticleOptions> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.VECTOR3F,
		p_341565_ -> p_341565_.fromColor,
		ByteBufCodecs.VECTOR3F,
		p_319428_ -> p_319428_.toColor,
		ByteBufCodecs.FLOAT,
		ScalableParticleOptionsBase::getScale,
		AlterationParticleOptions::new
	);
	private final Vector3f fromColor;
	private final Vector3f toColor;


	public static final AlterationParticleOptions ALTERATION_FADE = new AlterationParticleOptions(Vec3.fromRGB24(9868693).toVector3f(), Vec3.fromRGB24(11012618).toVector3f(), 1.0F);

	public AlterationParticleOptions(Vector3f p_254199_, Vector3f p_254529_, float p_254178_) {
		super(p_254178_);
		this.fromColor = p_254199_;
		this.toColor = p_254529_;
	}
	public Vector3f getFromColor() {
		return this.fromColor;
	}

	public Vector3f getToColor() {
		return this.toColor;
	}


	@Override
	public ParticleType<?> getType() {
		return RisusParticles.ALTERATION.get();
	}
}
