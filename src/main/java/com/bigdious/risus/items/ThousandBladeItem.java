package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.RisusCampfireBlock;
import com.bigdious.risus.entity.projectile.BloodSlash;
import com.bigdious.risus.entity.projectile.ThrownAxe;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;
import java.util.List;

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
				enchantment.is(Enchantments.KNOCKBACK) ||
				enchantment.is(Enchantments.POWER) ||
				enchantment.is(Enchantments.PIERCING) ||
				enchantment.is(Enchantments.MULTISHOT) ||
				enchantment.is(Enchantments.VANISHING_CURSE)
			;
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(itemstack);
		}
	}
	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return
			enchantment.is(Enchantments.SHARPNESS) || enchantment.is(Enchantments.SWEEPING_EDGE) ||
				enchantment.is(Enchantments.BANE_OF_ARTHROPODS) ||
				enchantment.is(Enchantments.LOOTING) ||
				enchantment.is(Enchantments.SMITE) ||
				enchantment.is(Enchantments.MENDING) ||
				enchantment.is(Enchantments.UNBREAKING) ||
				enchantment.is(Enchantments.KNOCKBACK) ||
				enchantment.is(Enchantments.POWER) ||
				enchantment.is(Enchantments.PIERCING) ||
				enchantment.is(Enchantments.MULTISHOT) ||
				enchantment.is(Enchantments.VANISHING_CURSE)
			;
	}
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int useTicks) {
		if (entity instanceof Player player) {
			int i = this.getUseDuration(stack, entity) - useTicks;
			if (i >= 10) {
				if (!level.isClientSide()) {
					player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
					if (stack.getEnchantmentLevel(level.registryAccess().holderOrThrow(Enchantments.MULTISHOT)) > 0) {
						player.hurt(entity.damageSources().source(RisusDamageTypes.VAMPIRISM), 3);
						BloodSlash slash = new BloodSlash(level, player, stack);
						BloodSlash slash1 = new BloodSlash(level, player, stack);
						BloodSlash slash2 = new BloodSlash(level, player, stack);
						slash.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.3F, 1.0F);
						slash1.shootFromRotation(player, player.getXRot(), player.getYRot()+10, 0.0F, 1.3F, 1.0F);
						slash2.shootFromRotation(player, player.getXRot(), player.getYRot()-10, 0.0F, 1.3F, 1.0F);
						level.addFreshEntity(slash);
						level.addFreshEntity(slash1);
						level.addFreshEntity(slash2);
						level.playSound(null , entity, SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.NEUTRAL, 1F, 0.6F);
					} else {
						player.hurt(entity.damageSources().source(RisusDamageTypes.VAMPIRISM), 1);
						BloodSlash slash = new BloodSlash(level, player, stack);
						slash.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.3F, 1.0F);
						level.addFreshEntity(slash);
						level.playSound(null , entity, SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.NEUTRAL, 1F, 0.6F);
					}
				}
				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

}

