package com.bigdious.risus.mixin;

import com.bigdious.risus.client.MusicHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerTickClientMixin extends Entity {

	public PlayerTickClientMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "tick",
		at = @At("TAIL"))
	private void risus$onTickPost(CallbackInfo ci) {
		if (this.level().isClientSide()) {
			MusicHandler.tickMusicFader();
		}
	}
}
