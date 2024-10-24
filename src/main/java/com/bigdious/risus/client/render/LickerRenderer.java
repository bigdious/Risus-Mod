package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.LickerModel;
import com.bigdious.risus.client.model.entity.LoverModel;
import com.bigdious.risus.entity.Licker;
import com.bigdious.risus.entity.Lover;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LickerRenderer extends MobRenderer<Licker, LickerModel<Licker>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/licker.png");

	public LickerRenderer(EntityRendererProvider.Context context) {
		super(context, new LickerModel<>(context.bakeLayer(RisusModelLayers.LICKER)), 0.0F);
	}


	@Override
	protected float getFlipDegrees(Licker licker) {
		return 0.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(Licker licker) {
		return TEXTURE;
	}
}
