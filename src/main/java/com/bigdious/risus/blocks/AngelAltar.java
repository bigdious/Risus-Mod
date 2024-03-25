package com.bigdious.risus.blocks;

import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class AngelAltar extends Block implements SimpleMultiloggedBlock{
	//start multilogging necessities
	public AngelAltar(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
	}
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder
			.add(BLOODLOGGED)
			.add(WATERLOGGED)
			.add(LAVALOGGED);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		return this.defaultBlockState()
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
		if (pState.getValue(LAVALOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
		}
		if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
	}
	//stop
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		ItemStack held = player.getItemInHand(hand);
		if (held.is(Items.TOTEM_OF_UNDYING)) {
			if(level.getBlockState(pos.above()).is(Blocks.AIR)) {
				if (!level.isClientSide) {
					this.explode(level, pos);
				}
				Angel summonedAngel = RisusEntities.ANGEL.get().create(level);
				summonedAngel.moveTo(pos.getCenter().x, pos.getCenter().y+1, pos.getCenter().z, 0.0F, 0.0F);
				level.addFreshEntity(summonedAngel);
				player.getMainHandItem().shrink(1);
				return InteractionResult.SUCCESS;
				}

			}

		return super.use(state, level, pos, player, hand, result);
	}
	private void explode(Level level,BlockPos pos) {
		Vec3 vec3 = pos.getCenter().add(0,2,0);
		level.explode((Entity)null, level.damageSources().magic(), null, vec3, 50.0F, true, Level.ExplosionInteraction.BLOCK);
	}
}
