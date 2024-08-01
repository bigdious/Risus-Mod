package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.WeaverModel;
import com.bigdious.risus.entity.Weaver;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class WeaverRenderer extends MobRenderer<Weaver, WeaverModel<Weaver>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/weaver.png");

	public WeaverRenderer(EntityRendererProvider.Context context) {
		super(context, new WeaverModel<>(context.bakeLayer(RisusModelLayers.WEAVER)), 0.0F);
		this.addLayer(new CoreLayer(this, context));
	}

	@Override
	protected void scale(Weaver weaver, PoseStack stack, float partialTicks) {
		stack.scale(0.5F, 0.5F, 0.5F);
	}


	@Override
	public ResourceLocation getTextureLocation(Weaver weaver) {
		return TEXTURE;
	}

	static class CoreLayer extends RenderLayer<Weaver, WeaverModel<Weaver>> {
		private final WeaverModel<Weaver> core;

		public CoreLayer(RenderLayerParent<Weaver, WeaverModel<Weaver>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			this.core = new WeaverModel<>(manager.bakeLayer(RisusModelLayers.WEAVER_CORE));
		}

		@Override
		public void render(PoseStack ms, MultiBufferSource buffers, int light, Weaver entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			if (!entity.isInvisible()) {
				this.core.copyPropertiesTo(this.getParentModel());
				this.core.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				this.core.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				VertexConsumer buffer = buffers.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
				this.core.renderCore(ms, buffer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1);
			}
		}
	}
}
