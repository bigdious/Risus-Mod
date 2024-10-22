package com.bigdious.risus.dispenser;

import com.bigdious.risus.blocks.ActuallyUseableDirectionalBlock;
import com.bigdious.risus.blocks.BiomeBlock;
import com.bigdious.risus.blocks.MultiDirectionalBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.items.OrganicMatterItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class OrganicMatterDispenseBehaviour extends DefaultDispenseItemBehavior {
	boolean fired = false;
	//TODO fix particles
	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		Level level = source.level();
		BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
		BlockState state = level.getBlockState(pos);
		RandomSource random = RandomSource.create();

		//This gets horny
		if (state.is(RisusBlocks.LAUGHING_STALK) && !state.getValue(BiomeBlock.SPREADING)) {
			level.setBlock(pos, RisusBlocks.LAUGHING_STALK.get().withPropertiesOf(state).setValue(BiomeBlock.SPREADING, true), 3);
		}

		//These get preserved
		if (state.is(RisusBlocks.TISSUE)) {
			level.setBlock(pos, RisusBlocks.LIVING_TISSUE.get().withPropertiesOf(state), 3);
			ParticleUtils.spawnParticlesOnBlockFaces(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusBlocks.TISSUE.get())), UniformInt.of(6, 12));
		}
		if (state.is(RisusBlocks.DECOMPOSING_TISSUE)) {
			level.setBlock(pos, RisusBlocks.DECOMPOSED_TISSUE.get().withPropertiesOf(state), 3);
			ParticleUtils.spawnParticlesOnBlockFaces(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusBlocks.TISSUE.get())), UniformInt.of(6, 12));
		}
		if (state.is(RisusBlocks.DECAYING_TISSUE)) {
			level.setBlock(pos, RisusBlocks.DECAYED_TISSUE.get().withPropertiesOf(state), 3);
			ParticleUtils.spawnParticlesOnBlockFaces(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusBlocks.TISSUE.get())), UniformInt.of(6, 12));
		}
		if (state.is(RisusBlocks.ROTTING_TISSUE)) {
			level.setBlock(pos, RisusBlocks.ROTTED_TISSUE.get().withPropertiesOf(state), 3);
			ParticleUtils.spawnParticlesOnBlockFaces(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusBlocks.TISSUE.get())), UniformInt.of(6, 12));
		}

		//These grow
		if (state.is(RisusBlocks.NEURON_HEAD) ||
			state.is(RisusBlocks.NEURON_STEM) ||
			state.is(RisusBlocks.VEINS) ||
			state.is(RisusBlocks.VEINS_END) ||
			state.is(RisusBlocks.SPREADING_REMAINS) ||
			state.is(RisusBlocks.TEETH)
		) {
			OrganicMatterItem.applyOrganicMatter(stack, level, pos, null);
			OrganicMatterItem.addGrowthParticles(level, pos, 10);
		}

		//These grow hair
		if (state.is(RisusBlocks.SKIN)) {
			level.setBlock(pos, RisusBlocks.HAIRY_SKIN.get().withPropertiesOf(state), 3);
			ParticleUtils.spawnParticlesOnBlockFaces(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 1));
		}
		if (state.is(RisusBlocks.FLESHY_SKIN)) {
			level.setBlock(pos, RisusBlocks.HAIRY_FLESHY_SKIN.get().withPropertiesOf(state), 3);
			ParticleUtils.spawnParticlesOnBlockFace(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), state.getValue(ActuallyUseableDirectionalBlock.FACING), () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)), 0.6);
		}
		//may god have mercy upon my soul
		if (state.is(RisusBlocks.CURVED_FLESHY_SKIN)) {
			level.setBlock(pos, RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get().withPropertiesOf(state), 3);

			Direction directionneeded;
			Direction directionneeded2;
			switch (state.getValue(MultiDirectionalBlock.ORIENTATION)) {
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
		}
		this.fired = true;
		return stack;
	}


	@Override
	protected void playSound(BlockSource source) {
		if (this.fired) {
			super.playSound(source);
			this.fired = false;
		} else {
			source.level().levelEvent(LevelEvent.SOUND_DISPENSER_FAIL, source.pos(), 0);
		}
	}
}
