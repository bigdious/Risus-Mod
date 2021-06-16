package com.bigdious.risus.client.render;

import java.util.Map;

import javax.annotation.Nullable;

import com.bigdious.risus.Risus;
import com.bigdious.risus.block.AbstractModSkullBlock;
import com.bigdious.risus.block.ModSkullBlock;
import com.bigdious.risus.block.ModSkullBlock.SkullType;
import com.bigdious.risus.block.ModWallSkullBlock;
import com.bigdious.risus.client.model.BloodWyrmHeadModel;
import com.bigdious.risus.tileentity.ModSkullTileEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

public class ModSkullTileEntityRenderer extends TileEntityRenderer<ModSkullTileEntity> {

    private static final Map<ModSkullBlock.SkullType, GenericHeadModel> MODELS = Util.make(Maps.newHashMap(), (p_209262_0_) -> {
        BloodWyrmHeadModel dragonheadmodel = new BloodWyrmHeadModel();
        p_209262_0_.put(ModSkullBlock.Types.BLOODWYRM, dragonheadmodel);
     });
     private static final Map<ModSkullBlock.SkullType, ResourceLocation> SKINS = Util.make(Maps.newHashMap(), (p_209263_0_) -> {
        p_209263_0_.put(ModSkullBlock.Types.BLOODWYRM, Risus.risusRL("textures/entity/bloodwyrm_head.png"));
     });
    
    public ModSkullTileEntityRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(ModSkullTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f = tileEntityIn.getAnimationProgress(partialTicks);
        BlockState blockstate = tileEntityIn.getBlockState();
        boolean flag = blockstate.getBlock() instanceof ModWallSkullBlock;
        Direction direction = flag ? blockstate.get(ModWallSkullBlock.FACING) : null;
        float f1 = 22.5F * (float)(flag ? (2 + direction.getHorizontalIndex()) * 4 : blockstate.get(ModSkullBlock.ROTATION));
        render(direction, f1, ((AbstractModSkullBlock)blockstate.getBlock()).getSkullType(), f, matrixStackIn, bufferIn, combinedLightIn);
     }

     public static void render(@Nullable Direction directionIn, float p_228879_1_, SkullType skullType, float animationProgress, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int combinedLight) {
        GenericHeadModel genericheadmodel = MODELS.get(skullType);
        matrixStackIn.push();
        if (directionIn == null) {
           matrixStackIn.translate(0.5D, 0.0D, 0.5D);
        } else {
            matrixStackIn.translate((double)(0.5F - (float)directionIn.getXOffset() * 0.15F), 0.25D, (double)(0.5F - (float)directionIn.getZOffset() * 0.15F));
        }

        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        IVertexBuilder ivertexbuilder = buffer.getBuffer(getRenderType(skullType));
        genericheadmodel.func_225603_a_(animationProgress, p_228879_1_, 0.0F);
        genericheadmodel.render(matrixStackIn, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
     }

     private static RenderType getRenderType(ModSkullBlock.SkullType skullType) {
        ResourceLocation resourcelocation = SKINS.get(skullType);
        return RenderType.getEntityCutoutNoCullZOffset(resourcelocation);
     } 
}
