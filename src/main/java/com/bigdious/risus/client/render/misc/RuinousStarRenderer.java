package com.bigdious.risus.client.render.misc;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.RuinousStarModel;
import com.bigdious.risus.client.render.MultiLayerBasicEntityRenderer;
import com.bigdious.risus.entity.RaidCoreEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class RuinousStarRenderer extends MultiLayerBasicEntityRenderer<RaidCoreEntity, RuinousStarModel<RaidCoreEntity>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/ruinous_star.png");


	public RuinousStarRenderer(EntityRendererProvider.Context context) {
		super(context, new RuinousStarModel<>(context.bakeLayer(RisusModelLayers.RUINOUS_STAR)));
		this.addLayer(new GlowLayer(this, context));
	}

	@Override
	public ResourceLocation getTextureLocation(RaidCoreEntity raidCoreEntity) {return TEXTURE;}

	static class GlowLayer extends EyesLayer<RaidCoreEntity, RuinousStarModel<RaidCoreEntity>> {

		public GlowLayer(RenderLayerParent<RaidCoreEntity, RuinousStarModel<RaidCoreEntity>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			RuinousStarModel<RaidCoreEntity> glow = new RuinousStarModel<>(manager.bakeLayer(RisusModelLayers.RUINOUS_STAR_GLOW));
		}
		@Override
		public RenderType renderType() {
			return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/ruinous_star_glow.png"));
		}
	}
}
