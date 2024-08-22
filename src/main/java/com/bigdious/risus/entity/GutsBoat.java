package com.bigdious.risus.entity;


import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.inventory.AbstractContainerMenu;

import javax.annotation.Nullable;

public class GutsBoat extends RisusBoat implements HasCustomInventoryScreen, ContainerEntity {

	private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
	@Nullable
	private ResourceKey<LootTable> lootTable;
	private long lootTableSeed;

	public GutsBoat(EntityType<? extends RisusBoat> type, Level level) {
		super(type, level);
	}

	public GutsBoat(Level level, double x, double y, double z) {
		this(RisusEntities.GUTS_BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	@Override
	protected float getSinglePassengerXOffset() {
		return 0.17F;
	}

	@Override
	protected int getMaxPassengers() {
		return 1;
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addChestVehicleSaveData(tag, this.registryAccess());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readChestVehicleSaveData(tag, this.registryAccess());
	}

	@Override
	public void destroy(DamageSource source) {
		super.destroy(source);
		this.chestVehicleDestroyed(source, this.level(), this);
	}

	@Override
	public void remove(Entity.RemovalReason reason) {
		if (!this.level().isClientSide() && reason.shouldDestroy()) {
			Containers.dropContents(this.level(), this, this);
		}

		super.remove(reason);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		return this.canAddPassenger(player) && !player.isSecondaryUseActive() ? super.interact(player, hand) : this.interactWithContainerVehicle(player);
	}

	@Override
	public void openCustomInventoryScreen(Player player) {
		player.openMenu(this);
		if (!player.level().isClientSide) {
			this.gameEvent(GameEvent.CONTAINER_OPEN, player);
			PiglinAi.angerNearbyPiglins(player, true);
		}

	}

	@Override
	public Item getDropItem() {
		return switch (this.getRisusBoatType()) {
			case BONDKNOT -> RisusItems.GUTS_BOAT.get();
		};
	}

	@Override
	public void clearContent() {
		this.clearChestVehicleContent();
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.getChestVehicleItem(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		return this.removeChestVehicleItem(slot, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return this.removeChestVehicleItemNoUpdate(slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.setChestVehicleItem(slot, stack);
	}

	@Override
	public SlotAccess getSlot(int index) {
		return this.getChestVehicleSlot(index);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player player) {
		return this.isChestVehicleStillValid(player);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		if (this.lootTable != null && player.isSpectator()) {
			return null;
		} else {
			this.unpackLootTable(inventory.player);
			return ChestMenu.threeRows(id, inventory, this);
		}
	}

	public void unpackLootTable(@Nullable Player player) {
		this.unpackChestVehicleLootTable(player);
	}

	@Nullable
	@Override
	public ResourceKey<LootTable> getLootTable() {
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
	public NonNullList<ItemStack> getItemStacks() {
		return this.itemStacks;
	}

	@Override
	public void clearItemStacks() {
		this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	}
}
