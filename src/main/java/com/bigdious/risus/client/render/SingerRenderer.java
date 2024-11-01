package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.LickerModel;
import com.bigdious.risus.client.model.entity.SingerModel;
import com.bigdious.risus.client.render.layer.SingerEyeLayer;
import com.bigdious.risus.client.render.layer.StalkerEyeLayer;
import com.bigdious.risus.entity.Licker;
import com.bigdious.risus.entity.Singer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SingerRenderer extends MobRenderer<Singer, SingerModel<Singer>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/singer.png");

	public SingerRenderer(EntityRendererProvider.Context context) {
		super(context, new SingerModel<>(context.bakeLayer(RisusModelLayers.SINGER)), 0.0F);
		this.addLayer(new SingerEyeLayer<>(this));
	}


	@Override
	protected float getFlipDegrees(Singer singer) {
		return 0.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(Singer singer) {
		return TEXTURE;
	}
}
