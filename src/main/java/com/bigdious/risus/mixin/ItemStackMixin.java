package com.bigdious.risus.mixin;

import com.bigdious.risus.event.ExecrationEvents;
import com.bigdious.risus.init.Execrations;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(method = "hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V", at = @At("INVOKE"))
	private void hurtAndBreak(int i, LivingEntity livingEntity, EquipmentSlot equipmentSlot, CallbackInfo ci) {
		if (i >0 && !livingEntity.level().isClientSide() && livingEntity.getItemBySlot(equipmentSlot).has(DataComponents.ENCHANTMENTS) && livingEntity.getItemBySlot(equipmentSlot).get(DataComponents.ENCHANTMENTS).getLevel(livingEntity.registryAccess().holderOrThrow(Execrations.RELOCATION)) > 0) {
			ExecrationEvents.performRelocation(livingEntity, livingEntity.getItemBySlot(equipmentSlot), equipmentSlot, livingEntity.getItemBySlot(equipmentSlot).get(DataComponents.ENCHANTMENTS).getLevel(livingEntity.registryAccess().holderOrThrow(Execrations.RELOCATION)));
		}
	}
}
