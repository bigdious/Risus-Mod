package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.*;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.util.RisusSkullType;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("deprecation")
public class RisusBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Risus.MODID);

	public static final BlockSetType BONDKNOT_SET = new BlockSetType(Risus.prefix("bondknot").toString());
	public static final WoodType BONDKNOT_TYPE = WoodType.register(new WoodType(Risus.prefix("bondknot").toString(), BONDKNOT_SET));

	public static final RegistryObject<Block> ALTERATION_CATALYST = BLOCKS.register("alteration_catalyst", () -> new AlterationCatalystBlock(Block.Properties.copy(Blocks.OBSIDIAN).noOcclusion()));
	public static final RegistryObject<Block> ANGEL_ALTAR = BLOCKS.register("angel_altar", () -> new AngelAltar(Block.Properties.copy(Blocks.OBSIDIAN).noOcclusion()));
	public static final RegistryObject<Block> DEPTH_VASE = BLOCKS.register("depth_vase", () -> new DepthVaseBlock(Block.Properties.copy(Blocks.DECORATED_POT).mapColor(MapColor.NONE).pushReaction(PushReaction.DESTROY).noOcclusion().strength(2.0F)));
	public static final RegistryObject<Block> ASHEN_REMAINS = BLOCKS.register("ashen_remains", () -> new AshenRemainsBlock(Block.Properties.copy(Blocks.SOUL_SOIL)));
	public static final RegistryObject<Block> SMILING_REMAINS = BLOCKS.register("smiling_remains", () -> new RemainsBlock(Block.Properties.copy(Blocks.NETHERRACK)));
	public static final RegistryObject<Block> LAUGHING_OBSIDIAN = BLOCKS.register("laughing_obsidian", () -> new LaughingObsidianBlock(Block.Properties.copy(Blocks.CRYING_OBSIDIAN)));
	public static final RegistryObject<RotatedPillarBlock> ENGRAVED_BASALT = BLOCKS.register("engraved_basalt", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.POLISHED_BASALT).noOcclusion()));
	public static final RegistryObject<Block> BURNT_HYPHAE = BLOCKS.register("burnt_hyphae", () -> new Block(Block.Properties.copy(Blocks.CRIMSON_HYPHAE)));
	public static final RegistryObject<Block> BIG_CHAIN = BLOCKS.register("big_chain", () -> new BigChainBlock(Block.Properties.copy(Blocks.CHAIN)));

	public static final RegistryObject<Block> MAW_GUTS = BLOCKS.register("maw_guts", () -> new MawGutsBlock(Block.Properties.of().mapColor(MapColor.NONE).pushReaction(PushReaction.DESTROY).noOcclusion().requiresCorrectToolForDrops().strength(4.0F)));
	public static final RegistryObject<Block> MIRAGE_GRASS_BLOCK = BLOCKS.register("mirage_grass_block", () -> new MirageBlock(Block.Properties.copy(Blocks.GRASS_BLOCK).noOcclusion(), Blocks.GRASS_BLOCK));
	public static final RegistryObject<Block> MIRAGE_SAND = BLOCKS.register("mirage_sand", () -> new MirageBlock(Block.Properties.copy(Blocks.SAND).noOcclusion(), Blocks.SAND));
	public static final RegistryObject<Block> MIRAGE_END_STONE = BLOCKS.register("mirage_end_stone", () -> new MirageBlock(Block.Properties.copy(Blocks.END_STONE).noOcclusion(), Blocks.END_STONE));
	public static final RegistryObject<Block> MIRAGE_NETHERRACK = BLOCKS.register("mirage_netherrack", () -> new MirageBlock(Block.Properties.copy(Blocks.NETHERRACK).noOcclusion(), Blocks.NETHERRACK));
	public static final RegistryObject<Block> GLUTTONY_SCALEPLATE = BLOCKS.register("gluttony_scaleplate", () -> new ScaleplateBlock(Block.Properties.copy(Blocks.ANCIENT_DEBRIS).noOcclusion()));
	public static final RegistryObject<Block> IMITATION_SCALEPLATE = BLOCKS.register("imitation_scaleplate", () -> new ScaleplateBlock(Block.Properties.copy(Blocks.ANCIENT_DEBRIS).noOcclusion()));
	public static final RegistryObject<Block> FLOWERING_IMITATION_SCALEPLATE = BLOCKS.register("flowering_imitation_scaleplate", () -> new ScaleplateBlock(Block.Properties.copy(Blocks.ANCIENT_DEBRIS).noOcclusion()));
	public static final RegistryObject<Block> BUDDING_IMITATION_SCALEPLATE = BLOCKS.register("budding_imitation_scaleplate", () -> new ScaleplateBlock(Block.Properties.copy(Blocks.ANCIENT_DEBRIS).noOcclusion()));
	public static final RegistryObject<Block> FLATTENED_SCALES_BLOCK = BLOCKS.register("flattened_scales_block", () -> new ActuallyUseableDirectionalBlock(Block.Properties.copy(RisusBlocks.GLUTTONY_SCALEPLATE.get()).forceSolidOn()));
	public static final RegistryObject<Block> FLATTENED_IMITATION_SCALES_BLOCK = BLOCKS.register("flattened_imitation_scales_block", () -> new ActuallyUseableDirectionalBlock(Block.Properties.copy(RisusBlocks.GLUTTONY_SCALEPLATE.get()).forceSolidOn()));
	public static final RegistryObject<Block> ZIT = BLOCKS.register("zit", () -> new ZitBlock(Block.Properties.copy(Blocks.SCULK).noCollission().noOcclusion().forceSolidOn().instabreak()));

	public static final RegistryObject<Block> CRYSTALLIZED_BONDS = BLOCKS.register("crystallized_bonds", () -> new CrystallizedBondsBlock(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().sound(SoundType.GLASS).strength(0.25F)));
	public static final RegistryObject<Block> BLOODWEAVE = BLOCKS.register("bloodweave", () -> new BloodweaveBlock(Block.Properties.of().mapColor(MapColor.COLOR_RED).noOcclusion().instabreak().sound(SoundType.HONEY_BLOCK).noCollission().forceSolidOn()));
	public static final RegistryObject<Block> BLOODWYRM_HEAD = BLOCKS.register("bloodwyrm_head", () -> new RisusSkullBlock(RisusSkullType.BLOODWYRM, Block.Properties.copy(Blocks.DRAGON_HEAD).instabreak()));
	public static final RegistryObject<Block> BLOODWYRM_WALL_HEAD = BLOCKS.register("bloodwyrm_wall_head", () -> new RisusWallSkullBlock(RisusSkullType.BLOODWYRM, Block.Properties.copy(Blocks.DRAGON_WALL_HEAD).instabreak()));

	public static final RegistryObject<RotatedPillarBlock> BONDKNOT_LOG = BLOCKS.register("bondknot_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> BONDKNOT_WOOD = BLOCKS.register("bondknot_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> POPPING_BONDKNOT_LOG = BLOCKS.register("popping_bondknot_log", () -> new PoppingBondknotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> POPPING_BONDKNOT_WOOD = BLOCKS.register("popping_bondknot_wood", () -> new PoppingBondknotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_BONDKNOT_LOG = BLOCKS.register("stripped_bondknot_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_BONDKNOT_WOOD = BLOCKS.register("stripped_bondknot_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BONDKNOT_PLANKS = BLOCKS.register("bondknot_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> BONDKNOT_STAIRS = BLOCKS.register("bondknot_stairs", () -> new RisusStairBlock(BONDKNOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<SlabBlock> BONDKNOT_SLAB = BLOCKS.register("bondknot_slab", () -> new RisusSlabBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<FenceBlock> BONDKNOT_FENCE = BLOCKS.register("bondknot_fence", () -> new RisusFenceBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get())));
	public static final RegistryObject<FenceGateBlock> BONDKNOT_FENCE_GATE = BLOCKS.register("bondknot_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()), BONDKNOT_TYPE));
	public static final RegistryObject<PressurePlateBlock> BONDKNOT_PRESSURE_PLATE = BLOCKS.register("bondknot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()), BONDKNOT_SET));
	public static final RegistryObject<ButtonBlock> BONDKNOT_BUTTON = BLOCKS.register("bondknot_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()), BONDKNOT_SET, 30, true));
	public static final RegistryObject<TrapDoorBlock> BONDKNOT_TRAPDOOR = BLOCKS.register("bondknot_trapdoor", () -> new RisusTrapDoorBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion(), BONDKNOT_SET));
	public static final RegistryObject<DoorBlock> BONDKNOT_DOOR = BLOCKS.register("bondknot_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion(), BONDKNOT_SET));
	public static final RegistryObject<StandingSignBlock> BONDKNOT_SIGN = BLOCKS.register("bondknot_sign", () -> new RisusSignBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion().noCollission().forceSolidOn(), RisusBlocks.BONDKNOT_TYPE));
	public static final RegistryObject<WallSignBlock> BONDKNOT_WALL_SIGN = BLOCKS.register("bondknot_wall_sign", () -> new RisusWallSignBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion().noCollission().forceSolidOn(), RisusBlocks.BONDKNOT_TYPE));
	public static final RegistryObject<CeilingHangingSignBlock> BONDKNOT_HANGING_SIGN = BLOCKS.register("bondknot_hanging_sign", () -> new RisusCeilingHangingSignBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion().noCollission().forceSolidOn(), RisusBlocks.BONDKNOT_TYPE));
	public static final RegistryObject<WallHangingSignBlock> BONDKNOT_WALL_HANGING_SIGN = BLOCKS.register("bondknot_wall_hanging_sign", () -> new RisusWallHangingSignBlock(BlockBehaviour.Properties.copy(BONDKNOT_PLANKS.get()).noOcclusion().forceSolidOn(), RisusBlocks.BONDKNOT_TYPE));

	public static final RegistryObject<Block> HEART_TRANSPLANT = BLOCKS.register("heart_transplant", () -> new HeartTransplantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().forceSolidOn().sound(SoundType.SCULK).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> REGEN_ROSE = BLOCKS.register("regen_rose", () -> new RegenRoseBlock(MobEffects.REGENERATION, 1 ,BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.ROOTS).offsetType(BlockBehaviour.OffsetType.XZ)));
public static final RegistryObject<Block> POTTED_HEART_TRANSPLANT = BLOCKS.register("potted_heart_transplant", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, HEART_TRANSPLANT, BlockBehaviour.Properties.of().mapColor(MapColor.NONE).pushReaction(PushReaction.DESTROY).instabreak().noOcclusion()));
	public static final RegistryObject<Block> POTTED_REGEN_ROSE = BLOCKS.register("potted_regen_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, REGEN_ROSE, BlockBehaviour.Properties.of().mapColor(MapColor.NONE).pushReaction(PushReaction.DESTROY).instabreak().noOcclusion()));
	public static final RegistryObject<Block> BABY_RIBCAGE = BLOCKS.register("baby_ribcage", () -> new BabyRibcageBlock(Block.Properties.copy(Blocks.BONE_BLOCK).noOcclusion().forceSolidOn()));
	public static final RegistryObject<Block> RIBCAGE = BLOCKS.register("ribcage", () -> new RibcageBlock(Block.Properties.copy(Blocks.BONE_BLOCK).noOcclusion()));
	public static final RegistryObject<RotatedPillarBlock> GRIMSTONE = BLOCKS.register("grimstone", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> GRIMSTONE_BRICKS = BLOCKS.register("grimstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<Block> CRACKED_GRIMSTONE_BRICKS = BLOCKS.register("cracked_grimstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<SlabBlock> GRIMSTONE_SLAB = BLOCKS.register("grimstone_slab", () -> new RisusSlabBlock(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<SlabBlock> GRIMSTONE_BRICKS_SLAB = BLOCKS.register("grimstone_bricks_slab", () -> new RisusSlabBlock(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<StairBlock> GRIMSTONE_BRICKS_STAIRS = BLOCKS.register("grimstone_bricks_stairs", () -> new RisusStairBlock(GRIMSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<StairBlock> GRIMSTONE_STAIRS = BLOCKS.register("grimstone_stairs", () -> new RisusStairBlock(GRIMSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<WallBlock> GRIMSTONE_WALL = BLOCKS.register("grimstone_wall", () -> new RisusWallBlock(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<WallBlock> GRIMSTONE_BRICKS_WALL = BLOCKS.register("grimstone_bricks_wall", () -> new RisusWallBlock(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<Block> POLISHED_GRIMSTONE = BLOCKS.register("polished_grimstone", () -> new Block(BlockBehaviour.Properties.copy(GRIMSTONE.get())));
	public static final RegistryObject<Block> CHISELED_GRIMSTONE = BLOCKS.register("chiseled_grimstone", () -> new ChiseledGrimstone(BlockBehaviour.Properties.copy(GRIMSTONE.get())) {
	});

	public static final RegistryObject<DecomposingTissueBlock> TISSUE = BLOCKS.register("tissue", () -> new DecomposingTissueBlock(DecomposingBlock.DecomposeState.NONE, Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<StairBlock> TISSUE_STAIRS = BLOCKS.register("tissue_stairs", () -> new RisusStairBlock(TISSUE.get().defaultBlockState(), Block.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<SlabBlock> TISSUE_SLAB = BLOCKS.register("tissue_slab", () -> new RisusSlabBlock(Block.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<WallBlock> ROTTING_TISSUE = BLOCKS.register("rotting_tissue", () -> new DecomposingTissueBlock(DecomposingBlock.DecomposeState.ROTTING, Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<WallBlock> DECOMPOSING_TISSUE = BLOCKS.register("decomposing_tissue", () -> new DecomposingTissueBlock(DecomposingBlock.DecomposeState.DECOMPOSING, Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<WallBlock> DECAYING_TISSUE = BLOCKS.register("decaying_tissue", () -> new DecomposingTissueBlock(DecomposingBlock.DecomposeState.DECAYING, Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<WallBlock> BONE_WALL = BLOCKS.register("bone_wall", () -> new RisusWallBlock(Block.Properties.copy(Blocks.BONE_BLOCK)));
	public static final RegistryObject<StairBlock> BONE_STAIRS = BLOCKS.register("bone_stairs", () -> new RisusStairBlock(Blocks.BONE_BLOCK.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));
	public static final RegistryObject<SlabBlock> BONE_SLAB = BLOCKS.register("bone_slab", () -> new RisusSlabBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));

	public static final RegistryObject<Block> LIVING_TISSUE = BLOCKS.register("living_tissue", () -> new Block(Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<WallBlock> ROTTED_TISSUE = BLOCKS.register("rotted_tissue", () -> new WallBlock(Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<WallBlock> DECOMPOSED_TISSUE = BLOCKS.register("decomposed_tissue", () -> new WallBlock(Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<WallBlock> DECAYED_TISSUE = BLOCKS.register("decayed_tissue", () -> new WallBlock(Block.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<Block> EYE_ENDER = BLOCKS.register("eye_ender", () -> new ActuallyUseableDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<Block> EYE_BLEACHED = BLOCKS.register("eye_bleached", () -> new ActuallyUseableDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<Block> EYE_BLOODSHOT = BLOCKS.register("eye_bloodshot", () -> new ActuallyUseableDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<Block> EYE_EMERALD = BLOCKS.register("eye_emerald", () -> new ActuallyUseableDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<Block> EYE_GOLDEN = BLOCKS.register("eye_golden", () -> new ActuallyUseableDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK)));
	public static final RegistryObject<Block> FLESHY_SKIN = BLOCKS.register("fleshy_skin", () -> new FleshySkinBlock(BlockBehaviour.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<Block> CURVED_FLESHY_SKIN = BLOCKS.register("curved_fleshy_skin", () -> new CurvedSkinBlock(BlockBehaviour.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<Block> SKIN = BLOCKS.register("skin", () -> new SkinBlock(BlockBehaviour.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<Block> HAIRY_FLESHY_SKIN = BLOCKS.register("hairy_fleshy_skin", () -> new HairyFleshySkinBlock(BlockBehaviour.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<Block> HAIRY_CURVED_FLESHY_SKIN = BLOCKS.register("hairy_curved_fleshy_skin", () -> new HairyCurvedSkinBlock(BlockBehaviour.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<Block> HAIRY_SKIN = BLOCKS.register("hairy_skin", () -> new HairySkinBlock(BlockBehaviour.Properties.copy(RisusBlocks.TISSUE.get())));
	public static final RegistryObject<Block> TALL_HAIR = BLOCKS.register("tall_hair", () -> new RisusTallGrassBlock(Block.Properties.copy(Blocks.TALL_GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)));
	public static final RegistryObject<RotatedPillarBlock> BUNDLE_OF_HAIR = BLOCKS.register("bundle_of_hair", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.DRIED_KELP_BLOCK).ignitedByLava()));
	public static final RegistryObject<Block> SCAB= BLOCKS.register("scab", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_HYPHAE).mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.SNARE).strength(3.0F, 3.0F).sound(SoundType.NETHER_WOOD).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> COAGULATED_BLOOD_BLOCK= BLOCKS.register("coagulated_blood_block", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_RED).noOcclusion().instabreak().sound(SoundType.HONEY_BLOCK).forceSolidOn()));

	public static final RegistryObject<Block> JOYFLAME_TORCH = BLOCKS.register("joyflame_torch", () -> new ModdedTorchBlock(Block.Properties.copy(Blocks.TORCH)));
	public static final RegistryObject<Block> JOYFLAME_WALL_TORCH = BLOCKS.register("joyflame_wall_torch", () -> new ModdedWallTorchBlock(Block.Properties.copy(Blocks.TORCH)));
	public static final RegistryObject<Block> JOYFLAME_CAMPFIRE = BLOCKS.register("joyflame_campfire", () -> new RisusCampfireBlock(true, 2, Block.Properties.copy(Blocks.CAMPFIRE)));
	public static final RegistryObject<Block> JOYFLAME_LANTERN = BLOCKS.register("joyflame_lantern", () -> new LanternBlock(Block.Properties.copy(Blocks.LANTERN)));
	public static final RegistryObject<Block> JOYFLAME_FIRE = BLOCKS.register("joyflame_fire", () -> new JoyflameFireBlock(Block.Properties.copy(Blocks.FIRE)));

	//big's trial
	public static final RegistryObject<GrowingPlantBodyBlock> NEURON_STEM = BLOCKS.register("neuron", () -> new NeuronStemBlock(Block.Properties.copy(Blocks.TWISTING_VINES_PLANT).offsetType(BlockBehaviour.OffsetType.XZ)));
//	public static final RegistryObject<Block> WEAVER_NEST = BLOCKS.register("weaver_nest", () -> new WeaverNestBlock(Block.Properties.copy(Blocks.SPAWNER)));
	public static final RegistryObject<GrowingPlantHeadBlock> NEURON_HEAD = BLOCKS.register("neuron_head", () -> new NeuronHeadBlock(Block.Properties.copy(Blocks.TWISTING_VINES).offsetType(BlockBehaviour.OffsetType.XZ)) {
	});
	public static final RegistryObject<MultifaceBlock> SPREADING_REMAINS = BLOCKS.register("spreading_remains", () -> new SpreadingRemainsBlock(Block.Properties.copy(Blocks.SCULK_VEIN)));
	public static final RegistryObject<MultifaceBlock> TEETH = BLOCKS.register("teeth", () -> new SpreadingRemainsBlock(Block.Properties.copy(Blocks.BONE_BLOCK).noOcclusion()));
	public static final RegistryObject<Block> DARKNESS = BLOCKS.register("darkness", () -> new DarknessBlock(Block.Properties.copy(Blocks.AIR).noOcclusion().sound(SoundType.SCULK)));
	public static final RegistryObject<Block> CURVED_RITUAL_BLOCK = BLOCKS.register("curved_ritual_block", () -> new ChiseledGrimstone(BlockBehaviour.Properties.copy(Blocks.BLACK_GLAZED_TERRACOTTA)));
	public static final RegistryObject<Block> LINEAR_RITUAL_BLOCK = BLOCKS.register("linear_ritual_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_GLAZED_TERRACOTTA)));

	public static final RegistryObject<LiquidBlock> BLOOD_FLUID_BLOCK = BLOCKS.register("blood_fluid_block", () -> new LiquidBlock(RisusFluids.SOURCE_BLOOD, BlockBehaviour.Properties.copy(Blocks.WATER)));

}
