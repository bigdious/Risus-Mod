package com.bigdious.risus.corruptions;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record AttractTargetEffect(LevelBasedValue strength) implements EnchantmentEntityEffect {
	public static final MapCodec<AttractTargetEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("strength").forGetter(AttractTargetEffect::strength))
		.apply(instance, AttractTargetEffect::new));

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		float strength = this.strength.calculate(i);
		attract( enchantedItemInUse.owner(), strength , entity);
	}

	public static void attract(LivingEntity attacker, float strength, Entity entity) {
		if (entity instanceof LivingEntity living) {
			Vec3 vec3 = (new Vec3(attacker.getX() - entity.getX(), (attacker.getY() - entity.getY())*0.05, attacker.getZ() - entity.getZ()).scale(strength));
			entity.setDeltaMovement(entity.getDeltaMovement().add(vec3));

		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
