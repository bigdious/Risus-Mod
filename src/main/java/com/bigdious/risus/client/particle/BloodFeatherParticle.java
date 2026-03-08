package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BloodFeatherParticle extends TextureSheetParticle {
	protected BloodFeatherParticle(ClientLevel level, double x, double y, double z) {
		super(level, x, y, z, 0.0F, 0.0F, 0.0F);
		this.speedUpWhenYMotionIsBlocked = false;
		this.friction = 0F;
		this.quadSize = 0.1F;
		this.lifetime = level.getRandom().nextIntBetweenInclusive(80, 120);
		this.hasPhysics = true;
	}

	@Override
	public void tick() {
		this.xd = 0.03F - 0.001*this.age;
		this.zd = 0.03F - 0.001*this.age;
		this.yd = -0.02;
		super.tick();

	}


	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public Provider(SpriteSet pSprites) {
			this.sprite = pSprites;
		}

		public Particle createParticle(
			SimpleParticleType pType,
			ClientLevel pLevel,
			double pX,
			double pY,
			double pZ,
			double pXSpeed,
			double pYSpeed,
			double pZSpeed
		) {
			BloodFeatherParticle bloodFeatherParticle = new BloodFeatherParticle(pLevel, pX, pY, pZ);
			bloodFeatherParticle.pickSprite(this.sprite);
			return bloodFeatherParticle;
		}
	}
	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
}
