package com.bigdious.risus.effects;

import net.minecraft.world.effect.AttributeModifierTemplate;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.Map;

public class ToothlusterEffect extends MobEffect {
	public ToothlusterEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	private double multiplier;

	public ToothlusterEffect(MobEffectCategory p_19426_, int p_19427_, double p_19428_) {
		super(p_19426_, p_19427_);
		this.multiplier = p_19428_;
	}

	public double getAttributeModifierValue(int p_19430_, AttributeModifier p_19431_) {
		return this.multiplier * (double) (p_19430_ + 1);
	}
}
