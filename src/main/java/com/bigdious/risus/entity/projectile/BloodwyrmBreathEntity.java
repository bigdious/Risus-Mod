package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;

public class BloodwyrmBreathEntity extends AbstractArrow {
	@Nullable
	private BlockState lastState;
	private int life;

	public BloodwyrmBreathEntity(EntityType<BloodwyrmBreathEntity> type, Level level) {
		super(type, level, ItemStack.EMPTY);
	}

	public BloodwyrmBreathEntity(Level world, LivingEntity living) {
		super(RisusEntities.BLOODWYRM_BREATH.get(), living, world, ItemStack.EMPTY);
		this.pickup = Pickup.DISALLOWED;
	}

	public void handleEntityEvent(byte p_37402_) {
		if (p_37402_ == 3) {
			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(RisusParticles.JOYFLAME.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		entity.hurt(this.damageSources().thrown(this, this.getOwner()), 1);
		entity.igniteForSeconds(10);
	}

	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents.FIRE_EXTINGUISH;
	}

	@Nullable
	@Override
	public void tick() {
		super.tick();
		boolean flag = this.isNoPhysics();
		Vec3 vec3 = this.getDeltaMovement();
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			double d0 = vec3.horizontalDistance();
			this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) (180F / (float) Math.PI)));
			this.setXRot((float) (Mth.atan2(vec3.y, d0) * (double) (180F / (float) Math.PI)));
			this.yRotO = this.getYRot();
			this.xRotO = this.getXRot();
		}

		BlockPos blockpos = this.blockPosition();
		BlockState blockstate = this.level().getBlockState(blockpos);
		if (!blockstate.isAir() && !flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
			if (!voxelshape.isEmpty()) {
				Vec3 vec31 = this.position();

				for (AABB aabb : voxelshape.toAabbs()) {
					if (aabb.move(blockpos).contains(vec31)) {
						this.inGround = true;
						break;
					}
				}
			}
		}

		if (this.shakeTime > 0) {
			--this.shakeTime;
		}

		if (this.isInWaterOrRain() || blockstate.is(Blocks.POWDER_SNOW) || this.isInFluidType((fluidType, height) -> this.canFluidExtinguish(fluidType))) {
			this.clearFire();
		}

		if (this.inGround && !flag) {
			if (this.lastState != blockstate && this.shouldFall()) {
				this.startFalling();
			} else if (!this.level().isClientSide) {
				this.tickDespawn();
			}

			++this.inGroundTime;
		} else {
			this.inGroundTime = 0;
			Vec3 vec32 = this.position();
			Vec3 vec33 = vec32.add(vec3);
			HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
			if (hitresult.getType() != HitResult.Type.MISS) {
				vec33 = hitresult.getLocation();
			}

			while (!this.isRemoved()) {
				EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
				if (entityhitresult != null) {
					hitresult = entityhitresult;
				}

				if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
					Entity entity = ((EntityHitResult) hitresult).getEntity();
					Entity entity1 = this.getOwner();
					if (entity instanceof Player && entity1 instanceof Player && !((Player) entity1).canHarmPlayer((Player) entity)) {
						hitresult = null;
						entityhitresult = null;
					}
				}

				if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag) {
					if (EventHooks.onProjectileImpact(this, hitresult))
						break;
					this.onHit(hitresult);
					this.hasImpulse = true;
				}

				if (entityhitresult == null || this.getPierceLevel() <= 0) {
					break;
				}

				hitresult = null;
			}

			vec3 = this.getDeltaMovement();
			double d5 = vec3.x;
			double d6 = vec3.y;
			double d1 = vec3.z;
			for (int i = 0; i < 4; ++i) {
				this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}

			double d7 = this.getX() + d5;
			double d2 = this.getY() + d6;
			double d3 = this.getZ() + d1;
			double d4 = vec3.horizontalDistance();
			if (flag) {
				this.setYRot((float) (Mth.atan2(-d5, -d1) * (double) (180F / (float) Math.PI)));
			} else {
				this.setYRot((float) (Mth.atan2(d5, d1) * (double) (180F / (float) Math.PI)));
			}

			this.setXRot((float) (Mth.atan2(d6, d4) * (double) (180F / (float) Math.PI)));
			this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
			this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
			float f = 0.99F;
			float f1 = 0.05F;
			if (this.isInWater()) {
				for (int j = 0; j < 4; ++j) {
					float f2 = 0.25F;
					this.level().addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25D, d2 - d6 * 0.25D, d3 - d1 * 0.25D, d5, d6, d1);
				}

				f = this.getWaterInertia();
			}

			this.setDeltaMovement(vec3.scale((double) f));
			if (!this.isNoGravity() && !flag) {
				Vec3 vec34 = this.getDeltaMovement();
				this.setDeltaMovement(vec34.x, vec34.y - (double) 0.05F, vec34.z);
			}

			this.setPos(d7, d2, d3);
			this.checkInsideBlocks();
		}
	}

	@Nullable
	private boolean shouldFall() {
		return this.inGround && this.level().noCollision((new AABB(this.position(), this.position())).inflate(0.06D));
	}

	@Nullable
	private void startFalling() {
		this.inGround = false;
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.multiply((double) (this.random.nextFloat() * 0.2F), (double) (this.random.nextFloat() * 0.2F), (double) (this.random.nextFloat() * 0.2F)));
		this.life = 0;
	}

	@Override
	protected void tickDespawn() {
		++this.life;
		if (this.life >= 100) {
			this.discard();
		}

	}

	@Override
	public byte getPierceLevel() {
		return 40;
	}

	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return null;
	}
}

