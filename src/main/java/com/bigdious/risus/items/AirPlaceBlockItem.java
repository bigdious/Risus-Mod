package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class AirPlaceBlockItem extends BlockItem {
	public AirPlaceBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		BlockPos pos = player.getOnPos().below();
		if (level.getBlockState(pos).isAir()) {
			BlockState state = RisusBlocks.TESSERACT.get().defaultBlockState();
			if (!level.isClientSide) {
				level.setBlock(pos, state, 11);
			}
			SoundType soundtype = state.getSoundType(level, pos, player);
			level.playSound(player, pos, this.getPlaceSound(state, level, pos, player), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, state));

			itemstack.consume(1, player);
			player.awardStat(Stats.ITEM_USED.get(this));
			return InteractionResultHolder.consume(itemstack);
		}
		return InteractionResultHolder.fail(itemstack);
	}
}
