package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Risus.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_BREAK = register("block.meat.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_FALL = register("block.meat.fall");
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_HIT = register("block.meat.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_PLACE = register("block.meat.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_STEP = register("block.meat.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> SQUIRT = register("item.bloodwyrm_head.squirt");

	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_RAK = register("music.disc.rak");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_MORK = register("music.disc.mork");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_FEIGR = register("music.disc.feigr");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_REGN = register("music.disc.regn");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_MORK = register("music.ambient.mork");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_FEIGR = register("music.ambient.feigr");


	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Risus.prefix( name)));
	}
}
