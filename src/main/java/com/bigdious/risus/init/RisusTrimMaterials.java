package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class RisusTrimMaterials {
	public static final ResourceKey<TrimMaterial> GLUTTONY_SCALES = registerKey("gluttony_scales");
	public static final ResourceKey<TrimMaterial> SKIN = registerKey("skin");
	private static ResourceKey<TrimMaterial> registerKey(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
	}
	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, GLUTTONY_SCALES, RisusItems.GLUTTONY_SCALES, Style.EMPTY.withColor(0x660000), 0.2F);
		register(context, SKIN, RisusBlocks.SKIN.asItem().builtInRegistryHolder(), Style.EMPTY.withColor(0xf2a688), 0.2F);
	}
	private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Holder<Item> trimItem, Style color, float itemModelIndex) {
		TrimMaterial material = new TrimMaterial(trimKey.location().getPath(), trimItem, itemModelIndex, Map.of(), Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(color));
		context.register(trimKey, material);
	}
}
