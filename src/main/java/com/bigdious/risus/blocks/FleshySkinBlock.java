package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FleshySkinBlock extends ActuallyUseableDirectionalBlock {

	public FleshySkinBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (stack.is(RisusItems.ORGANIC_MATTER.get())) {
			level.setBlock(pos, RisusBlocks.HAIRY_FLESHY_SKIN.get().defaultBlockState().setValue(ActuallyUseableDirectionalBlock.FACING, state.getValue(FACING)), 11);
			stack.consume(1, player);
			ParticleUtils.spawnParticlesOnBlockFace(level, pos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Blocks.GRAY_CONCRETE)), UniformInt.of(1, 4), state.getValue(FACING), () -> new Vec3(Mth.nextDouble(level.getRandom(), -0.1, 0.1), Mth.nextDouble(level.getRandom(), -0.1, 0.1), Mth.nextDouble(level.getRandom(), -0.1, 0.1)), 0.6);
			level.playSound(null, pos, RisusSoundEvents.HAIR_GROW.get(), SoundSource.PLAYERS);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useItemOn(stack, state, level, pos, player, hand, result);
	}
}
