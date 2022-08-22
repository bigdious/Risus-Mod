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
	private static final Field particle_Gravity = ObfuscationReflectionHelper.findField(Particle.class, "f_107226_"); //gravity
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

	public JoyParticle(ClientLevel level, double x, double y, double z, Fluid fluid) {
		super(level, x, y, z, fluid);
	}

	@Override
	public int getLightColor(float partialTicks) {
		return 240;
	}

	public record JoyFallProvider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {

		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
			DripParticle dripparticle = new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.EMPTY, RisusParticles.LANDING_JOY.get());
			try {
				handle_particle_Gravity_set.invokeExact((Particle) dripparticle, 0.01F);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.pickSprite(this.sprite());
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public record JoyHangProvider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {

		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
			DripParticle dripparticle = new DripParticle.DripHangParticle(level, x, y, z, Fluids.EMPTY, RisusParticles.FALLING_JOY.get());
			try {
				handle_particle_Gravity_set.invokeExact((Particle) dripparticle, (float) handle_particle_Gravity_get.invokeExact((Particle) dripparticle) * 0.01F);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
			dripparticle.setLifetime(100);
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.pickSprite(this.sprite());
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public record JoyLandProvider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {

		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
			DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY);
			dripparticle.setLifetime((int) (28.0D / (Math.random() * 0.8D + 0.2D)));
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.pickSprite(this.sprite());
			return dripparticle;
		}
	}
}
