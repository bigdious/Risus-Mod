package com.bigdious.risus.mixin;

import com.bigdious.risus.client.MusicHandler;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public abstract class MusicManagerMixin {

	@Inject(method = "tick()V",
		at = @At(value = "HEAD"),
		cancellable = true)
	private void risus$stopTickingMusicManager(CallbackInfo ci) {
		if (MusicHandler.RISUS_MUSIC_PLAYING) {
			ci.cancel();
		}
	}
}
