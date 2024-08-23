package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class FieryOrganicParticle extends BaseOrganicParticle {
	protected FieryOrganicParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
		super(pLevel, pX, pY, pZ, 8, 0.5F);
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
			FieryOrganicParticle organicParticle = new FieryOrganicParticle(pLevel, pX, pY, pZ);
			organicParticle.pickSprite(this.sprite);
			return organicParticle;
		}
	}

}
