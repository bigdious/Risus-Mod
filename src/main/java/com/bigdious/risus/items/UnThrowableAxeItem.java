package com.bigdious.risus.items;

import com.bigdious.risus.entity.projectile.ThrownAxe;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
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

public class UnThrowableAxeItem extends AxeItem {
	public UnThrowableAxeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}


	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		List<Enchantment> validEnchants = List.of(
			Enchantments.UNBREAKING,
			Enchantments.SHARPNESS,
			Enchantments.SMITE,
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.FIRE_ASPECT,
			Enchantments.KNOCKBACK,
			Enchantments.LOOTING);
		return validEnchants.contains(enchantment);
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack, ItemStack book) {
		List<Enchantment> validEnchants = List.of(Enchantments.MENDING, Enchantments.LOYALTY);
		AtomicBoolean flag = new AtomicBoolean(false);
		validEnchants.forEach(enchantment -> {
			if (EnchantmentHelper.canStoreEnchantments(book)) {
				flag.set(true);
			}
		});
		return flag.get();
	}
}
