package com.bigdious.risus.dispenser;

import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class ShavingDispenserBehaviour extends OptionalDispenseItemBehavior {

	private final DispenseItemBehavior vanillaBehavior;

	public ShavingDispenserBehaviour(DispenseItemBehavior vanillaBehavior) {
		this.vanillaBehavior = vanillaBehavior;
	}
	boolean fired = false;

	public static final Map<Block, Block> SHAVINGS = Map.of(
		RisusBlocks.HAIRY_SKIN.get(), RisusBlocks.SKIN.get(),
		RisusBlocks.HAIRY_FLESHY_SKIN.get(),  RisusBlocks.FLESHY_SKIN.get(),
		RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get(),  RisusBlocks.CURVED_FLESHY_SKIN.get()
	);

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		ServerLevel level = source.level();
		BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
		BlockState state = level.getBlockState(pos);
		Block checkingForBlock = state.getBlock();
		if (SHAVINGS.containsKey(checkingForBlock)) {
			level.setBlock(pos, SHAVINGS.get(checkingForBlock).withPropertiesOf(state), 3);
			ItemEntity summonedItem = EntityType.ITEM.create(level);
			summonedItem.moveTo(pos.above(), 0.0F, 0.0F);
			summonedItem.setItem(RisusItems.HAIR_FOLLICLES.toStack());
			level.addFreshEntity(summonedItem);
			stack.hurtAndBreak(1, level, null, item -> {});
		}
		this.fired = true;
		return this.vanillaBehavior.dispense(source, stack);
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
