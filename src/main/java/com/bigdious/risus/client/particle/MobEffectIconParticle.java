package com.bigdious.risus.client.particle;

import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class MobEffectIconParticle extends TextureSheetParticle {
	protected MobEffectIconParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, MobEffectInstance mobEffectInstance) {
		this(level, x, y, z, mobEffectInstance);
		this.speedUpWhenYMotionIsBlocked = true;
		this.friction = 0.92F;
		this.xd *= 0.01F;
		this.yd *= 0.01F;
		this.zd *= 0.01F;
		this.yd += 0.1;
		this.quadSize *= 2F;
		this.lifetime = 42;
		this.hasPhysics = false;
	}

	public float getQuadSize(float scaleFactor) {
		return this.quadSize * Mth.clamp(((float)this.age + scaleFactor+6) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
	}

	protected MobEffectIconParticle(ClientLevel level, double x, double y, double z, MobEffectInstance mobEffectInstance) {
		super(level, x, y, z, 0.0, 0.0, 0.0);
		var model = Minecraft.getInstance().getMobEffectTextures().get(mobEffectInstance.getEffect());
		this.setSprite(model);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return MoreParticleRenderTypes.MOBEFFECT_SHEET;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<MobEffectParticleOption> {
		public Provider() {
		}
		public Particle createParticle(MobEffectParticleOption type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed
		) {
			return new MobEffectIconParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, type.mobEffect);
		}
	}
}
