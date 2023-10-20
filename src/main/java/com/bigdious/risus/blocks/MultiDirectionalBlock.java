package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class MultiDirectionalBlock extends ActuallyUseableDirectionalBlock{
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(FACING);
        state.add(HORIZONTAL_FACING);

    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clicked = context.getClickedFace();
        BlockState state = defaultBlockState().setValue(FACING, clicked).setValue(HORIZONTAL_FACING, Direction.NORTH);
        if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
            return state;
        }
        for (Direction dir : context.getNearestLookingDirections()) {
            state = defaultBlockState().setValue(FACING, dir.getOpposite()).setValue(HORIZONTAL_FACING, Direction.NORTH);
            if (dir.getOpposite()==Direction.UP){
                this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());}
            if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
                return state;
            }
        }
        return null;
    }
    public MultiDirectionalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP).setValue(HORIZONTAL_FACING, Direction.NORTH));
    }
}
