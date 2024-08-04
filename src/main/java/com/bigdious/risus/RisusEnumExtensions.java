package com.bigdious.risus;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;


import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class RisusEnumExtensions {
	public static final EnumProxy<Rarity> BLOOD = new EnumProxy<>(
		Rarity.class, -1, "risus:blood", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.DARK_RED)
	);
}
