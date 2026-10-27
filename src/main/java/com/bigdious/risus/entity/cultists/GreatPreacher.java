package com.bigdious.risus.entity.cultists;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GreatPreacher extends AbstractIllager {
	public GreatPreacher(EntityType<? extends AbstractIllager> entityType, Level level) {
		super(entityType, level);
	}
	public final AnimationState wingFlapIdleState = new AnimationState();
	private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(GreatPreacher.class, EntityDataSerializers.BYTE);

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.35F)
			.add(Attributes.FOLLOW_RANGE, 12.0)
			.add(Attributes.MAX_HEALTH, 36.0)
			.add(Attributes.ATTACK_DAMAGE, 5.0);
	}

	@Override
	public void tick() {
		super.tick();
		this.setupAnimationStates();
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_FLAGS, (byte)0);
	}

	public boolean shouldFlap() {
		return (this.entityData.get(DATA_ID_FLAGS) & 1) != 0;
	}

	private void setupAnimationStates() {
		this.wingFlapIdleState.startIfStopped(this.tickCount);
	}

	@Override
	public void applyRaidBuffs(ServerLevel serverLevel, int i, boolean b) {

	}

	@Override
	public SoundEvent getCelebrateSound() {
		return null;
	}
}
