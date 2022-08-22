package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayerFactory;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Maw extends Monster implements CacheTargetOnClient {

	private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(Maw.class, EntityDataSerializers.INT);
	@Nullable
	private LivingEntity clientSideCachedAttackTarget;
	private boolean eatenTNT;
	private int eatenTNTTimer;

	public Maw(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.setYRot(0.0F);
		this.setXRot(0.0F);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(DATA_ID_ATTACK_TARGET, 0);
	}

	@Override
	protected void registerGoals() {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false));
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 300)
				.add(Attributes.ATTACK_DAMAGE, 500);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();
		if (!this.getLevel().getBlockState(this.blockPosition().below()).is(RisusBlocks.MAW_GUTS.get())) {
			this.kill();
		}

		if (this.eatenTNT) {
			this.eatenTNTTimer++;
			((ServerLevel) this.getLevel()).sendParticles(ParticleTypes.SMOKE, this.position().x(), this.position().y() + 0.5F, this.position().z(), 2, 0.1F, 0.1F, 0.1F, 0);

			if (this.eatenTNTTimer >= 60) {
				this.getLevel().explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Explosion.BlockInteraction.NONE);
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

	//[VanillaCopy], but include primed TNT in pushing so we can swallow it
	@Override
	protected void pushEntities() {
		List<Entity> list = this.level.getEntities(this, this.getBoundingBox(), EntitySelector.pushableBy(this).or(entity -> entity instanceof PrimedTnt));
		if (!list.isEmpty()) {
			int i = this.level.getGameRules().getInt(GameRules.RULE_MAX_ENTITY_CRAMMING);
			if (i > 0 && list.size() > i - 1 && this.random.nextInt(4) == 0) {
				int j = 0;

				for (Entity entity : list) {
					if (!entity.isPassenger()) {
						++j;
					}
				}

				if (j > i - 1) {
					this.hurt(DamageSource.CRAMMING, 6.0F);
				}
			}

			for (Entity entity : list) {
				this.doPush(entity);
			}
		}
	}

	@Override
	protected void doPush(Entity entity) {
		if (entity instanceof LivingEntity living && living.attackable()) {
			//set up the victim to think theyre being killed by a player
			living.setLastHurtByPlayer(FakePlayerFactory.getMinecraft((ServerLevel) this.getLevel()));
			//then do the actual damage
			this.doHurtTarget(living);
		} else if (entity instanceof PrimedTnt tnt) {
			tnt.discard();
			this.eatenTNT = true;
		}
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
}
