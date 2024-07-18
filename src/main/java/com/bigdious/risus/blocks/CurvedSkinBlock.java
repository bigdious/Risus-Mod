package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;


public class CurvedSkinBlock extends MultiDirectionalBlock {

	public CurvedSkinBlock(Properties p_49795_) {
		super(p_49795_);
	}

	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		RandomSource random = RandomSource.create();
		if (player.getItemInHand(hand).is(RisusItems.ORGANIC_MATTER.get())) {
			level.setBlock(pos, RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get().defaultBlockState().setValue(MultiDirectionalBlock.ORIENTATION, state.getValue(ORIENTATION)), 11);
			if (!player.isCreative())
				player.getMainHandItem().shrink(1);
			//just making sure they appear on the right faces. Could it be done nicer? Certainly, but idk how and I am not wasting more hours on this

			Direction directionneeded;
			Direction directionneeded2;
			switch (state.getValue(ORIENTATION)) {
				case UP_EAST -> {
					directionneeded = Direction.UP;
					directionneeded2 = Direction.SOUTH;
				}
				case UP_NORTH -> {
					directionneeded = Direction.UP;
					directionneeded2 = Direction.EAST;
				}
				case UP_SOUTH -> {
					directionneeded = Direction.UP;
					directionneeded2 = Direction.WEST;
				}
				case UP_WEST -> {
					directionneeded = Direction.UP;
					directionneeded2 = Direction.NORTH;
				}
				case EAST_UP -> {
					directionneeded = Direction.EAST;
					directionneeded2 = Direction.SOUTH;
				}
				case NORTH_UP -> {
					directionneeded = Direction.NORTH;
					directionneeded2 = Direction.EAST;
				}
				case SOUTH_UP -> {
					directionneeded = Direction.SOUTH;
					directionneeded2 = Direction.WEST;
				}
				case WEST_UP -> {
					directionneeded = Direction.WEST;
					directionneeded2 = Direction.NORTH;
				}
				case DOWN_EAST -> {
					directionneeded = Direction.DOWN;
					directionneeded2 = Direction.SOUTH;
				}
				case DOWN_NORTH -> {
					directionneeded = Direction.DOWN;
					directionneeded2 = Direction.EAST;
				}
				case DOWN_WEST -> {
					directionneeded = Direction.DOWN;
					directionneeded2 = Direction.WEST;
				}
				default -> {
					directionneeded = Direction.DOWN;
					directionneeded2 = Direction.NORTH;
				}
			}
			ParticleUtils.spawnParticlesOnBlockFace(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), directionneeded, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)), 0.6);
			ParticleUtils.spawnParticlesOnBlockFace(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), directionneeded2, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)), 0.6);

			level.playSound(null, pos, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.PLAYERS);
			return ItemInteractionResult.SUCCESS;

		}
		return ItemInteractionResult.FAIL;
	}
}
