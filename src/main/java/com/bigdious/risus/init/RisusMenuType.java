package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.inventory.MawGutsMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class RisusMenuType {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, Risus.MODID);

	public static final DeferredHolder<MenuType<?>, MenuType<MawGutsMenu>> MAW_GUTS = MENU_TYPES.register("amnesia", () -> new MenuType<>(MawGutsMenu::new, FeatureFlags.REGISTRY.allFlags()));
//	public static final DeferredHolder<MenuType<DepthVaseMenu>> DEPTH_VASE = MENU_TYPES.register("depthvase", () -> new MenuType<>(DepthVaseMenu::new, FeatureFlags.REGISTRY.allFlags()));
}
