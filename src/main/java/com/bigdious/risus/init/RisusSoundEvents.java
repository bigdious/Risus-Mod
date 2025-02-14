package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Risus.MODID);

	//items
//	public static final DeferredHolder<SoundEvent, SoundEvent> LUNGE = register("item.lunge");

	public static final DeferredHolder<SoundEvent, SoundEvent> SQUIRT = register("item.bloodwyrm_head.squirt");

	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_RAK = register("music.disc.rak");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_MORK = register("music.disc.mork");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_FEIGR = register("music.disc.feigr");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_REGN = register("music.disc.regn");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_MORK = register("music.ambient.mork");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_FEIGR = register("music.ambient.feigr");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHEEKY_LAUGH = register("mobs.cheeky_laugh");

	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Risus.prefix(name)));
	}
}
