package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class Stool extends Entity implements OwnableEntity {
	protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID;

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
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_OWNERUUID_ID, Optional.empty());
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

	static {
		DATA_OWNERUUID_ID = SynchedEntityData.defineId(Stool.class, EntityDataSerializers.OPTIONAL_UUID);
	}

}
