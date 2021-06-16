package com.bigdious.risus.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BoopDragonModel extends GenericHeadModel {
	private final ModelRenderer main;

	public BoopDragonModel() {
		textureWidth = 128;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 14.0F, 16.0F, 0.0F, false);
	}

	public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
		this.main.rotateAngleY = p_225603_2_ * ((float) Math.PI / 180F);
		this.main.rotateAngleX = p_225603_3_ * ((float) Math.PI / 180F);
	}

	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.main.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}