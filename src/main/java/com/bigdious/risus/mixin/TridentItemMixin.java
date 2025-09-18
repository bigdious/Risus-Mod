package com.bigdious.risus.mixin;

import com.bigdious.risus.init.Execrations;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusTags;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin {
	@ModifyReturnValue(method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", at = @At("RETURN"))
	private InteractionResultHolder<ItemStack> use(InteractionResultHolder<ItemStack> original, Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getDamageValue() <= itemstack.getMaxDamage() - 1 &&
			itemstack.has(DataComponents.ENCHANTMENTS) &&
			itemstack.get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.ERUPTION)) > 0) {
			if (player.isOnFire() || player.hasEffect(RisusMobEffects.EXBURN) || player.isInLava()) {
				player.startUsingItem(hand);
				return InteractionResultHolder.consume(itemstack);
			} else {
				return InteractionResultHolder.fail(itemstack);
			}
		} else {
			return original;
		}
	}
}
