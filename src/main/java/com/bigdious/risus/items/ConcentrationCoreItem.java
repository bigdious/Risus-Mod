package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.BaseRotatableBlock;
import com.bigdious.risus.entity.Litter;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ConcentrationCoreItem extends Item {

	private static final BlockPattern RITUAL = BlockPatternBuilder.start()
		//for some reason, south is up
		.aisle("   y   ")
		.aisle("ye z ny")
		.aisle(" yxBxy ")
		.aisle("ys z wy")
		.aisle("   y   ")
		.where('n', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.NORTH))
		.where('e', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.EAST))
		.where('s', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.SOUTH))
		.where('w', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.WEST))
		.where('y', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y))
		.where('z', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Z))
		.where('x', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.X))
		.where('B', BlockInWorld.hasState(state -> state.is(RisusTags.Blocks.LITTER_ALLOWED_LIGHT_BLOCKS) || state.is(RisusTags.Blocks.ILLEGAL_LITTER_ALLOWED_LIGHT_BLOCKS)))
		.build();

	private static final BlockPattern RITUAL_SIDEWAYS = BlockPatternBuilder.start()
		.aisle(" y y ")
		.aisle(" wys ")
		.aisle("  z  ")
		.aisle("yxBxy")
		.aisle("  z  ")
		.aisle(" nye ")
		.aisle(" y y ")
		.where('n', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.NORTH))
		.where('e', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.EAST))
		.where('s', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.SOUTH))
		.where('w', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.WEST))
		.where('y', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y))
		.where('z', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Z))
		.where('x', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.X))
		.where('B', BlockInWorld.hasState(state -> state.is(RisusTags.Blocks.LITTER_ALLOWED_LIGHT_BLOCKS) || state.is(RisusTags.Blocks.ILLEGAL_LITTER_ALLOWED_LIGHT_BLOCKS)))
		.build();

	public ConcentrationCoreItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		if ((state.is(RisusTags.Blocks.LITTER_ALLOWED_LIGHT_BLOCKS) || (level.getGameRules().getBoolean(Risus.ILLEGAL_LITTERS.get()) && state.is(RisusTags.Blocks.ILLEGAL_LITTER_ALLOWED_LIGHT_BLOCKS)))) {
			var ritual = this.getUsedRitual(level, pos);
			if (ritual != null) {
				level.setBlockAndUpdate(pos, Blocks.GLASS.defaultBlockState());
				ServerParticleUtils.spawnParticlesOnBlockFaces(level, pos.above(), ParticleTypes.END_ROD, UniformInt.of(3, 7));
				level.playSound(null, context.getClickedPos(), RisusSoundEvents.CONCENTRATION_CORE_LITTER.get(), SoundSource.PLAYERS);

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
		}
		return super.useOn(context);
	}

	@Nullable
	private BlockPattern.BlockPatternMatch getUsedRitual(Level level, BlockPos pos) {
		var normal = RITUAL.find(level, pos);
		if (normal != null) return normal;
		return RITUAL_SIDEWAYS.find(level, pos);
	}
}
