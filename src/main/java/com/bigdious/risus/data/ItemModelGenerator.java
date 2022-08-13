package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
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
		toBlock(RisusBlocks.ASHEN_REMAINS.get());
		toBlock(RisusBlocks.SMILING_REMAINS.get());
		toBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());
		toBlock(RisusBlocks.ENGRAVED_BASALT.get());
		toBlock(RisusBlocks.MAW_GUTS.get());
		toBlock(RisusBlocks.CRYSTALLIZED_BONDS.get());
		toBlockModel(RisusBlocks.BLOODWEAVE.get(), texture("block/bloodweave_core"));
		toBlock(RisusBlocks.BONDKNOT_LOG.get());
		toBlock(RisusBlocks.BONDKNOT_WOOD.get());
		toBlock(RisusBlocks.POPPING_BONDKNOT_LOG.get());
		toBlock(RisusBlocks.POPPING_BONDKNOT_WOOD.get());
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


		//items
		singleTex(RisusItems.BLOOD_FEATHER);
		singleTexTool(RisusItems.JOYKILLER);
		singleTex(RisusItems.SMILE);
		singleTex(RisusItems.GUILTY_APPLE);

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

	private void generated(String name, String parent, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
	}

	private void generated(String name, ResourceLocation... layers) {
		generated(name, "item/generated", layers);
	}

	private void singleTex(RegistryObject<Item> item) {
		generated(item.getId().getPath(), "item/generated", texture("items/" + item.getId().getPath()));
	}

	private void singleTexTool(RegistryObject<Item> item) {
		generated(item.getId().getPath(), "item/handheld", texture("items/" + item.getId().getPath()));
	}

	private ResourceLocation texture(String name) {
		return Risus.prefix(name);
	}

	@Override
	public String getName() {
		return "Risus Item Models";
	}
}
