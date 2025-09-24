package com.bigdious.risus.entity.creatures;

import com.bigdious.risus.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

public class Lover extends Monster {

	public Lover(EntityType<? extends Lover> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.moveControl = new FlyingMoveControl(this, 20, true);
		this.xpReward = 0;
	}

	@Override
	public boolean canBeLeashed() {
		return true;
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
			.add(Attributes.ATTACK_DAMAGE, 0.0F)
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
	public boolean isOnFire() {
		return false;
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
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.85D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1D));
		this.goalSelector.addGoal(1, new TemptGoal(this, 1.25, (itemStack) -> {
			return itemStack.is(ItemTags.BEE_FOOD);
		}, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true,
			entity -> entity.getType().builtInRegistryHolder().getData(RisusDataMaps.LOVER_CONVERSION) != null
		));
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean doHurtTarget(Entity entity) {
		//DO NOT .super HERE
		if (entity instanceof Mob mob && entity.level() instanceof ServerLevel serverLevel && mob.getType().builtInRegistryHolder().getData(RisusDataMaps.LOVER_CONVERSION) != null) {
			tryConvertEntity(serverLevel, (EntityType<? extends Mob>) mob.getType().builtInRegistryHolder().getData(RisusDataMaps.LOVER_CONVERSION).result(), mob);
		}
		return false;
	}

	private  <T extends Mob> boolean tryConvertEntity(ServerLevel level, EntityType<T> to, Mob from) {
		boolean flag = true;
		if (EventHooks.canLivingConvert(from, to, (timer) -> {})) {
			if (level.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
				return flag;
			}

			T offspring = from.convertTo(to, false);
			if (offspring != null) {
				offspring.finalizeSpawn(level, level.getCurrentDifficultyAt(offspring.blockPosition()), MobSpawnType.CONVERSION, null);
				EventHooks.onLivingConvert(from, offspring);
				if (!this.isSilent()) {
					this.playSound(RisusSoundEvents.LOVER_INFECT.get(), 2.0F, (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
				}
				BlockState spreading = RisusBlocks.SPREADING_REMAINS.get().defaultBlockState().setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), true);
				if (spreading.canSurvive(this.level(), offspring.blockPosition()) && level.getBlockState(offspring.blockPosition()).isAir() && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
					this.level().setBlockAndUpdate(offspring.blockPosition(), spreading);
				}
				for (int i = 0; i < 10; ++i) {
					level.sendParticles(ParticleTypes.HEART, from.getRandomX(0.5), from.getRandomY(), from.getRandomZ(0.5), 1, 0, 0.0, 0.0, 0.0);
					level.sendParticles(RisusParticles.RISUS_SOUL_PARTICLE.get(), from.getRandomX(0.5), from.getRandomY(), from.getRandomZ(0.5), 1, 0, 0.0, 0.0, 0.0);
				}
				flag = false;
			}
		}
		return flag;
	}

}

