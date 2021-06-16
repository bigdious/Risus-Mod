package com.bigdious.risus.data;


import com.bigdious.risus.Risus;
import com.bigdious.risus.block.RisusBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class BlockModelGenerator extends BlockStateProvider {

	public BlockModelGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Risus.ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(RisusBlocks.ASHEN_REMAINS.get());
		simpleBlock(RisusBlocks.SMILING_REMAINS.get());
		simpleBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());
		simpleBlock(RisusBlocks.BLOODWEAVE.get(), models().getExistingFile(texture("block/bloodweave_bwb")));

		simpleBlock(RisusBlocks.BLOODWYRM_HEAD.get(), models().getExistingFile(new ResourceLocation("block/skull")));
		simpleBlock(RisusBlocks.BLOODWYRM_WALL_HEAD.get(), models().getExistingFile(new ResourceLocation("block/skull")));
		axisBlock(RisusBlocks.BONDKNOT_LOG.get(), texture("block/bondknot_log"), texture("block/bondknot_log_top"));
		axisBlock(RisusBlocks.BONDKNOT_WOOD.get(), texture("block/bondknot_log"), texture("block/bondknot_log"));
		axisBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG.get(), texture("block/stripped_bondknot_log"), texture("block/bondknot_log_top"));
		axisBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), texture("block/stripped_bondknot_log"), texture("block/stripped_bondknot_log"));
		simpleBlock(RisusBlocks.BONDKNOT_PLANKS.get());
		slabBlock(RisusBlocks.BONDKNOT_SLAB.get(), texture("block/bondknot_planks"), texture("block/bondknot_planks"));
		stairsBlock(RisusBlocks.BONDKNOT_STAIRS.get(), texture("block/bondknot_planks"));
		fenceBlock(RisusBlocks.BONDKNOT_FENCE.get(), texture("block/bondknot_planks"));
		fenceGateBlock(RisusBlocks.BONDKNOT_FENCE_GATE.get(), texture("block/bondknot_planks"));
		woodPlate(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get(), texture("block/bondknot_planks"));
		woodButton(RisusBlocks.BONDKNOT_BUTTON.get(), texture("block/bondknot_planks"));

		axisBlock(RisusBlocks.GRIMSTONE.get(), texture("block/grimstone"), texture("block/grimstone_top"));
		simpleBlock(RisusBlocks.GRIMSTONE_BRICKS.get());
		simpleBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		slabBlock(RisusBlocks.GRIMSTONE_SLAB.get(), texture("block/grimstone_bricks"), texture("block/grimstone_bricks"));
		stairsBlock(RisusBlocks.GRIMSTONE_STAIRS.get(), texture("block/grimstone_bricks"));
		wallBlock(RisusBlocks.GRIMSTONE_WALL.get(), texture("block/grimstone_bricks"));
		simpleBlock(RisusBlocks.CHISELED_GRIMSTONE.get());
		simpleBlock(RisusBlocks.POLISHED_GRIMSTONE.get());

	}

	private void woodPlate(Block plate, ResourceLocation texName) {
		ModelFile unpressed = models().withExistingParent(plate.getRegistryName().getPath(), "pressure_plate_up").texture("texture", texName);
		ModelFile pressed = models().withExistingParent(plate.getRegistryName().getPath() + "_down", "pressure_plate_down").texture("texture", texName);

		getVariantBuilder(plate).forAllStates(state -> ConfiguredModel.builder().modelFile(state.get(PressurePlateBlock.POWERED) ? pressed : unpressed).build());
	}

	private void woodButton(Block button, ResourceLocation texName) {
		ModelFile unpressed = models().withExistingParent(button.getRegistryName().getPath(), "button").texture("texture", texName);
		ModelFile pressed = models().withExistingParent(button.getRegistryName().getPath() + "_pressed", "button_pressed").texture("texture", texName);

		getVariantBuilder(button).forAllStates(state -> {
			ModelFile model = state.get(AbstractButtonBlock.POWERED) ? pressed : unpressed;
			int rotX = 0;
			switch (state.get(HorizontalFaceBlock.FACE)) {
				case WALL:
					rotX = 90;
					break;
				case FLOOR:
					rotX = 0;
					break;
				case CEILING:
					rotX = 180;
					break;
			}
			int rotY = 0;
			if (state.get(HorizontalFaceBlock.FACE) == AttachFace.CEILING) {
				switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
					case NORTH:
						rotY = 180;
						break;
					case SOUTH:
						rotY = 0;
						break;
					case WEST:
						rotY = 90;
						break;
					case EAST:
						rotY = 270;
						break;
				}
			} else {
				switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
					case NORTH:
						rotY = 0;
						break;
					case SOUTH:
						rotY = 180;
						break;
					case WEST:
						rotY = 270;
						break;
					case EAST:
						rotY = 90;
						break;
				}
			}
			boolean uvlock = state.get(HorizontalFaceBlock.FACE) == AttachFace.WALL;

			return ConfiguredModel.builder().uvLock(uvlock).rotationX(rotX).rotationY(rotY).modelFile(model).build();
		});
	}

	private ResourceLocation texture(String name) {
		return Risus.risusRL(name);
	}

	@Nonnull
	@Override
	public String getName() {
		return "Risus blockstates and models";
	}
}
