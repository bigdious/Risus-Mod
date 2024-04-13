package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusCampfireBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RisusCampfireBlock extends CampfireBlock implements SimpleMultiloggedBlock {

	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;

	public RisusCampfireBlock(boolean spawnParticles, int fireDamage, BlockBehaviour.Properties properties) {
		super(spawnParticles, fireDamage, properties);
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(LIT, Boolean.TRUE).setValue(SIGNAL_FIRE, Boolean.FALSE)
				.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
				.setValue(FACING, Direction.NORTH));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIT, SIGNAL_FIRE, FACING, WATERLOGGED, FLUIDLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		LevelAccessor levelaccessor = pContext.getLevel();
		BlockPos blockpos = pContext.getClickedPos();
		return this.defaultBlockState()
				.setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()))
				.setValue(SIGNAL_FIRE, this.isSmokeSource(levelaccessor.getBlockState(blockpos.below())))
				.setValue(LIT, !MultiloggingEnum.getFromFluid(fluidstate.getType()).isExtinguishingFluid())
				.setValue(FACING, pContext.getHorizontalDirection());
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

		return direction == Direction.DOWN ? state.setValue(SIGNAL_FIRE, this.isSmokeSource(neighborState)) : state;
	}

	@Override
	public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
		BlockPos blockpos = pHit.getBlockPos();
		if (!pLevel.isClientSide && pProjectile.isOnFire() && pProjectile.mayInteract(pLevel, blockpos) && !pState.getValue(LIT) && !pState.getValue(FLUIDLOGGED).isExtinguishingFluid()) {
			pLevel.setBlock(blockpos, pState.setValue(BlockStateProperties.LIT, true), 11);
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RisusCampfireBlockEntity(pos, state);
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
		return SimpleMultiloggedBlock.super.canPlaceLiquid(player, getter, pos, state, fluid);
	}

	@Override
	public ItemStack pickupBlock(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		return SimpleMultiloggedBlock.super.pickupBlock(pPlayer, pLevel, pPos, pState);
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Optional.empty();
	}

	@Override
	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		return SimpleMultiloggedBlock.super.placeLiquid(pLevel, pPos, pState, pFluidState);
	}
}
