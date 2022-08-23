package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.*;
import com.bigdious.risus.util.RisusSkullType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("deprecation")
public class RisusBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Risus.MODID);

	public static final WoodType BONDKNOT = WoodType.create(Risus.prefix("bondknot").toString());

	public static final RegistryObject<Block> ALTERATION_MONOLITH = BLOCKS.register("alteration_monolith", () -> new AlterationMonolithBlock(Block.Properties.copy(Blocks.OBSIDIAN).noOcclusion()));
	public static final RegistryObject<Block> ASHEN_REMAINS = BLOCKS.register("ashen_remains", () -> new Block(Block.Properties.copy(Blocks.SOUL_SOIL)));
	public static final RegistryObject<Block> SMILING_REMAINS = BLOCKS.register("smiling_remains", () -> new FallingBlock(Block.Properties.copy(Blocks.SOUL_SOIL)));
	public static final RegistryObject<Block> LAUGHING_OBSIDIAN = BLOCKS.register("laughing_obsidian", () -> new LaughingObsidianBlock(Block.Properties.copy(Blocks.CRYING_OBSIDIAN)));
	public static final RegistryObject<RotatedPillarBlock> ENGRAVED_BASALT = BLOCKS.register("engraved_basalt", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.POLISHED_BASALT).noOcclusion()));
	public static final RegistryObject<Block> MAW_GUTS = BLOCKS.register("maw_guts", () -> new MawGutsBlock(Block.Properties.of(Material.CAKE).noOcclusion().requiresCorrectToolForDrops().strength(4.0F)));
	public static final RegistryObject<Block> MIRAGE_GRASS_BLOCK = BLOCKS.register("mirage_grass_block", () -> new MirageBlock(Block.Properties.copy(Blocks.GRASS_BLOCK).noOcclusion()));
	public static final RegistryObject<Block> MIRAGE_SAND = BLOCKS.register("mirage_sand", () -> new MirageBlock(Block.Properties.copy(Blocks.SAND).noOcclusion()));
	public static final RegistryObject<Block> GLUTTONY_SCALEPLATE = BLOCKS.register("gluttony_scaleplate", () -> new ScaleplateBlock(Block.Properties.copy(Blocks.ANCIENT_DEBRIS).noOcclusion()));

	public static final RegistryObject<Block> CRYSTALLIZED_BONDS = BLOCKS.register("crystallized_bonds", () -> new CrystallizedBondsBlock(Block.Properties.of(Material.GLASS).noOcclusion().sound(SoundType.GLASS).strength(0.25F)));
	public static final RegistryObject<Block> BLOODWEAVE = BLOCKS.register("bloodweave", () -> new Block(Block.Properties.of(Material.DECORATION).noOcclusion().instabreak()));
	public static final RegistryObject<Block> BLOODWYRM_HEAD = BLOCKS.register("bloodwyrm_head", () -> new RisusSkullBlock(RisusSkullType.BLOODWYRM, Block.Properties.copy(Blocks.DRAGON_HEAD).instabreak()));
	public static final RegistryObject<Block> BLOODWYRM_WALL_HEAD = BLOCKS.register("bloodwyrm_wall_head", () -> new RisusWallSkullBlock(RisusSkullType.BLOODWYRM, Block.Properties.copy(Blocks.DRAGON_WALL_HEAD).instabreak()));

	public static final RegistryObject<RotatedPillarBlock> BONDKNOT_LOG = BLOCKS.register("bondknot_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> BONDKNOT_WOOD = BLOCKS.register("bondknot_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> POPPING_BONDKNOT_LOG = BLOCKS.register("popping_bondknot_log", () -> new PoppingBondknotBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> POPPING_BONDKNOT_WOOD = BLOCKS.register("popping_bondknot_wood", () -> new PoppingBondknotBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_BONDKNOT_LOG = BLOCKS.register("stripped_bondknot_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_BONDKNOT_WOOD = BLOCKS.register("stripped_bondknot_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BONDKNOT_PLANKS = BLOCKS.register("bondknot_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> BONDKNOT_STAIRS = BLOCKS.register("bondknot_stairs", () -> new StairBlock(BONDKNOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<SlabBlock> BONDKNOT_SLAB = BLOCKS.register("bondknot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<FenceBlock> BONDKNOT_FENCE = BLOCKS.register("bondknot_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<FenceGateBlock> BONDKNOT_FENCE_GATE = BLOCKS.register("bondknot_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<PressurePlateBlock> BONDKNOT_PRESSURE_PLATE = BLOCKS.register("bondknot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<WoodButtonBlock> BONDKNOT_BUTTON = BLOCKS.register("bondknot_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<TrapDoorBlock> BONDKNOT_TRAPDOOR = BLOCKS.register("bondknot_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<DoorBlock> BONDKNOT_DOOR = BLOCKS.register("bondknot_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<StandingSignBlock> BONDKNOT_SIGN = BLOCKS.register("bondknot_sign", () -> new RisusSignBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion().noCollission(), BONDKNOT));
	public static final RegistryObject<WallSignBlock> BONDKNOT_WALL_SIGN = BLOCKS.register("bondknot_wall_sign", () -> new RisusWallSignBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion().noCollission(), BONDKNOT));
	//TODO: boat

	public static final RegistryObject<Block> BABY_RIBCAGE = BLOCKS.register("baby_ribcage", () -> new BabyRibcageBlock(Block.Properties.copy(Blocks.BONE_BLOCK).noOcclusion()));
	public static final RegistryObject<Block> RIBCAGE = BLOCKS.register("ribcage", () -> new RibcageBlock(Block.Properties.copy(Blocks.BONE_BLOCK).noOcclusion()));
	public static final RegistryObject<RotatedPillarBlock> GRIMSTONE = BLOCKS.register("grimstone", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> GRIMSTONE_BRICKS = BLOCKS.register("grimstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<Block> CRACKED_GRIMSTONE_BRICKS = BLOCKS.register("cracked_grimstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<SlabBlock> GRIMSTONE_SLAB = BLOCKS.register("grimstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<StairBlock> GRIMSTONE_STAIRS = BLOCKS.register("grimstone_stairs", () -> new StairBlock(GRIMSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<WallBlock> GRIMSTONE_WALL = BLOCKS.register("grimstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<Block> POLISHED_GRIMSTONE = BLOCKS.register("polished_grimstone", () -> new Block(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<Block> CHISELED_GRIMSTONE = BLOCKS.register("chiseled_grimstone", () -> new Block(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
}