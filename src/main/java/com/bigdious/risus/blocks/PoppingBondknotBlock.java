package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.PoppingBondknotBlockEntity;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

public class PoppingBondknotBlock extends RotatedPillarBlock implements EntityBlock {

	public static final EnumProperty<Direction> POP_SIDE = EnumProperty.create("pop_side", Direction.class);

	public PoppingBondknotBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(POP_SIDE, Direction.NORTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		Direction dir = Direction.getRandom(context.getLevel().getRandom());
		if (state.is(RisusBlocks.POPPING_BONDKNOT_LOG.get())) {
			while (dir.getAxis() == state.getValue(AXIS)) {
				dir = Direction.getRandom(context.getLevel().getRandom());
			}
		}
		return state.setValue(POP_SIDE, dir);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.PUSH_ONLY;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new PoppingBondknotBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide() ? null : createTickerHelper(type, RisusBlockEntities.POPPING_BONDKNOT.get(), PoppingBondknotBlockEntity::tick);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type1, BlockEntityType<E> type2, BlockEntityTicker<? super E> ticker) {
		return type2 == type1 ? (BlockEntityTicker<A>) ticker : null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(POP_SIDE);
	}
}
