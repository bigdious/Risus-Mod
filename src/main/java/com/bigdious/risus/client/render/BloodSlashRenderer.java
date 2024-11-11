package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.BloodSlashModel;
import com.bigdious.risus.client.model.entity.ThrownAxeModel;
import com.bigdious.risus.entity.projectile.BloodSlash;
import com.bigdious.risus.entity.projectile.ThrownAxe;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BloodSlashRenderer extends EntityRenderer<BloodSlash> {
	protected static final ResourceLocation SLASH_LOCATION = Risus.prefix("textures/entity/bloodslash.png");
	private final BloodSlashModel model;

	public BloodSlashRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new BloodSlashModel(context.bakeLayer(RisusModelLayers.BLOODSLASH));
	}
	@Override
	public void render(BloodSlash slash, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, slash.yRotO, slash.getYRot())));
		stack.translate(-1.5D, 0, 0.0D);
		stack.mulPose(Axis.XP.rotationDegrees(-slash.xRotO));
		stack.mulPose(Axis.ZP.rotationDegrees(90));
		stack.translate(0.0D, 0, 0.0D);
		stack.scale(1.0F, -1.0F, -1.0F);
		stack.translate(0.0D, 0, 0.0D);
		VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
			buffer, this.model.renderType(this.getTextureLocation(slash)), false, false
		);
		this.model.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
		stack.popPose();
		super.render(slash, yaw, partialTicks, stack, buffer, light);
	}
	@Override
	public ResourceLocation getTextureLocation(BloodSlash slash) {
		return SLASH_LOCATION;
	}
}
