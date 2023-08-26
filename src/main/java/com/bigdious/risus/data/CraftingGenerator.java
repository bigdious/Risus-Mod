package com.bigdious.risus.data;

import com.bigdious.risus.data.custom.AlterationRecipeBuilder;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.StrictNBTIngredient;

import java.util.Properties;
import java.util.function.Consumer;

public class CraftingGenerator extends RecipeProvider {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

		AlterationRecipeBuilder.alteration(Ingredient.of(Items.NETHERRACK), RisusItems.SMILING_REMAINS.get()).unlockedBy("has_stone", has(Items.STONE)).unlockedBy("has_cobble", has(Items.COBBLESTONE)).unlockedBy("has_netherrack", has(Items.NETHERRACK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.SOUL_SAND, Items.SOUL_SOIL), RisusItems.ASHEN_REMAINS.get()).unlockedBy("has_sand", has(Items.SAND)).unlockedBy("has_soul_sand", has(Items.SOUL_SAND)).unlockedBy("has_soul_soil", has(Items.SOUL_SOIL)).unlockedBy("has_dirt", has(Items.DIRT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.VINE), RisusItems.SPREADING_REMAINS.get()).unlockedBy("has_vine", has(Items.VINE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.TWISTING_VINES), RisusItems.NEURON_STEM.get()).unlockedBy("has_twisting_vine", has(Items.TWISTING_VINES)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GOLDEN_APPLE), RisusItems.GUILTY_APPLE.get()).unlockedBy("has_apple", has(Items.GOLDEN_APPLE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.COBWEB), RisusItems.BLOODWEAVE.get()).unlockedBy("has_cobweb", has(Items.COBWEB)).save(consumer);
		//bookshelf to cultic shelf when implemented
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BASALT, Items.POLISHED_BASALT, Items.SMOOTH_BASALT), RisusItems.ENGRAVED_BASALT.get()).unlockedBy("has_basalt", has(Items.BASALT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DRAGON_HEAD), RisusItems.BLOODWYRM_HEAD.get()).unlockedBy("has_dragon_head", has(Items.DRAGON_HEAD)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRYING_OBSIDIAN), RisusItems.LAUGHING_OBSIDIAN.get()).unlockedBy("has_crying_obsidian", has(Items.CRYING_OBSIDIAN)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ROTTEN_FLESH), RisusItems.ORGANIC_MATTER.get()).unlockedBy("has_flesh", has(Items.ROTTEN_FLESH)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE), RisusItems.GRIMSTONE.get()).unlockedBy("has_blackstone", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE), RisusItems.POLISHED_GRIMSTONE.get()).unlockedBy("has_blackstone", has(Items.POLISHED_BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICKS), RisusItems.GRIMSTONE_BRICKS.get()).unlockedBy("has_blackstone", has(Items.POLISHED_BLACKSTONE_BRICKS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS), RisusItems.CRACKED_GRIMSTONE_BRICKS.get()).unlockedBy("has_blackstone", has(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CHISELED_POLISHED_BLACKSTONE), RisusItems.CHISELED_GRIMSTONE.get()).unlockedBy("has_blackstone", has(Items.CHISELED_POLISHED_BLACKSTONE)).save(consumer);
		//TODO enchanted book to corrupt book handler
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.PORKCHOP, Items.BEEF, Items.MUTTON, Items.RABBIT), RisusItems.TISSUE.get()).unlockedBy("has_pork", has(Items.PORKCHOP)).unlockedBy("has_beef", has(Items.BEEF)).unlockedBy("has_mutton", has(Items.MUTTON)).unlockedBy("has_rabbit", has(Items.RABBIT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRIMSON_HYPHAE, Items.CRIMSON_STEM, Items.WARPED_HYPHAE, Items.WARPED_STEM), RisusItems.BURNT_HYPHAE.get()).unlockedBy("has_chyphae", has(Items.CRIMSON_HYPHAE)).unlockedBy("has_cstem", has(Items.CRIMSON_STEM)).unlockedBy("has_whyphae", has(Items.WARPED_HYPHAE)).unlockedBy("has_wstem", has(Items.WARPED_STEM)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.WITHER_ROSE), RisusItems.REGEN_ROSE.get()).unlockedBy("has_wither_rose", has(Items.WITHER_ROSE)).save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_WOOD.get(), 3)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_LOG.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_LOG.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), 3)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.unlockedBy("has_item", has(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RisusBlocks.BONDKNOT_PLANKS.get(), 4)
				.requires(Ingredient.of(ItemTagGenerator.BONDKNOT_LOGS))
				.unlockedBy("has_item", has(ItemTagGenerator.BONDKNOT_LOGS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_STAIRS.get(), 8)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_SLAB.get(), 6)
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RisusBlocks.BONDKNOT_BUTTON.get())
				.requires(Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_FENCE.get(), 6)
				.pattern("#/#")
				.pattern("#/#")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_FENCE_GATE.get())
				.pattern("/#/")
				.pattern("/#/")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get())
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_DOOR.get(), 3)
				.pattern("##")
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_TRAPDOOR.get(), 6)
				.pattern("###")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_SIGN.get().asItem(), 3)
				.pattern("###")
				.pattern("###")
				.pattern(" / ")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_BRICKS.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_STAIRS.get(), 8)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_SLAB.get(), 6)
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_WALL.get(), 6)
				.pattern("###")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.CHISELED_GRIMSTONE.get())
				.pattern(" # ")
				.pattern(" # ")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_SLAB.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_SLAB.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.POLISHED_GRIMSTONE.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusItems.UNAWAKENED_VESSEL.get())
				.pattern(" BG")
				.pattern("BGC")
				.pattern("GLL")
				.define('B', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
				.define('C', Ingredient.of(RisusItems.CONCENTRATION_CORE.get()))
				.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('L', Ingredient.of(ItemTags.LOGS))
				.unlockedBy("has_core", has(RisusItems.CONCENTRATION_CORE.get()))
				.unlockedBy("has_bonds", has(RisusItems.CRYSTALLIZED_BOND.get()))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RisusItems.CRESCENT_DISASTER.get())
				.requires(RisusItems.UNAWAKENED_VESSEL.get())
				.requires(RisusItems.BLOOD_FEATHER.get())
				.unlockedBy("has_feather", has(RisusItems.BLOOD_FEATHER.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusItems.CONCENTRATION_CORE.get())
				.pattern("BGB")
				.pattern("GMG")
				.pattern("BGB")
				.define('G', Ingredient.of(RisusItems.NEURON_STEM.get()))
				.define('M', Ingredient.of(RisusItems.MEMORY_CORE.get()))
				.define('B', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_core", has(RisusItems.MEMORY_CORE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusItems.LIGHT_DEVOURER.get())
				.pattern("FBS")
				.pattern("BPB")
				.pattern("GBL")
				.define('F', Ingredient.of(Items.OCHRE_FROGLIGHT))
				.define('P', Ingredient.of(Items.ENDER_PEARL))
				.define('B', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('S', Ingredient.of(Items.GLOWSTONE))
				.define('L', Ingredient.of(Items.SHROOMLIGHT))
				.define('G', Ingredient.of(Items.SEA_LANTERN))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);


		ShapedRecipeBuilder.shaped(RisusBlocks.BIG_CHAIN.get(), 8)
				.pattern("I I")
				.pattern("III")
				.pattern("I I")
				.define('I', Ingredient.of(Items.IRON_INGOT))
				.unlockedBy("has_item", has(Items.IRON_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.JOYFLAME_CAMPFIRE.get())
				.pattern(" S ")
				.pattern("S#S")
				.pattern("LLL")
				.define('L', ItemTags.LOGS)
				.define('S', Items.STICK)
				.define('#', ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS)
				.unlockedBy("has_stick", has(Items.STICK))
				.unlockedBy("has_remains", has(ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.JOYFLAME_TORCH.get(), 4)
				.pattern("X")
				.pattern("#")
				.pattern("S")
				.define('X', Ingredient.of(Items.COAL, Items.CHARCOAL))
				.define('#', Items.STICK)
				.define('S', ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS)
				.unlockedBy("has_remains", has(ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.JOYFLAME_LANTERN.get())
				.pattern("XXX")
				.pattern("X#X")
				.pattern("XXX")
				.define('#', RisusItems.JOYFLAME_TORCH.get())
				.define('X', Items.IRON_NUGGET)
				.unlockedBy("has_soul_torch", has(RisusItems.JOYFLAME_TORCH.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONE_FENCE.get(), 3)
				.pattern("B/B")
				.pattern("B/B")
				.define('B', Items.BONE_BLOCK)
				.define('/', Items.BONE)
				.unlockedBy("has_bone", has(Items.BONE_BLOCK))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONE_WALL.get(), 6)
				.pattern("BBB")
				.pattern("BBB")
				.define('B', Items.BONE_BLOCK)
				.unlockedBy("has_bone", has(Items.BONE_BLOCK))
				.save(consumer);
	}
}
