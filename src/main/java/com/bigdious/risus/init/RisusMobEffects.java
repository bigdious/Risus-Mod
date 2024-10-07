package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class RisusMobEffects {

	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Risus.MODID);

	public static final DeferredHolder<MobEffect, MobEffect> AMNESIA = MOB_EFFECTS.register("amnesia", () -> new AmnesiaMobEffect(MobEffectCategory.HARMFUL, 0x990099));
	public static final DeferredHolder<MobEffect, MobEffect> MATING_FRENZY = MOB_EFFECTS.register("mating_frenzy", () -> new MatingFrenzyEffect(MobEffectCategory.HARMFUL, 0x990000).addAttributeModifier(Attributes.MOVEMENT_SPEED, Risus.prefix("mating_frenzy_speed"), 1.5D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
	public static final DeferredHolder<MobEffect, MobEffect> PLEASURE = MOB_EFFECTS.register("pleasure", () -> new PleasureEffect(MobEffectCategory.HARMFUL, 0x009900));
	public static final DeferredHolder<MobEffect, MobEffect> TOOTHLUSTER = MOB_EFFECTS.register("toothluster", () -> new ToothlusterEffect(MobEffectCategory.BENEFICIAL, 16762624, 3.0D).addAttributeModifier(Attributes.ATTACK_DAMAGE, Risus.prefix("toothluster_damage"), 1.0D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
	public static final DeferredHolder<MobEffect, MobEffect> EXBURN = MOB_EFFECTS.register("existential_burn", () -> new ExBurnEffect(MobEffectCategory.HARMFUL, 0x990000));
	public static final DeferredHolder<MobEffect, MobEffect> BLOODCLOGGED = MOB_EFFECTS.register("bloodclogged", () -> new BloodcloggedEffect(MobEffectCategory.HARMFUL, 0x990000).addAttributeModifier(Attributes.MAX_HEALTH, Risus.prefix("bloodclogged_health"), -1.0D, AttributeModifier.Operation.ADD_VALUE));
}
