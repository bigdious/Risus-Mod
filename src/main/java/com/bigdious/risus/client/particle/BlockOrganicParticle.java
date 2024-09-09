package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BlockOrganicParticle extends BaseOrganicParticle {
	protected BlockOrganicParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet) {
		super(pLevel, pX, pY, pZ, 8, 0F, spriteSet);
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
			BlockOrganicParticle blockOrganicParticle = new BlockOrganicParticle(pLevel, pX, pY, pZ, this.sprite);
			blockOrganicParticle.pickSprite(this.sprite);
			return blockOrganicParticle;
		}
	}

}

