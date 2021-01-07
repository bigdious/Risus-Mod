package com.bigdious.risus.client.render;

public class ModSkullTileEntityRenderer {

/*    public ModSkullTileEntityRenderer(TileEntityRendererDispatcher p_i226006_1_) {
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
     } */
}
