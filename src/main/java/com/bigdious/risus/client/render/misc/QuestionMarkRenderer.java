package com.bigdious.risus.client.render.misc;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.QuestionMarkModel;
import com.bigdious.risus.client.render.layer.AbsoluteWhiteLayer;
import com.bigdious.risus.entity.QuestionMark;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class QuestionMarkRenderer extends MobRenderer<QuestionMark, QuestionMarkModel<QuestionMark>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/question_mark.png");
	protected static final ResourceLocation WHITE_TEXTURE = Risus.prefix("textures/entity/question_mark_white.png");

	public QuestionMarkRenderer(EntityRendererProvider.Context context) {
		super(context, new QuestionMarkModel<>(context.bakeLayer(RisusModelLayers.QUESTION_MARK)), 0.0F);
		this.addLayer(new AbsoluteWhiteLayer<>(this, WHITE_TEXTURE));
	}

	@Override
	protected void scale(QuestionMark questionMark, PoseStack stack, float partialTicks) {
		stack.scale(2F, 2F, 2F);
	}

	@Override
	protected int getBlockLightLevel(QuestionMark entity, BlockPos pos) {
		return 0;
	}

	@Override
	public ResourceLocation getTextureLocation(QuestionMark questionMark) {
		return TEXTURE;
	}
}
