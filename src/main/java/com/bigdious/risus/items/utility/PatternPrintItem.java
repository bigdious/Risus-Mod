package com.bigdious.risus.items.utility;

import com.bigdious.risus.components.item.PatternPrintComponent;
import com.bigdious.risus.init.RisusDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.stream.Collectors;

public class PatternPrintItem extends Item {
	public PatternPrintItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		ItemStack stack = context.getItemInHand();
//		BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
		if (player.isShiftKeyDown()){
			stack.set(RisusDataComponents.BLOCK_STATE, level.getBlockState(blockpos));
			if (stack.has(RisusDataComponents.BLOCK_STATE) && stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT) == PatternPrintComponent.EMPTY) {
				PatternPrintComponent ppComponent = stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT);
				int range = 3;
				List<BlockPos> nearbyBlocks = BlockPos.betweenClosedStream(blockpos.getX()-range, blockpos.getY(), blockpos.getZ()-range, blockpos.getX()+range, blockpos.getY()+range*2, blockpos.getZ()+range).toList();
				PatternPrintComponent.Mutable ppComponent$mutable = new PatternPrintComponent.Mutable(ppComponent);
				for (BlockPos pos : nearbyBlocks) {
					if (stack.get(RisusDataComponents.BLOCK_STATE).getBlock() == level.getBlockState(blockpos).getBlock() && !level.isClientSide()) {
						//we need relative coordinates, not real coordinates
						player.sendSystemMessage(Component.literal("we found"));
						ppComponent$mutable.addPosition(new BlockPos(blockpos.getX()-pos.getX(), blockpos.getY()-pos.getY(), blockpos.getZ()-pos.getZ()));
						pos.immutable();
					}
					stack.set(RisusDataComponents.PATTERN_PRINT_COMPONENT, ppComponent$mutable.toImmutable());
				}
			}
			return InteractionResult.sidedSuccess(level.isClientSide());
		} else {
			return InteractionResult.FAIL;
		}



//		if (DarknessBlock.canBePlacedAt(level, blockpos1, context.getHorizontalDirection())) {
//			level.playSound(player, blockpos1, RisusSoundEvents.DARKNESS_PLACE.get(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
//			BlockState blockstate1 = RisusBlocks.DARKNESS.get().defaultBlockState().setValue(DarknessBlock.FLUIDLOGGED, SimpleMultiloggedBlock.MultiloggingEnum.getFromFluid(level.getFluidState(blockpos1).getType()));
//			level.setBlock(blockpos1, blockstate1, 11);
//			level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
//			player.awardStat(Stats.ITEM_USED.get(this));
//			return InteractionResult.sidedSuccess(level.isClientSide());
//		} else {
//			return InteractionResult.FAIL;
//		}
	}

	@Override
	public Component getName(ItemStack stack) {
		return stack.has(RisusDataComponents.BLOCK_STATE)
			? Component.translatable(this.getDescriptionId() + ".with_block", stack.get(RisusDataComponents.BLOCK_STATE).getBlock().getName())
			: super.getName(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (stack.has(RisusDataComponents.PATTERN_PRINT_COMPONENT) && stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT) != PatternPrintComponent.EMPTY) {
			tooltip.add(Component.translatable("tooltip.risus.pattern_print").withStyle(ChatFormatting.GRAY));
		}
	}
}

