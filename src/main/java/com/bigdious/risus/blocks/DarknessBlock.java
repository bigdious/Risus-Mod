package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class DarknessBlock extends Block implements SimpleMultiloggedBlock {
	protected static final VoxelShape SHAPE = Block.box(1.0D, 1.0D, 1.0D, 14.0D, 14.0D, 14.0D);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public DarknessBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}
	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}
	public static boolean canBePlacedAt(Level level, BlockPos pos, Direction direction) {
		BlockState blockstate = level.getBlockState(pos);
		return blockstate.isAir() || blockstate.is(RisusBlocks.BLOOD_FLUID_BLOCK) || blockstate.is(Blocks.WATER) || blockstate.is(Blocks.LAVA);
	}
	public static BlockState getState(BlockGetter getter, BlockPos pos) {
		BlockState blockstate = getter.getBlockState(pos);
		if (blockstate.getFluidState().is(RisusFluids.SOURCE_BLOOD.get())) return RisusBlocks.DARKNESS.get().defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.BLOOD);
		else if (blockstate.getFluidState().is(Fluids.WATER)) return RisusBlocks.DARKNESS.get().defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.WATER);
		else if (blockstate.getFluidState().is(Fluids.LAVA)) return RisusBlocks.DARKNESS.get().defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.LAVA);
		else return RisusBlocks.DARKNESS.get().defaultBlockState();
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
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		if (context.isHoldingItem(RisusItems.LIGHT_DEVOURER.get())) {
			return SHAPE;
		}
		return Shapes.empty();
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 0.0F;
	}

	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return false;
	}

}
