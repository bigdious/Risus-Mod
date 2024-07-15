package com.bigdious.risus.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import org.joml.Vector3f;

public class AlterationParticle extends DustParticleBase<AlterationParticleOptions> {
	private final Vector3f fromColor;
	private final Vector3f toColor;

	private final double xStart;
	private final double yStart;
	private final double zStart;

	protected AlterationParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, AlterationParticleOptions options, SpriteSet spriteSet) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed, options, spriteSet);
		this.fromColor = options.getFromColor();
		this.toColor = options.getToColor();
		this.xd = xSpeed;
		this.yd = ySpeed;
		this.zd = zSpeed;
		this.xStart = x;
		this.yStart = y;
		this.zStart = z;
		this.xo = x + xSpeed;
		this.yo = y + ySpeed;
		this.zo = z + zSpeed;
		this.x = this.xo;
		this.y = this.yo;
		this.z = this.zo;
		this.quadSize = 0.125F * (this.random.nextFloat() * 0.5F + 0.2F);
		float f = this.random.nextFloat() * 0.6F + 0.4F;
		this.rCol = fromColor.x();
		this.gCol = fromColor.y();
		this.bCol = fromColor.z();
		this.hasPhysics = false;
		this.lifetime = (int) (Math.random() * 10.0D) + 30;
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			float f = (float) this.age / (float) this.lifetime;
			f = 1.0F - f;
			float f1 = 1.0F - f;
			f1 *= f1;
			f1 *= f1;
			this.x = this.xStart + this.xd * (double) f;
			this.y = this.yStart + this.yd * (double) f - (double) (f1 * 1.2F);
			this.z = this.zStart + this.zd * (double) f;
			this.setPos(this.x, this.y, this.z);
		}
	}

	private void lerpColors(float partialTicks) {
		float f = ((float) this.age + partialTicks) / ((float) this.lifetime + 1.0F);
		Vector3f vector3f = new Vector3f(this.fromColor);
		vector3f.lerp(this.toColor, f);
		this.rCol = vector3f.x();
		this.gCol = vector3f.y();
		this.bCol = vector3f.z();
	}

	@Override
	public int getLightColor(float p_106486_) {
		int i = super.getLightColor(p_106486_);
		float f = (float) this.age / (float) this.lifetime;
		f *= f;
		f *= f;
		int j = i & 255;
		int k = i >> 16 & 255;
		k += (int) (f * 15.0F * 16.0F);
		if (k > 240) {
			k = 240;
		}

		return j | k << 16;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void render(VertexConsumer consumer, Camera camera, float partialTicks) {
		this.lerpColors(partialTicks);
		super.render(consumer, camera, partialTicks);
	}

	public record Provider(SpriteSet sprites) implements ParticleProvider<AlterationParticleOptions> {

		public Particle createParticle(AlterationParticleOptions options, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			AlterationParticle particle = new AlterationParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, options, this.sprites);
			particle.pickSprite(sprites);
			return particle;
		}
	}
}
