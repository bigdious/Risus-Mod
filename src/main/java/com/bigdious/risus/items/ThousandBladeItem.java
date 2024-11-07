package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.RisusCampfireBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ThousandBladeItem extends SwordItem {

	public ThousandBladeItem(Tier material, Properties properties) {
		super(material, properties);
	}

	public static ItemAttributeModifiers createThousandBladeAttributes(Tier tier, int damage, float speed) {
		return SwordItem.createAttributes(tier, damage, speed)
			.withModifierAdded(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(Risus.prefix("knockback_res_modifier"), 0.1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Risus.prefix("range_modifier"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(Risus.prefix("speed_modifier"), -0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND);
	}
	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return material.is(RisusItems.GLUTTONY_SCALES);
	}
	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return
			enchantment.is(Enchantments.SHARPNESS) ||
				enchantment.is(Enchantments.BANE_OF_ARTHROPODS) ||
				enchantment.is(Enchantments.SWEEPING_EDGE) ||
				enchantment.is(Enchantments.LOOTING) ||
				enchantment.is(Enchantments.SMITE) ||
				enchantment.is(Enchantments.MENDING) ||
				enchantment.is(Enchantments.UNBREAKING) ||
				enchantment.is(Enchantments.FIRE_ASPECT) ||
				enchantment.is(Enchantments.KNOCKBACK) ||
				enchantment.is(Enchantments.VANISHING_CURSE)
			;
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return
			enchantment.is(Enchantments.SHARPNESS) || enchantment.is(Enchantments.SWEEPING_EDGE) ||
			enchantment.is(Enchantments.BANE_OF_ARTHROPODS)||
			enchantment.is(Enchantments.LOOTING)||
			enchantment.is(Enchantments.SMITE)||
			enchantment.is(Enchantments.MENDING)||
			enchantment.is(Enchantments.UNBREAKING)||
			enchantment.is(Enchantments.FIRE_ASPECT)||
			enchantment.is(Enchantments.KNOCKBACK)||
			enchantment.is(Enchantments.VANISHING_CURSE)
			;
	}

}

