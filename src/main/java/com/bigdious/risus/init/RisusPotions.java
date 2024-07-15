package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class RisusPotions {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Risus.MODID);

	public static final DeferredHolder<Potion, Potion> MATING_FRENZY = POTIONS.register("mating_frenzy", () -> new Potion(new MobEffectInstance(RisusMobEffects.MATING_FRENZY, 400)));
	public static final DeferredHolder<Potion, Potion> LONG_MATING_FRENZY = POTIONS.register("long_mating_frenzy", () -> new Potion("mating_frenzy", new MobEffectInstance(RisusMobEffects.MATING_FRENZY, 1200)));
}
