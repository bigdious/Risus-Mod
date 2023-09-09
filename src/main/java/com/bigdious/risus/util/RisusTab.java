package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class RisusTab {
	private static final ResourceLocation RISUS_TABS = Risus.prefix("textures/gui/tabs.png");
	private static final ResourceLocation RISUS_BACKGROUND = Risus.prefix("textures/gui/tab_risus.png");

	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Risus.MODID);

	public static final RegistryObject<CreativeModeTab> INSTANCE = CREATIVE_TABS.register("risus", () -> CreativeModeTab.builder()
			.hideTitle()
			.displayItems(
					(parameters, output) -> ForgeRegistries.ITEMS.getEntries().stream()
							.filter(entry -> entry.getKey().location().getNamespace().equals(Risus.MODID))
							.map(Map.Entry::getValue)
							.forEach(output::accept)
			)
			.withTabsImage(RISUS_TABS)
			.withBackgroundLocation(RISUS_BACKGROUND)
			.icon(() -> new ItemStack(RisusItems.SMILE.get()))
			.build());
}