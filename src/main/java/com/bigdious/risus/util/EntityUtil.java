package com.bigdious.risus.util;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EntityUtil {
	public static boolean properlyApplyCustomDamageSource(Mob entity, Entity victim, DamageSource source) {
		float f = (float) entity.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float f1 = (float) entity.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
		if (victim instanceof LivingEntity) {
			f += EnchantmentHelper.getDamageBonus(entity.getMainHandItem(), ((LivingEntity) victim).getMobType());
			f1 += (float) EnchantmentHelper.getKnockbackBonus(entity);
		}

		int i = EnchantmentHelper.getFireAspect(entity);
		if (i > 0) {
			victim.setSecondsOnFire(i * 4);
		}

		boolean flag = victim.hurt(source, f);
		if (flag) {
			if (f1 > 0.0F && victim instanceof LivingEntity) {
				((LivingEntity) victim).knockback(f1 * 0.5F, Mth.sin(entity.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(entity.getYRot() * ((float) Math.PI / 180F)));
				entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
			}

			entity.doEnchantDamageEffects(entity, victim);
			entity.setLastHurtMob(victim);
		}

		return flag;
	}
}
