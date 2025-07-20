package com.bigdious.risus.items.utility;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;

public class RisusBookItem extends Item {

	public RisusBookItem(Item.Properties props) {
		super(props);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (player instanceof ServerPlayer playerser) {
			PatchouliAPI.get().openBookGUI(playerser, BuiltInRegistries.ITEM.getKey(this));
			return super.use(level, player, hand);
		}
		return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.risus.book_button_press",Component.translatable("tooltip.risus.book_button_press.outline", Component.keybind("keybind.researchers_notes_open").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GRAY));
		tooltipComponents.add(Component.translatable("tooltip.risus.researchers_notes").withStyle(ChatFormatting.GRAY));
	}
}
