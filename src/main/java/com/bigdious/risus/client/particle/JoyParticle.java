package com.bigdious.risus.client.particle;

import com.bigdious.risus.init.RisusParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

public class JoyParticle extends DripParticle {

	private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
	private static final Field particle_Gravity = ObfuscationReflectionHelper.findField(Particle.class, "f_107226_");
	private static final MethodHandle handle_particle_Gravity_set;
	private static final MethodHandle handle_particle_Gravity_get;

	static {
		MethodHandle tmp_handle_particle_Gravity_set = null;
		MethodHandle tmp_handle_particle_Gravity_get = null;
		try {
			tmp_handle_particle_Gravity_set = LOOKUP.unreflectSetter(particle_Gravity);
			tmp_handle_particle_Gravity_get = LOOKUP.unreflectGetter(particle_Gravity);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		handle_particle_Gravity_set = tmp_handle_particle_Gravity_set;
		handle_particle_Gravity_get = tmp_handle_particle_Gravity_get;
	}

	public JoyParticle(ClientLevel p_106051_, double p_106052_, double p_106053_, double p_106054_, Fluid p_106055_) {
		super(p_106051_, p_106052_, p_106053_, p_106054_, p_106055_);
	}

	@Override
	public int getLightColor(float p_106065_) {
		return 240;
	}

	public static class JoyFallProvider implements ParticleProvider<SimpleParticleType> {
		protected final SpriteSet sprite;

		public JoyFallProvider(SpriteSet p_106310_) {
			this.sprite = p_106310_;
		}

		public Particle createParticle(SimpleParticleType type, ClientLevel p_106322_, double p_106323_, double p_106324_, double p_106325_, double p_106326_, double p_106327_, double p_106328_) {
			DripParticle dripparticle = new DripParticle.FallAndLandParticle(p_106322_, p_106323_, p_106324_, p_106325_, Fluids.EMPTY, RisusParticles.LANDING_JOY.get());
			try {
				handle_particle_Gravity_set.invokeExact((Particle)dripparticle, 0.01F);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.pickSprite(this.sprite);
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class JoyHangProvider implements ParticleProvider<SimpleParticleType> {
		protected final SpriteSet sprite;

		public JoyHangProvider(SpriteSet p_106331_) {
			this.sprite = p_106331_;
		}

		public Particle createParticle(SimpleParticleType p_106342_, ClientLevel p_106343_, double p_106344_, double p_106345_, double p_106346_, double p_106347_, double p_106348_, double p_106349_) {
			DripParticle dripparticle = new DripParticle.DripHangParticle(p_106343_, p_106344_, p_106345_, p_106346_, Fluids.EMPTY, RisusParticles.FALLING_JOY.get());
			try {
				handle_particle_Gravity_set.invokeExact((Particle)dripparticle, (float)handle_particle_Gravity_get.invokeExact((Particle)dripparticle) * 0.01F);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
			dripparticle.setLifetime(100);
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.pickSprite(this.sprite);
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class JoyLandProvider implements ParticleProvider<SimpleParticleType> {
		protected final SpriteSet sprite;

		public JoyLandProvider(SpriteSet p_106352_) {
			this.sprite = p_106352_;
		}

		public Particle createParticle(SimpleParticleType p_106363_, ClientLevel p_106364_, double p_106365_, double p_106366_, double p_106367_, double p_106368_, double p_106369_, double p_106370_) {
			DripParticle dripparticle = new DripParticle.DripLandParticle(p_106364_, p_106365_, p_106366_, p_106367_, Fluids.EMPTY);
			dripparticle.setLifetime((int)(28.0D / (Math.random() * 0.8D + 0.2D)));
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.pickSprite(this.sprite);
			return dripparticle;
		}
	}
}
