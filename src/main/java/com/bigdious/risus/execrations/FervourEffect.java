package com.bigdious.risus.execrations;

import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record FervourEffect(LevelBasedValue strength) implements EnchantmentEntityEffect {
	public static final MapCodec<FervourEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("strength").forGetter(FervourEffect::strength))
		.apply(instance, FervourEffect::new));

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		float strength = this.strength.calculate(i);
		LivingEntity user = enchantedItemInUse.owner();
		user.igniteForSeconds(strength);
		for (int j = 0; i < 20; ++i) {
			serverLevel.sendParticles(ParticleTypes.FLAME, user.getRandomX(1F), user.getRandomY(), user.getRandomZ(1F), 1, 0.0F, 0.0F, 0.0F, 0.1+strength/30);
		}
		serverLevel.playSound(null, user.getOnPos().above(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS);
		List<Entity> list = user.level().getEntities(user, user.getBoundingBox().inflate(strength/2));
		for (Entity victim : list) {
			if (!(victim instanceof ItemEntity)) {
				victim.igniteForSeconds(strength);
			}
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
