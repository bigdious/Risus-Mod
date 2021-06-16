package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.HolderModel;
import com.bigdious.risus.entity.HolderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HolderRenderer extends MobRenderer<HolderEntity, HolderModel<HolderEntity>> {

    protected static final ResourceLocation TEXTURE = Risus.risusRL("textures/entity/holder.png");

    public HolderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HolderModel<>(), 0.0F);
    }

    @Override
    public ResourceLocation getEntityTexture(HolderEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(HolderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.75F, 0.75F, 0.75F);
     }

}

