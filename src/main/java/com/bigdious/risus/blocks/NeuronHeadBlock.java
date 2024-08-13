package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class NeuronHeadBlock extends GrowingPlantHeadBlock implements SimpleMultiloggedBlock,OrganicMatterableBlock {

	public static final MapCodec<NeuronHeadBlock> CODEC = simpleCodec(NeuronHeadBlock::new);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

	public NeuronHeadBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.UP, SHAPE, false, 0.1D);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY).setValue(AGE, 0));
	}

	@Override
	protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
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
		if (direction == this.growthDirection.getOpposite() && !state.canSurvive(accessor, pos)) {
			accessor.scheduleTick(pos, this, 1);
		}

		if (direction != this.growthDirection || !neighborState.is(this) && !neighborState.is(this.getBodyBlock())) {
			if (this.scheduleFluidTicks && state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
				accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
			}

			return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
		} else {
			return this.updateBodyAfterConvertedFromHead(state, this.getBodyBlock().defaultBlockState().setValue(FLUIDLOGGED, state.getValue(FLUIDLOGGED)));
		}
	}

	@Override
	protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
		return NetherVines.getBlocksToGrowWhenBonemealed(random);
	}

	@Override
	public boolean canGrowInto(BlockState pState) {
		return pState.isAir();
	}

	@Override
	protected Block getBodyBlock() {
		return RisusBlocks.NEURON_STEM.get();
	}

	protected static boolean canDefinitelyGrowInto(BlockState state) {
		return true;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
		return false;
	}

	@Override
	public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		return false;
	}

	@Override
	public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {}

	@Override
	public boolean isValidOrganicMatterTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
		return this.canGrowInto(pLevel.getBlockState(pPos.relative(this.growthDirection)));
	}

	@Override
	public boolean isOrganicMatterSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		return true;
	}

	@Override
	public void performOrganicMatter(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		BlockPos blockpos = pPos.relative(this.growthDirection);
		int i = Math.min(pState.getValue(AGE) + 1, 25);
		int j = this.getBlocksToGrowWhenBonemealed(pRandom);

		for (int k = 0; k < j && this.canGrowInto(pLevel.getBlockState(blockpos)); k++) {
			pLevel.setBlockAndUpdate(blockpos, pState.setValue(AGE, Integer.valueOf(i)));
			blockpos = blockpos.relative(this.growthDirection);
			i = Math.min(i + 1, 25);
		}
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return SimpleMultiloggedBlock.super.canPlaceLiquid(player, getter, pos, state, fluid);
	}
}
