package com.bigdious.risus.event;

import com.bigdious.risus.entity.creatures.Licker;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class ParticleEvents {
	public static void addParticles(EntityTickEvent.Post event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity living) {
			if (living.tickCount % 5 == 0 && living.level() instanceof ServerLevel serverLevel) {
				if (living.hasEffect(RisusMobEffects.EXBURN)) {
					for (int i = 0; i < 2; i++) {
						serverLevel.sendParticles(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5), 1, 0.0, 0.0, 0.0, 0);
					}
				}
				if (living.hasEffect(RisusMobEffects.DESTINED_DEATH)) {
					for (int i = 0; i < 2; i++) {
						serverLevel.sendParticles(RisusParticles.DESTINED_DEATH_PARTICLE.get(), living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5), 1, 0.0, 0.0, 0.0, 0);
					}
				}
				if (living.hasEffect(RisusMobEffects.MATING_FRENZY)) {
					serverLevel.sendParticles(ParticleTypes.HEART, living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5), 3, 0, 0, 0, 0);
				}
				if (living.hasEffect(RisusMobEffects.BLOODCLOGGED)) {
						serverLevel.sendParticles(RisusParticles.FALLING_BLOOD.get(), living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5), 1, 0.0, 0.0, 0.0, 0);
				}
			}
		}
	}

	public static void knockOutSomeTeeth(LivingIncomingDamageEvent event) {
		Entity source = event.getSource().getEntity();

		if (source instanceof Player player) {
			if (player.hasEffect(RisusMobEffects.TOOTHLUSTER)) {
				if (event.getEntity().level() instanceof ServerLevel serverLevel) {
					serverLevel.sendParticles(RisusParticles.TOOTHICAL.get(), event.getEntity().getX(), event.getEntity().getEyeY(), event.getEntity().getZ(), 10, 0, 0, 0, 1);
				}
			}
		}
	}

	public static void addEggSack(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Licker licker) {
			if (licker.level() instanceof ServerLevel serverLevel) {
				serverLevel.sendParticles(ParticleTypes.ITEM_COBWEB, licker.getRandomX(0.5), licker.getY() + 1, licker.getRandomZ(0.5), 7, 0, 0, 0, 0);
			}
		}
	}
}
