package com.bigdious.risus.entity;

import com.bigdious.risus.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Weaver extends Spider implements CacheTargetOnClient {
	private int attackTimer;

	private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(Weaver.class, EntityDataSerializers.INT);
	@Nullable
	private LivingEntity clientSideCachedAttackTarget;
	public final AnimationState leapAnim = new AnimationState();
	public final AnimationState biteAnim = new AnimationState();
	public int memories;


	public Weaver(EntityType<? extends Spider> type, Level level) {
		super(type, level);
		this.xpReward = 5;
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_ATTACK_TARGET, 0);
	}
	@Override
	public void readAdditionalSaveData(CompoundTag pCompound) {
		super.readAdditionalSaveData(pCompound);
		if (pCompound.contains("Memories")) {
			this.memories = pCompound.getInt("Memories");
		}
	}
	@Override
	public void addAdditionalSaveData(CompoundTag pCompound) {
		super.addAdditionalSaveData(pCompound);
		pCompound.putInt("Memories", this.memories);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F) {
			@Override
			public void start() {
				super.start();
				Weaver.this.level().broadcastEntityEvent(Weaver.this, (byte) 66);
			}
		});
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new WeaverHurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new WeaverNeastAttackableGoal(this, LivingEntity.class, true,
			entity -> !(entity instanceof ArmorStand)
				&& !(entity instanceof Holder)
				&& !(entity instanceof QuestionMark)
				&& !(entity instanceof Stalker)
				&& !(entity instanceof Lover)
				&& !(entity instanceof Angel)));
	}

	public void aiStep() {
		super.aiStep();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
		if (this.memories>=3) {
			if (this.level().getBlockState(this.blockPosition()).is(Blocks.AIR) && this.onGround() && this.level().getEntitiesOfClass(Weaver.class, this.getBoundingBox().inflate(10)).size()<2) {
				this.kill();
				this.level().setBlock(this.blockPosition(), RisusBlocks.WEAVER_NEST.get().defaultBlockState(), 3);
			}
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_33814_) {
		return null;
	}


	public RisusMobType getRisusMobType() {
		return RisusMobType.OFFSPRING;
	}
	public static class WeaverEffectsGroupData implements SpawnGroupData {
		@Nullable
		public net.minecraft.core.Holder<MobEffect> effect;

		public void setRandomEffect(RandomSource pRandom) {
			int i = pRandom.nextInt(8);
			if (i <= 1) {
				this.effect = MobEffects.MOVEMENT_SPEED;
			} else if (i <= 2) {
				this.effect = MobEffects.DAMAGE_BOOST;
			} else if (i <= 3) {
				this.effect = MobEffects.REGENERATION;
			} else if (i <= 4) {
				this.effect = MobEffects.JUMP;
			} else if (i <= 5) {
				this.effect = MobEffects.DAMAGE_RESISTANCE;
			} else if (i <= 6) {
				this.effect = MobEffects.FIRE_RESISTANCE;
			} else if (i <= 7) {
				this.effect = MobEffects.INVISIBILITY;
			}
		}
	}
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
		pSpawnGroupData = super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
		RandomSource randomsource = pLevel.getRandom();

		if (pSpawnGroupData == null) {
			pSpawnGroupData = new Weaver.WeaverEffectsGroupData();
			if (pLevel.getDifficulty() == Difficulty.HARD && randomsource.nextFloat() < 0.1F * pDifficulty.getSpecialMultiplier()) {
				((Weaver.WeaverEffectsGroupData)pSpawnGroupData).setRandomEffect(randomsource);
			}
		}

		if (pSpawnGroupData instanceof Weaver.WeaverEffectsGroupData weaverEffectsGroupData) {
			net.minecraft.core.Holder<MobEffect> holder = weaverEffectsGroupData.effect;
			if (holder != null) {
				this.addEffect(new MobEffectInstance(holder, -1, 2));
			}
		}

		return pSpawnGroupData;
	}

	@Override
	public void makeStuckInBlock(BlockState p_33796_, Vec3 p_33797_) {
		if (!p_33796_.is(RisusBlocks.BLOODWEAVE.get())) {
			super.makeStuckInBlock(p_33796_, p_33797_);
		}

	}

	@Override
	public boolean canAttackType(EntityType<?> type) {
		return super.canAttackType(type) && type != this.getType();
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		this.attackTimer = 10;
		this.level().broadcastEntityEvent(this, (byte) 4);
		if (super.doHurtTarget(entity)) {
			if (entity instanceof LivingEntity living) {
				Level level = living.level();
				BlockPos pos = living.getOnPos();
				int i = 3;
				if (this.level().getDifficulty() == Difficulty.NORMAL) {
					i = 5;
				} else if (this.level().getDifficulty() == Difficulty.HARD) {
					i = 8;
				}
				living.addEffect(new MobEffectInstance(RisusMobEffects.AMNESIA, i * 20, 0), this);
				if (living.getHealth() == 0 && level.getBlockState(pos.above()).is(Blocks.AIR)) {
					this.memories++;
					level.setBlock(pos.above(), RisusBlocks.BLOODWEAVE.get().defaultBlockState(), 3);
				}

			}
		}

		return false;
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

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		}
		if (id == 66) {
			this.leapAnim.start(this.tickCount);
		}
	}

	private static class WeaverHurtByTargetGoal extends HurtByTargetGoal {

		public WeaverHurtByTargetGoal(PathfinderMob mob) {
			super(mob);
		}

		@Override
		public void start() {
			super.start();
			((Weaver) this.mob).setActiveAttackTarget(this.targetMob.getId());
		}

		@Override
		public void stop() {
			super.stop();
			((Weaver) this.mob).setActiveAttackTarget(0);
		}
	}

	private static class WeaverNeastAttackableGoal extends NearestAttackableTargetGoal<LivingEntity> {

		public WeaverNeastAttackableGoal(Mob mob, Class<LivingEntity> attack, boolean needsLOS, Predicate<LivingEntity> predicate) {
			super(mob, attack, needsLOS, predicate);
		}

		@Override
		public void start() {
			super.start();
			((Weaver) this.mob).setActiveAttackTarget(this.target.getId());
		}

		@Override
		public void stop() {
			super.stop();
			((Weaver) this.mob).setActiveAttackTarget(0);
		}

	}
	public int getAttackTimer() {
		return this.attackTimer;
	}
}
