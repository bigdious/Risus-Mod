package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class AshenSpireBlock extends Block implements SimpleMultiloggedBlock {
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final EnumProperty<LanternEnum> LANTERN = LanternEnum.LANTERN;
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final BooleanProperty FLIPPED = BooleanProperty.create("flipped");

	public AshenSpireBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(HALF, DoubleBlockHalf.LOWER)
			.setValue(FLIPPED, false)
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
			.setValue(LANTERN, LanternEnum.EMPTY)
		);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (stack.is(ItemTags.HOES)) {
			level.setBlock(pos, state.cycle(FLIPPED), 3);
			if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
				level.setBlock(pos.below(), level.getBlockState(pos.below()).cycle(FLIPPED), 3);
			} else {
				level.setBlock(pos.above(), level.getBlockState(pos.above()).cycle(FLIPPED), 3);
			}
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		if (state.getValue(HALF) == DoubleBlockHalf.UPPER && state.getValue(LanternEnum.LANTERN) == LanternEnum.EMPTY) {
			if (stack.is(RisusBlocks.JOYFLAME_LANTERN.asItem())) {
				level.setBlock(pos, state.setValue(LanternEnum.LANTERN, LanternEnum.CINDERGLEE), 3);
				stack.shrink(1);
				return ItemInteractionResult.sidedSuccess(level.isClientSide());
			}
			if (stack.is(Blocks.LANTERN.asItem())) {
				level.setBlock(pos, state.setValue(LanternEnum.LANTERN, LanternEnum.FIRE), 3);
				stack.shrink(1);
				return ItemInteractionResult.sidedSuccess(level.isClientSide());
			}
			if (stack.is(Blocks.SOUL_LANTERN.asItem())) {
				level.setBlock(pos, state.setValue(LanternEnum.LANTERN, LanternEnum.SOUL), 3);
				stack.shrink(1);
				return ItemInteractionResult.sidedSuccess(level.isClientSide());
			}
		}
		if (state.getValue(HALF) == DoubleBlockHalf.UPPER && state.getValue(LanternEnum.LANTERN) != LanternEnum.EMPTY && stack.isEmpty()) {
			level.setBlock(pos, state.setValue(LanternEnum.LANTERN, LanternEnum.EMPTY), 3);
			popResource(level, pos, new ItemStack(
				state.getValue(LanternEnum.LANTERN) == LanternEnum.FIRE ? Blocks.LANTERN.asItem() :
					state.getValue(LanternEnum.LANTERN) == LanternEnum.SOUL ? Blocks.SOUL_LANTERN.asItem() :
						RisusBlocks.JOYFLAME_LANTERN.asItem()));
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useItemOn(stack, state, level, pos, player, hand, result);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HALF, FLIPPED, FLUIDLOGGED, LANTERN);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
		DoubleBlockHalf half = state.getValue(HALF);
		if (state.getValue(FLUIDLOGGED) != MultiloggingEnum.EMPTY) {
			accessor.scheduleTick(pos, state.getValue(FLUIDLOGGED).getFluid(), state.getValue(FLUIDLOGGED).getFluid().getTickDelay(accessor));
		}
		if (direction.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.is(this) && neighborState.getValue(HALF) != half) {
			return half == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, accessor, pos, neighborPos);
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}
	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.below();
		return canSupportRigidBlock(level, blockpos) || canSupportCenter(level, blockpos, Direction.UP);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		boolean top = state.getValue(HALF) == DoubleBlockHalf.UPPER;
		BlockPos blockpos = top ? pos.below() : pos.above();
		level.setBlock(blockpos, copyFluidLoggingFrom(level, blockpos, this.defaultBlockState().setValue(HALF, top ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER)), 3);
	}

	public static BlockState copyFluidLoggingFrom(LevelReader reader, BlockPos pos, BlockState state) {
		return state.hasProperty(FLUIDLOGGED) ? state.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(reader.getFluidState(pos).getType())) : state;
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide()) {
			if (player.isCreative()) {
				preventCreativeDropFromBottomPart(level, pos, state, player);
			} else {
				dropResources(state, level, pos, null, player, player.getMainHandItem());
			}
		}

		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack);
	}

	protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			BlockPos blockpos = pos.below();
			BlockState blockstate = level.getBlockState(blockpos);
			if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
				level.setBlock(blockpos, blockstate1, 35);
				level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			}
		}
	}
	@Override
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
		if (state.getValue(LanternEnum.LANTERN) != LanternEnum.EMPTY && newState.getBlock() != state.getBlock()) {
			popResource(level, pos, new ItemStack(
				state.getValue(LanternEnum.LANTERN) == LanternEnum.FIRE ? Blocks.LANTERN.asItem() :
					state.getValue(LanternEnum.LANTERN) == LanternEnum.SOUL ? Blocks.SOUL_LANTERN.asItem() :
						RisusBlocks.JOYFLAME_LANTERN.asItem()));
		}
		super.onRemove(state, level, pos, newState, moving);
	}


	public enum LanternEnum implements StringRepresentable {
		EMPTY,
		FIRE,
		SOUL,
		CINDERGLEE;

		public static final EnumProperty<LanternEnum> LANTERN = EnumProperty.create("lantern", LanternEnum.class);

		@Override
		public String getSerializedName() {return this.name().toLowerCase(Locale.ROOT); }
	}
}