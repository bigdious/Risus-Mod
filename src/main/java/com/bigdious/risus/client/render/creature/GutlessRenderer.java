package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.GutlessModel;
import com.bigdious.risus.entity.creatures.Gutless;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class GutlessRenderer extends MobRenderer<Gutless, GutlessModel> {
	private static final ResourceLocation GUTLESS_LOCATION = Risus.prefix("textures/entity/gutless.png");

	public GutlessRenderer(EntityRendererProvider.Context context) {
		super(context, new GutlessModel(context.bakeLayer(RisusModelLayers.GUTLESS)), 0.0F);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	public ResourceLocation getTextureLocation(Gutless entity) {
		return GUTLESS_LOCATION;
	}
}
