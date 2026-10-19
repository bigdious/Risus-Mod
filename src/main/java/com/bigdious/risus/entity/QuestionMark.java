package com.bigdious.risus.entity;

import com.bigdious.risus.entity.creatures.Weaver;
import com.bigdious.risus.entity.creatures.pets.Holder;
import com.bigdious.risus.init.RisusCriterionTriggers;
import com.bigdious.risus.init.RisusDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.Optional;
import java.util.UUID;

public class QuestionMark extends Entity {
	private static final EntityDataAccessor<Boolean> TRANSIENT = SynchedEntityData.defineId(QuestionMark.class, EntityDataSerializers.BOOLEAN);
	private boolean isTransient;
	private int killTimer;

	public QuestionMark(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.isTransient && this.getRandom().nextInt(3) > 1) {
			this.killTimer++;
		}
		if (this.killTimer >= 60) {
			this.discard();
		}
	}

	public void setTransient() {
		this.isTransient = true;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putBoolean("transient", this.isTransient);
		tag.putShort("timer", (short) this.killTimer);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		this.killTimer = tag.getShort("timer");
		this.isTransient = tag.getBoolean("transient");
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(TRANSIENT, this.isTransient);
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
	public boolean isOnFire() {
		return false;
	}


	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getEntity() instanceof ServerPlayer sp) {
			RisusCriterionTriggers.DISCOVER.get().trigger(sp);
		}
		return source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && super.hurt(source, amount);
	}

	@Override
	public PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

	@Override
	public boolean isPushedByFluid(FluidType type) {
		return false;
	}

	@Override
	public void setDeltaMovement(Vec3 deltaMovement) {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}


}
