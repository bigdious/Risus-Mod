package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.AshenSpireBlock;
import com.bigdious.risus.blocks.CoalificationBlock;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBiomes;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusTags;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.QuartPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;

import java.util.List;

public class CoalificationBlockEntity extends BlockEntity {

	public int timer;
	public int range;

	public CoalificationBlockEntity(BlockPos pos, BlockState blockState) {
		super(RisusBlockEntities.COALIFICATION.get(), pos, blockState);
		this.timer = 0;
		this.range = 12;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, CoalificationBlockEntity entity) {
		//I imagine the below math looks puzzling to someone who is better versed in mathematics than I am, but even so, it does exactly what I want it to
		if (entity.timer > Math.pow(entity.range, 2)) {
			level.setBlock(pos,entity.getBlockState().getValue(CoalificationBlock.FLUIDLOGGED).getFluidBlock().defaultBlockState(), 11);
		} else {
			entity.timer++;
		}
		Holder<Biome> biome = level.registryAccess().registryOrThrow(Registries.BIOME).getHolderOrThrow(RisusBiomes.COALIFICATION);
		//avoiding bound issues
		int rangeDownscaled = entity.range < 4 ? 0 : entity.range - 3;
		int dx = level.getRandom().nextInt(rangeDownscaled * 2 + 1) - rangeDownscaled;
		int dy = level.getRandom().nextInt(rangeDownscaled * 2 + 1) - rangeDownscaled;
		int dz = level.getRandom().nextInt(rangeDownscaled * 2 + 1) - rangeDownscaled;
		BlockPos blockpos = pos.offset(dx, dy, dz);
		BlockState blockstate = level.getBlockState(blockpos);
		if (blockstate.is(RisusTags.Blocks.SPAWN_SPIRE_ON) && level.getBlockState(blockpos.above()).canBeReplaced() && level.getBlockState(blockpos.above(2)).canBeReplaced()) {
			level.setBlock(blockpos.above(), RisusBlocks.ASHEN_SPIRE.get().defaultBlockState()
				.setValue(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED, SimpleMultiloggedBlock.MultiloggingEnum.getFromFluid(level.getFluidState(blockpos.above()).getType()))
				, 3);
			level.setBlock(blockpos.above(2), RisusBlocks.ASHEN_SPIRE.get().defaultBlockState()
				.setValue(AshenSpireBlock.HALF, DoubleBlockHalf.UPPER).setValue(AshenSpireBlock.FLIPPED, level.getBlockState(blockpos.above()).getBlock().defaultBlockState().is(RisusBlocks.ASHEN_SPIRE) ? level.getBlockState(blockpos.above()).getValue(AshenSpireBlock.FLIPPED) : false)
				.setValue(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED, SimpleMultiloggedBlock.MultiloggingEnum.getFromFluid(level.getFluidState(blockpos.above(2)).getType()))
				, 3);
			ServerParticleUtils.spawnParticleInBlock(level, blockpos.above(2), 3, new BlockParticleOption(ParticleTypes.BLOCK, RisusBlocks.ASHEN_SPIRE.get().defaultBlockState()));
			level.playSound(null, blockpos.above(), SoundEvents.CAVE_VINES_PLACE, SoundSource.BLOCKS);
		}
		if (level instanceof ServerLevel serverLevel) {
			for (int i = 0; i < 16; i++) {
				//y needed some random offset too
				BlockPos dPos = entity.randomOffset(serverLevel.getRandom(), pos, entity.range, entity.range + 5, entity.range);

				// Holder<Biome>(dpos).is(biome) is deprecated and could cause issues in the future
				if (serverLevel.getBiome(dPos) == biome)
					continue;

				int minY = QuartPos.fromBlock(serverLevel.getMinBuildHeight());
				int maxY = minY + QuartPos.fromBlock(serverLevel.getHeight()) - 1;

				int x = QuartPos.fromBlock(dPos.getX());
				int z = QuartPos.fromBlock(dPos.getZ());
				int y = QuartPos.fromBlock(dPos.getY());

				// Get chunk at random relative position
				LevelChunk chunkAt = serverLevel.getChunk(dPos.getX() >> 4, dPos.getZ() >> 4);
				int u = QuartPos.fromBlock(chunkAt.getMinBuildHeight());
				int k = u + QuartPos.fromBlock(chunkAt.getHeight()) - 1;
				int l = Mth.clamp(QuartPos.fromBlock(dPos.getY()), u, k);
				int j = chunkAt.getSectionIndex(QuartPos.toBlock(l));
				// Iterate over all sections in the chunk
				//WE ONLY NEED ONE SECTION HERE, THAT'S WHAT THE PROBLEM WAS
				LevelChunkSection section = chunkAt.getSection(j);
				// Iterate over all blocks in quarters in the section
				for (int sy = 0; sy < 16; sy += 4) {
					// Get y position clamped between the minY(0) and maxY(320)
					//this feels like a lie^
//					int y = Mth.clamp(QuartPos.fromBlock(chunkAt.getMinSection() + sy), minY, maxY);

					// Holder<Biome>(x, y, z).is(biome) is deprecated and could cause issues in the future
					// Check if the biome at the position between index 0 and 3 is the same as the biome we want to set
					if (section.getBiomes().get(x & 3, y & 3, z & 3) == (biome))
						continue;

					// Set the biome at the position
					if (section.getBiomes() instanceof PalettedContainer<Holder<Biome>> container)
						// set the biome at the x y z with all 3 coordinates clamped between index 0 and 3 to fit in the 4x4x4 Quarter
						container.set(x & 3, y & 3, z & 3, biome);
				}

				if (!chunkAt.isUnsaved()) chunkAt.setUnsaved(true);
				serverLevel.getChunkSource().chunkMap.resendBiomesForChunks(List.of(chunkAt));
			}
		}

	}

	public BlockPos randomOffset(RandomSource random, BlockPos pos, int rx, int ry, int rz) {
		int dx = random.nextInt(rx * 2 + 1) - rx;
		int dy = random.nextInt(ry * 2 + 1) - ry;
		int dz = random.nextInt(rz * 2 + 1) - rz;
		return pos.offset(dx, dy, dz);
	}

	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("timer", this.timer);
		tag.putInt("range", this.range);
	}


	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		this.timer = tag.getInt("timer");
		this.range = tag.getInt("range");
		super.loadAdditional(tag, registries);
	}


	public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
		CompoundTag tag = new CompoundTag();
		tag.putInt("timer", this.timer);
		tag.putInt("range", this.range);
		super.saveAdditional(tag, pRegistries);
		return tag;
	}
}
