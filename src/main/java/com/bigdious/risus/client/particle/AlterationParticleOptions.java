package com.bigdious.risus.client.particle;

import com.bigdious.risus.init.RisusParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class AlterationParticleOptions extends DustParticleOptionsBase {

	public static final Codec<AlterationParticleOptions> CODEC = RecordCodecBuilder.create((p_175763_) ->
			p_175763_.group(
							ExtraCodecs.VECTOR3F.fieldOf("fromColor").forGetter((p_175773_) -> p_175773_.color),
							ExtraCodecs.VECTOR3F.fieldOf("toColor").forGetter((p_175770_) -> p_175770_.toColor),
							Codec.FLOAT.fieldOf("scale").forGetter((p_175765_) -> p_175765_.scale))
					.apply(p_175763_, AlterationParticleOptions::new));
    //deserializer broke
	public static final ParticleOptions.Deserializer<AlterationParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
		public AlterationParticleOptions fromCommand(ParticleType<AlterationParticleOptions> p_175777_, StringReader p_175778_) throws CommandSyntaxException {
			Vector3f vector3f = DustParticleOptionsBase.readVector3f(p_175778_);
			p_175778_.expect(' ');
			float f = p_175778_.readFloat();
			Vector3f vector3f1 = DustParticleOptionsBase.readVector3f(p_175778_);
			return new AlterationParticleOptions(vector3f, vector3f1, f);
		}

		public AlterationParticleOptions fromNetwork(ParticleType<AlterationParticleOptions> p_175780_, FriendlyByteBuf p_175781_) {
			Vector3f vector3f = DustParticleOptionsBase.readVector3f(p_175781_);
			float f = p_175781_.readFloat();
			Vector3f vector3f1 = DustParticleOptionsBase.readVector3f(p_175781_);
			return new AlterationParticleOptions(vector3f, vector3f1, f);
		}
	};

	public static final AlterationParticleOptions ALTERATION_FADE = new AlterationParticleOptions(Vec3.fromRGB24(9868693).toVector3f(), Vec3.fromRGB24(11012618).toVector3f(), 1.0F);
	public static final AlterationParticleOptions LETTERS_APPEAR = new AlterationParticleOptions(Vec3.fromRGB24(526343).toVector3f(), Vec3.fromRGB24(11012618).toVector3f(), 1.0F);
	private final Vector3f toColor;

	public AlterationParticleOptions(Vector3f fromColor, Vector3f toColor, float scale) {
		super(fromColor, scale);
		this.toColor = toColor;
	}

	public Vector3f getFromColor() {
		return this.color;
	}

	public Vector3f getToColor() {
		return this.toColor;
	}

	public void writeToNetwork(FriendlyByteBuf buffer) {
		super.writeToNetwork(buffer);
		buffer.writeFloat(this.toColor.x());
		buffer.writeFloat(this.toColor.y());
		buffer.writeFloat(this.toColor.z());
	}

	@Override
	public ParticleType<?> getType() {
		return RisusParticles.ALTERATION.get();
	}
}
