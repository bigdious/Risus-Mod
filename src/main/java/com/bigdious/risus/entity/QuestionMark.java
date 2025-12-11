package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

public class QuestionMark extends Monster {

	private boolean isTransient;
	private int killTimer;

	public QuestionMark(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 1024.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.0D)
			.add(Attributes.ATTACK_DAMAGE, 10.0D);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.setYRot(0.0F);
		this.yRotO = 0.0F;
		this.setYHeadRot(0.0F);
		this.yHeadRotO = 0.0F;
		this.setYBodyRot(0.0F);
		this.yBodyRotO = 0.0F;
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();
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
		super.addAdditionalSaveData(tag);
		tag.putBoolean("transient", this.isTransient);
		tag.putShort("timer", (short) this.killTimer);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.killTimer = tag.getShort("timer");
		this.isTransient = tag.getBoolean("transient");
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.AMBIENT_CAVE.value();
	}

	@Override
	public int getAmbientSoundInterval() {
		return 200;
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}

	@Override
	public boolean canBeSeenAsEnemy() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public void knockback(double x, double y, double z) {
	}

	@Override
	public boolean removeWhenFarAway(double dist) {
		return false;
	}

	@Override
	public boolean attackable() {
		return false;
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public void playerTouch(Player player) {
		if (this.getBoundingBox().intersects(player.getBoundingBox()) && this.isAlive()) {
				if (this.hasLineOfSight(player) && player.hurt(this.damageSources().source(RisusDamageTypes.INEXISTENCE), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue())) {
					// was doEnchantDamageEffects
					this.doAutoAttackOnTouch(player);
				}
			}
	}
	@Override
	public boolean isOnFire() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && super.hurt(source, amount);
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
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
