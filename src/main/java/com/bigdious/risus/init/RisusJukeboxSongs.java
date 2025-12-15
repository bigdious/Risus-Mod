package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredHolder;

public class RisusJukeboxSongs {
	public static final ResourceKey<JukeboxSong> RAK = create("rak");
	public static final ResourceKey<JukeboxSong> REGN = create("regn");
	public static final ResourceKey<JukeboxSong> FEIGR = create("feigr");
	public static final ResourceKey<JukeboxSong> MORK = create("mork");
	public static final ResourceKey<JukeboxSong> CYCLE = create("cycle");
	private static ResourceKey<JukeboxSong> create(String name) {
		return ResourceKey.create(Registries.JUKEBOX_SONG,  ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}
	private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, DeferredHolder<SoundEvent, SoundEvent> sound, float length, int output) {
		context.register(key, new JukeboxSong(sound, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), length, output));
	}
	public static void bootstrap(BootstrapContext<JukeboxSong> context) {
		register(context, RAK, RisusSoundEvents.MUSIC_DISC_RAK, 135, 15);
		register(context, REGN, RisusSoundEvents.MUSIC_DISC_REGN, 121, 14);
		register(context, FEIGR, RisusSoundEvents.MUSIC_DISC_FEIGR, 102, 10);
		register(context, MORK, RisusSoundEvents.MUSIC_DISC_MORK, 67, 7);
		register(context, CYCLE, RisusSoundEvents.MUSIC_DISC_CYCLE, 159, 6);
	}
}
