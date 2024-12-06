package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.RisusWeatheringCopper;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class CopperAmalgamBlock extends MultiloggedRotateableBlock implements RisusWeatheringCopper {
	private final RisusWeatherState weatherState;
	public CopperAmalgamBlock(RisusWeatherState weatherState, Properties properties) {
		super(properties);
		this.weatherState = weatherState;
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(FLUIDLOGGED, SimpleMultiloggedBlock.MultiloggingEnum.EMPTY));
	}
	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		this.changeOverTime(state, level, pos, random);
	}

	protected boolean isRandomlyTicking(BlockState state) {
		return RisusWeatheringCopper.getNext(state.getBlock()).isPresent();
	}

	public RisusWeatherState getAge() {
		return this.weatherState;
	}
}
