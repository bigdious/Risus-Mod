package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.BeatingHeartBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;

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
		if (effectType != BeatingHeartBlock.HealthEffectEnum.EMPTY && level.getGameTime() % 160L == 0L) {
			AABB aabb = (new AABB(pos)).inflate(30);
			List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb);
			for(LivingEntity entities : list) {
				entities.addEffect(new MobEffectInstance(HEALTH_EFFECTS.get(effectType), 10));
			}
		}

		if (level.getGameTime() % 80 ==0L) {
			heart.beat();
			level.playSound(null, pos, RisusSoundEvents.HEARTBEAT.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
		}
	}

	public void beat() {
		if (this.getLevel() != null && !this.getLevel().isClientSide()) {
			this.getLevel().blockEvent(this.getBlockPos(), this.getBlockState().getBlock(), EVENT_HEART_BEATS, 1);

		}
	}

	@Override
	public boolean triggerEvent(int id, int type) {
		//thanks for pointing this out giz
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

	public static final Map<BeatingHeartBlock.HealthEffectEnum, Holder<MobEffect>> HEALTH_EFFECTS = Map.ofEntries(
		Map.entry(BeatingHeartBlock.HealthEffectEnum.REGEN, MobEffects.REGENERATION),
		Map.entry(BeatingHeartBlock.HealthEffectEnum.HEALTH_BOOST, MobEffects.HEALTH_BOOST),
		Map.entry(BeatingHeartBlock.HealthEffectEnum.BLOODCLOGGED, RisusMobEffects.BLOODCLOGGED),
		Map.entry(BeatingHeartBlock.HealthEffectEnum.POISON, MobEffects.POISON),
		Map.entry(BeatingHeartBlock.HealthEffectEnum.WITHER, MobEffects.WITHER),
		Map.entry(BeatingHeartBlock.HealthEffectEnum.HEALING, MobEffects.HEAL)

	);

}
