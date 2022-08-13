package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.JoyParticle;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.util.RisusSkullType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Risus.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class RisusClientEvents {

	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		event.register(RisusParticles.DRIPPING_JOY.get(), JoyParticle.JoyHangProvider::new);
		event.register(RisusParticles.FALLING_JOY.get(), JoyParticle.JoyFallProvider::new);
		event.register(RisusParticles.LANDING_JOY.get(), JoyParticle.JoyLandProvider::new);
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(RisusBlockEntities.RISUS_SIGN.get(), SignRenderer::new);

		event.enqueueWork(() -> {
			SkullBlockRenderer.SKIN_BY_TYPE.put(RisusSkullType.BLOODWYRM, Risus.prefix("textures/entity/bloodwyrm_head.png"));

			Sheets.addWoodType(RisusBlocks.BONDKNOT);
		});
	}
}
