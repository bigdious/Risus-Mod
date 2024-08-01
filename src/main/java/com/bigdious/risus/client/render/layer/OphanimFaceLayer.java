package com.bigdious.risus.client.render.layer;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.entity.AngelModel;
import com.bigdious.risus.client.model.entity.StalkerModel;
import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.entity.Stalker;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class OphanimFaceLayer<T extends Angel, M extends AngelModel<T>> extends EyesLayer<T, M> {
	public OphanimFaceLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/ophanim_face.png"));
	}
}

