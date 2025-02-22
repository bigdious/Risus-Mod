package com.bigdious.risus.items;

import com.bigdious.risus.blocks.DarknessBlock;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class LightDevourerItem extends Item {

	public LightDevourerItem(Item.Properties props) {
		super(props);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();

		BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
		if (DarknessBlock.canBePlacedAt(level, blockpos1, context.getHorizontalDirection())) {
			level.playSound(player, blockpos1, RisusSoundEvents.DARKNESS_PLACE.get(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
			BlockState blockstate1 = RisusBlocks.DARKNESS.get().defaultBlockState().setValue(DarknessBlock.FLUIDLOGGED, SimpleMultiloggedBlock.MultiloggingEnum.getFromFluid(level.getFluidState(blockpos1).getType()));
			level.setBlock(blockpos1, blockstate1, 11);
			level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
			return InteractionResult.sidedSuccess(level.isClientSide());
		} else {
			return InteractionResult.FAIL;
		}
	}
}
