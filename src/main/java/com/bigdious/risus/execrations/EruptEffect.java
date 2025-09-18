package com.bigdious.risus.execrations;

import com.bigdious.risus.init.RisusMobEffects;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record EruptEffect(LevelBasedValue strength) implements EnchantmentEntityEffect {
	public static final MapCodec<EruptEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("strength").forGetter(EruptEffect::strength))
		.apply(instance, EruptEffect::new));

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		float strength = this.strength.calculate(i);
			if (entity != null) {
				eruptAtEntity(enchantedItemInUse.owner(), strength, entity);
			} else {
				eruptAtBlock(enchantedItemInUse.owner(), strength, vec3);
			}
	}

	public static void eruptAtBlock(LivingEntity attacker,float strength, Vec3 landing) {
		BlockPos blockpos = BlockPos.containing(landing);
		if ((attacker.isOnFire() || attacker.isInLava() || attacker.hasEffect(RisusMobEffects.EXBURN)) && (attacker.isCrouching() || attacker.isShiftKeyDown())) {
			attacker.teleportTo(blockpos.getX(), blockpos.getY()+1, blockpos.getZ());
			erupt(attacker, strength);
		}
	}

	public static void eruptAtEntity(LivingEntity attacker, float strength, Entity entity) {
		if (attacker.isOnFire() || attacker.isInLava() || attacker.hasEffect(RisusMobEffects.EXBURN) ||
		entity.isOnFire() || entity.isInLava() || (entity instanceof LivingEntity living && living.hasEffect(RisusMobEffects.EXBURN))) {
			attacker.teleportTo(entity.getX(), entity.getY(), entity.getZ());
			erupt(attacker, strength);
		}
	}
	public static void erupt(LivingEntity source, float length) {
		source.igniteForSeconds(length);
		List<Entity> list = source.level().getEntities(source, source.getBoundingBox().inflate(length * 3));
		for (Entity victim : list) {
			victim.igniteForSeconds(length);
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
