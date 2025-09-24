package com.bigdious.risus.client.render.layer;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.entity.QuestionMarkModel;
import com.bigdious.risus.entity.QuestionMark;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class AbsoluteWhiteLayer<T extends QuestionMark, M extends QuestionMarkModel<T>> extends EyesLayer<T, M> {
	public AbsoluteWhiteLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return RenderType.beaconBeam(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/question_mark_white.png"), false);
	}
}

