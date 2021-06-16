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
    public static final RegistryObject<Block> LAUGHING_OBSIDIAN = BLOCKS.register("laughing_obsidian", () -> new LaughingObsidianBlock(Block.Properties.from(Blocks.CRYING_OBSIDIAN)));
    public static final RegistryObject<Block> ENGRAVED_BASALT = BLOCKS.register("engraved_basalt", () -> new EngravedBasaltBlock(Block.Properties.from(Blocks.POLISHED_BASALT).notSolid()));
    public static final RegistryObject<Block> MAW_GUTS = BLOCKS.register("maw_guts", () -> new MawGutsBlock(Block.Properties.create(Material.CAKE).notSolid().setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0F)));
    public static final RegistryObject<Block> CRYSTALIZED_BONDS = BLOCKS.register("crystalized_bonds", () -> new CrystalizedBondsBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.25F)));
    public static final RegistryObject<Block> BLOODWEAVE = BLOCKS.register("bloodweave", () -> new BloodweaveBlock(Block.Properties.create(Material.MISCELLANEOUS).notSolid().zeroHardnessAndResistance()));
    public static final RegistryObject<Block> BLOODWYRM_HEAD = BLOCKS.register("bloodwyrm_head", () -> new ModSkullBlock(ModSkullBlock.Types.BLOODWYRM, Block.Properties.from(Blocks.DRAGON_HEAD)));
    public static final RegistryObject<Block> BLOODWYRM_WALL_HEAD = BLOCKS.register("bloodwyrm_wall_head", () -> new ModWallSkullBlock(ModSkullBlock.Types.BLOODWYRM, Block.Properties.from(Blocks.DRAGON_WALL_HEAD)));

    public static final RegistryObject<RotatedPillarBlock> BONDKNOT_LOG = BLOCKS.register("bondknot_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> BONDKNOT_WOOD = BLOCKS.register("bondknot_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BONDKNOT_LOG = BLOCKS.register("stripped_bondknot_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BONDKNOT_WOOD = BLOCKS.register("stripped_bondknot_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BONDKNOT_PLANKS = BLOCKS.register("bondknot_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<StairsBlock> BONDKNOT_STAIRS = BLOCKS.register("bondknot_stairs", () -> new StairsBlock(BONDKNOT_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(BONDKNOT_PLANKS.get())));
    public static final RegistryObject<SlabBlock> BONDKNOT_SLAB = BLOCKS.register("bondknot_slab", () -> new SlabBlock(AbstractBlock.Properties.from(BONDKNOT_PLANKS.get())));
    public static final RegistryObject<FenceBlock> BONDKNOT_FENCE = BLOCKS.register("bondknot_fence", () -> new FenceBlock(AbstractBlock.Properties.from(BONDKNOT_PLANKS.get())));
    public static final RegistryObject<FenceGateBlock> BONDKNOT_FENCE_GATE = BLOCKS.register("bondknot_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(BONDKNOT_PLANKS.get())));
    public static final RegistryObject<PressurePlateBlock> BONDKNOT_PRESSURE_PLATE = BLOCKS.register("bondknot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(BONDKNOT_PLANKS.get())));
    public static final RegistryObject<WoodButtonBlock> BONDKNOT_BUTTON = BLOCKS.register("bondknot_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(BONDKNOT_PLANKS.get())));
    //TODO: door, trapdoor, sign, boat

    public static final RegistryObject<Block> BABY_RIBCAGE = BLOCKS.register("baby_ribcage", () -> new RibcageBlock(Block.Properties.from(Blocks.BONE_BLOCK).notSolid()));
    public static final RegistryObject<Block> RIBCAGE = BLOCKS.register("ribcage", () -> new RibcageBlock(Block.Properties.from(Blocks.BONE_BLOCK).notSolid()));
    public static final RegistryObject<RotatedPillarBlock> GRIMSTONE = BLOCKS.register("grimstone", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STONE)));
    public static final RegistryObject<Block> GRIMSTONE_BRICKS = BLOCKS.register("grimstone_bricks", () -> new Block(AbstractBlock.Properties.from(GRIMSTONE.get())));
    public static final RegistryObject<Block> CRACKED_GRIMSTONE_BRICKS = BLOCKS.register("cracked_grimstone_bricks", () -> new Block(AbstractBlock.Properties.from(GRIMSTONE.get())));
    public static final RegistryObject<SlabBlock> GRIMSTONE_SLAB = BLOCKS.register("grimstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GRIMSTONE.get())));
    public static final RegistryObject<StairsBlock> GRIMSTONE_STAIRS = BLOCKS.register("grimstone_stairs", () -> new StairsBlock(GRIMSTONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(GRIMSTONE.get())));
    public static final RegistryObject<WallBlock> GRIMSTONE_WALL = BLOCKS.register("grimstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(GRIMSTONE.get())));
    public static final RegistryObject<Block> POLISHED_GRIMSTONE = BLOCKS.register("polished_grimstone", () -> new Block(AbstractBlock.Properties.from(GRIMSTONE.get())));
    public static final RegistryObject<Block> CHISELED_GRIMSTONE = BLOCKS.register("chiseled_grimstone", () -> new Block(AbstractBlock.Properties.from(GRIMSTONE.get())));

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.register(blockItem(ASHEN_REMAINS));
        r.register(blockItem(SMILING_REMAINS));
        r.register(blockItem(LAUGHING_OBSIDIAN));
        r.register(blockItem(ENGRAVED_BASALT));
        r.register(blockItem(MAW_GUTS));
        r.register(blockItem(CRYSTALIZED_BONDS));
        r.register(blockItem(BLOODWEAVE));
        r.register(blockItem(BONDKNOT_LOG));
        r.register(blockItem(BONDKNOT_WOOD));
        r.register(blockItem(STRIPPED_BONDKNOT_LOG));
        r.register(blockItem(STRIPPED_BONDKNOT_WOOD));
        r.register(blockItem(BONDKNOT_PLANKS));
        r.register(blockItem(BONDKNOT_STAIRS));
        r.register(blockItem(BONDKNOT_SLAB));
        r.register(blockItem(BONDKNOT_BUTTON));
        r.register(blockItem(BONDKNOT_FENCE));
        r.register(blockItem(BONDKNOT_FENCE_GATE));
        r.register(blockItem(BONDKNOT_PRESSURE_PLATE));
        r.register(blockItem(BABY_RIBCAGE));
        r.register(blockItem(RIBCAGE));
        r.register(blockItem(GRIMSTONE));
        r.register(blockItem(GRIMSTONE_BRICKS));
        r.register(blockItem(CRACKED_GRIMSTONE_BRICKS));
        r.register(blockItem(GRIMSTONE_SLAB));
        r.register(blockItem(GRIMSTONE_STAIRS));
        r.register(blockItem(GRIMSTONE_WALL));
        r.register(blockItem(CHISELED_GRIMSTONE));
        r.register(blockItem(POLISHED_GRIMSTONE));

    }

    private static <B extends Block> Item blockItem(RegistryObject<B> block) {
        return new BlockItem(block.get(), RisusItems.defaultWithRarity()).setRegistryName(block.getId());
    }

}
