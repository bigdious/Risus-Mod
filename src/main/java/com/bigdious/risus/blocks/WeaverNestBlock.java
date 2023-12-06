package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.WeaverNestBlockEntity;
import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class WeaverNestBlock extends BaseEntityBlock {
	//TODO figure this weavernest shit out
    public WeaverNestBlock(BlockBehaviour.Properties p_56781_) {
        super(p_56781_);
    }

    public BlockEntity newBlockEntity(BlockPos p_154687_, BlockState p_154688_) {
        return new WeaverNestBlockEntity(p_154687_, p_154688_);
    }

    public void spawnAfterBreak(BlockState p_222477_, ServerLevel p_222478_, BlockPos p_222479_, ItemStack p_222480_, boolean p_222481_) {
        super.spawnAfterBreak(p_222477_, p_222478_, p_222479_, p_222480_, p_222481_);

    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.level.LevelReader world, net.minecraft.util.RandomSource randomSource, BlockPos pos, int fortune, int silktouch) {
        return 15 + randomSource.nextInt(15) + randomSource.nextInt(15);
    }

    public RenderShape getRenderShape(BlockState p_56794_) {
        return RenderShape.MODEL;
    }

    public ItemStack getCloneItemStack(BlockGetter p_56785_, BlockPos p_56786_, BlockState p_56787_) {
        return ItemStack.EMPTY;
    }
}

