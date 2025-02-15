package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Holder;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class UnThrowableAxeItem extends AxeItem {
	public UnThrowableAxeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.CRESCENT_DISASTER_ALLOWED_ENCHANTS);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.CRESCENT_DISASTER_ALLOWED_ENCHANTS);
	}
}
