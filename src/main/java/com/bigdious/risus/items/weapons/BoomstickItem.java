package com.bigdious.risus.items.weapons;

import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Holder;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;

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

}
