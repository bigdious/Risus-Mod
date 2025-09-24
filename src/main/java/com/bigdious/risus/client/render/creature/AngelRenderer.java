package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.AngelModel;
import com.bigdious.risus.client.render.layer.OphanimFaceLayer;
import com.bigdious.risus.entity.creatures.Angel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AngelRenderer extends MobRenderer<Angel, AngelModel<Angel>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/ophanim.png");

	public AngelRenderer(EntityRendererProvider.Context context) {
		super(context, new AngelModel<>(context.bakeLayer(RisusModelLayers.ANGEL)), 0.0F);
		this.addLayer(new OphanimFaceLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Angel angel) {
		return TEXTURE;
	}
}
