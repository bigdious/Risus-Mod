package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.block.BloodWyrmHeadModel;
import com.bigdious.risus.client.model.entity.AngelModel;
import com.bigdious.risus.client.model.entity.HolderModel;
import com.bigdious.risus.client.model.entity.MawModel;
import com.bigdious.risus.client.model.entity.WeaverModel;
import com.bigdious.risus.client.render.AngelRenderer;
import com.bigdious.risus.client.render.HolderRenderer;
import com.bigdious.risus.client.render.MawRenderer;
import com.bigdious.risus.client.render.WeaverRenderer;
import com.bigdious.risus.entity.Weaver;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.util.RisusSkullType;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Risus.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class RisusModelLayers {

	public static final ModelLayerLocation ANGEL = register("angel");
	public static final ModelLayerLocation HOLDER = register("holder");
	public static final ModelLayerLocation MAW = register("maw");
	public static final ModelLayerLocation WEAVER = register("weaver");

	public static final ModelLayerLocation BLOODWYRM_HEAD = register("bloodwyrm_head");

	private static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	private static ModelLayerLocation register(String name, String type) {
		return new ModelLayerLocation(new ResourceLocation(Risus.MODID, name), type);
	}

	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ANGEL, AngelModel::create);
		event.registerLayerDefinition(HOLDER, HolderModel::create);
		event.registerLayerDefinition(MAW, MawModel::create);
		event.registerLayerDefinition(WEAVER, WeaverModel::create);
		event.registerLayerDefinition(BLOODWYRM_HEAD, BloodWyrmHeadModel::create);
	}

	@SubscribeEvent
	static void registerSkullModel(EntityRenderersEvent.CreateSkullModels event) {
		event.registerSkullModel(RisusSkullType.BLOODWYRM, new BloodWyrmHeadModel(event.getEntityModelSet().bakeLayer(BLOODWYRM_HEAD)));
	}

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RisusEntities.ANGEL.get(), AngelRenderer::new);
		event.registerEntityRenderer(RisusEntities.HOLDER.get(), HolderRenderer::new);
		event.registerEntityRenderer(RisusEntities.MAW.get(), MawRenderer::new);
		event.registerEntityRenderer(RisusEntities.WEAVER.get(), WeaverRenderer::new);

		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_SKULL.get(), SkullBlockRenderer::new);
	}
}
