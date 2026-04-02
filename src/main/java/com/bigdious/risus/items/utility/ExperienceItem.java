package com.bigdious.risus.items.utility;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ExperienceItem extends Item {
	public ExperienceItem(Properties properties) {
		super(properties);
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!level.isClientSide) {
			int i = 9 + level.random.nextInt(5);
			ExperienceOrb.award((ServerLevel)level, player.position(), player.isCrouching() ? itemstack.getCount()*i : i );
		}
		itemstack.consume(player.isCrouching() ? itemstack.getCount() : 1, player);
		player.awardStat(Stats.ITEM_USED.get(this));
		return InteractionResultHolder.consume(itemstack);
	}
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.risus.memory_core").withStyle(ChatFormatting.GRAY));
	}

}
