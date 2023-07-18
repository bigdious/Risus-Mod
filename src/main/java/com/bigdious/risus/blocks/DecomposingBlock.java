package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface DecomposingBlock extends ChangeOverTimeBlock<DecomposingBlock.DecomposeState> {

	Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
			.put(RisusBlocks.TISSUE.get(), RisusBlocks.ROTTING_TISSUE.get())
			.put(RisusBlocks.ROTTING_TISSUE.get(), RisusBlocks.DECOMPOSING_TISSUE.get())
			.put(RisusBlocks.DECOMPOSING_TISSUE.get(), RisusBlocks.DECAYING_TISSUE.get())
			.put(RisusBlocks.DECAYING_TISSUE.get(), RisusBlocks.BONE_WALL.get())
			.build());

	static Optional<Block> getNext(Block block) {
		return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
	}

	default Optional<BlockState> getNext(BlockState state) {
		return getNext(state.getBlock()).map(block -> block.withPropertiesOf(state));
	}

	default float getChanceModifier() {
		return this.getAge() == DecomposeState.NONE ? 0.75F : 1.0F;
	}

	enum DecomposeState {
		NONE,
		ROTTING,
		DECOMPOSING,
		DECAYING;
	}
}
