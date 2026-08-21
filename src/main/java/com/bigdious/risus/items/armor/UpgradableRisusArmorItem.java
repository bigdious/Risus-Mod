package com.bigdious.risus.items.armor;

import com.bigdious.risus.components.item.ArmorUpgradingContent;
import com.bigdious.risus.components.tooltip.ArmorUpgradingTooltipComponent;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class UpgradableRisusArmorItem extends RisusArmorItem{
	public UpgradableRisusArmorItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}
	@Override
	public boolean overrideOtherStackedOnMe(
		ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access
	) {
		if (stack.getCount() != 1) return false;
		if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
			ArmorUpgradingContent armorUpgradingContent = stack.get(RisusDataComponents.ARMOR_UPGRADING_CONTENT);
			if (armorUpgradingContent == null) {
				return false;
			} else {
				ArmorUpgradingContent.Mutable armorUpgradingContent$mutable = new ArmorUpgradingContent.Mutable(armorUpgradingContent);
				if (other.isEmpty()) {
					ItemStack itemstack = armorUpgradingContent$mutable.remove();
					if (stack.has(RisusDataComponents.ABILITY_VARIANT)) {
						stack.remove(RisusDataComponents.ABILITY_VARIANT);
					}
					if (itemstack != null) {
						this.playRemoveOneSound(player);
						access.set(itemstack);
					}
				} else {

					ItemStack placeholder = other.copy();
					TagKey<Item> tag = other.is(RisusTags.Items.POCKETABLE) && stack.is(RisusItems.SINNER_ROBES_LEGGINGS) ? RisusTags.Items.POCKETABLE : acceptedTag();
					if (armorUpgradingContent$mutable.tryInsert(other, tag)) {
						if (placeholder.is(RisusTags.Items.POCKETABLE)) {
							stack.set(RisusDataComponents.ABILITY_VARIANT, "pocket");
						} else if (ArmorUpgradingTooltipComponent.ABILITIES.containsKey(placeholder.getItem())) {
							stack.set(RisusDataComponents.ABILITY_VARIANT, ArmorUpgradingTooltipComponent.ABILITIES.get(placeholder.getItem()).getFirst());
						}
						this.playInsertSound(player);
					}
				}

				stack.set(RisusDataComponents.ARMOR_UPGRADING_CONTENT, armorUpgradingContent$mutable.toImmutable());
				return true;
			}
		} else {
			return false;
		}
	}

	public TagKey<Item> acceptedTag() {
		return null;
	}

	@Override
	public void onDestroyed(ItemEntity itemEntity) {
		ArmorUpgradingContent armorUpgradingContent = itemEntity.getItem().get(RisusDataComponents.ARMOR_UPGRADING_CONTENT);
		if (armorUpgradingContent != null) {
			itemEntity.getItem().set(RisusDataComponents.ARMOR_UPGRADING_CONTENT, ArmorUpgradingContent.EMPTY_EMPTY);
			itemEntity.level().addFreshEntity(new ItemEntity(itemEntity.level(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), armorUpgradingContent.item()));
		}
	}

	@Override
	public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
		return !stack.has(DataComponents.HIDE_TOOLTIP) && !stack.has(DataComponents.HIDE_ADDITIONAL_TOOLTIP)
			? Optional.ofNullable(stack.get(RisusDataComponents.ARMOR_UPGRADING_CONTENT)).map(Tooltip::new)
			: Optional.empty();
	}

	private void playRemoveOneSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
	}

	private void playInsertSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
	}

	private void playDropContentsSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
	}

	public record Tooltip(ArmorUpgradingContent content) implements TooltipComponent {}
}
