package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.*;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class BlockModelGenerator extends BlockStateProvider {

	public BlockModelGenerator(PackOutput packOutput, ExistingFileHelper exFileHelper) {
		super(packOutput, Risus.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(RisusBlocks.ALTERATION_CATALYST.get(), models().withExistingParent(RisusBlocks.ALTERATION_CATALYST.getId().getPath(), "block/block").texture("particle", new ResourceLocation("block/netherite_block"))
				.customLoader(CompositeModelBuilder::begin)
				.child("ring", models().withExistingParent("catalyst_ring", Risus.prefix("block/alteration_catalyst_ring")))
				.child("base", models().withExistingParent("catalyst_base", Risus.prefix("block/alteration_catalyst_base"))).end());
		getVariantBuilder(RisusBlocks.ASHEN_REMAINS.get()).forAllStates(state -> {
			ModelFile noEyes = models().cubeAll("ashen_remains", texture("block/ashen_remains"));
			ModelFile eyes = models().cubeAll("ashen_remains_eyes", texture("block/ashen_remains_eyes"));
			return ConfiguredModel.builder().modelFile(state.getValue(AshenRemainsBlock.HAS_EYES) ? eyes : noEyes).weight(1).nextModel().modelFile(noEyes).weight(30).build();
		});
		simpleBlock(RisusBlocks.SMILING_REMAINS.get(), make2LayerCubeAllSidesSame(RisusBlocks.SMILING_REMAINS.getId().getPath(), new ResourceLocation("cutout"), 0, 10, false)
				.texture("all", Risus.prefix("block/smiling_remains"))
				.texture("all2", Risus.prefix("block/smiling_remains_overlay")));
		simpleBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());

		getVariantBuilder(RisusBlocks.RIBCAGE.get()).forAllStates(state -> {
			ModelFile bottom = models().getExistingFile(texture("block/ribcage_cage"));
			ModelFile top = models().getExistingFile(texture("block/ribcage_spine"));
			return ConfiguredModel.builder()
					.modelFile(state.getValue(RibcageBlock.HALF) == DoubleBlockHalf.LOWER ? bottom : top)
					.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
					.build();
		});
		simpleBlock(RisusBlocks.ANGEL_ALTAR.get(), models().getExistingFile(texture("block/angel_altar")));
		horizontalBlock(RisusBlocks.BABY_RIBCAGE.get(), models().getExistingFile(texture("block/baby_ribcage")));
		directionalBlock(RisusBlocks.CRYSTALLIZED_BONDS.get(), models().getExistingFile(texture("block/crystallized_bonds")));
		axisBlock(RisusBlocks.ENGRAVED_BASALT.get(), models().getExistingFile(texture("block/engraved_basalt")), models().getExistingFile(texture("block/engraved_basalt")));
		simpleBlock(RisusBlocks.MAW_GUTS.get(), models().getExistingFile(texture("block/maw_guts")));
		simpleBlock(RisusBlocks.MIRAGE_GRASS_BLOCK.get(), models().withExistingParent(RisusBlocks.MIRAGE_GRASS_BLOCK.getId().getPath(), "block/grass_block").renderType("minecraft:cutout").texture("top", texture("block/mirage_grass_block_top")));
		simpleBlock(RisusBlocks.MIRAGE_SAND.get(), cubeAll(RisusBlocks.MIRAGE_SAND.get()));
		simpleBlock(RisusBlocks.MIRAGE_NETHERRACK.get(), cubeAll(RisusBlocks.MIRAGE_NETHERRACK.get()));
		simpleBlock(RisusBlocks.MIRAGE_END_STONE.get(), cubeAll(RisusBlocks.MIRAGE_END_STONE.get()));
		horizontalBlock(RisusBlocks.GLUTTONY_SCALEPLATE.get(), models().getExistingFile(texture("gluttony_scaleplate")));
		horizontalBlock(RisusBlocks.IMITATION_SCALEPLATE.get(), models().getExistingFile(texture("imitation_scaleplate")));
		simpleBlock(RisusBlocks.BLOODWYRM_HEAD.get(), models().getExistingFile(new ResourceLocation("block/skull")));
		simpleBlock(RisusBlocks.BLOODWYRM_WALL_HEAD.get(), models().getExistingFile(new ResourceLocation("block/skull")));
		axisBlock(RisusBlocks.BONDKNOT_LOG.get(), texture("block/bondknot_log"), texture("block/bondknot_log_top"));
		axisBlock(RisusBlocks.BONDKNOT_WOOD.get(), texture("block/bondknot_log"), texture("block/bondknot_log"));
		getVariantBuilder(RisusBlocks.POPPING_BONDKNOT_LOG.get()).forAllStates(state -> {
			ModelFile baseFile = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_LOG.getId().getPath() + "_base", "block/cube")
					.texture("particle", Risus.prefix("block/bondknot_log"))
					.texture("north", Risus.prefix("block/bondknot_log")).texture("south", Risus.prefix("block/bondknot_log"))
					.texture("west", Risus.prefix("block/bondknot_log")).texture("east", Risus.prefix("block/bondknot_log"))
					.texture("up", Risus.prefix("block/bondknot_log_top")).texture("down", Risus.prefix("block/bondknot_log_top"));

			ModelFile poppingN = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_LOG.getId().getPath() + "_north", baseFile.getLocation())
					.texture("north", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingW = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_LOG.getId().getPath() + "_west", baseFile.getLocation())
					.texture("west", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingS = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_LOG.getId().getPath() + "_south", baseFile.getLocation())
					.texture("south", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingE = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_LOG.getId().getPath() + "_east", baseFile.getLocation())
					.texture("east", Risus.prefix("block/popping_bondknot_log_side"));
			switch (state.getValue(BlockStateProperties.AXIS)) {
				case X -> {
					ModelFile file = switch (state.getValue(PoppingBondknotBlock.POP_SIDE)) {
						case UP -> poppingS;
						case NORTH -> poppingW;
						case SOUTH -> poppingE;
						default -> poppingN;
					};
					return ConfiguredModel.builder().modelFile(file).rotationX(90).rotationY(90).build();
				}
				case Z -> {
					ModelFile file = switch (state.getValue(PoppingBondknotBlock.POP_SIDE)) {
						case UP -> poppingS;
						case WEST -> poppingW;
						case EAST -> poppingE;
						default -> poppingN;
					};
					return ConfiguredModel.builder().modelFile(file).rotationX(90).build();
				}
				//actually case y
				default -> {
					ModelFile file = switch (state.getValue(PoppingBondknotBlock.POP_SIDE)) {
						case SOUTH -> poppingS;
						case WEST -> poppingW;
						case EAST -> poppingE;
						default -> poppingN;
					};
					return ConfiguredModel.builder().modelFile(file).build();
				}
			}
		});
		getVariantBuilder(RisusBlocks.POPPING_BONDKNOT_WOOD.get()).forAllStates(state -> {
			ModelFile baseFile = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_WOOD.getId().getPath() + "_base", "block/cube")
					.texture("particle", Risus.prefix("block/bondknot_log"))
					.texture("north", Risus.prefix("block/bondknot_log")).texture("south", Risus.prefix("block/bondknot_log"))
					.texture("west", Risus.prefix("block/bondknot_log")).texture("east", Risus.prefix("block/bondknot_log"))
					.texture("up", Risus.prefix("block/bondknot_log")).texture("down", Risus.prefix("block/bondknot_log"));

			ModelFile poppingN = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_WOOD.getId().getPath() + "_north", baseFile.getLocation())
					.texture("north", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingW = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_WOOD.getId().getPath() + "_west", baseFile.getLocation())
					.texture("west", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingS = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_WOOD.getId().getPath() + "_south", baseFile.getLocation())
					.texture("south", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingE = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_WOOD.getId().getPath() + "_east", baseFile.getLocation())
					.texture("east", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingU = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_WOOD.getId().getPath() + "_up", baseFile.getLocation())
					.texture("up", Risus.prefix("block/popping_bondknot_log_side"));
			ModelFile poppingD = models().withExistingParent(RisusBlocks.POPPING_BONDKNOT_WOOD.getId().getPath() + "_down", baseFile.getLocation())
					.texture("down", Risus.prefix("block/popping_bondknot_log_side"));


			switch (state.getValue(BlockStateProperties.AXIS)) {
				case X -> {
					ModelFile file = switch (state.getValue(PoppingBondknotBlock.POP_SIDE)) {
						case UP -> poppingS;
						case NORTH -> poppingW;
						case SOUTH -> poppingE;
						case WEST -> poppingU;
						case EAST -> poppingD;
						default -> poppingN;
					};
					return ConfiguredModel.builder().modelFile(file).rotationX(90).rotationY(90).build();
				}
				case Z -> {
					ModelFile file = switch (state.getValue(PoppingBondknotBlock.POP_SIDE)) {
						case UP -> poppingS;
						case WEST -> poppingW;
						case EAST -> poppingE;
						case NORTH -> poppingU;
						case SOUTH -> poppingD;
						default -> poppingN;
					};
					return ConfiguredModel.builder().modelFile(file).rotationX(90).build();
				}
				//actually case y
				default -> {
					ModelFile file = switch (state.getValue(PoppingBondknotBlock.POP_SIDE)) {
						case SOUTH -> poppingS;
						case WEST -> poppingW;
						case EAST -> poppingE;
						case UP -> poppingU;
						case DOWN -> poppingD;
						default -> poppingN;
					};
					return ConfiguredModel.builder().modelFile(file).build();
				}
			}
		});
		axisBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG.get(), texture("block/stripped_bondknot_log"), texture("block/stripped_bondknot_log_top"));
		axisBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), texture("block/stripped_bondknot_log"), texture("block/stripped_bondknot_log"));
		simpleBlock(RisusBlocks.BONDKNOT_PLANKS.get());
		slabBlock(RisusBlocks.BONDKNOT_SLAB.get(), texture("block/bondknot_planks"), texture("block/bondknot_planks"));
		stairsBlock(RisusBlocks.BONDKNOT_STAIRS.get(), texture("block/bondknot_planks"));
		fenceBlock(RisusBlocks.BONDKNOT_FENCE.get(), texture("block/bondknot_planks"));
		fenceGateBlock(RisusBlocks.BONDKNOT_FENCE_GATE.get(), texture("block/bondknot_planks"));
		woodPlate(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get(), texture("block/bondknot_planks"));
		woodButton(RisusBlocks.BONDKNOT_BUTTON.get(), texture("block/bondknot_planks"));
		trapdoorBlockWithRenderType(RisusBlocks.BONDKNOT_TRAPDOOR.get(), texture("block/bondknot_trapdoor"), true, new ResourceLocation("cutout"));
		doorBlockWithRenderType(RisusBlocks.BONDKNOT_DOOR.get(), texture("block/bondknot_door_bottom"), texture("block/bondknot_door_top"), new ResourceLocation("cutout"));
		builtinEntity(RisusBlocks.BONDKNOT_SIGN.get(), texture("block/bondknot_planks").toString());
		builtinEntity(RisusBlocks.BONDKNOT_WALL_SIGN.get(), texture("block/bondknot_planks").toString());

		simpleBlock(RisusBlocks.GRIMSTONE.get());
		simpleBlock(RisusBlocks.GRIMSTONE_BRICKS.get());
		simpleBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		slabBlock(RisusBlocks.GRIMSTONE_SLAB.get(), texture("block/grimstone_bricks"), texture("block/grimstone_bricks"));
		stairsBlock(RisusBlocks.GRIMSTONE_STAIRS.get(), texture("block/grimstone_bricks"));
		wallBlock(RisusBlocks.GRIMSTONE_WALL.get(), texture("block/grimstone_bricks"));
		simpleBlock(RisusBlocks.POLISHED_GRIMSTONE.get());
		horizontalBlock(RisusBlocks.CHISELED_GRIMSTONE.get(), models().getExistingFile(Risus.prefix("block/chiseled_grimstone")));
		horizontalBlock(RisusBlocks.CURVED_RITUAL_BLOCK.get(), models().withExistingParent("curved_ritual_block", new ResourceLocation("block/template_glazed_terracotta")).texture("pattern", Risus.prefix("block/curved_ritual_block")));
		axisBlock((RotatedPillarBlock) RisusBlocks.LINEAR_RITUAL_BLOCK.get(), texture("block/linear_ritual_block_side"), texture("block/linear_ritual_block_top"));
		simpleBlock(RisusBlocks.DARKNESS.get(), models().cubeAll("darkness", Risus.prefix("block/darkness")).renderType("minecraft:cutout"));
		simpleBlock(RisusBlocks.NEURON_HEAD.get(), models().cross("neuron_head", Risus.prefix("block/neuron_head")).renderType("minecraft:cutout"));
		simpleBlock(RisusBlocks.NEURON_STEM.get(), models().cross("neuron", Risus.prefix("block/neuron")).renderType("minecraft:cutout"));

		simpleBlock(RisusBlocks.TISSUE.get());
		wallBlock(RisusBlocks.BONE_WALL.get(), models().getExistingFile(Risus.prefix("block/tissue/bone_wall_post")), models().getExistingFile(Risus.prefix("block/tissue/bone_wall_side")), models().getExistingFile(Risus.prefix("block/tissue/bone_wall_tall_side")));
		wallBlock(RisusBlocks.ROTTING_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_tall_side")));
		wallBlock(RisusBlocks.DECOMPOSING_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_tall_side")));
		wallBlock(RisusBlocks.DECAYING_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_tall_side")));

		simpleBlock(RisusBlocks.LIVING_TISSUE.get(), models().cubeAll("living_tissue", Risus.prefix("block/tissue")));
		wallBlock(RisusBlocks.ROTTED_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_tall_side")));
		wallBlock(RisusBlocks.DECOMPOSED_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_tall_side")));
		wallBlock(RisusBlocks.DECAYED_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_tall_side")));
		directionalBlock(RisusBlocks.EYE_ENDER.get(), models().getExistingFile(texture("block/eye_ender")));
		directionalBlock(RisusBlocks.EYE_BLEACHED.get(), models().getExistingFile(texture("block/eye_bleached")));
		directionalBlock(RisusBlocks.EYE_BLOODSHOT.get(), models().getExistingFile(texture("block/eye_bloodshot")));
		directionalBlock(RisusBlocks.EYE_GOLDEN.get(), models().getExistingFile(texture("block/eye_golden")));
		directionalBlock(RisusBlocks.EYE_EMERALD.get(), models().getExistingFile(texture("block/eye_emerald")));
		directionalBlock(RisusBlocks.FLESHY_SKIN.get(), models().getExistingFile(texture("block/fleshy_skin")));
		simpleBlock(RisusBlocks.SKIN.get());
		directionalBlock(RisusBlocks.HAIRY_FLESHY_SKIN.get(), models().getExistingFile(texture("block/hairy_fleshy_skin")));





		simpleBlock(RisusBlocks.BURNT_HYPHAE.get());
		horizontalBlock(RisusBlocks.HEART_TRANSPLANT.get(), models().getExistingFile(texture("block/heart_transplant")));
		simpleBlock(RisusBlocks.POTTED_HEART_TRANSPLANT.get(), models().getExistingFile(texture("block/potted_heart_transplant")));
		simpleBlock(RisusBlocks.REGEN_ROSE.get(), models().getExistingFile(texture("block/regen_rose")));
		simpleBlock(RisusBlocks.POTTED_REGEN_ROSE.get(), models().getExistingFile(texture("block/potted_regen_rose")));
		getVariantBuilder(RisusBlocks.BIG_CHAIN.get())
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
				.modelForState().modelFile(models().getExistingFile(texture("block/big_chain"))).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
				.modelForState().modelFile(models().getExistingFile(texture("block/big_chain"))).rotationX(90).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
				.modelForState().modelFile(models().getExistingFile(texture("block/big_chain"))).rotationX(90).rotationY(90).addModel();
		torchBlock(RisusBlocks.JOYFLAME_TORCH, RisusBlocks.JOYFLAME_WALL_TORCH);
		getVariantBuilder(RisusBlocks.JOYFLAME_CAMPFIRE.get()).forAllStatesExcept(state -> {
			ModelFile on = models().withExistingParent("joyflame_campfire", new ResourceLocation("block/template_campfire")).texture("fire", texture("block/joyflame_campfire_fire")).texture("lit_log", texture("block/joyflame_campfire_log_lit")).renderType("minecraft:cutout");
			ModelFile off = models().withExistingParent("joyflame_campfire_off", new ResourceLocation("block/campfire_off")).renderType("minecraft:cutout");
			return ConfiguredModel.builder().modelFile(state.getValue(CampfireBlock.LIT) ? on : off).rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build();
		}, CampfireBlock.WATERLOGGED, CampfireBlock.SIGNAL_FIRE);
		getVariantBuilder(RisusBlocks.JOYFLAME_LANTERN.get()).forAllStatesExcept(state -> {
			ModelFile normal = models().withExistingParent("joyflame_lantern", new ResourceLocation("block/template_lantern")).texture("lantern", texture("block/joyflame_lantern")).renderType("minecraft:cutout");
			ModelFile hanging = models().withExistingParent("joyflame_lantern_hanging", new ResourceLocation("block/template_hanging_lantern")).texture("lantern", texture("block/joyflame_lantern")).renderType("minecraft:cutout");
			return ConfiguredModel.builder().modelFile(state.getValue(LanternBlock.HANGING) ? hanging : normal).build();
		}, LanternBlock.WATERLOGGED);

		getVariantBuilder(RisusBlocks.ZIT.get()).forAllStates(state -> {
			ModelFile normal = models().getExistingFile(texture("block/zit"));
			ModelFile popped = models().getExistingFile(texture("block/zit_popped"));
			int x = 0;
			int y = 0;
			switch (state.getValue(ZitBlock.FACING)) {
				case DOWN -> x = 180;
				case NORTH -> x = 90;
				case SOUTH -> {
					x = 90;
					y = 180;
				}
				case WEST -> {
					x = 90;
					y = 270;
				}
				case EAST -> {
					x = 90;
					y = 90;
				}
			}
			return ConfiguredModel.builder().modelFile(state.getValue(ZitBlock.POPPED) ? popped : normal).rotationX(x).rotationY(y).build();
		});

		ModelFile floor = models().withExistingParent("joyflame_fire_floor", new ResourceLocation("block/template_fire_floor")).texture("fire", texture("block/joyflame_fire")).renderType("minecraft:cutout");
		ModelFile side = models().withExistingParent("joyflame_fire_side", new ResourceLocation("block/template_fire_side")).texture("fire", texture("block/joyflame_fire")).renderType("minecraft:cutout");
		ModelFile sideAlt = models().withExistingParent("joyflame_fire_side_alt", new ResourceLocation("block/template_fire_side_alt")).texture("fire", texture("block/joyflame_fire")).renderType("minecraft:cutout");

		getMultipartBuilder(RisusBlocks.JOYFLAME_FIRE.get())
				.part().modelFile(floor).addModel().end()
				.part().modelFile(side).nextModel().modelFile(sideAlt).addModel().end()
				.part().modelFile(side).rotationY(90).nextModel().modelFile(sideAlt).rotationY(90).addModel().end()
				.part().modelFile(side).rotationY(180).nextModel().modelFile(sideAlt).rotationY(180).addModel().end()
				.part().modelFile(side).rotationY(270).nextModel().modelFile(sideAlt).rotationY(270).addModel().end();

	}

	public BlockModelBuilder fencePost(String name, ResourceLocation texture) {
		return models().singleTexture(name, texture("block/template_fence_post"), texture);
	}

	public BlockModelBuilder wallPost(String name, ResourceLocation wall) {
		return models().singleTexture(name, texture("block/template_wall_post"), "wall", wall);
	}

	public BlockModelBuilder wallSide(String name, ResourceLocation wall) {
		return models().singleTexture(name, texture("block/template_wall_side"), "wall", wall);
	}

	public BlockModelBuilder wallSideTall(String name, ResourceLocation wall) {
		return models().singleTexture(name, texture("block/template_wall_side_tall"), "wall", wall);
	}

	public void torchBlock(Supplier<? extends Block> block, Supplier<? extends Block> wall) {
		ModelFile torch = models().torch(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), new ResourceLocation(Risus.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath())).renderType("minecraft:cutout");
		ModelFile torchwall = models().torchWall(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), new ResourceLocation(Risus.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath())).renderType("minecraft:cutout");
		simpleBlock(block.get(), torch);
		getVariantBuilder(wall.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(torchwall)
						.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
						.build());
	}

	private void woodPlate(Block plate, ResourceLocation texName) {
		ModelFile unpressed = models().withExistingParent(ForgeRegistries.BLOCKS.getKey(plate).getPath(), "pressure_plate_up").texture("texture", texName);
		ModelFile pressed = models().withExistingParent(ForgeRegistries.BLOCKS.getKey(plate).getPath() + "_down", "pressure_plate_down").texture("texture", texName);

		getVariantBuilder(plate).forAllStates(state -> ConfiguredModel.builder().modelFile(state.getValue(PressurePlateBlock.POWERED) ? pressed : unpressed).build());
	}

	private void woodButton(Block button, ResourceLocation texName) {
		ModelFile unpressed = models().withExistingParent(ForgeRegistries.BLOCKS.getKey(button).getPath(), "button").texture("texture", texName);
		ModelFile pressed = models().withExistingParent(ForgeRegistries.BLOCKS.getKey(button).getPath() + "_pressed", "button_pressed").texture("texture", texName);

		getVariantBuilder(button).forAllStates(state -> {
			ModelFile model = state.getValue(ButtonBlock.POWERED) ? pressed : unpressed;
			int rotX = switch (state.getValue(FaceAttachedHorizontalDirectionalBlock.FACE)) {
				case WALL -> 90;
				case FLOOR -> 0;
				case CEILING -> 180;
			};
			int rotY = 0;
			if (state.getValue(FaceAttachedHorizontalDirectionalBlock.FACE) == AttachFace.CEILING) {
				switch (state.getValue(HorizontalDirectionalBlock.FACING)) {
					case NORTH -> rotY = 180;
					case WEST -> rotY = 90;
					case EAST -> rotY = 270;
				}
			} else {
				rotY = switch (state.getValue(HorizontalDirectionalBlock.FACING)) {
					case NORTH -> 0;
					case SOUTH -> 180;
					case WEST -> 270;
					case EAST -> 90;
					default -> rotY;
				};
			}
			boolean uvlock = state.getValue(FaceAttachedHorizontalDirectionalBlock.FACE) == AttachFace.WALL;

			return ConfiguredModel.builder().uvLock(uvlock).rotationX(rotX).rotationY(rotY).modelFile(model).build();
		});
	}

	protected BlockModelBuilder make2LayerCubeAllSidesSame(String name, ResourceLocation renderType, int layer1em, int layer2em, boolean shade) {
		return this.make2LayerCube(name, renderType,
						layer1em, layer1em, layer1em, layer1em, layer1em, layer1em,
						layer2em, layer2em, layer2em, layer2em, layer2em, layer2em, shade)
				.texture("north", "#all").texture("south", "#all").texture("east", "#all")
				.texture("west", "#all").texture("top", "#all").texture("bottom", "#all")
				.texture("north2", "#all2").texture("south2", "#all2").texture("east2", "#all2")
				.texture("west2", "#all2").texture("top2", "#all2").texture("bottom2", "#all2");
	}

	protected BlockModelBuilder make2LayerCube(String name, ResourceLocation renderType,
											   int layer1emN, int layer1emS, int layer1emW, int layer1emE, int layer1emU, int layer1emD,
											   int layer2emN, int layer2emS, int layer2emW, int layer2emE, int layer2emU, int layer2emD, boolean shade) {
		int skylightLevel = 0;

		return models().withExistingParent(name, "minecraft:block/block").renderType(renderType).texture("particle", "#bottom")
				.element().from(0.0F, 0.0F, 0.0F).to(16.0F, 16.0F, 16.0F).shade(shade)
				.face(Direction.NORTH).texture("#north").cullface(Direction.NORTH).emissivity(layer1emN, skylightLevel).end()
				.face(Direction.EAST).texture("#east").cullface(Direction.EAST).emissivity(layer1emE, skylightLevel).end()
				.face(Direction.SOUTH).texture("#south").cullface(Direction.SOUTH).emissivity(layer1emS, skylightLevel).end()
				.face(Direction.WEST).texture("#west").cullface(Direction.WEST).emissivity(layer1emW, skylightLevel).end()
				.face(Direction.UP).texture("#top").cullface(Direction.UP).emissivity(layer1emU, skylightLevel).end()
				.face(Direction.DOWN).texture("#bottom").cullface(Direction.DOWN).emissivity(layer1emD, skylightLevel).end().end()
				.element().from(0.0F, 0.0F, 0.0F).to(16.0F, 16.0F, 16.0F).shade(shade)
				.face(Direction.NORTH).texture("#north2").cullface(Direction.NORTH).emissivity(layer2emN, skylightLevel).tintindex(0).end()
				.face(Direction.EAST).texture("#east2").cullface(Direction.EAST).emissivity(layer2emE, skylightLevel).tintindex(0).end()
				.face(Direction.SOUTH).texture("#south2").cullface(Direction.SOUTH).emissivity(layer2emS, skylightLevel).tintindex(0).end()
				.face(Direction.WEST).texture("#west2").cullface(Direction.WEST).emissivity(layer2emW, skylightLevel).tintindex(0).end()
				.face(Direction.UP).texture("#top2").cullface(Direction.UP).emissivity(layer2emU, skylightLevel).tintindex(0).end()
				.face(Direction.DOWN).texture("#bottom2").cullface(Direction.DOWN).emissivity(layer2emD, skylightLevel).tintindex(0).end().end();
	}

	private void builtinEntity(Block b, String particle) {
		simpleBlock(b, models().getBuilder(ForgeRegistries.BLOCKS.getKey(b).getPath())
				.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
				.texture("particle", particle));
	}

	private ResourceLocation texture(String name) {
		return Risus.prefix(name);
	}

	@Nonnull
	@Override
	public String getName() {
		return "Risus blockstates and models";
	}
}
