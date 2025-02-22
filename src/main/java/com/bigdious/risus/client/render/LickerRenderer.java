package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.LickerModel;
import com.bigdious.risus.entity.Licker;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LickerRenderer extends MobRenderer<Licker, LickerModel<Licker>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/licker.png");

	public LickerRenderer(EntityRendererProvider.Context context) {
		super(context, new LickerModel<>(context.bakeLayer(RisusModelLayers.LICKER)), 0.0F);
	}


	@Override
	protected float getFlipDegrees(Licker licker) {
		return 180.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(Licker licker) {
		return TEXTURE;
	}
}
