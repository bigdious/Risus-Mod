package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class RisusPotions {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Risus.MODID);

	public static final DeferredHolder<Potion, Potion> MATING_FRENZY = POTIONS.register("mating_frenzy", () -> new Potion(new MobEffectInstance(RisusMobEffects.MATING_FRENZY, 600)));
	public static final DeferredHolder<Potion, Potion> LONG_MATING_FRENZY = POTIONS.register("long_mating_frenzy", () -> new Potion("mating_frenzy", new MobEffectInstance(RisusMobEffects.MATING_FRENZY, 1200)));

	public static final DeferredHolder<Potion, Potion> AMNESIA = POTIONS.register("amnesia", () -> new Potion(new MobEffectInstance(RisusMobEffects.AMNESIA, 400)));
	public static final DeferredHolder<Potion, Potion> LONG_AMNESIA = POTIONS.register("long_amnesia", () -> new Potion("amnesia", new MobEffectInstance(RisusMobEffects.AMNESIA, 800)));

	public static final DeferredHolder<Potion, Potion> LIFE_SMOULDERING = POTIONS.register("life_smouldering", () -> new Potion(new MobEffectInstance(RisusMobEffects.EXBURN, 1800), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 1)));
	public static final DeferredHolder<Potion, Potion> LONG_LIFE_SMOULDERING = POTIONS.register("long_life_smouldering", () -> new Potion(new MobEffectInstance(RisusMobEffects.EXBURN, 3600), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 1)));
	public static final DeferredHolder<Potion, Potion> STRONG_LIFE_SMOULDERING = POTIONS.register("strong_life_smouldering", () -> new Potion(new MobEffectInstance(RisusMobEffects.EXBURN, 1800, 1), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 2)));
}
