package com.bigdious.risus.mixin;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusTags;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
	@ModifyReturnValue(method = "getFullname(Lnet/minecraft/core/Holder;I)Lnet/minecraft/network/chat/Component;", at = @At("RETURN"))
	private static Component spectrum$obfuscateEnchantmentNames(Component original, Holder<Enchantment> enchantment, int level) {
		MutableComponent mutablecomponent = enchantment.value().description().copy();
		ComponentUtils.mergeStyles(mutablecomponent, Style.EMPTY.withColor(ChatFormatting.DARK_RED));
		if (level != 1 || (enchantment.value()).getMaxLevel() != 1) {
			mutablecomponent.append(CommonComponents.SPACE).append(Component.translatable("enchantment.level." + level));
		}
		return enchantment.is(RisusTags.Enchantments.CORRUPTION) ? mutablecomponent : original;
	}
}
