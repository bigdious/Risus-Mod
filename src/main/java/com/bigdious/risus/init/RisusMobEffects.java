package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.effects.AmnesiaMobEffect;
import com.bigdious.risus.effects.MatingFrenzyEffect;
import com.bigdious.risus.effects.PleasureEffect;
import com.bigdious.risus.effects.ToothlusterEffect;
import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusMobEffects {

	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Risus.MODID);

	public static final RegistryObject<MobEffect> AMNESIA = MOB_EFFECTS.register("amnesia", () -> new AmnesiaMobEffect(MobEffectCategory.HARMFUL, 0x990099));
	public static final RegistryObject<MobEffect> MATING_FRENZY = MOB_EFFECTS.register("mating_frenzy", () -> new MatingFrenzyEffect(MobEffectCategory.HARMFUL, 0x990000).addAttributeModifier(Attributes.MOVEMENT_SPEED, "8bca5419-6ea8-481e-a521-b79456bb5c99", 1.5D, AttributeModifier.Operation.MULTIPLY_TOTAL));
	public static final RegistryObject<MobEffect> PLEASURE = MOB_EFFECTS.register("pleasure", () -> new PleasureEffect(MobEffectCategory.HARMFUL, 0x009900));
	public static final RegistryObject<MobEffect> TOOTHLUSTER = MOB_EFFECTS.register("toothluster", () -> new ToothlusterEffect(MobEffectCategory.BENEFICIAL, 16762624, 3.0D).addAttributeModifier(Attributes.ATTACK_DAMAGE, "77af0b7e-7346-444d-949c-90e35ad0138b", 1.0D,AttributeModifier.Operation.ADDITION));
}
