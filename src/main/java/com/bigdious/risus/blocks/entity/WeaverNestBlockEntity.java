package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.entity.creatures.Weaver;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusEntities;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.extensions.IOwnedSpawner;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;

public class WeaverNestBlockEntity extends BlockEntity implements IOwnedSpawner {

	private static final int DELAY = 1200;
	private static final int MAX_NEARBY = 10;
	private static final int PLAYER_RANGE = 32;
	private static final int SPAWN_RANGE = 10;

	private int spawnDelay;

	public WeaverNestBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.WEAVER_NEST.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, WeaverNestBlockEntity entity) {
		if (level instanceof ServerLevel serverLevel && entity.isNearPlayer(level, pos) && level.getDifficulty() != Difficulty.PEACEFUL) {
			if (entity.spawnDelay == -1) {
				entity.spawnDelay = DELAY;
			}

			if (entity.spawnDelay > 0) {
				entity.spawnDelay--;
			} else {
				int k = level.getEntities(EntityTypeTest.forExactClass(Weaver.class), new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).inflate(SPAWN_RANGE), EntitySelector.NO_SPECTATORS).size();
				if (k >= MAX_NEARBY) {
					entity.spawnDelay = DELAY;
					return;
				}

				Weaver weaver = RisusEntities.WEAVER.get().create(level);
				weaver.moveTo(pos.getBottomCenter(), 0.0F, 0.0F);

				EventHooks.finalizeMobSpawnSpawner(weaver, serverLevel, level.getCurrentDifficultyAt(weaver.blockPosition()), MobSpawnType.SPAWNER, null, entity, true);

				if (!level.addFreshEntity(weaver)) {
					entity.spawnDelay = DELAY;
					return;
				}

				level.gameEvent(weaver, GameEvent.ENTITY_PLACE, pos);
				weaver.spawnAnim();

				entity.spawnDelay = DELAY;
			}
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putShort("Delay", (short) this.spawnDelay);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.spawnDelay = tag.getShort("Delay");

	}

	private boolean isNearPlayer(Level level, BlockPos pos) {
		return level.hasNearbyAlivePlayer(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, PLAYER_RANGE);
	}

	@Nullable
	@Override
	public Either<BlockEntity, Entity> getOwner() {
		return Either.left(this);
	}
}
