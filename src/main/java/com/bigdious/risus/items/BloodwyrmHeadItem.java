package com.bigdious.risus.items;

import com.bigdious.risus.entity.projectile.BloodwyrmBreathEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

//TODO MAYBE flamethrower change from entity shot to hitscan ala scepter of life (CHECK DISADVANTAGES)
public class BloodwyrmHeadItem extends Item {

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!player.isInWater()) {
			if (itemstack.getDamageValue() >= 100) {
				int validSlot = 0;
				for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
					ItemStack checkStack = player.getInventory().getItem(i);
					if (checkStack.is(Items.DRAGON_BREATH)) {
						validSlot = i;
						break;
					}
				}
				ItemStack ammo = player.getInventory().getItem(validSlot);
				//fuck you -1
				if (validSlot != -1 && ammo.is(Items.DRAGON_BREATH)) {
					itemstack.hurt(-100, level.getRandom(), null);
					ammo.shrink(1);
					level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
				} else {

					if (itemstack.getDamageValue() == itemstack.getMaxDamage()) {
						return InteractionResultHolder.fail(player.getItemInHand(hand));
					} else {
						if (!level.isClientSide) {
							ItemStack stack = player.getItemInHand(hand);
							BloodwyrmBreathEntity breath = new BloodwyrmBreathEntity(level, player);
							breath.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
							level.addFreshEntity(breath);
							stack.hurt(1, level.getRandom(), null);
							player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.PLAYERS, .5f, .75f);
						}
						return InteractionResultHolder.success(player.getItemInHand(hand));
					}
				}
			} else {

				if (itemstack.getDamageValue() == itemstack.getMaxDamage()) {
					return InteractionResultHolder.fail(player.getItemInHand(hand));
				} else {
					if (!level.isClientSide) {
						ItemStack stack = player.getItemInHand(hand);
						BloodwyrmBreathEntity breath = new BloodwyrmBreathEntity(level, player);
						breath.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
						level.addFreshEntity(breath);
						stack.hurt(1, level.getRandom(), null);
						player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, .5f, .75f);
					}
					return InteractionResultHolder.success(player.getItemInHand(hand));
				}
			}
		}
		return InteractionResultHolder.fail(player.getItemInHand(hand));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}


	public BloodwyrmHeadItem(Properties p_41383_) {
		super(p_41383_);
	}
}
