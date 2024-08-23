package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.model.block.BloodWyrmHeadModel;
import com.bigdious.risus.client.model.entity.*;
import com.bigdious.risus.client.particle.*;
import com.bigdious.risus.client.render.*;
import com.bigdious.risus.entity.RisusBoat;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.*;
import com.bigdious.risus.util.RisusSkullType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GrassColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;

public class RisusClientEvents {

	private static final RenderType MONOLITH_PORTAL = RenderType.create("risus:monolith_portal", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 256, true, false, RenderType.CompositeState.builder().setShaderState(RenderStateAccessor.getEndPortal()).setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(TheEndPortalRenderer.END_SKY_LOCATION, false, false).add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false).build()).createCompositeState(false));
	private static final List<ResourceLocation> HEARTS = List.of(
			Risus.prefix("hearts/exburn_normal"),
			Risus.prefix("hearts/exburn_normal_blinking"),
			Risus.prefix("hearts/exburn_half"),
			Risus.prefix("hearts/exburn_half_blinking"),
			Risus.prefix("hearts/exburn_hardcore"),
			Risus.prefix("hearts/exburn_hardcore_blinking"),
			Risus.prefix("hearts/exburn_hardcore_half"),
			Risus.prefix("hearts/exburn_hardcore_half_blinking")
	);

	public static void initEvents(IEventBus bus) {
		bus.addListener(RisusClientEvents::clientSetup);
		bus.addListener(RisusClientEvents::registerParticleFactories);
		bus.addListener(RisusClientEvents::registerEntityLayers);
		bus.addListener(RisusClientEvents::registerSkullModel);
		bus.addListener(RisusClientEvents::registerEntityRenderers);
		bus.addListener(RisusClientEvents::registerOverlays);
		bus.addListener(RisusClientEvents::registerScreens);
		bus.addListener(RisusClientEvents::registerBlockColors);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::killScreenWithAmnesia);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::killHandWithAmnesia);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(RisusFluids.SOURCE_BLOOD.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(RisusFluids.FLOWING_BLOOD.get(), RenderType.translucent());

		event.enqueueWork(() -> {
			SkullBlockRenderer.SKIN_BY_TYPE.put(RisusSkullType.BLOODWYRM, Risus.prefix("textures/entity/bloodwyrm_head.png"));

			Sheets.addWoodType(RisusBlocks.BONDKNOT_TYPE);
		});
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
		event.registerSpriteSet(RisusParticles.ORGANIC_PARTICLE.get(), FieryOrganicParticle.Provider::new);
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

	private static void registerOverlays(RegisterGuiLayersEvent event) {
		event.registerAbove(VanillaGuiLayers.PLAYER_HEALTH, Risus.prefix("exburn_hearts"), (gui, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.hasEffect(RisusMobEffects.EXBURN) && minecraft.gameMode.canHurtPlayer()) {
				renderExburnHearts(gui.guiWidth(), gui.guiHeight(),  gui, minecraft.gui, player);
			}
		});
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

	private static void renderExburnHearts(int width, int height, GuiGraphics graphics, Gui gui, Player player) {
		int health = Mth.ceil(player.getHealth());
		boolean highlight = gui.healthBlinkTime > (long) gui.getGuiTicks() && (gui.healthBlinkTime - (long) gui.getGuiTicks()) / 3L % 2L == 1L;

		if (health < gui.lastHealth && player.invulnerableTime > 0) {
			gui.lastHealthTime = Util.getMillis();
			gui.healthBlinkTime = gui.getGuiTicks() + 20;
		} else if (health > gui.lastHealth && player.invulnerableTime > 0) {
			gui.lastHealthTime = Util.getMillis();
			gui.healthBlinkTime = gui.getGuiTicks() + 10;
		}

		if (Util.getMillis() - gui.lastHealthTime > 1000L) {
			gui.lastHealth = health;
			gui.displayHealth = health;
			gui.lastHealthTime = Util.getMillis();
		}

		gui.lastHealth = health;
		int healthLast = gui.displayHealth;

		AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
		float healthMax = Math.max((float) attrMaxHealth.getValue(), Math.max(healthLast, health));
		int absorb = Mth.ceil(player.getAbsorptionAmount());

		int healthRows = Mth.ceil((healthMax + absorb) / 2.0F / 10.0F);
		int rowHeight = Math.max(10 - (healthRows - 2), 3);

		//do NOT cast this to long
		gui.random.setSeed(gui.getGuiTicks() * 312871);

		int x = width / 2 - 91;
		int y = height - 39;

		int regen = -1;
		if (player.hasEffect(MobEffects.REGENERATION)) {
			regen = gui.getGuiTicks() % Mth.ceil(healthMax + 5.0F);
		}

		renderHearts(graphics, gui, player, x, y, rowHeight, regen, healthMax, health, healthLast, absorb, highlight);
	}

	private static void renderHearts(GuiGraphics graphics, Gui gui, Player player, int x, int y, int height, int regen, float healthMax, int health, int healthLast, int absorb, boolean highlight) {
		boolean hardcore = player.level().getLevelData().isHardcore();
		int healthAmount = Mth.ceil((double) healthMax / 2.0D);
		int absorptionAmount = Mth.ceil((double) absorb / 2.0D);
		int l = healthAmount * 2;

		for (int i1 = healthAmount + absorptionAmount - 1; i1 >= 0; --i1) {
			int j1 = i1 / 10;
			int k1 = i1 % 10;
			int newX = x + k1 * 8;
			int newY = y - j1 * height;
			if (health + absorb <= 4) {
				newY += gui.random.nextInt(2);
			}

			if (i1 < healthAmount && i1 == regen) {
				newY -= 2;
			}

			renderHeartBG(graphics, newX, newY, hardcore, highlight);
			int j2 = i1 * 2;
			boolean flag = i1 >= healthAmount;
			if (flag) {
				int k2 = j2 - l;
				if (k2 < absorb) {
					boolean half = k2 + 1 == absorb;
					renderExburnHeart(graphics, newX, newY, hardcore, false, half);
				}
			}

			if (highlight && j2 < healthLast) {
				boolean half = j2 + 1 == healthLast;
				renderExburnHeart(graphics, newX, newY, hardcore, true, half);
			}

			if (j2 < health) {
				boolean half = j2 + 1 == health;
				renderExburnHeart(graphics, newX, newY, hardcore, false, half);
			}
		}
	}

	private static void renderHeartBG(GuiGraphics graphics, int x, int y, boolean hardcore, boolean blinking) {
		graphics.blitSprite(Gui.HeartType.CONTAINER.getSprite(hardcore, false, blinking), x, y, 9, 9);
	}

	private static void renderExburnHeart(GuiGraphics graphics, int x, int y, boolean hardcore, boolean blinking, boolean halfHeart) {
		graphics.blitSprite(getExburnHeartSprite(hardcore, halfHeart, blinking), x, y, 9, 9);
	}

	private static ResourceLocation getExburnHeartSprite(boolean hardcore, boolean half, boolean blinking) {
		if (!hardcore) {
			if (half) {
				return blinking ? HEARTS.get(3) : HEARTS.get(2);
			} else {
				return blinking ? HEARTS.get(1) : HEARTS.get(0);
			}
		} else if (half) {
			return blinking ? HEARTS.get(7) : HEARTS.get(6);
		} else {
			return blinking ? HEARTS.get(5) : HEARTS.get(4);
		}
	}

	public static class RenderStateAccessor extends RenderStateShard {

		public RenderStateAccessor(String p_110161_, Runnable p_110162_, Runnable p_110163_) {
			super(p_110161_, p_110162_, p_110163_);
		}

		public static ShaderStateShard getEndPortal() {
			return RENDERTYPE_END_PORTAL_SHADER;
		}
	}
}
