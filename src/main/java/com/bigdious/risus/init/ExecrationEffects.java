package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.execrations.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExecrationEffects {

	public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Risus.MODID);

	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<EatExperienceBarEffect>> EAT_EXPERIENCE_BAR = ENTITY_EFFECTS.register("eat_experience_bar", () -> EatExperienceBarEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<AttractTargetEffect>> ATTRACT_TARGET = ENTITY_EFFECTS.register("attract_target", () -> AttractTargetEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<TakeRevengeOnImbecileEffect>> TAKE_REVENGE_ON_IMBECILE = ENTITY_EFFECTS.register("take_revenge_on_imbecile", () -> TakeRevengeOnImbecileEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<ConferAgonyEffect>> CONFER_AGONY = ENTITY_EFFECTS.register("confer_agony", () -> ConferAgonyEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<DoubleSummonEntityEffect>> DOUBLE_SUMMON_ENTITY = ENTITY_EFFECTS.register("double_summon_entity", () -> DoubleSummonEntityEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<BatteringEffect>> BATTERING = ENTITY_EFFECTS.register("battering", () -> BatteringEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<FierySpeedEffect>> FIERY_SPEED = ENTITY_EFFECTS.register("fiery_speed", () -> FierySpeedEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<ReduceAmmoEffect>> REDUCE_AMMO = ENTITY_EFFECTS.register("reduce_ammo", () -> ReduceAmmoEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<EruptEffect>> ERUPT = ENTITY_EFFECTS.register("erupt", () -> EruptEffect.CODEC);
}
