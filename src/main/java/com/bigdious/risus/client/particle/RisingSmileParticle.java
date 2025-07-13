package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class RisingSmileParticle extends TextureSheetParticle {
	protected RisingSmileParticle(ClientLevel level, double x, double y, double z) {
		super(level, x, y, z, (double)0.0F, (double)0.0F, (double)0.0F);
		this.speedUpWhenYMotionIsBlocked = true;
		this.friction = 0.92F;
		this.xd *= (double)0.01F;
		this.yd *= (double)0.01F;
		this.zd *= (double)0.01F;
		this.yd += 0.1;
		this.quadSize *= 1.5F;
		this.lifetime = 42;
		this.hasPhysics = false;
	}

	public float getQuadSize(float scaleFactor) {
		return this.quadSize * Mth.clamp(((float)this.age + scaleFactor+6) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
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
			RisingSmileParticle risingParticle = new RisingSmileParticle(pLevel, pX, pY, pZ);
			risingParticle.pickSprite(this.sprite);
			return risingParticle;
		}
	}
	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
}
