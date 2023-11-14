package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

public class HairyFleshySkinBlock extends ActuallyUseableDirectionalBlock {

	public HairyFleshySkinBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(player.getMainHandItem().is(Tags.Items.SHEARS)){
			level.setBlock(pos, RisusBlocks.FLESHY_SKIN.get().defaultBlockState().setValue(ActuallyUseableDirectionalBlock.FACING, state.getValue(FACING)), 11);
			player.getMainHandItem().hurtAndBreak(1, player, (p_43388_) -> {
				p_43388_.broadcastBreakEvent(player.getUsedItemHand());
			});
			ItemEntity hairitem = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(RisusItems.HAIR_FOLLICLES.get()));
			level.addFreshEntity(hairitem);
			level.playSound(null, pos , SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 1.0F, 1.0F);
			return InteractionResult.SUCCESS;

		}
		return super.use(state, level, pos, player, hand, result);
		}
	}
