package com.bigdious.risus.mixin;

import com.bigdious.risus.worldgen.structures.StructureBehaviors;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
	@Inject(method = "doTick()V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/PlayerTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;)V"))
	private void risus$checkIfInSpecialStructures(CallbackInfo ci) {
		ServerPlayer serverPlayer = (ServerPlayer)(Object)this;
		StructureBehaviors.runStructureEffects(serverPlayer);
	}
}
