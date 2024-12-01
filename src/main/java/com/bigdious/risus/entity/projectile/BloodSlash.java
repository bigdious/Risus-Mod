package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusParticles;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class BloodSlash extends AbstractArrow {

	private static final EntityDataAccessor<Byte> ID_POWER = SynchedEntityData.defineId(BloodSlash.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> ID_PIERCING = SynchedEntityData.defineId(BloodSlash.class, EntityDataSerializers.BYTE);
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	private List<Entity> piercedAndKilledEntities;
	private double baseDamage;
	protected boolean inGround;
	private int life;

	public BloodSlash(EntityType<BloodSlash> type, Level level) {
		super(type, level);
	}

	public BloodSlash(Level level, LivingEntity owner, ItemStack pPickupItemStack) {
		super(RisusEntities.BLOODSLASH.get(), owner, level, pPickupItemStack, null);
		this.entityData.set(ID_POWER, (byte) pPickupItemStack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.POWER))));
		this.entityData.set(ID_PIERCING, (byte) pPickupItemStack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.PIERCING))));
		this.baseDamage= 15.0;
	}
	@Override
	public boolean isNoGravity() {
		return true;
	}
	@Override
	protected ItemStack getDefaultPickupItem() {
		return null;
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
		super.defineSynchedData(pBuilder);
		pBuilder.define(ID_POWER, (byte)0);
		pBuilder.define(ID_PIERCING, (byte)0);
	}
	public byte getPierceLevel() {
		return this.entityData.get(ID_PIERCING);
	}
	public void tick() {
		super.tick();
		++this.life;
		if (this.life >= 50) {
			this.discard();
		}
		level().addParticle(RisusParticles.BLOODSLASH_TRAIL.get(), true, this.getX(), this.getRandomY()-1.5+(Math.random() * 2.8), this.getZ(), 0, 0,0);
		playSound(SoundEvents.BREEZE_WHIRL);
	}
	protected float getWaterInertia() {
		return 1F;
	}
	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		float f;

		f = this.entityData.get(ID_POWER);
		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().source(RisusDamageTypes.BLOODSLASH, entity1 == null ? this : entity1);
		if (this.level() instanceof ServerLevel serverlevel) {
			f += EnchantmentHelper.modifyDamage(serverlevel, this.getPickupItemStackOrigin(), entity, damagesource, f);
		}

		if (this.getPierceLevel() > 0) {
			if (this.piercingIgnoreEntityIds == null) {
				this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
			}

			if (this.piercedAndKilledEntities == null) {
				this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
			}

			if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
				this.discard();
				return;
			}

			this.piercingIgnoreEntityIds.add(entity.getId());
		}


		if (entity.hurt(damagesource, (float)baseDamage+this.entityData.get(ID_POWER))) {
			if (entity instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity)entity;
				this.doKnockback(livingentity, damagesource);
				Level var13 = this.level();
				if (var13 instanceof ServerLevel) {
					ServerLevel serverlevel1 = (ServerLevel)var13;
					EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, livingentity, damagesource, this.getWeaponItem());
				}

				this.doPostHurtEffects(livingentity);

				if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
					this.piercedAndKilledEntities.add(livingentity);
				}
			}
			if (this.getPierceLevel() <= 0) {
				this.discard();
			}
		}
	}

	@Override
	protected boolean tryPickup(Player player) {
		return false;
	}

	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents.BREEZE_LAND;
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

		this.entityData.set(ID_POWER, (byte) this.getPickupItemStackOrigin().getEnchantmentLevel((this.level().registryAccess().holderOrThrow(Enchantments.POWER))));
		this.entityData.set(ID_PIERCING, (byte)  this.getPickupItemStackOrigin().getEnchantmentLevel((this.level().registryAccess().holderOrThrow(Enchantments.PIERCING))));
	}
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putShort("life", (short) this.life);
		compound.putDouble("damage", this.baseDamage);
		compound.putByte("PierceLevel", this.getPierceLevel());
	}

	@Override
	protected boolean canHitEntity(Entity target) {
		return super.canHitEntity(target) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(target.getId()));
	}
	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		this.discard();
	}

	@Override
	public boolean shouldRender(double x, double y, double z) {
		return true;
	}
}
