package com.bigdious.risus.execrations;

import com.bigdious.risus.init.RisusDamageTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.Vec3;

public record BatteringEffect(LevelBasedValue strength) implements EnchantmentEntityEffect {
	public static final MapCodec<BatteringEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("strength").forGetter(BatteringEffect::strength))
		.apply(instance, BatteringEffect::new));

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		float strength = this.strength.calculate(i);
		batter( enchantedItemInUse.owner(), strength , entity);
	}

	public static void batter(LivingEntity attacker, float strength, Entity entity) {
		if (entity instanceof LivingEntity living && living.getAttribute(Attributes.ARMOR) != null) {
			living.invulnerableTime=0;
			living.hurt(attacker.damageSources().source(DamageTypes.ARROW, attacker), ((float)living.getAttributeValue(Attributes.ARMOR)/5)*strength );
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
