package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.DisplayNotchBlockEntity;
import com.bigdious.risus.blocks.entity.DisplayNotchStandBlockEntity;
import com.bigdious.risus.init.RisusBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class DisplayNotchStandBlock extends BaseEntityBlock implements SimpleMultiloggedBlock {
	public static final MapCodec<DisplayNotchStandBlock> CODEC = simpleCodec(DisplayNotchStandBlock::new);
	private static final VoxelShape SHAPE1 = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 1.0D, 9.0D);
	private static final VoxelShape SHAPE2 = Block.box(7.0D, 15.0D, 7.0D, 9.0D, 16.0D, 9.0D);
	private static final VoxelShape SHAPE3 = Block.box(7.0D, 7.0D, 15.0D, 9.0D, 9.0D, 16.0D);
	private static final VoxelShape SHAPE4 = Block.box(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 1.0D);
	private static final VoxelShape SHAPE5 = Block.box(15.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);
	private static final VoxelShape SHAPE6 = Block.box(0.0D, 7.0D, 7.0D, 1.0D, 9.0D, 9.0D);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final EnumProperty<FrontAndTop> ORIENTATION = BlockStateProperties.ORIENTATION;
	public static final BooleanProperty ELEVATE = BooleanProperty.create("elevate");
	public DisplayNotchStandBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
		this.registerDefaultState(this.getStateDefinition().any().setValue(ORIENTATION, FrontAndTop.NORTH_UP));
		this.registerDefaultState(this.getStateDefinition().any().setValue(ELEVATE, false));
	}

	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (level.isClientSide() || hand != InteractionHand.MAIN_HAND || !(level.getBlockEntity(pos) instanceof DisplayNotchBlockEntity notch))
			return ItemInteractionResult.FAIL;
		if (player.isCrouching() && player.getMainHandItem().isEmpty()) {
			if (state.getValue(ELEVATE) == false) {
				level.setBlock(pos, state.setValue(ELEVATE, true), 3);
				return ItemInteractionResult.SUCCESS;
			} else {
				level.setBlock(pos, state.setValue(ELEVATE, false), 3);
				return ItemInteractionResult.SUCCESS;
			}
		}
		else if (notch.getInputItem() != null) {
			if (notch.getInputItem().isEmpty()) {
				notch.setInputItem(player.getInventory().removeItem(player.getInventory().selected, 1));
				InteractionResult.sidedSuccess(level.isClientSide);
			} else {
				ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), notch.getInputItem());
				level.addFreshEntity(item);
				notch.setInputItem(ItemStack.EMPTY);
			}
		}

		level.sendBlockUpdated(pos, state, state, 2);
		return ItemInteractionResult.sidedSuccess(level.isClientSide);
	}
	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		Direction direction = context.getNearestLookingDirection().getOpposite();
		Direction direction1 = switch (direction) {
			case DOWN -> context.getHorizontalDirection().getOpposite();
			case UP -> context.getHorizontalDirection();
			case NORTH, SOUTH, WEST, EAST -> Direction.UP;
		};

		return this.defaultBlockState()
			.setValue(ORIENTATION, FrontAndTop.fromFrontAndTop(direction, direction1))
			.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()))
			//the elevation is reversed due to laziness
			.setValue(ELEVATE, false);
	}
	@Override
	public BlockState rotate(BlockState blockState, Rotation rotation) {
		return blockState.setValue(ORIENTATION, rotation.rotation().rotate(blockState.getValue(ORIENTATION)));
	}

	@Override
	public BlockState mirror(BlockState blockState, Mirror mirror) {
		return blockState.setValue(ORIENTATION, mirror.rotation().rotate(blockState.getValue(ORIENTATION)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ORIENTATION);
		builder.add(FLUIDLOGGED);
		builder.add(ELEVATE);
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

		return super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
	}
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		if (state.getValue(ORIENTATION) == FrontAndTop.DOWN_EAST || state.getValue(ORIENTATION) == FrontAndTop.DOWN_NORTH || state.getValue(ORIENTATION) == FrontAndTop.DOWN_WEST || state.getValue(ORIENTATION) == FrontAndTop.DOWN_SOUTH) return SHAPE2;
		if (state.getValue(ORIENTATION) == FrontAndTop.NORTH_UP) return SHAPE3;
		if (state.getValue(ORIENTATION) == FrontAndTop.SOUTH_UP) return SHAPE4;
		if (state.getValue(ORIENTATION) == FrontAndTop.WEST_UP) return SHAPE5;
		if (state.getValue(ORIENTATION) == FrontAndTop.EAST_UP) return SHAPE6;
		return SHAPE1;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DisplayNotchStandBlockEntity(pos, state);
	}
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, RisusBlockEntities.DISPLAY_NOTCH_STAND.get(), DisplayNotchStandBlockEntity::tick);
	}
}
