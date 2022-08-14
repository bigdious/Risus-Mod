package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.AngelModel;
import com.bigdious.risus.client.model.entity.MawModel;
import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.entity.Maw;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MawRenderer extends MobRenderer<Maw, MawModel<Maw>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/maw.png");

	public MawRenderer(EntityRendererProvider.Context context) {
		super(context, new MawModel<>(context.bakeLayer(RisusModelLayers.MAW)), 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(Maw maw) {
		return TEXTURE;
	}
}
