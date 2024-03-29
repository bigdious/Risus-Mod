package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.RisusBoat;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.items.*;
import com.bigdious.risus.util.ModItemTier;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class RisusItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Risus.MODID);

	public static final FoodProperties GUILTY_FOOD = new FoodProperties.Builder().nutrition(10).saturationMod(0.2F).alwaysEat().effect(() -> new MobEffectInstance(RisusMobEffects.PLEASURE.get(), 90), 1.0F).build();
	public static final FoodProperties ORGANIC_FOOD = new FoodProperties.Builder().alwaysEat().saturationMod(0.1F).build();

	public static final RegistryObject<Item> SMILE = ITEMS.register("smile", () -> new Item(defaultNoTab()));

	public static final RegistryObject<Item> CRESCENT_DISASTER = ITEMS.register("crescent_disaster", () -> new ThrowableAxeItem(ModItemTier.CRESCENT, 5.0F, -3.0F, defaultWithRarity()));
	public static final RegistryObject<Item> GOLD_FIST = ITEMS.register("gold_fist", () -> new GoldFistItem(ModItemTier.CRESCENT, 0, -2.0F, defaultWithRarity()));
	public static final RegistryObject<Item> HAND_OF_GREED = ITEMS.register("hand_of_greed", () -> new HandOfGreedItem(defaultWithRarity()));
	public static final RegistryObject<Item> UNAWAKENED_VESSEL = ITEMS.register("unawakened_vessel", () -> new AxeItem(ModItemTier.CRESCENT, 5.0F, -3.0F, defaultWithRarity()));
	public static final RegistryObject<Item> TOOTHKNOCKER = ITEMS.register("toothknocker", () -> new ToothknockerItem(ModItemTier.CRESCENT, -2, 4.0F, defaultWithRarity()));

	public static final RegistryObject<Item> BONDKNOT_LOG = ITEMS.register("bondknot_log", () -> new BlockItem(RisusBlocks.BONDKNOT_LOG.get(), defaultWithRarity()));
	public static final RegistryObject<Item> STRIPPED_BONDKNOT_LOG = ITEMS.register("stripped_bondknot_log", () -> new BlockItem(RisusBlocks.STRIPPED_BONDKNOT_LOG.get(), defaultWithRarity()));
	public static final RegistryObject<Item> STRIPPED_BONDKNOT_WOOD = ITEMS.register("stripped_bondknot_wood", () -> new BlockItem(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_WOOD = ITEMS.register("bondknot_wood", () -> new BlockItem(RisusBlocks.BONDKNOT_WOOD.get(), defaultWithRarity()));
	public static final RegistryObject<Item> POPPING_BONDKNOT_LOG = ITEMS.register("popping_bondknot_log", () -> new BlockItem(RisusBlocks.POPPING_BONDKNOT_LOG.get(), defaultNoTab()));
	public static final RegistryObject<Item> POPPING_BONDKNOT_WOOD = ITEMS.register("popping_bondknot_wood", () -> new BlockItem(RisusBlocks.POPPING_BONDKNOT_WOOD.get(), defaultNoTab()));
	public static final RegistryObject<Item> BONDKNOT_PLANKS = ITEMS.register("bondknot_planks", () -> new BlockItem(RisusBlocks.BONDKNOT_PLANKS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_SLAB = ITEMS.register("bondknot_slab", () -> new BlockItem(RisusBlocks.BONDKNOT_SLAB.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_STAIRS = ITEMS.register("bondknot_stairs", () -> new BlockItem(RisusBlocks.BONDKNOT_STAIRS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_FENCE = ITEMS.register("bondknot_fence", () -> new BlockItem(RisusBlocks.BONDKNOT_FENCE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_FENCE_GATE = ITEMS.register("bondknot_fence_gate", () -> new BlockItem(RisusBlocks.BONDKNOT_FENCE_GATE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_PRESSURE_PLATE = ITEMS.register("bondknot_pressure_plate", () -> new BlockItem(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_BUTTON = ITEMS.register("bondknot_button", () -> new BlockItem(RisusBlocks.BONDKNOT_BUTTON.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_TRAPDOOR = ITEMS.register("bondknot_trapdoor", () -> new BlockItem(RisusBlocks.BONDKNOT_TRAPDOOR.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_DOOR = ITEMS.register("bondknot_door", () -> new BlockItem(RisusBlocks.BONDKNOT_DOOR.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONDKNOT_SIGN = ITEMS.register("bondknot_sign", () -> new SignItem(RisusItems.defaultWithRarity().stacksTo(16), RisusBlocks.BONDKNOT_SIGN.get(), RisusBlocks.BONDKNOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> BONDKNOT_HANGING_SIGN = ITEMS.register("bondknot_hanging_sign", () -> new HangingSignItem(RisusBlocks.BONDKNOT_HANGING_SIGN.get(), RisusBlocks.BONDKNOT_WALL_HANGING_SIGN.get(), RisusItems.defaultWithRarity().stacksTo(16)));
	public static final RegistryObject<Item> BONDKNOT_BOAT = ITEMS.register("bondknot_boat", () -> new RisusBoatItem(RisusBoat.Type.BONDKNOT, defaultWithRarity()));
	public static final RegistryObject<Item> RIBCAGE = ITEMS.register("ribcage", () -> new BlockItem(RisusBlocks.RIBCAGE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BABY_RIBCAGE = ITEMS.register("baby_ribcage", () -> new BlockItem(RisusBlocks.BABY_RIBCAGE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> CRYSTALLIZED_BONDS = ITEMS.register("crystallized_bonds", () -> new BlockItem(RisusBlocks.CRYSTALLIZED_BONDS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> HEART_TRANSPLANT = ITEMS.register("heart_transplant", () -> new BlockItem(RisusBlocks.HEART_TRANSPLANT.get(), defaultWithRarity()));
	public static final RegistryObject<Item> REGEN_ROSE = ITEMS.register("regen_rose", () -> new BlockItem(RisusBlocks.REGEN_ROSE.get(), defaultWithNoRarity()));
	public static final RegistryObject<Item> TEETH = ITEMS.register("teeth", () -> new BlockItem(RisusBlocks.TEETH.get(), defaultWithNoRarity()));

	public static final RegistryObject<Item> GRIMSTONE = ITEMS.register("grimstone", () -> new BlockItem(RisusBlocks.GRIMSTONE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GRIMSTONE_BRICKS = ITEMS.register("grimstone_bricks", () -> new BlockItem(RisusBlocks.GRIMSTONE_BRICKS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> CRACKED_GRIMSTONE_BRICKS = ITEMS.register("cracked_grimstone_bricks", () -> new BlockItem(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GRIMSTONE_SLAB = ITEMS.register("grimstone_slab", () -> new BlockItem(RisusBlocks.GRIMSTONE_SLAB.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GRIMSTONE_BRICKS_SLAB = ITEMS.register("grimstone_bricks_slab", () -> new BlockItem(RisusBlocks.GRIMSTONE_BRICKS_SLAB.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GRIMSTONE_STAIRS = ITEMS.register("grimstone_stairs", () -> new BlockItem(RisusBlocks.GRIMSTONE_STAIRS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GRIMSTONE_BRICKS_STAIRS = ITEMS.register("grimstone_bricks_stairs", () -> new BlockItem(RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GRIMSTONE_WALL = ITEMS.register("grimstone_wall", () -> new BlockItem(RisusBlocks.GRIMSTONE_WALL.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GRIMSTONE_BRICKS_WALL = ITEMS.register("grimstone_bricks_wall", () -> new BlockItem(RisusBlocks.GRIMSTONE_BRICKS_WALL.get(), defaultWithRarity()));
	public static final RegistryObject<Item> CHISELED_GRIMSTONE = ITEMS.register("chiseled_grimstone", () -> new BlockItem(RisusBlocks.CHISELED_GRIMSTONE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> POLISHED_GRIMSTONE = ITEMS.register("polished_grimstone", () -> new BlockItem(RisusBlocks.POLISHED_GRIMSTONE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> CURVED_RITUAL_BLOCK = ITEMS.register("curved_ritual_block", () -> new BlockItem(RisusBlocks.CURVED_RITUAL_BLOCK.get(), defaultWithRarity()));
	public static final RegistryObject<Item> LINEAR_RITUAL_BLOCK = ITEMS.register("linear_ritual_block", () -> new BlockItem(RisusBlocks.LINEAR_RITUAL_BLOCK.get(), defaultWithRarity()));
	public static final RegistryObject<Item> SCAB = ITEMS.register("scab", () -> new BlockItem(RisusBlocks.SCAB.get(), defaultWithRarity()));
	public static final RegistryObject<Item> COAGULATED_BLOOD_BLOCK = ITEMS.register("coagulated_blood_block", () -> new BlockItem(RisusBlocks.COAGULATED_BLOOD_BLOCK.get(), defaultWithRarity()));

	public static final RegistryObject<Item> JOYFLAME_TORCH = ITEMS.register("joyflame_torch", () -> new StandingAndWallBlockItem(RisusBlocks.JOYFLAME_TORCH.get(), RisusBlocks.JOYFLAME_WALL_TORCH.get(), defaultWithRarity(), Direction.DOWN));
	public static final RegistryObject<Item> JOYFLAME_LANTERN = ITEMS.register("joyflame_lantern", () -> new BlockItem(RisusBlocks.JOYFLAME_LANTERN.get(), defaultWithRarity()));
	public static final RegistryObject<Item> JOYFLAME_CAMPFIRE = ITEMS.register("joyflame_campfire", () -> new BlockItem(RisusBlocks.JOYFLAME_CAMPFIRE.get(), defaultWithRarity()));

	public static final RegistryObject<Item> TISSUE = ITEMS.register("tissue", () -> new BlockItem(RisusBlocks.TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> TISSUE_STAIRs = ITEMS.register("tissue_stairs", () -> new BlockItem(RisusBlocks.TISSUE_STAIRS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> TISSUE_SLAB = ITEMS.register("tissue_slab", () -> new BlockItem(RisusBlocks.TISSUE_SLAB.get(), defaultWithRarity()));
	public static final RegistryObject<Item> ROTTING_TISSUE = ITEMS.register("rotting_tissue", () -> new BlockItem(RisusBlocks.ROTTING_TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> DECOMPOSING_TISSUE = ITEMS.register("decomposing_tissue", () -> new BlockItem(RisusBlocks.DECOMPOSING_TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> DECAYING_TISSUE = ITEMS.register("decaying_tissue", () -> new BlockItem(RisusBlocks.DECAYING_TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONE_WALL = ITEMS.register("bone_wall", () -> new BlockItem(RisusBlocks.BONE_WALL.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONE_STAIRS = ITEMS.register("bone_stairs", () -> new BlockItem(RisusBlocks.BONE_STAIRS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BONE_SLAB = ITEMS.register("bone_slab", () -> new BlockItem(RisusBlocks.BONE_SLAB.get(), defaultWithRarity()));
	public static final RegistryObject<Item> LIVING_TISSUE = ITEMS.register("living_tissue", () -> new BlockItem(RisusBlocks.LIVING_TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> ROTTED_TISSUE = ITEMS.register("rotted_tissue", () -> new BlockItem(RisusBlocks.ROTTED_TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> DECOMPOSED_TISSUE = ITEMS.register("decomposed_tissue", () -> new BlockItem(RisusBlocks.DECOMPOSED_TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> DECAYED_TISSUE = ITEMS.register("decayed_tissue", () -> new BlockItem(RisusBlocks.DECAYED_TISSUE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> EYE_ENDER = ITEMS.register("eye_ender", () -> new BlockItem(RisusBlocks.EYE_ENDER.get(), defaultWithRarity()));
	public static final RegistryObject<Item> EYE_BLEACHED = ITEMS.register("eye_bleached", () -> new BlockItem(RisusBlocks.EYE_BLEACHED.get(), defaultWithRarity()));
	public static final RegistryObject<Item> EYE_GOLDEN = ITEMS.register("eye_golden", () -> new BlockItem(RisusBlocks.EYE_GOLDEN.get(), defaultWithRarity()));
	public static final RegistryObject<Item> EYE_BLOODSHOT = ITEMS.register("eye_bloodshot", () -> new BlockItem(RisusBlocks.EYE_BLOODSHOT.get(), defaultWithRarity()));
	public static final RegistryObject<Item> EYE_EMERALD = ITEMS.register("eye_emerald", () -> new BlockItem(RisusBlocks.EYE_EMERALD.get(), defaultWithRarity()));
	public static final RegistryObject<Item> FLESHY_SKIN = ITEMS.register("fleshy_skin", () -> new BlockItem(RisusBlocks.FLESHY_SKIN.get(), defaultWithRarity()));
	public static final RegistryObject<Item> SKIN = ITEMS.register("skin", () -> new BlockItem(RisusBlocks.SKIN.get(), defaultWithRarity()));
    public static final RegistryObject<Item> CURVED_FLESHY_SKIN = ITEMS.register("curved_fleshy_skin", () -> new BlockItem(RisusBlocks.CURVED_FLESHY_SKIN.get(), defaultWithRarity()));
	public static final RegistryObject<Item> HAIRY_FLESHY_SKIN = ITEMS.register("hairy_fleshy_skin", () -> new BlockItem(RisusBlocks.HAIRY_FLESHY_SKIN.get(), defaultWithRarity()));
	public static final RegistryObject<Item> HAIRY_SKIN = ITEMS.register("hairy_skin", () -> new BlockItem(RisusBlocks.HAIRY_SKIN.get(), defaultWithRarity()));
	public static final RegistryObject<Item> HAIRY_CURVED_FLESHY_SKIN = ITEMS.register("hairy_curved_fleshy_skin", () -> new BlockItem(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get(), defaultWithRarity()));

	public static final RegistryObject<Item> HAIR_FOLLICLES = ITEMS.register("hair_follicles", () -> new Item(defaultWithRarity()));
	public static final RegistryObject<Item> TALL_HAIR = ITEMS.register("tall_hair", () -> new BlockItem(RisusBlocks.TALL_HAIR.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BUNDLE_OF_HAIR = ITEMS.register("bundle_of_hair", () -> new BlockItem(RisusBlocks.BUNDLE_OF_HAIR.get(),defaultWithRarity()));
	public static final RegistryObject<Item> ASHEN_REMAINS = ITEMS.register("ashen_remains", () -> new BlockItem(RisusBlocks.ASHEN_REMAINS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> SMILING_REMAINS = ITEMS.register("smiling_remains", () -> new BlockItem(RisusBlocks.SMILING_REMAINS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> LAUGHING_OBSIDIAN = ITEMS.register("laughing_obsidian", () -> new BlockItem(RisusBlocks.LAUGHING_OBSIDIAN.get(), defaultWithRarity()));
	public static final RegistryObject<Item> ENGRAVED_BASALT = ITEMS.register("engraved_basalt", () -> new BlockItem(RisusBlocks.ENGRAVED_BASALT.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BLOODWEAVE = ITEMS.register("bloodweave", () -> new BlockItem(RisusBlocks.BLOODWEAVE.get(), defaultWithRarity()));
//	public static final RegistryObject<Item> WEAVER_NEST = ITEMS.register("weaver_nest", () -> new BlockItem(RisusBlocks.WEAVER_NEST.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BIG_CHAIN = ITEMS.register("big_chain", () -> new BlockItem(RisusBlocks.BIG_CHAIN.get(), defaultWithNoRarity()));
	public static final RegistryObject<Item> BURNT_HYPHAE = ITEMS.register("burnt_hyphae", () -> new BlockItem(RisusBlocks.BURNT_HYPHAE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BLOODWYRM_HEAD = ITEMS.register("bloodwyrm_head", () -> new StandingAndWallBlockItem(RisusBlocks.BLOODWYRM_HEAD.get(), RisusBlocks.BLOODWYRM_WALL_HEAD.get(), RisusItems.defaultWithRarity(), Direction.DOWN));
	public static final RegistryObject<Item> BLOODWYRM_HEAD_WEAPON = ITEMS.register("bloodwyrm_head_weapon", () -> new BloodwyrmHeadItem(RisusItems.defaultWithRarity().durability(500)));
	public static final RegistryObject<Item> ZIT = ITEMS.register("zit", () -> new BlockItem(RisusBlocks.ZIT.get(), defaultWithRarity()));

	public static final RegistryObject<Item> MAW_GUTS = ITEMS.register("maw_guts", () -> new BlockItem(RisusBlocks.MAW_GUTS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GLUTTONY_SCALEPLATE = ITEMS.register("gluttony_scaleplate", () -> new BlockItem(RisusBlocks.GLUTTONY_SCALEPLATE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> IMITATION_SCALEPLATE = ITEMS.register("imitation_scaleplate", () -> new BlockItem(RisusBlocks.IMITATION_SCALEPLATE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> FLOWERING_IMITATION_SCALEPLATE = ITEMS.register("flowering_imitation_scaleplate", () -> new BlockItem(RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BUDDING_IMITATION_SCALEPLATE = ITEMS.register("budding_imitation_scaleplate", () -> new BlockItem(RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> FLATTENED_SCALES_BLOCK = ITEMS.register("flattened_scales_block", () -> new BlockItem(RisusBlocks.FLATTENED_SCALES_BLOCK.get(), defaultWithRarity()));
	public static final RegistryObject<Item> FLATTENED_IMITATION_SCALES_BLOCK = ITEMS.register("flattened_imitation_scales_block", () -> new BlockItem(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(), defaultWithRarity()));
	public static final RegistryObject<Item> GLUTTONY_SCALES = ITEMS.register("gluttony_scales", () -> new Item(defaultWithRarity()));

	public static final RegistryObject<Item> MIRAGE_SAND = ITEMS.register("mirage_sand", () -> new BlockItem(RisusBlocks.MIRAGE_SAND.get(), defaultWithRarity()));
	public static final RegistryObject<Item> MIRAGE_GRASS_BLOCK = ITEMS.register("mirage_grass_block", () -> new BlockItem(RisusBlocks.MIRAGE_GRASS_BLOCK.get(), defaultWithRarity()));
	public static final RegistryObject<Item> MIRAGE_NETHERRACK = ITEMS.register("mirage_netherrack", () -> new BlockItem(RisusBlocks.MIRAGE_NETHERRACK.get(), defaultWithRarity()));
	public static final RegistryObject<Item> MIRAGE_END_STONE = ITEMS.register("mirage_end_stone", () -> new BlockItem(RisusBlocks.MIRAGE_END_STONE.get(), defaultWithRarity()));

	public static final RegistryObject<Item> ALTERATION_CATALYST = ITEMS.register("alteration_catalyst", () -> new BlockItem(RisusBlocks.ALTERATION_CATALYST.get(), defaultWithRarity()));
	public static final RegistryObject<Item> ANGEL_ALTAR = ITEMS.register("angel_altar", () -> new BlockItem(RisusBlocks.ANGEL_ALTAR.get(), defaultWithRarity()));
	public static final RegistryObject<Item> DEPTH_VASE = ITEMS.register("depth_vase", () -> new BlockItem(RisusBlocks.DEPTH_VASE.get(), defaultWithRarity()));
	public static final RegistryObject<Item> BLOOD_FEATHER = ITEMS.register("blood_feather", () -> new Item(defaultWithRarity()));
	public static final RegistryObject<Item> CONCENTRATION_CORE = ITEMS.register("concentration_core", () -> new Item(defaultWithRarity()));
	public static final RegistryObject<Item> CRYSTALLIZED_BOND = ITEMS.register("crystallized_bond", () -> new Item(defaultWithRarity()));
	public static final RegistryObject<Item> GUILTY_APPLE = ITEMS.register("guilty_apple", () -> new Item(defaultWithRarity().food(GUILTY_FOOD)));
	public static final RegistryObject<Item> MEMORY_CORE = ITEMS.register("memory_core", () -> new Item(defaultWithRarity()));
	public static final RegistryObject<Item> ORGANIC_MATTER = ITEMS.register("organic_matter", () -> new OrganicMatterItem(defaultWithRarity().food(ORGANIC_FOOD)));
	public static final RegistryObject<Item> NEURON_STEM = ITEMS.register("neuron", () -> new BlockItem(RisusBlocks.NEURON_HEAD.get(), defaultWithRarity()));

	public static final RegistryObject<Item> MEMORY1_ITEM = ITEMS.register("memory1_item", () -> new Memory1Item(defaultWithNoRarity()));
	public static final RegistryObject<Item> SPREADING_REMAINS = ITEMS.register("spreading_remains", () -> new BlockItem(RisusBlocks.SPREADING_REMAINS.get(), defaultWithRarity()));

	public static final RegistryObject<Item> LIGHT_DEVOURER = ITEMS.register("light_devourer", () -> new LightDevourerItem(RisusBlocks.DARKNESS.get(), defaultWithRarity()));
	public static final RegistryObject<Item> ENDLESS_PEARL = ITEMS.register("endless_pearl", () -> new EndlessPearlItem(defaultWithRarity().durability(10000)));

	public static final RegistryObject<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket", () -> new BucketItem(RisusFluids.SOURCE_BLOOD,defaultWithRarity().stacksTo(1).craftRemainder(Items.BUCKET)));
	public static Item.Properties defaultWithRarity() {
		return new Item.Properties().rarity(Risus.getRarity());
	}

	public static Item.Properties defaultNoTab() {
		return new Item.Properties().rarity(Risus.getRarity());
	}

	public static Item.Properties defaultWithNoRarity() {
		return new Item.Properties();
	}
}

