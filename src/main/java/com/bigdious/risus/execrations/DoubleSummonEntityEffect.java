package com.bigdious.risus.execrations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.SummonEntityEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public record DoubleSummonEntityEffect(HolderSet<EntityType<?>> entityTypes, boolean joinTeam) implements EnchantmentEntityEffect {
	public static final MapCodec<DoubleSummonEntityEffect> CODEC = RecordCodecBuilder.mapCodec((p_345616_) -> p_345616_.group(RegistryCodecs.homogeneousList(Registries.ENTITY_TYPE).fieldOf("entity").forGetter(DoubleSummonEntityEffect::entityTypes), Codec.BOOL.optionalFieldOf("join_team", false).forGetter(DoubleSummonEntityEffect::joinTeam)).apply(p_345616_, DoubleSummonEntityEffect::new));

	public DoubleSummonEntityEffect(HolderSet<EntityType<?>> entityTypes, boolean joinTeam) {
		this.entityTypes = entityTypes;
		this.joinTeam = joinTeam;
	}

	public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity p_entity, Vec3 origin) {
		BlockPos blockpos = BlockPos.containing(origin);
		if (Level.isInSpawnableBounds(blockpos)) {
			Optional<Holder<EntityType<?>>> optional = this.entityTypes().getRandomElement(level.getRandom());
			if (!optional.isEmpty()) {
				Entity entity = ((EntityType)((Holder)optional.get()).value()).spawn(level, blockpos, MobSpawnType.TRIGGERED);
				Entity entity2 = ((EntityType)((Holder)optional.get()).value()).spawn(level, blockpos, MobSpawnType.TRIGGERED);
				if (entity != null && entity2!= null) {
					if (entity instanceof LightningBolt && entity2 instanceof LightningBolt) {
						LightningBolt lightningbolt = (LightningBolt)entity;
						LightningBolt lightningbolt2 = (LightningBolt)entity;
						LivingEntity var11 = item.owner();
						if (var11 instanceof ServerPlayer) {
							ServerPlayer serverplayer = (ServerPlayer)var11;
							lightningbolt.setCause(serverplayer);
							lightningbolt2.setCause(serverplayer);
						}
					}

					if (this.joinTeam && p_entity.getTeam() != null) {
						level.getScoreboard().addPlayerToTeam(entity.getScoreboardName(), p_entity.getTeam());
						level.getScoreboard().addPlayerToTeam(entity2.getScoreboardName(), p_entity.getTeam());
					}
					LivingEntity owner = item.owner();
					entity.moveTo(origin.x, origin.y, origin.z, entity.getYRot(), entity.getXRot());
					entity2.moveTo(owner.getX(), owner.getY(), owner.getZ(), owner.getYRot(), owner.getXRot());
				}
			}
		}

	}

	public MapCodec<DoubleSummonEntityEffect> codec() {
		return CODEC;
	}

	public HolderSet<EntityType<?>> entityTypes() {
		return this.entityTypes;
	}

	public boolean joinTeam() {
		return this.joinTeam;
	}
}