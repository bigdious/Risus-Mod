package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.BeatingHeartBlockEntity;
import com.bigdious.risus.blocks.entity.WeavingMechanismBlockEntity;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class BeatingHeartBlock extends BaseEntityBlock implements SimpleMultiloggedBlock {
	public static final MapCodec<BeatingHeartBlock> CODEC = simpleCodec(BeatingHeartBlock::new);
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<BeatingHeartBlock.HealthEffectEnum> HEALTH_EFFECT = BeatingHeartBlock.HealthEffectEnum.HEALTH_EFFECT;

	protected static final VoxelShape BOUNDING_BOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);

	public BeatingHeartBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY)
			.setValue(HORIZONTAL_FACING, Direction.NORTH)
			.setValue(HEALTH_EFFECT, BeatingHeartBlock.HealthEffectEnum.EMPTY)
		);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection()).setValue(FLUIDLOGGED, MultiloggingEnum.getFromFluid(fluidstate.getType()));
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FLUIDLOGGED, HORIZONTAL_FACING, HEALTH_EFFECT);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 1.0F;
	}

	@Override
	public BlockState rotate(BlockState blockState, Rotation rotation) {
		return blockState.setValue(HORIZONTAL_FACING, rotation.rotation().rotate(blockState.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState mirror(BlockState blockState, Mirror mirror) {
		return blockState.setValue(HORIZONTAL_FACING, mirror.rotation().rotate(blockState.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return BOUNDING_BOX;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new BeatingHeartBlockEntity(blockPos, blockState);
	}
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, RisusBlockEntities.BEATING_HEART.get(), BeatingHeartBlockEntity::tick);
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.getValue(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED) == SimpleMultiloggedBlock.MultiloggingEnum.LAVA ? 15 : 0;
	}


	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {return CODEC;}

	public enum HealthEffectEnum implements StringRepresentable {
		//this will be so fucking deadly
		EMPTY,
		REGEN,
		HEALTH_BOOST,
		BLOODCLOGGED,
		POISON,
		WITHER,
		ABSORPTION;

		public static final EnumProperty<BeatingHeartBlock.HealthEffectEnum> HEALTH_EFFECT = EnumProperty.create("health_effect", BeatingHeartBlock.HealthEffectEnum.class);

		@Override
		public String getSerializedName() {return this.name().toLowerCase(Locale.ROOT); }
	}
}
