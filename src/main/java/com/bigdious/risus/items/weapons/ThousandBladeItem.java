package com.bigdious.risus.items.weapons;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.projectile.BloodSlash;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

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
			if (i >= 20) {
				if (!level.isClientSide()) {
					int multishot = stack.getEnchantmentLevel(level.registryAccess().holderOrThrow(Enchantments.MULTISHOT));
					this.shoot((ServerLevel) level, player, player.getUsedItemHand(), stack, multishot);
					if (!player.isCreative()) player.hurt(entity.damageSources().source(RisusDamageTypes.VAMPIRISM), 1 + 2 * multishot);
				}
				level.playSound(null, entity, RisusSoundEvents.THOUSAND_BLADE_SLASH.get(), SoundSource.NEUTRAL, 1F, 0.6F);
				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	protected void shoot(ServerLevel level, LivingEntity living, InteractionHand hand, ItemStack stack, int multishot) {
		float arrowSize = 1 + multishot * 2;
		float f = EnchantmentHelper.processProjectileSpread(level, stack, living, 0.0F);
		float f1 = arrowSize == 1 ? 0.0F : 2.0F * f / arrowSize - 1;
		float f2 = ((arrowSize - 1) % 2) * f1 / 2.0F;
		float f3 = 1.0F;

		for (int i = 0; i < arrowSize; ++i) {
			float f4 = f2 + f3 * (float) ((i + 1) / 2) * f1;
			f3 = -f3;
			BloodSlash slash = new BloodSlash(level, living, stack);
			this.shootProjectile(living, slash, 1.3F, 1.0F, f4);
			level.addFreshEntity(slash);
			stack.hurtAndBreak(1, living, LivingEntity.getSlotForHand(hand));
			if (stack.isEmpty()) {
				break;
			}
		}
	}

	protected void shootProjectile(LivingEntity shooter, Projectile projectile, float velocity, float inaccuracy, float angle) {
		Vector3f vector3f;
		Vec3 vec3 = shooter.getUpVector(1.0F);
		Quaternionf quaternionf = (new Quaternionf()).setAngleAxis(angle * Mth.DEG_TO_RAD, vec3.x, vec3.y, vec3.z);
		Vec3 vec31 = shooter.getViewVector(1.0F);
		vector3f = vec31.toVector3f().rotate(quaternionf);

		projectile.shoot(vector3f.x(), vector3f.y(), vector3f.z(), velocity, inaccuracy);
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

