package com.bigdious.risus.items;

import com.bigdious.risus.entity.projectile.EggSac;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExperienceItem extends Item {
	public ExperienceItem(Properties properties) {
		super(properties);
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!level.isClientSide) {
			int i = 3 + level.random.nextInt(5) + level.random.nextInt(5);
			ExperienceOrb.award((ServerLevel)level, player.position(), i);
		}
		itemstack.consume(1, player);
		player.awardStat(Stats.ITEM_USED.get(this));
		return InteractionResultHolder.consume(itemstack);
	}

}
