package com.bigdious.risus.execrations;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record SoarEffect(LevelBasedValue strength) implements EnchantmentEntityEffect {
	public static final MapCodec<SoarEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("strength").forGetter(SoarEffect::strength))
		.apply(instance, SoarEffect::new));

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		float strength = this.strength.calculate(i);
		soar( enchantedItemInUse.owner(), strength , entity);
	}

	public static void soar(LivingEntity attacker, float strength, Entity entity) {
		if (entity instanceof LivingEntity living) {
			Vec3 vec3 = (new Vec3(0, 1, 0).scale(strength/3));
			living.setDeltaMovement(entity.getDeltaMovement().add(vec3));
			living.hurtMarked = true;
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
