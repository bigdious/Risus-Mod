package com.bigdious.risus.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class PleasureEffect extends MobEffect {
	public PleasureEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		entity.hurt(entity.damageSources().magic(), 1);
		entity.invulnerableTime = 0;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 4 == 0;
	}
}
