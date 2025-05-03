package com.bigdious.risus.dispenser;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class WaxingDispenserBehaviour extends OptionalDispenseItemBehavior {

	private final DispenseItemBehavior vanillaBehavior;

	public WaxingDispenserBehaviour(DispenseItemBehavior vanillaBehavior) {
		this.vanillaBehavior = vanillaBehavior;
	}
	boolean fired = false;

	public static final Map<Block, Block> WAXINGS = Map.of(
		RisusBlocks.COPPER_AMALGAM.get(), RisusBlocks.WAXED_COPPER_AMALGAM.get(),
		RisusBlocks.EXPOSED_COPPER_AMALGAM.get(),  RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(),
		RisusBlocks.OXIDIZED_COPPER_AMALGAM.get(),  RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get(),
		RisusBlocks.WEATHERED_COPPER_AMALGAM.get(),  RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get()
	);

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		ServerLevel level = source.level();
		BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
		BlockState state = level.getBlockState(pos);
		Block checkingForBlock = state.getBlock();
		if (WAXINGS.containsKey(checkingForBlock)) {
			level.setBlock(pos, WAXINGS.get(checkingForBlock).withPropertiesOf(state), 3);
			ServerParticleUtils.spawnParticlesOnBlockFaces(source.level(), pos, ParticleTypes.WAX_ON, UniformInt.of(6, 12));
			stack.shrink(1);
			this.fired = true;
		}
		if (!this.fired) {
			return this.vanillaBehavior.dispense(source, stack);
		}
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
