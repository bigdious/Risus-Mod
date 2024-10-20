package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class RisusModelLayers {
	public static final ModelLayerLocation ANGEL = register("angel");
	public static final ModelLayerLocation HOLDER = register("holder");
	public static final ModelLayerLocation MAW = register("maw");
	public static final ModelLayerLocation QUESTION_MARK = register("question_mark");
	public static final ModelLayerLocation THROWN_AXE = register("thrown_axe");
	public static final ModelLayerLocation BLOODWYRM_BREATH = register("bloodwyrm_breath");
	public static final ModelLayerLocation WEAVER = register("weaver");
	public static final ModelLayerLocation LOVER = register("lover");
	public static final ModelLayerLocation STALKER = register("stalker");
	public static final ModelLayerLocation WEAVER_CORE = register("weaver", "core");

	public static final ModelLayerLocation MEMORY1 = register("memory1");
	public static final ModelLayerLocation DEPTH_VASE = register("depth_vase");

	public static final ModelLayerLocation BLOODWYRM_HEAD = register("bloodwyrm_head");
	public static final ModelLayerLocation GUTS_BOAT = register("guts_boat");

	private static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	private static ModelLayerLocation register(String name, String type) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name), type);
	}
}
