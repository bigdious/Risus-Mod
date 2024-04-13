package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.projectile.BloodwyrmBreathEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BloodwyrmBreathEntityRenderer extends EntityRenderer<BloodwyrmBreathEntity> {
	public static final ResourceLocation BREATH_LOCATION = Risus.prefix("textures/entity/breath.png");

	public BloodwyrmBreathEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
	}


	public ResourceLocation getTextureLocation(BloodwyrmBreathEntity breath) {
		return BREATH_LOCATION;
	}
}
