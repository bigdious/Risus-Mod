package com.bigdious.risus.effects;

import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;

public class MatingFrenzyEffect extends MobEffect {
	public MatingFrenzyEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof Animal animal) {
			if (animal.isBaby()) animal.removeEffect(RisusMobEffects.MATING_FRENZY.get());
			if (!animal.isBaby() && animal.canFallInLove()) {
				animal.hurt(DamageSource.MAGIC, 1.0F);
				animal.setInLove(null);
				animal.setAge(0);
			}
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return duration % 20 == 0;
	}
}
