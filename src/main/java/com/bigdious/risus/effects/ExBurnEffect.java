package com.bigdious.risus.effects;

import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusDataAttachments;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Objects;
import java.util.Set;


public class ExBurnEffect extends MobEffect {

	public ExBurnEffect(MobEffectCategory category, int color) {
		super(category, color);
	}
	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
		cures.clear();
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (!entity.level().isClientSide()) {
			if (entity.getAttribute(Attributes.MAX_HEALTH) != null && Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH)).getValue() > 6.0D) {
				if (RisusConfig.canonExBurn) {
					entity.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(entity.getAttributes().getInstance(Attributes.MAX_HEALTH).getBaseValue()-1);
				} else entity.getData(RisusDataAttachments.EX_BURN).decrementHealth(entity);
			}
		}
		return super.applyEffectTick(entity, amplifier);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 100/(1+amplifier) == 0;
	}
}
