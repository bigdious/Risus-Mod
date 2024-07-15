package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDamageTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Maw extends Monster implements CacheTargetOnClient {

	private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(Maw.class, EntityDataSerializers.INT);
	@Nullable
	private LivingEntity clientSideCachedAttackTarget;
	private boolean eatenTNT;
	private int eatenTNTTimer;

	public final AnimationState biteAnim = new AnimationState();

	public Maw(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.setYRot(0.0F);
		this.setXRot(0.0F);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_ATTACK_TARGET, 0);
	}

	@Override
	protected void registerGoals() {
		this.targetSelector.addGoal(1, new MawNearestAttackableGoal(this, LivingEntity.class, false, LivingEntity::attackable));
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 300)
				.add(Attributes.ATTACK_DAMAGE, 40);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();
		if (!this.level().getBlockState(this.blockPosition().below()).is(RisusBlocks.MAW_GUTS.get())) {
			this.kill();
		}

		if (this.eatenTNT) {
			this.eatenTNTTimer++;
			((ServerLevel) this.level()).sendParticles(ParticleTypes.SMOKE, this.position().x(), this.position().y() + 0.5F, this.position().z(), 2, 0.1F, 0.1F, 0.1F, 0);

			if (this.eatenTNTTimer >= 60) {
				this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Level.ExplosionInteraction.NONE);
				this.kill();
			}
		}
	}

	@Override
	public boolean isNoGravity() {
		return this.isDeadOrDying() || super.isNoGravity();
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
	public boolean hurt(DamageSource source, float amount) {
		return source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && super.hurt(source, amount);
	}

	//[VanillaCopy], but include primed TNT in pushing so we can swallow it
	@Override
	protected void pushEntities() {
		List<Entity> list = this.level().getEntities(this, this.getBoundingBox(), EntitySelector.pushableBy(this).or(entity -> entity instanceof PrimedTnt).and(EntitySelector.NO_CREATIVE_OR_SPECTATOR));
		if (!list.isEmpty()) {
			int i = this.level().getGameRules().getInt(GameRules.RULE_MAX_ENTITY_CRAMMING);
			if (i > 0 && list.size() > i - 1 && this.random.nextInt(4) == 0) {
				int j = 0;

				for (Entity entity : list) {
					if (!entity.isPassenger()) {
						++j;
					}
				}

				if (j > i - 1) {
					this.hurt(this.level().damageSources().cramming(), 6.0F);
				}
			}

			for (Entity entity : list) {
				this.doPush(entity);
			}
		}
	}

	@Override
	protected void doPush(Entity entity) {
		this.level().broadcastEntityEvent(this, (byte) 66);
		if (entity instanceof LivingEntity living && living.attackable()) {
			//set up the victim to think theyre being killed by a player
			if (this.level() instanceof ServerLevel server)
				living.setLastHurtByPlayer(FakePlayerFactory.getMinecraft(server));
			//then do the actual damage
			if (entity.hurt(new DamageSource(this.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(RisusDamageTypes.GLUTTONY)), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue())) {
				this.doHurtTarget(living);
			}
		} else if (entity instanceof PrimedTnt tnt) {
			tnt.discard();
			this.eatenTNT = true;
		}
	}

	@Override
	protected void tickDeath() {
		super.tickDeath();
		if (this.deathTime == 20 && !this.level().isClientSide()) {
			if (this.level().getBlockState(this.blockPosition().below()).is(RisusBlocks.MAW_GUTS.get())) {
				this.level().destroyBlock(this.blockPosition().below(), true);
				this.playSound(SoundEvents.WITHER_BREAK_BLOCK);
			}
			//Commented for now as it breaks progression
			//for (Direction dir : Direction.Plane.HORIZONTAL) {
			//if (this.level().getBlockState(this.blockPosition().below().relative(dir)).is(RisusBlocks.GLUTTONY_SCALEPLATE.get())) {
			//this.level().destroyBlock(this.blockPosition().below().relative(dir), true);
			//}
			//}
		}
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 66) {
			this.biteAnim.start(this.tickCount);
		}
		super.handleEntityEvent(id);
	}

	public void setActiveAttackTarget(int target) {
		this.entityData.set(DATA_ID_ATTACK_TARGET, target);
	}

	@Override
	public @Nullable LivingEntity getCachedAttackTarget() {
		return this.clientSideCachedAttackTarget;
	}

	@Override
	public void setCachedAttackTarget(@Nullable LivingEntity living) {
		this.clientSideCachedAttackTarget = living;
	}

	@Override
	public int getDataTarget() {
		return this.getEntityData().get(DATA_ID_ATTACK_TARGET);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
		super.onSyncedDataUpdated(accessor);
		if (DATA_ID_ATTACK_TARGET.equals(accessor)) {
			this.clientSideCachedAttackTarget = null;
		}
	}

	private static class MawNearestAttackableGoal extends NearestAttackableTargetGoal<LivingEntity> {

		public MawNearestAttackableGoal(Mob mob, Class<LivingEntity> attack, boolean needsLOS, Predicate<LivingEntity> predicate) {
			super(mob, attack, needsLOS, predicate);
		}

		@Override
		public void start() {
			super.start();
			((Maw) this.mob).setActiveAttackTarget(this.target.getId());
		}

		@Override
		public void stop() {
			super.stop();
			((Maw) this.mob).setActiveAttackTarget(0);
		}
	}
}
