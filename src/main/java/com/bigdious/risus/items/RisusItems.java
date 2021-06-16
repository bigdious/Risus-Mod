package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.Risus.RisusTab;
import com.bigdious.risus.block.RisusBlocks;
import com.bigdious.risus.client.ISTER;
import com.bigdious.risus.tileentity.RisusTileEntities;
import com.bigdious.risus.util.ModItemTier;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.SkullItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.Callable;

public class RisusItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Risus.ID);
    
    public static final RegistryObject<Item> JOYKILLER = ITEMS.register("joykiller", () -> new SwordItem(ModItemTier.JOY, 3, -2.4F, defaultWithRarity()));
    public static final RegistryObject<Item> SMILE = ITEMS.register("smile", () -> new Item(defaultWithRarity()));
    public static final RegistryObject<Item> BLOOD_FEATHER = ITEMS.register("blood_feather", () -> new Item(defaultWithRarity()));
    public static final RegistryObject<Item> BLOODWYRM_HEAD = ITEMS.register("bloodwyrm_head", () -> new SkullItem(RisusBlocks.BLOODWYRM_HEAD.get(), RisusBlocks.BLOODWYRM_WALL_HEAD.get(), defaultWithRarity().setISTER(() -> new Callable<ItemStackTileEntityRenderer>() {
        @Override
        public ItemStackTileEntityRenderer call() {
            return new ISTER(RisusTileEntities.BLOODWYRM.getId());
        }
    })));

    public static Item.Properties defaultWithRarity() {
        return new Item.Properties().rarity(Risus.getRarity()).group(RisusTab.INSTANCE);
    }
}
