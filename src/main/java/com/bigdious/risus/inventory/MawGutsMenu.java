package com.bigdious.risus.inventory;

import com.bigdious.risus.init.RisusMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MawGutsMenu extends AbstractContainerMenu {
	private final Container mawGuts;

	public MawGutsMenu(int id, Inventory inventory) {
		this(id, inventory, new SimpleContainer(54));
	}

	public MawGutsMenu(int id, Inventory inventory, Container container) {
		super(RisusMenuTypes.MAW_GUTS.get(), id);
		this.mawGuts = container;
		checkContainerSize(mawGuts, 54);
		container.startOpen(inventory.player);

		//guts inventory
		for (int gutsY = 0; gutsY < 6; ++gutsY) {
			for (int gutsX = 0; gutsX < 9; ++gutsX) {
				this.addSlot(new Slot(container, gutsX + gutsY * 9, 8 + gutsX * 18, 18 + gutsY * 18));
			}
		}

		//player inventory
		for (int invY = 0; invY < 3; ++invY) {
			for (int invX = 0; invX < 9; ++invX) {
				this.addSlot(new Slot(inventory, invX + invY * 9 + 9, 8 + invX * 18, 103 + invY * 18 + 36));
			}
		}

		//player hotbar
		for (int hotbar = 0; hotbar < 9; ++hotbar) {
			this.addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 197));
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotNum) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotNum);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (slotNum < 54) {
				if (!this.moveItemStackTo(itemstack1, 54, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, 54, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.mawGuts.stillValid(player);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.mawGuts.stopOpen(player);
	}
}
