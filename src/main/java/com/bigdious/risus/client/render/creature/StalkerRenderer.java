package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.StalkerModel;
import com.bigdious.risus.entity.creatures.Stalker;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class StalkerRenderer extends MobRenderer<Stalker, StalkerModel<Stalker>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/stalker.png");

	public StalkerRenderer(EntityRendererProvider.Context context) {
		super(context, new StalkerModel<>(context.bakeLayer(RisusModelLayers.STALKER)), 0.0F);
		this.addLayer(new StalkerEyeLayer(this, context));
	}

	@Override
	public ResourceLocation getTextureLocation(Stalker stalker) {
		return TEXTURE;
	}


	static class StalkerEyeLayer extends EyesLayer<Stalker, StalkerModel<Stalker>> {

		public StalkerEyeLayer(RenderLayerParent<Stalker, StalkerModel<Stalker>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			StalkerModel<Stalker> eye = new StalkerModel<>(manager.bakeLayer(RisusModelLayers.STALKER_EYE));
		}
		@Override
		public RenderType renderType() {
			return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/stalker_eye.png"));
		}
	}

}

