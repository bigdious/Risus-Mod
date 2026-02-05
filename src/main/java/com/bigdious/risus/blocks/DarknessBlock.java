package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.RisusItems;
import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.*;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;


public class DarknessBlock extends Block implements SimpleMultiloggedBlock {
	protected static final VoxelShape SHAPE = Block.box(1.0D, 1.0D, 1.0D, 14.0D, 14.0D, 14.0D);
	protected static final VoxelShape SHAPE2 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.9F, 1.0D);


	public DarknessBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT).equals("shadow_walker") && level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.ASH, player.getRandomX(0.5), player.getOnPos().above().getY(), player.getRandomZ(0.5), 1, 0, 0, 0, 0);
		}
		super.stepOn(level, pos, state, entity);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED);
	}

	@Override
	protected int getLightBlock(BlockState p_154828_, BlockGetter p_154829_, BlockPos p_154830_) {
		return 14;
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
	}

	public static boolean canBePlacedAt(Level level, BlockPos pos, Direction direction) {
		BlockState blockstate = level.getBlockState(pos);
		return blockstate.isAir() || blockstate.is(RisusBlocks.BLOOD_FLUID_BLOCK) || blockstate.is(Blocks.WATER) || blockstate.is(Blocks.LAVA);
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
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		if (context.isHoldingItem(RisusItems.LIGHT_DEVOURER.get())) {
			return SHAPE;
		}
		return Shapes.empty();
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
					FadingShadowBlock.canEntityWalkOnShadows(entity, pos) && entity.getY()>pos.getY() && collision.isDescending()) {
					return SHAPE2;
				}
			}
		}

		return Shapes.empty();
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f) {
		entity.causeFallDamage(f, FadingShadowBlock.canEntityWalkOnShadows(entity, pos) ? 0.0F : 1F, level.damageSources().fall());
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 0.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return false;
	}

}
