package com.bigdious.risus.components.item;

import com.bigdious.risus.init.RisusTags;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;

import javax.annotation.Nullable;
import java.util.List;

public class ArmorUpgradingContent implements TooltipComponent {
	//based off of vanilla BundleContents
	public static final ArmorUpgradingContent EMPTY = new ArmorUpgradingContent(ItemStack.EMPTY);
	final ItemStack item;
	public static final Codec<ArmorUpgradingContent> CODEC = ItemStack.OPTIONAL_CODEC.xmap(ArmorUpgradingContent::new, ArmorUpgradingContent::item);
	public static final StreamCodec<RegistryFriendlyByteBuf, ArmorUpgradingContent> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.map(ArmorUpgradingContent::new, ArmorUpgradingContent::item);
	ArmorUpgradingContent(ItemStack item) {
		this.item = item;
	}

	public ItemStack getItem() {
		return this.item;
	}

	public boolean isEmpty() {
		return this.item.isEmpty();
	}

	public ItemStack item() {
		return this.item;
	}

	public String toString() {
		return "ArmorUpgradingContents" + this.item;
	}

	@Override
	public int hashCode() {
		return ItemStack.hashItemAndComponents(this.item);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else {
			boolean var10000;
			if (other instanceof ArmorUpgradingContent) {
				ArmorUpgradingContent contents = (ArmorUpgradingContent)other;
				var10000 = ItemStack.matches(this.item, contents.item);
			} else {
				var10000 = false;
			}

			return var10000;
		}
	}

	public static class Mutable {
		private ItemStack item;

		public Mutable(ArmorUpgradingContent contents) {
			this.item = contents.item;
		}

		public ArmorUpgradingContent.Mutable clearItem() {
			this.item.copyAndClear();
			return this;
		}


		public boolean tryInsert(ItemStack stack, TagKey<Item> itemtag) {
			if (this.item.isEmpty() && !stack.isEmpty() && stack.is(itemtag)) {
				this.item = stack.copyAndClear();

				return true;
			} else {
				return false;
			}
		}


		@Nullable
		public ItemStack remove() {
			if (this.item.isEmpty()) {
				return null;
			} else {
				ItemStack itemstack = (this.item.copyAndClear());
				return itemstack;
			}
		}


		public ArmorUpgradingContent toImmutable() {
			return new ArmorUpgradingContent(this.item);
		}
	}
}
