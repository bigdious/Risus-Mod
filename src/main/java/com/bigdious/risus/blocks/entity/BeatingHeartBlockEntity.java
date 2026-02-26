package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.BeatingHeartBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import com.mojang.datafixers.util.Pair;

import java.util.List;
import java.util.Map;

public class BeatingHeartBlockEntity extends BlockEntity {
	public static final int EVENT_HEART_BEATS = 1;
	public long beatStartedAtTick;
	public BeatingHeartBlockEntity(BlockPos pos, BlockState blockState) {
		super(RisusBlockEntities.BEATING_HEART.get(), pos, blockState);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, BeatingHeartBlockEntity heart) {
		BeatingHeartBlock.HealthEffectEnum effectType = heart.getBlockState().getValue(BeatingHeartBlock.HealthEffectEnum.HEALTH_EFFECT);
		if (level.getGameTime() % 70L == 0L) {
			heart.beat();
			level.playSound(null, pos, RisusSoundEvents.HEARTBEAT.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
			if (effectType != BeatingHeartBlock.HealthEffectEnum.EMPTY) {
				AABB aabb = (new AABB(pos)).inflate(30);
				List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb);
				for (LivingEntity entities : list) {
					if (effectType != BeatingHeartBlock.HealthEffectEnum.HEALING) {
						entities.addEffect(new MobEffectInstance(HEALTH_EFFECTS.get(effectType).getFirst(), 100));
					} else {
						entities.heal(entities.isInvertedHealAndHarm() ? -1 : 1);


					}
				}
			}
		}
	}

	public void beat() {
		if (this.getLevel() != null && !this.getLevel().isClientSide()) {
			this.getLevel().blockEvent(this.getBlockPos(), this.getBlockState().getBlock(), EVENT_HEART_BEATS, 1);

		}
	}

	@Override
	public boolean triggerEvent(int id, int type) {
		if (this.getLevel() != null && id == 1 && type >= 0) {
			this.beatStartedAtTick = this.getLevel().getGameTime();
			return true;
		} else {
			return super.triggerEvent(id, type);
		}
	}

	public Direction getDirection() {
		return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
	}

	public static final Map<BeatingHeartBlock.HealthEffectEnum, Pair<Holder<MobEffect>, ResourceLocation>> HEALTH_EFFECTS = Map.of(
		BeatingHeartBlock.HealthEffectEnum.REGEN, Pair.of(MobEffects.REGENERATION, Risus.prefix("textures/block/beating_heart/regen.png")),
		BeatingHeartBlock.HealthEffectEnum.EMPTY, Pair.of(null, Risus.prefix("textures/block/beating_heart/empty.png")),
		BeatingHeartBlock.HealthEffectEnum.HEALTH_BOOST, Pair.of(MobEffects.HEALTH_BOOST, Risus.prefix("textures/block/beating_heart/health_boost.png")),
		BeatingHeartBlock.HealthEffectEnum.BLOODCLOGGED, Pair.of(RisusMobEffects.BLOODCLOGGED, Risus.prefix("textures/block/beating_heart/bloodclogged.png")),
		BeatingHeartBlock.HealthEffectEnum.POISON, Pair.of(MobEffects.POISON, Risus.prefix("textures/block/beating_heart/poison.png")),
		BeatingHeartBlock.HealthEffectEnum.WITHER, Pair.of(MobEffects.WITHER, Risus.prefix("textures/block/beating_heart/wither.png")),
		BeatingHeartBlock.HealthEffectEnum.HEALING, Pair.of(MobEffects.HEAL, Risus.prefix("textures/block/beating_heart/heal.png"))
	);

}
