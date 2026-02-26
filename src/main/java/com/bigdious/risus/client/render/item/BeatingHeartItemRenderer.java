package com.bigdious.risus.client.render.item;

import com.bigdious.risus.blocks.BeatingHeartBlock;
import com.bigdious.risus.blocks.entity.BeatingHeartBlockEntity;
import com.bigdious.risus.client.EntityCache;
import com.bigdious.risus.client.render.block.BeatingHeartRenderer;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.items.summoners.LitterItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BeatingHeartItemRenderer extends BlockEntityWithoutLevelRenderer {
	public BeatingHeartItemRenderer() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
		Minecraft minecraft = Minecraft.getInstance();
		BlockItemStateProperties stateProperties = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
		BeatingHeartBlockEntity beatingHeart = new BeatingHeartBlockEntity(BlockPos.ZERO, RisusBlocks.BEATING_HEART.get().defaultBlockState()
			.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
			.setValue(BeatingHeartBlock.HealthEffectEnum.HEALTH_EFFECT,
				stateProperties.isEmpty() ? BeatingHeartBlock.HealthEffectEnum.EMPTY : stateProperties.get(BeatingHeartBlock.HealthEffectEnum.HEALTH_EFFECT)));
		if (minecraft.getBlockEntityRenderDispatcher().getRenderer(beatingHeart) instanceof BeatingHeartRenderer renderer) {
			renderer.render(beatingHeart, 0, pose, buffer, light, overlay);
		}

	}

}
