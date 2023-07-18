package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class OrganicMatterItem extends Item {

	public static final Supplier<BiMap<Block, Block>> PRESERVABLE = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
			.put(RisusBlocks.TISSUE.get(), RisusBlocks.LIVING_TISSUE.get())
			.put(RisusBlocks.ROTTING_TISSUE.get(), RisusBlocks.ROTTED_TISSUE.get())
			.put(RisusBlocks.DECOMPOSING_TISSUE.get(), RisusBlocks.DECOMPOSED_TISSUE.get())
			.put(RisusBlocks.DECAYING_TISSUE.get(), RisusBlocks.DECAYED_TISSUE.get())
			.build());

	public OrganicMatterItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		return getWaxed(blockstate).map((p_238251_) -> {
			Player player = context.getPlayer();
			ItemStack itemstack = context.getItemInHand();
			if (player instanceof ServerPlayer) {
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
			}

			itemstack.shrink(1);
			level.setBlock(blockpos, p_238251_, 11);
			level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, p_238251_));
			level.levelEvent(player, 3003, blockpos, 0);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}).orElse(InteractionResult.PASS);
	}

	public static Optional<BlockState> getWaxed(BlockState state) {
		return Optional.ofNullable(PRESERVABLE.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
	}
}
