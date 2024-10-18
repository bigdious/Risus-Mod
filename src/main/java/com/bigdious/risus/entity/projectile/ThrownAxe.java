package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ThrownAxe extends AbstractArrow {
//	loyalty requirement removed until enchantments are fixed

//	private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(ThrownAxe.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ThrownAxe.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> ID_SHARPNESS = SynchedEntityData.defineId(ThrownAxe.class, EntityDataSerializers.BYTE);
	private boolean dealtDamage;
	private boolean shouldSpin = true;
	public int spinTickCount;
	public int clientSideReturnAxeTickCount;

	public ThrownAxe(EntityType<ThrownAxe> type, Level level) {
		super(type, level);
	}

	public ThrownAxe(Level level, LivingEntity owner, ItemStack pPickupItemStack) {
		super(RisusEntities.THROWN_AXE.get(), owner, level, pPickupItemStack, null);
//		this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(pPickupItemStack));
		this.entityData.set(ID_SHARPNESS, (byte) pPickupItemStack.getDamageValue());
		this.entityData.set(ID_FOIL, pPickupItemStack.hasFoil());
	}
	private byte getLoyaltyFromItem(ItemStack p_345571_) {
		return this.level() instanceof ServerLevel serverlevel
			? (byte) Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverlevel, p_345571_, this), 0, 127)
			: 0;
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
		//this.entityData.get(ID_LOYALTY);
		int i = 2;
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
		return entity == null || !entity.isAlive() ? false : !(entity instanceof ServerPlayer) || !entity.isSpectator();
	}


	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(RisusItems.CRESCENT_DISASTER.get());
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
	protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
		super.defineSynchedData(pBuilder);
//		pBuilder.define(ID_LOYALTY, (byte)0);
		pBuilder.define(ID_SHARPNESS, (byte)0);
		pBuilder.define(ID_FOIL, false);
	}
	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		float f;

		f = this.entityData.get(ID_SHARPNESS);
		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().trident(this, entity1 == null ? this : entity1);
		if (this.level() instanceof ServerLevel serverlevel) {
			f += EnchantmentHelper.modifyDamage(serverlevel, this.getPickupItemStackOrigin(), entity, damagesource, f);
		}



		this.dealtDamage = true;
		SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
		//10 is base damage of crescent, update if it changes
		if (entity.hurt(damagesource, 10+f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity livingentity1) {
				if (entity1 instanceof LivingEntity) {
					this.doPostHurtEffects(livingentity1);
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

		this.dealtDamage = tag.getBoolean("DealtDamage");
//		this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(this.getPickupItemStackOrigin()));
		this.entityData.set(ID_SHARPNESS, (byte) this.getPickupItemStackOrigin().getDamageValue());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("DealtDamage", this.dealtDamage);
	}

	@Override
	public void tickDespawn() {
//		this.entityData.get(ID_LOYALTY);
		int i = 2;
		if (this.pickup != AbstractArrow.Pickup.ALLOWED || i <= 0) {
			super.tickDespawn();
		}

	}

	@Override
	public boolean shouldRender(double x, double y, double z) {
		return true;
	}
}
