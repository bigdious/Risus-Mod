package com.bigdious.risus.execrations;

import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusParticles;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public record FallAsleepAtNightEffect(LevelBasedValue strength) implements EnchantmentEntityEffect {
	public static final MapCodec<FallAsleepAtNightEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("strength").forGetter(FallAsleepAtNightEffect::strength))
		.apply(instance, FallAsleepAtNightEffect::new));
	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		float strength = this.strength.calculate(i);
		if (entity instanceof Player player && serverLevel.dimension() == ServerLevel.OVERWORLD ) {
			if (serverLevel.getDayTime() > 12600 && serverLevel.getDayTime() < 23400 && serverLevel.getGameTime() % 25 == 0) {
				serverLevel.sendParticles(RisusParticles.SLEEPY.get(), player.getX(), player.getEyeY()+0.3, player.getZ(), 1, 0, 0.0, 0.0, 0.01);
			}
			if (serverLevel.getDayTime() > 12600 && serverLevel.getDayTime() < 23400 && (serverLevel.getDayTime() == 12640 || serverLevel.getDayTime() % 3600/(strength) == 0)) {
				player.stopFallFlying();
				player.stopRiding();
				player.startSleepInBed(player.getOnPos().above());

			}
		}
	}


	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
