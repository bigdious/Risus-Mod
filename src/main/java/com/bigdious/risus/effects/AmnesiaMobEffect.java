package com.bigdious.risus.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;

public class AmnesiaMobEffect extends MobEffect {

	public AmnesiaMobEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.getRandom().nextFloat() < 0.1F * (amplifier / 10.0F)) {
			if (entity instanceof PathfinderMob mob && mob.getTarget() != null) {
				mob.setTarget(null);
			}
		}
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 10 == 0;
	}
}
