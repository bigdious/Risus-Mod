package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class RisusArmorMaterials {
	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Risus.MODID);
	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SKIN = ARMOR_MATERIALS.register("skin", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.BOOTS, 1);
			map.put(ArmorItem.Type.LEGGINGS, 1);
			map.put(ArmorItem.Type.CHESTPLATE, 1);
			map.put(ArmorItem.Type.HELMET, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_SKIN, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "skin"))), 0.0F, 0.0F)
	);
	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BLOOD_FEATHER = ARMOR_MATERIALS.register("blood_feather", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.BOOTS, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_THREADS, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "blood_feather"))), 0.0F, 0.0F)
	);
	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> REGEN_ROSE = ARMOR_MATERIALS.register("regen_rose", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.HELMET, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_ROSE, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "rose_crown"))), 0.0F, 0.0F)
	);
	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BONE = ARMOR_MATERIALS.register("bone", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.HELMET, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_BONE, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "crown_of_bones"))), 0.0F, 0.0F)
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SINNER_ROBE_HELMET = ARMOR_MATERIALS.register("sinner_robe_helmet", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.HELMET, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_BONE, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/helmet/base"), "_dyed", true), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/helmet/cosmetic"), "", false), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/helmet/upgrade"), "", false)), 0.0F, 0.0F)
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SINNER_ROBE_CHESTPLATE = ARMOR_MATERIALS.register("sinner_robe_chestplate", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.CHESTPLATE, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_BONE, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/chestplate/base"), "_dyed", true), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/chestplate/cosmetic"), "", false), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/chestplate/upgrade"), "", false)), 0.0F, 0.0F)
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SINNER_ROBE_LEGGINGS = ARMOR_MATERIALS.register("sinner_robe_leggings", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.LEGGINGS, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_BONE, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/leggings/base"), "_dyed", true), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/leggings/cosmetic"), "", false), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/leggings/upgrade"), "", false)), 0.0F, 0.0F)
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SINNER_ROBE_BOOTS = ARMOR_MATERIALS.register("sinner_robe_boots", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.BOOTS, 1);
		}), 10, RisusSoundEvents.ARMOR_EQUIP_BONE, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/boots/base"), "_dyed", true), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/boots/cosmetic"), "", false), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "robe/boots/upgrade"), "", false)), 0.0F, 0.0F)
	);


}
