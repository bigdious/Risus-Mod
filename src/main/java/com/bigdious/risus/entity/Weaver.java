package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class Weaver extends Monster implements CacheTargetOnClient {

	private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(Weaver.class, EntityDataSerializers.INT);
	@Nullable
	private LivingEntity clientSideCachedAttackTarget;

	public Weaver(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(DATA_ID_ATTACK_TARGET, 0);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new WeaverHurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new WeaverNeastAttackableGoal(this, LivingEntity.class, true, entity -> !(entity instanceof ArmorStand) && !(entity instanceof Angel)));
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	@Override
	public boolean canAttackType(EntityType<?> type) {
		return super.canAttackType(type) && type != this.getType();
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (super.doHurtTarget(entity)) {
			if (entity instanceof LivingEntity living) {
				int i = 3;
				if (this.getLevel().getDifficulty() == Difficulty.NORMAL) {
					i = 5;
				} else if (this.getLevel().getDifficulty() == Difficulty.HARD) {
					i = 8;
				}
				living.addEffect(new MobEffectInstance(RisusMobEffects.AMNESIA.get(), i * 20, 0), this);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getDataTarget() {
		return this.getEntityData().get(DATA_ID_ATTACK_TARGET);
	}

	@Override
	public @Nullable LivingEntity getCachedAttackTarget() {
		return this.clientSideCachedAttackTarget;
	}

	@Override
	public void setCachedAttackTarget(@Nullable LivingEntity living) {
		this.clientSideCachedAttackTarget = living;
	}

	public void setActiveAttackTarget(int target) {
		this.entityData.set(DATA_ID_ATTACK_TARGET, target);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
		super.onSyncedDataUpdated(accessor);
		if (DATA_ID_ATTACK_TARGET.equals(accessor)) {
			this.clientSideCachedAttackTarget = null;
		}
	}

	private static class WeaverHurtByTargetGoal extends HurtByTargetGoal {

		public WeaverHurtByTargetGoal(PathfinderMob mob) {
			super(mob);
		}

		@Override
		public void start() {
			super.start();
			((Weaver)this.mob).setActiveAttackTarget(this.targetMob.getId());
		}

		@Override
		public void stop() {
			super.stop();
			((Weaver)this.mob).setActiveAttackTarget(0);
		}
	}

	private static class WeaverNeastAttackableGoal extends NearestAttackableTargetGoal<LivingEntity> {

		public WeaverNeastAttackableGoal(Mob mob, Class<LivingEntity> attack, boolean needsLOS, Predicate<LivingEntity> predicate) {
			super(mob, attack, needsLOS, predicate);
		}

		@Override
		public void start() {
			super.start();
			((Weaver)this.mob).setActiveAttackTarget(this.target.getId());
		}

		@Override
		public void stop() {
			super.stop();
			((Weaver)this.mob).setActiveAttackTarget(0);
		}
	}
}
