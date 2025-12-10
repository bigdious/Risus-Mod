package com.bigdious.risus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class LockableTrapdoor extends RisusTrapDoorBlock{
	public static final BooleanProperty LOCKED;
	private final BlockSetType type;
	protected static final VoxelShape EAST_OPEN_AABB;
	protected static final VoxelShape WEST_OPEN_AABB;
	protected static final VoxelShape SOUTH_OPEN_AABB;
	protected static final VoxelShape NORTH_OPEN_AABB;
	protected static final VoxelShape BOTTOM_AABB;
	protected static final VoxelShape TOP_AABB;

	public LockableTrapdoor(BlockSetType type, Properties properties) {
		super(type, properties);
		this.type = type;
		StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<>(this);
		builder.add(FACING, OPEN, HALF, POWERED, FLUIDLOGGED, LOCKED);
		this.stateDefinition = builder.create(Block::defaultBlockState, BlockState::new);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(FACING, Direction.NORTH)
			.setValue(OPEN, false)
			.setValue(HALF, Half.BOTTOM)
			.setValue(POWERED, false)
			.setValue(LOCKED, false)
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (!state.getValue(OPEN)) {
			return state.getValue(HALF) == Half.TOP ? TOP_AABB : BOTTOM_AABB;
		} else {
			switch (state.getValue(FACING)) {
				case NORTH:
				default:
					return NORTH_OPEN_AABB;
				case SOUTH:
					return SOUTH_OPEN_AABB;
				case WEST:
					return WEST_OPEN_AABB;
				case EAST:
					return EAST_OPEN_AABB;
			}
		}
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {

		if (stack.is(Items.TRIPWIRE_HOOK)) {
			if (!level.isClientSide()) level.setBlock(pos, state.cycle(LOCKED), 2);
			if (level.isClientSide()) player.displayClientMessage(Component.translatable(!state.getValue(LOCKED) ? "lockable_trapdoor.locked" : "lockable_trapdoor.unlocked"), true);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useItemOn(stack, state, level, pos, player, hand, result);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (!this.type.canOpenByHand()) {
			return InteractionResult.PASS;
		}
		if (state.getValue(LOCKED)) {
			if (level.isClientSide()) player.displayClientMessage(Component.translatable("lockable_trapdoor.locked"), true);
			return InteractionResult.PASS;
		} else {
			this.toggleTwo(state, level, pos, player);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	@Override
	protected void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
		if (explosion.canTriggerBlocks() && this.type.canOpenByWindCharge() && !state.getValue(POWERED) && !state.getValue(LOCKED)) {
			this.toggleTwo(state, level, pos, null);
		}

		super.onExplosionHit(state, level, pos, explosion, dropConsumer);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Override
	protected void playSound(@Nullable Player player, Level level, BlockPos pos, boolean isOpened) {
		level.playSound(player, pos, isOpened ? BlockSetType.IRON.trapdoorOpen() : BlockSetType.IRON.trapdoorClose(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
		level.gameEvent(player, isOpened ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
	}

	static {
		LOCKED = BlockStateProperties.LOCKED;
		EAST_OPEN_AABB = Block.box(0.0F, 0.0F, 0.0F, 2.0F, 16.0F, 16.0F);
		WEST_OPEN_AABB = Block.box(14.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
		SOUTH_OPEN_AABB = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 2.0F);
		NORTH_OPEN_AABB = Block.box(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 16.0F);
		BOTTOM_AABB = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F);
		TOP_AABB = Block.box(0.0F, 14.0F, 0.0F, 16.0F, 16.0F, 16.0F);
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 1.0F;
	}
}
