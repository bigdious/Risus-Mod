package com.bigdious.risus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

public class BaseOrganicParticle extends TextureSheetParticle {
	protected BaseOrganicParticle(
		ClientLevel pLevel,
		double pX,
		double pY,
		double pZ,
		float life,
		float friction,
		SpriteSet spriteSet
	) {
		super(pLevel, pX, pY, pZ, 0.0, 0.0, 0.0);
		this.speedUpWhenYMotionIsBlocked = true;
		this.friction = friction;
		this.xd *= 0.01F;
		this.yd *= 0.01F;
		this.zd *= 0.01F;
		this.yd += 0.01;
		this.quadSize *= 0.35F;
		this.lifetime = (int)((double)life / ((double)pLevel.random.nextFloat() * 0.8 + 0.1));
		this.lifetime = Math.max(this.lifetime, 1);
		this.hasPhysics = false;
		this.gravity = -1;
	}
	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
}

