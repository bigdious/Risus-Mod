package com.bigdious.risus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class EngravedBasaltBlock extends RotatedPillarBlock {

	public static final VoxelShape SHAPE = Block.makeCuboidShape(0.01, 0, 0.01, 15.99, 15.99, 15.99);
	
	public EngravedBasaltBlock(Properties properties) {
		super(properties);
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
     }


}
