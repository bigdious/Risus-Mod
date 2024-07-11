package com.bigdious.risus.effects;

import com.bigdious.risus.init.RisusDataAttachments;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class ExBurnEffect extends MobEffect {
	public ExBurnEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (!entity.level().isClientSide()) {
			if (entity.getAttribute(Attributes.MAX_HEALTH) != null && entity.getAttribute(Attributes.MAX_HEALTH).getValue() > 6.0D) {
				entity.getData(RisusDataAttachments.EX_BURN).decrementHealth(entity);
			}
		}
		return super.applyEffectTick(entity, amplifier);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 100 == 0;
	}
}
