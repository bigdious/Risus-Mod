package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.StalkerModel;
import com.bigdious.risus.client.render.layer.StalkerEyeLayer;
import com.bigdious.risus.entity.creatures.Stalker;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StalkerRenderer extends MobRenderer<Stalker, StalkerModel<Stalker>> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/stalker.png");

	public StalkerRenderer(EntityRendererProvider.Context context) {
		super(context, new StalkerModel<>(context.bakeLayer(RisusModelLayers.STALKER)), 0.0F);
		this.addLayer(new StalkerEyeLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Stalker stalker) {
		return TEXTURE;
	}
}

