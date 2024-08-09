package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.Objects;

public class RisusBook extends Item {

    public RisusBook(Item.Properties props) {
		super(props);
	}

		@Override
		public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

			if (player instanceof ServerPlayer playerser) {
				PatchouliAPI.get().openBookGUI(playerser, BuiltInRegistries.ITEM.getKey(this));
				return super.use(level, player, hand);
			}
			return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
		}

}
