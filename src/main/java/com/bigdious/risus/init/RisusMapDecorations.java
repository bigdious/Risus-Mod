package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusMapDecorations {
	public static final DeferredRegister<MapDecorationType> MAP_DECORATION_TYPES = DeferredRegister.create(BuiltInRegistries.MAP_DECORATION_TYPE, Risus.MODID);

	public static final DeferredHolder<MapDecorationType, MapDecorationType> ANGEL_ALTAR = register("angel_altar");
	public static final DeferredHolder<MapDecorationType, MapDecorationType> HEART_CHAMBER = register("heart_chamber");
	public static final DeferredHolder<MapDecorationType, MapDecorationType> RIBS_FOSSIL = register("ribs_fossil");
	public static final DeferredHolder<MapDecorationType, MapDecorationType> SKULL_FOSSIL = register("skull_fossil");
	public static final DeferredHolder<MapDecorationType, MapDecorationType> GREAT_BODY = register("great_body");
	public static final DeferredHolder<MapDecorationType, MapDecorationType> CHURCH = register("church");
	public static final DeferredHolder<MapDecorationType, MapDecorationType> BLOOD_POOL = register("blood_pool");
	public static final DeferredHolder<MapDecorationType, MapDecorationType> FLOWER_FIELD = register("flower_field");


	private static DeferredHolder<MapDecorationType, MapDecorationType> register(String name) {
		return MAP_DECORATION_TYPES.register(name, () -> new MapDecorationType(Risus.prefix(name), true, 5242880, false, true));
	}
}
