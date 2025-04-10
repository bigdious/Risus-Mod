package com.bigdious.risus.items;

import com.bigdious.risus.entity.projectile.BloodwyrmBreathEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class BloodwyrmHeadItem extends Item {

	public BloodwyrmHeadItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!player.isInWaterOrRain()) {
			if (itemstack.getDamageValue() >= 200) {
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
					itemstack.hurtAndBreak(-200, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
					ItemUtils.createFilledResult(ammo, player, new ItemStack(Items.GLASS_BOTTLE));
					level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
				}
			}
			if (itemstack.getDamageValue() == itemstack.getMaxDamage()) {
				return InteractionResultHolder.fail(player.getItemInHand(hand));
			} else {
				player.startUsingItem(hand);
				return InteractionResultHolder.success(player.getItemInHand(hand));
			}
		}
		return super.use(level, player, hand);
	}

	@Override
	public void onUseTick(Level level, LivingEntity player, ItemStack stack, int count) {
		if (stack.getDamageValue() == stack.getMaxDamage()) {
			player.stopUsingItem();
			return;
		}
		if (count % 5 == 0) {
			if (!level.isClientSide()) {
				BloodwyrmBreathEntity breath1 = new BloodwyrmBreathEntity(level, player, stack);
				breath1.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 15F);
				level.addFreshEntity(breath1);
				BloodwyrmBreathEntity breath2 = new BloodwyrmBreathEntity(level, player, stack);
				breath2.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 15F);
				level.addFreshEntity(breath2);
				BloodwyrmBreathEntity breath3 = new BloodwyrmBreathEntity(level, player, stack);
				breath3.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 15F);
				level.addFreshEntity(breath3);
				stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
				player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.PLAYERS, .5f, .75f);
			}
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 72000;
	}

	@Override
	public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
		return oldStack.getItem() == newStack.getItem();
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || newStack.getItem() != oldStack.getItem();
	}
}
