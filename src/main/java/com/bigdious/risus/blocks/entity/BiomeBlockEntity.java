package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BiomeBlockEntity extends BlockEntity {
	public int decaytime;
	public int tickCount;
	public BiomeBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(RisusBlockEntities.BIOME_BLOCK.get(), pPos, pBlockState);
	}
	public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, E e) {
	}
//	public static void serverTick(Level level, BlockPos pos, BlockState state, BiomeBlockEntity blockEntity) {
//		++blockEntity.tickCount;
//		long i = level.getGameTime();
//		switch (blockEntity.getBlockState().getValue(BiomeBlock.MUSIC_PLAYING)) {
//			//set delays based on music length
//			case NONE -> {
//				return;
//			}
//			case MORK -> {
//				if (i % 1340L == 0L) {
//					level.playSound(null, pos, RisusSoundEvents.MUSIC_DISC_MORK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
//				}
//			}
//			case REGN -> {
//				if (i % 2420L == 0L) {
//					level.playSound(null, pos, RisusSoundEvents.MUSIC_DISC_REGN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
//				}
//			}
//			case FEIGR -> {
//				if (i % 1640L == 0L) {
//					level.playSound(null, pos, RisusSoundEvents.MUSIC_DISC_FEIGR.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
//				}
//			}
//			case RAK -> {
//				if (i % 2700L == 0L) {
//					level.playSound(null, pos, RisusSoundEvents.MUSIC_DISC_RAK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
//				}
//			}
//
//		}
//
//	}
}
