package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.inventory.MawGutsMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MawGutsBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> pItems) {
		this.items = pItems;
	}
	private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);
	private int cooldownTime = -1;

	public MawGutsBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.MAW_GUTS.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, MawGutsBlockEntity te) {
		if (!level.hasNeighborSignal(pos)) {
			if (te.cooldownTime-- <= 0) {
				te.cooldownTime = 0;
				if (suckInItems(level, te)) {
					te.cooldownTime = 10;
				}
			}
		}
	}

	public static boolean suckInItems(Level level, MawGutsBlockEntity te) {
		for (ItemEntity itementity : getItemsAtAndAbove(level, te)) {
			if (addItem(te, itementity)) {
				return true;
			}
		}
		return false;
	}

	public static List<ItemEntity> getItemsAtAndAbove(Level level, MawGutsBlockEntity te) {
		VoxelShape DETECTION_AREA = Block.box(-24.0D, 0.0D, -24.0D, 24.0D, 48.0D, 24.0D);
		return DETECTION_AREA.toAabbs().stream().flatMap((aabb) -> level.getEntitiesOfClass(ItemEntity.class, aabb.move(te.getBlockPos().getX() + 0.5D, te.getBlockPos().getY() + 1.0D, te.getBlockPos().getZ() + 0.5D), EntitySelector.ENTITY_STILL_ALIVE).stream()).collect(Collectors.toList());
	}

	public static boolean addItem(Container container, ItemEntity entity) {
		boolean flag = false;
		ItemStack itemstack = entity.getItem().copy();
		ItemStack itemstack1 = addItem(container, itemstack);
		if (itemstack1.isEmpty()) {
			flag = true;
			entity.discard();
		} else {
			entity.setItem(itemstack1);
		}
		return flag;
	}

	public static ItemStack addItem(Container container, ItemStack stack) {
		int i = container.getContainerSize();

		for (int j = 0; j < i && !stack.isEmpty(); ++j) {
			stack = tryMoveInItem(container, stack, j);
		}

		return stack;
	}

	private static ItemStack tryMoveInItem(Container container, ItemStack stack, int slot) {
		ItemStack itemstack = container.getItem(slot);
		if (canPlaceItemInContainer(container, stack, slot)) {
			if (itemstack.isEmpty()) {
				container.setItem(slot, stack);
				stack = ItemStack.EMPTY;
			} else if (canMergeItems(itemstack, stack)) {
				int i = stack.getMaxStackSize() - itemstack.getCount();
				int j = Math.min(stack.getCount(), i);
				stack.shrink(j);
				itemstack.grow(j);
			}
		}
		return stack;
	}

	private static boolean canPlaceItemInContainer(Container container, ItemStack stack, int slot) {
		if (!container.canPlaceItem(slot, stack)) {
			return false;
		} else {
			return !(container instanceof WorldlyContainer worldly) || worldly.canPlaceItemThroughFace(slot, stack, null);
		}
	}

	private static boolean canMergeItems(ItemStack oldStack, ItemStack newStack) {
		return ItemStack.isSameItem(oldStack, newStack);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
		super.loadAdditional(tag, pRegistries);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.items, pRegistries);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
		super.saveAdditional(tag, pRegistries);
		ContainerHelper.saveAllItems(tag, this.items, pRegistries);
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.risus.maw_guts");
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new MawGutsMenu(id, inventory, this);
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	public boolean isEmpty() {
		return this.items.stream().allMatch(ItemStack::isEmpty);
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		return ContainerHelper.removeItem(this.items, slot, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.items.set(slot, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.getLevel().getBlockEntity(this.getBlockPos()) != this) {
			return false;
		} else {
			return !(player.distanceToSqr((double) this.getBlockPos().getX() + 0.5D, (double) this.getBlockPos().getY() + 0.5D, (double) this.getBlockPos().getZ() + 0.5D) > 64.0D);
		}
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return IntStream.range(0, 54).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return stack.getItem().canFitInsideContainerItems();
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return true;
	}
}
