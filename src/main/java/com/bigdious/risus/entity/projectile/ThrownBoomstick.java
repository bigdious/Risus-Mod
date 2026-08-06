package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.event.ItemEffectEvents;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ThrownBoomstick extends AbstractArrow {
	private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(ThrownBoomstick.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ThrownBoomstick.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> ID_POWER = SynchedEntityData.defineId(ThrownBoomstick.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> ID_WINDBURST = SynchedEntityData.defineId(ThrownBoomstick.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> ID_FLAME = SynchedEntityData.defineId(ThrownBoomstick.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Boolean> IS_DRUMSTICK = SynchedEntityData.defineId(ThrownBoomstick.class, EntityDataSerializers.BOOLEAN);

	private boolean dealtDamage;
	public int clientSideReturnBoomstickTickCount;

	public ThrownBoomstick(EntityType<ThrownBoomstick> type, Level level) {
		super(type, level);
	}

	public ThrownBoomstick(Level level, LivingEntity owner, ItemStack pPickupItemStack) {
		super(RisusEntities.THROWN_BOOMSTICK.get(), owner, level, pPickupItemStack, null);
		this.entityData.set(IS_DRUMSTICK, pPickupItemStack.getHoverName().getString().equalsIgnoreCase("drumstick"));
		this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(pPickupItemStack));
		this.entityData.set(ID_POWER, (byte) pPickupItemStack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.POWER))));
		this.entityData.set(ID_WINDBURST, (byte) pPickupItemStack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.WIND_BURST))));
		this.entityData.set(ID_FLAME, (byte) pPickupItemStack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.FLAME))));
		this.entityData.set(ID_FOIL, pPickupItemStack.hasFoil());
	}

	private byte getLoyaltyFromItem(ItemStack p_345571_) {
		return this.level() instanceof ServerLevel serverlevel
			? (byte) Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverlevel, p_345571_, this), 0, 127)
			: 0;
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(RisusItems.BOOMSTICK.get());
	}
	@Override
	public boolean displayFireAnimation() {
		return this.entityData.get(ID_FLAME) > 0 ;
	}

	@Override
	public void tick() {
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
				if (this.clientSideReturnBoomstickTickCount == 0) {
					this.playSound(RisusSoundEvents.BOOMSTICK_RETURN.get(), 10.0F, 1.0F);
				}
				++this.clientSideReturnBoomstickTickCount;
			}
		}

		super.tick();
	}

	private boolean isAcceptableReturnOwner() {
		Entity entity = this.getOwner();
		return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayer) || !entity.isSpectator());
	}

	public boolean isFoil() {
		return this.entityData.get(ID_FOIL);
	}
	public boolean isDrumstick() {return this.entityData.get(IS_DRUMSTICK);}

	@Nullable
	@Override
	protected EntityHitResult findHitEntity(Vec3 p_37575_, Vec3 p_37576_) {
		return this.dealtDamage ? null : super.findHitEntity(p_37575_, p_37576_);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		Entity entity1 = this.getOwner();

		if (entity.getType() == EntityType.ENDERMAN) {
			return;
		}

		if (entity instanceof LivingEntity livingEntity && livingEntity.level() instanceof ServerLevel && this.level() instanceof ServerLevel) {
			ItemEffectEvents.boomstickLogic(this.getPickupItemStackOrigin(), livingEntity, this);
			if (this.isDrumstick()) {

			}
			this.doPostHurtEffects(livingEntity);
			this.shootFromRotation(entity1, (float) this.getRandom().nextIntBetweenInclusive(180, 360), (float) this.getRandom().nextIntBetweenInclusive(0, 360), 0.0F, 1F, 1.0F);

			this.playSound(SoundEvents.CHICKEN_HURT, 1.5F, 1.0F);
		}

		float f1 = 1.0F;
	}

	@Override
	protected boolean tryPickup(Player player) {
		boolean ret = super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());

		if (ret & this.entityData.get(ID_LOYALTY) > 0 && this.ownedBy(player)) {
			ItemEffectEvents.boomstickLogic(this.getPickupItemStackOrigin(), player, player);
		}
		return ret;
	}


	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return RisusSoundEvents.BOOMSTICK_HIT_GROUND.get();
	}

	@Override
	public void playerTouch(Player player) {
		if (this.ownedBy(player) || this.getOwner() == null) {
			super.playerTouch(player);
		}

	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
		super.defineSynchedData(pBuilder);
		pBuilder.define(IS_DRUMSTICK, false);
		pBuilder.define(ID_LOYALTY, (byte) 0);
		pBuilder.define(ID_POWER, (byte) 0);
		pBuilder.define(ID_WINDBURST, (byte) 0);
		pBuilder.define(ID_FLAME, (byte) 0);
		pBuilder.define(ID_FOIL, false);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		this.dealtDamage = tag.getBoolean("DealtDamage");
		this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(this.getPickupItemStackOrigin()));
		this.entityData.set(ID_POWER, (byte) this.getPickupItemStackOrigin().getEnchantmentLevel((this.level().registryAccess().holderOrThrow(Enchantments.POWER))));
		this.entityData.set(ID_WINDBURST, (byte) this.getPickupItemStackOrigin().getEnchantmentLevel((this.level().registryAccess().holderOrThrow(Enchantments.WIND_BURST))));
		this.entityData.set(ID_FLAME, (byte) this.getPickupItemStackOrigin().getEnchantmentLevel((this.level().registryAccess().holderOrThrow(Enchantments.FLAME))));
		this.entityData.set(ID_FOIL, this.getPickupItemStackOrigin().hasFoil());
		this.entityData.set(IS_DRUMSTICK, this.getPickupItemStackOrigin().getHoverName().getString().equalsIgnoreCase("drumstick"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("DealtDamage", this.dealtDamage);
	}

	@Override
	public void tickDespawn() {
		int i = this.entityData.get(ID_LOYALTY);
		if (this.pickup != Pickup.ALLOWED || i <= 0) {
			super.tickDespawn();
		}
	}

	@Override
	public boolean shouldRender(double x, double y, double z) {
		return true;
	}
}
