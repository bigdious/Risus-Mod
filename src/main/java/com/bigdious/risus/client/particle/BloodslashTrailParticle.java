package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BloodslashTrailParticle extends TextureSheetParticle {
	private final SpriteSet sprites;
	protected boolean isGlowing;

	BloodslashTrailParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
		super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
		this.sprites = pSprites;
		this.scale(1F);
		this.setSpriteFromAge(pSprites);
		this.lifetime = 6;
		this.friction = 0.96F;
		this.xd = this.xd * 0.009999999776482582 + pXSpeed;
		this.yd = this.yd * 0.009999999776482582 + pYSpeed;
		this.zd = this.zd * 0.009999999776482582 + pZSpeed;
		this.x += (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.05F);
		this.y += (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.05F);
		this.z += (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.05F);
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
		this.setSize(1-this.age*0.1F, 1-this.age*0.1F);
	}

	@OnlyIn(Dist.CLIENT)
	public static class EmissiveProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public EmissiveProvider(SpriteSet pSprite) {
			this.sprite = pSprite;
		}

		public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed
		) {
			BloodslashTrailParticle trail = new BloodslashTrailParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprite);
			trail.setAlpha(1.0F);
			trail.isGlowing = true;
			return trail;
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
			BloodslashTrailParticle trail = new BloodslashTrailParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprite);
			trail.setAlpha(1.0F);
			return trail;
		}
	}
}
