package com.bigdious.risus;

import com.bigdious.risus.init.ModEntityTypes;
import com.bigdious.risus.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("risus")
public class Risus {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "risus";
    
    private static final Rarity risus = Rarity.create("RISUS", TextFormatting.DARK_RED);

    public Risus() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModEntityTypes.entityAttributes();
    }

    private void doClientStuff(final FMLClientSetupEvent event) { }

    public static class RisusTab extends ItemGroup {
    	public static final RisusTab INSTANCE = new RisusTab();

    	public RisusTab() {
    		super(MOD_ID);
    		setNoTitle();
    		setBackgroundImageName("risus.png");
    	}
    	
    	private static final ResourceLocation RISUS_TABS = new ResourceLocation("risus", "textures/gui/tabs.png");
    	@Override
    	public ResourceLocation getTabsImage() {
			return RISUS_TABS;
    	}

    	@Nonnull
    	@Override
    	public ItemStack createIcon() {
    		return new ItemStack(ModItems.SMILE);
    	}
	}
    
    public static Rarity getRarity() {
		return risus != null ? risus : Rarity.UNCOMMON;
	}
}
