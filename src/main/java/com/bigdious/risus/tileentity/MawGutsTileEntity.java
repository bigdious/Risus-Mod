package com.bigdious.risus.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class MawGutsTileEntity extends LockableLootTileEntity implements ITickableTileEntity {

    private NonNullList<ItemStack> contents = NonNullList.withSize(54, ItemStack.EMPTY);

    public MawGutsTileEntity() {
        super(RisusTileEntities.MAW_GUTS.get());
    }

    @Override
    public int getSizeInventory() {
        return 54;
    }

    @Override
    public void tick() {
        
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.contents;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.contents = items;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.risus.mawguts");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return ChestContainer.createGeneric9X6(id, player, this);
    }

}
