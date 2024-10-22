package com.bigdious.risus.dispenser;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
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
