package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

public class CurvedSkinBlock extends MultiDirectionalBlock {

	public CurvedSkinBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(player.getMainHandItem().is(RisusItems.ORGANIC_MATTER.get())){
			level.setBlock(pos, RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get().defaultBlockState().setValue(MultiDirectionalBlock.ORIENTATION, state.getValue(ORIENTATION)), 11);
			player.getMainHandItem().shrink(1);

			return InteractionResult.SUCCESS;

		}
		return super.use(state, level, pos, player, hand, result);
		}
	}