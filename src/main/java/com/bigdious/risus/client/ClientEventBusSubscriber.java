package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.block.RisusBlocks;
import com.bigdious.risus.client.render.AngelRenderer;
import com.bigdious.risus.client.render.HolderRenderer;
import com.bigdious.risus.client.render.ModSkullTileEntityRenderer;
import com.bigdious.risus.entity.RisusEntities;
import com.bigdious.risus.tileentity.RisusTileEntities;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Risus.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RisusEntities.ANGEL, AngelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RisusEntities.HOLDER, HolderRenderer::new);
        
        ClientRegistry.bindTileEntityRenderer(RisusTileEntities.BLOODWYRM.get(), ModSkullTileEntityRenderer::new);
    }

    public static void renderLayers() {
        RenderTypeLookup.setRenderLayer(RisusBlocks.BLOODWEAVE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(RisusBlocks.CRYSTALIZED_BONDS.get(), RenderType.getTranslucent());
    }
}
