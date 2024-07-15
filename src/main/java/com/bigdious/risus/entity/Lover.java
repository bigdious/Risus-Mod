package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusMobType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class Lover extends Monster {
	public Lover(EntityType<? extends Lover> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.xpReward = 5;
		this.moveControl = new FlyingMoveControl(this, 20, true);
	}

	public static boolean canLoverSpawn(EntityType<? extends Lover> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
	}
	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}
	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 30.0D)
			.add(Attributes.FLYING_SPEED, 0.1F)
			.add(Attributes.MOVEMENT_SPEED, 0.1F)
			.add(Attributes.ATTACK_DAMAGE, 100.0D)
			.add(Attributes.FOLLOW_RANGE, 30.0D);
	}
	@Override
	protected PathNavigation createNavigation(Level p_218342_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}
	@Override
	protected void checkFallDamage(double p_218316_, boolean p_218317_, BlockState p_218318_, BlockPos p_218319_) {
	}
	@Override
	public void travel(Vec3 p_218382_) {
		if (this.isControlledByLocalInstance()) {
			if (this.isInWater()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
			} else if (this.isInLava()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5));
			} else {
				this.moveRelative(this.getSpeed(), p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.91F));
			}
		}

		this.calculateEntityAnimation(false);
	}
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.85D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1D));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Creeper.class, true));
	}
	@Override
	public boolean killedEntity(ServerLevel level, LivingEntity entity) {
		boolean flag = super.killedEntity(level, entity);
		if ((entity instanceof Creeper creeper && net.neoforged.neoforge.event.EventHooks.canLivingConvert(entity, RisusEntities.STALKER.get(), (timer) -> {}))) {
			if (level.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
				return flag;
			}

			Stalker stalker = creeper.convertTo(RisusEntities.STALKER.get(), false);
			if (stalker != null) {
				stalker.finalizeSpawn(
					level,
					level.getCurrentDifficultyAt(stalker.blockPosition()),
					MobSpawnType.CONVERSION,
					new Zombie.ZombieGroupData(false, true)
				);
				net.neoforged.neoforge.event.EventHooks.onLivingConvert(entity, stalker);
				if (!this.isSilent()) {
					level.levelEvent(null, 1026, this.blockPosition(), 0);
				}
				if (entity.level().getBlockState(entity.getOnPos().above()).is(Blocks.AIR))
				entity.level().setBlock(entity.getOnPos(), RisusBlocks.SMILING_REMAINS.get().defaultBlockState(), 3);

				flag = false;
			}
		}

		return flag;
	}


	public RisusMobType getRisusMobType() {
		return RisusMobType.OFFSPING;
	}
}

