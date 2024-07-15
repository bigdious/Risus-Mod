package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SkinBlock extends Block {

	public SkinBlock(Properties properties) {
		super(properties);
	}


	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (player.getMainHandItem().is(RisusItems.ORGANIC_MATTER.get())) {
			level.setBlock(pos, RisusBlocks.HAIRY_SKIN.get().defaultBlockState(), 11);
			player.getMainHandItem().shrink(1);
			ParticleUtils.spawnParticlesOnBlockFaces(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 1));
			level.playSound(null, pos, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.PLAYERS);
			return InteractionResult.SUCCESS;

		}
		return use(state, level, pos, player, hand, result);
	}
}
