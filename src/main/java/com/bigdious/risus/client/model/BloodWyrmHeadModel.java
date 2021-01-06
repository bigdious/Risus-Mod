package com.bigdious.risus.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BloodWyrmHeadModel extends GenericHeadModel {
	private final ModelRenderer upperJaw;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer lowerJaw;
	private final ModelRenderer cube_r11;
	private final ModelRenderer lowerJawMouth_r1;
	private final ModelRenderer main;

	public BloodWyrmHeadModel() {
		textureWidth = 128;
		textureHeight = 64;

		upperJaw = new ModelRenderer(this);
		upperJaw.setRotationPoint(0.0F, 18.0F, -7.0F);
		setRotationAngle(upperJaw, -0.1745F, 0.0F, 0.0F);
		upperJaw.setTextureOffset(84, 47).addBox(-4.0F, -6.0F, -14.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		upperJaw.setTextureOffset(84, 50).addBox(2.0F, -6.0F, -14.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		upperJaw.setTextureOffset(56, 25).addBox(-6.0F, -4.0F, -16.0F, 12.0F, 4.0F, 16.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(4.0F, -2.0F, -8.0F);
		upperJaw.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -3.1416F);
		cube_r1.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-5.0F, -2.0F, -8.0F);
		upperJaw.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -3.1416F);
		cube_r2.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-5.0F, -1.0F, -11.0F);
		upperJaw.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -3.1416F);
		cube_r3.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(4.0F, -1.0F, -11.0F);
		upperJaw.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -3.1416F);
		cube_r4.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(4.0F, -1.2F, -14.0F);
		upperJaw.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -3.1416F);
		cube_r5.setTextureOffset(0, 0).addBox(-1.0F, -2.8F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-5.0F, -1.2F, -14.0F);
		upperJaw.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -3.1416F);
		cube_r6.setTextureOffset(0, 0).addBox(-1.0F, -2.8F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-4.0F, -1.2F, -15.0F);
		upperJaw.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -3.1416F);
		cube_r7.setTextureOffset(0, 0).addBox(-1.0F, -2.8F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(3.0F, -1.2F, -15.0F);
		upperJaw.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, -3.1416F);
		cube_r8.setTextureOffset(0, 0).addBox(-1.0F, -2.8F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(4.0F, -0.2F, -15.0F);
		upperJaw.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, -3.1416F);
		cube_r9.setTextureOffset(0, 0).addBox(-1.0F, -2.8F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-5.0F, -0.2F, -15.0F);
		upperJaw.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, -3.1416F);
		cube_r10.setTextureOffset(0, 0).addBox(-1.0F, -2.8F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		lowerJaw = new ModelRenderer(this);
		lowerJaw.setRotationPoint(0.0F, 19.0F, -7.0F);
		setRotationAngle(lowerJaw, 0.1309F, 0.0F, 0.0F);
		

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-3.0F, 4.0F, -15.0F);
		lowerJaw.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.3054F, 0.0F, 0.0F);
		cube_r11.setTextureOffset(0, 0).addBox(-2.01F, -1.7604F, 3.5976F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(-2.01F, -1.5208F, 7.1952F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(-2.01F, -1.2813F, 10.7927F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(6.99F, -1.2813F, 10.7927F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(6.99F, -1.5208F, 7.1952F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(6.99F, -1.7604F, 3.5976F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(4.99F, -1.3912F, 0.7934F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(-0.01F, -1.3912F, 0.7934F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(4.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(5.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r11.setTextureOffset(0, 0).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		lowerJawMouth_r1 = new ModelRenderer(this);
		lowerJawMouth_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		lowerJaw.addChild(lowerJawMouth_r1);
		setRotationAngle(lowerJawMouth_r1, 0.3054F, 0.0F, 0.0F);
		lowerJawMouth_r1.setTextureOffset(56, 45).addBox(-6.0F, -1.0F, -16.0F, 12.0F, 4.0F, 16.0F, 0.0F, false);

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 14.0F, 16.0F, 0.0F, false);
		main.setTextureOffset(0, 0).addBox(-5.0F, -19.0F, -2.0F, 2.0F, 3.0F, 6.0F, 0.0F, false);
		main.setTextureOffset(0, 0).addBox(3.0F, -19.0F, -2.0F, 2.0F, 3.0F, 6.0F, 0.0F, false);
		main.setTextureOffset(83, 52).addBox(-11.0F, -8.0F, 5.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		main.setTextureOffset(10, 38).addBox(-10.5F, -7.5F, -10.0F, 2.0F, 2.0F, 15.0F, 0.0F, false);
		main.setTextureOffset(21, 47).addBox(-8.5F, -7.5F, -10.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);
	}
	
	public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
	      this.lowerJawMouth_r1.rotateAngleX = (float)(Math.sin((double)(p_225603_1_ * (float)Math.PI * 0.2F)) + 1.0D) * 0.2F;
	      this.main.rotateAngleY = p_225603_2_ * ((float)Math.PI / 180F);
	      this.main.rotateAngleX = p_225603_3_ * ((float)Math.PI / 180F);
	   }

	   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
	      matrixStackIn.push();
	      matrixStackIn.translate(0.0D, (double)-0.374375F, 0.0D);
	      matrixStackIn.scale(0.75F, 0.75F, 0.75F);
	      this.main.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	      matrixStackIn.pop();
	   }

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}