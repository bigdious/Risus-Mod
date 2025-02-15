package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;

public class RemainsBlock extends Block {

	public RemainsBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT) && level.getBlockState(pos.above()).isAir()) {
			level.setBlock(pos.above(), RisusBlocks.JOYFLAME_FIRE.get().defaultBlockState(), 11);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useItemOn(stack, state, level, pos, player, hand, result);
	}
}
