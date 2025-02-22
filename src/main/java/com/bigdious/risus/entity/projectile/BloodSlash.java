package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class BloodSlash extends Projectile {

	private static final EntityDataAccessor<Byte> ID_POWER = SynchedEntityData.defineId(BloodSlash.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> ID_PIERCING = SynchedEntityData.defineId(BloodSlash.class, EntityDataSerializers.BYTE);
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	private float baseDamage;
	@Nullable
	private ItemStack weapon = null;

	public BloodSlash(EntityType<BloodSlash> type, Level level) {
		super(type, level);
	}

	public BloodSlash(Level level, LivingEntity owner, @Nullable ItemStack weapon) {
		super(RisusEntities.BLOODSLASH.get(), level);
		this.setOwner(owner);
		this.setPos(owner.getX(), owner.getEyeY() - 0.1F, owner.getZ());
		//remember to update if needed
		this.baseDamage = 12.0F;
		this.weapon = weapon;
		if (weapon != null) {
			this.getEntityData().set(ID_POWER, (byte) weapon.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.POWER))));
			this.getEntityData().set(ID_PIERCING, (byte) weapon.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.PIERCING))));

		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(ID_POWER, (byte) 0);
		builder.define(ID_PIERCING, (byte) 0);
	}

	public byte getPierceLevel() {
		return this.getEntityData().get(ID_PIERCING);
	}

	public void tick() {
		super.tick();

		HitResult blockResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
		HitResult entityResult = getEntityHitResult(this.position(), this, this::canHitEntity, this.getDeltaMovement(), this.level(), 0.3F, ClipContext.Block.COLLIDER);
		if (entityResult.getType() == HitResult.Type.ENTITY && !EventHooks.onProjectileImpact(this, entityResult)) {
			this.hitTargetOrDeflectSelf(entityResult);
		} else if (blockResult.getType() == HitResult.Type.BLOCK && !EventHooks.onProjectileImpact(this, blockResult)) {
			this.onHit(blockResult);
		}

		this.checkInsideBlocks();
		Vec3 vec3 = this.getDeltaMovement();
		double d0 = this.getX() + vec3.x;
		double d1 = this.getY() + vec3.y;
		double d2 = this.getZ() + vec3.z;
		this.updateRotation();

		this.setDeltaMovement(vec3.scale(0.99F));
		this.applyGravity();
		this.setPos(d0, d1, d2);

		this.level().addParticle(RisusParticles.BLOODSLASH_TRAIL.get(), true, this.getX(), this.getRandomY() - 1.5 + (Math.random() * 2.8), this.getZ(), 0, 0, 0);
		if (this.tickCount % 40 == 0) this.playSound(RisusSoundEvents.BLOOD_SLASH_WHOOSH.get());
		if (tickCount > 70) this.kill();
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		float damage = this.baseDamage;

		damage += this.getEntityData().get(ID_POWER);
		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().source(RisusDamageTypes.BLOODSLASH, entity1 == null ? this : entity1);
		if (this.level() instanceof ServerLevel serverlevel && this.weapon != null) {
			damage += EnchantmentHelper.modifyDamage(serverlevel, this.weapon, entity, damagesource, damage);
		}

		if (this.getPierceLevel() > 0) {
			if (this.piercingIgnoreEntityIds == null) {
				this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
			}

			if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
				this.discard();
				return;
			}

			this.piercingIgnoreEntityIds.add(entity.getId());
		}

		if (entity.hurt(damagesource, damage)) {
			if (entity instanceof LivingEntity livingentity) {
				double d0 = this.weapon != null && this.level() instanceof ServerLevel serverlevel ? EnchantmentHelper.modifyKnockback(serverlevel, this.weapon, entity, damagesource, 0.0F) : 0.0F;
				if (d0 > 0.0) {
					double d1 = Math.max(0.0, 1.0 - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
					Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(d0 * 0.6 * d1);
					if (vec3.lengthSqr() > 0.0) {
						entity.push(vec3.x, 0.1, vec3.z);
					}
				}
				if (this.level() instanceof ServerLevel serverlevel1) {
					EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, livingentity, damagesource, this.getWeaponItem());
				}
			}
			if (this.getPierceLevel() <= 0) {
				this.discard();
			}
		}
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

	private static HitResult getEntityHitResult(Vec3 pos, Entity projectile, Predicate<Entity> filter, Vec3 deltaMovement, Level level, float margin, ClipContext.Block clipContext) {
		AABB entityHitbox = projectile.getBoundingBox().inflate(0.0D, 1.25D, 0.0D).move(0.0D, -0.25D, 0.0D);
		Vec3 vec3 = pos.add(deltaMovement);
		HitResult hitresult = level.clip(new ClipContext(pos, vec3, clipContext, ClipContext.Fluid.NONE, projectile));
		if (hitresult.getType() != HitResult.Type.MISS) {
			vec3 = hitresult.getLocation();
		}

		HitResult hitresult1 = ProjectileUtil.getEntityHitResult(level, projectile, pos, vec3, entityHitbox.expandTowards(deltaMovement).inflate(1.0), filter, 1.3F);
		if (hitresult1 != null) {
			hitresult = hitresult1;
		}

		return hitresult;
	}
}
