package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusMapDecorations {
	public static final DeferredRegister<MapDecorationType> MAP_DECORATION_TYPES = DeferredRegister.create(BuiltInRegistries.MAP_DECORATION_TYPE, Risus.MODID);

	public static final DeferredHolder<MapDecorationType, MapDecorationType> ANGEL_ALTAR = register("angel_altar");


	private static DeferredHolder<MapDecorationType, MapDecorationType> register(String name) {
		return MAP_DECORATION_TYPES.register(name, () -> new MapDecorationType(Risus.prefix(name), true, 5242880, false, true));
	}
}
