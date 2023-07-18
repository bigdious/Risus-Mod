package com.bigdious.risus.init;

import com.bigdious.risus.items.OrganicMatterItem;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class RisusDispenserRegistry {

	public static void init() {
		DispenserBlock.registerBehavior(RisusItems.ORGANIC_MATTER.get(), new OptionalDispenseItemBehavior() {
			public ItemStack execute(BlockSource source, ItemStack stack) {
				BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
				Level level = source.getLevel();
				BlockState blockstate = level.getBlockState(blockpos);
				Optional<BlockState> optional = OrganicMatterItem.getWaxed(blockstate);
				if (optional.isPresent()) {
					level.setBlockAndUpdate(blockpos, optional.get());
					ServerParticleUtils.spawnParticlesOnBlockFaces(source.getLevel(), blockpos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusBlocks.TISSUE.get())), UniformInt.of(6, 12));
					level.playSound(null, blockpos, SoundEvents.SCULK_VEIN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
					stack.shrink(1);
					this.setSuccess(true);
					return stack;
				} else {
					return super.execute(source, stack);
				}
			}

			@Override
			protected void playSound(BlockSource source) {
				super.playSound(source);
			}
		});
	}
}
