package com.bigdious.risus.items;

import com.bigdious.risus.blocks.DarknessBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;

public class LightDevourerItem extends Item {

	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);

			BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
			if (DarknessBlock.canBePlacedAt(level, blockpos1, context.getHorizontalDirection())) {
				level.playSound(player, blockpos1, SoundEvents.WARDEN_NEARBY_CLOSE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
				BlockState blockstate1 = DarknessBlock.getState(level, blockpos1);
				level.setBlock(blockpos1, blockstate1, 11);
				level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
				return InteractionResult.sidedSuccess(level.isClientSide());
			} else {
				return InteractionResult.FAIL;
			}
	}


	public LightDevourerItem(Item.Properties props) {
		super( props);
	}
}
