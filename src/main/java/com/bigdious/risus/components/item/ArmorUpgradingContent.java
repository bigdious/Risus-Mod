package com.bigdious.risus.components.item;

import com.bigdious.risus.init.RisusTags;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import javax.print.DocFlavor;
import java.util.List;

public class ArmorUpgradingContent implements TooltipComponent {
	//based off of vanilla BundleContents
	public static final ArmorUpgradingContent EMPTY_BOOT = new ArmorUpgradingContent(ItemStack.EMPTY, "boot_upgrade");
	public static final ArmorUpgradingContent EMPTY_LEG = new ArmorUpgradingContent(ItemStack.EMPTY, "leg_upgrade");
	public static final ArmorUpgradingContent EMPTY_CHEST = new ArmorUpgradingContent(ItemStack.EMPTY, "chest_upgrade");
	public static final ArmorUpgradingContent EMPTY_HEAD = new ArmorUpgradingContent(ItemStack.EMPTY, "head_upgrade");
	public static final ArmorUpgradingContent EMPTY_EMPTY = new ArmorUpgradingContent(ItemStack.EMPTY, "?");
	final ItemStack item;
	final String origin;
	public static final Codec<ArmorUpgradingContent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		ItemStack.OPTIONAL_CODEC.optionalFieldOf("item", ItemStack.EMPTY).forGetter(ArmorUpgradingContent::item),
		Codec.STRING.optionalFieldOf("origin", "?").forGetter(ArmorUpgradingContent::origin)
	).apply(instance, ArmorUpgradingContent::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, ArmorUpgradingContent> STREAM_CODEC = StreamCodec.composite(
		ItemStack.STREAM_CODEC, ArmorUpgradingContent::item,
		ByteBufCodecs.STRING_UTF8, ArmorUpgradingContent::origin,
		ArmorUpgradingContent::new);

	public ArmorUpgradingContent(ItemStack item, String origin) {
		this.item = item;
		this.origin = origin;
	}


	public boolean isEmpty() {
		return this.item.isEmpty();
	}

	public ItemStack item() {
		return this.item;
	}

	public String origin() {
		return this.origin;
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
		private String origin;

		public Mutable(ArmorUpgradingContent contents) {
			this.item = contents.item;
			this.origin = contents.origin;
		}

		public ArmorUpgradingContent.Mutable clearItem() {
			this.item.copyAndClear();
			return this;
		}


		public boolean tryInsert(ItemStack stack, TagKey<Item> itemtag) {
			if (this.item.isEmpty() && !stack.isEmpty() && stack.is(itemtag) && stack.getCount()==1) {
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
			return new ArmorUpgradingContent(this.item, this.origin);
		}
	}
}
