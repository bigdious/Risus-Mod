package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.BiomeBlockEntity;
import com.bigdious.risus.data.RisusBiomes;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.util.WorldUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class BiomeBlock extends ActuallyUseableDirectionalBlock implements SimpleMultiloggedBlock, EntityBlock {
	//copy from Twilight Forest TransCore
	public static final BooleanProperty SPREADING = BooleanProperty.create("spreading");

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
		Direction.UP, Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
		Direction.DOWN, Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
		Direction.EAST, Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D),
		Direction.WEST, Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D),
		Direction.NORTH, Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D),
		Direction.SOUTH, Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D)
	));

	public BiomeBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2.0F).sound(SoundType.BAMBOO));

		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(SPREADING, false)
			.setValue(FACING, Direction.UP)
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SPREADING)
			.add(FLUIDLOGGED);
	}
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		Direction clicked = context.getClickedFace();
		BlockState state = defaultBlockState()
			.setValue(FACING, clicked)
			.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
		if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
			return state;
		}
		for (Direction dir : context.getNearestLookingDirections()) {
			state = defaultBlockState()
				.setValue(FACING, dir.getOpposite())
				.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
			if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
				return state;
			}
		}
		return null;
	}
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
		}
		if (!this.canSurvive(state, accessor, pos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
		}
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
		if ((level.getBlockEntity(pos) instanceof BiomeBlockEntity laughingStalk) && laughingStalk.decaytime==40) {
			level.setBlockAndUpdate(pos, state.setValue(SPREADING, false));
			laughingStalk.decaytime=0;
		}

		this.performConversion(level, pos, rand, state);
		level.scheduleTick(pos, this, this.tickRate());

	}

	public ItemInteractionResult useItemOn (ItemStack stack,BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		ItemStack held = player.getItemInHand(hand);
		if (!state.getValue(SPREADING) && held.is(RisusItems.ORGANIC_MATTER)) {
			level.setBlockAndUpdate(pos, state.setValue(SPREADING, true));
			level.scheduleTick(pos, this, this.tickRate());
			player.getMainHandItem().shrink(1);
			return ItemInteractionResult.SUCCESS;
		}

		return ItemInteractionResult.FAIL;
	}

	void performConversion(ServerLevel level, BlockPos pos, RandomSource rand, BlockState state) {
		Holder<Biome> biome = level.registryAccess().registryOrThrow(Registries.BIOME).getHolderOrThrow(RisusBiomes.COALIFICATION);
		int range = 9;
		for (int i = 0; i < 16; i++) {
			BlockPos dPos = WorldUtil.randomOffset(rand, pos, range, 0, range);
			if (dPos.distSqr(pos) > 256.0)
				continue;

			if (level.getBiome(dPos).is(RisusBiomes.COALIFICATION))
				continue;

			int minY = QuartPos.fromBlock(level.getMinBuildHeight());
			int maxY = minY + QuartPos.fromBlock(level.getHeight()) - 1;

			int x = QuartPos.fromBlock(dPos.getX());
			int z = QuartPos.fromBlock(dPos.getZ());

			LevelChunk chunkAt = level.getChunk(dPos.getX() >> 4, dPos.getZ() >> 4);
			for (LevelChunkSection section : chunkAt.getSections()) {
				for (int sy = 0; sy < 16; sy += 4) {
					int y = Mth.clamp(QuartPos.fromBlock(chunkAt.getMinSection() + sy), minY, maxY);
					if (section.getBiomes().get(x & 3, y & 3, z & 3).is(RisusBiomes.COALIFICATION))
						continue;
					if (section.getBiomes() instanceof PalettedContainer<Holder<Biome>> container)
						container.set(x & 3, y & 3, z & 3, biome);
				}
			}

			if (!chunkAt.isUnsaved()) chunkAt.setUnsaved(true);
			level.getChunkSource().chunkMap.resendBiomesForChunks(List.of(chunkAt));

			if (state.getValue(SPREADING) && level.getBlockEntity(pos) instanceof BiomeBlockEntity laughingStalk) laughingStalk.decaytime++;
			break;
		}
	}
	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new BiomeBlockEntity(pos, state);
	}
	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		Direction facing = state.getValue(DirectionalBlock.FACING);
		BlockPos restingPos = pos.relative(facing.getOpposite());
		return canSupportCenter(world, restingPos, facing);
	}
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide() ? null : createTickerHelper(type, RisusBlockEntities.BIOME_BLOCK.get(), BiomeBlockEntity::tick);
	}
	@SuppressWarnings("unchecked")
	@Nullable
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type1, BlockEntityType<E> type2, BlockEntityTicker<? super E> ticker) {
		return type2 == type1 ? (BlockEntityTicker<A>) ticker : null;
	}
	@Override
	protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
		if (!pLevel.isClientSide) {
			boolean flag = pState.getValue(SPREADING);
			if (flag != pLevel.hasNeighborSignal(pPos)) {
				if (flag) {
					pLevel.scheduleTick(pPos, this, 4);
				} else {
					pLevel.scheduleTick(pPos, this, 4);
				}
			}
		}
	}
}

