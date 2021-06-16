package com.bigdious.risus.client.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//this class causes me so much pain inside
//anyway, I ripped a ton of this stuff from DripParticle, just so I could use it with laughing obsidian
@OnlyIn(Dist.CLIENT)
public class JoyParticle extends SpriteTexturedParticle {
	private final Fluid fluid;
	protected boolean fullbright;

	private JoyParticle(ClientWorld world, double x, double y, double z, Fluid fluid) {
		super(world, x, y, z);
		this.setSize(0.01F, 0.01F);
		this.particleGravity = 0.06F;
		this.fluid = fluid;
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public int getBrightnessForRender(float partialTick) {
		return this.fullbright ? 240 : super.getBrightnessForRender(partialTick);
	}

	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.ageParticle();
		if (!this.isExpired) {
			this.motionY -= (double)this.particleGravity;
			this.move(this.motionX, this.motionY, this.motionZ);
			this.updateMotion();
			if (!this.isExpired) {
				this.motionX *= (double)0.98F;
				this.motionY *= (double)0.98F;
				this.motionZ *= (double)0.98F;
				BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
				FluidState fluidstate = this.world.getFluidState(blockpos);
				if (fluidstate.getFluid() == this.fluid && this.posY < (double)((float)blockpos.getY() + fluidstate.getActualHeight(this.world, blockpos))) {
					this.setExpired();
				}

			}
		}
	}

	protected void ageParticle() {
		if (this.maxAge-- <= 0) {
			this.setExpired();
		}

	}

	protected void updateMotion() {
	}
	@OnlyIn(Dist.CLIENT)
	static class Dripping extends JoyParticle {
		private final IParticleData particleData;

		private Dripping(ClientWorld world, double x, double y, double z, Fluid fluid, IParticleData particleData) {
			super(world, x, y, z, fluid);
			this.particleData = particleData;
			this.particleGravity *= 0.02F;
			this.maxAge = 40;
		}

		protected void ageParticle() {
			if (this.maxAge-- <= 0) {
				this.setExpired();
				this.world.addParticle(this.particleData, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
			}

		}

		protected void updateMotion() {
			this.motionX *= 0.02D;
			this.motionY *= 0.02D;
			this.motionZ *= 0.02D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	static class FallingLiquidParticle extends JoyParticle.FallingDummyParticle {
		protected final IParticleData particleData;

		private FallingLiquidParticle(ClientWorld world, double x, double y, double z, Fluid fluid, IParticleData particleData) {
			super(world, x, y, z, fluid);
			this.particleData = particleData;
		}

		protected void updateMotion() {
			if (this.onGround) {
				this.setExpired();
				this.world.addParticle(this.particleData, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}

		}
	}

	@OnlyIn(Dist.CLIENT)
	static class FallingDummyParticle extends JoyParticle {
		private FallingDummyParticle(ClientWorld world, double x, double y, double z, Fluid fluid) {
			super(world, x, y, z, fluid);
			this.maxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
		}

		protected void updateMotion() {
			if (this.onGround) {
				this.setExpired();
			}

		}
	}

	@OnlyIn(Dist.CLIENT)
	static class Landing extends JoyParticle {
		private Landing(ClientWorld world, double x, double y, double z, Fluid fluid) {
			super(world, x, y, z, fluid);
			this.maxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class DrippingJoyFactory implements IParticleFactory<BasicParticleType> {
		protected final IAnimatedSprite spriteSet;

		public DrippingJoyFactory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			JoyParticle.Dripping dripparticle$dripping = new JoyParticle.Dripping(worldIn, x, y, z, Fluids.EMPTY, RisusParticles.FALLING_JOY.get());
			dripparticle$dripping.fullbright = true;
			dripparticle$dripping.particleGravity *= 0.01F;
			dripparticle$dripping.maxAge = 100;
			dripparticle$dripping.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle$dripping.selectSpriteRandomly(this.spriteSet);
			return dripparticle$dripping;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class FallingJoyFactory implements IParticleFactory<BasicParticleType> {
		protected final IAnimatedSprite spriteSet;

		public FallingJoyFactory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			JoyParticle dripparticle = new JoyParticle.FallingLiquidParticle(worldIn, x, y, z, Fluids.EMPTY, RisusParticles.LANDING_JOY.get());
			dripparticle.fullbright = true;
			dripparticle.particleGravity = 0.01F;
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.selectSpriteRandomly(this.spriteSet);
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class LandingJoyFactory implements IParticleFactory<BasicParticleType> {
		protected final IAnimatedSprite spriteSet;

		public LandingJoyFactory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			JoyParticle dripparticle = new JoyParticle.Landing(worldIn, x, y, z, Fluids.EMPTY);
			dripparticle.fullbright = true;
			dripparticle.maxAge = (int)(28.0D / (Math.random() * 0.8D + 0.2D));
			dripparticle.setColor(0.51171875F, 0.03125F, 0.890625F);
			dripparticle.selectSpriteRandomly(this.spriteSet);
			return dripparticle;
		}
	}
}
