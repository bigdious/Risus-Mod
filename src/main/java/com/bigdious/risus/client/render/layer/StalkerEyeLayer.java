package com.bigdious.risus.client.render.layer;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.entity.HolderModel;
import com.bigdious.risus.client.model.entity.StalkerModel;
import com.bigdious.risus.entity.Holder;
import com.bigdious.risus.entity.Stalker;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class StalkerEyeLayer<T extends Stalker, M extends StalkerModel<T>> extends EyesLayer<T, M> {
	public StalkerEyeLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/stalker_eye.png"));
	}
}

