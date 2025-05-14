package com.bigdious.risus.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public abstract class TamableMonster extends Monster implements OwnableEntity {
	//adapted copy of TamableAnimal
	protected TamableMonster(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
	}

	public final boolean unableToMoveToOwner() {
		return this.isPassenger() || this.mayBeLeashed() || this.getOwner() != null && this.getOwner().isSpectator();
	}
	public boolean shouldTryTeleportToOwner() {
		LivingEntity livingentity = this.getOwner();
		return livingentity != null && this.distanceToSqr(this.getOwner()) >= (double)144.0F;
	}
	public void tryToTeleportToOwner() {
		LivingEntity livingentity = this.getOwner();
		if (livingentity != null) {
			this.teleportToAroundBlockPos(livingentity.blockPosition());
		}

	}
	private void teleportToAroundBlockPos(BlockPos pos) {
		for(int i = 0; i < 10; ++i) {
			int j = this.random.nextIntBetweenInclusive(-3, 3);
			int k = this.random.nextIntBetweenInclusive(-3, 3);
			if (Math.abs(j) >= 2 || Math.abs(k) >= 2) {
				int l = this.random.nextIntBetweenInclusive(-1, 1);
				if (this.maybeTeleportTo(pos.getX() + j, pos.getY() + l, pos.getZ() + k)) {
					return;
				}
			}
		}

	}
	private boolean maybeTeleportTo(int x, int y, int z) {
		if (!this.canTeleportTo(new BlockPos(x, y, z))) {
			return false;
		} else {
			this.moveTo((double)x + (double)0.5F, (double)y, (double)z + (double)0.5F, this.getYRot(), this.getXRot());
			this.navigation.stop();
			return true;
		}
	}
	private boolean canTeleportTo(BlockPos pos) {
		PathType pathtype = WalkNodeEvaluator.getPathTypeStatic(this, pos);
		if (pathtype != PathType.WALKABLE) {
			return false;
		} else {
			BlockState blockstate = this.level().getBlockState(pos.below());
			if (!this.canFlyToOwner() && blockstate.getBlock() instanceof LeavesBlock) {
				return false;
			} else {
				BlockPos blockpos = pos.subtract(this.blockPosition());
				return this.level().noCollision(this, this.getBoundingBox().move(blockpos));
			}
		}
	}
	protected boolean canFlyToOwner() {
		return false;
	}

}
