package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;

public class HairySkinBlock extends Block {

	public HairySkinBlock(Properties properties) {
		super(properties);
	}


	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (player.getMainHandItem().is(Tags.Items.TOOLS_SHEAR)) {
			level.setBlock(pos, RisusBlocks.SKIN.get().defaultBlockState(), 11);
			player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			ItemEntity hairitem = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(RisusItems.HAIR_FOLLICLES.get()));
			level.addFreshEntity(hairitem);
			level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 1.0F, 1.0F);
			return ItemInteractionResult.SUCCESS;
		}
		return ItemInteractionResult.FAIL;
	}
}
