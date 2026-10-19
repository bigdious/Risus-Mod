package com.bigdious.risus.client.render.misc;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.QuestionMarkModel;
import com.bigdious.risus.client.render.MultiLayerBasicEntityRenderer;
import com.bigdious.risus.entity.QuestionMark;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class QuestionMarkRenderer extends MultiLayerBasicEntityRenderer<QuestionMark, QuestionMarkModel<QuestionMark>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/question_mark.png");

	public QuestionMarkRenderer(EntityRendererProvider.Context context) {
		super(context, new QuestionMarkModel<>(context.bakeLayer(RisusModelLayers.QUESTION_MARK)));
		this.addLayer(new InexistenceLayer(this, context));
	}

	@Override
	protected int getBlockLightLevel(QuestionMark entity, BlockPos pos) {
		return 0;
	}

	@Override
	public ResourceLocation getTextureLocation(QuestionMark questionMark) {
		return TEXTURE;
	}

	static class InexistenceLayer extends EyesLayer<QuestionMark, QuestionMarkModel<QuestionMark>> {

		public InexistenceLayer(RenderLayerParent<QuestionMark, QuestionMarkModel<QuestionMark>> renderer, EntityRendererProvider.Context manager) {
			super(renderer);
			QuestionMarkModel<QuestionMark> inexistence = new QuestionMarkModel<>(manager.bakeLayer(RisusModelLayers.QUESTION_MARK_INEXISTENCE));
		}
		@Override
		public RenderType renderType() {
			return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/question_mark_white.png"));
		}
	}
}
