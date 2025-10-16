package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SleepyParticle extends TextureSheetParticle {
	protected SleepyParticle(ClientLevel level, double x, double y, double z) {
		super(level, x, y, z, (double)0.0F, (double)0.0F, (double)0.0F);
		this.speedUpWhenYMotionIsBlocked = true;
		this.friction = 1F;
		this.xd = 0.02F;
		this.zd *= (double)0.01F;
		this.yd = 0.01F;
		this.quadSize = 0.07F;
		this.lifetime = 60;
		this.hasPhysics = false;
	}

	@Override
	public float getQuadSize(float scaleFactor) {
		float f = ((float)this.age + scaleFactor)*1.5F / (float)this.lifetime;
		return this.quadSize * (1.0F + f * f);
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
			SleepyParticle sleepyParticle = new SleepyParticle(pLevel, pX, pY, pZ);
			sleepyParticle.pickSprite(this.sprite);
			return sleepyParticle;
		}
	}
	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
}
