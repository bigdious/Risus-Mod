package com.bigdious.risus.entity.cultists;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.level.Level;

public class GreatPreacher extends AbstractIllager {
	public GreatPreacher(EntityType<? extends AbstractIllager> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void applyRaidBuffs(ServerLevel serverLevel, int i, boolean b) {

	}

	@Override
	public SoundEvent getCelebrateSound() {
		return null;
	}
}
