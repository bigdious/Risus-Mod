package com.bigdious.risus.entity.ai;

import com.bigdious.risus.entity.Licker;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.schedule.Activity;

import java.util.List;
import java.util.Set;

public class LickerAi {
	public static final List<SensorType<? extends Sensor<? super Licker>>> SENSOR_TYPES;
	public static final List<MemoryModuleType<?>> MEMORY_TYPES;
	public LickerAi() {
	}
	public static Brain<?> makeBrain(Licker licker, Brain<Licker> lickerBrain) {
		initCoreActivity(lickerBrain);
		initIdleActivity(lickerBrain);
		initFightActivity(licker, lickerBrain);
		lickerBrain.setCoreActivities(Set.of(Activity.CORE));
		lickerBrain.setDefaultActivity(Activity.FIGHT);
		lickerBrain.useDefaultActivity();
		return lickerBrain;
	}
	private static void initCoreActivity(Brain<Licker> lickerBrain) {
		lickerBrain.addActivity(Activity.CORE, 0, ImmutableList.of(
			new Swim(0.8F),
			new MoveToTargetSink(),
			new LookAtTargetSink(45, 90)));
	}
	private static void initIdleActivity(Brain<Licker> lickerBrain) {
		lickerBrain.addActivity(Activity.IDLE, ImmutableList.of(Pair.of(0, StartAttacking.create((p_312881_) -> {
			return p_312881_.getBrain().getMemory(MemoryModuleType.NEAREST_ATTACKABLE);
		})), Pair.of(1, StartAttacking.create(Licker::getHurtBy)),
			Pair.of(2, new RunOne(ImmutableList.of(
				Pair.of(new DoNothing(20, 100), 1),
				Pair.of(RandomStroll.stroll(0.6F), 2))))));
	}
	private static void initFightActivity(Licker licker, Brain<Licker> lickerBrain) {
		lickerBrain.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, ImmutableList.of(StopAttackingIfTargetInvalid.create((unknown) -> {
			return !licker.canTargetEntity(licker);
		}), SetEntityLookTarget.create((target) -> {
			return isTarget(licker, target);
		}, (float)licker.getAttributeValue(Attributes.FOLLOW_RANGE)), SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(1.2F), MeleeAttack.create(6)), MemoryModuleType.ATTACK_TARGET);
	}
		private static boolean isTarget(Licker licker, LivingEntity target) {
			return licker.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).filter((trueTarget) -> {
				return trueTarget == target;
			}).isPresent();
		}
	public static void updateActivity(Licker licker) {
		licker.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
	}
	static {
		SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, SensorType.NEAREST_PLAYERS);
		MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_ATTACKABLE, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.BREEZE_JUMP_COOLDOWN, MemoryModuleType.BREEZE_JUMP_INHALING, MemoryModuleType.BREEZE_SHOOT, MemoryModuleType.BREEZE_SHOOT_CHARGING, MemoryModuleType.BREEZE_SHOOT_RECOVERING, MemoryModuleType.BREEZE_SHOOT_COOLDOWN, new MemoryModuleType[]{MemoryModuleType.BREEZE_JUMP_TARGET, MemoryModuleType.BREEZE_LEAVING_WATER, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.PATH});
	}
}
