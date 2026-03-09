package com.bigdious.risus.effects;

import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusTags;
import it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.neoforge.common.EffectCure;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class BloodcloggedEffect extends MobEffect {
	public BloodcloggedEffect(MobEffectCategory pCategory, int pColor) {
		super(pCategory, pColor);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.getType().is(RisusTags.Entities.OFFSPRINGS_AND_BELOVEDS)) {
			entity.removeEffect(RisusMobEffects.BLOODCLOGGED);
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
