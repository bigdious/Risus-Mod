package com.bigdious.risus.mixin;

import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpongeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedList;
import java.util.Queue;

@Mixin(SpongeBlock.class)
public class SpongeBlockMixin {
	private static final int MAX_DEPTH = 6;
	private static final int MAX_COUNT = 64;
	private static final Direction[] ALL_DIRECTIONS = Direction.values();

	@Inject(method = "onPlace", at = @At("HEAD"))
	private void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
		tryAbsorbBlood(level, pos);
	}

	private void tryAbsorbBlood(Level level, BlockPos pos) {
		if (this.removeBloodBreadthFirstSearch(level, pos)) {
			level.setBlock(pos, RisusBlocks.BLOODY_SPONGE.get().defaultBlockState(), 2);
			level.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0F, 1.0F);
		}
	}

	private boolean removeBloodBreadthFirstSearch(Level level, BlockPos pos) {
		Queue<BlockPos> queue = new LinkedList<>();
		queue.add(pos);
		int count = 0;

		while (!queue.isEmpty()) {
			BlockPos currentPos = queue.poll();

			for (Direction direction : ALL_DIRECTIONS) {
				BlockPos adjacentPos = currentPos.relative(direction);

				if (isWithinRadius(pos, adjacentPos, MAX_DEPTH) && count < MAX_COUNT) {
					BlockState adjacentState = level.getBlockState(adjacentPos);

					if (adjacentState.getBlock() == RisusBlocks.BLOOD_FLUID_BLOCK.get()) {
						level.setBlock(adjacentPos, Blocks.AIR.defaultBlockState(), 3);
						queue.add(adjacentPos);
						count++;
					}
				}
			}
		}

		return count > 0;
	}

	private boolean isWithinRadius(BlockPos origin, BlockPos target, int radius) {
		return Math.abs(origin.getX() - target.getX()) <= radius &&
			Math.abs(origin.getY() - target.getY()) <= radius &&
			Math.abs(origin.getZ() - target.getZ()) <= radius;
	}
}