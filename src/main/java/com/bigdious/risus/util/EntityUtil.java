package com.bigdious.risus.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import javax.annotation.Nullable;

public class EntityUtil {
	//based on Twilight Forest's EntityUtil class https://github.com/TeamTwilight/twilightforest/blob/1.21.x/src/main/java/twilightforest/util/entities/EntityUtil.java#L145
	public static boolean properlyApplyCustomDamageSource (Mob entity, Entity victim, DamageSource source, @Nullable SoundEvent flingSound) {
		float f = (float) entity.getAttributeValue(Attributes.ATTACK_DAMAGE);
		if (entity.level() instanceof ServerLevel serverlevel) {
			f = EnchantmentHelper.modifyDamage(serverlevel, entity.getWeaponItem(), entity, source, f);
		}

		boolean flag = victim.hurt(source, f);
		if (flag) {
			float f1 = getKnockback(entity, victim, source);
			if (f1 > 0.0F && victim instanceof LivingEntity livingentity) {
				if (flingSound != null) {
					entity.playSound(flingSound, 1.0F, 1.0F);
				}
				livingentity.knockback(f1 * 0.5F, Mth.sin(entity.getYRot() * Mth.DEG_TO_RAD), -Mth.cos(entity.getYRot() * Mth.DEG_TO_RAD));
				entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
			}
			if (entity.level() instanceof ServerLevel level) {
				EnchantmentHelper.doPostAttackEffects(level, victim, source);
			}
			entity.setLastHurtMob(entity);
		}

		return flag;
	}
	protected static float getKnockback(Mob entity, Entity victim, DamageSource source) {
		float f = (float) entity.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
		return entity.level() instanceof ServerLevel serverlevel ? EnchantmentHelper.modifyKnockback(serverlevel, entity.getWeaponItem(), victim, source, f) : f;
	}
}
