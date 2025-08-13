package com.bigdious.risus.items;

import com.bigdious.risus.config.RisusConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class DisplayNotchItem extends BlockItem {
	public DisplayNotchItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		if (Screen.hasShiftDown()) {
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.start").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.axe").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.shovel").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.pickaxe").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.glow_ink_sac").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.dye").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.phantom_membrane").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.tripwire_hook").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.glass").withStyle(ChatFormatting.GRAY));
			if (RisusConfig.spinningSource == RisusConfig.SpinningSource.SIGNAL) {
				tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.signal").withStyle(ChatFormatting.GRAY));
			} else {
				tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.redstone_torch").withStyle(ChatFormatting.GRAY));
			}
		} else {
			tooltipComponents.add(Component.translatable("tooltip.risus.display_notch.hidden").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
		}

		super.appendHoverText(stack, context, tooltipComponents, isAdvanced);
	}
}
