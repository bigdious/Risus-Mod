package com.bigdious.risus.items.armor;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;

public class AngelWingsItem extends Item implements Equipable {

	public AngelWingsItem(Item.Properties pProperties) {
		super(pProperties);
		DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
	}

	public static boolean isFlyEnabled(ItemStack elytraStack) {
		return elytraStack.getDamageValue() < elytraStack.getMaxDamage() - 31;
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return this.swapWithEquipmentSlot(this, level, player, hand);
	}

	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return isFlyEnabled(stack);
	}

	public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
		int nextFlightTick = flightTicks + 1;
		if (nextFlightTick % 10 == 0) {
			if (nextFlightTick % 400 == 0) {
				stack.hurtAndBreak(1, entity, EquipmentSlot.CHEST);
			}
			entity.gameEvent(GameEvent.ELYTRA_GLIDE);
		}

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

	public Holder<SoundEvent> getEquipSound() {
		return RisusSoundEvents.ARMOR_EQUIP_WINGS;
	}

	public EquipmentSlot getEquipmentSlot() {
		return EquipmentSlot.CHEST;
	}

	@Override
	public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
		return pRepair.is(RisusItems.BLOOD_FEATHER);
	}


}
