package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.GreatPreacherModel;
import com.bigdious.risus.entity.cultists.GreatPreacher;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class GreatPreacherRenderer extends MobRenderer<GreatPreacher, GreatPreacherModel<GreatPreacher>> {
	private static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/great_preacher.png");

	public GreatPreacherRenderer(EntityRendererProvider.Context context) {
		super(context, new GreatPreacherModel<>(context.bakeLayer(RisusModelLayers.GREAT_PREACHER)), 0.0F);
		this.addLayer(new AppendagesLayer(this, context));
	}

	public ResourceLocation getTextureLocation(GreatPreacher entity) {
		return TEXTURE;
	}

	static class AppendagesLayer extends EyesLayer<GreatPreacher, GreatPreacherModel<GreatPreacher>> {

		public AppendagesLayer(RenderLayerParent<GreatPreacher, GreatPreacherModel<GreatPreacher>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			GreatPreacherModel<GreatPreacher> appendages = new GreatPreacherModel<>(manager.bakeLayer(RisusModelLayers.GREAT_PREACHER_APPENDAGES));
		}

		@Override
		public RenderType renderType() {
			return RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/great_preacher_appendages.png"));
		}

	}
}

