package com.bigdious.risus.execrations;

import com.bigdious.risus.advancement.LostWillTrigger;
import com.bigdious.risus.init.RisusCriterionTriggers;
import com.bigdious.risus.init.RisusDamageTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ConferAgonyEffect() implements EnchantmentEntityEffect {
	public static final MapCodec<ConferAgonyEffect> CODEC = MapCodec.unit(ConferAgonyEffect::new);
	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		if (entity instanceof LivingEntity victim && enchantedItemInUse.owner() != null && (enchantedItemInUse.owner().getLastDamageSource() == null || !enchantedItemInUse.owner().getLastDamageSource().is(RisusDamageTypes.AGONY))) {
			agonize(serverLevel, enchantedItemInUse.owner(), enchantedItemInUse.itemStack(), victim);
		}
	}
	public static void agonize(ServerLevel level, LivingEntity attacker, ItemStack stack, LivingEntity victim) {
		float enemyDamage = victim.getAttribute(Attributes.ATTACK_DAMAGE) == null ? 0 : (float) victim.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
		float damage = attacker.getMaxHealth() - attacker.getHealth() + enemyDamage;
		attacker.invulnerableTime=0;
		attacker.hurt(victim.damageSources().source(RisusDamageTypes.AGONY), enemyDamage/2);
		victim.hurt(attacker.damageSources().source(RisusDamageTypes.AGONY), damage);
		if (attacker instanceof ServerPlayer sp && damage > 40) {
			RisusCriterionTriggers.AGONY.get().trigger(sp);
		}

	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
