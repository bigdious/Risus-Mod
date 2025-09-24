package com.bigdious.risus.effects;

import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusDataAttachments;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Objects;
import java.util.Set;


public class BloodcloggedEffect extends MobEffect {
	public BloodcloggedEffect(MobEffectCategory pCategory, int pColor) {
		super(pCategory, pColor);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.isAlive() && !(entity.getType().is(RisusTags.Entities.OFFSPRING))) {
			if (entity.getAttribute(Attributes.MAX_HEALTH) != null && entity.getMaxHealth()>entity.getHealth()) {
				entity.addEffect(new MobEffectInstance(RisusMobEffects.BLOODCLOGGED, entity.getEffect(RisusMobEffects.BLOODCLOGGED).getDuration(), entity.getEffect(RisusMobEffects.BLOODCLOGGED).getAmplifier()+1, false, false, true));
			}
		}
		return super.applyEffectTick(entity, amplifier);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
		cures.clear();
	}
}
