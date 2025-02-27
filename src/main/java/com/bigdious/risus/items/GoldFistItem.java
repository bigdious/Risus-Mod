package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class GoldFistItem extends ToothknockerItem {

	public GoldFistItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		RandomSource random = RandomSource.create();
		Level level = entity.level();
		int i = random.nextInt(9);
		if (entity instanceof LivingEntity target && entity.getType() != EntityType.PLAYER && !(entity.getType().is(RisusTags.Entities.CANT_BE_STOLEN_FROM))) {
			//change random bound to define chances, 4 is 100% 8 is 50% etc.
			int rand = random.nextInt(20);
			if (rand < 4) {
				EquipmentSlot slot = EquipmentSlot.values()[rand + 2];
				if (target.hasItemInSlot(slot)) {
					//we need to check if item was added, otherwise it can strip with no loot. Blame Mojang/java
					ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(slot));
					level.addFreshEntity(item);
					if  (item.isAddedToLevel()) {
						level.playSound(player, player.getOnPos(), RisusSoundEvents.STRIPPER_STRIP.get(), SoundSource.PLAYERS);
						target.setItemSlot(slot, ItemStack.EMPTY);
					}
				}
			}
		} else if (entity instanceof Player playertarget) {
			if (player.getUUID() == UUID.fromString("4b455c5e-d81f-441c-906e-768708f6ca32")) {
				playertarget.getInventory().dropAll();
				level.playSound(player, player.getOnPos(), RisusSoundEvents.STRIPPER_STRIP.get(), SoundSource.PLAYERS);
				player.sendSystemMessage(Component.literal(ChatFormatting.DARK_RED + "Your items are being repossessed due to outstanding debt."));
			} else if (!playertarget.getInventory().getItem(i).isEmpty()) {
				ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), playertarget.getInventory().getItem(i));
				level.addFreshEntity(item);
				//we need to check if item was added, otherwise it can strip with no loot. Blame Mojang/java
				if  (item.isAddedToLevel()) {
					level.playSound(player, player.getOnPos(), RisusSoundEvents.STRIPPER_STRIP.get(), SoundSource.PLAYERS);
					playertarget.getInventory().setItem(i, ItemStack.EMPTY);
				}


			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
}
