package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.SingerModel;
import com.bigdious.risus.entity.creatures.Singer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class SingerRenderer extends MobRenderer<Singer, SingerModel<Singer>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/singer.png");

	public SingerRenderer(EntityRendererProvider.Context context) {
		super(context, new SingerModel<>(context.bakeLayer(RisusModelLayers.SINGER)), 0.0F);
		this.addLayer(new SingerEyesLayer(this, context));
	}

	@Override
	public ResourceLocation getTextureLocation(Singer singer) {
		return TEXTURE;
	}

	static class SingerEyesLayer extends EyesLayer<Singer, SingerModel<Singer>> {

		public SingerEyesLayer(RenderLayerParent<Singer, SingerModel<Singer>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			SingerModel<Singer> eyes = new SingerModel<>(manager.bakeLayer(RisusModelLayers.SINGER_EYES));
		}
		@Override
		public RenderType renderType() {
			return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/singer_eyes.png"));
		}
	}
}
