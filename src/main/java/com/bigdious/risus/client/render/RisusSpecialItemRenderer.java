package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.DepthVaseBlock;
import com.bigdious.risus.blocks.entity.DepthVaseBlockEntity;
import com.bigdious.risus.init.RisusBlocks;
import com.google.common.base.Suppliers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Supplier;

public class RisusSpecialItemRenderer extends BlockEntityWithoutLevelRenderer {

	private final DepthVaseBlockEntity vase = new DepthVaseBlockEntity(BlockPos.ZERO, RisusBlocks.DEPTH_VASE.get().defaultBlockState());

	public static final Supplier<RisusSpecialItemRenderer> INSTANCE = Suppliers.memoize(RisusSpecialItemRenderer::new);
	public static final IClientItemExtensions CLIENT_ITEM_EXTENSION = Util.make(() -> new IClientItemExtensions() {
		@Override
		public BlockEntityWithoutLevelRenderer getCustomRenderer() {
			return INSTANCE.get();
		}
	});

	private RisusSpecialItemRenderer() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack itemStack, ItemDisplayContext context, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {
		Item item = itemStack.getItem();
		if (item instanceof BlockItem blockItem) {
			Block block = blockItem.getBlock();
			Minecraft minecraft = Minecraft.getInstance();
			if (block instanceof DepthVaseBlock) {
				minecraft.getBlockEntityRenderDispatcher().renderItem(this.vase, stack, buffer, light, overlay);
			}
		}
	}
}
