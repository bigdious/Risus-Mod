package com.bigdious.risus;

import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.GameRules;
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
				if (RisusConfig.customWeaponAnims) {
					boolean right = arm == HumanoidArm.RIGHT;
					ModelPart modelpart = right ? model.rightArm : model.leftArm;
					modelpart.yRot = (right ? 0.4F : -0.4F) + model.head.yRot;
					modelpart.xRot = -Mth.HALF_PI + model.head.xRot + 1.4F;
				}
			};
	}

	public static Object TOOTHKNOCKER(int idx, Class<?> type) {

		if (idx == 0)
			return true; //two handed. Set to false to only pose the hand holding the item
		return (IArmPoseTransformer) (model, entity, arm) -> {
			if (RisusConfig.customWeaponAnims) {
				boolean flagMain = entity.getItemBySlot(EquipmentSlot.MAINHAND).is(RisusItems.TOOTHKNOCKER);
				boolean flagOff = entity.getItemBySlot(EquipmentSlot.OFFHAND).is(RisusItems.TOOTHKNOCKER);
				boolean right = arm == HumanoidArm.RIGHT;
				if (!flagOff || (flagMain && flagOff)) {
					ModelPart hand = right ? model.rightArm : model.leftArm;
					hand.yRot = (right ? 0.1F : -0.1F) + model.head.yRot;
					hand.zRot = right ? 0.4F : -0.4F;
					hand.xRot = -Mth.HALF_PI + model.head.xRot + 0.5F;
					if (flagMain && flagOff) {
						ModelPart otherHand = right ? model.leftArm : model.rightArm;
						otherHand.yRot = hand.yRot;
						otherHand.xRot = hand.xRot;
						otherHand.zRot = -hand.zRot;
						if (!(model.attackTime <= 0)) {
							float f = 1.0F - model.attackTime;
							f *= f;
							f *= f;
							f = 1.0F - f;
							float f1 = Mth.sin(f * (float) Math.PI);
							float f2 = Mth.sin(model.attackTime * (float) Math.PI) * -(model.head.xRot - 0.7F) * 0.75F;
							otherHand.xRot -= f1 * 1.2F + f2;
							otherHand.yRot = otherHand.yRot + model.body.yRot * -2.0F * (right ? 1F : -1F);
							otherHand.zRot = otherHand.zRot + (right ? Mth.cos(model.attackTime * (float) Math.PI) : Mth.sin(model.attackTime * (float) Math.PI)) * 0.4F * (right ? 1F : -1F);
						}
					}
				}
			}
		};
	}

	public static Object SCYTHE(int idx, Class<?> type) {
		if (idx == 0)
			return true; //two handed. Set to false to only pose the hand holding the item
		return (IArmPoseTransformer) (model, entity, arm) -> {
			if (!entity.isUsingItem() && RisusConfig.customWeaponAnims) {
				ModelPart right = model.rightArm;
				ModelPart left = model.leftArm;
				float armRotationWithoutReturn = Mth.lerp(model.attackTime * 4, 0.0F, 1.0F);
				float armRotation = armRotationWithoutReturn + (armRotationWithoutReturn < 2 ? 0 : 4 - 2*armRotationWithoutReturn);
				model.attackTime = 0.0F;
				if (arm == HumanoidArm.RIGHT) {
					right.xRot = -1.35F + model.head.xRot * 0.5F - armRotation * 0.15F;
					right.zRot = 0.45F;
					right.yRot = -0.40F - armRotation * 0.4F ;

					left.xRot = -0.75F+ model.head.xRot * 0.7F- armRotation * 0.3F;
					left.zRot = -0.45F - model.head.xRot * 0.1F+ armRotation * 0.5F ;
					left.yRot = -0.30F + model.head.xRot * 0.7F - armRotation * 1.15F ;

				} else {
					left.xRot = -1.65F + model.head.xRot * 0.5F + armRotation * 0.05F;
					left.zRot = -0.05F;
					left.yRot = 0.40F + armRotation * 0.4F;

					right.xRot = -0.65F + model.head.xRot * 0.5F - armRotation * 0.3F;
					right.zRot = 0.25F - model.head.xRot * 0.2F - armRotation * 0.5F;
					right.yRot = 0.20F + armRotation * 1.2F;
				}
				right.zRot += -1 * (Mth.cos(entity.tickCount * 0.09F) * 0.05F + 0.05F);
				right.xRot += -1 * Mth.sin(entity.tickCount * 0.067F) * 0.05F;
				left.zRot += 1 * (Mth.cos(entity.tickCount * 0.09F) * 0.05F + 0.05F);
				left.xRot += 1 * Mth.sin(entity.tickCount * 0.067F) * 0.05F;

			}
		};
	}
}
