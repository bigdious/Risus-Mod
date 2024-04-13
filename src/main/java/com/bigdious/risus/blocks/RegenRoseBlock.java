package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class RegenRoseBlock extends FlowerBlock {
	protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

	public RegenRoseBlock(Supplier<MobEffect> effect, int amp, Properties properties) {
		super(effect, amp, properties);
	}


	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType type) {
		return type == PathComputationType.AIR && !this.hasCollision || super.isPathfindable(state, getter, pos, type);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!level.isClientSide() && entity instanceof LivingEntity living && living.isAlive() && (living.tickCount % 50 == 0 || !living.hasEffect(MobEffects.REGENERATION))) {
			living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100));
		}
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 1.0F;
	}
}
