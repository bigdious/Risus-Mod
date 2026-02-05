package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class FadingShadowBlock extends Block{
	//partial copy from FrostedIceBlock + DarknessBlock
	protected static final VoxelShape SHAPE2 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE;
	private static final int NEIGHBORS_TO_AGE = 4;
	private static final int NEIGHBORS_TO_MELT = 2;
	private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.9F, 1.0D);

	public FadingShadowBlock(Properties properties) {
		super(properties);
		this.registerDefaultState((this.stateDefinition.any()).setValue(AGE, 0));
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT).equals("shadow_walker") && level instanceof ServerLevel serverLevel) {
				serverLevel.sendParticles(ParticleTypes.ASH, player.getRandomX(0.5), player.getOnPos().above().getY(), player.getRandomZ(0.5), 1, 0, 0, 0, 0);
		}
		super.stepOn(level, pos, state, entity);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collision) {
		if (collision instanceof EntityCollisionContext entitycollisioncontext) {
			Entity entity = entitycollisioncontext.getEntity();
			if (entity != null) {
				if (entity.fallDistance > 2.5F) {
					return FALLING_COLLISION_SHAPE;
				}
				if (entity instanceof FallingBlockEntity ||
					canEntityWalkOnShadows(entity, pos) && entity.getY()>pos.getY() && collision.isDescending()) {
					return SHAPE2;
				}
			}
		}

		return Shapes.empty();
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f) {
		entity.causeFallDamage(f, canEntityWalkOnShadows(entity, pos) ? 0.0F : 1F, level.damageSources().fall());
	}

	protected int getLightBlock(BlockState p_154828_, BlockGetter p_154829_, BlockPos p_154830_) {
		return 0;
	}

	protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

		if ((random.nextInt(3) == 0 || this.fewerNeigboursThan(level, pos, 4)) && (level.isDay() || level.getBrightness(LightLayer.BLOCK, pos) > 1) && this.slightlyFade(state, level, pos)) {
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

			for(Direction direction : Direction.values()) {
				blockpos$mutableblockpos.setWithOffset(pos, direction);
				BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
				if (blockstate.is(this) && !this.slightlyFade(blockstate, level, blockpos$mutableblockpos)) {
					level.scheduleTick(blockpos$mutableblockpos, this, Mth.nextInt(random, 20, 40));
				}
			}
		} else {
			level.scheduleTick(pos, this, Mth.nextInt(random, 20, 40));
		}


	}

	public static boolean canEntityWalkOnShadows(Entity entity, BlockPos pos) {
		return entity.level().getBrightness(LightLayer.BLOCK, pos) < 1 && entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT) != null && living.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT).equals("shadow_walker");
	}

	private boolean slightlyFade(BlockState state, Level level, BlockPos pos) {

		int i = state.getValue(AGE);
		if (i < 3) {
			level.setBlock(pos, state.setValue(AGE, i + 1), 2);
			return false;
		} else {
			level.removeBlock(pos, false);
			return true;
		}
	}

	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (block.defaultBlockState().is(this) && this.fewerNeigboursThan(level, pos, 2)) {
			level.removeBlock(pos, false);
		}

		super.neighborChanged(state, level, pos, block, fromPos, isMoving);
	}

	private boolean fewerNeigboursThan(BlockGetter level, BlockPos pos, int neighborsRequired) {
		int i = 0;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(Direction direction : Direction.values()) {
			blockpos$mutableblockpos.setWithOffset(pos, direction);
			if (level.getBlockState(blockpos$mutableblockpos).is(this)) {
				++i;
				if (i >= neighborsRequired) {
					return false;
				}
			}
		}

		return true;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return ItemStack.EMPTY;
	}



	static {
		AGE = BlockStateProperties.AGE_3;
	}
}
