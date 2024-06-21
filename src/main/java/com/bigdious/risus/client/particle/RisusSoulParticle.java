package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RisusSoulParticle extends RisingParticle {
	private final SpriteSet sprites;
	protected boolean isGlowing;

	RisusSoulParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
		super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
		this.sprites = pSprites;
		this.scale(1.5F);
		this.setSpriteFromAge(pSprites);
	}

	@Override
	public int getLightColor(float pPartialTick) {
		return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		this.setSpriteFromAge(this.sprites);
	}

	@OnlyIn(Dist.CLIENT)
	public static class EmissiveProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public EmissiveProvider(SpriteSet pSprite) {
			this.sprite = pSprite;
		}

		public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed
		) {
			RisusSoulParticle risusSoulParticle = new RisusSoulParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprite);
			risusSoulParticle.setAlpha(1.0F);
			risusSoulParticle.isGlowing = true;
			return risusSoulParticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public Provider(SpriteSet pSprites) {
			this.sprite = pSprites;
		}

		public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed
		) {
			RisusSoulParticle risusSoulParticle = new RisusSoulParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprite);
			risusSoulParticle.setAlpha(1.0F);
			return risusSoulParticle;
		}
	}
}
