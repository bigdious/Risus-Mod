package com.bigdious.risus.execrations;

import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record SummonStarParticlesEffect() implements EnchantmentEntityEffect {
	public static final MapCodec<SummonStarParticlesEffect> CODEC = MapCodec.unit(SummonStarParticlesEffect::new);

	@Override
	public void apply(ServerLevel serverLevel, int j, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		for (int i = 0; i < 2; ++i) {
			serverLevel.sendParticles(RisusParticles.STARS.get(), entity.getRandomX(4F), entity.getY()+entity.getRandom().nextFloat()*2-entity.getRandom().nextFloat()*2, entity.getRandomZ(4F), 1, 0.0F, 0.0F, 0.0F, 2);
		}

	}



	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
