package com.bigdious.risus.blocks;


import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.entity.DepthVaseBlockEntity;
import com.bigdious.risus.entity.projectile.ThrownAxe;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vazkii.patchouli.api.PatchouliAPI;

public class DepthVaseBlock extends BaseEntityBlock implements SimpleMultiloggedBlock {
	//start multilogging
	public DepthVaseBlock(Properties props) {
		super(props);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(FACING, Direction.NORTH)
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
	}
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
	public BlockState rotate(BlockState pState, Rotation pRot) {
		return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)))
			.setValue(BLOODLOGGED, Boolean.valueOf(false))
			.setValue(WATERLOGGED, Boolean.valueOf(false))
			.setValue(LAVALOGGED, Boolean.valueOf(false));
	}
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING)
			.add(BLOODLOGGED)
			.add(WATERLOGGED)
			.add(LAVALOGGED);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection())
			.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
			.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}
	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {return Fluids.LAVA.getSource().defaultFluidState();}
		if (pState.getValue(BLOODLOGGED)) {return RisusFluids.SOURCE_BLOOD.get().getSource().defaultFluidState();}
		if (pState.getValue(WATERLOGGED)) {return Fluids.WATER.getSource().defaultFluidState();}
		else return  super.getFluidState(pState);
	}
	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
		if (pState.getValue(BLOODLOGGED)) {
			pLevel.scheduleTick(pPos, RisusFluids.SOURCE_BLOOD.get(), 5);
		}
		else if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		else if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
	}
	//stop multilogging
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof DepthVaseBlockEntity vase) {
				vase.setCustomName(stack.getHoverName());
			}
		}
	}
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (hand != InteractionHand.MAIN_HAND || !(level.getBlockEntity(pos) instanceof DepthVaseBlockEntity depthVase))
			return InteractionResult.PASS;
		if (player.getMainHandItem().is(PatchouliAPI.get().getBookStack(new ResourceLocation(Risus.MODID,"research_notes")).getItem())) {
			if (depthVase.depthToSlotRatio==1) {
				player.sendSystemMessage(Component.literal(depthVase.depthToSlotRatio + " Slot"));
				return InteractionResult.SUCCESS;
			}
			else {
				player.sendSystemMessage(Component.literal(depthVase.depthToSlotRatio + " Slots"));
				return InteractionResult.SUCCESS;
			}
		}
		else {
			if (!player.getMainHandItem().isEmpty() && !player.isCrouching()) {
				for (int i = 0; i < depthVase.depthToSlotRatio + 1; ++i) {
					if (depthVase.canMergeItems(player.getMainHandItem(), depthVase.getInputItem(i)) && depthVase.getInputItem(i).getCount() < depthVase.getInputItem(i).getMaxStackSize()) {
						if (depthVase.getInputItem(i).getCount() + player.getMainHandItem().getCount() <= depthVase.getInputItem(i).getMaxStackSize()) {
							depthVase.getInputItem(i).grow(player.getMainHandItem().getCount());
							player.getMainHandItem().shrink(player.getMainHandItem().getCount());
							return InteractionResult.sidedSuccess(level.isClientSide);
						} else {
							player.getMainHandItem().shrink(depthVase.getInputItem(i).getMaxStackSize() - depthVase.getInputItem(i).getCount());
							depthVase.getInputItem(i).grow(depthVase.getInputItem(i).getMaxStackSize() - depthVase.getInputItem(i).getCount());
							return InteractionResult.sidedSuccess(level.isClientSide);
						}
					} else if (depthVase.getInputItem(i).isEmpty()) {
						depthVase.setInputItem(i, player.getInventory().removeItem(player.getInventory().selected, 1));
						return InteractionResult.sidedSuccess(level.isClientSide);
					}
					if (i > depthVase.depthToSlotRatio) {
						return InteractionResult.FAIL;
					}
				}
			}
			if (player.getMainHandItem().isEmpty() && !player.isCrouching()) {
				for (int i = depthVase.depthToSlotRatio - 1; i >= 0; --i) {
					if (!depthVase.getInputItem(i).isEmpty()) {
						ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), depthVase.getInputItem(i));
						level.addFreshEntity(item);
						depthVase.setInputItem(i, ItemStack.EMPTY);
						return InteractionResult.SUCCESS;
					}
				}
			}
		}
		return InteractionResult.FAIL;
	}


	public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
		BlockPos blockpos = pHit.getBlockPos();
		if (!pLevel.isClientSide && pProjectile.mayInteract(pLevel, blockpos) && pProjectile instanceof ThrownTrident && pProjectile.getDeltaMovement().length() > 0.6D) {
			pLevel.destroyBlock(blockpos, true);
		}
		if (!pLevel.isClientSide && pProjectile.mayInteract(pLevel, blockpos) && pProjectile instanceof ThrownAxe && pProjectile.getDeltaMovement().length() > 0.6D) {
			pLevel.destroyBlock(blockpos, true);
		}

	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
		if (!state.is(newState.getBlock())) {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof Container container) {
				Containers.dropContents(level, pos, container);
				level.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, level, pos, newState, moving);
		}
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DepthVaseBlockEntity(pos, state);
	}
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, RisusBlockEntities.DEPTH_VASE.get(), DepthVaseBlockEntity::tick);
	}
	@Nullable
	@SuppressWarnings("unchecked")
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type1, BlockEntityType<E> type2, BlockEntityTicker<? super E> ticker) {
		return type2 == type1 ? (BlockEntityTicker<A>) ticker : null;
	}

}
