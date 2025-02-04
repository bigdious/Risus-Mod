package com.bigdious.risus.blocks.interfaces;

import com.bigdious.risus.init.RisusBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface RisusWeatheringCopper extends ChangeOverTimeBlock<RisusWeatheringCopper.RisusWeatherState> {
	Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
		.put(RisusBlocks.COPPER_AMALGAM.get(), RisusBlocks.EXPOSED_COPPER_AMALGAM.get())
		.put(RisusBlocks.EXPOSED_COPPER_AMALGAM.get(), RisusBlocks.WEATHERED_COPPER_AMALGAM.get())
		.put(RisusBlocks.WEATHERED_COPPER_AMALGAM.get(), RisusBlocks.OXIDIZED_COPPER_AMALGAM.get())
		.build());

	static Optional<Block> getNext(Block block) {
		return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
	}

	default Optional<BlockState> getNext(BlockState state) {
		return getNext(state.getBlock()).map(block -> block.withPropertiesOf(state));
	}

	default float getChanceModifier() {
		return this.getAge() == RisusWeatheringCopper.RisusWeatherState.UNAFFECTED ? 0.75F : 1.0F;
	}

	enum RisusWeatherState {
		UNAFFECTED,
		EXPOSED,
		WEATHERED,
		OXIDIZED
	}

}
