package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class CurvedSkinBlock extends MultiDirectionalBlock {

	public CurvedSkinBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		RandomSource random = RandomSource.create();
		if(player.getMainHandItem().is(RisusItems.ORGANIC_MATTER.get())){
			level.setBlock(pos, RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get().defaultBlockState().setValue(MultiDirectionalBlock.ORIENTATION, state.getValue(ORIENTATION)), 11);
			player.getMainHandItem().shrink(1);
			//just making sure they appear on the right faces. Could it be done nicer? Certainly, but idk how and I am not wasting more hours on this
			if(state.getValue(ORIENTATION)== FrontAndTop.UP_EAST){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.UP, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.SOUTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.UP_NORTH){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.UP, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.EAST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.UP_SOUTH){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.UP, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.WEST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.UP_WEST){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.UP, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.NORTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.EAST_UP){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.EAST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.SOUTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.NORTH_UP){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.NORTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.EAST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.SOUTH_UP){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.SOUTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.WEST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.WEST_UP){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.WEST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.NORTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.DOWN_EAST){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.DOWN, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.SOUTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.DOWN_NORTH){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.DOWN, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.EAST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.DOWN_WEST){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.DOWN, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.WEST, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			if(state.getValue(ORIENTATION)== FrontAndTop.UP_EAST){
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.DOWN, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
				ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), Direction.NORTH, () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			}
			level.playSound(null, pos, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.PLAYERS);
			return InteractionResult.SUCCESS;

		}
		return super.use(state, level, pos, player, hand, result);
		}
	}
