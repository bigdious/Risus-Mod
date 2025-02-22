package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.DepthVaseBlockEntity;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class DepthVaseBlock extends BaseEntityBlock implements SimpleMultiloggedBlock {

	public static final MapCodec<DepthVaseBlock> CODEC = simpleCodec(DepthVaseBlock::new);
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	protected static final VoxelShape BOUNDING_BOX = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);

	public DepthVaseBlock(Properties props) {
		super(props);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, FLUIDLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
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
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return BOUNDING_BOX;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (level.getBlockEntity(pos) instanceof DepthVaseBlockEntity vase) {
			//display slot count when right clicked with notes
			if (stack.is(RisusItems.RESEARCHERS_NOTES)) {
				//this only fires on the client to prevent the message from displaying twice
				if (level.isClientSide()) player.displayClientMessage(Component.literal(vase.depthToSlotRatio + " Slots"), true);
				return ItemInteractionResult.sidedSuccess(level.isClientSide());
			} else {
				//otherwise, attempt to add items into the pot
				if (!player.isCrouching() && !stack.isEmpty()) {
					for (int i = 0; i < vase.depthToSlotRatio; i++) {
						ItemStack currentItem = vase.getItem(i);
						if (currentItem.isEmpty() || (ItemStack.isSameItemSameComponents(stack, currentItem) && currentItem.getCount() < currentItem.getMaxStackSize())) {
							//find the difference between the 2 stacks to get the vase to a full stack and let the player keep the remainder
							//so if a vase has a stack of 35 blocks and the player tries to insert 42, they will only insert 29 to make the vase contents a full stack.
							int toInsert = Math.min(stack.getCount() + currentItem.getCount(), stack.getMaxStackSize()) - currentItem.getCount();
							ItemStack inputItem = stack.split(toInsert);
							if (currentItem.isEmpty()) {
								vase.setItem(i, inputItem);
							} else {
								currentItem.grow(inputItem.getCount());
							}
							player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
							this.playWobbleEffects(vase, level, pos, ParticleTypes.SMOKE);
							return ItemInteractionResult.sidedSuccess(level.isClientSide());
						}
					}
				}
			}
			//if we get here, it means the player was either crouching or wasnt holding an item. This moves further logic to fire from useWithoutItem
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
		return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.getBlockEntity(pos) instanceof DepthVaseBlockEntity vase) {
			//if not crouching try to pull an item out of the vase
			if (!player.isCrouching() && !vase.isEmpty()) {
				for (int i = vase.depthToSlotRatio - 1; i >= 0; i--) {
					if (!vase.getItem(i).isEmpty()) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), vase.getItem(i));
						level.addFreshEntity(item);
						vase.setItem(i, ItemStack.EMPTY);
						this.playWobbleEffects(vase, level, pos, RisusParticles.RISUS_SOUL_PARTICLE.get());
						return InteractionResult.SUCCESS;
					}
				}
			}
			//if no item is fetched or the player is crouching, play fail effects
			level.playSound(null, pos, RisusSoundEvents.DEPTH_VASE_INSERT_FAIL.get(), SoundSource.BLOCKS);
			vase.wobble(DepthVaseBlockEntity.DepthWobbleStyle.NEGATIVE);
			level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	private void playWobbleEffects(DepthVaseBlockEntity entity, Level level, BlockPos pos, ParticleOptions particle) {
		entity.wobble(DepthVaseBlockEntity.DepthWobbleStyle.POSITIVE);
		level.playSound(null, pos, RisusSoundEvents.DEPTH_VASE_INSERT.get(), SoundSource.BLOCKS, 1.0F, 0.7F);
		if (level instanceof ServerLevel serverlevel) {
			serverlevel.sendParticles(
				particle,
				(double) pos.getX() + 0.5D,
				(double) pos.getY() + 1.0D,
				(double) pos.getZ() + 0.5D,
				1,
				0.0D,
				0.0D,
				0.0D,
				0.0D
			);
		}
	}

	@Override
	public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
		BlockPos blockpos = result.getBlockPos();
		if (!level.isClientSide() && projectile.mayInteract(level, blockpos) && projectile.getType().is(RisusTags.Entities.BREAKS_DEPTH_VASES)) {
			level.destroyBlock(blockpos, true, projectile);
		}
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
		Containers.dropContentsOnDestroy(state, newState, level, pos);
		super.onRemove(state, level, pos, newState, moving);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DepthVaseBlockEntity(pos, state);
	}
}
