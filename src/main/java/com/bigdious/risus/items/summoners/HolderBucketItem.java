package com.bigdious.risus.items.summoners;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class HolderBucketItem extends MobBucketItem {
	public HolderBucketItem(EntityType<?> type, Fluid content, SoundEvent emptySound, Properties properties) {
		super(type, content, emptySound, properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		BlockHitResult blockhitresult = getPlayerPOVHitResult(
			level, player, ClipContext.Fluid.NONE
		);
		if (blockhitresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			BlockPos blockpos = blockhitresult.getBlockPos();
			Direction direction = blockhitresult.getDirection();
			BlockPos blockpos1 = blockpos.relative(direction);
			if (!level.mayInteract(player, blockpos) || !player.mayUseItemAt(blockpos1, direction, itemstack)) {
				return InteractionResultHolder.fail(itemstack);
			} else {
				BlockState blockstate = level.getBlockState(blockpos);
				BlockPos blockpos2 = canBlockContainFluid(player, level, blockpos, blockstate) ? blockpos : blockpos1;
					this.checkExtraContent(player, level, itemstack, blockpos2);
					if (!level.isClientSide()) {
						level.playSound(null, player.getOnPos().above(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS);
					}
					if (player instanceof ServerPlayer) {
						CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos2, itemstack);
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, getEmptySuccessItem(itemstack, player));
					return InteractionResultHolder.sidedSuccess(itemstack1, level.isClientSide());
			}
		}
	}
}
