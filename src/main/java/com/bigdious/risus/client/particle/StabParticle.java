package com.bigdious.risus.client.particle;

import com.bigdious.risus.client.particle.data.StabParticleData;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class StabParticle extends TextureSheetParticle {
	private final SpriteSet sprites;
	private float yRoll;

	protected StabParticle(ClientLevel level, double x, double y, double z, double quadSizeMultiplier, SpriteSet sprites) {
		super(level, x, y, z, 0.0, 0.0, 0.0);
		this.sprites = sprites;
		this.lifetime = 4;
		float f = this.random.nextFloat() * 0.6F + 0.4F;
		this.rCol = f;
		this.gCol = f;
		this.bCol = f;
		this.quadSize = 1F;
		this.yRoll = 0;
		this.setSpriteFromAge(sprites);
	}

	public int getLightColor(float partialTick) {
		return 15728880;
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			this.setSpriteFromAge(this.sprites);
		}

	}

	@Override
	public void render(VertexConsumer buffer, Camera entity, float partialTicks) {
		Quaternionf quaternion = new Quaternionf();
		quaternion.rotateZ(this.oRoll);
		quaternion.rotateY( -((this.yRoll) * (float)(Math.PI / 180)));
		this.renderRotatedQuad(buffer, entity, quaternion, partialTicks);
		quaternion.rotateY(-Mth.PI).rotateZ(Mth.PI);
		this.renderRotatedQuad(buffer, entity, quaternion, partialTicks);
	}


	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	public record Factory(SpriteSet sprites) implements ParticleProvider<StabParticleData> {

		@Override
		public Particle createParticle(StabParticleData data, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			StabParticle particle = new StabParticle(level, x, y, z, xSpeed, sprites);
			particle.yRoll = data.y_rot();
			particle.pickSprite(this.sprites);
			return particle;
		}
	}
}

