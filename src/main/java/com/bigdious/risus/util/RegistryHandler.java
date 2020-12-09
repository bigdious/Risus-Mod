package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.BlockItemBase;
import com.bigdious.risus.blocks.SmilingRemains;
import com.bigdious.risus.items.ItemBase;
import com.bigdious.risus.tools.ModItemTier;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Risus.MOD_ID);
    public static DeferredRegister<Block>  BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Risus.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
    // Items
    public static final RegistryObject<Item> SMILE = ITEMS.register("smile", ItemBase::new);
    public static final RegistryObject<Item> ANGEL_FEATHER = ITEMS.register("angel_feather", ItemBase::new);

    // Tools
    public static final RegistryObject<SwordItem> JOYKILLER = ITEMS.register("joykiller", ()->
            new SwordItem(ModItemTier.JOY, 3, -0F, new Item.Properties().group(Risus.TAB)));

    // Blocks
    public static final RegistryObject<Block> SMILING_REMAINS = BLOCKS.register("smiling_remains", SmilingRemains:: new);

    // Block Items
    public static final RegistryObject<Item> SMILING_REMAINS_ITEM = ITEMS.register("smiling_remains", () -> new BlockItemBase(SMILING_REMAINS.get()));

        }
