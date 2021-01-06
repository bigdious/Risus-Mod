package com.bigdious.risus.block;

import com.bigdious.risus.entity.tileentity.MawGutsTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class MawGutsBlock extends ContainerBlock {

    public static final VoxelShape SHAPE = Block.makeCuboidShape(0.01, 0, 0.01, 15.99, 15.99, 15.99);
    
    public MawGutsBlock(Properties properties) {
        super(properties);
    }
    
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
     }
    
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new MawGutsTileEntity();
    }

}
