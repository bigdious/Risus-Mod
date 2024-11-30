package com.bigdious.risus.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Set;


public class BloodcloggedEffect extends MobEffect {
	public BloodcloggedEffect(MobEffectCategory pCategory, int pColor) {
		super(pCategory, pColor);
	}
	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
		cures.clear();
	}
}
