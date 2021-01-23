package com.bigdious.risus.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BloodWyrmHeadModel extends GenericHeadModel {
	private final ModelRenderer main;
	private final ModelRenderer flamethrower;
	private final ModelRenderer lowerJaw;
	private final ModelRenderer lowerTeeth;
	private final ModelRenderer largeRightTooth;
	private final ModelRenderer largeLeftTooth;
	private final ModelRenderer lowerLip;
	private final ModelRenderer upperJaw;
	private final ModelRenderer upperTeeth;
	private final ModelRenderer largeRight;
	private final ModelRenderer largeLeft;
	private final ModelRenderer upperLip;

	public BloodWyrmHeadModel() {
		textureWidth = 128;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 26.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 14.0F, 16.0F, 0.0F, false);
		main.setTextureOffset(0, 0).addBox(-5.0F, -19.0F, -2.0F, 2.0F, 3.0F, 6.0F, 0.0F, false);
		main.setTextureOffset(0, 0).addBox(3.0F, -19.0F, -2.0F, 2.0F, 3.0F, 6.0F, 0.0F, false);

		flamethrower = new ModelRenderer(this);
		flamethrower.setRotationPoint(0.0F, 0.0F, 0.0F);
		main.addChild(flamethrower);
		flamethrower.setTextureOffset(83, 52).addBox(-11.0F, -8.0F, 5.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		flamethrower.setTextureOffset(10, 38).addBox(-10.5F, -7.5F, -10.0F, 2.0F, 2.0F, 15.0F, 0.0F, false);
		flamethrower.setTextureOffset(21, 47).addBox(-8.5F, -7.5F, -10.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);

		lowerJaw = new ModelRenderer(this);
		lowerJaw.setRotationPoint(0.0F, -5.0F, -7.0F);
		main.addChild(lowerJaw);
		setRotationAngle(lowerJaw, -0.3054F, 0.0F, 0.0F);
		

		lowerTeeth = new ModelRenderer(this);
		lowerTeeth.setRotationPoint(-3.0F, 4.0F, -15.0F);
		lowerJaw.addChild(lowerTeeth);
		setRotationAngle(lowerTeeth, 0.3054F, 0.0F, 0.0F);
		lowerTeeth.setTextureOffset(0, 0).addBox(-2.01F, -1.7604F, 3.5976F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		lowerTeeth.setTextureOffset(0, 0).addBox(-2.01F, -1.5208F, 7.1952F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		lowerTeeth.setTextureOffset(0, 0).addBox(-2.01F, -1.2813F, 10.7927F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		lowerTeeth.setTextureOffset(0, 0).addBox(6.99F, -1.2813F, 10.7927F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		lowerTeeth.setTextureOffset(0, 0).addBox(6.99F, -1.5208F, 7.1952F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		lowerTeeth.setTextureOffset(0, 0).addBox(6.99F, -1.7604F, 3.5976F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		largeRightTooth = new ModelRenderer(this);
		largeRightTooth.setRotationPoint(0.0F, 0.0F, 0.0F);
		lowerTeeth.addChild(largeRightTooth);
		largeRightTooth.setTextureOffset(0, 0).addBox(-0.01F, -1.3912F, 0.7934F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		largeRightTooth.setTextureOffset(0, 0).addBox(1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		largeRightTooth.setTextureOffset(0, 0).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		largeLeftTooth = new ModelRenderer(this);
		largeLeftTooth.setRotationPoint(0.0F, 0.0F, 0.0F);
		lowerTeeth.addChild(largeLeftTooth);
		largeLeftTooth.setTextureOffset(0, 0).addBox(4.99F, -1.3912F, 0.7934F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		largeLeftTooth.setTextureOffset(0, 0).addBox(4.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		largeLeftTooth.setTextureOffset(0, 0).addBox(5.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		lowerLip = new ModelRenderer(this);
		lowerLip.setRotationPoint(0.0F, 0.0F, 0.0F);
		lowerJaw.addChild(lowerLip);
		setRotationAngle(lowerLip, 0.3054F, 0.0F, 0.0F);
		lowerLip.setTextureOffset(56, 42).addBox(-6.0F, -1.0F, -16.0F, 12.0F, 4.0F, 16.0F, 0.0F, false);

		upperJaw = new ModelRenderer(this);
		upperJaw.setRotationPoint(0.0F, -6.0F, -7.0F);
		main.addChild(upperJaw);
		

		upperTeeth = new ModelRenderer(this);
		upperTeeth.setRotationPoint(0.0F, 0.0F, 0.0F);
		upperJaw.addChild(upperTeeth);
		upperTeeth.setTextureOffset(0, 0).addBox(4.0F, -2.0F, -8.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		upperTeeth.setTextureOffset(0, 0).addBox(4.0F, -1.0F, -11.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		upperTeeth.setTextureOffset(0, 0).addBox(-5.0F, -2.0F, -8.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		upperTeeth.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -11.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		largeRight = new ModelRenderer(this);
		largeRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		upperTeeth.addChild(largeRight);
		largeRight.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -14.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		largeRight.setTextureOffset(0, 0).addBox(-4.0F, -1.0F, -15.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		largeRight.setTextureOffset(0, 0).addBox(-5.0F, 0.0F, -15.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		largeLeft = new ModelRenderer(this);
		largeLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		upperTeeth.addChild(largeLeft);
		largeLeft.setTextureOffset(0, 0).addBox(4.0F, 0.0F, -15.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		largeLeft.setTextureOffset(0, 0).addBox(3.0F, -1.0F, -15.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		largeLeft.setTextureOffset(0, 0).addBox(4.0F, -1.0F, -14.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		upperLip = new ModelRenderer(this);
		upperLip.setRotationPoint(0.0F, 0.0F, 0.0F);
		upperJaw.addChild(upperLip);
		upperLip.setTextureOffset(84, 47).addBox(-4.0F, -6.0F, -14.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		upperLip.setTextureOffset(84, 50).addBox(2.0F, -6.0F, -14.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		upperLip.setTextureOffset(56, 21).addBox(-6.0F, -4.0F, -16.0F, 12.0F, 4.0F, 16.0F, 0.0F, false);
	}

	public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
	      this.upperJaw.rotateAngleX = -(float)(Math.sin((double)(p_225603_1_ * (float)Math.PI * 0.2F)) + 1.0D) * 0.2F;
	      this.lowerJaw.rotateAngleX = (float)(Math.sin((double)(p_225603_1_ * (float)Math.PI * 0.2F)) + 1.0D) * 0.2F;
	      this.main.rotateAngleY = p_225603_2_ * ((float)Math.PI / 180F);
	      this.main.rotateAngleX = p_225603_3_ * ((float)Math.PI / 180F);
	   }

	   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
	      matrixStackIn.push();
	      matrixStackIn.translate(0.0D, -1.125F, 0.0D);
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