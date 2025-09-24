package com.bigdious.risus.client;

import com.bigdious.risus.entity.creatures.pets.Litter;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityCache {
	private static final Map<EntityType<?>, Entity> ENTITY_MAP = new HashMap<>();
	private static final Set<EntityType<?>> IGNORED_ENTITIES = new HashSet<>();

	@Nullable
	public static LivingEntity fetchEntity(EntityType<?> type, @Nullable Level level, @Nullable BlockState state) {
		if (level != null && !IGNORED_ENTITIES.contains(type)) {
			Entity entity;
			if (type == EntityType.PLAYER) {
				entity = Minecraft.getInstance().player;
			} else {
				entity = ENTITY_MAP.computeIfAbsent(type, t -> {
					Entity created = t.create(level);
					if (created != null) {
						created.setYRot(0.0F);
						created.setYHeadRot(0.0F);
						created.setYBodyRot(0.0F);
						created.hasImpulse = false;
						if (created instanceof Mob mob) {
							mob.setNoAi(true);
						}
					}
					return created;
				});
			}
			if (entity instanceof LivingEntity living) {
				if (entity instanceof Litter litter && state != null) {
					litter.setLightBlockState(state);
				}
				return living;
			} else {
				IGNORED_ENTITIES.add(type);
				ENTITY_MAP.remove(type);
			}
		}
		return null;
	}
}
