package com.bigdious.risus.dispenser;

import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

public class ShavingDispenserBehaviour extends DefaultDispenseItemBehavior {
	boolean fired = false;
	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		Level level = source.level();
		BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
		BlockState state = level.getBlockState(pos);
		if (state.is(RisusBlocks.HAIRY_SKIN)) {
			level.setBlock(pos, RisusBlocks.SKIN.get().withPropertiesOf(state), 3);
		}
		if (state.is(RisusBlocks.HAIRY_FLESHY_SKIN)) {
			level.setBlock(pos, RisusBlocks.FLESHY_SKIN.get().withPropertiesOf(state), 3);
		}
		if (state.is(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN)) {
			level.setBlock(pos, RisusBlocks.CURVED_FLESHY_SKIN.get().withPropertiesOf(state), 3);
		}
		this.fired = true;
		ItemEntity summonedItem = EntityType.ITEM.create(level);
		summonedItem.moveTo(pos.above(), 0.0F, 0.0F);
		summonedItem.setItem(RisusItems.HAIR_FOLLICLES.toStack());
		level.addFreshEntity(summonedItem);
		return stack;
	}

	@Override
	protected void playSound(BlockSource source) {
		if (this.fired) {
			super.playSound(source);
			this.fired = false;
		} else {
			source.level().levelEvent(LevelEvent.SOUND_DISPENSER_FAIL, source.pos(), 0);
		}
	}
}
