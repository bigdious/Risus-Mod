package com.bigdious.risus.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class ZitBlock extends DirectionalBlock {

	public static final BooleanProperty POPPED = BooleanProperty.create("popped");

	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.UP, Block.box(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D),
			Direction.DOWN, Block.box(5.0D, 15.0D, 5.0D, 11.0D, 15.0D, 11.0D),
			Direction.EAST, Block.box(0.0D, 5.0D, 5.0D, 1.0D, 11.0D, 11.0D),
			Direction.WEST, Block.box(15.0D, 5.0D, 5.0D, 15.0D, 11.0D, 11.0D),
			Direction.NORTH, Block.box(5.0D, 5.0D, 15.0D, 11.0D, 11.0D, 15.0D),
			Direction.SOUTH, Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 1.0D)
	));

	public ZitBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(POPPED, false).setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}

	protected boolean hasNeighborSignal(Level level, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING).getOpposite();
		return level.hasSignal(pos.relative(direction), direction);
	}

	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos newPos, boolean moving) {
		if (!state.getValue(POPPED) && this.hasNeighborSignal(level, pos, state)) {
			LlamaSpit spit = new LlamaSpit(EntityType.LLAMA_SPIT, level);
			double d0 = pos.getX() - (state.getValue(FACING).getStepX() * 0.5F) + 0.5F;
			double d1 = pos.getY() + 0.5F;
			double d2 = pos.getZ() - (state.getValue(FACING).getStepZ() * 0.5F) + 0.5F;
			double d3 = Math.sqrt(d0 * d0 + d2 * d2) * (double)0.2F;
			spit.setPosRaw(d0, d1, d2);
			spit.shoot(d0, d1 + d3, d2, 1.5F, 11.0F);
			level.playSound(null, pos, SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1.0F, 1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F);
			level.addFreshEntity(spit);
			state.setValue(POPPED, true);
			level.scheduleTick(pos, this, 60);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		state.setValue(POPPED, false);
	}

	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		return reader.getBlockState(blockpos).isFaceSturdy(reader, blockpos, direction);
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
		return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, accessor, pos, newPos);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(POPPED, false);
	}

	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POPPED);
	}
}
