package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.corruptions.AttractTargetEffect;
import com.bigdious.risus.corruptions.EatExperienceBarEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CorruptionEffects {

	public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Risus.MODID);

	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<EatExperienceBarEffect>> EAT_EXPERIENCE_BAR = ENTITY_EFFECTS.register("eat_experience_bar", () -> EatExperienceBarEffect.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<AttractTargetEffect>> ATTRACT_TARGET = ENTITY_EFFECTS.register("attract_target", () -> AttractTargetEffect.CODEC);

}
