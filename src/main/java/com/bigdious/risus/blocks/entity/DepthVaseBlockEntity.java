package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.inventory.DepthVaseMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DepthVaseBlockEntity extends RandomizableContainerBlockEntity {
	public int depthToSlotRatio = (int) Math.round((54-(this.getBlockPos().getY()+64)/7.11));
	private NonNullList<ItemStack> items = NonNullList.withSize(depthToSlotRatio, ItemStack.EMPTY);

	;


	public DepthVaseBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(RisusBlockEntities.DEPTH_VASE.get(), pPos, pBlockState);
	}
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		if (!this.trySaveLootTable(pTag)) {
			ContainerHelper.saveAllItems(pTag, this.items);
		}

	}

	public void load(CompoundTag pTag) {
		super.load(pTag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(pTag)) {
			ContainerHelper.loadAllItems(pTag, this.items);
		}

	}

	/**
	 * Returns the number of slots in the inventory.
	 */
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


	void updateBlockState(BlockState pState, boolean pOpen) {
		this.level.setBlock(this.getBlockPos(), pState.setValue(BarrelBlock.OPEN, Boolean.valueOf(pOpen)), 3);
	}

	void playSound(BlockState pState, SoundEvent pSound) {
		Vec3i vec3i = pState.getValue(BarrelBlock.FACING).getNormal();
		double d0 = (double)this.worldPosition.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
		double d1 = (double)this.worldPosition.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
		double d2 = (double)this.worldPosition.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
		this.level.playSound((Player)null, d0, d1, d2, pSound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
	}

	public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, E e) {
	}
}
