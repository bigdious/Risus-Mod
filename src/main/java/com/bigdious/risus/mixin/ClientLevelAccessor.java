package com.bigdious.risus.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(ClientLevel.class)
public interface ClientLevelAccessor {
	//based on Bumblezone's ClientLevelAccessor
	@Accessor("MARKER_PARTICLE_ITEMS")
	static Set<Item> risus$getMARKER_PARTICLE_ITEMS() {
		throw new UnsupportedOperationException();
	}

	@Mutable
	@Accessor("MARKER_PARTICLE_ITEMS")
	static void risus$setMARKER_PARTICLE_ITEMS(Set<Item> MARKER_PARTICLE_ITEMS) {
		throw new UnsupportedOperationException();
	}
}
