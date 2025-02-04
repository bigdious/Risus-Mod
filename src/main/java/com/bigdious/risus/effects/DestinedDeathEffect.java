package com.bigdious.risus.effects;

import com.bigdious.risus.init.RisusDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Set;

public class DestinedDeathEffect extends MobEffect {
	public DestinedDeathEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		entity.hurt(new DamageSource(entity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(RisusDamageTypes.DESTINED_DEATH)), entity.getMaxHealth());
		return super.applyEffectTick(entity, amplifier);
	}
	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration < 21;
	}
	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
		cures.clear();
	}

}
