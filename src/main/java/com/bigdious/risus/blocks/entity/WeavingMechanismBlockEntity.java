package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WeavingMechanismBlockEntity extends BlockEntity implements WorldlyContainer, ContainerSingleItem.BlockContainerSingleItem{
	protected ItemStack item = ItemStack.EMPTY;
	AABB SUCK_AABB = Block.box(-16.0F, 0.0F, -16.0F, 32.0F, 32.0F, 32.0F).toAabbs().get(0);
	public int xpStored;
	public boolean isWeaving;
	private int weavingCounter;
	private int xpCollectionCooldown;

	public WeavingMechanismBlockEntity(BlockPos pos, BlockState blockState) {
		super(RisusBlockEntities.WEAVING_MECHANISM.get(), pos, blockState);
	}

	public Direction getDirection() {
		return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
	}

	AABB getSuckAabb() {
		return SUCK_AABB;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, WeavingMechanismBlockEntity weaver) {
		int weavingTime=200;
		if (!level.isClientSide() && weaver.xpStored > 12 && weaver.item == ItemStack.EMPTY) {
			weaver.isWeaving = true;
			weaver.setChanged();
		}

		if (weaver.isWeaving) {
			weaver.weavingCounter++;
		}

		if (weaver.xpCollectionCooldown > 1 && weaver.xpStored < 30000) {
			weaver.xpCollectionCooldown--;
		}

		if (weaver.xpCollectionCooldown < 2 && weaver.xpStored < 30000) {
			List<ExperienceOrb> xpOrbs = level.getEntitiesOfClass(ExperienceOrb.class, weaver.getSuckAabb().move(weaver.getBlockPos().getX(), weaver.getBlockPos().getY(), weaver.getBlockPos().getZ()), EntitySelector.ENTITY_STILL_ALIVE);
			for (ExperienceOrb xpOrb : xpOrbs) {
				if (weaver.xpStored < 30000) {
					weaver.xpStored = weaver.xpStored+xpOrb.getValue();
					xpOrb.remove(Entity.RemovalReason.DISCARDED);
				}
			}
			weaver.xpCollectionCooldown=50;
		}

		if (weaver.weavingCounter > weavingTime) {
			weaver.weavingCounter = 1;
			if (weaver.isWeaving) {
				ItemStack core = new ItemStack(RisusItems.MEMORY_CORE.get());
				weaver.item = core;
				weaver.isWeaving = false;
				weaver.weavingCounter = 0;
				weaver.xpStored = weaver.xpStored-12;
				weaver.setChanged();
			}
			weaver.updateBlock();
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		if (this.item != null && !this.item.isEmpty()) {
			tag.put("item", this.item.save(registries));
		}
		tag.putInt("xpStored", this.xpStored);
		tag.putBoolean("isWeaving", this.isWeaving);
		tag.putInt("counter", this.weavingCounter);
		tag.putInt("xpCollectionCooldown", this.xpCollectionCooldown);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		if (tag.contains("item")) {
			this.item = ItemStack.parse(registries, tag.getCompound("item")).orElse(ItemStack.EMPTY);
		} else {
			this.item = ItemStack.EMPTY;
		}
		this.xpStored = tag.getInt("xpStored");
		this.weavingCounter = tag.getInt("counter");
		this.xpCollectionCooldown = tag.getInt("xpCollectionCooldown");
		this.isWeaving = tag.getBoolean("isWeaving");
		super.loadAdditional(tag, registries);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();
		if (this.item != null && !this.item.isEmpty()) {
			Tag reagentTag = this.item.save(registries);
			tag.put("item", reagentTag);
		}
		tag.putInt("counter", this.weavingCounter);
		tag.putInt("xpStored", this.xpStored);
		tag.putInt("xpCollectionCooldown", this.xpCollectionCooldown);
		tag.putBoolean("isWeaving", this.isWeaving);
		super.saveAdditional(tag, registries);
		return tag;
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider pRegistries) {
		super.onDataPacket(net, packet, pRegistries);
		this.handleUpdateTag(packet.getTag() == null ? new CompoundTag() : packet.getTag(), pRegistries);
	}

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public ItemStack splitTheItem(int amount) {
		return BlockContainerSingleItem.super.splitTheItem(amount);
	}

	@Override
	public ItemStack getTheItem() {
		return this.item;
	}

	@Override
	public void setTheItem(ItemStack item) {
		this.item = item;
		this.updateBlock();
	}

	public boolean updateBlock() {
		if (this.weavingCounter == 0) {
			this.weavingCounter = 1;
		}
		if (this.getLevel() != null) {
			BlockState state = this.getLevel().getBlockState(this.getBlockPos());
			this.getLevel().sendBlockUpdated(this.getBlockPos(), state, state, 2);
			this.setChanged();
			return true;
		}
		return false;
	}
	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return false;
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public BlockEntity getContainerBlockEntity() {
		return this;
	}


	@Override
	public int[] getSlotsForFace(Direction direction) {return new int[]{0};}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		boolean ret = direction == Direction.DOWN && !this.item.isEmpty();
		if (ret) this.updateBlock();
		return ret;
	}
}
