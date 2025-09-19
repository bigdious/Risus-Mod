package com.bigdious.risus.execrations;

import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
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
		if (enchantedItemInUse.owner() != null && entity.getType().is(RisusTags.Entities.TRIDENT_LIKE_PROJECTILES)) {
			LivingEntity attacker = enchantedItemInUse.owner();
			if (attacker.isOnFire() || attacker.isInLava() || attacker.hasEffect(RisusMobEffects.EXBURN)) {
				attacker.teleportTo(entity.getX(), entity.getY(), entity.getZ());
				erupt(attacker, strength, entity);
			}
		}
	}
	//TODO fix particles not spawning if thrown too far

	public static void erupt(LivingEntity source, float length, Entity target) {
		source.igniteForSeconds(length);
		List<Entity> list = source.level().getEntities(source, source.getBoundingBox().inflate(length/2-1));
		for (Entity victim : list) {
			if (!(victim instanceof ItemEntity)) {
				victim.igniteForSeconds(length);
			}
		}
		if (target.level() instanceof ServerLevel serverLevel) {
			for (int i = 0; i < 20; ++i) {
				serverLevel.sendParticles(ParticleTypes.LAVA, target.getRandomX(2F), target.getRandomY(), target.getRandomZ(2F), 1, 0.0F, 0.0F, 0.0F, 2);
			}
			serverLevel.playSound(null, target.getOnPos().above(), RisusSoundEvents.ERUPT.get(), SoundSource.PLAYERS);
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
