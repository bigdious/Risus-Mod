package com.bigdious.risus.items;

import com.bigdious.risus.entity.Lover;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataMaps;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;

public class LoverCreamItem extends Item {
	public LoverCreamItem(Properties properties) {
		super(properties);
	}
	@Override
	@SuppressWarnings("unchecked")
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity instanceof Mob mob && entity.getType().builtInRegistryHolder().getData(RisusDataMaps.LOVER_CONVERSION) != null && entity.level() instanceof ServerLevel serverLevel) {
			tryConvertEntity(serverLevel,(EntityType<? extends Mob>) mob.getType().builtInRegistryHolder().getData(RisusDataMaps.LOVER_CONVERSION).result(), mob);
			player.level().playSound(player, player.getOnPos().above() ,RisusSoundEvents.LOVER_INFECT.get(), SoundSource.PLAYERS, 2.0F, (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.2F + 1.0F);
			boolean isClient = entity.level().isClientSide();
			if (!isClient) {
				stack.shrink(1);
			}
			return InteractionResult.sidedSuccess(isClient);
		}

		return InteractionResult.PASS;
	}

private <T extends Mob> boolean tryConvertEntity(ServerLevel level, EntityType<T> to, Mob from) {
	boolean flag = true;
	if (EventHooks.canLivingConvert(from, to, (timer) -> {})) {
		if (level.getDifficulty() != Difficulty.HARD && level.random.nextBoolean()) {
			return flag;
		}

		T offspring = from.convertTo(to, false);
		if (offspring != null) {
			offspring.finalizeSpawn(level, level.getCurrentDifficultyAt(offspring.blockPosition()), MobSpawnType.CONVERSION, null);
			EventHooks.onLivingConvert(from, offspring);

			BlockState spreading = RisusBlocks.SPREADING_REMAINS.get().defaultBlockState().setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), true);
			if (spreading.canSurvive(level, offspring.blockPosition()) && level.getBlockState(offspring.blockPosition()).isAir() && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
				level.setBlockAndUpdate(offspring.blockPosition(), spreading);
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

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.HONEY_DRINK;
	}
	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}
}
