package com.bigdious.risus.effects;

import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusDataAttachments;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Objects;
import java.util.Set;


public class MilkedEffect extends MobEffect {

	public MilkedEffect(MobEffectCategory category, int color) {
		super(category, color);
	}
	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
		cures.clear();
	}

}
