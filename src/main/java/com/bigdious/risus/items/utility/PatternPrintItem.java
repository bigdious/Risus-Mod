package com.bigdious.risus.items.utility;

import com.bigdious.risus.components.item.PatternPrintComponent;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.block.RotatedPillarBlock.AXIS;
import static net.minecraft.world.level.block.StairBlock.*;

public class PatternPrintItem extends Item {
	public PatternPrintItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		//this is pattern rotation logic
		ItemStack stack = player.getItemInHand(hand);
		if (stack.has(RisusDataComponents.BLOCK_STATE) && stack.has(RisusDataComponents.PATTERN_PRINT_COMPONENT)) {
			PatternPrintComponent ppComponent = stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT);
			PatternPrintComponent.Mutable ppComponent$mutable = new PatternPrintComponent.Mutable(ppComponent);
			List<BlockPos> rotatedlist = new ArrayList<>();
			for (BlockPos pos : ppComponent.positions()) {
				//we rotate based on positive/negative values of z and x (keeping in mind the 0 cases). Since coordinates are relative, this *should* always be correct
				//throw a sun at big if not
				if (Math.signum(pos.getX()) == Math.signum(pos.getZ())) {
					BlockPos rotatedPos = new BlockPos(-pos.getX(), pos.getY(), pos.getZ());
					rotatedlist.add(rotatedPos);
				}
				else {
					BlockPos rotatedPos = new BlockPos(
						pos.getZ() == 0 ? 0 : pos.getX()==0 ? -pos.getZ() : pos.getX(),
						pos.getY(),
						pos.getX()==0 ? 0 : pos.getZ()==0 ? pos.getX() : -pos.getZ());
					rotatedlist.add(rotatedPos);
				}
			}
			ppComponent$mutable.clearPositions();
			ppComponent$mutable.addList(rotatedlist);
			stack.set(RisusDataComponents.PATTERN_PRINT_COMPONENT, ppComponent$mutable.toImmutable());
			level.playSound(player, player.getOnPos().above(), RisusSoundEvents.PP_ROTATED.get(), SoundSource.PLAYERS);
			return InteractionResultHolder.sidedSuccess(stack, !level.isClientSide());
		}
		return InteractionResultHolder.fail(stack);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		ItemStack stack = context.getItemInHand();
		BlockPos placementPosition = blockpos.relative(context.getClickedFace());
		if (player.getMainHandItem() == stack) {
			if (player.isShiftKeyDown()) {
				if (!level.getBlockState(blockpos).hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF) && !level.getBlockState(blockpos).is(RisusTags.Blocks.COPYRIGHTED)) {
					stack.set(RisusDataComponents.BLOCK_STATE, level.getBlockState(blockpos));
				} else {
					level.playSound(player, player.getOnPos().above(), RisusSoundEvents.PP_DENIED.get(), SoundSource.PLAYERS, 1.8F, 1.8F);
					return InteractionResult.sidedSuccess(level.isClientSide());
				}


				if (stack.has(RisusDataComponents.BLOCK_STATE) && stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT) == PatternPrintComponent.EMPTY) {
					PatternPrintComponent ppComponent = stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT);
					int range = stack.has(RisusDataComponents.PRINT_RANGE) ? stack.get(RisusDataComponents.PRINT_RANGE) : 0;
					List<BlockPos> nearbyBlocks = BlockPos.betweenClosedStream(blockpos.getX() - range, blockpos.getY(), blockpos.getZ() - range, blockpos.getX() + range, blockpos.getY() + range * 2, blockpos.getZ() + range).map(BlockPos::immutable).toList();
					PatternPrintComponent.Mutable ppComponent$mutable = new PatternPrintComponent.Mutable(ppComponent);
					for (BlockPos pos : nearbyBlocks) {
						if (stack.get(RisusDataComponents.BLOCK_STATE).getBlock() == level.getBlockState(pos).getBlock() && !level.isClientSide()) {
							//we need relative coordinates, not real coordinates
							ppComponent$mutable.addPosition(new BlockPos(pos.getX() - blockpos.getX(), pos.getY() - blockpos.getY(), pos.getZ() - blockpos.getZ()));
							pos.immutable();
							//wait, is the saved coordinate flipped?
							//ah fuck it, it works
						}
						stack.set(RisusDataComponents.PATTERN_PRINT_COMPONENT, ppComponent$mutable.toImmutable());
					}
				}
				return InteractionResult.sidedSuccess(level.isClientSide());
			} else if (stack.has(RisusDataComponents.BLOCK_STATE) && stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT) != PatternPrintComponent.EMPTY) {

				boolean flag = false;
				Block patternBlock = stack.get(RisusDataComponents.BLOCK_STATE).getBlock();
				for (BlockPos patternPlacePos : stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT).positions()) {
					//combine the context's placement position with the stored positions to get the placement of blocks for the pattern
					BlockPos finalPos = new BlockPos(placementPosition.getX() + patternPlacePos.getX(), placementPosition.getY() + patternPlacePos.getY(), placementPosition.getZ() + patternPlacePos.getZ());


					if (level.getBlockState(finalPos).canBeReplaced() &&
						(player.getInventory().hasAnyMatching(itemStack -> itemStack.is(patternBlock.asItem())) || player.isCreative())) {
						BlockState placementState = patternBlock.getStateForPlacement(new BlockPlaceContext(context));
						level.setBlock(finalPos, placementState == null ? patternBlock.defaultBlockState() : placementState, 11);
						level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
						player.awardStat(Stats.ITEM_USED.get(patternBlock.asItem()));
						if (!player.isCreative()) {
							player.getInventory().getItem(player.getInventory().findSlotMatchingItem(patternBlock.asItem().getDefaultInstance())).shrink(1);
						}
						flag = true;
					}
				}
				if (!flag) {
					level.playSound(player, player.getOnPos().above(), RisusSoundEvents.PP_FAILED.get(), SoundSource.PLAYERS, 1.8F, 1.8F);
				} else {
					level.playSound(player, blockpos, patternBlock.getSoundType(patternBlock.defaultBlockState(), level, blockpos, null).getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
				return InteractionResult.sidedSuccess(level.isClientSide());
			}
		}
		return InteractionResult.FAIL;
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
			tooltip.add(Component.translatable("tooltip.risus.pattern_print", stack.get(RisusDataComponents.PATTERN_PRINT_COMPONENT).count()).withStyle(ChatFormatting.GRAY));
		}
	}
}

