package com.bigdious.risus.blocks;

import com.bigdious.risus.fluid.RisusFluids;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class ZitBlock extends DirectionalBlock implements SimpleMultiloggedBlock{

	public static final BooleanProperty POPPED = BooleanProperty.create("popped");

	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.UP, Block.box(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D),
			Direction.DOWN, Block.box(5.0D, 15.0D, 5.0D, 11.0D, 16.0D, 11.0D),
			Direction.EAST, Block.box(0.0D, 5.0D, 5.0D, 1.0D, 11.0D, 11.0D),
			Direction.WEST, Block.box(15.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D),
			Direction.NORTH, Block.box(5.0D, 5.0D, 15.0D, 11.0D, 11.0D, 16.0D),
			Direction.SOUTH, Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 1.0D)
	));

	public ZitBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(POPPED, false)
			.setValue(FACING, Direction.NORTH)
			.setValue(BLOODLOGGED, false)
			.setValue(WATERLOGGED, false)
			.setValue(LAVALOGGED, false));
	}
	public static final BooleanProperty BLOODLOGGED = SimpleMultiloggedBlock.BLOODLOGGED;
	public static final BooleanProperty LAVALOGGED = SimpleMultiloggedBlock.LAVALOGGED;
	public static final BooleanProperty WATERLOGGED = SimpleMultiloggedBlock.WATERLOGGED;

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
			if (state.getValue(FACING)==Direction.UP) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				spit.shoot(0, 10, 0, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.DOWN) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
				spit.shoot(0, - 10, 0, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.NORTH) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 1);
				spit.shoot(0, 0, pos.getZ()-10, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.SOUTH) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ());
				spit.shoot(0, 0, +10, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.EAST) {
				spit.setPosRaw(pos.getX(), pos.getY()+ 0.5, pos.getZ() + 0.5);
				spit.shoot(10, 0, 0, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.WEST) {
				spit.setPosRaw(pos.getX() + 1, pos.getY()+ 0.5, pos.getZ() + 0.5);
				spit.shoot(-10, 0, 0, 1.5F, 11.0F);
			}
			level.playSound(null, pos, SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1.0F, 1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F);
			level.addFreshEntity(spit);
			level.setBlockAndUpdate(pos, state.setValue(POPPED, true));
			level.scheduleTick(pos, this, 60);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		level.setBlockAndUpdate(pos, state.setValue(POPPED, false));
	}

	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		return reader.getBlockState(blockpos).isFaceSturdy(reader, blockpos, direction);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
		return this.defaultBlockState()
			.setValue(FACING, pContext.getClickedFace())
			.setValue(POPPED, false)
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
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
		if (state.getValue(BLOODLOGGED)) {
			accessor.scheduleTick(pos, RisusFluids.SOURCE_BLOOD.get(), 5);
		}
		if (state.getValue(LAVALOGGED)) {
			accessor.scheduleTick(pos, Fluids.LAVA, Fluids.LAVA.getTickDelay(accessor));
		}
		if (state.getValue(WATERLOGGED)) {
			accessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
		}

		return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, accessor, pos, newPos);
	}
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!state.getValue(POPPED)) {
			LlamaSpit spit = new LlamaSpit(EntityType.LLAMA_SPIT, level);
			if (state.getValue(FACING)==Direction.UP) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				spit.shoot(0, 10, 0, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.DOWN) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
				spit.shoot(0, - 10, 0, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.NORTH) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 1);
				spit.shoot(0, 0, pos.getZ()-10, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.SOUTH) {
				spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ());
				spit.shoot(0, 0, +10, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.EAST) {
				spit.setPosRaw(pos.getX(), pos.getY()+ 0.5, pos.getZ() + 0.5);
				spit.shoot(10, 0, 0, 1.5F, 11.0F);
			}
			else if (state.getValue(FACING)==Direction.WEST) {
				spit.setPosRaw(pos.getX() + 1, pos.getY()+ 0.5, pos.getZ() + 0.5);
				spit.shoot(-10, 0, 0, 1.5F, 11.0F);
			}
			level.playSound(null, pos, SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1.0F, 1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F);
			level.addFreshEntity(spit);
			level.setBlockAndUpdate(pos, state.setValue(POPPED, true));
			level.scheduleTick(pos, this, 60);
			return InteractionResult.SUCCESS;
		}
		else return InteractionResult.FAIL;
	}

	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity pEntity) {
			if (!state.getValue(POPPED) && this.hasNeighborSignal(level, pos, state)) {
				LlamaSpit spit = new LlamaSpit(EntityType.LLAMA_SPIT, level);
				if (state.getValue(FACING)==Direction.UP) {
					spit.setPosRaw(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
					spit.shoot(0, 10, 0, 1.5F, 11.0F);
				}
				else if (state.getValue(FACING)==Direction.DOWN) {
					spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
					spit.shoot(0, - 10, 0, 1.5F, 11.0F);
				}
				else if (state.getValue(FACING)==Direction.NORTH) {
					spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 1);
					spit.shoot(0, 0, pos.getZ()-10, 1.5F, 11.0F);
				}
				else if (state.getValue(FACING)==Direction.SOUTH) {
					spit.setPosRaw(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ());
					spit.shoot(0, 0, +10, 1.5F, 11.0F);
				}
				else if (state.getValue(FACING)==Direction.EAST) {
					spit.setPosRaw(pos.getX(), pos.getY()+ 0.5, pos.getZ() + 0.5);
					spit.shoot(10, 0, 0, 1.5F, 11.0F);
				}
				else if (state.getValue(FACING)==Direction.WEST) {
					spit.setPosRaw(pos.getX() + 1, pos.getY()+ 0.5, pos.getZ() + 0.5);
					spit.shoot(-10, 0, 0, 1.5F, 11.0F);
				}
				level.playSound(null, pos, SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1.0F, 1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F);
				level.addFreshEntity(spit);
				level.setBlockAndUpdate(pos, state.setValue(POPPED, true));
				level.scheduleTick(pos, this, 60);
			}
		}

	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POPPED)
			.add(BLOODLOGGED)
			.add(WATERLOGGED)
			.add(LAVALOGGED);
	}
}
