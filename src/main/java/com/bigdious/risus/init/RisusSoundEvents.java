package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Risus.MODID);
	public static final RegistryObject<SoundEvent> MEAT_BREAK = register("block.meat.break");
	public static final RegistryObject<SoundEvent> MEAT_FALL = register("block.meat.fall");
	public static final RegistryObject<SoundEvent> MEAT_HIT = register("block.meat.hit");
	public static final RegistryObject<SoundEvent> MEAT_PLACE = register("block.meat.place");
	public static final RegistryObject<SoundEvent> MEAT_STEP = register("block.meat.step");
    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Risus.MODID, name)));
    }
}
