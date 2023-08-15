package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class DarknessBlock extends Block {
    public DarknessBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
    @Override
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 0.F;
    }
}
