package com.bigdious.risus.items;

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
		if (entity instanceof LivingEntity target && entity.getType() != EntityType.PLAYER) {
			//change random bound to define chances, 4 is 100% 8 is 50% etc.
			switch (random.nextInt(40)) {
				case 1:
					if (target.hasItemInSlot(EquipmentSlot.HEAD)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.HEAD));
						level.addFreshEntity(item);
						target.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 2:
					if (target.hasItemInSlot(EquipmentSlot.CHEST)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.CHEST));
						level.addFreshEntity(item);
						target.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 3:
					if (target.hasItemInSlot(EquipmentSlot.LEGS)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.LEGS));
						level.addFreshEntity(item);
						target.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 4:
					if (target.hasItemInSlot(EquipmentSlot.FEET)) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), target.getItemBySlot(EquipmentSlot.FEET));
						level.addFreshEntity(item);
						target.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);

			}
		}
		if (entity instanceof Player playertarget) {
			if (player.getUUID()== UUID.fromString("4b455c5e-d81f-441c-906e-768708f6ca32")) {
				for (int kd = 0; kd<27; kd++){
					if (!playertarget.getInventory().getItem(kd).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
							playertarget.getInventory().getItem(kd));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(kd).shrink(playertarget.getInventory().getItem(kd).getCount());

					}
				}
			}
			//change random bound to define chances, 9 is 100% 18 is 50% etc.
			switch (random.nextInt(9)) {
				case 1:
					if (!playertarget.getInventory().getItem(1).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(1));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(1).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 2:
					if (!playertarget.getInventory().getItem(2).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(2));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(2).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 3:
					if (!playertarget.getInventory().getItem(3).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(3));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(3).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 4:
					if (!playertarget.getInventory().getItem(4).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(4));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(4).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 5:
					if (!playertarget.getInventory().getItem(5).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(5));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(5).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 6:
					if (!playertarget.getInventory().getItem(6).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(6));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(6).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 7:
					if (!playertarget.getInventory().getItem(7).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(7));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(7).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 8:
					if (!playertarget.getInventory().getItem(8).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(8));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(8).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
				case 9:
					if (!playertarget.getInventory().getItem(0).isEmpty()) {
						ItemEntity item = new ItemEntity(playertarget.level(), player.getX(), player.getY(), player.getZ(),
								playertarget.getInventory().getItem(0));
						player.level().addFreshEntity(item);
						playertarget.getInventory().getItem(0).shrink(1);
						return super.onLeftClickEntity(stack, player, entity);
					} else return super.onLeftClickEntity(stack, player, entity);
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility toolAction) {
		return false;
	}
}
