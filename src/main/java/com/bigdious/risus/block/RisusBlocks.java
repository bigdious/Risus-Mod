package com.bigdious.risus.block;

import com.bigdious.risus.Risus;
import com.bigdious.risus.items.RisusItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Risus.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RisusBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Risus.ID);

    public static final RegistryObject<Block> ASHEN_REMAINS = BLOCKS.register("ashen_remains", () -> new Block(Block.Properties.from(Blocks.SOUL_SOIL)));
    public static final RegistryObject<Block> SMILING_REMAINS = BLOCKS.register("smiling_remains", () -> new FallingBlock(Block.Properties.from(Blocks.SOUL_SAND)));
    public static final RegistryObject<Block> ENGRAVED_BASALT = BLOCKS.register("engraved_basalt", () -> new EngravedBasaltBlock(Block.Properties.from(Blocks.POLISHED_BASALT).notSolid()));
    public static final RegistryObject<Block> MAW_GUTS = BLOCKS.register("maw_guts", () -> new MawGutsBlock(Block.Properties.create(Material.CAKE).notSolid().setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0F)));
    public static final RegistryObject<Block> BLOODWYRM_HEAD = BLOCKS.register("bloodwyrm_head", () -> new ModSkullBlock(ModSkullBlock.Types.BLOODWYRM, Block.Properties.from(Blocks.DRAGON_HEAD)));
    public static final RegistryObject<Block> BLOODWYRM_WALL_HEAD = BLOCKS.register("bloodwyrm_wall_head", () -> new ModWallSkullBlock(ModSkullBlock.Types.BLOODWYRM, Block.Properties.from(Blocks.DRAGON_WALL_HEAD)));

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.register(blockItem(ASHEN_REMAINS));
        r.register(blockItem(SMILING_REMAINS));
        r.register(blockItem(ENGRAVED_BASALT));
        r.register(blockItem(MAW_GUTS));

    }

    private static <B extends Block> Item blockItem(RegistryObject<B> block) {
        return new BlockItem(block.get(), RisusItems.defaultWithRarity()).setRegistryName(block.getId());
    }

}
