package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Holder;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class BoomstickItem extends Item {

	public BoomstickItem( Item.Properties pProperties){
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
		return enchantment.is(Enchantments.UNBREAKING);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(Enchantments.UNBREAKING) || enchantment.is(Enchantments.MENDING);
	}


}
