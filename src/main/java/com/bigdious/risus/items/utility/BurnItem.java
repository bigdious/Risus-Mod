package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import com.bigdious.risus.compat.curios.CuriosCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;

public class BurnItem extends Item {

	public BurnItem(Properties properties) {
		super(properties);
	}

	//thanks Kapitencraft
	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
		if (entity instanceof LivingEntity living && (!(living instanceof Player) || isSelected || living.getItemBySlot(EquipmentSlot.OFFHAND).is(stack.getItem()) || Risus.curiosSearch(living, stack.getItem()))) {
			if (!living.fireImmune() &&
				!living.hasEffect(MobEffects.FIRE_RESISTANCE) &&
				!level.isClientSide() &&
				level.getGameTime() % 20 == 0
			) {
				living.igniteForTicks(100);
			}
		}
		super.inventoryTick(stack, level, entity, slotId, isSelected);
	}

	//vanillacopy from FireChargeItem, added crouching

	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		Player player = context.getPlayer();
		boolean flag = false;
		BlockState blockstate2 = blockstate.getToolModifiedState(context, ItemAbilities.FIRESTARTER_LIGHT, false);
		if (player != null && player.isCrouching()) {
			if (blockstate2 == null) {
				blockpos = blockpos.relative(context.getClickedFace());
				if (BaseFireBlock.canBePlacedAt(level, blockpos, context.getHorizontalDirection())) {
					this.playSound(level, blockpos);
					level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(level, blockpos));
					level.gameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, blockpos);
					flag = true;
				}
			} else {
				this.playSound(level, blockpos);
				level.setBlockAndUpdate(blockpos, blockstate2);
				level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
				flag = true;
			}
		}

		if (flag) {
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return InteractionResult.FAIL;
		}
	}

	private void playSound(Level level, BlockPos pos) {
		RandomSource randomsource = level.getRandom();
		level.playSound((Player)null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (randomsource.nextFloat() - randomsource.nextFloat()) * 0.2F + 1.0F);
	}
}
