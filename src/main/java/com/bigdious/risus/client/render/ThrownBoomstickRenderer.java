package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.ThrownAxeModel;
import com.bigdious.risus.client.model.entity.ThrownBoomstickModel;
import com.bigdious.risus.client.model.entity.ThrownDrumstickModel;
import com.bigdious.risus.entity.projectile.ThrownAxe;
import com.bigdious.risus.entity.projectile.ThrownBoomstick;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ThrownBoomstickRenderer extends EntityRenderer<ThrownBoomstick> {
	public static final ResourceLocation BOOMSTICK_LOCATION = Risus.prefix("textures/entity/boomstick.png");
	public static final ResourceLocation DRUMSTICK_LOCATION = Risus.prefix("textures/item/drumstick.png");
	private final ThrownBoomstickModel model;
	private final ThrownDrumstickModel alternateModel;

	public ThrownBoomstickRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new ThrownBoomstickModel(context.bakeLayer(RisusModelLayers.THROWN_BOOMSTICK));
		this.alternateModel = new ThrownDrumstickModel(context.bakeLayer(RisusModelLayers.THROWN_DRUMSTICK));
	}

	@Override
	public void render(ThrownBoomstick boomstick, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, boomstick.yRotO, boomstick.getYRot()) - 90.0F));
		stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, boomstick.xRotO, boomstick.getXRot()) + 90.0F));
		if (boomstick.isDrumstick()) {
			VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
				buffer, this.alternateModel.renderType(this.getTextureLocation(boomstick)), false, boomstick.isFoil()
			);
			this.alternateModel.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
		} else {
			VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
				buffer, this.model.renderType(this.getTextureLocation(boomstick)), false, boomstick.isFoil()
			);
			this.model.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
		}
		stack.popPose();
		super.render(boomstick, yaw, partialTicks, stack, buffer, light);
	}

	public ResourceLocation getTextureLocation(ThrownBoomstick boomstick) {
		return boomstick.isDrumstick() ? DRUMSTICK_LOCATION : BOOMSTICK_LOCATION;
	}
}
