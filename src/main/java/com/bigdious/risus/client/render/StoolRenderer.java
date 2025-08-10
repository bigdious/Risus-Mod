package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.StoolModel;
import com.bigdious.risus.entity.Stool;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class StoolRenderer extends EntityRenderer<Stool> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/great_stool.png");
	private final Model model;

	public StoolRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new StoolModel<>(context.bakeLayer(RisusModelLayers.GREAT_STOOL));
	}

	@Override
	public void render(Stool entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		super.render(entity, yaw, partialTicks, stack, buffer, light);
		stack.pushPose();
		this.model.renderToBuffer(stack, buffer.getBuffer(this.model.renderType(TEXTURE)), light, OverlayTexture.NO_OVERLAY);
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(Stool stool) {
		return TEXTURE;
	}
}
