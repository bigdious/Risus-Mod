package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.AngelModel;
import com.bigdious.risus.entity.creatures.Angel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class AngelRenderer extends MobRenderer<Angel, AngelModel<Angel>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/ophanim.png");


	public AngelRenderer(EntityRendererProvider.Context context) {
		super(context, new AngelModel<>(context.bakeLayer(RisusModelLayers.ANGEL)), 0.0F);
		this.addLayer(new AngelFaceLayer(this, context));
	}

	@Override
	public ResourceLocation getTextureLocation(Angel angel) {
		return TEXTURE;
	}

	static class AngelFaceLayer extends EyesLayer<Angel, AngelModel<Angel>> {

		public AngelFaceLayer(RenderLayerParent<Angel, AngelModel<Angel>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			AngelModel<Angel> face = new AngelModel<>(manager.bakeLayer(RisusModelLayers.ANGEL_FACE));
		}
		@Override
		public RenderType renderType() {
			return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/angel_eye.png"));
		}
	}
}
