package com.bigdious.risus.items;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class SoulScytheItem extends ScytheItem{
	public SoulScytheItem(Tier material, Properties properties) {
		super(material, properties);
	}
	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(Enchantments.SHARPNESS) ||
			enchantment.is(Enchantments.BANE_OF_ARTHROPODS)||
			enchantment.is(Enchantments.MENDING)||
			enchantment.is(Enchantments.UNBREAKING)||
			enchantment.is(Enchantments.FIRE_ASPECT)||
			enchantment.is(Enchantments.KNOCKBACK)||
			enchantment.is(Enchantments.VANISHING_CURSE)
			;
	}
}
