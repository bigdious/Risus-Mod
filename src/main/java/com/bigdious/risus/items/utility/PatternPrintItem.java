package com.bigdious.risus.items.utility;

import com.bigdious.risus.components.item.PatternPrintComponent;
import com.bigdious.risus.entity.projectile.EggSac;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
				stack.set(RisusDataComponents.BLOCK_STATE, level.getBlockState(blockpos));
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
						level.setBlock(finalPos, patternBlock.defaultBlockState(), 11);
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


//	@Override
//	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//		ItemStack itemstack = player.getItemInHand(hand);
//		if (!level.isClientSide() && player.isShiftKeyDown() && ) {
//			EggSac eggSac = new EggSac(level, player);
//			eggSac.setItem(itemstack);
//			eggSac.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
//			level.addFreshEntity(eggSac);
//		}
//
//		player.awardStat(Stats.ITEM_USED.get(this));
//		itemstack.consume(1, player);
//		return InteractionResultHolder.success(itemstack);
//	}

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

