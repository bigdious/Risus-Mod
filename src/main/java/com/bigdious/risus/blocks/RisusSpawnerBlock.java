package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusSpawnerBlockEntity;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RisusSpawnerBlock extends SpawnerBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusSpawnerBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
		}

		return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RisusSpawnerBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
		return createTickerHelper(blockEntityType_, RisusBlockEntities.SPAWNER.get(), level_.isClientSide ? RisusSpawnerBlockEntity::clientTick : RisusSpawnerBlockEntity::serverTick);
	}
}
