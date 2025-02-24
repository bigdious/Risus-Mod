package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SeededContainerLoot;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.Nullable;

public class DepthVaseBlockEntity extends BlockEntity implements RandomizableContainer {
	public final int depthToSlotRatio = (int) Math.round((81 - (this.getBlockPos().getY() + 64) / 4.74D));
	public static final int EVENT_POT_WOBBLES = 1;
	public long wobbleStartedAtTick;
	@Nullable
	public DepthWobbleStyle lastWobbleStyle;
	private NonNullList<ItemStack> items = NonNullList.withSize(this.depthToSlotRatio, ItemStack.EMPTY);
	@Nullable
	protected ResourceKey<LootTable> lootTable;
	protected long lootTableSeed;

	public DepthVaseBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.DEPTH_VASE.get(), pos, state);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		if (!this.trySaveLootTable(tag)) {
			ContainerHelper.saveAllItems(tag, this.items, registries);
		}
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(tag)) {
			ContainerHelper.loadAllItems(tag, this.items, registries);
		}
	}

	@Override
	public int getContainerSize() {
		return this.depthToSlotRatio;
	}

	@Override
	public boolean isEmpty() {
		this.unpackLootTable(null);
		return this.items.stream().allMatch(ItemStack::isEmpty);
	}

	@Override
	public ItemStack getItem(int slot) {
		this.unpackLootTable(null);
		return this.items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		this.unpackLootTable(null);
		ItemStack itemstack = ContainerHelper.removeItem(this.items, slot, amount);
		if (!itemstack.isEmpty()) {
			this.setChanged();
		}

		return itemstack;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		this.unpackLootTable(null);
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.unpackLootTable(null);
		this.items.set(slot, stack);
		stack.limitSize(this.getMaxStackSize(stack));
		this.setChanged();
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	public Direction getDirection() {
		return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
	}

	public void wobble(DepthWobbleStyle style) {
		if (this.getLevel() != null && !this.getLevel().isClientSide()) {
			this.getLevel().blockEvent(this.getBlockPos(), this.getBlockState().getBlock(), EVENT_POT_WOBBLES, style.ordinal());
		}
	}

	@Override
	public boolean triggerEvent(int id, int type) {
		//thanks for pointing this out giz
		if (this.getLevel() != null && id == 1 && type >= 0 && type < DepthWobbleStyle.values().length) {
			this.wobbleStartedAtTick = this.getLevel().getGameTime();
			this.lastWobbleStyle = DepthWobbleStyle.values()[type];
			return true;
		} else {
			return super.triggerEvent(id, type);
		}
	}

	@Override
	public @Nullable ResourceKey<LootTable> getLootTable() {
		return this.lootTable;
	}

	@Override
	public void setLootTable(@Nullable ResourceKey<LootTable> lootTable) {
		this.lootTable = lootTable;
	}

	@Override
	public long getLootTableSeed() {
		return this.lootTableSeed;
	}

	@Override
	public void setLootTableSeed(long seed) {
		this.lootTableSeed = seed;
	}

	@Override
	protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
		super.applyImplicitComponents(componentInput);
		SeededContainerLoot seededcontainerloot = componentInput.get(DataComponents.CONTAINER_LOOT);
		if (seededcontainerloot != null) {
			this.lootTable = seededcontainerloot.lootTable();
			this.lootTableSeed = seededcontainerloot.seed();
		}
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder components) {
		super.collectImplicitComponents(components);
		if (this.lootTable != null) {
			components.set(DataComponents.CONTAINER_LOOT, new SeededContainerLoot(this.lootTable, this.lootTableSeed));
		}
	}

	@Override
	public void removeComponentsFromTag(CompoundTag tag) {
		super.removeComponentsFromTag(tag);
		tag.remove("LootTable");
		tag.remove("LootTableSeed");
	}

	public enum DepthWobbleStyle {
		POSITIVE(7),
		NEGATIVE(10);

		public final int duration;

		DepthWobbleStyle(int duration) {
			this.duration = duration;
		}
	}
}
