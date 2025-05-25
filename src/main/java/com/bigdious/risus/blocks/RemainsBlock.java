package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;

public class RemainsBlock extends Block{

	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

	public RemainsBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, true));
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (stack.is(RisusTags.Items.WILLFUL_WEAPON) && state.getValue(ACTIVE)) {
			level.setBlock(pos, state.setValue(ACTIVE, false), 3);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}

		if (stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT) && level.getBlockState(pos.above()).isAir()) {
			level.setBlock(pos.above(), RisusBlocks.JOYFLAME_FIRE.get().defaultBlockState(), 11);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useItemOn(stack, state, level, pos, player, hand, result);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(ACTIVE);
	}
}
