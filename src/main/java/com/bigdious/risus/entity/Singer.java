package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;

public class Singer extends Monster {
	private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(Singer.class, EntityDataSerializers.BOOLEAN);
	private int targetChangeTime;

	public Singer(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 5;
	}

	@Override
	public boolean isSensitiveToWater() {
		return true;
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 30.0D)
			.add(Attributes.MOVEMENT_SPEED, 0F)
			.add(Attributes.FOLLOW_RANGE, 48)
			.add(Attributes.JUMP_STRENGTH, 0)
			.add(Attributes.ATTACK_DAMAGE, 0F);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new Singer.SingerInducesNauseaAttack(this));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, player -> !player.getItemBySlot(EquipmentSlot.HEAD).is(Items.CARVED_PUMPKIN)));
	}

	public void setCharging(boolean charging) {
		this.entityData.set(DATA_IS_CHARGING, charging);
	}

	public boolean isCharging() {
		return this.entityData.get(DATA_IS_CHARGING);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_IS_CHARGING, false);
	}

	public void aiStep() {
		if (this.level().isClientSide) {
			for (int i = 0; i < 2; ++i) {
				this.level().addParticle(ParticleTypes.PORTAL, this.getRandomX(-0.5), this.getY() + 1, this.getRandomZ(-0.5), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.0);
			}
		}
		if (this.isAggressive() && this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) && !this.level().isClientSide) {
			RandomSource randomsource = this.getRandom();
			Level level = this.level();
			int i = Mth.floor(this.getX() - 4.0 + randomsource.nextDouble() * 8.0);
			int j = Mth.floor(this.getY() + randomsource.nextDouble() * 6.0);
			int k = Mth.floor(this.getZ() - 4.0 + randomsource.nextDouble() * 8.0);
			BlockPos blockpos = new BlockPos(i, j, k);
			BlockState blockstate = level.getBlockState(blockpos);
			if (blockstate.is(Blocks.GLASS) || blockstate.is(Blocks.GLASS_PANE)) {
				level.destroyBlock(blockpos, true);
			}
		}

		super.aiStep();
	}

	protected void customServerAiStep() {
		if (this.level().isDay() && this.tickCount >= this.targetChangeTime + 600) {
			float f = this.getLightLevelDependentMagicValue();
			if (f > 0.5F && this.level().canSeeSky(this.blockPosition()) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
				this.setTarget(null);
				this.teleport();
			}
		}

		super.customServerAiStep();
	}

	protected boolean teleport() {
		if (!this.level().isClientSide() && this.isAlive()) {
			double d0 = this.getX() + (this.random.nextDouble() - 0.5) * 64.0;
			double d1 = this.getY() + (double) (this.random.nextInt(64) - 32);
			double d2 = this.getZ() + (this.random.nextDouble() - 0.5) * 64.0;
			return this.teleport(d0, d1, d2);
		} else {
			return false;
		}
	}

	private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

		while (blockpos$mutableblockpos.getY() > this.level().getMinBuildHeight() && !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
			blockpos$mutableblockpos.move(Direction.DOWN);
		}

		BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
		boolean flag = blockstate.blocksMotion();
		boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
		if (flag && !flag1) {
			EntityTeleportEvent.EnderEntity event = EventHooks.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
			if (event.isCanceled()) {
				return false;
			} else {
				Vec3 vec3 = this.position();
				boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
				if (flag2) {
					this.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
					if (!this.isSilent()) {
						this.level().playSound(null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
						this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
					}
				}

				return flag2;
			}
		} else {
			return false;
		}
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENDERMAN_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_32527_) {
		return SoundEvents.ENDERMAN_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENDERMAN_DEATH;
	}

	public boolean hurt(DamageSource p_32494_, float p_32495_) {
		if (this.isInvulnerableTo(p_32494_)) {
			return false;
		} else {
			boolean flag = p_32494_.getDirectEntity() instanceof ThrownPotion;
			boolean flag1;
			if (!p_32494_.is(DamageTypeTags.IS_PROJECTILE) && !flag) {
				flag1 = super.hurt(p_32494_, p_32495_);
				if (!this.level().isClientSide() && !(p_32494_.getEntity() instanceof LivingEntity) && this.random.nextInt(10) != 0) {
					this.teleport();
				}

				return flag1;
			} else {
				flag1 = flag && this.hurtWithCleanWater(p_32494_, (ThrownPotion) p_32494_.getDirectEntity(), p_32495_);

				for (int i = 0; i < 64; ++i) {
					if (this.teleport()) {
						return true;
					}
				}

				return flag1;
			}
		}
	}

	private boolean hurtWithCleanWater(DamageSource p_186273_, ThrownPotion p_186274_, float p_186275_) {
		ItemStack itemstack = p_186274_.getItem();
		PotionContents potioncontents = itemstack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
		return potioncontents.is(Potions.WATER) && super.hurt(p_186273_, p_186275_);
	}

	static class SingerInducesNauseaAttack extends Goal {
		private final Singer singer;
		public int chargeTime;


		public SingerInducesNauseaAttack(Singer singer) {
			this.singer = singer;
		}

		public boolean canUse() {
			return this.singer.getTarget() != null;
		}

		public void start() {
			this.chargeTime = 0;
			singer.setAggressive(true);
		}

		public void stop() {
			this.singer.setCharging(false);
			singer.setAggressive(false);
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			LivingEntity livingentity = this.singer.getTarget();
			if (livingentity != null) {
				this.singer.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
				if (this.singer.hasLineOfSight(livingentity)) {
					++this.chargeTime;
					if (this.chargeTime == 4) {
						singer.playSound(RisusSoundEvents.SINGER_SCREAM.get(), 4, 1.1F);
						livingentity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, false, true));
						this.chargeTime = -8;
					}
				}
			} else if (this.chargeTime > 0) {
				--this.chargeTime;
			}

			this.singer.setCharging(this.chargeTime > 2);
		}
	}

}
