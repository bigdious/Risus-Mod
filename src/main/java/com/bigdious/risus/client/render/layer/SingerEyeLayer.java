package com.bigdious.risus.client.render.layer;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.entity.SingerModel;
import com.bigdious.risus.client.model.entity.StalkerModel;
import com.bigdious.risus.entity.Singer;
import com.bigdious.risus.entity.Stalker;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class SingerEyeLayer<T extends Singer, M extends SingerModel<T>> extends EyesLayer<T, M> {
	public SingerEyeLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/singer_eye.png"));
	}
}

