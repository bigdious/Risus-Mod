package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.google.common.base.Preconditions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiConsumer;

public class RisusGateBlock extends HorizontalDirectionalBlock implements SimpleMultiloggedBlock {
	public static final MapCodec<RisusGateBlock> CODEC = RecordCodecBuilder.mapCodec((p_308823_) -> p_308823_.group(WoodType.CODEC.optionalFieldOf("wood_type").forGetter((p_304842_) -> Optional.ofNullable(p_304842_.type)), propertiesCodec(), SoundEvent.DIRECT_CODEC.optionalFieldOf("open_sound").forGetter((fence) -> Optional.of(fence.openSound).filter((s) -> fence.type == null || s != fence.type.fenceGateOpen())), SoundEvent.DIRECT_CODEC.optionalFieldOf("close_sound").forGetter((fence) -> Optional.of(fence.closeSound).filter((s) -> fence.type == null || s != fence.type.fenceGateClose()))).apply(p_308823_, RisusGateBlock::new));
	public static final BooleanProperty OPEN;
	public static final BooleanProperty POWERED;
	public static final BooleanProperty TALL;
	protected static final VoxelShape Z_SHAPE;
	protected static final VoxelShape X_SHAPE;
	protected static final VoxelShape Z_COLLISION_SHAPE;
	protected static final VoxelShape X_COLLISION_SHAPE;
	protected static final VoxelShape Z_SUPPORT_SHAPE;
	protected static final VoxelShape X_SUPPORT_SHAPE;
	protected static final VoxelShape Z_OCCLUSION_SHAPE;
	protected static final VoxelShape X_OCCLUSION_SHAPE;
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public final SoundEvent openSound;
	public final SoundEvent closeSound;
	private final @Nullable WoodType type;

	public MapCodec<RisusGateBlock> codec() {
		return CODEC;
	}

	public RisusGateBlock(WoodType type, BlockBehaviour.Properties properties) {
		this(Optional.of(type), properties.sound(type.soundType()), Optional.of(type.fenceGateOpen()), Optional.of(type.fenceGateClose()));
	}

	public RisusGateBlock(Optional<WoodType> type, BlockBehaviour.Properties properties, Optional<SoundEvent> openSound, Optional<SoundEvent> closeSound) {
		super(properties);
		Preconditions.checkArgument(type.isPresent() || openSound.isPresent() && closeSound.isPresent(), "Fence gates must have sounds set");
		this.type = type.orElse(null);
		this.openSound = openSound.orElseGet(() -> this.type.fenceGateOpen());
		this.closeSound = closeSound.orElseGet(() -> this.type.fenceGateClose());
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
			.setValue(OPEN, false)
			.setValue(TALL, false)
			.setValue(POWERED, false));
	}

	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
			return (state.getValue(FACING)).getAxis() == Direction.Axis.X ? X_SHAPE : Z_SHAPE;
	}

	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		Direction.Axis direction$axis = facing.getAxis();
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			level.scheduleTick(currentPos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(level));
		}
		if (facingState.getBlock() instanceof RisusGateBlock && (facing==Direction.DOWN || facing==Direction.UP) && (facingState.getValue(FACING) == state.getValue(FACING).getOpposite() || facingState.getValue(FACING) == state.getValue(FACING))) {
			return state.setValue(OPEN, facingState.getValue(OPEN)).setValue(FACING, facingState.getValue(FACING)).setValue(TALL, shouldTall(level, currentPos.above()));
		}
		if ((state.getValue(FACING)).getClockWise().getAxis() != direction$axis) {
			return state.setValue(TALL, shouldTall(level, currentPos.above()));
		}
		 	return state.setValue(TALL, shouldTall(level, currentPos.above()));
	}

	private boolean shouldTall(LevelReader level, BlockPos pos) {
		return level.getBlockState(pos).getBlock() instanceof RisusGateBlock;
	}


	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}


	protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
		if (state.getValue(OPEN)) {
			return Shapes.empty();
		} else {
			return (state.getValue(FACING)).getAxis() == Direction.Axis.Z ? Z_SUPPORT_SHAPE : X_SUPPORT_SHAPE;
		}
	}

	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(OPEN)) {
			return Shapes.empty();
		} else {
			return (state.getValue(FACING)).getAxis() == Direction.Axis.Z ? Z_COLLISION_SHAPE : X_COLLISION_SHAPE;
		}
	}

	protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
			return (state.getValue(FACING)).getAxis() == Direction.Axis.X ? X_OCCLUSION_SHAPE : Z_OCCLUSION_SHAPE;
	}

	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		switch (pathComputationType) {
			case LAND -> {
				return state.getValue(OPEN);
			}
			case AIR -> {
				return state.getValue(OPEN);
			}
			default -> {
				return false;
			}
		}
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = level.hasNeighborSignal(blockpos);
		Direction direction = context.getHorizontalDirection();
		return this.defaultBlockState()
			.setValue(FACING, direction)
			.setValue(OPEN, flag)
			.setValue(POWERED, flag)
			.setValue(TALL, shouldTall(level, blockpos.above()))
			.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}

	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (state.getValue(OPEN)) {
			state = state.setValue(OPEN, false);
			level.setBlock(pos, state, 10);
		} else {
			Direction direction = player.getDirection();
			if (state.getValue(FACING) == direction.getOpposite()) {
				state = state.setValue(FACING, direction);
			}

			state = state.setValue(OPEN, true);
			level.setBlock(pos, state, 10);
		}

		boolean flag = state.getValue(OPEN);
		level.playSound(player, pos, flag ? this.openSound : this.closeSound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
		level.gameEvent(player, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	protected void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
		if (explosion.canTriggerBlocks() && !state.getValue(POWERED)) {
			boolean flag = state.getValue(OPEN);
			level.setBlockAndUpdate(pos, state.setValue(OPEN, !flag));
			level.playSound(null, pos, flag ? this.closeSound : this.openSound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
			level.gameEvent(flag ? GameEvent.BLOCK_CLOSE : GameEvent.BLOCK_OPEN, pos, GameEvent.Context.of(state));
		}

		super.onExplosionHit(state, level, pos, explosion, dropConsumer);
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return SimpleMultiloggedBlock.super.canPlaceLiquid(player, getter, pos, state, fluid);
	}

	@Override
	public ItemStack pickupBlock(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		return SimpleMultiloggedBlock.super.pickupBlock(pPlayer, pLevel, pPos, pState);
	}


	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (!level.isClientSide) {
			boolean flag = level.hasNeighborSignal(pos);
			if (state.getValue(POWERED) != flag) {
				level.setBlock(pos, (state.setValue(POWERED, flag)).setValue(OPEN, flag), 2);
				if (state.getValue(OPEN) != flag) {
					level.playSound(null, pos, flag ? this.openSound : this.closeSound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
					level.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
				}
			}
		}

	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, OPEN, POWERED, FLUIDLOGGED, TALL);
	}

	public static boolean connectsToDirection(BlockState state, Direction direction) {
		return (state.getValue(FACING)).getAxis() == direction.getClockWise().getAxis();
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getValue(MultiloggingEnum.FLUIDLOGGED) != MultiloggingEnum.WATER;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.getValue(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED) == SimpleMultiloggedBlock.MultiloggingEnum.LAVA ? 15 : 0;
	}


	static {
		OPEN = BlockStateProperties.OPEN;
		POWERED = BlockStateProperties.POWERED;
		TALL = BooleanProperty.create("tall");
		Z_SHAPE = Block.box(0.0F, 0.0F, 7.0F, 16.0F, 16.0F, 9.0F);
		X_SHAPE = Block.box(7.0F, 0.0F, 0.0F, 9.0F, 16.0F, 16.0F);
		Z_COLLISION_SHAPE = Block.box(0.0F, 0.0F, 7.0F, 16.0F, 24.0F, 9.0F);
		X_COLLISION_SHAPE = Block.box(7.0F, 0.0F, 0.0F, 9.0F, 24.0F, 16.0F);
		Z_SUPPORT_SHAPE = Block.box(0.0F, 5.0F, 7.0F, 16.0F, 24.0F, 9.0F);
		X_SUPPORT_SHAPE = Block.box(7.0F, 5.0F, 0.0F, 9.0F, 24.0F, 16.0F);
		Z_OCCLUSION_SHAPE = Shapes.or(Block.box(0.0F, 5.0F, 7.0F, 2.0F, 16.0F, 9.0F), Block.box(14.0F, 5.0F, 7.0F, 16.0F, 16.0F, 9.0F));
		X_OCCLUSION_SHAPE = Shapes.or(Block.box(7.0F, 5.0F, 0.0F, 9.0F, 16.0F, 2.0F), Block.box(7.0F, 5.0F, 14.0F, 9.0F, 16.0F, 16.0F));
	}
}
