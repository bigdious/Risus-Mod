package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.DisplayNotchBlock;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class DisplayNotchBlockEntity extends BlockEntity implements WorldlyContainer, ContainerSingleItem.BlockContainerSingleItem {
	protected ItemStack item = ItemStack.EMPTY;
	public boolean stand;
	public boolean shouldRotate;

	public DisplayNotchBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.DISPLAY_NOTCH.get(), pos, state);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		if (this.item != null && !this.item.isEmpty()) {
			tag.put("item", this.item.save(registries));
		}
		tag.putBoolean("stand", this.stand);
		tag.putBoolean("shouldRotate", this.shouldRotate);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		//the below needs the itemtag check, otherwise throws log errors
		if (tag.contains("item")) {
			this.item = ItemStack.parse(registries, tag.getCompound("item")).orElse(ItemStack.EMPTY);
		} else {
			this.item = ItemStack.EMPTY;
		}
		this.stand = tag.getBoolean("stand");
		this.shouldRotate = tag.getBoolean("shouldRotate");
		super.loadAdditional(tag, registries);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return this.saveWithoutMetadata(registries);
	}

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public ItemStack getTheItem() {
		return this.item;
	}

	@Override
	public void setTheItem(ItemStack item) {
		this.item = item;
		this.setChanged();
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public BlockEntity getContainerBlockEntity() {
		return this;
	}

	public boolean handleBEInteractions(ItemStack stack, Level level, BlockPos pos, BlockState state) {
		if (!state.getValue(DisplayNotchBlock.LOCKED)) {
			if (stack.is(Tags.Items.DYES)) {
				DyeColor color = DyeColor.getColor(stack);
				if (color != null) {
					var oldBe = level.getBlockEntity(pos);
					level.setBlockAndUpdate(pos, DisplayNotchBlock.NOTCH_BY_DYE.get(color).get().withPropertiesOf(state));
					level.setBlockEntity(oldBe);
					this.setChanged();
					level.sendBlockUpdated(pos, state, state, 2);
					return true;
				}
			} else if (stack.is(ItemTags.AXES)) {
				this.stand = !this.stand;
				this.setChanged();
				level.sendBlockUpdated(pos, state, state, 2);
				return true;
			} else if (stack.is(Items.REDSTONE_TORCH) && RisusConfig.spinningSource == RisusConfig.SpinningSource.TORCH_ITEM) {
				this.shouldRotate = !this.shouldRotate;
				this.setChanged();
				level.sendBlockUpdated(pos, state, state, 2);
				return true;
			} else if (stack.is(Tags.Items.GLASS_BLOCKS)) {
				var oldBe = level.getBlockEntity(pos);
				level.setBlockAndUpdate(pos, state.getBlock().defaultBlockState().is(RisusBlocks.INVISIBLE_DISPLAY_NOTCH) ? RisusBlocks.DISPLAY_NOTCH.get().withPropertiesOf(state) : RisusBlocks.INVISIBLE_DISPLAY_NOTCH.get().withPropertiesOf(state));
				level.setBlockEntity(oldBe);
				this.setChanged();
				level.sendBlockUpdated(pos, state, state, 2);
				return true;
			}
		}
		return false;
	}

	public boolean updateBlock() {
		if (this.getLevel() != null) {
			BlockState state = this.getLevel().getBlockState(this.getBlockPos());
			this.getLevel().sendBlockUpdated(this.getBlockPos(), state, state, 2);
			this.setChanged();
			return true;
		}
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {return new int[]{0};}


	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		boolean ret =  this.item.isEmpty();
		if (ret) this.updateBlock();
		return ret;
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		boolean ret = direction == Direction.DOWN && !this.item.isEmpty();
		if (ret) this.updateBlock();
		return ret;
	}
}
