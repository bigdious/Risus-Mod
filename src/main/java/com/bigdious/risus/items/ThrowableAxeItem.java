package com.bigdious.risus.items;

import com.bigdious.risus.entity.ThrownAxe;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThrowableAxeItem extends AxeItem {
	public ThrowableAxeItem(Tier tier, float damage, float speed, Properties properties) {
		super(tier, damage, speed, properties);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		List<Enchantment> validEnchants = List.of(Enchantments.MENDING, Enchantments.UNBREAKING, Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.LOYALTY, Enchantments.IMPALING);
		AtomicBoolean flag = new AtomicBoolean(false);
		validEnchants.forEach(enchantment -> {
			if (EnchantmentHelper.getEnchantments(book).containsKey(enchantment)) {
				flag.set(true);
			}
		});
		return flag.get();
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		List<Enchantment> validEnchants = List.of(Enchantments.MENDING, Enchantments.UNBREAKING, Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.LOYALTY, Enchantments.IMPALING);
		return validEnchants.contains(enchantment);
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

	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int useTicks) {
		if (entity instanceof Player player) {
			int i = this.getUseDuration(stack) - useTicks;
			if (i >= 10) {
				if (!level.isClientSide()) {
					stack.hurtAndBreak(1, player, (p_43388_) -> {
						p_43388_.broadcastBreakEvent(entity.getUsedItemHand());
					});
					ThrownAxe axe = new ThrownAxe(level, player, stack);
					axe.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
					if (player.getAbilities().instabuild) {
						axe.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
					}

					level.addFreshEntity(axe);
					level.playSound(null, axe, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
					if (!player.getAbilities().instabuild) {
						player.getInventory().removeItem(stack);
					}
				}

				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}
}
