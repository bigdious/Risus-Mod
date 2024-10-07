package com.bigdious.risus;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;


import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class RisusEnumExtensions {
	public static final EnumProxy<Rarity> BLOOD = new EnumProxy<>(
		Rarity.class, -1, "risus:blood", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.DARK_RED)
	);
	public static Object EXBURN_HEARTS(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/normal");
			case 1 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/normal_blinking");
			case 2 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/half");
			case 3 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/half_blinking");
			case 4 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore");
			case 5 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore_blinking");
			case 6 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore_half");
			case 7 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore_half_blinking");
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}
	public static Object BLOODCLOGGED_HEARTS(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/normal");
			case 1 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/normal_blinking");
			case 2 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/half");
			case 3 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/half_blinking");
			case 4 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore");
			case 5 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore_blinking");
			case 6 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore_half");
			case 7 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore_half_blinking");
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}
}
