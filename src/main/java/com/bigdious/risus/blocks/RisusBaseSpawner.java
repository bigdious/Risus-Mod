package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusDataMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.EntityGetter;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.EventHooks;

import java.util.Objects;
import java.util.Optional;

public abstract class RisusBaseSpawner extends BaseSpawner {

	public void serverTick(ServerLevel serverLevel, BlockPos pos) {
		if (this.isNearPlayer(serverLevel, pos)) {
			if (this.spawnDelay == -1) {
				this.delay(serverLevel, pos);
			}

			if (this.spawnDelay > 0) {
				--this.spawnDelay;
			} else {
				boolean flag = false;
				RandomSource randomsource = serverLevel.getRandom();
				SpawnData spawndata = this.getOrCreateNextSpawnData(serverLevel, randomsource, pos);

				for(int i = 0; i < this.spawnCount; ++i) {
					CompoundTag compoundtag = spawndata.getEntityToSpawn();
					Optional<EntityType<?>> optional = EntityType.by(compoundtag);
					if (optional.isEmpty()) {
						this.delay(serverLevel, pos);
						return;
					}

					ListTag listtag = compoundtag.getList("Pos", 6);
					int j = listtag.size();
					double d0 = j >= 1 ? listtag.getDouble(0) : (double)pos.getX() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double)this.spawnRange + (double)0.5F;
					double d1 = j >= 2 ? listtag.getDouble(1) : (double)(pos.getY() + randomsource.nextInt(3) - 1);
					double d2 = j >= 3 ? listtag.getDouble(2) : (double)pos.getZ() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double)this.spawnRange + (double)0.5F;
					if (serverLevel.noCollision((optional.get()).getSpawnAABB(d0, d1, d2))) {
						BlockPos blockpos = BlockPos.containing(d0, d1, d2);
						if (spawndata.getCustomSpawnRules().isPresent()) {
							if (!(optional.get()).getCategory().isFriendly() && serverLevel.getDifficulty() == Difficulty.PEACEFUL) {
								continue;
							}

							SpawnData.CustomSpawnRules spawndata$customspawnrules = spawndata.getCustomSpawnRules().get();
							if (!spawndata$customspawnrules.isValidPosition(blockpos, serverLevel)) {
								continue;
							}
						} else if (!SpawnPlacements.checkSpawnRules(optional.get(), serverLevel, MobSpawnType.SPAWNER, blockpos, serverLevel.getRandom())) {
							continue;
						}

						Entity entity = EntityType.loadEntityRecursive(compoundtag, serverLevel, (p_151310_) -> {
							p_151310_.moveTo(d0, d1, d2, p_151310_.getYRot(), p_151310_.getXRot());
							return p_151310_;
						});
						if (entity == null) {
							this.delay(serverLevel, pos);
							return;
						}

						int k = serverLevel.getEntities(EntityTypeTest.forExactClass(entity.getClass()), (new AABB(pos.getX(), pos.getY(), pos.getZ(), (pos.getX() + 1), (pos.getY() + 1), (pos.getZ() + 1))).inflate(this.spawnRange), EntitySelector.NO_SPECTATORS).size();

						//here's the change compared to base. We want to account for beloved versions too to avoid for ex. Lover converting creepers to stalkers and then spawner never stops spawning creepers
						int l = entity.getType().builtInRegistryHolder().getData(RisusDataMaps.LOVER_CONVERSION) == null ? 0 : serverLevel.getEntities(entity.getType().builtInRegistryHolder().getData(RisusDataMaps.LOVER_CONVERSION).result(), (new AABB(pos.getX(), pos.getY(), pos.getZ(), (pos.getX() + 1), (pos.getY() + 1), (pos.getZ() + 1))).inflate(this.spawnRange), EntitySelector.NO_SPECTATORS).size();
						if (k + l>= this.maxNearbyEntities) {
							this.delay(serverLevel, pos);
							return;
						}

						entity.moveTo(entity.getX(), entity.getY(), entity.getZ(), randomsource.nextFloat() * 360.0F, 0.0F);
						if (entity instanceof Mob) {
							Mob mob = (Mob)entity;
							if (!EventHooks.checkSpawnPositionSpawner(mob, serverLevel, MobSpawnType.SPAWNER, spawndata, this)) {
								continue;
							}

							boolean flag1 = spawndata.getEntityToSpawn().size() == 1 && spawndata.getEntityToSpawn().contains("id", 8);
							EventHooks.finalizeMobSpawnSpawner(mob, serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.SPAWNER, null, this, flag1);
							Optional<EquipmentTable> var10000 = spawndata.getEquipment();
							Objects.requireNonNull(mob);
							var10000.ifPresent(mob::equip);
						}

						if (!serverLevel.tryAddFreshEntityWithPassengers(entity)) {
							this.delay(serverLevel, pos);
							return;
						}

						serverLevel.levelEvent(2004, pos, 0);
						serverLevel.gameEvent(entity, GameEvent.ENTITY_PLACE, blockpos);
						if (entity instanceof Mob) {
							((Mob)entity).spawnAnim();
						}

						flag = true;
					}
				}

				if (flag) {
					this.delay(serverLevel, pos);
				}
			}
		}

	}
}
