package com.bigdious.risus.client;

import com.bigdious.risus.block.AbstractModSkullBlock;
import com.bigdious.risus.client.render.ModSkullTileEntityRenderer;
import com.bigdious.risus.tileentity.ModSkullTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

public class ISTER extends ItemStackTileEntityRenderer {

	private final ResourceLocation typeId;
	private TileEntity dummy;

	// When this is called from Item register, TEType register has not run yet so we can't pass the actual object
	public ISTER(ResourceLocation typeId) {
		this.typeId = typeId;
	}

	@Override
	public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType camera, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		if (dummy == null) {
			dummy = ForgeRegistries.TILE_ENTITIES.getValue(typeId).create();
		}
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();
			if (block instanceof AbstractModSkullBlock) {
				ModSkullTileEntityRenderer.render(null, 180.0F, ((AbstractModSkullBlock)block).getSkullType(), 0.0F, matrixStack, buffer, combinedLight);
			}
		}
	}
}
