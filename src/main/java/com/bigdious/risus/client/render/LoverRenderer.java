package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.LoverModel;
import com.bigdious.risus.entity.Lover;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LoverRenderer extends MobRenderer<Lover, LoverModel<Lover>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/lover.png");

	public LoverRenderer(EntityRendererProvider.Context context) {
		super(context, new LoverModel<>(context.bakeLayer(RisusModelLayers.LOVER)), 0.0F);
	}

	@Override
	protected float getFlipDegrees(Lover lover) {
		return 0.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(Lover lover) {
		return TEXTURE;
	}
}
