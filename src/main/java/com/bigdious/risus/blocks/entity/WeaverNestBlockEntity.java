package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.entity.Weaver;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.world.level.WeaverNestHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

public class WeaverNestBlockEntity extends BlockEntity {
	private final WeaverNestHelper nestHelper = new WeaverNestHelper();



	public WeaverNestBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(RisusBlockEntities.WEAVER_NEST.get(), pPos, pBlockState);
	}


	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, WeaverNestBlockEntity pBlockEntity) {
		if (pLevel instanceof ServerLevel serverLevel) {
			pBlockEntity.nestHelper.tick(pState, serverLevel, pPos);
		}
	}
}
