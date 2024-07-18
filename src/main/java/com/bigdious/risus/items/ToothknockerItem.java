package com.bigdious.risus.items;


import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusMobEffects;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ToothknockerItem extends SwordItem {


	public ToothknockerItem(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}


	public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
		return !p_43294_.isCreative();
	}

	public boolean mineBlock(ItemStack itemstack, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity player) {
		if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
			itemstack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
		}

		return true;
	}


	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		List<Enchantment> validEnchants = List.of(Enchantments.UNBREAKING, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK, Enchantments.VANISHING_CURSE);
		return validEnchants.contains(enchantment);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		if (player.isHolding(RisusItems.TOOTHKNOCKER.get()) && player.getOffhandItem().is(RisusItems.TOOTHKNOCKER.get())) {
			player.addEffect(new MobEffectInstance(RisusMobEffects.TOOTHLUSTER, 0, 0));
			player.getOffhandItem().hurtAndBreak(1, player, EquipmentSlot.OFFHAND);
			player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			Level level = player.level();
			level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TURTLE_EGG_BREAK, SoundSource.PLAYERS, 0.5F, 1.0F);
			;
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
		ItemStack itemstack = player.getItemInHand(pHand);
		if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
			return InteractionResultHolder.fail(itemstack);
		} else {

			float f7 = player.getYRot();
			float f = player.getXRot();
			float f1 = -Mth.sin(f7 * (float) (Math.PI / 180.0)) * Mth.cos(f * (float) (Math.PI / 180.0));
			float f2 = -Mth.sin(f * (float) (Math.PI / 180.0));
			float f3 = Mth.cos(f7 * (float) (Math.PI / 180.0)) * Mth.cos(f * (float) (Math.PI / 180.0));
			float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
			float f5 = 3.0F * ((1F) / 4.0F);
			f1 *= f5 / f4;
			f2 *= f5 / f4;
			f3 *= f5 / f4;
			player.push((double) f1, (double) f2, (double) f3);
			float f6 = 1.1999999F;
			player.move(MoverType.PISTON, new Vec3(0.0, 1.1999999F, 0.0));
			player.getCooldowns().addCooldown(this, 30);
			player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			return InteractionResultHolder.consume(itemstack);
		}
	}
	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ToolAction toolAction) {
		return false;
	}
}

