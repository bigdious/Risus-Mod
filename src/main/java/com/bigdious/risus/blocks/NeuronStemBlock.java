package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NeuronStemBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public NeuronStemBlock(BlockBehaviour.Properties p_154873_) {
        super(p_154873_, Direction.UP, SHAPE, false);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock)RisusBlocks.NEURON_HEAD.get();
    }
}