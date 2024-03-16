package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.RisusCampfireBlockEntity;
import com.bigdious.risus.fluid.RisusFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Optional;

public class RisusCampfireBlock extends BaseEntityBlock implements SimpleMultiloggedBlock {
	//vanillacopy to allow multilog
	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final BooleanProperty SIGNAL_FIRE = BlockStateProperties.SIGNAL_FIRE;
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	private static final VoxelShape VIRTUAL_FENCE_POST = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private static final int SMOKE_DISTANCE = 5;
	private final boolean spawnParticles;
	private final int fireDamage;
	//start multilog
	public RisusCampfireBlock(boolean pSpawnParticles, int pFireDamage, BlockBehaviour.Properties pProperties) {
		super(pProperties);
		this.spawnParticles = pSpawnParticles;
		this.fireDamage = pFireDamage;
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(LIT, Boolean.valueOf(true)).setValue(SIGNAL_FIRE, Boolean.valueOf(false))
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false)
			.setValue(FACING, Direction.NORTH));
	}
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder
			.add(LIT, SIGNAL_FIRE, FACING)
			.add(BLOODLOGGED)
			.add(WATERLOGGED)
			.add(LAVALOGGED);
	}
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.WATER || fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get();
		LevelAccessor levelaccessor = pContext.getLevel();
		BlockPos blockpos = pContext.getClickedPos();
		return this.defaultBlockState()
			.setValue(BLOODLOGGED, Boolean.valueOf(fluidstate.getType() == RisusFluids.SOURCE_BLOOD.get()))
			.setValue(LAVALOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.LAVA))
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER))
			.setValue(SIGNAL_FIRE, Boolean.valueOf(this.isSmokeSource(levelaccessor.getBlockState(blockpos.below()))))
			.setValue(LIT, Boolean.valueOf(!flag))
			.setValue(FACING, pContext.getHorizontalDirection());
	}
	public FluidState getFluidState(BlockState pState) {
		if (pState.getValue(LAVALOGGED)) {return Fluids.LAVA.getSource().defaultFluidState();}
		if (pState.getValue(BLOODLOGGED)) {return RisusFluids.SOURCE_BLOOD.get().getSource().defaultFluidState();}
		if (pState.getValue(WATERLOGGED)) {return Fluids.WATER.getSource().defaultFluidState();}
		else return  super.getFluidState(pState);
	}
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
		if (pState.getValue(BLOODLOGGED)) {
			pLevel.scheduleTick(pPos, RisusFluids.SOURCE_BLOOD.get(), 5);
		}
		if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		return pFacing == Direction.DOWN ? pState.setValue(SIGNAL_FIRE, Boolean.valueOf(this.isSmokeSource(pNeighborState))) : super.updateShape(pState, pFacing, pNeighborState, pLevel, pPos, pNeighborPos);
	}

	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		BlockEntity blockentity = pLevel.getBlockEntity(pPos);
		if (blockentity instanceof CampfireBlockEntity campfireblockentity) {
			ItemStack itemstack = pPlayer.getItemInHand(pHand);
			Optional<CampfireCookingRecipe> optional = campfireblockentity.getCookableRecipe(itemstack);
			if (optional.isPresent()) {
				if (!pLevel.isClientSide && campfireblockentity.placeFood(pPlayer, pPlayer.getAbilities().instabuild ? itemstack.copy() : itemstack, optional.get().getCookingTime())) {
					pPlayer.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
					return InteractionResult.SUCCESS;
				}

				return InteractionResult.CONSUME;
			}
		}

		return InteractionResult.PASS;
	}

	public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
		if (pState.getValue(LIT) && pEntity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)pEntity)) {
			pEntity.hurt(pLevel.damageSources().inFire(), (float)this.fireDamage);
		}

		super.entityInside(pState, pLevel, pPos, pEntity);
	}

	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if (!pState.is(pNewState.getBlock())) {
			BlockEntity blockentity = pLevel.getBlockEntity(pPos);
			if (blockentity instanceof CampfireBlockEntity) {
				Containers.dropContents(pLevel, pPos, ((CampfireBlockEntity)blockentity).getItems());
			}

			super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
		}
	}

	private boolean isSmokeSource(BlockState pState) {
		return pState.is(Blocks.HAY_BLOCK);
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}

	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
		if (pState.getValue(LIT)) {
			if (pRandom.nextInt(10) == 0) {
				pLevel.playLocalSound((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + pRandom.nextFloat(), pRandom.nextFloat() * 0.7F + 0.6F, false);
			}

			if (this.spawnParticles && pRandom.nextInt(5) == 0) {
				for(int i = 0; i < pRandom.nextInt(1) + 1; ++i) {
					pLevel.addParticle(ParticleTypes.LAVA, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, (double)(pRandom.nextFloat() / 2.0F), 5.0E-5D, (double)(pRandom.nextFloat() / 2.0F));
				}
			}

		}
	}

	public static void dowse(@Nullable Entity pEntity, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		if (pLevel.isClientSide()) {
			for(int i = 0; i < 20; ++i) {
				makeParticles((Level)pLevel, pPos, pState.getValue(SIGNAL_FIRE), true);
			}
		}

		BlockEntity blockentity = pLevel.getBlockEntity(pPos);
		if (blockentity instanceof CampfireBlockEntity) {
			((CampfireBlockEntity)blockentity).dowse();
		}

		pLevel.gameEvent(pEntity, GameEvent.BLOCK_CHANGE, pPos);
	}

	public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
		//water
		if (!pState.getValue(SimpleMultiloggedBlock.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
			boolean flag = pState.getValue(LIT);
			if (flag) {
				if (!pLevel.isClientSide()) {
					pLevel.playSound((Player)null, pPos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
				}

				dowse((Entity)null, pLevel, pPos, pState);
			}

			pLevel.setBlock(pPos, pState
				.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(true))
				.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
				.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
				.setValue(LIT, Boolean.valueOf(false)), 3);
			pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			return true;
		}
		//blood
		if (!pState.getValue(SimpleMultiloggedBlock.BLOODLOGGED) && pFluidState.getType() == RisusFluids.SOURCE_BLOOD.get()) {
			boolean flag = pState.getValue(LIT);
			if (flag) {
				if (!pLevel.isClientSide()) {
					pLevel.playSound((Player)null, pPos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
				}

				dowse((Entity)null, pLevel, pPos, pState);
			}

			pLevel.setBlock(pPos, pState
				.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(true))
				.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(false))
				.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
				.setValue(LIT, Boolean.valueOf(false)), 3);
			pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			return true;
		}
		if (!pState.getValue(SimpleMultiloggedBlock.LAVALOGGED) && pFluidState.getType() == Fluids.LAVA) {
			if (!pLevel.isClientSide()) {
				pLevel.setBlock(pPos, pState
						.setValue(SimpleMultiloggedBlock.LAVALOGGED, Boolean.valueOf(true))
						.setValue(SimpleMultiloggedBlock.BLOODLOGGED, Boolean.valueOf(false))
						.setValue(SimpleMultiloggedBlock.WATERLOGGED, Boolean.valueOf(false))
					.setValue(LIT, Boolean.valueOf(true)), 3);
				pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
			}
			return true;
		}
		else {
			return false;
		}
	}

	public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
		BlockPos blockpos = pHit.getBlockPos();
		if (!pLevel.isClientSide && pProjectile.isOnFire() && pProjectile.mayInteract(pLevel, blockpos) && !pState.getValue(LIT) && !pState.getValue(WATERLOGGED) && !pState.getValue(BLOODLOGGED)) {
			pLevel.setBlock(blockpos, pState.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
		}

	}

	public static void makeParticles(Level pLevel, BlockPos pPos, boolean pIsSignalFire, boolean pSpawnExtraSmoke) {
		RandomSource randomsource = pLevel.getRandom();
		SimpleParticleType simpleparticletype = pIsSignalFire ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
		pLevel.addAlwaysVisibleParticle(simpleparticletype, true, (double)pPos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pPos.getY() + randomsource.nextDouble() + randomsource.nextDouble(), (double)pPos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
		if (pSpawnExtraSmoke) {
			pLevel.addParticle(ParticleTypes.SMOKE, (double)pPos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pPos.getY() + 0.4D, (double)pPos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
		}

	}

	public static boolean isSmokeyPos(Level pLevel, BlockPos pPos) {
		for(int i = 1; i <= 5; ++i) {
			BlockPos blockpos = pPos.below(i);
			BlockState blockstate = pLevel.getBlockState(blockpos);
			if (isLitCampfire(blockstate)) {
				return true;
			}

			boolean flag = Shapes.joinIsNotEmpty(VIRTUAL_FENCE_POST, blockstate.getCollisionShape(pLevel, blockpos, CollisionContext.empty()), BooleanOp.AND); // FORGE: Fix MC-201374
			if (flag) {
				BlockState blockstate1 = pLevel.getBlockState(blockpos.below());
				return isLitCampfire(blockstate1);
			}
		}

		return false;
	}

	public static boolean isLitCampfire(BlockState pState) {
		return pState.hasProperty(LIT) && pState.is(BlockTags.CAMPFIRES) && pState.getValue(LIT);
	}


	public BlockState rotate(BlockState pState, Rotation pRot) {
		return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
	}
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}




	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		if (pLevel.isClientSide) {
			return pState.getValue(LIT) ? createTickerHelper(pBlockEntityType, BlockEntityType.CAMPFIRE, CampfireBlockEntity::particleTick) : null;
		} else {
			return pState.getValue(LIT) ? createTickerHelper(pBlockEntityType, BlockEntityType.CAMPFIRE, CampfireBlockEntity::cookTick) : createTickerHelper(pBlockEntityType, BlockEntityType.CAMPFIRE, CampfireBlockEntity::cooldownTick);
		}
	}

	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		return false;
	}

	public static boolean canLight(BlockState pState) {
		return pState.is(BlockTags.CAMPFIRES, (p_51262_) -> {
			return p_51262_.hasProperty(WATERLOGGED) && p_51262_.hasProperty(BLOODLOGGED) && p_51262_.hasProperty(LIT);
		}) && !pState.getValue(WATERLOGGED) && !pState.getValue(BLOODLOGGED) && !pState.getValue(LIT);
	}


	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RisusCampfireBlockEntity(pos, state);
	}
}
