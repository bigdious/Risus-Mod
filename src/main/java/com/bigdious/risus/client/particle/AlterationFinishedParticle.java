package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class AlterationFinishedParticle extends TextureSheetParticle {
	public AlterationFinishedParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(level, x, y, z);
		this.setColor(0.5F, 0.0F, 0.0F);
		this.scale(0.55F);
		this.gravity = 3.0E-6F;
		this.xd = xSpeed;
		this.yd = ySpeed + (double)(this.random.nextFloat() / 500.0F);
		this.zd = zSpeed;
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
			this.xd += this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1);
			this.zd += this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1);
			this.yd -= this.gravity;
			this.move(this.xd, this.yd, this.zd);

		} else {
			this.remove();
		}
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public static record Provider(SpriteSet set) implements ParticleProvider<SimpleParticleType> {
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			AlterationFinishedParticle particle = new AlterationFinishedParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
			particle.pickSprite(this.set);
			return particle;
		}
	}
}
