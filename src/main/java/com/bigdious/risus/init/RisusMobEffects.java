package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.effects.MatingFrenzyEffect;
import com.bigdious.risus.effects.PleasureEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusMobEffects {

	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Risus.MODID);

	public static final RegistryObject<MobEffect> MATING_FRENZY = MOB_EFFECTS.register("mating_frenzy", () -> new MatingFrenzyEffect(MobEffectCategory.HARMFUL, 0x990000).addAttributeModifier(Attributes.MOVEMENT_SPEED, "8bca5419-6ea8-481e-a521-b79456bb5c99", 1.5D, AttributeModifier.Operation.MULTIPLY_TOTAL));
	public static final RegistryObject<MobEffect> PLEASURE = MOB_EFFECTS.register("pleasure", () -> new PleasureEffect(MobEffectCategory.HARMFUL, 0x009900));
}
