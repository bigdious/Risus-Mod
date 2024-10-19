package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.block.BloodWyrmHeadModel;
import com.bigdious.risus.client.model.entity.*;
import com.bigdious.risus.client.particle.*;
import com.bigdious.risus.client.render.*;
import com.bigdious.risus.client.render.layer.AngelWingsLayer;
import com.bigdious.risus.entity.RisusBoat;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.*;
import com.bigdious.risus.util.RisusSkullType;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.List;

public class RisusClientEvents {

	private static final RenderType MONOLITH_PORTAL = RenderType.create("risus:monolith_portal", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 256, true, false, RenderType.CompositeState.builder().setShaderState(RenderStateAccessor.getEndPortal()).setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(TheEndPortalRenderer.END_SKY_LOCATION, false, false).add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false).build()).createCompositeState(false));

	public static void initEvents(IEventBus bus) {
		bus.addListener(RisusClientEvents::clientSetup);
		bus.addListener(RisusClientEvents::registerParticleFactories);
		bus.addListener(RisusClientEvents::registerEntityLayers);
		bus.addListener(RisusClientEvents::registerSkullModel);
		bus.addListener(RisusClientEvents::registerEntityRenderers);
		bus.addListener(RisusClientEvents::registerScreens);
		bus.addListener(RisusClientEvents::registerBlockColors);
		bus.addListener(RisusClientEvents::registerClientExtensions);

		NeoForge.EVENT_BUS.addListener(RisusClientEvents::killScreenWithAmnesia);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::killHandWithAmnesia);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::renderExburnHearts);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::renderBloodcloggedHearts);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::renderExBurning);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(RisusFluids.SOURCE_BLOOD.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(RisusFluids.FLOWING_BLOOD.get(), RenderType.translucent());

		event.enqueueWork(() -> {
			SkullBlockRenderer.SKIN_BY_TYPE.put(RisusSkullType.BLOODWYRM, Risus.prefix("textures/entity/bloodwyrm_head.png"));

			Sheets.addWoodType(RisusBlocks.BONDKNOT_TYPE);
		});
//		registerElytraLayer();
	}

	private static void registerParticleFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(RisusParticles.ALTERATION.get(), AlterationParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.ALTERATION_FINISHED.get(), AlterationFinishedParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.DRIPPING_JOY.get(), JoyParticle.JoyHangProvider::new);
		event.registerSpriteSet(RisusParticles.FALLING_JOY.get(), JoyParticle.JoyFallProvider::new);
		event.registerSpriteSet(RisusParticles.JOYFLAME.get(), FlameParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.LANDING_JOY.get(), JoyParticle.JoyLandProvider::new);
		event.registerSpriteSet(RisusParticles.TOOTHICAL.get(), ToothicalParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.RISUS_SOUL_PARTICLE.get(), RisusSoulParticle.EmissiveProvider::new);
		event.registerSpriteSet(RisusParticles.RISUS_SOUL_PARTICLE.get(), RisusSoulParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), FieryOrganicParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.BLOCK_ORGANIC_PARTICLE.get(), BlockOrganicParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.BLOOD.get(), BloodParticle.Factory::new);
		event.registerSpriteSet(RisusParticles.BLOOD_BIT.get(), BloodBitParticle.Factory::new);
	}

	private static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(RisusMenuType.MAW_GUTS.get(), MawGutsScreen::new);
	}

	private static void registerRenderTypes(RegisterNamedRenderTypesEvent event) {
		//TODO must wait on forge to allow us to add custom render types to the chunk buffer
		//event.register("monolith_portal", MONOLITH_PORTAL, RenderType.entitySolid(TheEndGatewayRenderer.END_PORTAL_LOCATION));
	}


	private static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, getter, pos, i) -> getter != null && pos != null ? BiomeColors.getAverageGrassColor(getter, pos) : GrassColor.get(0.5D, 1.0D), RisusBlocks.MIRAGE_GRASS_BLOCK.get());
	}

	private static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (RisusBoat.Type boatType : RisusBoat.Type.values()) {
			event.registerLayerDefinition(RisusBoatRenderer.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(RisusBoatRenderer.createGutsBoatModelName(boatType), GutsBoatModel::createBodyModel);
		}
		event.registerLayerDefinition(RisusModelLayers.ANGEL, AngelModel::create);
		event.registerLayerDefinition(RisusModelLayers.HOLDER, HolderModel::create);
		event.registerLayerDefinition(RisusModelLayers.MAW, MawModel::create);
		event.registerLayerDefinition(RisusModelLayers.THROWN_AXE, ThrownAxeModel::create);
		event.registerLayerDefinition(RisusModelLayers.WEAVER, WeaverModel::create);
		event.registerLayerDefinition(RisusModelLayers.WEAVER_CORE, WeaverModel::create);
		event.registerLayerDefinition(RisusModelLayers.LOVER, LoverModel::create);
		event.registerLayerDefinition(RisusModelLayers.STALKER, StalkerModel::create);
		event.registerLayerDefinition(RisusModelLayers.BLOODWYRM_HEAD, BloodWyrmHeadModel::create);
		event.registerLayerDefinition(RisusModelLayers.QUESTION_MARK, QuestionMarkModel::create);
		event.registerLayerDefinition(RisusModelLayers.MEMORY1, Memory1Model::create);
	}

	private static void registerSkullModel(EntityRenderersEvent.CreateSkullModels event) {
		event.registerSkullModel(RisusSkullType.BLOODWYRM, new BloodWyrmHeadModel(event.getEntityModelSet().bakeLayer(RisusModelLayers.BLOODWYRM_HEAD)));
	}

	private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RisusEntities.ANGEL.get(), AngelRenderer::new);
		event.registerEntityRenderer(RisusEntities.HOLDER.get(), HolderRenderer::new);
		event.registerEntityRenderer(RisusEntities.MAW.get(), MawRenderer::new);
		event.registerEntityRenderer(RisusEntities.THROWN_AXE.get(), ThrownAxeRenderer::new);
		event.registerEntityRenderer(RisusEntities.BLOODWYRM_BREATH.get(), BloodwyrmBreathEntityRenderer::new);
		event.registerEntityRenderer(RisusEntities.WEAVER.get(), WeaverRenderer::new);
		event.registerEntityRenderer(RisusEntities.LOVER.get(), LoverRenderer::new);
		event.registerEntityRenderer(RisusEntities.STALKER.get(), StalkerRenderer::new);
		event.registerEntityRenderer(RisusEntities.QUESTION_MARK.get(), QuestionMarkRenderer::new);
		event.registerEntityRenderer(RisusEntities.TRANSIENT_QUESTION_MARK.get(), QuestionMarkRenderer::new);
		event.registerEntityRenderer(RisusEntities.MEMORY1.get(), Memory1Renderer::new);

		event.registerEntityRenderer(RisusEntities.BOAT.get(), (context) -> new RisusBoatRenderer(context, false));
		event.registerEntityRenderer(RisusEntities.GUTS_BOAT.get(), (context) -> new RisusBoatRenderer(context, true));
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_SKULL.get(), SkullBlockRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_SIGN.get(), SignRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_HANGING_SIGN.get(), HangingSignRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_CAMPFIRE.get(), CampfireRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.ALTERATION_CATALYST.get(), AlterationCatalystRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.DEPTH_VASE.get(), context -> new DepthVaseRenderer());
		event.registerBlockEntityRenderer(RisusBlockEntities.DISPLAY_NOTCH.get(), DisplayNotchRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.DISPLAY_NOTCH_STAND.get(), DisplayNotchStandRenderer::new);
	}
	private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			@Override
			public ResourceLocation getStillTexture() {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "block/blood_still");
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "block/blood_flow");
			}

			@Override
			public ResourceLocation getOverlayTexture() {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "block/blood_flow");
			}

			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/misc/blood_fluid_block.png");
			}
			@Override
			public int getTintColor() {
				return 0xFFE60E07;
			}

			@Override
			public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
				return new Vector3f(54f / 255f, 4f / 255f, 4f / 255f);
			}

			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(0f);
				RenderSystem.setShaderFogEnd(6f);
			}
		}, RisusFluids.BLOOD_FLUID_TYPE.get());
	}


	private static void killScreenWithAmnesia(RenderGuiLayerEvent.Pre event) {
		if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(RisusMobEffects.AMNESIA)) {
			event.setCanceled(true);
		}
	}

	private static void killHandWithAmnesia(RenderHandEvent event) {
		if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(RisusMobEffects.AMNESIA)) {
			event.setCanceled(true);
		}
	}

	private static void renderExburnHearts(PlayerHeartTypeEvent event) {
		if (event.getEntity().hasEffect(RisusMobEffects.EXBURN)) {
			event.setType(Gui.HeartType.valueOf("RISUS_EXBURN"));
		}
	}

	private static void renderBloodcloggedHearts(PlayerHeartTypeEvent event) {
		if (event.getEntity().hasEffect(RisusMobEffects.BLOODCLOGGED)) {
			event.setType(Gui.HeartType.valueOf("RISUS_BLOODCLOGGED"));
		}
	}

	//doesn't work... But should it?
    private static void renderExBurning(RenderBlockScreenEffectEvent event) {
		if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(RisusMobEffects.EXBURN)) {
			ClientHooks.renderBlockOverlay(Minecraft.getInstance().player, event.getPoseStack(), RenderBlockScreenEffectEvent.OverlayType.FIRE, RisusBlocks.JOYFLAME_FIRE.get().defaultBlockState(), Minecraft.getInstance().player.blockPosition());
		}
	}


//	public static void registerElytraLayer()
//	{
//		Minecraft mc = Minecraft.getInstance();
//		mc.getEntityRenderDispatcher().getSkinMap().values()
//			.forEach(player -> ((LivingEntityRenderer) player).addLayer(new AngelWingsLayer((LivingEntityRenderer) player, mc.getEntityModels())));
//	}


	public static class RenderStateAccessor extends RenderStateShard {

		public RenderStateAccessor(String p_110161_, Runnable p_110162_, Runnable p_110163_) {
			super(p_110161_, p_110162_, p_110163_);
		}

		public static ShaderStateShard getEndPortal() {
			return RENDERTYPE_END_PORTAL_SHADER;
		}
	}
}
