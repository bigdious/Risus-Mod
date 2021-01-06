package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.render.AngelRenderer;
import com.bigdious.risus.client.render.HolderRenderer;
import com.bigdious.risus.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Risus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ANGEL, AngelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HOLDER, HolderRenderer::new);
    }
}
