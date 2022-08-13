package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusPotions {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Risus.MODID);

	public static final RegistryObject<Potion> MATING_FRENZY = POTIONS.register("mating_frenzy", () -> new Potion(new MobEffectInstance(RisusMobEffects.MATING_FRENZY.get(), 400)));
	public static final RegistryObject<Potion> LONG_MATING_FRENZY = POTIONS.register("long_mating_frenzy", () -> new Potion("mating_frenzy", new MobEffectInstance(RisusMobEffects.MATING_FRENZY.get(), 1200)));
}
