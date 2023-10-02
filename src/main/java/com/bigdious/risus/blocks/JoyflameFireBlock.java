package com.bigdious.risus.blocks;

import com.bigdious.risus.data.BlockTagGenerator;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.print.attribute.Attribute;
import java.util.UUID;

public class JoyflameFireBlock extends BaseFireBlock {

	public JoyflameFireBlock(BlockBehaviour.Properties properties) {
		super(properties, 2.0F);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return canSurviveOnBlock(context.getLevel().getBlockState(context.getClickedPos().below())) ? RisusBlocks.JOYFLAME_FIRE.get().defaultBlockState() : Blocks.AIR.defaultBlockState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
		return this.canSurvive(state, accessor, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return canSurviveOnBlock(reader.getBlockState(pos.below()));
	}

	public static boolean canSurviveOnBlock(BlockState state) {
		return state.is(BlockTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS);
	}

	@Override
	protected boolean canBurn(BlockState state) {
		return true;
	}

}
