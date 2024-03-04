package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.inventory.DepthVaseMenu;
import com.bigdious.risus.inventory.MawGutsMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusMenuTypes {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Risus.MODID);

	public static final RegistryObject<MenuType<MawGutsMenu>> MAW_GUTS = MENU_TYPES.register("amnesia", () -> new MenuType<>(MawGutsMenu::new, FeatureFlags.REGISTRY.allFlags()));
	public static final RegistryObject<MenuType<DepthVaseMenu>> DEPTH_VASE = MENU_TYPES.register("depthvase", () -> new MenuType<>(DepthVaseMenu::new, FeatureFlags.REGISTRY.allFlags()));
}
