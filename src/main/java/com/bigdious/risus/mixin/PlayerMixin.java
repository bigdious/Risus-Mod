package com.bigdious.risus.mixin;

import com.bigdious.risus.client.event.RisusClientEvents;
import com.bigdious.risus.init.Execrations;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusMobEffects;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Player.class)
public abstract class PlayerMixin {
	@Inject(method = "isScoping", at = @At("RETURN"), cancellable = true)
	public void isScoping(CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue() == Boolean.TRUE) return;
		Player player = (Player) (Object) this;
		if (player.getInventory().getArmor(3).has(RisusDataComponents.ABILITY_VARIANT) && Objects.equals(player.getInventory().getArmor(3).get(RisusDataComponents.ABILITY_VARIANT), "spyglass") && RisusClientEvents.isSpyGlassModeActive) {
			cir.setReturnValue(true);
			return;
		}
	}

}
