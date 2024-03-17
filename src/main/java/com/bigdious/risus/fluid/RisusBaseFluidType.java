package com.bigdious.risus.fluid;

import com.bigdious.risus.Risus;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class RisusBaseFluidType extends FluidType {
	private final ResourceLocation stillTexture;
	private final ResourceLocation flowingTexture;
	private final ResourceLocation overlayTexture;
	private final ResourceLocation renderoverlaytexture;
	private final int tintColor;
	private final Vector3f fogColor;

	public RisusBaseFluidType(final ResourceLocation stillTexture, final ResourceLocation flowingTexture, final ResourceLocation overlayTexture, final ResourceLocation renderoverlaytexture,
						 final int tintColor, final Vector3f fogColor, final Properties properties) {
		super(properties);
		this.stillTexture = stillTexture;
		this.flowingTexture = flowingTexture;
		this.overlayTexture = overlayTexture;
		this.renderoverlaytexture = renderoverlaytexture;
		this.tintColor = tintColor;
		this.fogColor = fogColor;
	}
	public ResourceLocation getStillTexture() {
		return stillTexture;
	}

	public ResourceLocation getFlowingTexture() {
		return flowingTexture;
	}

	public int getTintColor() {
		return tintColor;
	}

	public ResourceLocation getOverlayTexture() {
		return overlayTexture;
	}
	public ResourceLocation getRenderOverlayTexture() {
		return renderoverlaytexture;
	}

	public Vector3f getFogColor() {
		return fogColor;
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			private static final ResourceLocation UNDERWATER_LOCATION = new ResourceLocation(Risus.MODID,"textures/misc/blood_fluid_block.png");
			@Override
			public ResourceLocation getStillTexture() {
				return stillTexture;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return flowingTexture;
			}

			public ResourceLocation getOverlayTexture() {
				return overlayTexture;
			}

			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return UNDERWATER_LOCATION;
			}

			@Override
			public int getTintColor() {
				return tintColor;
			}

			@Override
			public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level,
													int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
				return fogColor;
			}
			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick,
										float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(0f);
				RenderSystem.setShaderFogEnd(6f); // distance when the fog starts
			}
		});
	}
}
