package com.bigdious.risus.mixin;

import com.bigdious.risus.blocks.DarknessBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.items.LightDevourerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {
	//thanks for the help TelephaticGrunt

	@Inject(method = "getMarkerParticleTarget()Lnet/minecraft/world/level/block/Block;",
		at = @At(value = "RETURN"),
		cancellable = true)
	private void risus$showDarknessInSurvival(CallbackInfoReturnable<Block> cir) {
		Player player = Minecraft.getInstance().player;
		if (player != null) {
			Item item = player.getMainHandItem().getItem();
			if (item instanceof LightDevourerItem) {
				cir.setReturnValue(RisusBlocks.DARKNESS.value());
			}
		}
	}
}
