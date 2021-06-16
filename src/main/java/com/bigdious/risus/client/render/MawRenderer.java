package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.MawModel;
import com.bigdious.risus.entity.MawEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MawRenderer extends MobRenderer<MawEntity, MawModel<MawEntity>>{
    protected static final ResourceLocation TEXTURE = Risus.risusRL("textures/entity/maw.png");

    public MawRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MawModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getEntityTexture(MawEntity entity) {
        return TEXTURE;
    }
}
