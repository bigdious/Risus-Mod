package com.bigdious.risus.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class CrystallizedBondItem extends Item {
	public CrystallizedBondItem(Properties properties) {
		super(properties);
	}
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.risus.crystallized_bond").withStyle(ChatFormatting.GRAY));
	}
}
