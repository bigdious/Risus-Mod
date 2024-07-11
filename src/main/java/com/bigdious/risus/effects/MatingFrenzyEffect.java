package com.bigdious.risus.effects;

import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;

public class MatingFrenzyEffect extends MobEffect {
	public MatingFrenzyEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof Animal animal) {
			if (animal.isBaby()) animal.removeEffect(RisusMobEffects.MATING_FRENZY);
			if (!animal.isBaby() && animal.canFallInLove()) {
				animal.hurt(entity.damageSources().magic(), 1.0F);
				animal.setInLove(null);
				animal.setAge(0);
			}
		}
		if (entity instanceof Player player) {
			entity.removeEffect(RisusMobEffects.MATING_FRENZY);
			player.sendSystemMessage(Component.literal(ChatFormatting.DARK_RED + "You are incapable of Love."));
		}
		return super.applyEffectTick(entity, amplifier);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 20 == 0;
	}
}
