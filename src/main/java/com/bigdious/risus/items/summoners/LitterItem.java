package com.bigdious.risus.items.summoners;

import com.bigdious.risus.entity.creatures.pets.Litter;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class LitterItem extends Item {
	public LitterItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		BlockHitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		ItemStack stack = player.getItemInHand(hand);
		if (result.getType() == HitResult.Type.BLOCK) {
			Vec3 vec = result.getLocation();
			if (!level.isClientSide()) {
				Litter entity = RisusEntities.LITTER.get().create(level);
				if (entity != null) {
					entity.moveTo(vec);
					entity.tame(player);
					entity.setLightBlockState(stack.getOrDefault(RisusDataComponents.BLOCK_STATE, Blocks.STONE.defaultBlockState()));
					level.addFreshEntity(entity);
					if (stack.has(DataComponents.CUSTOM_NAME)) {
						entity.setCustomName(stack.getHoverName());
					}
					stack.consume(1, player);
				}
			}
			return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
		}
		return InteractionResultHolder.pass(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (stack.has(RisusDataComponents.BLOCK_STATE)) {
			tooltip.add(stack.get(RisusDataComponents.BLOCK_STATE).getBlock().getName().withStyle(ChatFormatting.GRAY));
		}
	}
	@Override
	@Nullable
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.HEAD;
	}
}
