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
	public static final DeferredHolder<Potion, Potion> STRONG_LIFE_SMOULDERING = POTIONS.register("strong_life_smouldering", () -> new Potion(new MobEffectInstance(RisusMobEffects.EXBURN, 900, 1), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 900, 2)));

	public static final DeferredHolder<Potion, Potion> LONG_LUCK = POTIONS.register("long_luck", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 12000)));
	public static final DeferredHolder<Potion, Potion> STRONG_LUCK = POTIONS.register("strong_luck", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 3000, 1)));

	public static final DeferredHolder<Potion, Potion> BAD_LUCK = POTIONS.register("bad_luck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 6000)));
	public static final DeferredHolder<Potion, Potion> LONG_BAD_LUCK = POTIONS.register("long_bad_luck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 12000)));
	public static final DeferredHolder<Potion, Potion> STRONG_BAD_LUCK = POTIONS.register("strong_bad_luck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 3000, 1)));

	public static final DeferredHolder<Potion, Potion> GLOWING = POTIONS.register("glowing", () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 4800)));
	public static final DeferredHolder<Potion, Potion> LONG_GLOWING = POTIONS.register("long_glowing", () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 9600)));

	public static final DeferredHolder<Potion, Potion> CLOTTING = POTIONS.register("clotting", () -> new Potion(new MobEffectInstance(RisusMobEffects.BLOODCLOGGED, 400)));
	public static final DeferredHolder<Potion, Potion> LONG_CLOTTING = POTIONS.register("long_clotting", () -> new Potion(new MobEffectInstance(RisusMobEffects.BLOODCLOGGED, 800)));

	public static final DeferredHolder<Potion, Potion> GOLDEN_GLORY = POTIONS.register("golden_glory", () -> new Potion(new MobEffectInstance(RisusMobEffects.GOLD_RUSH, 3600)));

	public static final DeferredHolder<Potion, Potion> COPPER_AGE = POTIONS.register("copper_age", () -> new Potion(new MobEffectInstance(RisusMobEffects.VERDIGRIS_VEHEMENCE, 3600)));

	public static final DeferredHolder<Potion, Potion> TRANSFUSION = POTIONS.register("transfusion", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_TRANSFUSION = POTIONS.register("long_transfusion", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600)));
	public static final DeferredHolder<Potion, Potion> STRONG_TRANSFUSION = POTIONS.register("strong_transfusion", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1800, 1)));

}

