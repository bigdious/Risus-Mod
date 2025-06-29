package com.bigdious.risus.items.weapons;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbility;

public class ToothknockerItem extends SwordItem {

	public ToothknockerItem(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}
	public static ItemAttributeModifiers createKnuckleAttributes(Tier tier, int damage, float speed) {
		return SwordItem.createAttributes(tier, damage, speed)
			.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Risus.prefix("range_modifier"), -0.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.withModifierAdded(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(Risus.prefix("fall_distance_modifier"), +4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND);
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
		if (state.getDestroySpeed(level, pos) != 0.0F) {
			itemstack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
		}

		return true;
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.TOOTHKNOCKER_ALLOWED_ENCHANTS);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.TOOTHKNOCKER_ALLOWED_ENCHANTS);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		if (player.getItemInHand(InteractionHand.MAIN_HAND).is(RisusItems.TOOTHKNOCKER.get()) && player.getItemInHand(InteractionHand.OFF_HAND).is(RisusItems.TOOTHKNOCKER.get())) {
			player.addEffect(new MobEffectInstance(RisusMobEffects.TOOTHLUSTER, 0, 0, false, false, false));
			player.getOffhandItem().hurtAndBreak(1, player, EquipmentSlot.OFFHAND);
			player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			Level level = player.level();
			level.playSound(null, player.getX(), player.getY(), player.getZ(), RisusSoundEvents.TOOTHKNOCKER_CRACK.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
		ItemStack itemstack = player.getItemInHand(pHand);
		if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1 || player.isFallFlying() || player.isInWater()) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			float f7 = player.getYRot();
			float f = player.getXRot();
			float f1 = -Mth.sin(f7 * Mth.DEG_TO_RAD) * Mth.cos(f * Mth.DEG_TO_RAD);
			float f2 = -Mth.sin(f * Mth.DEG_TO_RAD);
			float f3 = Mth.cos(f7 * Mth.DEG_TO_RAD) * Mth.cos(f * Mth.DEG_TO_RAD);
			float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
			float f5 = 3.0F * ((1F) / 4.0F);
			f1 *= f5 / f4;
			f2 *= f5 / f4;
			f3 *= f5 / f4;
			player.push(f1, f2, f3);
			player.move(MoverType.PISTON, new Vec3(0.0, 1.1999999F, 0.0));
			for (int i=0;i <6; i++) {
				pLevel.addParticle(ParticleTypes.POOF, player.getX(), player.getRandomY(), player.getZ(), 0, 0, 0);
			}
			itemstack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			player.getCooldowns().addCooldown(this, 30);
			pLevel.playSound(player, player.getOnPos().above(), RisusSoundEvents.TOOTHKNOCKER_DASH.get(), SoundSource.PLAYERS);
			return InteractionResultHolder.consume(itemstack);
		}
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
		return false;
	}
}

