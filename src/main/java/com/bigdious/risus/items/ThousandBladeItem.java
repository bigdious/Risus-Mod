package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.projectile.BloodSlash;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;

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
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.THOUSAND_BLADE_ALLOWED_ENCHANTS);
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
		return enchantment.is(RisusTags.Enchantments.THOUSAND_BLADE_ALLOWED_ENCHANTS);
	}

	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int useTicks) {
		if (entity instanceof Player player) {

			int i = this.getUseDuration(stack, entity) - useTicks;
			if (i < 0) return;
			if (!level.isClientSide()) {
				this.shoot((ServerLevel) level, player, player.getUsedItemHand(), stack);
				player.hurt(entity.damageSources().source(RisusDamageTypes.VAMPIRISM), 1 + 2 * stack.getEnchantmentLevel(level.registryAccess().holderOrThrow(Enchantments.MULTISHOT)));
			}
			level.playSound(null, entity, RisusSoundEvents.THOUSAND_BLADE_SLASH.get(), SoundSource.NEUTRAL, 1F, 0.6F);
			player.awardStat(Stats.ITEM_USED.get(this));

//			if (!level.isClientSide()) {
//					stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
//					//TODO account for higher possible levels of multishot
//					if (stack.getEnchantmentLevel(level.registryAccess().holderOrThrow(Enchantments.MULTISHOT)) > 0) {
//						player.hurt(entity.damageSources().source(RisusDamageTypes.VAMPIRISM), 3);
//
//						BloodSlash slash1 = new BloodSlash(level, player, stack);
//						BloodSlash slash2 = new BloodSlash(level, player, stack);
//						slash.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.3F, 1.0F);
//						slash1.shootFromRotation(player, player.getXRot(), player.getYRot() + 10, 0.0F, 1.3F, 1.0F);
//						slash2.shootFromRotation(player, player.getXRot(), player.getYRot() - 10, 0.0F, 1.3F, 1.0F);
//						level.addFreshEntity(slash);
//						level.addFreshEntity(slash1);
//						level.addFreshEntity(slash2);
//						level.playSound(null, entity, RisusSoundEvents.THOUSAND_BLADE_SLASH.get(), SoundSource.NEUTRAL, 1F, 0.6F);
//					} else {
//						player.hurt(entity.damageSources().source(RisusDamageTypes.VAMPIRISM), 1);
//						BloodSlash slash = new BloodSlash(level, player, stack);
//						slash.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.3F, 1.0F);
//						level.addFreshEntity(slash);
//						level.playSound(null, entity, RisusSoundEvents.THOUSAND_BLADE_SLASH.get(), SoundSource.NEUTRAL, 1F, 0.6F);
//					}
//				}
//				player.awardStat(Stats.ITEM_USED.get(this));
//			}
		}
	}


	protected void shoot(ServerLevel level, LivingEntity living, InteractionHand hand, ItemStack stack) {

		float arrowSize = 1+stack.getEnchantmentLevel(level.registryAccess().holderOrThrow(Enchantments.MULTISHOT))*2;
		float f = EnchantmentHelper.processProjectileSpread(level, stack, living, 0.0F);
		float f1 = arrowSize == 1 ? 0.0F : 2.0F * f / arrowSize-1;
		float f2 = ((arrowSize-1) % 2) * f1 / 2.0F;
		float f3 = 1.0F;

		for (int i = 0; i < arrowSize; ++i) {
			float f4 = f2 + f3 * (float) ((i + 1) / 2) * f1;
			f3 = -f3;
			BloodSlash slash = new BloodSlash(level, living, stack);
			slash.shootFromRotation(living, living.getXRot(), living.getYRot()+f4, 0.0f, 1.3F, 1.0F);
			level.addFreshEntity(slash);
			stack.hurtAndBreak(1, living, LivingEntity.getSlotForHand(hand));
			if (stack.isEmpty()) {
				break;
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

	public static class ItemExtensions implements IClientItemExtensions {

		public static final ItemExtensions INSTANCE = new ItemExtensions();

		@Override
		public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
			return HumanoidModel.ArmPose.valueOf("RISUS_THOUSAND_BLADE");
		}
	}

}

