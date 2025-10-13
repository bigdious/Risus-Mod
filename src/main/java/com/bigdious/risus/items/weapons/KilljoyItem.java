package com.bigdious.risus.items.weapons;

import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Holder;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;

public class KilljoyItem extends AxeItem {
	public KilljoyItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.KILLJOY_ALLOWED_ENCHANTS);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.KILLJOY_ALLOWED_ENCHANTS);
	}
}
