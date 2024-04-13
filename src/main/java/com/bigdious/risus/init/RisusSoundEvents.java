package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Risus.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_BREAK = register("block.meat.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_FALL = register("block.meat.fall");
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_HIT = register("block.meat.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_PLACE = register("block.meat.place");
	//TODO check why meatplace sound doesn't play
	public static final DeferredHolder<SoundEvent, SoundEvent> MEAT_STEP = register("block.meat.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> SQUIRT = register("item.bloodwyrm_head.squirt");

	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Risus.MODID, name)));
	}
}
