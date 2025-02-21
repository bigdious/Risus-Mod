package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.DisplayNotchBlockEntity;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class DisplayNotchBlock extends BaseEntityBlock implements SimpleMultiloggedBlock {
	public static final MapCodec<DisplayNotchBlock> CODEC = simpleCodec(DisplayNotchBlock::new);
	private static final VoxelShape SHAPE1 = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 1.0D, 9.0D);
	private static final VoxelShape SHAPE2 = Block.box(7.0D, 15.0D, 7.0D, 9.0D, 16.0D, 9.0D);
	private static final VoxelShape SHAPE3 = Block.box(7.0D, 7.0D, 15.0D, 9.0D, 9.0D, 16.0D);
	private static final VoxelShape SHAPE4 = Block.box(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 1.0D);
	private static final VoxelShape SHAPE5 = Block.box(15.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);
	private static final VoxelShape SHAPE6 = Block.box(0.0D, 7.0D, 7.0D, 1.0D, 9.0D, 9.0D);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty ELEVATE = BooleanProperty.create("elevate");
	public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;

	public static final Map<DyeColor, DeferredBlock<Block>> NOTCH_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), map -> {
		map.put(DyeColor.WHITE, RisusBlocks.WHITE_DISPLAY_NOTCH);
		map.put(DyeColor.ORANGE, RisusBlocks.ORANGE_DISPLAY_NOTCH);
		map.put(DyeColor.MAGENTA, RisusBlocks.MAGENTA_DISPLAY_NOTCH);
		map.put(DyeColor.LIGHT_BLUE, RisusBlocks.LIGHT_BLUE_DISPLAY_NOTCH);
		map.put(DyeColor.YELLOW, RisusBlocks.YELLOW_DISPLAY_NOTCH);
		map.put(DyeColor.LIME, RisusBlocks.LIME_DISPLAY_NOTCH);
		map.put(DyeColor.PINK, RisusBlocks.PINK_DISPLAY_NOTCH);
		map.put(DyeColor.GRAY, RisusBlocks.GRAY_DISPLAY_NOTCH);
		map.put(DyeColor.LIGHT_GRAY, RisusBlocks.LIGHT_GRAY_DISPLAY_NOTCH);
		map.put(DyeColor.CYAN, RisusBlocks.CYAN_DISPLAY_NOTCH);
		map.put(DyeColor.PURPLE, RisusBlocks.PURPLE_DISPLAY_NOTCH);
		map.put(DyeColor.BLUE, RisusBlocks.BLUE_DISPLAY_NOTCH);
		map.put(DyeColor.BROWN, RisusBlocks.BROWN_DISPLAY_NOTCH);
		map.put(DyeColor.GREEN, RisusBlocks.GREEN_DISPLAY_NOTCH);
		map.put(DyeColor.RED, RisusBlocks.RED_DISPLAY_NOTCH);
		map.put(DyeColor.BLACK, RisusBlocks.DISPLAY_NOTCH);
	});

	public DisplayNotchBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
			.setValue(FACING, Direction.UP)
			.setValue(ROTATION, 0)
			.setValue(ELEVATE, false));
	}

	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!(level.getBlockEntity(pos) instanceof DisplayNotchBlockEntity notch))
			return ItemInteractionResult.FAIL;

		if (!notch.getTheItem().isEmpty() && stack.is(ItemTags.SHOVELS)) {
			level.setBlock(pos, state.cycle(ELEVATE), 3);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		} else if (!notch.getTheItem().isEmpty() && stack.is(ItemTags.PICKAXES)) {
			level.setBlock(pos, state.cycle(ROTATION), 3);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		} else if (!notch.getTheItem().isEmpty() && notch.handleBEInteractions(stack, level, pos, state)) {
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		} else {
			if (!level.isClientSide()) {
				if (notch.getTheItem().isEmpty()) {
					notch.setTheItem(player.getInventory().removeItem(player.getInventory().selected, 1));
				} else {
					ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), notch.getTheItem());
					level.addFreshEntity(item);
					notch.setTheItem(ItemStack.EMPTY);
					level.setBlock(pos, state.setValue(ELEVATE, false), 3);
				}

				notch.setChanged();
				level.sendBlockUpdated(pos, state, state, 2);
			}
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

		return this.defaultBlockState()
			.setValue(FACING, context.getClickedFace())
			.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()))
			//the elevation is reversed due to laziness
			.setValue(ELEVATE, false);
	}

	@Override
	public BlockState rotate(BlockState blockState, Rotation rotation) {
		return blockState.setValue(FACING, rotation.rotation().rotate(blockState.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState blockState, Mirror mirror) {
		return blockState.setValue(FACING, mirror.rotation().rotate(blockState.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, FLUIDLOGGED, ELEVATE, ROTATION);
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
		return switch (state.getValue(FACING)) {
			case DOWN -> SHAPE2;
			case NORTH -> SHAPE3;
			case SOUTH -> SHAPE4;
			case WEST -> SHAPE5;
			case EAST -> SHAPE6;
			default -> SHAPE1;
		};
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DisplayNotchBlockEntity(pos, state);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		return createTickerHelper(blockEntityType, RisusBlockEntities.DISPLAY_NOTCH.get(), DisplayNotchBlockEntity::tick);
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
		if (!(newState.getBlock() instanceof DisplayNotchBlock)) {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof Container container) {
				Containers.dropContents(level, pos, container);
				level.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, level, pos, newState, moving);
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
		return new ItemStack(RisusBlocks.DISPLAY_NOTCH);
	}
}
