package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.RisusBoat;
import com.bigdious.risus.items.*;
import com.bigdious.risus.items.armor.AngelWingsItem;
import com.bigdious.risus.items.armor.RisusArmorItem;
import com.bigdious.risus.items.summoners.EggSacItem;
import com.bigdious.risus.items.summoners.LitterItem;
import com.bigdious.risus.items.summoners.Memory1Item;
import com.bigdious.risus.items.summoners.RisusBoatItem;
import com.bigdious.risus.items.weapons.*;
import com.bigdious.risus.util.RisusToolMaterials;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RisusItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Risus.MODID);
	public static final Rarity BLOOD = Rarity.valueOf("RISUS_BLOOD");

	//TOOLS AND SHIT
	public static final DeferredItem<Item> RESEARCHERS_NOTES = register("researchers_notes", RisusBookItem::new, () -> new Item.Properties().rarity(BLOOD).stacksTo(1));
	public static final DeferredItem<Item> CRESCENT_DISASTER = register("crescent_disaster", properties -> new ThrowableAxeItem(RisusToolMaterials.GLUTTONY, properties), () -> new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(RisusToolMaterials.GLUTTONY, 8, -3F)).rarity(BLOOD));
	public static final DeferredItem<Item> GOLD_FIST = register("gold_fist", properties -> new GoldFistItem(RisusToolMaterials.GLUTTONY, properties), () -> new Item.Properties().fireResistant().attributes(GoldFistItem.createKnuckleAttributes(RisusToolMaterials.GLUTTONY, 3, 4F)).rarity(BLOOD));
	public static final DeferredItem<Item> BOOMSTICK = register("boomstick", BoomstickItem::new, () -> new Item.Properties().fireResistant().rarity(BLOOD).durability(64));
	public static final DeferredItem<Item> HAND_OF_GREED = register("hand_of_greed", properties -> new HandOfGreedItem(RisusToolMaterials.GLUTTONY, properties), () -> new Item.Properties().fireResistant().attributes(HandOfGreedItem.createHandOfGreedAttributes()).rarity(BLOOD));
	public static final DeferredItem<Item> UNAWAKENED_VESSEL = register("unawakened_vessel", properties -> new UnThrowableAxeItem(RisusToolMaterials.GLUTTONY, properties), () -> new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(RisusToolMaterials.GLUTTONY, 10, -3F)).rarity(BLOOD));
	public static final DeferredItem<Item> TOOTHKNOCKER = register("toothknocker", properties -> new ToothknockerItem(RisusToolMaterials.GLUTTONY, properties), () -> new Item.Properties().fireResistant().attributes(ToothknockerItem.createKnuckleAttributes(RisusToolMaterials.GLUTTONY, 2, 4F)).rarity(BLOOD));
	public static final DeferredItem<Item> SCYTHE = register("scythe", properties -> new ScytheItem(RisusToolMaterials.GLUTTONY, RisusTags.Enchantments.SCYTHE_ALLOWED_ENCHANTS, properties), () -> new Item.Properties().fireResistant().attributes(ScytheItem.createScytheAttributes(RisusToolMaterials.GLUTTONY, 10, -3.4F)).rarity(BLOOD));
	public static final DeferredItem<Item> FIRE_SCYTHE = register("fire_scythe", properties -> new ScytheItem(RisusToolMaterials.GLUTTONY, RisusTags.Enchantments.FIRE_SCYTHE_ALLOWED_ENCHANTS, properties), () -> new Item.Properties().fireResistant().attributes(ScytheItem.createScytheAttributes(RisusToolMaterials.GLUTTONY, 7, -3.4F)).rarity(BLOOD));
	public static final DeferredItem<Item> SOUL_SCYTHE = register("soul_scythe", properties -> new ScytheItem(RisusToolMaterials.GLUTTONY, RisusTags.Enchantments.SOUL_SCYTHE_ALLOWED_ENCHANTS, properties), () -> new Item.Properties().fireResistant().attributes(ScytheItem.createScytheAttributes(RisusToolMaterials.GLUTTONY, 7, -3.4F)).rarity(BLOOD));
	public static final DeferredItem<Item> CINDERGLEE_SCYTHE = register("cinderglee_scythe", properties -> new ScytheItem(RisusToolMaterials.GLUTTONY, RisusTags.Enchantments.CINDERGLEE_SCYTHE_ALLOWED_ENCHANTS, properties), () -> new Item.Properties().fireResistant().attributes(ScytheItem.createScytheAttributes(RisusToolMaterials.GLUTTONY, 7, -3.4F)).rarity(BLOOD));
	public static final DeferredItem<Item> THOUSAND_BLADE = register("thousand_blade", properties -> new ThousandBladeItem(RisusToolMaterials.GLUTTONY, properties), () -> new Item.Properties().fireResistant().attributes(ThousandBladeItem.createThousandBladeAttributes(RisusToolMaterials.GLUTTONY, 14, -3.6F)).rarity(BLOOD));
	public static final DeferredItem<Item> BLOOD_BUCKET = register("blood_bucket", properties -> new BucketItem(RisusFluids.SOURCE_BLOOD.get(), properties), () -> new Item.Properties().rarity(BLOOD).stacksTo(1).craftRemainder(Items.BUCKET));
	public static final DeferredItem<Item> LIGHT_DEVOURER = register("light_devourer", LightDevourerItem::new, () -> new Item.Properties().fireResistant().rarity(BLOOD));
	public static final DeferredItem<Item> ENDLESS_PEARL = register("endless_pearl", EndlessPearlItem::new, () -> new Item.Properties().fireResistant().rarity(BLOOD).durability(10000));
	public static final DeferredItem<Item> BLOODWYRM_HEAD_WEAPON = register("bloodwyrm_head_weapon", BloodwyrmHeadItem::new, () -> new Item.Properties().fireResistant().rarity(BLOOD).durability(1000));
	public static final DeferredItem<Item> ANGEL_WINGS = register("angel_wings", AngelWingsItem::new, () -> new Item.Properties().fireResistant().rarity(BLOOD).durability(666));
	public static final DeferredItem<Item> SACRIFICE_CATALYST = register("sacrifice_catalyst", Item::new, () -> new Item.Properties().fireResistant().rarity(BLOOD).durability(1000));
	public static final DeferredItem<Item> LITTER = register("litter", LitterItem::new, () -> new Item.Properties().rarity(BLOOD));

	//ARMORS
	public static final DeferredItem<ArmorItem> SKIN_HELMET = register("skin_helmet", properties -> new RisusArmorItem(RisusArmorMaterials.SKIN, ArmorItem.Type.HELMET, properties), () -> new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(12)).attributes(RisusArmorItem.createSkinAttributes(ArmorItem.Type.HELMET, 1)).rarity(BLOOD));
	public static final DeferredItem<ArmorItem> SKIN_CHESTPLATE = register("skin_chestplate", properties -> new RisusArmorItem(RisusArmorMaterials.SKIN, ArmorItem.Type.CHESTPLATE, properties), () -> new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(12)).attributes(RisusArmorItem.createSkinAttributes(ArmorItem.Type.CHESTPLATE, 1)).rarity(BLOOD));
	public static final DeferredItem<ArmorItem> SKIN_LEGGINGS = register("skin_leggings", properties -> new RisusArmorItem(RisusArmorMaterials.SKIN, ArmorItem.Type.LEGGINGS, properties), () -> new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(12)).attributes(RisusArmorItem.createSkinAttributes(ArmorItem.Type.LEGGINGS, 1)).rarity(BLOOD));
	public static final DeferredItem<ArmorItem> SKIN_BOOTS = register("skin_boots", properties -> new RisusArmorItem(RisusArmorMaterials.SKIN, ArmorItem.Type.BOOTS, properties), () -> new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(12)).attributes(RisusArmorItem.createSkinAttributes(ArmorItem.Type.BOOTS, 1)).rarity(BLOOD));
	public static final DeferredItem<ArmorItem> THREADERS_OF_THE_FIRMAMENT = register("threaders_of_the_firmament", properties -> new RisusArmorItem(RisusArmorMaterials.BLOOD_FEATHER, ArmorItem.Type.BOOTS, properties), () -> new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(12)).attributes(RisusArmorItem.createBloodFeatherAttributes(ArmorItem.Type.BOOTS, 1)).rarity(BLOOD));

	//CONSUMABLES
	public static final FoodProperties GUILTY_FOOD = new FoodProperties.Builder().nutrition(6).saturationModifier(0.2F).alwaysEdible().effect(() -> new MobEffectInstance(RisusMobEffects.PLEASURE, 45), 1.0F).build();
	public static final FoodProperties ORGANIC_FOOD = new FoodProperties.Builder().alwaysEdible().saturationModifier(0.1F).fast().build();
	public static final FoodProperties EYE_FOOD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5F).fast().effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 7200), 1.0F).build();
	public static final FoodProperties EYE_SANDWICH_FOOD = new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 4800), 1.0F).build();
	public static final DeferredItem<Item> GUILTY_APPLE = register("guilty_apple", Item::new, () -> new Item.Properties().rarity(BLOOD).food(GUILTY_FOOD));
	public static final DeferredItem<Item> ORGANIC_MATTER = register("organic_matter", OrganicMatterItem::new, () -> new Item.Properties().rarity(BLOOD).food(ORGANIC_FOOD));
	public static final DeferredItem<Item> STALKER_EYE = register("stalker_eye", Item::new, () -> new Item.Properties().rarity(BLOOD).food(EYE_FOOD));
	public static final DeferredItem<Item> EYE_SANDWICH = register("eye_sandwich", Item::new, () -> new Item.Properties().rarity(BLOOD).food(EYE_SANDWICH_FOOD));
	public static final DeferredItem<Item> EGG_SAC = register("egg_sac", EggSacItem::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> TOTEM_OF_UNYIELDING = register("totem_of_unyielding", Item::new, () -> new Item.Properties().rarity(BLOOD).stacksTo(1));

	//SUMMONERS
	public static final DeferredItem<Item> BONDKNOT_BOAT = register("bondknot_boat", properties -> new RisusBoatItem(false, RisusBoat.Type.BONDKNOT, properties), () -> new Item.Properties().rarity(BLOOD).stacksTo(1));
	public static final DeferredItem<Item> GUTS_BOAT = register("guts_boat", properties -> new RisusBoatItem(true, RisusBoat.Type.BONDKNOT, properties), () -> new Item.Properties().rarity(BLOOD).stacksTo(1));
	public static final DeferredItem<Item> MEMORY1_ITEM = register("memory1_item", Memory1Item::new, Item.Properties::new);

	public static final DeferredItem<Item> BONDKNOT_DOOR = register("bondknot_door", properties -> new DoubleHighBlockItem(RisusBlocks.BONDKNOT_DOOR.get(), properties), () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> BONDKNOT_SIGN = register("bondknot_sign", properties -> new SignItem(properties, RisusBlocks.BONDKNOT_SIGN.get(), RisusBlocks.BONDKNOT_WALL_SIGN.get()), () -> new Item.Properties().rarity(BLOOD).stacksTo(16));
	public static final DeferredItem<Item> BONDKNOT_HANGING_SIGN = register("bondknot_hanging_sign", properties -> new HangingSignItem(RisusBlocks.BONDKNOT_HANGING_SIGN.get(), RisusBlocks.BONDKNOT_WALL_HANGING_SIGN.get(), properties), () -> new Item.Properties().rarity(BLOOD).stacksTo(16));
	public static final DeferredItem<Item> JOYFLAME_TORCH = register("joyflame_torch", properties -> new StandingAndWallBlockItem(RisusBlocks.JOYFLAME_TORCH.get(), RisusBlocks.JOYFLAME_WALL_TORCH.get(), properties, Direction.DOWN), () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> BLOODWYRM_HEAD = register("bloodwyrm_head", properties -> new StandingAndWallBlockItem(RisusBlocks.BLOODWYRM_HEAD.get(), RisusBlocks.BLOODWYRM_WALL_HEAD.get(), properties, Direction.DOWN), () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> CRYSTALLIZED_BOND = register("crystallized_bond", CrystallizedBondItem::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> VEINS = register("veins", properties -> new BlockItem(RisusBlocks.VEINS_END.get(), properties), () -> new Item.Properties().rarity(BLOOD));

	//LEFTOVER CRAFTING MATERIALS
	public static final DeferredItem<Item> BLOOD_FEATHER = register("blood_feather", BloodFeatherItem::new, () -> new Item.Properties().fireResistant().attributes(BloodFeatherItem.createBloodFeatherAttributes()).rarity(BLOOD));
	public static final DeferredItem<Item> CONCENTRATION_CORE = register("concentration_core", ConcentrationCoreItem::new, () -> new Item.Properties().fireResistant().rarity(BLOOD));
	public static final DeferredItem<Item> MEMORY_CORE = register("memory_core", ExperienceItem::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> HAIR_FOLLICLES = register("hair_follicles", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> GLUTTONY_SCALES = register("gluttony_scales", GluttonyScalesItem::new, () -> new Item.Properties().fireResistant().rarity(BLOOD));

	//COLLECTABLES
	public static final DeferredItem<Item> SMILE_PATTERN = register("smile_banner_pattern", properties -> new BannerPatternItem(RisusTags.BannerPatterns.SMILE_PATTERN, properties), () -> new Item.Properties().rarity(BLOOD).stacksTo(1));
	public static final DeferredItem<Item> DIVINITY_PATTERN = register("divinity_banner_pattern", properties -> new BannerPatternItem(RisusTags.BannerPatterns.DIVINITY_PATTERN, properties), () -> new Item.Properties().rarity(BLOOD).stacksTo(1));
	public static final DeferredItem<Item> TREE_PATTERN = register("tree_banner_pattern", properties -> new BannerPatternItem(RisusTags.BannerPatterns.TREE_PATTERN, properties), () -> new Item.Properties().rarity(BLOOD).stacksTo(1));
	public static final DeferredItem<Item> MUSIC_DISC_RAK = register("music_disc_rak", Item::new, () -> new Item.Properties().rarity(BLOOD).stacksTo(1).jukeboxPlayable(RisusJukeboxSongs.RAK));
	public static final DeferredItem<Item> MUSIC_DISC_FEIGR = register("music_disc_feigr", Item::new, () -> new Item.Properties().rarity(BLOOD).stacksTo(1).jukeboxPlayable(RisusJukeboxSongs.FEIGR));
	public static final DeferredItem<Item> MUSIC_DISC_MORK = register("music_disc_mork", Item::new, () -> new Item.Properties().rarity(BLOOD).stacksTo(1).jukeboxPlayable(RisusJukeboxSongs.MORK));
	public static final DeferredItem<Item> MUSIC_DISC_REGN = register("music_disc_regn", Item::new, () -> new Item.Properties().rarity(BLOOD).stacksTo(1).jukeboxPlayable(RisusJukeboxSongs.REGN));

	//DISPLAY ONLY ITEMS

	public static final DeferredItem<Item> SMILE = register("smile", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> ESSENCE_OF_GLUTTONY = register("essence_of_gluttony", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> ESSENCE_OF_GREED = register("essence_of_greed", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> ESSENCE_OF_SLOTH = register("essence_of_sloth", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> ESSENCE_OF_LUST = register("essence_of_lust", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> ESSENCE_OF_MELANCHOLY = register("essence_of_melancholy", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> EMBODIMENT_OF_INTIMACY = register("embodiment_of_intimacy", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> EMBODIMENT_OF_DEVOTION = register("embodiment_of_devotion", Item::new, () -> new Item.Properties().rarity(BLOOD));
	public static final DeferredItem<Item> EMBODIMENT_OF_COURTSHIP = register("embodiment_of_courtship", Item::new, () -> new Item.Properties().rarity(BLOOD));

	public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
		return ITEMS.register(name, () -> item.apply(properties.get()));
	}
}

