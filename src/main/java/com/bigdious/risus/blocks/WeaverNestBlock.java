//package com.bigdious.risus.blocks;
//
//import com.bigdious.risus.blocks.entity.AlterationCatalystBlockEntity;
//import com.bigdious.risus.blocks.entity.WeaverNestBlockEntity;
//import com.bigdious.risus.init.RisusBlockEntities;
//import net.minecraft.core.BlockPos;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.BaseEntityBlock;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.RenderShape;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.shapes.CollisionContext;
//import net.minecraft.world.phys.shapes.VoxelShape;
//import org.jetbrains.annotations.Nullable;
//TODO fix this bullshit
//public class WeaverNestBlock extends BaseEntityBlock {
//	private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
//    public WeaverNestBlock(BlockBehaviour.Properties p_56781_) {
//        super(p_56781_);
//    }
//	@Override
//	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
//		return SHAPE;
//	}
//
//	@Nullable
//	@Override
//	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//		return new WeaverNestBlockEntity(pos, state);
//	}
////	@Nullable
////	@Override
////	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
////		return createTickerHelper(type, BossSpawnerBlockEntity::tick);
////	}
//
//    public void spawnAfterBreak(BlockState p_222477_, ServerLevel p_222478_, BlockPos p_222479_, ItemStack p_222480_, boolean p_222481_) {
//        super.spawnAfterBreak(p_222477_, p_222478_, p_222479_, p_222480_, p_222481_);
//
//    }
//
//    @Override
//    public int getExpDrop(BlockState state, net.minecraft.world.level.LevelReader world, net.minecraft.util.RandomSource randomSource, BlockPos pos, int fortune, int silktouch) {
//        return 15 + randomSource.nextInt(15) + randomSource.nextInt(15);
//    }
//
//    public RenderShape getRenderShape(BlockState p_56794_) {
//        return RenderShape.MODEL;
//    }}
//
////	@Nullable
////	@Override
////	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
////		return createTickerHelper(type, RisusBlockEntities.WEAVER_NEST.get(), WeaverNestBlockEntity::tick);
////	}
////}
//
//
