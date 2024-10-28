package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.BabySpiderModel;
import com.bigdious.risus.client.model.entity.LickerModel;
import com.bigdious.risus.entity.BabySpider;
import com.bigdious.risus.entity.Licker;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabySpiderRenderer extends MobRenderer<BabySpider, BabySpiderModel<BabySpider>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/baby_spider.png");

	public BabySpiderRenderer(EntityRendererProvider.Context context) {
		super(context, new BabySpiderModel<>(context.bakeLayer(RisusModelLayers.BABY_SPIDER)), 0.0F);
	}


	@Override
	protected float getFlipDegrees(BabySpider babySpider) {
		return 0.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(BabySpider babySpider) {
		return TEXTURE;
	}
}
