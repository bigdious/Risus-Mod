package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.util.ModItemTier;
import com.bigdious.risus.util.RisusTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Risus.MODID);

	public static final FoodProperties GUILTY_FOOD = new FoodProperties.Builder().nutrition(10).saturationMod(0.2F).alwaysEat().effect(() -> new MobEffectInstance(RisusMobEffects.PLEASURE.get(), 60), 1.0F).build();

	public static final RegistryObject<Item> JOYKILLER = ITEMS.register("joykiller", () -> new SwordItem(ModItemTier.JOY, 3, -2.4F, defaultWithRarity()));
	public static final RegistryObject<Item> SMILE = ITEMS.register("smile", () -> new Item(defaultWithRarity()));
	public static final RegistryObject<Item> BLOOD_FEATHER = ITEMS.register("blood_feather", () -> new Item(defaultWithRarity()));
	public static final RegistryObject<Item> GUILTY_APPLE = ITEMS.register("guilty_apple", () -> new Item(defaultWithRarity().food(GUILTY_FOOD)));

	public static Item.Properties defaultWithRarity() {
		return new Item.Properties().rarity(Risus.getRarity()).tab(RisusTab.INSTANCE);
	}
}
