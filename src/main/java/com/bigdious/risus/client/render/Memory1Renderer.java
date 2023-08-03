package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;

import com.bigdious.risus.client.model.entity.Memory1Model;
import com.bigdious.risus.entity.Memory1;
import com.bigdious.risus.entity.QuestionMark;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Memory1Renderer extends MobRenderer<Memory1, Memory1Model<Memory1>> {
    protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/memory1.png");

    public Memory1Renderer(EntityRendererProvider.Context context) {
        super(context, new Memory1Model<>(context.bakeLayer(RisusModelLayers.MEMORY1)), 0.0F);
    }
    @Override
    public ResourceLocation getTextureLocation(Memory1 memory1) {
        return TEXTURE;
    }
}
