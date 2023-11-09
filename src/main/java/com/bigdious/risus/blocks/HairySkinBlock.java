package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ItemBasedSteering;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

public class HairySkinBlock extends Block {

	public HairySkinBlock(Properties p_49795_) {
		super(p_49795_);
	}
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(player.getMainHandItem().is(Tags.Items.SHEARS)){
			level.setBlock(pos, RisusBlocks.SKIN.get().defaultBlockState(), 11);
			return InteractionResult.SUCCESS;

		}
		return super.use(state, level, pos, player, hand, result);
		}
	}
