package com.bigdious.risus.entity.creatures;

import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BabySpider extends Monster {
	private int attackTimer;
	private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

	public BabySpider(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward=1;
	}
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_FLAGS_ID, (byte) 0);
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 3.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.30F)
			.add(Attributes.FOLLOW_RANGE, 24)
			.add(Attributes.ENTITY_INTERACTION_RANGE, 1.5)
			.add(Attributes.ATTACK_DAMAGE, 1F);
	}
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1F, true));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true,
			entity -> !(entity instanceof ArmorStand)
				&& !(entity.getType().is(RisusTags.Entities.OFFSPRINGS_AND_BELOVEDS))
				&& !(entity instanceof BabySpider)
				&& !(entity instanceof Spider)));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		} else {
			super.handleEntityEvent(id);
		}
	}
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SPIDER_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_33814_) {
		return SoundEvents.SPIDER_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.SPIDER_DEATH;
	}

	protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}
	public void makeStuckInBlock(BlockState p_33796_, Vec3 p_33797_) {
		if (!p_33796_.is(Blocks.COBWEB)) {
			super.makeStuckInBlock(p_33796_, p_33797_);
		}

	}
	public boolean canBeAffected(MobEffectInstance p_33809_) {
		return !p_33809_.is(MobEffects.POISON) && super.canBeAffected(p_33809_);
	}
//make em climb
protected PathNavigation createNavigation(Level level) {
	return new WallClimberNavigation(this, level);
}

	public void tick() {
		super.tick();
		if (!this.level().isClientSide) {
			this.setClimbing(this.horizontalCollision);
		}
	}

	public boolean onClimbable() {
		return this.isClimbing();
	}

	public boolean isClimbing() {
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setClimbing(boolean climbing) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (climbing) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 &= -2;
		}

		this.entityData.set(DATA_FLAGS_ID, b0);
	}

	static {
		DATA_FLAGS_ID = SynchedEntityData.defineId(BabySpider.class, EntityDataSerializers.BYTE);
	}
}
