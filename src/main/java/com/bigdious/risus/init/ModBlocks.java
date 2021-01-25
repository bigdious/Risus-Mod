package com.bigdious.risus.init;

import java.util.ArrayList;

import com.bigdious.risus.Risus;
import com.bigdious.risus.Risus.RisusTab;
import com.bigdious.risus.block.EngravedBasaltBlock;
import com.bigdious.risus.block.MawGutsBlock;
import com.bigdious.risus.block.ModSkullBlock;
import com.bigdious.risus.block.ModWallSkullBlock;
import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "risus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static final ArrayList<Block> BLOCKS = new ArrayList<>();
    public static final ArrayList<Item> ITEMS = new ArrayList<>();

    public static final Block ASHEN_REMAINS = register("ashen_remains", new Block(Block.Properties.from(Blocks.SOUL_SOIL)));
    public static final Block SMILING_REMAINS = register("smiling_remains", new Block(Block.Properties.from(Blocks.SOUL_SAND)));
    public static final Block ENGRAVED_BASALT = register("engraved_basalt", new EngravedBasaltBlock(Block.Properties.from(Blocks.POLISHED_BASALT)));
    public static final Block MAW_GUTS = register("maw_guts", new MawGutsBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(10.0F)));
    
    public static final Block BLOODWYRM_HEAD = register("bloodwyrm_head", new ModSkullBlock(ModSkullBlock.Types.BLOODWYRM, Block.Properties.from(Blocks.DRAGON_HEAD)), (BlockItem) null);
    public static final Block BLOODWYRM_WALL_HEAD = register("bloodwyrm_wall_head", new ModWallSkullBlock(ModSkullBlock.Types.BLOODWYRM, Block.Properties.from(Blocks.DRAGON_WALL_HEAD)), (BlockItem) null);
    
    // Template
    // public static final Block = register("", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F)));

    private static Block register(String name, Block block) {
        return register(name, block, new Item.Properties().rarity(Risus.getRarity()).group(RisusTab.INSTANCE));
    }

    private static Block register(String name, Block block, Item.Properties properties) {
        return register(name, block, new BlockItem(block, properties));
    }

    private static Block register(String name, Block block, BlockItem item) {
        return register(name, block, block1 -> item);
    }

    private static Block register(String name, Block block, Function<Block, BlockItem> function) {
        block.setRegistryName(name);
        BLOCKS.add(block);
        if (block.getRegistryName() != null) {
            Item item = function.apply(block);
            if (item != null) {
                item.setRegistryName(name);
                ITEMS.add(item);
            }
        }
        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BLOCKS.forEach(block -> event.getRegistry().register(block));
        BLOCKS.clear();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ITEMS.forEach(item -> event.getRegistry().register(item));
        ITEMS.clear();
    }
}
