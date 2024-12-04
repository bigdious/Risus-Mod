package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.BiomeBlock;
import com.bigdious.risus.blocks.DisplayNotchBlock;
import com.bigdious.risus.blocks.interfaces.PlayingMusicEnums;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusSoundEvents;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
