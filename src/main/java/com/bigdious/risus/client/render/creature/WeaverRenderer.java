package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.WeaverModel;
import com.bigdious.risus.entity.creatures.Weaver;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
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

	static class CoreLayer extends EyesLayer<Weaver, WeaverModel<Weaver>> {

		public CoreLayer(RenderLayerParent<Weaver, WeaverModel<Weaver>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			WeaverModel<Weaver> memoryCore = new WeaverModel<>(manager.bakeLayer(RisusModelLayers.WEAVER_CORE));
		}
		@Override
		public RenderType renderType() {
			return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/weaver_core.png"));
		}
	}
}
