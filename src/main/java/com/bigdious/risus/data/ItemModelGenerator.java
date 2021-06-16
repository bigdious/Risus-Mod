package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.block.RisusBlocks;
import com.bigdious.risus.items.RisusItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ItemModelGenerator extends ItemModelProvider {

	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Risus.ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//blocks
		toBlock(RisusBlocks.ASHEN_REMAINS.get());
		toBlock(RisusBlocks.SMILING_REMAINS.get());
		toBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());
		toBlock(RisusBlocks.ENGRAVED_BASALT.get());
		toBlock(RisusBlocks.MAW_GUTS.get());
		toBlock(RisusBlocks.CRYSTALIZED_BONDS.get());
		toBlockModel(RisusBlocks.BLOODWEAVE.get(), texture("block/bloodweave_bwb"));
		toBlock(RisusBlocks.BONDKNOT_LOG.get());
		toBlock(RisusBlocks.BONDKNOT_WOOD.get());
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
		toBlock(RisusBlocks.BONDKNOT_PLANKS.get());
		toBlock(RisusBlocks.BONDKNOT_SLAB.get());
		toBlock(RisusBlocks.BONDKNOT_STAIRS.get());
		toBlock(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		toBlock(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
		getBuilder(RisusBlocks.BONDKNOT_FENCE.get().getRegistryName().getPath())
				.parent(getExistingFile(new ResourceLocation("block/fence_inventory")))
				.texture("texture", texture("block/bondknot_planks"));
		getBuilder(RisusBlocks.BONDKNOT_BUTTON.get().getRegistryName().getPath())
				.parent(getExistingFile(new ResourceLocation("block/button_inventory")))
				.texture("texture", texture("block/bondknot_planks"));
		toBlock(RisusBlocks.BABY_RIBCAGE.get());
		toBlock(RisusBlocks.RIBCAGE.get());
		toBlock(RisusBlocks.GRIMSTONE.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.GRIMSTONE_SLAB.get());
		toBlock(RisusBlocks.GRIMSTONE_STAIRS.get());
		getBuilder(RisusBlocks.GRIMSTONE_WALL.get().getRegistryName().getPath())
				.parent(getExistingFile(new ResourceLocation("block/wall_inventory")))
				.texture("wall", texture("block/grimstone_bricks"));
		toBlock(RisusBlocks.CHISELED_GRIMSTONE.get());
		toBlock(RisusBlocks.POLISHED_GRIMSTONE.get());


		//items
		singleTex(RisusItems.BLOOD_FEATHER);
		singleTex(RisusItems.JOYKILLER);
		singleTex(RisusItems.SMILE);

	}

	private void toBlock(Block b) {
		toBlockModel(b, texture("block/" + b.getRegistryName().getPath()));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(b.getRegistryName().getPath(), model);
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/generated");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder singleTex(RegistryObject<Item> item) {
		return generated(item.getId().getPath(), texture("items/" + item.getId().getPath()));
	}

	private ResourceLocation texture(String name) {
		return Risus.risusRL(name);
	}

	@Override
	public String getName() {
		return "Risus Item Models";
	}
}
