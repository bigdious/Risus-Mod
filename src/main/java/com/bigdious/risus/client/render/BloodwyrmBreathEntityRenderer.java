package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.BloodwyrmBreathEntityModel;
import com.bigdious.risus.client.model.entity.ThrownAxeModel;
import com.bigdious.risus.entity.projectile.BloodwyrmBreathEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import org.joml.Quaternionf;

import javax.annotation.Nonnull;

public class BloodwyrmBreathEntityRenderer extends EntityRenderer<BloodwyrmBreathEntity> {
	public static final ResourceLocation BREATH_LOCATION = Risus.prefix("textures/entity/breath.png");

	public BloodwyrmBreathEntityRenderer(EntityRendererProvider.Context context)
	{
		super(context);
	}


	public ResourceLocation getTextureLocation(BloodwyrmBreathEntity breath)
	{
		return BREATH_LOCATION;
	}
}
