package com.bigdious.risus.client.particle;

import com.bigdious.risus.Risus;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Random;

public class BloodParticle extends TextureSheetParticle {
	private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/particle/cauldron_boil_particle.png");
	// Thanks to JoeFoxe and his mod Hexerei for this code
	public static final Vec3[] CUBE = {
			// bottom render
			//right
			new Vec3(0.5, -0.1, -0.5), // >^
			new Vec3(0.5, -0.1, 0.5),  // >V
			new Vec3(0.25, -0.1, 0.5), // <V
			new Vec3(0.25, -0.1, -0.5),// <^
			//left
			new Vec3(-0.25, -0.1, -0.5), // >^
			new Vec3(-0.25, -0.1, 0.5),  // >V
			new Vec3(-0.5, -0.1, 0.5), // <V
			new Vec3(-0.5, -0.1, -0.5),// <^
			//front
			new Vec3(0.25, -0.1, -0.5), // >^
			new Vec3(0.25, -0.1, -0.25),  // >V
			new Vec3(-0.25, -0.1, -0.25), // <V
			new Vec3(-0.25, -0.1, -0.5),// <^
			//back
			new Vec3(-0.25, -0.1, 0.5), // >^
			new Vec3(-0.25, -0.1, 0.25),  // >V
			new Vec3(0.25, -0.1, 0.25), // <V
			new Vec3(0.25, -0.1, 0.5),// <^


			// top render
			new Vec3(0.25, 0.1, -0.5), // >^
			new Vec3(0.25, 0.1, 0.5),  // >V
			new Vec3(0.5, 0.1, 0.5), // <V
			new Vec3(0.5, 0.1, -0.5),// <^
			//left
			new Vec3(-0.5, 0.1, -0.5), // >^
			new Vec3(-0.5, 0.1, 0.5),  // >V
			new Vec3(-0.25, 0.1, 0.5), // <V
			new Vec3(-0.25, 0.1, -0.5),// <^
			//front
			new Vec3(-0.25, 0.1, -0.5), // >^
			new Vec3(-0.25, 0.1, -0.25),  // >V
			new Vec3(0.25, 0.1, -0.25), // <V
			new Vec3(0.25, 0.1, -0.5),// <^
			//back
			new Vec3(0.25, 0.1, 0.5), // >^
			new Vec3(0.25, 0.1, 0.25),  // >V
			new Vec3(-0.25, 0.1, 0.25), // <V
			new Vec3(-0.25, 0.1, 0.5),// <^


			// front render
			new Vec3(-0.5, -0.1, -0.5),
			new Vec3(-0.5, 0.1, -0.5),
			new Vec3(0.5, 0.1, -0.5),
			new Vec3(0.5, -0.1, -0.5),

			// back render
			new Vec3(0.5, -0.1, 0.5),
			new Vec3(0.5, 0.1, 0.5),
			new Vec3(-0.5, 0.1, 0.5),
			new Vec3(-0.5, -0.1, 0.5),

			// left render
			new Vec3(0.5, -0.1, -0.5),
			new Vec3(0.5, 0.1, -0.5),
			new Vec3(0.5, 0.1, 0.5),
			new Vec3(0.5, -0.1, 0.5),

			// right render
			new Vec3(-0.5, -0.1, 0.5),
			new Vec3(-0.5, 0.1, 0.5),
			new Vec3(-0.5, 0.1, -0.5),
			new Vec3(-0.5, -0.1, -0.5),


			// inside
			// front render
			new Vec3(-0.25, -0.1, 0.25),
			new Vec3(-0.25, 0.1, 0.25),
			new Vec3(0.25, 0.1, 0.25),
			new Vec3(0.25, -0.1, 0.25),

			// back render
			new Vec3(0.25, -0.1, -0.25),
			new Vec3(0.25, 0.1, -0.25),
			new Vec3(-0.25, 0.1, -0.25),
			new Vec3(-0.25, -0.1, -0.25),

			// left render
			new Vec3(-0.25, -0.1, -0.25),
			new Vec3(-0.25, 0.1, -0.25),
			new Vec3(-0.25, 0.1, 0.25),
			new Vec3(-0.25, -0.1, 0.25),

			// right render
			new Vec3(0.25, -0.1, 0.25),
			new Vec3(0.25, 0.1, 0.25),
			new Vec3(0.25, 0.1, -0.25),
			new Vec3(0.25, -0.1, -0.25),

			//middle top inside
			new Vec3(0.25, -0.01, -0.25),
			new Vec3(0.25, -0.01, 0.25),
			new Vec3(-0.25, -0.01, 0.25),
			new Vec3(-0.25, -0.01, -0.25),
			// middle bottom render
			new Vec3(-0.25, 0.01, -0.25),
			new Vec3(-0.25, 0.01, 0.25),
			new Vec3(0.25, 0.01, 0.25),
			new Vec3(0.25, 0.01, -0.25),


	};

	public static final Vec3[] CUBE_NORMALS = {
			// modified normals for the sides
			new Vec3(0, 0.1, 0),
			new Vec3(0, 0.1, 0),
			new Vec3(0, 0.1, 0),
			new Vec3(0, 0.1, 0),
			new Vec3(0, -0.5, 0),
			new Vec3(0, -0.5, 0),
			new Vec3(0, -0.5, 0),
			new Vec3(0, -0.5, 0),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
			new Vec3(0, 0, 0.5),
	};

	private static final ParticleRenderType renderType = new ParticleRenderType() {
		@Override
		public BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {

			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

			return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
		}
	};

	protected float scale;
	protected float rotationDirection;
	protected float rotation;
	protected float rotationOffsetYaw;
	protected float rotationOffsetPitch;
	protected float rotationOffsetRoll;
	protected float colorOffset;


	public BloodParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z);
		this.xd = motionX;
		this.yd = motionY;
		this.zd = motionZ;
		this.rotation = 0;

		averageAge(80);

		Random random = new Random();

//		this.setColorOffset = (random.nextFloat() * 0.25f);
		this.rotationOffsetYaw = random.nextFloat();
		this.rotationOffsetPitch = random.nextFloat();
		this.rotationOffsetRoll = random.nextFloat();

		setScale(0.2F);
		setRotationDirection(random.nextFloat() - 0.5f);
	}

	public void setScale(float scale) {
		this.scale = scale;
		this.setSize(scale * 0.5f, scale * 0.5f);
	}

	public void averageAge(int age) {
		Random random = new Random();
		this.lifetime = (int) (age + (random.nextDouble() * 2D - 1D) * 8);
	}

	public void setRotationDirection(float rotationDirection) {
		this.rotationDirection = rotationDirection;
	}


	@Override
	public void tick() {

		this.rotation = (this.rotationDirection * 0.1f) + this.rotation;

		super.tick();
	}

	@Override
	public void render(VertexConsumer builder, Camera renderInfo, float p_225606_3_) {
		Vec3 projectedView = renderInfo.getPosition();
		float lerpX = (float) (Mth.lerp(p_225606_3_, this.xo, this.x) - projectedView.x());
		float lerpY = (float) (Mth.lerp(p_225606_3_, this.yo, this.y) - projectedView.y());
		float lerpZ = (float) (Mth.lerp(p_225606_3_, this.zo, this.z) - projectedView.z());

		int light = 15728880;
		double ageMultiplier = 1 - Math.pow(Mth.clamp(age + p_225606_3_, 0, lifetime), 3) / Math.pow(lifetime, 3);

		RenderSystem.setShaderTexture(0, TEXTURE);

		for (int i = 0; i < CUBE.length / 4; i++) {
			for (int j = 0; j < 4; j++) {
				Vec3 vec = CUBE[i * 4 + j];
				vec = vec
						.yRot(this.rotation + this.rotationOffsetYaw)
						.xRot(this.rotation + this.rotationOffsetPitch)
						.zRot(this.rotation + this.rotationOffsetRoll)
						.scale(scale * ageMultiplier)
						.add(lerpX, lerpY, lerpZ);

				Vec3 normal = CUBE_NORMALS[i];

				if (i == 0 || i == 1 || i == 2 || i == 3) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
						.setColor(Mth.clamp(rCol * 1.25f, 0, 1.0f), Mth.clamp(gCol * 1.25f, 0, 1.0f), Mth.clamp(bCol * 1.25f, 0, 1.0f), alpha)
						.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
						.setUv2(light, light);
				} else if (i == 4 || i == 5 || i == 6 || i == 7) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 0.55f, gCol * 0.55f, bCol * 0.55f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				} else if (i == 8) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 0.75f, gCol * 0.95f, bCol * 0.95f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				} else if (i == 9) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 0.85f, gCol * 0.75f, bCol * 0.75f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				} else if (i == 10) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 0.95f, gCol * 0.9f, bCol * 0.9f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				} else if (i == 11) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 1.05f, gCol * 1.05f, bCol * 1.05f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				} else if (i == 12 || i == 13 || i == 14 || i == 15) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 0.4f, gCol * 0.4f, bCol * 0.4f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				} else if (i == 17 || i == 16) {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 0.4f, gCol * 0.4f, bCol * 0.4f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				} else {
					builder.addVertex((float)vec.x, (float)vec.y, (float)vec.z)
							.setUv(0, 0)
							.setColor(rCol * 0.85f, gCol * 0.85f, bCol * 0.85f, alpha)
							.setNormal((float) normal.x, (float) normal.y, (float) normal.z)
							.setUv2(light, light)
							;
				}
			}
		}
	}


	@Override
	public ParticleRenderType getRenderType() {
		return renderType;
	}

	public static class Factory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Factory(SpriteSet sprite) {
			this.spriteSet = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			BloodParticle cauldronParticle = new BloodParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			Random random = new Random();
			float colorOffset = (random.nextFloat() * 0.20f);
			cauldronParticle.setColor(0.05f + colorOffset, 0.05f, 0.05f);
			cauldronParticle.setAlpha(1.0f);
			cauldronParticle.pickSprite(this.spriteSet);
			return cauldronParticle;

		}
	}

}
