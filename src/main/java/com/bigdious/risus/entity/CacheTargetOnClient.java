package com.bigdious.risus.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.Nullable;

public interface CacheTargetOnClient {
	@Nullable
	default LivingEntity getActiveAttackTarget(Mob mob) {
		if (!this.hasActiveAttackTarget()) {
			return null;
		} else if (mob.getLevel().isClientSide()) {
			if (this.getCachedAttackTarget() != null) {
				return this.getCachedAttackTarget();
			} else {
				Entity entity = mob.getLevel().getEntity(this.getDataTarget());
				if (entity instanceof LivingEntity living) {
					this.setCachedAttackTarget(living);
					return this.getCachedAttackTarget();
				} else {
					return null;
				}
			}
		} else {
			return mob.getTarget();
		}
	}

	default boolean hasActiveAttackTarget() {
		return this.getDataTarget() != 0;
	}

	@Nullable LivingEntity getCachedAttackTarget();

	void setCachedAttackTarget(@Nullable LivingEntity living);

	int getDataTarget();
}
