package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusCampfireBlockEntity;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
	protected void entityInside(BlockState pState, Level level, BlockPos pPos, Entity entity) {
		if (pState.getValue(LIT) && entity instanceof LivingEntity) {
			if (!level.isClientSide() && entity instanceof LivingEntity living && living.isAlive() && (living.tickCount % 100 == 0 || !living.hasEffect(RisusMobEffects.EXBURN))) {
				living.addEffect(new MobEffectInstance(RisusMobEffects.EXBURN, 600, 0, false, false, true));
			}
		}
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
	@Override
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
		if (pState.getValue(LIT)) {
			if (pRandom.nextInt(24) == 0) {
				pLevel.playLocalSound(
					(double) pPos.getX() + 0.5,
					(double) pPos.getY() + 0.5,
					(double) pPos.getZ() + 0.5,
					SoundEvents.FIRE_AMBIENT,
					SoundSource.BLOCKS,
					1.0F + pRandom.nextFloat(),
					pRandom.nextFloat() * 0.7F + 0.3F,
					false
				);
			}
			for (int i = 0; i < 2; i++) {
				double d0 = (double) pPos.getX() + pRandom.nextDouble();
				double d1 = (double) pPos.getY() + pRandom.nextDouble() * 0.5 + 0.5;
				double d2 = (double) pPos.getZ() + pRandom.nextDouble();
				pLevel.addParticle(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), d0, d1, d2, 0.0, 0.0, 0.0);
			}
			pLevel.scheduleTick(pPos, this, 10);
		}
	}
}
