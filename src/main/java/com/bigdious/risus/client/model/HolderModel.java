package com.bigdious.risus.client.model;

import com.bigdious.risus.entity.HolderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;


public class HolderModel<T extends HolderEntity> extends EntityModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer arms;
	private final ModelRenderer main;

	public HolderModel() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 23.0F, 0.0F);
		body.setTextureOffset(0, 10).addBox(-4.0F, -8.0F, -1.0F, 7.0F, 9.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(6, 15).addBox(-6.0F, -5.0F, 2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(2, 12).addBox(-5.0F, -2.0F, -4.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(5, 14).addBox(0.0F, -11.0F, 0.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(2, 14).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(1, 18).addBox(-2.0F, 0.0F, -6.0F, 7.0F, 1.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(5, 15).addBox(1.0F, -3.0F, -2.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(4, 21).addBox(-7.0F, 0.0F, -1.0F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(13, 23).addBox(4.0F, 0.0F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(1.0F, 19.0F, 4.0F);
		arms.setTextureOffset(5, 6).addBox(-6.0F, -1.0F, -11.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		arms.setTextureOffset(5, 6).addBox(1.0F, -1.0F, -11.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-3.0F, -11.0F, -2.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		arms.render(matrixStack, buffer, packedLight, packedOverlay);
		main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}