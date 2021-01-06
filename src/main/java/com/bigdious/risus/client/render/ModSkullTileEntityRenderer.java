package com.bigdious.risus.client.render;

import java.util.Map;

import javax.annotation.Nullable;

import com.bigdious.risus.block.ModSkullBlock;
import com.bigdious.risus.entity.tileentity.ModSkullTileEntity;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class ModSkullTileEntityRenderer extends TileEntityRenderer<ModSkullTileEntity>{

    public ModSkullTileEntityRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(ModSkullTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f = tileEntityIn.getAnimationProgress(partialTicks);
        BlockState blockstate = tileEntityIn.getBlockState();
        boolean flag = blockstate.getBlock() instanceof WallSkullBlock;
        Direction direction = flag ? blockstate.get(WallSkullBlock.FACING) : null;
        float f1 = 22.5F * (float)(flag ? (2 + direction.getHorizontalIndex()) * 4 : blockstate.get(ModSkullBlock.ROTATION));
        render(direction, f1, ((AbstractModSkullBlock)blockstate.getBlock()).getSkullType(), tileEntityIn.getPlayerProfile(), f, matrixStackIn, bufferIn, combinedLightIn);
     }

     public static void render(@Nullable Direction directionIn, float p_228879_1_, ModSkullBlock.ISkullType skullType, @Nullable GameProfile gameProfileIn, float animationProgress, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int combinedLight) {
        GenericHeadModel genericheadmodel = MODELS.get(skullType);
        matrixStackIn.push();
        if (directionIn == null) {
           matrixStackIn.translate(0.5D, 0.0D, 0.5D);
        } else {
           float f = 0.25F;
           matrixStackIn.translate((double)(0.5F - (float)directionIn.getXOffset() * 0.25F), 0.25D, (double)(0.5F - (float)directionIn.getZOffset() * 0.25F));
        }

        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        IVertexBuilder ivertexbuilder = buffer.getBuffer(getRenderType(skullType, gameProfileIn));
        genericheadmodel.func_225603_a_(animationProgress, p_228879_1_, 0.0F);
        genericheadmodel.render(matrixStackIn, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
     }

     private static RenderType getRenderType(ModSkullBlock.SkullType skullType, @Nullable GameProfile gameProfileIn) {
        ResourceLocation resourcelocation = SKINS.get(skullType);
        return RenderType.getEntityCutoutNoCullZOffset(resourcelocation);
     }
}
