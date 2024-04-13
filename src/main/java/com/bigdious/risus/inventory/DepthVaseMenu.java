package com.bigdious.risus.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DepthVaseMenu extends AbstractContainerMenu {

	private static final net.minecraft.world.Container Container = null;
	private final Container depthVase;


	public DepthVaseMenu(int id, Inventory inventory) {
		this(id, inventory, new SimpleContainer(54) {
		});
	}

	public DepthVaseMenu(int id, Inventory inventory, Container container) {
		super((MenuType<?>) null, id);
		this.depthVase = container;
		checkContainerSize(depthVase, this.depthVase.getContainerSize());
		container.startOpen(inventory.player);
		int totalsize = this.depthVase.getContainerSize();

		//vase inventory and checking for size
		if (totalsize >= 1 && totalsize < 9) {
			for (int vaseY = 0; vaseY < 1; ++vaseY) {
				for (int vaseX = 0; vaseX < totalsize; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, 18 + vaseY * 18 + 18));
				}
			}
		}
		if (totalsize >= 9 && totalsize < 18) {
			for (int vaseY = 0; vaseY < 1; ++vaseY) {
				for (int vaseX = 0; vaseX < 9; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, vaseY * 18 + 18));
				}
			}
			for (int vaseY = 0; vaseY < 1; ++vaseY) {
				for (int vaseX = 0; vaseX < totalsize - 9; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, 36));
				}
			}
		}

		if (totalsize >= 18 && totalsize < 27) {
			for (int vaseY = 0; vaseY < 2; ++vaseY) {
				for (int vaseX = 0; vaseX < 9; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, vaseY * 18 + 18));
				}
			}
			for (int vaseY = 0; vaseY < 1; ++vaseY) {
				for (int vaseX = 0; vaseX < totalsize - 18; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, 54));
				}
			}
		}

		if (totalsize >= 27 && totalsize < 36) {
			for (int vaseY = 0; vaseY < 3; ++vaseY) {
				for (int vaseX = 0; vaseX < 9; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, vaseY * 18 + 18));
				}
			}
			for (int vaseY = 0; vaseY < 1; ++vaseY) {
				for (int vaseX = 0; vaseX < totalsize - 27; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, 72));
				}
			}
		}

		if (totalsize >= 36 && totalsize < 45) {
			for (int vaseY = 0; vaseY < 4; ++vaseY) {
				for (int vaseX = 0; vaseX < 9; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, vaseY * 18 + 18));
				}
			}
			for (int vaseY = 0; vaseY < 1; ++vaseY) {
				for (int vaseX = 0; vaseX < totalsize - 36; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, 90));
				}
			}
		}

		if (totalsize >= 45 && totalsize <= 54) {
			for (int vaseY = 0; vaseY < 5; ++vaseY) {
				for (int vaseX = 0; vaseX < 9; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, vaseY * 18 + 18));
				}
			}
			for (int vaseY = 0; vaseY < 1; ++vaseY) {
				for (int vaseX = 0; vaseX < totalsize - 45; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, 108));
				}
			}
		}
		if (totalsize == 0) {
			for (int vaseY = 0; vaseY < 5; ++vaseY) {
				for (int vaseX = 0; vaseX < 9; ++vaseX) {
					this.addSlot(new Slot(container, vaseX + vaseY * 9, 8 + vaseX * 18, vaseY * 18 + 18));
				}
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
		return this.depthVase.stillValid(player);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.depthVase.stopOpen(player);
	}

}
