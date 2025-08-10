package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Stool extends Entity implements OwnableEntity {

	@Nullable
	private UUID OwnerUUID;

	public Stool(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}


	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return false;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains("OwnerUUID")) {
			this.OwnerUUID = tag.getUUID("OwnerUUID");
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
		if (this.OwnerUUID != null) {
			tag.putUUID("OwnerUUID", this.OwnerUUID);
		}
	}

	@Nullable
	@Override
	public UUID getOwnerUUID() {
		return this.OwnerUUID;
	}

	public UUID setOwnerUUID(UUID uuid) {
		return this.OwnerUUID = uuid;
	}
}
