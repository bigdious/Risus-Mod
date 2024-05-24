package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBiomes;
import com.bigdious.risus.util.WorldUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class BiomeBlock extends ActuallyUseableDirectionalBlock{
	//copy from Twilight Forest TransCore
	public static final BooleanProperty SPREADING = BooleanProperty.create("spreading");

	public BiomeBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2.0F).sound(SoundType.WOOD).lightLevel((state) -> state.getValue(SPREADING) ? 15 : 0));

		this.registerDefaultState(this.getStateDefinition().any().setValue(SPREADING, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SPREADING);
	}

	//No longer an override, but keep here for sanity
	public int tickRate() {
		return 20;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		level.scheduleTick(pos, this, this.tickRate());
	}

	@Override
	@SuppressWarnings("deprecation")
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (!state.getValue(SPREADING)) return;

		this.playSound(level, pos, rand);
		this.performConversion(level, pos, rand);

		level.scheduleTick(pos, this, this.tickRate());
	}

	protected InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
		if (!state.getValue(SPREADING)) {
			level.setBlockAndUpdate(pos, state.setValue(SPREADING, true));
			level.scheduleTick(pos, this, this.tickRate());
			return InteractionResult.SUCCESS;
		} else if (state.getValue(SPREADING)) {
			level.setBlockAndUpdate(pos, state.setValue(SPREADING, false));
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	void performConversion(ServerLevel level, BlockPos pos, RandomSource rand){
		ResourceKey<Biome> target = RisusBiomes.COALIFICATION;
		Holder<Biome> biome = level.registryAccess().registryOrThrow(Registries.BIOME).getHolderOrThrow(target);
		int range = 20;
		for (int i = 0; i < 16; i++) {
			BlockPos dPos = WorldUtil.randomOffset(rand, pos, range, 0, range);
			if (dPos.distSqr(pos) > 256.0)
				continue;

			if (level.getBiome(dPos).is(target))
				continue;

			int minY = QuartPos.fromBlock(level.getMinBuildHeight());
			int maxY = minY + QuartPos.fromBlock(level.getHeight()) - 1;

			int x = QuartPos.fromBlock(dPos.getX());
			int z = QuartPos.fromBlock(dPos.getZ());

			LevelChunk chunkAt = level.getChunk(dPos.getX() >> 4, dPos.getZ() >> 4);
			for (LevelChunkSection section : chunkAt.getSections()) {
				for (int sy = 0; sy < 16; sy += 4) {
					int y = Mth.clamp(QuartPos.fromBlock(chunkAt.getMinSection() + sy), minY, maxY);
					if (section.getBiomes().get(x & 3, y & 3, z & 3).is(target))
						continue;
					if (section.getBiomes() instanceof PalettedContainer<Holder<Biome>> container)
						container.set(x & 3, y & 3, z & 3, biome);
				}
			}

			if (!chunkAt.isUnsaved()) chunkAt.setUnsaved(true);
			level.getChunkSource().chunkMap.resendBiomesForChunks(List.of(chunkAt));

			break;
		}
	}

	@SuppressWarnings("BooleanMethodIsAlwaysInverted")


	protected void playSound(Level level, BlockPos pos, RandomSource rand) {
	}
}
