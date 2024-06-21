package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.inventory.DepthVaseMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class DepthVaseBlockEntity extends RandomizableContainerBlockEntity {
	public int depthToSlotRatio = (int) Math.round((81 - (this.getBlockPos().getY() + 64) / 4.74));
	public static final int EVENT_POT_WOBBLES = 1;
	public long wobbleStartedAtTick;
	@Nullable
	public DepthVaseBlockEntity.WobbleStyle lastWobbleStyle;
	@Nullable
	private ItemStack item = ItemStack.EMPTY;
	private NonNullList<ItemStack> items = NonNullList.withSize(depthToSlotRatio, ItemStack.EMPTY);

	public DepthVaseBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(RisusBlockEntities.DEPTH_VASE.get(), pPos, pBlockState);
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		if (!this.trySaveLootTable(pTag)) {
			ContainerHelper.saveAllItems(pTag, this.items);
		}

	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(pTag)) {
			ContainerHelper.loadAllItems(pTag, this.items);
		}

	}
	//the below needs to stay or else depthvase allows for overflow when interacted with, deleting items

	public ItemStack getTheItem() {
		this.unpackLootTable(null);
		return this.item;
	}
	public int getContainerSize() {
		return depthToSlotRatio;
	}

	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	protected void setItems(NonNullList<ItemStack> pItems) {
		this.items = pItems;
	}

	protected Component getDefaultName() {
		return Component.translatable("container.risus.depth_vase");
	}

	protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
		return new DepthVaseMenu(pId, pPlayer);
	}
	public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, E e) {
	}
	public void setItem(int pIndex, ItemStack pStack) {
		this.unpackLootTable((Player)null);
		this.getItems().set(pIndex, pStack);
		if (pStack.getCount() > this.getMaxStackSize()) {
			pStack.setCount(this.getMaxStackSize());
		}

	}
	public boolean canMergeItems(ItemStack oldStack, ItemStack newStack) {
		return ItemStack.isSameItemSameTags(oldStack, newStack);
	}
	public void setInputItem(int slot, ItemStack item) {
		this.setItem(slot, item);
		this.setChanged();

	}
	@org.jetbrains.annotations.Nullable
	public ItemStack getInputItem(int slot) {
		return this.getItem(slot);
	}
	public static enum WobbleStyle {
		POSITIVE(7),
		NEGATIVE(10);

		public final int duration;

		private WobbleStyle(int pDuration) {
			this.duration = pDuration;
		}
	}
	public void wobble(DepthVaseBlockEntity.WobbleStyle pStyle) {
		if (this.level != null && !this.level.isClientSide()) {
			this.level.blockEvent(this.getBlockPos(), this.getBlockState().getBlock(), 1, pStyle.ordinal());
		}
	}
	@Override
	public boolean triggerEvent(int pId, int pType) {
		if (this.level != null && pId == 1 && pType >= 0 && pType < DepthVaseBlockEntity.WobbleStyle.values().length) {
			this.wobbleStartedAtTick = this.level.getGameTime();
			this.lastWobbleStyle = DepthVaseBlockEntity.WobbleStyle.values()[pType];
			return true;
		} else {
			return super.triggerEvent(pId, pType);
		}
	}

}
