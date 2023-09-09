package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ThrownAxe extends AbstractArrow {
	private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(ThrownAxe.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ThrownAxe.class, EntityDataSerializers.BOOLEAN);
	private ItemStack axeItem = new ItemStack(RisusItems.UNAWAKENED_VESSEL.get());
	private boolean dealtDamage;
	private boolean shouldSpin = true;
	public int spinTickCount;
	public int clientSideReturnAxeTickCount;

	public ThrownAxe(EntityType<ThrownAxe> type, Level level) {
		super(type, level);
	}

	public ThrownAxe(Level level, LivingEntity owner, ItemStack stack) {
		super(RisusEntities.THROWN_AXE.get(), owner, level);
		this.axeItem = stack.copy();
		this.entityData.set(ID_LOYALTY, (byte) EnchantmentHelper.getLoyalty(stack));
		this.entityData.set(ID_FOIL, stack.hasFoil());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ID_LOYALTY, (byte) 0);
		this.entityData.define(ID_FOIL, false);
	}

	public boolean shouldSpin() {
		return this.shouldSpin;
	}

	@Override
	public void tick() {
		if (this.level().isClientSide() && this.shouldSpin()) {
			this.spinTickCount++;
		}

		if (this.inGroundTime > 4) {
			this.dealtDamage = true;
		}

		Entity entity = this.getOwner();
		int i = this.entityData.get(ID_LOYALTY);
		if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
			if (!this.isAcceptableReturnOwner()) {
				if (!this.level().isClientSide() && this.pickup == AbstractArrow.Pickup.ALLOWED) {
					this.spawnAtLocation(this.getPickupItem(), 0.1F);
				}

				this.discard();
			} else {
				this.setNoPhysics(true);
				Vec3 vec3 = entity.getEyePosition().subtract(this.position());
				this.setPosRaw(this.getX(), this.getY() + vec3.y() * 0.015D * (double) i, this.getZ());
				if (this.level().isClientSide()) {
					this.yOld = this.getY();
				}

				double d0 = 0.05D * (double) i;
				this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
				if (this.clientSideReturnAxeTickCount == 0) {
					this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
				}

				++this.clientSideReturnAxeTickCount;
			}
		}

		super.tick();
	}

	private boolean isAcceptableReturnOwner() {
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) {
			return !(entity instanceof ServerPlayer) || !entity.isSpectator();
		} else {
			return false;
		}
	}

	@Override
	protected ItemStack getPickupItem() {
		return this.axeItem.copy();
	}

	public boolean isFoil() {
		return this.entityData.get(ID_FOIL);
	}

	@Nullable
	@Override
	protected EntityHitResult findHitEntity(Vec3 p_37575_, Vec3 p_37576_) {
		return this.dealtDamage ? null : super.findHitEntity(p_37575_, p_37576_);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		this.shouldSpin = false;
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		float f;
		if (this.axeItem.getItem() instanceof DiggerItem digger) {
			f = digger.getAttackDamage();
		} else {
			f = 5.0F;
		}
		if (entity instanceof LivingEntity livingentity) {
			f += EnchantmentHelper.getDamageBonus(this.axeItem, livingentity.getMobType());
		}

		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().trident(this, entity1 == null ? this : entity1);
		this.dealtDamage = true;
		SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
		if (entity.hurt(damagesource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity livingentity1) {
				if (entity1 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity) entity1, livingentity1);
				}

				this.doPostHurtEffects(livingentity1);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
		float f1 = 1.0F;

		this.playSound(soundevent, f1, 1.0F);
		this.shouldSpin = false;
	}

	@Override
	protected boolean tryPickup(Player player) {
		return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
	}

	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents.TRIDENT_HIT_GROUND;
	}

	@Override
	public void playerTouch(Player player) {
		if (this.ownedBy(player) || this.getOwner() == null) {
			super.playerTouch(player);
		}

	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("AxeInfo", 10)) {
			this.axeItem = ItemStack.of(tag.getCompound("AxeInfo"));
		}

		this.dealtDamage = tag.getBoolean("DealtDamage");
		this.entityData.set(ID_LOYALTY, (byte) EnchantmentHelper.getLoyalty(this.axeItem));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.put("AxeInfo", this.axeItem.save(new CompoundTag()));
		tag.putBoolean("DealtDamage", this.dealtDamage);
	}

	@Override
	public void tickDespawn() {
		int i = this.entityData.get(ID_LOYALTY);
		if (this.pickup != AbstractArrow.Pickup.ALLOWED || i <= 0) {
			super.tickDespawn();
		}

	}

	@Override
	public boolean shouldRender(double x, double y, double z) {
		return true;
	}
}
