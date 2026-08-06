package com.bigdious.risus.items.weapons;

import com.bigdious.risus.entity.projectile.ThrownAxe;
import com.bigdious.risus.entity.projectile.ThrownBoomstick;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class BoomstickItem extends Item {

	public BoomstickItem(Item.Properties pProperties){
		super(pProperties);
	}
	@Override
	public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
		return pRepair.is(Items.TNT);
	}
	@Override
	public boolean isEnchantable(ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.BOOMSTICK_ALLOWED_ENCHANTS);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {

		return enchantment.is(RisusTags.Enchantments.BOOMSTICK_ALLOWED_ENCHANTS);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(itemstack);
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int useTicks) {
		if (entity instanceof Player player) {
			int i = this.getUseDuration(stack, entity) - useTicks;
			if (i >= 10) {
				Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND)
					.orElse(RisusSoundEvents.BOOMSTICK_THROW);
				if (!level.isClientSide()) {
					stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
					ThrownBoomstick boomstick = new ThrownBoomstick(level, player, stack);
					boomstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
					if (player.getAbilities().instabuild) {
						boomstick.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
					}

					level.addFreshEntity(boomstick);
					level.playSound(null, boomstick, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
					if (!player.getAbilities().instabuild) {
						player.getInventory().removeItem(stack);
					}
				}

				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}
}
