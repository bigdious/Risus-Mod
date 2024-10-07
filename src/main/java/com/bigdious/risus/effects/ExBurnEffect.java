package com.bigdious.risus.effects;

import com.bigdious.risus.client.particle.BaseOrganicParticle;
import com.bigdious.risus.init.RisusDataAttachments;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Function;


public class ExBurnEffect extends MobEffect {
	private static final int AMBIENT_ALPHA = Mth.floor(38.25F);

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
//		ParticleUtils.spawnParticles(entity.level(), entity.getOnPos().above(2), 9, 3.0, 1.0, true, RisusParticles.BLOCK_ORGANIC_PARTICLE.get());
		return super.applyEffectTick(entity, amplifier);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 100 == 0;
	}
}
