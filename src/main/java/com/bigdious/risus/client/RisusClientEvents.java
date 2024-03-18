package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.fluid.RisusFluids;
import net.minecraft.client.gui.Gui;
import com.bigdious.risus.client.particle.AlterationFinishedParticle;
import com.bigdious.risus.client.particle.AlterationParticle;
import com.bigdious.risus.client.particle.JoyParticle;
import com.bigdious.risus.client.particle.ToothicalParticle;
import com.bigdious.risus.client.render.AlterationCatalystRenderer;
import com.bigdious.risus.init.*;
import com.bigdious.risus.util.RisusSkullType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.MenuScreens;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import snownee.jade.overlay.OverlayRenderer;
import snownee.jade.overlay.TooltipRenderer;

@EventBusSubscriber(modid = Risus.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class RisusClientEvents {

	private static final RenderType MONOLITH_PORTAL = RenderType.create("risus:monolith_portal", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 256, true, false, RenderType.CompositeState.builder().setShaderState(RenderStateAccessor.getEndPortal()).setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(TheEndPortalRenderer.END_SKY_LOCATION, false, false).add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false).build()).createCompositeState(false));
	private static final ResourceLocation EXBURN_HEARTS = new ResourceLocation(Risus.MODID, "textures/hearts/exburn_hearts_0.png");

	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(RisusParticles.ALTERATION.get(), AlterationParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.ALTERATION_FINISHED.get(), AlterationFinishedParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.DRIPPING_JOY.get(), JoyParticle.JoyHangProvider::new);
		event.registerSpriteSet(RisusParticles.FALLING_JOY.get(), JoyParticle.JoyFallProvider::new);
		event.registerSpriteSet(RisusParticles.JOYFLAME.get(), FlameParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.LANDING_JOY.get(), JoyParticle.JoyLandProvider::new);
		event.registerSpriteSet(RisusParticles.TOOTHICAL.get(), ToothicalParticle.Provider::new);
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(RisusBlockEntities.RISUS_SIGN.get(), SignRenderer::new);
		BlockEntityRenderers.register(RisusBlockEntities.RISUS_HANGING_SIGN.get(), HangingSignRenderer::new);
		BlockEntityRenderers.register(RisusBlockEntities.RISUS_CAMPFIRE.get(), CampfireRenderer::new);
		BlockEntityRenderers.register(RisusBlockEntities.ALTERATION_CATALYST.get(), context ->  new AlterationCatalystRenderer());

		ItemBlockRenderTypes.setRenderLayer(RisusFluids.SOURCE_BLOOD.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(RisusFluids.FLOWING_BLOOD.get(), RenderType.translucent());

		MenuScreens.register(RisusMenuTypes.MAW_GUTS.get(), MawGutsScreen::new);

//		MenuScreens.register(RisusMenuTypes.DEPTH_VASE.get(), DepthVaseScreen::new);

		event.enqueueWork(() -> {
			SkullBlockRenderer.SKIN_BY_TYPE.put(RisusSkullType.BLOODWYRM, Risus.prefix("textures/entity/bloodwyrm_head.png"));

			Sheets.addWoodType(RisusBlocks.BONDKNOT_TYPE);
		});
	}

	@SubscribeEvent
	public static void registerRenderTypes(RegisterNamedRenderTypesEvent event) {
		//TODO must wait on forge to allow us to add custom render types to the chunk buffer
		//event.register("monolith_portal", MONOLITH_PORTAL, RenderType.entitySolid(TheEndGatewayRenderer.END_PORTAL_LOCATION));
	}
	//I'm rendering it here, sorry giz
	@SubscribeEvent
	public static void registerOverlays(RegisterGuiOverlaysEvent event) {
		event.registerAbove(VanillaGuiOverlay.PLAYER_HEALTH.id(), "exburn_hearts", (gui, stack, partialTicks, width, height) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.hasEffect(RisusMobEffects.EXBURN.get()) && gui.shouldDrawSurvivalElements()) {
				renderExburnHearts(width, height, stack, gui, player);
			}
		});
	}

	private static void renderExburnHearts(int width, int height, GuiGraphics graphics, ForgeGui gui, Player player) {
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

	private static void renderHearts(GuiGraphics graphics, ForgeGui gui, Player player, int x, int y, int height, int regen, float healthMax, int health, int healthLast, int absorb, boolean highlight) {
		Gui.HeartType heartType = Gui.HeartType.forPlayer(player);
		int hardcoreOffset = 9 * (player.level().getLevelData().isHardcore() ? 5 : 0);
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

			renderHeart(graphics, Gui.HeartType.CONTAINER, newX, newY, hardcoreOffset, highlight, false);
			int j2 = i1 * 2;
			boolean flag = i1 >= healthAmount;
			if (flag) {
				int k2 = j2 - l;
				if (k2 < absorb) {
					boolean flag1 = k2 + 1 == absorb;
					renderHeart(graphics, heartType == Gui.HeartType.WITHERED ? heartType : Gui.HeartType.ABSORBING, newX, newY, hardcoreOffset, false, flag1);
				}
			}

			if (highlight && j2 < healthLast) {
				boolean flag2 = j2 + 1 == healthLast;
				renderHeart(graphics, heartType, newX, newY, hardcoreOffset, true, flag2);
			}

			if (j2 < health) {
				boolean flag3 = j2 + 1 == health;
				renderHeart(graphics, heartType, newX, newY, hardcoreOffset, false, flag3);
			}
		}
	}

	private static void renderHeart(GuiGraphics graphics, Gui.HeartType type, int x, int y, int offset, boolean blinking, boolean halfHeart) {
		graphics.blit(EXBURN_HEARTS, x, y, type.getX(halfHeart, blinking), offset, 9, 9);
	}


	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, getter, pos, i) -> getter != null && pos != null ? BiomeColors.getAverageGrassColor(getter, pos) : GrassColor.get(0.5D, 1.0D), RisusBlocks.MIRAGE_GRASS_BLOCK.get());
	}

	@EventBusSubscriber(modid = Risus.MODID, value = Dist.CLIENT, bus = Bus.FORGE)
	public static class RisusForgeEvents {
		@SubscribeEvent
		public static void killScreenWithAmnesia(RenderGuiOverlayEvent.Pre event) {
			if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(RisusMobEffects.AMNESIA.get())) {
				event.setCanceled(true);
			}
		}

		@SubscribeEvent
		public static void killHandWithAmnesia(RenderHandEvent event) {
			if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(RisusMobEffects.AMNESIA.get())) {
				event.setCanceled(true);
			}
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
