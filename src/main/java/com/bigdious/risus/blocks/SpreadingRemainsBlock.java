package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;


public class SpreadingRemainsBlock extends MultifaceBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    public SpreadingRemainsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        super.createBlockStateDefinition(state);
        state.add(FACING, WATERLOGGED);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clicked = context.getClickedFace();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = defaultBlockState().setValue(FACING, clicked).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
            return state;
        }

        for (Direction dir : context.getNearestLookingDirections()) {
            state = defaultBlockState().setValue(FACING, clicked);
            if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
                return state;
            }
        }
        return null;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }



    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelacc, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            levelacc.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelacc));
        }
        if (!this.canSurvive(state, levelacc, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, direction, neighborState, levelacc, pos, neighborPos);
        }
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext blockplace) {
        return !blockplace.getItemInHand().is(RisusItems.SPREADING_REMAINS.get()) || super.canBeReplaced(state, blockplace);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction facing = state.getValue(DirectionalBlock.FACING);
        BlockPos restingPos = pos.relative(facing.getOpposite());
        return canSupportCenter(world, restingPos, facing);
    }

    public boolean isValidStateForPlacement(BlockGetter p_221572_, BlockState p_221573_, BlockPos p_221574_, Direction p_221575_) {
        if (this.isFaceSupported(p_221575_) && (!p_221573_.is(this) || !hasFace(p_221573_, p_221575_))) {
            BlockPos blockpos = p_221574_.relative(p_221575_);
            return canAttachTo(p_221572_, p_221575_, blockpos, p_221572_.getBlockState(blockpos));
        } else {
            return false;
        }
    }

    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }
}