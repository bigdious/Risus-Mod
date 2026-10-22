package com.bigdious.risus.entity.cultists;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class GreatPreacher extends AbstractIllager {
	public GreatPreacher(EntityType<? extends AbstractIllager> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.35F)
			.add(Attributes.FOLLOW_RANGE, 12.0)
			.add(Attributes.MAX_HEALTH, 36.0)
			.add(Attributes.ATTACK_DAMAGE, 5.0);
	}

	@Override
	public void applyRaidBuffs(ServerLevel serverLevel, int i, boolean b) {

	}

	@Override
	public SoundEvent getCelebrateSound() {
		return null;
	}
}
