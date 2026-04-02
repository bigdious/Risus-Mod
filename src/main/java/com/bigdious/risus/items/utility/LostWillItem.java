package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusCriterionTriggers;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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

public class LostWillItem extends Item {
	public LostWillItem(Properties properties) {
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
		if (player instanceof ServerPlayer sp) {
			ServerAdvancementManager manager = sp.getCommandSenderWorld().getServer().getAdvancements();
			//maybe this can be automated, but that's not worth the time. Check the lost wills category in the patchouli book for number of entries
			int numberOfLostWillEntries = 5;
			for (int i = 1; i<numberOfLostWillEntries+1 ; i++) {
				AdvancementHolder holder = manager.get(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "lost_will_" + i));
				if (holder != null && sp.getAdvancements().getOrStartProgress(holder).getPercent() == 0) {
					RisusCriterionTriggers.LOST_WILL.get().trigger(sp, i);
					break;
				}
			}

		}
		player.awardStat(Stats.ITEM_USED.get(this));
		return InteractionResultHolder.consume(itemstack);
	}
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.risus.lost_will").withStyle(ChatFormatting.GRAY));


	}

}
