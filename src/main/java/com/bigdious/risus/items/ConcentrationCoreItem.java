package com.bigdious.risus.items;

import com.bigdious.risus.entity.Litter;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.phys.Vec3;

public class ConcentrationCoreItem extends Item {

	private static final BlockPattern RITUAL = BlockPatternBuilder.start()
		.aisle("   L   ")
		.aisle("LC L CL")
		.aisle(" LLBLL ")
		.aisle("LC L CL")
		.aisle("   L   ")
		.where('L', BlockInWorld.hasState(BlockStatePredicate.forBlock(RisusBlocks.LINEAR_RITUAL_BLOCK.get())))
		.where('C', BlockInWorld.hasState(BlockStatePredicate.forBlock(RisusBlocks.CURVED_RITUAL_BLOCK.get())))
		.where('B', BlockInWorld.hasState(state -> state.is(RisusTags.Blocks.LITTER_ALLOWED_LIGHT_BLOCKS)))
		.build();

	public ConcentrationCoreItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		var ritual = RITUAL.find(level, pos);
		if (ritual != null) {
			level.setBlockAndUpdate(pos, Blocks.GLASS.defaultBlockState());
			ParticleUtils.spawnParticlesOnBlockFaces(level, pos.above(), ParticleTypes.END_ROD, UniformInt.of(3, 7));
			level.playSound(null, context.getClickedPos(), SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.PLAYERS);

			Litter summonedLitter = new Litter(level, context.getPlayer());
			summonedLitter.setLightBlockState(state);
			summonedLitter.moveTo(Vec3.atBottomCenterOf(pos.above()));
			level.addFreshEntity(summonedLitter);

			for (int x = 0; x < ritual.getWidth(); x++) {
				for (int y = 0; y < ritual.getHeight(); y++) {
					for (int z = 0; z < ritual.getDepth(); z++) {
						var worldBlock = ritual.getBlock(x, y, z);
						if (worldBlock.getState().is(RisusBlocks.CURVED_RITUAL_BLOCK) || worldBlock.getState().is(RisusBlocks.LINEAR_RITUAL_BLOCK)) {
							level.setBlockAndUpdate(worldBlock.getPos(), RisusBlocks.ASHEN_REMAINS.get().defaultBlockState());
							level.addDestroyBlockEffect(worldBlock.getPos(), RisusBlocks.ASHEN_REMAINS.get().defaultBlockState());
						}
					}
				}
			}
			context.getItemInHand().consume(1, context.getPlayer());

			return InteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useOn(context);
	}
}
