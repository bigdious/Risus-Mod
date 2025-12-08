package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.blocks.plantblocks.RisusGrowingPlantHeadBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.Tags;

public class NeuronHeadBlock extends RisusGrowingPlantHeadBlock implements SimpleMultiloggedBlock {

	public static final MapCodec<NeuronHeadBlock> CODEC = simpleCodec(NeuronHeadBlock::new);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

	public NeuronHeadBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.UP, SHAPE, true, 0.05);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected MapCodec<NeuronHeadBlock> codec() {
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
		return this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()))
			.setValue(AGE, context.getLevel().getRandom().nextInt(25));
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
	protected Block getBodyBlock() {
		return RisusBlocks.NEURON_STEM.get();
	}

	@Override
	protected int getBlocksToGrowWhenOrganicMattered(RandomSource pRandom) {
		return 1;
	}

	@Override
	protected boolean canGrowInto(BlockState state) {
		return
			state.isAir() ||
				state.getBlock() == Blocks.WATER ||
				state.getBlock() == Blocks.LAVA ||
				state.getBlock() == RisusBlocks.BLOOD_FLUID_BLOCK.get();
	}

	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (stack.canPerformAction(ItemAbilities.SHEARS_HARVEST) && state.getValue(AGE) < 25) {
			level.setBlock(pos, RisusBlocks.NEURON_HEAD.get().defaultBlockState().setValue(AGE, 25), 2);
			level.playSound(player, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useItemOn(stack, state, level, pos, player, hand, result);
	}

	@Override
	protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		if (pState.getValue(AGE) < 25 && net.neoforged.neoforge.common.CommonHooks.canCropGrow(pLevel, pPos.relative(this.growthDirection), pState, pRandom.nextDouble() < 0.05)) {
			BlockPos blockpos = pPos.relative(this.growthDirection);
			Block neighborFluid = pLevel.getBlockState(blockpos).getBlock();
			if (this.canGrowInto(pLevel.getBlockState(blockpos))) {
				pLevel.setBlockAndUpdate(blockpos, this.getGrowIntoState(pState.setValue(FLUIDLOGGED, neighborFluid == Blocks.WATER ? MultiloggingEnum.WATER : neighborFluid == Blocks.LAVA ? MultiloggingEnum.LAVA : neighborFluid == RisusBlocks.BLOOD_FLUID_BLOCK.get() ? MultiloggingEnum.BLOOD : MultiloggingEnum.EMPTY), pLevel.random));
				net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(pLevel, blockpos, pLevel.getBlockState(blockpos));
			}
		}
	}

	@Override
	public void performOrganicMatter(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
		BlockPos blockpos = pPos.relative(this.growthDirection);
		Block neighborFluid = pLevel.getBlockState(blockpos).getBlock();
		int i = Math.min(pState.getValue(AGE) + 1, 25);
		int j = this.getBlocksToGrowWhenOrganicMattered(pRandom);

		for (int k = 0; k < j && this.canGrowInto(pLevel.getBlockState(blockpos)); k++) {
			pLevel.setBlockAndUpdate(blockpos, pState.setValue(AGE, i)
				.setValue(FLUIDLOGGED, neighborFluid == Blocks.WATER ? MultiloggingEnum.WATER : neighborFluid == Blocks.LAVA ? MultiloggingEnum.LAVA : neighborFluid == RisusBlocks.BLOOD_FLUID_BLOCK.get() ? MultiloggingEnum.BLOOD : MultiloggingEnum.EMPTY));
			blockpos = blockpos.relative(this.growthDirection);
			i = Math.min(i + 1, 25);
		}
	}
}

