package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ItemModelGenerator extends ItemModelProvider {

	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//blocks
		toBlock(RisusBlocks.ALTERATION_CATALYST.get());
		toBlock(RisusBlocks.ASHEN_REMAINS.get());
		toBlock(RisusBlocks.SMILING_REMAINS.get());
		toBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());
		toBlock(RisusBlocks.ENGRAVED_BASALT.get());
		toBlock(RisusBlocks.MAW_GUTS.get());
		toBlock(RisusBlocks.GLUTTONY_SCALEPLATE.get());
		toBlock(RisusBlocks.CRYSTALLIZED_BONDS.get());
		toBlock(RisusBlocks.MIRAGE_GRASS_BLOCK.get());
		toBlock(RisusBlocks.MIRAGE_SAND.get());
		toBlock(RisusBlocks.MIRAGE_NETHERRACK.get());
		toBlock(RisusBlocks.MIRAGE_END_STONE.get());
		toBlockModel(RisusBlocks.BLOODWEAVE.get(), texture("block/bloodweave_core"));
		toBlock(RisusBlocks.BONDKNOT_LOG.get());
		toBlock(RisusBlocks.BONDKNOT_WOOD.get());
		toBlockModel(RisusBlocks.POPPING_BONDKNOT_LOG.get(), texture("block/popping_bondknot_log_north"));
		toBlockModel(RisusBlocks.POPPING_BONDKNOT_WOOD.get(), texture("block/popping_bondknot_wood_north"));
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
		toBlock(RisusBlocks.BONDKNOT_PLANKS.get());
		toBlock(RisusBlocks.BONDKNOT_SLAB.get());
		toBlock(RisusBlocks.BONDKNOT_STAIRS.get());
		toBlock(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		toBlock(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
		getBuilder(RisusBlocks.BONDKNOT_FENCE.getId().getPath())
				.parent(getExistingFile(new ResourceLocation("block/fence_inventory")))
				.texture("texture", texture("block/bondknot_planks"));
		getBuilder(RisusBlocks.BONDKNOT_BUTTON.getId().getPath())
				.parent(getExistingFile(new ResourceLocation("block/button_inventory")))
				.texture("texture", texture("block/bondknot_planks"));
		toBlockModel(RisusBlocks.BONDKNOT_TRAPDOOR.get(), texture("block/bondknot_trapdoor_bottom"));
		generated(RisusBlocks.BONDKNOT_DOOR.getId().getPath(), texture("items/bondknot_door"));
		generated(RisusBlocks.BONDKNOT_SIGN.getId().getPath(), texture("items/bondknot_sign"));
		toBlock(RisusBlocks.BABY_RIBCAGE.get());
		toBlock(RisusBlocks.RIBCAGE.get());
		toBlock(RisusBlocks.GRIMSTONE.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.GRIMSTONE_SLAB.get());
		toBlock(RisusBlocks.GRIMSTONE_STAIRS.get());
		getBuilder(RisusBlocks.GRIMSTONE_WALL.getId().getPath())
				.parent(getExistingFile(new ResourceLocation("block/wall_inventory")))
				.texture("wall", texture("block/grimstone_bricks"));
		toBlock(RisusBlocks.CHISELED_GRIMSTONE.get());
		toBlock(RisusBlocks.POLISHED_GRIMSTONE.get());
		toBlock(RisusBlocks.BURNT_HYPHAE.get());
		toBlock(RisusBlocks.ZIT.get());
		toBlock(RisusBlocks.TISSUE.get());
		toBlockModel(RisusBlocks.ROTTING_TISSUE.get(), Risus.prefix("block/tissue/rotting_tissue_post"));
		toBlockModel(RisusBlocks.DECOMPOSING_TISSUE.get(), Risus.prefix("block/tissue/decomposing_tissue_post"));
		toBlockModel(RisusBlocks.DECAYING_TISSUE.get(), Risus.prefix("block/tissue/decaying_tissue_post"));
		toBlock(RisusBlocks.LIVING_TISSUE.get());
		toBlockModel(RisusBlocks.ROTTED_TISSUE.get(), Risus.prefix("block/tissue/rotting_tissue_post"));
		toBlockModel(RisusBlocks.DECOMPOSED_TISSUE.get(), Risus.prefix("block/tissue/decomposing_tissue_post"));
		toBlockModel(RisusBlocks.DECAYED_TISSUE.get(), Risus.prefix("block/tissue/decaying_tissue_post"));
		getBuilder(RisusBlocks.BONE_FENCE.getId().getPath())
				.parent(getExistingFile(new ResourceLocation("block/fence_inventory")))
				.texture("texture", new ResourceLocation("block/bone_block_side"));
		getBuilder(RisusBlocks.BONE_WALL.getId().getPath())
				.parent(getExistingFile(new ResourceLocation("block/wall_inventory")))
				.texture("wall", new ResourceLocation("block/bone_block_side"));

		//items
		singleTex(RisusItems.BLOOD_FEATHER);
		singleTex(RisusItems.BONDKNOT_BOAT);
		singleTex(RisusItems.CONCENTRATION_CORE);
		singleTex(RisusItems.CRYSTALLIZED_BOND);
		singleTex(RisusItems.GLUTTONY_SCALES);
		singleTex(RisusItems.GUILTY_APPLE);
		singleTex(RisusItems.MEMORY_CORE);
		singleTex(RisusItems.ORGANIC_MATTER);
		singleTexTool(RisusItems.CRESCENT_DISASTER);
		ItemModelBuilder crescent = nested().parent(getExistingFile(Risus.prefix("item/base_axe_model"))).texture("axe", Risus.prefix("entity/crescent_disaster"));
		withExistingParent(RisusItems.CRESCENT_DISASTER.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
				.base(generated("crescent_disaster_base", Risus.prefix("items/crescent_disaster")))
				.perspective(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND, crescent)
				.perspective(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND, crescent)
				.perspective(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, crescent)
				.perspective(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, crescent)
				.perspective(ItemTransforms.TransformType.HEAD, crescent)
				.end();
		ItemModelBuilder unawakened = nested().parent(getExistingFile(Risus.prefix("item/base_axe_model"))).texture("axe", Risus.prefix("entity/unawakened_vessel"));
		withExistingParent(RisusItems.UNAWAKENED_VESSEL.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
				.base(generated("unawakened_vessel_base", Risus.prefix("items/unawakened_vessel")))
				.perspective(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND, unawakened)
				.perspective(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND, unawakened)
				.perspective(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, unawakened)
				.perspective(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, unawakened)
				.perspective(ItemTransforms.TransformType.HEAD, unawakened)
				.end();
		singleTex(RisusItems.SMILE);
		singleTex(RisusItems.JOYFLAME_CAMPFIRE);
		singleTex(RisusItems.JOYFLAME_LANTERN);
		singleTex(RisusItems.JOYFLAME_TORCH);
		singleTex(RisusItems.HEART_TRANSPLANT);
		singleTex(RisusItems.BIG_CHAIN);
		singleTex(RisusItems.SPREADING_REMAINS);
		singleTex(RisusItems.MEMORY1_ITEM);
		singleTex(RisusItems.REGEN_ROSE);

		//spawn eggs
		for (Item i : Registry.ITEM) {
			if (i instanceof SpawnEggItem && Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(i)).getNamespace().equals(Risus.MODID)) {
				getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(i)).getPath())
						.parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
			}
		}

	}

	private void toBlock(Block b) {
		toBlockModel(b, texture("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getPath()));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getPath(), model);
	}

	private ItemModelBuilder generated(String name, String parent, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		return generated(name, "item/generated", layers);
	}

	private ItemModelBuilder singleTex(RegistryObject<Item> item) {
		return generated(item.getId().getPath(), "item/generated", texture("items/" + item.getId().getPath()));
	}

	private ItemModelBuilder singleTexTool(RegistryObject<Item> item) {
		return generated(item.getId().getPath(), "item/handheld", texture("items/" + item.getId().getPath()));
	}

	private ResourceLocation texture(String name) {
		return Risus.prefix(name);
	}

	@Override
	public String getName() {
		return "Risus Item Models";
	}
}
