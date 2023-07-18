package com.bigdious.risus.compat;

import com.bigdious.risus.blocks.MirageBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import snownee.jade.addon.vanilla.VanillaPlugin;
import snownee.jade.api.*;

@WailaPlugin
public class JadeCompat implements IWailaPlugin {

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.addRayTraceCallback(this::risusOverrides);
	}

	@Nullable
	public Accessor<?> risusOverrides(HitResult hitResult, @Nullable Accessor<?> accessor, @Nullable Accessor<?> originalAccessor) {
		if (accessor instanceof BlockAccessor target) {
			Player player = accessor.getPlayer();
			if (player.isCreative() || player.isSpectator())
				return accessor;
			IWailaClientRegistration client = VanillaPlugin.CLIENT_REGISTRATION;
			if (target.getBlock() instanceof MirageBlock mirage) {
				return client.blockAccessor().from(target).blockState(mirage.getMimickedBlock().defaultBlockState()).build();
			}
		}
		return accessor;
	}
}
