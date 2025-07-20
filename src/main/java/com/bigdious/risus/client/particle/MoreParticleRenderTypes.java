package com.bigdious.risus.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public interface MoreParticleRenderTypes extends ParticleRenderType {
	ParticleRenderType MOBEFFECT_SHEET = new ParticleRenderType() {
		public BufferBuilder begin(Tesselator tesselator, TextureManager manager) {
			RenderSystem.disableBlend();
			RenderSystem.depthMask(true);
			RenderSystem.setShaderTexture(0, ResourceLocation.withDefaultNamespace("textures/atlas/mob_effects.png"));
			return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
		}

		public String toString() {
			return "MOBEFFECT_SHEET";
		}

		public boolean isTranslucent() {
			return false;
		}
	};
}
