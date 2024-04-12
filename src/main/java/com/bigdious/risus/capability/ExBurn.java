package com.bigdious.risus.capability;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


public interface ExBurn extends INBTSerializable<CompoundTag> {
	ResourceLocation ID = Risus.prefix("cap_ex_burn");
	//TODO figure out how to keep exburn cap after death
	public static void resetValueOnRespawn(PlayerEvent.Clone event ) {
		if (event.getEntity() instanceof ServerPlayer serverPlayerNew && event.getOriginal() instanceof ServerPlayer serverPlayerOld) {
			serverPlayerOld.reviveCaps();
			serverPlayerNew.getCapability(RisusCapabilities.EX_BURN).ifPresent(capabilityNew ->
				serverPlayerOld.getCapability(RisusCapabilities.EX_BURN).ifPresent(capabilityOld ->
					capabilityNew.deserializeNBT(capabilityOld.serializeNBT())));

			serverPlayerOld.invalidateCaps();
		}
	}

	void setEntity(LivingEntity entity);

	void decrementHealth();

	void incrementHealth();
}
