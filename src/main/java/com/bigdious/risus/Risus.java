package com.bigdious.risus;

import com.bigdious.risus.block.RisusBlocks;
import com.bigdious.risus.client.ClientEventBusSubscriber;
import com.bigdious.risus.client.particles.RisusParticles;
import com.bigdious.risus.items.RisusItems;
import com.bigdious.risus.tileentity.RisusTileEntities;
import com.google.common.collect.Maps;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@Mod(Risus.ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Risus {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String ID = "risus";
    
    private static final Rarity risus = Rarity.create("RISUS", TextFormatting.DARK_RED);

    public Risus() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		RisusBlocks.BLOCKS.register(bus);
		RisusItems.ITEMS.register(bus);
		RisusTileEntities.TILE_ENTITIES.register(bus);
		RisusParticles.PARTICLES.register(bus);

    }

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
			AxeItem.BLOCK_STRIPPING_MAP.put(RisusBlocks.BONDKNOT_LOG.get(), RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
			AxeItem.BLOCK_STRIPPING_MAP.put(RisusBlocks.BONDKNOT_WOOD.get(), RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
		});
	}

	@SubscribeEvent
	public void clientSetup(FMLClientSetupEvent event) {
		ClientEventBusSubscriber.renderLayers();
	}

    public static class RisusTab extends ItemGroup {
    	public static final RisusTab INSTANCE = new RisusTab();

    	public RisusTab() {
    		super(ID);
    		setNoTitle();
    		setBackgroundImage(risusRL("textures/gui/tab_risus.png"));
    	}
    	
    	private static final ResourceLocation RISUS_TABS = risusRL("textures/gui/tabs.png");
    	@Override
    	public ResourceLocation getTabsImage() {
			return RISUS_TABS;
    	}

    	@Nonnull
    	@Override
    	public ItemStack createIcon() {
    		return new ItemStack(RisusItems.SMILE.get());
    	}
	}

	public static ResourceLocation risusRL(String name) {
		return new ResourceLocation(ID, name.toLowerCase(Locale.ROOT));
	}
    
    public static Rarity getRarity() {
		return risus != null ? risus : Rarity.UNCOMMON;
	}
}
