package com.bigdious.risus.init;

import java.util.ArrayList;

import com.bigdious.risus.Risus;
import com.bigdious.risus.tools.ModItemTier;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "risus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    private static final ArrayList<Item> ITEMS = new ArrayList<>();
    
    public static final Item JOYKILLER = register("joykiller", new SwordItem(ModItemTier.JOY, 3, -0F, (new Item.Properties()).group(Risus.TAB)));
    public static final Item SMILE = register("smile", new Item((new Item.Properties()).group(Risus.TAB)));
    public static final Item BLOOD_FEATHER = register("blood_feather", new Item((new Item.Properties()).group(Risus.TAB)));
    
    //Template
    //public static final Item  = register("", new Item((new Item.Properties()).group(Risus.TAB)));
    
    private static Item register(String name, Item item) {
        item.setRegistryName(name);
        ITEMS.add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ITEMS.forEach(item -> event.getRegistry().register((Item) item));
        ITEMS.clear();
    }
}
