package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.DisplayNotchBlock;
import com.bigdious.risus.blocks.entity.DisplayNotchBlockEntity;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.init.RisusBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class DisplayNotchRenderer implements BlockEntityRenderer<DisplayNotchBlockEntity> {
	private final ItemRenderer itemRenderer;
	protected static final ResourceLocation BLACK = Risus.prefix("textures/entity/display_notch/black.png");
	protected static final ResourceLocation GRAY = Risus.prefix("textures/entity/display_notch/gray.png");
	protected static final ResourceLocation LIGHT_GRAY = Risus.prefix("textures/entity/display_notch/light_gray.png");
	protected static final ResourceLocation WHITE = Risus.prefix("textures/entity/display_notch/white.png");
	protected static final ResourceLocation BROWN = Risus.prefix("textures/entity/display_notch/brown.png");
	protected static final ResourceLocation RED = Risus.prefix("textures/entity/display_notch/red.png");
	protected static final ResourceLocation ORANGE = Risus.prefix("textures/entity/display_notch/orange.png");
	protected static final ResourceLocation YELLOW = Risus.prefix("textures/entity/display_notch/yellow.png");
	protected static final ResourceLocation LIME = Risus.prefix("textures/entity/display_notch/lime.png");
	protected static final ResourceLocation GREEN = Risus.prefix("textures/entity/display_notch/green.png");
	protected static final ResourceLocation CYAN = Risus.prefix("textures/entity/display_notch/cyan.png");
	protected static final ResourceLocation LIGHT_BLUE = Risus.prefix("textures/entity/display_notch/light_blue.png");
	protected static final ResourceLocation BLUE = Risus.prefix("textures/entity/display_notch/blue.png");
	protected static final ResourceLocation PURPLE = Risus.prefix("textures/entity/display_notch/purple.png");
	protected static final ResourceLocation MAGENTA = Risus.prefix("textures/entity/display_notch/magenta.png");
	protected static final ResourceLocation PINK = Risus.prefix("textures/entity/display_notch/pink.png");

	private final ModelPart notch;
	private final ModelPart flat_notch;

	public DisplayNotchRenderer(BlockEntityRendererProvider.Context pContext) {
		this.itemRenderer = pContext.getItemRenderer();
		ModelPart modelpart = pContext.bakeLayer(RisusModelLayers.DISPLAY_NOTCH);
		this.notch = modelpart.getChild("notch");
		this.flat_notch = modelpart.getChild("flat_notch");
	}

	public static LayerDefinition createBaseLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("notch", CubeListBuilder.create().texOffs(0, 0).addBox(-0.99F, -32.01F, -1.01F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		partdefinition.addOrReplaceChild("flat_notch", CubeListBuilder.create().texOffs(0, 0).addBox(-0.99F, -0.49F, -25.01F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, 1.5708F, 0.0F, 0.0F));


		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	private int getLightVal(DisplayNotchBlockEntity entity, int glowLightVal, int regularLightVal) {
		return entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH.get()) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND.get()) ? glowLightVal : regularLightVal;
	}

	private ResourceLocation getColor(DisplayNotchBlockEntity entity) {
		return switch (entity.getBlockState().getValue(DisplayNotchBlock.COLOR)) {
			case BLACK -> BLACK;
			case GRAY -> GRAY;
			case LIGHT_GRAY -> LIGHT_GRAY;
			case WHITE -> WHITE;
			case BROWN -> BROWN;
			case RED -> RED;
			case ORANGE -> ORANGE;
			case YELLOW -> YELLOW;
			case LIME -> LIME;
			case GREEN -> GREEN;
			case CYAN -> CYAN;
			case LIGHT_BLUE -> LIGHT_BLUE;
			case BLUE -> BLUE;
			case PURPLE -> PURPLE;
			case MAGENTA -> MAGENTA;
			case PINK -> PINK;
		};
	}

	@Override
	public void render(DisplayNotchBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		ItemStack itemstack = entity.getInputItem();
		stack.pushPose();
			switch (entity.getBlockState().getValue(DisplayNotchBlock.FACING)) {
				case UP -> {
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.25, 0.5D);
						} else {
							stack.translate(0.5D, 0.03625, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(90));
						stack.mulPose(Axis.ZP.rotationDegrees(180+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.mulPose(Axis.YP.rotationDegrees(0));
						stack.scale(1f, 1f, 1f);
					}
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH_STAND) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.25, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(180+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.scale(1f, 1f, 1f);
					}
				}
				case DOWN -> {
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.75, 0.5D);
						} else {
							stack.translate(0.5D, 0.96375, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(90));
						stack.mulPose(Axis.ZP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.mulPose(Axis.YP.rotationDegrees(180));
						stack.scale(1f, 1f, 1f);
					}
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH_STAND) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.75, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(180));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(180+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.scale(1f, 1f, 1f);
					}
				}

				case NORTH -> {
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.5, 0.75);
						} else {
							stack.translate(0.5D, 0.5, 0.96375);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.scale(1f, 1f, 1f);
					}
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH_STAND) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.5, 0.75);
						} else {
							stack.translate(0.5D, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(270));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.scale(1f, 1f, 1f);
					}
				}
				case SOUTH -> {
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.5, 0.25);
						} else {
							stack.translate(0.5D, 0.5, 0.03625);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.mulPose(Axis.YP.rotationDegrees(180));
						stack.scale(1f, 1f, 1f);
					}
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH_STAND) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.5D, 0.5, 0.25);
						} else {
							stack.translate(0.5D, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(90));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.scale(1f, 1f, 1f);
					}
				}
				case EAST -> {
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.25D, 0.5, 0.5);
						} else {
							stack.translate(0.03625, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(270));
						stack.scale(1f, 1f, 1f);
					}
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH_STAND) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.25D, 0.5, 0.5);
						} else {
							stack.translate(0.5D, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(90));
						stack.mulPose(Axis.ZP.rotationDegrees(270));
						stack.mulPose(Axis.YP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.scale(1f, 1f, 1f);
					}
				}
				case WEST -> {
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.75D, 0.5, 0.5);
						} else {
							stack.translate(0.96375, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(90));
						stack.scale(1f, 1f, 1f);
					}
					if (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH_STAND) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND)) {
						if (entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE)) {
							stack.translate(0.75D, 0.5, 0.5);
						} else {
							stack.translate(0.5, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(270));
						stack.mulPose(Axis.ZP.rotationDegrees(90));
						stack.mulPose(Axis.YP.rotationDegrees(0+(entity.getBlockState().getValue(DisplayNotchBlock.ROTATION)*22.5F)));
						stack.scale(1f, 1f, 1f);
					}
				}
			}
			if (entity.getInputItem() != null && !entity.getInputItem().isEmpty()) {
				int k = this.getLightVal(entity, 15728880, light);
				assert itemstack != null;
				this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, k, OverlayTexture.NO_OVERLAY, stack, buffers, entity.getLevel(), (int) entity.getBlockPos().asLong());
			}
		VertexConsumer vertexconsumer = buffers.getBuffer(RenderType.entityCutout(getColor(entity)));
		if (!entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE) && (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH_STAND) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH_STAND))) {
			this.notch.render(stack, vertexconsumer, light, overlay);
		}
		if (!entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE) && (entity.getBlockState().is(RisusBlocks.DISPLAY_NOTCH) || entity.getBlockState().is(RisusBlocks.GLOW_DISPLAY_NOTCH))) {
			this.flat_notch.render(stack, vertexconsumer, light, overlay);
		}
		stack.popPose();
	}
}