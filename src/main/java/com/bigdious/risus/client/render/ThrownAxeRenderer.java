package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.ThrownAxeModel;
import com.bigdious.risus.entity.ThrownAxe;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ThrownAxeRenderer extends EntityRenderer<ThrownAxe> {
	public static final ResourceLocation AXE_LOCATION = Risus.prefix("textures/entity/crescent_disaster.png");
	private final ThrownAxeModel model;

	public ThrownAxeRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new ThrownAxeModel(context.bakeLayer(RisusModelLayers.THROWN_AXE));
	}

	@Override
	public void render(ThrownAxe axe, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		stack.pushPose();
		stack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, axe.yRotO, axe.getYRot())));
		stack.translate(0.0D, -0.5D, 0.0D);
		stack.translate(0.0D, 0.65D, 0.0D);
		stack.scale(1.0F, -1.0F, -1.0F);
		stack.mulPose(Vector3f.XP.rotationDegrees(axe.shouldSpin() ? axe.spinTickCount * 20.0F : 55.0F));
		stack.translate(0.0D, -0.65D, 0.0D);
		VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(axe)), false, axe.isFoil());
		this.model.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		stack.popPose();
		super.render(axe, yaw, partialTicks, stack, buffer, light);
	}

	public ResourceLocation getTextureLocation(ThrownAxe axe) {
		return AXE_LOCATION;
	}
}