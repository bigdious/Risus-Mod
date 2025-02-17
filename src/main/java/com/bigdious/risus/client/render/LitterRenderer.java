package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.BabySpiderModel;
import com.bigdious.risus.client.model.entity.LitterModel;
import com.bigdious.risus.entity.BabySpider;
import com.bigdious.risus.entity.Litter;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.shedaniel.cloth.clothconfig.shadowed.org.yaml.snakeyaml.tokens.BlockMappingStartToken;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LitterRenderer extends MobRenderer<Litter, LitterModel<Litter>> {
	private static final ResourceLocation DEFAULT_TEXTURE = Risus.prefix("textures/entity/litter.png");

	public LitterRenderer(EntityRendererProvider.Context context) {
		super(context, new LitterModel<>(context.bakeLayer(RisusModelLayers.LITTER)), 0.0F);
	}

	@Override
	public void render(Litter entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		BlockState renderBlockState = entity.getLightBlockState();
		Block block = renderBlockState.getBlock();

		ResourceLocation blockTexture = ResourceLocation.fromNamespaceAndPath(
			BuiltInRegistries.BLOCK.getKey(block).getNamespace(),
			"textures/block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + ".png"
		);

		this.model.BlockBody.render(poseStack, buffer.getBuffer(RenderType.entityCutout(blockTexture)), packedLight, getOverlayCoords(entity, 0));

		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutout(DEFAULT_TEXTURE));
		this.model.root().render(poseStack, vertexconsumer, packedLight, getOverlayCoords(entity, 0));
	}

	@Override
	protected float getFlipDegrees(Litter litter) {
		return 0.0F;
	}


	@Override
	public ResourceLocation getTextureLocation(Litter litter) {
		return DEFAULT_TEXTURE;
	}

}