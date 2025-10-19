package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public abstract class RisusCrossCollisionBlock extends Block implements SimpleMultiloggedBlock {
	//vanillacopy of CrossCollisionBlock to implement multilogging

	public static final BooleanProperty NORTH;
	public static final BooleanProperty EAST;
	public static final BooleanProperty SOUTH;
	public static final BooleanProperty WEST;
	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
	protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION;
	protected final VoxelShape[] collisionShapeByIndex;
	protected final VoxelShape[] shapeByIndex;
	private final Object2IntMap<BlockState> stateToIndex = new Object2IntOpenHashMap<>();

	protected RisusCrossCollisionBlock(float nodeWidth, float extensionWidth, float nodeHeight, float extensionHeight, float collisionHeight, BlockBehaviour.Properties properties) {
		super(properties);
		this.collisionShapeByIndex = this.makeShapes(nodeWidth, extensionWidth, collisionHeight, 0.0F, collisionHeight);
		this.shapeByIndex = this.makeShapes(nodeWidth, extensionWidth, nodeHeight, 0.0F, extensionHeight);
		UnmodifiableIterator<BlockState> var7 = this.stateDefinition.getPossibleStates().iterator();

		while(var7.hasNext()) {
			BlockState blockstate = var7.next();
			this.getAABBIndex(blockstate);
		}

	}

	protected abstract MapCodec<? extends CrossCollisionBlock> codec();

	protected VoxelShape[] makeShapes(float nodeWidth, float extensionWidth, float nodeHeight, float extensionBottom, float extensionHeight) {
		float f = 8.0F - nodeWidth;
		float f1 = 8.0F + nodeWidth;
		float f2 = 8.0F - extensionWidth;
		float f3 = 8.0F + extensionWidth;
		VoxelShape voxelshape = Block.box(f, 0.0F, f, f1, nodeHeight, f1);
		VoxelShape voxelshape1 = Block.box(f2, extensionBottom, 0.0F, f3, extensionHeight, f3);
		VoxelShape voxelshape2 = Block.box(f2, extensionBottom, f2, f3, extensionHeight, 16.0F);
		VoxelShape voxelshape3 = Block.box(0.0F, extensionBottom, f2, f3, extensionHeight, f3);
		VoxelShape voxelshape4 = Block.box(f2, extensionBottom, f2, 16.0F, extensionHeight, f3);
		VoxelShape voxelshape5 = Shapes.or(voxelshape1, voxelshape4);
		VoxelShape voxelshape6 = Shapes.or(voxelshape2, voxelshape3);
		VoxelShape[] avoxelshape = new VoxelShape[]{Shapes.empty(), voxelshape2, voxelshape3, voxelshape6, voxelshape1, Shapes.or(voxelshape2, voxelshape1), Shapes.or(voxelshape3, voxelshape1), Shapes.or(voxelshape6, voxelshape1), voxelshape4, Shapes.or(voxelshape2, voxelshape4), Shapes.or(voxelshape3, voxelshape4), Shapes.or(voxelshape6, voxelshape4), voxelshape5, Shapes.or(voxelshape2, voxelshape5), Shapes.or(voxelshape3, voxelshape5), Shapes.or(voxelshape6, voxelshape5)};

		for(int i = 0; i < 16; ++i) {
			avoxelshape[i] = Shapes.or(voxelshape, avoxelshape[i]);
		}

		return avoxelshape;
	}

	protected boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getValue(FLUIDLOGGED) == MultiloggingEnum.WATER;
	}

	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.shapeByIndex[this.getAABBIndex(state)];
	}

	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.collisionShapeByIndex[this.getAABBIndex(state)];
	}

	private static int indexFor(Direction facing) {
		return 1 << facing.get2DDataValue();
	}

	protected int getAABBIndex(BlockState state) {
		return this.stateToIndex.computeIntIfAbsent(state, (state1) -> {
			int i = 0;
			if (state1.getValue(NORTH)) {
				i |= indexFor(Direction.NORTH);
			}

			if (state1.getValue(EAST)) {
				i |= indexFor(Direction.EAST);
			}

			if (state1.getValue(SOUTH)) {
				i |= indexFor(Direction.SOUTH);
			}

			if (state1.getValue(WEST)) {
				i |= indexFor(Direction.WEST);
			}

			return i;
		});
	}

	protected FluidState getFluidState(BlockState state) {
		return state.getValue(FLUIDLOGGED).getFluid().defaultFluidState();
	}

	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

	protected BlockState rotate(BlockState state, Rotation rot) {
		switch (rot) {
			case CLOCKWISE_180 -> {
				return (((state.setValue(NORTH, state.getValue(SOUTH))).setValue(EAST, state.getValue(WEST))).setValue(SOUTH, state.getValue(NORTH))).setValue(WEST, state.getValue(EAST));
			}
			case COUNTERCLOCKWISE_90 -> {
				return (((state.setValue(NORTH, state.getValue(EAST))).setValue(EAST, state.getValue(SOUTH))).setValue(SOUTH, state.getValue(WEST))).setValue(WEST, state.getValue(NORTH));
			}
			case CLOCKWISE_90 -> {
				return (((state.setValue(NORTH, state.getValue(WEST))).setValue(EAST, state.getValue(NORTH))).setValue(SOUTH, state.getValue(EAST))).setValue(WEST, state.getValue(SOUTH));
			}
			default -> {
				return state;
			}
		}
	}

	protected BlockState mirror(BlockState state, Mirror mirror) {
		switch (mirror) {
			case LEFT_RIGHT -> {
				return (state.setValue(NORTH, state.getValue(SOUTH))).setValue(SOUTH, state.getValue(NORTH));
			}
			case FRONT_BACK -> {
				return (state.setValue(EAST, state.getValue(WEST))).setValue(WEST, state.getValue(EAST));
			}
			default -> {
				return super.mirror(state, mirror);
			}
		}
	}

	static {
		NORTH = PipeBlock.NORTH;
		EAST = PipeBlock.EAST;
		SOUTH = PipeBlock.SOUTH;
		WEST = PipeBlock.WEST;
		PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((p_52346_) -> (p_52346_.getKey()).getAxis().isHorizontal()).collect(Util.toMap());
	}
}
