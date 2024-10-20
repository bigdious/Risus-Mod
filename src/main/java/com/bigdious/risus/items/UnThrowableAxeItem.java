package com.bigdious.risus.items;

import com.bigdious.risus.entity.projectile.ThrownAxe;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.Holder;
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
	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return material.is(RisusItems.GLUTTONY_SCALES);
	}
	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(Enchantments.SHARPNESS) ||
			enchantment.is(Enchantments.BANE_OF_ARTHROPODS)||
			enchantment.is(Enchantments.LOYALTY)||
			enchantment.is(Enchantments.SMITE)||
			enchantment.is(Enchantments.MENDING)||
			enchantment.is(Enchantments.UNBREAKING)||
			enchantment.is(Enchantments.FIRE_ASPECT)||
			enchantment.is(Enchantments.KNOCKBACK)||
			enchantment.is(Enchantments.VANISHING_CURSE)
			;
	}
}
