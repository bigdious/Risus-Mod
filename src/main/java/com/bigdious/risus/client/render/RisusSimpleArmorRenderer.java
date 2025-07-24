package com.bigdious.risus.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RisusSimpleArmorRenderer implements IClientItemExtensions {
	//copied from Twilight Forest's TFSimpleArmorRenderer

	public static final List<RisusSimpleArmorRenderer> INSTANCES = new ArrayList<>();
	protected final Lazy<HumanoidModel<?>> INNER_ARMOR_MODEL;
	protected final Lazy<HumanoidModel<?>> OUTER_ARMOR_MODEL;

	public RisusSimpleArmorRenderer(Function<ModelPart, HumanoidArmorModel<?>> createModelInstance, ModelLayerLocation innerLayerLocation, ModelLayerLocation outerLayerLocation) {
		INSTANCES.add(this);
		this.INNER_ARMOR_MODEL = Lazy.of(() -> {
			ModelPart baked = Minecraft.getInstance().getEntityModels().bakeLayer(innerLayerLocation);
			return createModelInstance.apply(baked);
		});
		this.OUTER_ARMOR_MODEL = Lazy.of(() -> {
			ModelPart baked = Minecraft.getInstance().getEntityModels().bakeLayer(outerLayerLocation);
			return createModelInstance.apply(baked);
		});
	}

	// can be overridden
	public void resetModelCache() {
		INNER_ARMOR_MODEL.invalidate();
		OUTER_ARMOR_MODEL.invalidate();
	}

	public static void resetAllModelCache() {
		INSTANCES.forEach(RisusSimpleArmorRenderer::resetModelCache);
	}

	@Override
	public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
		return slot == EquipmentSlot.LEGS ? INNER_ARMOR_MODEL.get() : OUTER_ARMOR_MODEL.get();
	}

	public static final class ResourceReloadListener implements ResourceManagerReloadListener {
		@Override
		public void onResourceManagerReload(ResourceManager resourceManager) {
			RisusSimpleArmorRenderer.resetAllModelCache();
		}
	}
}
