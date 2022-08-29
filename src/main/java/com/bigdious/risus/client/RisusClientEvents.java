package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.AlterationFinishedParticle;
import com.bigdious.risus.client.particle.AlterationParticle;
import com.bigdious.risus.client.particle.JoyParticle;
import com.bigdious.risus.client.render.AlterationCatalystRenderer;
import com.bigdious.risus.init.*;
import com.bigdious.risus.util.RisusSkullType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Risus.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class RisusClientEvents {

	private static final RenderType MONOLITH_PORTAL = RenderType.create("risus:monolith_portal", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 256, true, false, RenderType.CompositeState.builder().setShaderState(RenderStateAccessor.getEndPortal()).setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(TheEndPortalRenderer.END_SKY_LOCATION, false, false).add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false).build()).createCompositeState(false));

	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		event.register(RisusParticles.ALTERATION.get(), AlterationParticle.Provider::new);
		event.register(RisusParticles.ALTERATION_FINISHED.get(), AlterationFinishedParticle.Provider::new);
		event.register(RisusParticles.DRIPPING_JOY.get(), JoyParticle.JoyHangProvider::new);
		event.register(RisusParticles.FALLING_JOY.get(), JoyParticle.JoyFallProvider::new);
		event.register(RisusParticles.LANDING_JOY.get(), JoyParticle.JoyLandProvider::new);
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(RisusBlockEntities.RISUS_SIGN.get(), SignRenderer::new);
		BlockEntityRenderers.register(RisusBlockEntities.ALTERATION_CATALYST.get(), context ->  new AlterationCatalystRenderer());

		MenuScreens.register(RisusMenuTypes.MAW_GUTS.get(), MawGutsScreen::new);

		event.enqueueWork(() -> {
			SkullBlockRenderer.SKIN_BY_TYPE.put(RisusSkullType.BLOODWYRM, Risus.prefix("textures/entity/bloodwyrm_head.png"));

			Sheets.addWoodType(RisusBlocks.BONDKNOT);
		});
	}

	@SubscribeEvent
	public static void registerRenderTypes(RegisterNamedRenderTypesEvent event) {
		//TODO must wait on forge to allow us to add custom render types to the chunk buffer
		//event.register("monolith_portal", MONOLITH_PORTAL, RenderType.entitySolid(TheEndGatewayRenderer.END_PORTAL_LOCATION));
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
