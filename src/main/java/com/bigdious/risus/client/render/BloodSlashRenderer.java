package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.BloodSlashModel;
import com.bigdious.risus.entity.projectile.BloodSlash;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Minecart;

public class BloodSlashRenderer extends EntityRenderer<BloodSlash> {
	protected static final ResourceLocation SLASH_LOCATION = Risus.prefix("textures/entity/bloodslash.png");
	private final BloodSlashModel model;

	public BloodSlashRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new BloodSlashModel(context.bakeLayer(RisusModelLayers.BLOODSLASH));
	}

	@Override
	public void render(BloodSlash slash, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		super.render(slash, yaw, partialTicks, stack, buffer, light);
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, slash.yRotO, slash.getYRot())));
		stack.translate(-1.5D, 0, 0.0D);
		stack.mulPose(Axis.XP.rotationDegrees(-slash.xRotO));
		stack.mulPose(Axis.ZP.rotationDegrees(90));

		stack.scale(1.0F, -1.0F, -1.0F);

		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(SLASH_LOCATION));
		this.model.renderToBuffer(stack, vertexconsumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
		stack.popPose();

		if (Minecraft.getInstance().getEntityRenderDispatcher().shouldRenderHitBoxes()) {
			stack.pushPose();
			stack.mulPose(Axis.YP.rotationDegrees(slash.yRotO));
			stack.mulPose(Axis.XP.rotationDegrees(-slash.xRotO));
			double d0 = -slash.getX();
			double d1 = -slash.getY();
			double d2 = -slash.getZ();
			stack.translate(d0, d1, d2);
			LevelRenderer.renderLineBox(stack, buffer.getBuffer(RenderType.lines()), slash.getBoundingBox().inflate(0.01D, 1.25D, 0.01D).move(0.0D, -0.25D, 0.0D), 1.0F, 0.0F, 0.0F, 1.0F);
			stack.popPose();
		}
	}

	@Override
	public ResourceLocation getTextureLocation(BloodSlash slash) {
		return SLASH_LOCATION;
	}
}
