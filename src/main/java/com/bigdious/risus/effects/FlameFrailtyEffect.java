package com.bigdious.risus.effects;

import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;

public class FlameFrailtyEffect extends MobEffect {
	public FlameFrailtyEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof Player player){
			if (player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
				player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1, false, false, false));
				player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false, false));
				player.removeEffect(MobEffects.FIRE_RESISTANCE);
				player.hurt(entity.damageSources().onFire(), 2);
				if (player.level() instanceof ServerLevel serverLevel) {
					for (int i = 0; i < 4; i++) {
						serverLevel.sendParticles(ParticleTypes.FLAME, entity.getRandomX(0.5), entity.getRandomY(), entity.getRandomZ(0.5), 1, 0, 0, 0.0, 0);
					}
				}
			} else if (player.isOnFire()){
				player.hurt(entity.damageSources().onFire(), 1);
				player.setRemainingFireTicks(entity.getRemainingFireTicks()+40);
				if (player.level() instanceof ServerLevel serverLevel) {
					for (int i = 0; i < 4; i++) {
						serverLevel.sendParticles(ParticleTypes.FLAME, entity.getRandomX(0.5), entity.getRandomY(), entity.getRandomZ(0.5), 1, 0, 0, 0.0, 0);
					}
				}
			}
		}
		if (entity.getType().fireImmune()) {
			entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 1, false, false, false));
			entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2, false, false, false));
			entity.hurt(entity.damageSources().generic(), 1);
			if (entity.level() instanceof ServerLevel serverLevel) {
				for (int i = 0; i < 4; i++) {
					serverLevel.sendParticles(ParticleTypes.FLAME, entity.getRandomX(0.5), entity.getRandomY(), entity.getRandomZ(0.5), 1, 0, 0, 0.0, 0);
				}
			}
		}
		if (entity.isOnFire()) {
			entity.hurt(entity.damageSources().onFire(), 1);
			entity.setRemainingFireTicks(entity.getRemainingFireTicks()+30);
			if (entity.level() instanceof ServerLevel serverLevel) {
				for (int i = 0; i < 4; i++) {
					serverLevel.sendParticles(ParticleTypes.FLAME, entity.getRandomX(0.5), entity.getRandomY(), entity.getRandomZ(0.5), 1, 0, 0, 0.0, 0);
				}
			}
		}

		return super.applyEffectTick(entity, amplifier);
	}
	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 30 == 0;
	}
}
