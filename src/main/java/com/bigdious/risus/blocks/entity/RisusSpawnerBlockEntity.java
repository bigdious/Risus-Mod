package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class RisusSpawnerBlockEntity extends BlockEntity implements Spawner {
	private final BaseSpawner spawner = new BaseSpawner() {
		public void broadcastEvent(Level level, BlockPos pos, int i) {
			level.blockEvent(pos, RisusBlocks.FLESHY_SPAWNER.get(), i, 0);
		}

		public void setNextSpawnData(@Nullable Level level, BlockPos pos, SpawnData data) {
			super.setNextSpawnData(level, pos, data);
			if (level != null) {
				BlockState blockstate = level.getBlockState(pos);
				level.sendBlockUpdated(pos, blockstate, blockstate, 4);
			}

		}

		public Either<BlockEntity, Entity> getOwner() {
			return Either.left(RisusSpawnerBlockEntity.this);
		}
	};

	public RisusSpawnerBlockEntity(BlockPos pos, BlockState blockState) {
		super(RisusBlockEntities.SPAWNER.get(), pos, blockState);
	}

	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.spawner.load(this.level, this.worldPosition, tag);
	}

	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		this.spawner.save(tag);
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, RisusSpawnerBlockEntity blockEntity) {
		blockEntity.spawner.clientTick(level, pos);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, RisusSpawnerBlockEntity blockEntity) {
		blockEntity.spawner.serverTick((ServerLevel)level, pos);
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag compoundtag = this.saveCustomOnly(registries);
		compoundtag.remove("SpawnPotentials");
		return compoundtag;
	}

	public boolean triggerEvent(int id, int type) {
		return this.spawner.onEventTriggered(this.level, id) ? true : super.triggerEvent(id, type);
	}

	public boolean onlyOpCanSetNbt() {
		return true;
	}

	public void setEntityId(EntityType<?> type, RandomSource random) {
		this.spawner.setEntityId(type, this.level, random, this.worldPosition);
		this.setChanged();
	}

	public BaseSpawner getSpawner() {
		return this.spawner;
	}
}
