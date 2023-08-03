package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.block.BloodWyrmHeadModel;
import com.bigdious.risus.client.model.entity.*;
import com.bigdious.risus.client.render.*;
import com.bigdious.risus.entity.RisusBoat;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.util.RisusSkullType;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Risus.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class RisusModelLayers {

	public static final ModelLayerLocation ANGEL = register("angel");
	public static final ModelLayerLocation HOLDER = register("holder");
	public static final ModelLayerLocation MAW = register("maw");
	public static final ModelLayerLocation QUESTION_MARK = register("question_mark");
	public static final ModelLayerLocation THROWN_AXE = register("thrown_axe");
	public static final ModelLayerLocation WEAVER = register("weaver");
	public static final ModelLayerLocation WEAVER_CORE = register("weaver", "core");

	public static final ModelLayerLocation MEMORY1 = register("memory1");

	public static final ModelLayerLocation BLOODWYRM_HEAD = register("bloodwyrm_head");

	private static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	private static ModelLayerLocation register(String name, String type) {
		return new ModelLayerLocation(new ResourceLocation(Risus.MODID, name), type);
	}

	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (RisusBoat.Type boatType : RisusBoat.Type.values()) {
			event.registerLayerDefinition(RisusBoatRenderer.createBoatModelName(boatType), () -> BoatModel.createBodyModel(false));
		}
		event.registerLayerDefinition(ANGEL, AngelModel::create);
		event.registerLayerDefinition(HOLDER, HolderModel::create);
		event.registerLayerDefinition(MAW, MawModel::create);
		event.registerLayerDefinition(THROWN_AXE, ThrownAxeModel::create);
		event.registerLayerDefinition(WEAVER, WeaverModel::create);
		event.registerLayerDefinition(WEAVER_CORE, WeaverModel::create);
		event.registerLayerDefinition(BLOODWYRM_HEAD, BloodWyrmHeadModel::create);
		event.registerLayerDefinition(QUESTION_MARK, QuestionMarkModel::create);
		event.registerLayerDefinition(MEMORY1, Memory1Model::create);
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
		event.registerEntityRenderer(RisusEntities.THROWN_AXE.get(), ThrownAxeRenderer::new);
		event.registerEntityRenderer(RisusEntities.WEAVER.get(), WeaverRenderer::new);
		event.registerEntityRenderer(RisusEntities.QUESTION_MARK.get(), QuestionMarkRenderer::new);
		event.registerEntityRenderer(RisusEntities.MEMORY1.get(), Memory1Renderer::new);

		event.registerEntityRenderer(RisusEntities.BOAT.get(), RisusBoatRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_SKULL.get(), SkullBlockRenderer::new);
	}
}
