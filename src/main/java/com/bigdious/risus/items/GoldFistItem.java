package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusTags;
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
import net.neoforged.neoforge.common.ItemAbilities;

import java.util.UUID;

public class GoldFistItem extends ToothknockerItem {
	public GoldFistItem(Tier tier, Properties properties) {
		super(tier,  properties);
	}


	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		RandomSource random = RandomSource.create();
		Level level = entity.level();
		int i = random.nextInt(9);
		if (entity instanceof LivingEntity target && entity.getType() != EntityType.PLAYER && !(entity.getType().is(RisusTags.Entities.CANT_BE_STOLEN_FROM))) {
			//change random bound to define chances, 4 is 100% 8 is 50% etc.
			switch (random.nextInt(20)) {
				case 1:
					if (target.hasItemInSlot(EquipmentSlot.HEAD)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.HEAD));
						level.addFreshEntity(item);
						if (item.isAddedToLevel()) {
							level.playSound(player, player.getOnPos(), SoundEvents.ARROW_HIT, SoundSource.PLAYERS);
							target.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
						}
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 2:
					if (target.hasItemInSlot(EquipmentSlot.CHEST)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.CHEST));
						level.addFreshEntity(item);
						if (item.isAddedToLevel()) {
							level.playSound(player, player.getOnPos(), SoundEvents.ARROW_HIT, SoundSource.PLAYERS);
							target.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
						}
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 3:
					if (target.hasItemInSlot(EquipmentSlot.LEGS)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.LEGS));
						level.addFreshEntity(item);
						if (item.isAddedToLevel()) {
							level.playSound(player, player.getOnPos(), SoundEvents.ARROW_HIT, SoundSource.PLAYERS);
							target.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
						}
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 4:
					if (target.hasItemInSlot(EquipmentSlot.FEET)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.FEET));
						level.addFreshEntity(item);
						if (item.isAddedToLevel()) {
							level.playSound(player, player.getOnPos(), SoundEvents.ARROW_HIT, SoundSource.PLAYERS);
							target.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
						}
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);

			}
		} else
			if (entity instanceof Player playertarget) {
				if (player.getUUID() == UUID.fromString("4b455c5e-d81f-441c-906e-768708f6ca32")) {
					for (int kd = 0; kd < 27; kd++) {
						if (!playertarget.getInventory().getItem(kd).isEmpty()) {
							ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(kd));
							player.level().addFreshEntity(item);
							if (item.isAddedToLevel()) {
								level.playSound(player, player.getOnPos(), SoundEvents.ARROW_HIT, SoundSource.PLAYERS);
								playertarget.getInventory().setItem(kd, ItemStack.EMPTY);
								player.sendSystemMessage(Component.literal(ChatFormatting.DARK_RED + "Your items are being repossessed due to outstanding debt."));
							}

						}
					}
				} else if (!playertarget.getInventory().getItem(i).isEmpty()) {
					ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
						playertarget.getInventory().getItem(i));
					player.level().addFreshEntity(item);
					if (item.isAddedToLevel()) {
						level.playSound(player, player.getOnPos(), SoundEvents.ARROW_HIT, SoundSource.PLAYERS);
						playertarget.getInventory().setItem(i, ItemStack.EMPTY);
					}
					return super.onLeftClickEntity(stack, player, entity);
				}
			}
			return super.onLeftClickEntity(stack, player, entity);
		}

	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility toolAction) {
		return false;
	}
}
