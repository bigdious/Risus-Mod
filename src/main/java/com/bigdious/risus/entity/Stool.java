package com.bigdious.risus.entity;

import com.bigdious.risus.entity.creatures.pets.Holder;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class Stool extends Entity {

	public Stool(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}


	@Override
	public boolean isNoGravity() {
		return true;
	}

//kill stool if no rider
	@Override
	public void tick() {
		if (!this.isVehicle() && this.level() instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.POOF, this.getX(), this.getY() + 0.5, this.getZ(), 3, 0, 0, 0, 0.1);
			serverLevel.playSound(null, this.getOnPos(), SoundEvents.WOOD_BREAK, SoundSource.PLAYERS);
			this.remove(RemovalReason.DISCARDED);
		}
	}

	@Override
	public Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
		return new Vec3(this.getX(), this.getBoundingBox().maxY, this.getZ());
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
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
	}

}
