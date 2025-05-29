package com.bigdious.risus.client.model.entity.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;

public interface HandAnimHelper {

	@SafeVarargs
	@SuppressWarnings("unchecked")
	static void followBodyRotations(final LivingEntity livingEntity, final HumanoidModel<LivingEntity>... models) {
		LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> render = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(livingEntity);
			EntityModel<LivingEntity> entityModel = render.getModel();
			if (entityModel instanceof HumanoidModel) {
				for(HumanoidModel<LivingEntity> model : models) {
					HumanoidModel<LivingEntity> bipedModel = (HumanoidModel)entityModel;
					bipedModel.copyPropertiesTo(model);
				}
			}

	}
}
