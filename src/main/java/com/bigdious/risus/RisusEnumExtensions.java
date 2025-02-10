package com.bigdious.risus;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;


import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class RisusEnumExtensions {
	public static final EnumProxy<Rarity> BLOOD = new EnumProxy<>(
		Rarity.class, -1, "risus:blood", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.DARK_RED)
	);
	public static Object EXBURN_HEARTS(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/normal");
			case 1 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/normal_blinking");
			case 2 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/half");
			case 3 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/half_blinking");
			case 4 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore");
			case 5 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore_blinking");
			case 6 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore_half");
			case 7 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "exburn_hearts/hardcore_half_blinking");
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}
	public static Object BLOODCLOGGED_HEARTS(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/normal");
			case 1 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/normal_blinking");
			case 2 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/half");
			case 3 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/half_blinking");
			case 4 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore");
			case 5 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore_blinking");
			case 6 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore_half");
			case 7 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "bloodclogged_hearts/hardcore_half_blinking");
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}
	public static Object DEATH_HEARTS(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/normal");
			case 1 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/normal_blinking");
			case 2 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/half");
			case 3 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/half_blinking");
			case 4 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/hardcore");
			case 5 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/hardcore_blinking");
			case 6 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/hardcore_half");
			case 7 -> ResourceLocation.fromNamespaceAndPath(Risus.MODID, "death_hearts/hardcore_half_blinking");
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}
	public static Object THOUSAND_BLADE(int idx, Class<?> type) {
		if (idx == 0)
			return true; //two handed. Set to false to only pose the hand holding the item
		return (IArmPoseTransformer) (model, entity, arm) -> {
			if (!entity.isUsingItem()) {
				boolean right = arm == HumanoidArm.RIGHT;
				ModelPart modelpart = right ? model.rightArm : model.leftArm;
				ModelPart modelpart1 = right ? model.leftArm : model.rightArm;
				modelpart.yRot = (right ? -0.6F : 0.6F) + model.head.yRot;
				modelpart1.yRot = (right ? 0.6F : -0.6F) + model.head.yRot;
				modelpart.xRot = -Mth.HALF_PI + model.head.xRot + 0.5F;
				modelpart1.xRot = -Mth.HALF_PI + model.head.xRot + 0.5F;
			}
		};
	}
}
