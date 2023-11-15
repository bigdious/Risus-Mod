package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FleshySkinBlock extends ActuallyUseableDirectionalBlock {

	public FleshySkinBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		RandomSource random = RandomSource.create();
		if(player.getMainHandItem().is(RisusItems.ORGANIC_MATTER.get())){
			level.setBlock(pos, RisusBlocks.HAIRY_FLESHY_SKIN.get().defaultBlockState().setValue(ActuallyUseableDirectionalBlock.FACING, state.getValue(FACING)), 11);
			player.getMainHandItem().shrink(1);
			ParticleUtils.spawnParticlesOnBlockFace(level , pos, (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)),UniformInt.of(1, 4), state.getValue(FACING) , () -> new Vec3(Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1), Mth.nextDouble(random, -0.1, 0.1)) , 0.6);
			level.playSound(null, pos, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.PLAYERS);
			return InteractionResult.SUCCESS;


		}
		return super.use(state, level, pos, player, hand, result);
		}
	}
