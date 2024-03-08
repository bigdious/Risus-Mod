package com.bigdious.risus.blocks;

import com.bigdious.risus.blocks.entity.AlterationCatalystBlockEntity;
import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AlterationCatalystBlock extends BaseEntityBlock {

	private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

	public AlterationCatalystBlock(Properties properties) {
		super(properties);
	}

	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (level.isClientSide() || hand != InteractionHand.MAIN_HAND || !(level.getBlockEntity(pos) instanceof AlterationCatalystBlockEntity alteration))
			return InteractionResult.PASS;
		if (alteration.isCrafting)
			return InteractionResult.PASS;

		if (alteration.getInputItem() != null) {
			if (alteration.getInputItem().isEmpty()) {
				alteration.setInputItem(player.getInventory().removeItem(player.getInventory().selected, 1));
				InteractionResult.sidedSuccess(level.isClientSide);
			} else {
				ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), alteration.getInputItem());
				level.addFreshEntity(item);
				alteration.setInputItem(ItemStack.EMPTY);
			}
		}

		level.sendBlockUpdated(pos, state, state, 2);
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AlterationCatalystBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, RisusBlockEntities.ALTERATION_CATALYST.get(), AlterationCatalystBlockEntity::tick);
	}
}
