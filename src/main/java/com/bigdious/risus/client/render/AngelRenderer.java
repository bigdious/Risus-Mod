package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.AngelModel;
import com.bigdious.risus.entities.AngelEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AngelRenderer extends MobRenderer<AngelEntity, AngelModel<AngelEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Risus.MOD_ID, "textures/entity/angel.png");

    public AngelRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AngelModel<>(), 0.8F);
    }

    @Override
    public ResourceLocation getEntityTexture(AngelEntity entity) {
        return TEXTURE;
    }
    }


