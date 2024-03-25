package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.StalkerModel;
import com.bigdious.risus.entity.Stalker;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StalkerRenderer extends MobRenderer<Stalker, StalkerModel<Stalker>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/stalker.png");

	public StalkerRenderer(EntityRendererProvider.Context context) {
		super(context, new StalkerModel<>(context.bakeLayer(RisusModelLayers.STALKER)), 0.0F);
	}

	@Override
	protected float getFlipDegrees(Stalker stalker) {
		return 0.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(Stalker stalker) {
		return TEXTURE;
	}
}

