package com.bigdious.risus.blocks;

import com.bigdious.risus.data.BlockTagGenerator;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class JoyflameFireBlock extends SoulFireBlock {

	public JoyflameFireBlock(BlockBehaviour.Properties properties) {
		super(properties);
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


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!level.isClientSide() && entity instanceof LivingEntity living && living.isAlive() && (living.tickCount % 100 == 0 || !living.hasEffect(RisusMobEffects.EXBURN.get()))) {
			living.addEffect(new MobEffectInstance(RisusMobEffects.EXBURN.get(), 600));
		}
	}
}

