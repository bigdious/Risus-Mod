package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.QuestionMarkModel;
import com.bigdious.risus.entity.QuestionMark;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class QuestionMarkRenderer extends MobRenderer<QuestionMark, QuestionMarkModel<QuestionMark>> {

    protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/question_mark.png");

    public QuestionMarkRenderer(EntityRendererProvider.Context context) {
        super(context, new QuestionMarkModel<>(context.bakeLayer(RisusModelLayers.QUESTION_MARK)), 0.0F);
    }


    @Override
    public ResourceLocation getTextureLocation(QuestionMark questionMark) {
        return TEXTURE;
    }
}