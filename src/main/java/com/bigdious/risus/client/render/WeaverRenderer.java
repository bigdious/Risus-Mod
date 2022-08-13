package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.WeaverModel;
import com.bigdious.risus.entity.Weaver;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WeaverRenderer extends MobRenderer<Weaver, WeaverModel<Weaver>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/weaver.png");

	public WeaverRenderer(EntityRendererProvider.Context context) {
		super(context, new WeaverModel<>(context.bakeLayer(RisusModelLayers.WEAVER)), 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(Weaver weaver) {
		return TEXTURE;
	}
}