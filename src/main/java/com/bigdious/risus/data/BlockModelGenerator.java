package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.*;
import com.bigdious.risus.blocks.enums.FenceSide;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Supplier;

public class BlockModelGenerator extends BlockStateProvider {


	public BlockModelGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, Risus.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(RisusBlocks.ALTERATION_CATALYST.get(), models().getExistingFile(Risus.prefix("block/alteration_catalyst")));
		models().withExistingParent("alteration_catalyst_inventory", "block/block").texture("particle", ResourceLocation.withDefaultNamespace("block/netherite_block"))
			.customLoader(CompositeModelBuilder::begin)
			.child("ring", models().withExistingParent("catalyst_ring", Risus.prefix("block/alteration_catalyst_ring")))
			.child("base", models().withExistingParent("catalyst_base", Risus.prefix("block/alteration_catalyst"))).end();
		getVariantBuilder(RisusBlocks.ASHEN_REMAINS.get()).forAllStates(state -> {
			ModelFile noEyes = models().cubeAll("ashen_remains", Risus.prefix("block/ashen_remains"));
			ModelFile eyes = models().cubeAll("ashen_remains_eyes", Risus.prefix("block/ashen_remains_eyes"));
			return ConfiguredModel.builder().modelFile(state.getValue(RemainsBlock.ACTIVE) ? eyes : noEyes).weight(1).nextModel().modelFile(noEyes).weight(30).build();
		});
		//so many lines for such a simple thing
		getVariantBuilder(RisusBlocks.SMILING_REMAINS.get()).forAllStates(state -> {
			//blank needs to use an overlay too, despite it being empty. Needed for correct lighting
			ModelFile blank = make2LayerCubeAllSidesSame("block/smiling_remains/0", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/0")).texture("all2", Risus.prefix("block/smiling_remains/overlay_0"));
			ModelFile smile1 = make2LayerCubeAllSidesSame("block/smiling_remains/1", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/1")).texture("all2", Risus.prefix("block/smiling_remains/overlay_1"));
			ModelFile smile2 = make2LayerCubeAllSidesSame("block/smiling_remains/2", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/2")).texture("all2", Risus.prefix("block/smiling_remains/overlay_2"));
			ModelFile smile3 = make2LayerCubeAllSidesSame("block/smiling_remains/3", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/3")).texture("all2", Risus.prefix("block/smiling_remains/overlay_3"));
			ModelFile smile4 = make2LayerCubeAllSidesSame("block/smiling_remains/4", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/4")).texture("all2", Risus.prefix("block/smiling_remains/overlay_4"));
			ModelFile smile5 = make2LayerCubeAllSidesSame("block/smiling_remains/5", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/5")).texture("all2", Risus.prefix("block/smiling_remains/overlay_5"));
			ModelFile smile6 = make2LayerCubeAllSidesSame("block/smiling_remains/6", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/6")).texture("all2", Risus.prefix("block/smiling_remains/overlay_6"));
			ModelFile smile7 = make2LayerCubeAllSidesSame("block/smiling_remains/7", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/7")).texture("all2", Risus.prefix("block/smiling_remains/overlay_7"));
			ModelFile smile8 = make2LayerCubeAllSidesSame("block/smiling_remains/8", ResourceLocation.withDefaultNamespace("cutout"), 0, 10 , false)
				.texture("all", Risus.prefix("block/smiling_remains/8")).texture("all2", Risus.prefix("block/smiling_remains/overlay_8"));
			return ConfiguredModel.builder()
				.modelFile(blank).weight(3)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile1 : blank).weight(1)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile2 : blank).weight(1)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile3 : blank).weight(1)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile4 : blank).weight(1)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile5 : blank).weight(1)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile6 : blank).weight(1)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile7 : blank).weight(1)
				.nextModel().modelFile(state.getValue(RemainsBlock.ACTIVE) ? smile8 : blank).weight(1)
				.build();
		});
		simpleBlock(RisusBlocks.BURNT_HYPHAE.get(), make2LayerCubeAllSidesSame(RisusBlocks.BURNT_HYPHAE.getId().getPath(), ResourceLocation.withDefaultNamespace("cutout"), 0, 10, false)
			.texture("all", Risus.prefix("block/burnt_hyphae"))
			.texture("all2", Risus.prefix("block/burnt_hyphae_overlay")));
		simpleBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());
		simpleBlock(RisusBlocks.BLOOD_FLUID_BLOCK.get(), models().getBuilder("blood_fluid_block").texture("particle", Risus.prefix("block/blood_still")));
		simpleBlock(RisusBlocks.BLOODY_SPONGE.get());
		simpleBlock(RisusBlocks.BLOOD_CAULDRON.get(), models().withExistingParent("blood_cauldron", "block/template_cauldron_full").texture("content", Risus.prefix("block/blood_still")));


		getVariantBuilder(RisusBlocks.RIBCAGE.get()).forAllStates(state -> {
			ModelFile bottom = models().getExistingFile(Risus.prefix("block/ribcage_cage"));
			ModelFile top = models().getExistingFile(Risus.prefix("block/ribcage_spine"));
			return ConfiguredModel.builder()
					.modelFile(state.getValue(RibcageBlock.HALF) == DoubleBlockHalf.LOWER ? bottom : top)
					.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
					.build();
		});
		getVariantBuilder(RisusBlocks.ASHEN_SPIRE.get()).forAllStates(state -> {
			ModelFile bottom = models().getExistingFile(Risus.prefix("block/ashen_spire/bottom"));
			ModelFile bottomFlipped = models().getExistingFile(Risus.prefix("block/ashen_spire/bottom_flipped"));
			ModelFile top = models().getExistingFile(Risus.prefix("block/ashen_spire/top"));
			ModelFile topFlipped = models().getExistingFile(Risus.prefix("block/ashen_spire/top_flipped"));
			ModelFile topCinderglee = models().withExistingParent("cinderglee" ,Risus.prefix("block/ashen_spire/top_lantern")).texture("1", Risus.prefix("block/joyflame_lantern"));
			ModelFile topFire = models().withExistingParent("fire" ,Risus.prefix("block/ashen_spire/top_lantern")).texture("1", "minecraft:block/lantern" );
			ModelFile topSoul = models().withExistingParent("soul" ,Risus.prefix("block/ashen_spire/top_lantern")).texture("1", "minecraft:block/soul_lantern" );
			ModelFile topCindergleeFlipped = models().withExistingParent("cinderglee_flipped" ,Risus.prefix("block/ashen_spire/top_lantern_flipped")).texture("1", Risus.prefix("block/joyflame_lantern"));
			ModelFile topFireFlipped = models().withExistingParent("fire_flipped" ,Risus.prefix("block/ashen_spire/top_lantern_flipped")).texture("1", "minecraft:block/lantern" );
			ModelFile topSoulFlipped = models().withExistingParent("soul_flipped" ,Risus.prefix("block/ashen_spire/top_lantern_flipped")).texture("1", "minecraft:block/soul_lantern" );
			return ConfiguredModel.builder()
				//if this is stupid, why does it work perfectly
				.modelFile(state.getValue(AshenSpireBlock.FLIPPED) ? (
					state.getValue(AshenSpireBlock.HALF) == DoubleBlockHalf.LOWER ? bottomFlipped :
						state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.FIRE ? topFireFlipped :
							state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.SOUL ? topSoulFlipped :
								state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.CINDERGLEE ? topCindergleeFlipped : topFlipped)
					:
					state.getValue(AshenSpireBlock.HALF) == DoubleBlockHalf.LOWER ? bottom :
						state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.FIRE ? topFire :
							state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.SOUL ? topSoul :
								state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.CINDERGLEE ? topCinderglee : top)
				.weight(1).nextModel()
				.modelFile(state.getValue(AshenSpireBlock.FLIPPED) ? (
					state.getValue(AshenSpireBlock.HALF) == DoubleBlockHalf.LOWER ? bottom :
						state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.FIRE ? topFire :
							state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.SOUL ? topSoul :
								state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.CINDERGLEE ? topCinderglee : top)
					:
					state.getValue(AshenSpireBlock.HALF) == DoubleBlockHalf.LOWER ? bottomFlipped :
						state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.FIRE ? topFireFlipped :
							state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.SOUL ? topSoulFlipped :
								state.getValue(AshenSpireBlock.LANTERN) == AshenSpireBlock.LanternEnum.CINDERGLEE ? topCindergleeFlipped : topFlipped)
				.weight(1)
				.build();
		});

		simpleBlock(RisusBlocks.ANGEL_ALTAR.get(), models().getExistingFile(Risus.prefix("block/angel_altar")));
		simpleBlock(RisusBlocks.FLESHY_SPAWNER.get(),  models().getExistingFile(Risus.prefix("block/fleshy_spawner")));
		simpleBlock(RisusBlocks.WEAVER_NEST.get(), models().getExistingFile(Risus.prefix("block/weaver_nest")));
		simpleBlock(RisusBlocks.ORGANIC_MATTER_BLOCK.get(), models().getExistingFile(Risus.prefix("block/organic_matter_block")));
		builtinEntity(RisusBlocks.DEPTH_VASE.get(), Risus.prefix("block/depth_vase"));
		builtinEntity(RisusBlocks.MEMORY1.get(), Risus.prefix("block/memory1"));
		builtinEntity(RisusBlocks.WEAVING_MECHANISM.get(), Risus.prefix("block/weaving_mechanism"));
		beatingHeartBlock(RisusBlocks.BEATING_HEART.get(), Risus.prefix("block/beating_heart/empty"), Risus.prefix("block/beating_heart/wither"), Risus.prefix("block/beating_heart/poison"), Risus.prefix("block/beating_heart/bloodclogged"), Risus.prefix("block/beating_heart/health_boost"), Risus.prefix("block/beating_heart/regen"), Risus.prefix("block/beating_heart/absorption"));
		horizontalBlock(RisusBlocks.INACTIVE_HOLDER.get(), models().getExistingFile(Risus.prefix("block/inactive_holder")));
		horizontalBlock(RisusBlocks.BABY_RIBCAGE.get(), models().getExistingFile(Risus.prefix("block/baby_ribcage")));
		horizontalBlock(RisusBlocks.COPPER_AMALGAM.get(), models().withExistingParent("copper_amalgam", Risus.prefix("block/template_copper_amalgam")).texture("texture", Risus.prefix("block/copper_amalgam")));
		horizontalBlock(RisusBlocks.EXPOSED_COPPER_AMALGAM.get(), models().withExistingParent("exposed_copper_amalgam", Risus.prefix("block/template_copper_amalgam")).texture("texture", Risus.prefix("block/exposed_copper_amalgam")));
		horizontalBlock(RisusBlocks.WEATHERED_COPPER_AMALGAM.get(), models().withExistingParent("weathered_copper_amalgam", Risus.prefix("block/template_copper_amalgam")).texture("texture", Risus.prefix("block/weathered_copper_amalgam")));
		horizontalBlock(RisusBlocks.OXIDIZED_COPPER_AMALGAM.get(), models().withExistingParent("oxidized_copper_amalgam", Risus.prefix("block/template_copper_amalgam")).texture("texture", Risus.prefix("block/oxidized_copper_amalgam")));
		horizontalBlock(RisusBlocks.WAXED_COPPER_AMALGAM.get(), models().getExistingFile(Risus.prefix("block/copper_amalgam")));
		horizontalBlock(RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(), models().getExistingFile(Risus.prefix("block/exposed_copper_amalgam")));
		horizontalBlock(RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(), models().getExistingFile(Risus.prefix("block/weathered_copper_amalgam")));
		horizontalBlock(RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get(), models().getExistingFile(Risus.prefix("block/oxidized_copper_amalgam")));
		rotatingDirectionalBlock(RisusBlocks.CRYSTALLIZED_BONDS.get(), models().getExistingFile(Risus.prefix("block/crystallized_bonds")), models().getExistingFile(Risus.prefix("block/crystallized_bonds_tilted")), 180);
		directionalBlock(RisusBlocks.LIGHT_EXCREMENT.get(), models().getExistingFile(Risus.prefix("block/light_excrement")));

		for (var notch : RisusBlocks.BLOCKS.getEntries().stream().filter(holder -> holder.getRegisteredName().contains("display_notch")).toList()) {
			this.getVariantBuilder(notch.get()).forAllStatesExcept(state -> {
				boolean normal = notch.get() == RisusBlocks.DISPLAY_NOTCH.get();
				boolean invisible = notch.get() == RisusBlocks.INVISIBLE_DISPLAY_NOTCH.get();
				ModelFile base = models().withExistingParent(notch.getRegisteredName(), Risus.prefix("block/template_display_notch")).renderType("minecraft:cutout")
					.texture("texture", invisible? Risus.prefix("block/mark") : blockTexture(normal ? Blocks.BLACK_WOOL : BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace(notch.getRegisteredName().replace("risus:", "").replace("display_notch", "wool")))));
				Direction dir = state.getValue(BlockStateProperties.FACING);
				return ConfiguredModel.builder()
					.modelFile(base)
					.rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
					.rotationY(dir.getAxis().isVertical() ? 0 : (int) (((dir.toYRot()) + 180) % 360))
					.build();
			}, DisplayNotchBlock.ROTATION, DisplayNotchBlock.FLUIDLOGGED);
		}

		axisBlock(RisusBlocks.ENGRAVED_BASALT.get(), models().getExistingFile(Risus.prefix("block/engraved_basalt")), models().getExistingFile(Risus.prefix("block/engraved_basalt")));
		horizontalBlock(RisusBlocks.MAW_GUTS.get(), models().getExistingFile(Risus.prefix("block/maw_guts")));
		simpleBlock(RisusBlocks.MIRAGE_GRASS_BLOCK.get(), models().withExistingParent(RisusBlocks.MIRAGE_GRASS_BLOCK.getId().getPath(), "block/grass_block").renderType("minecraft:cutout").texture("top", Risus.prefix("block/mirage_grass_block_top")));
		simpleBlock(RisusBlocks.MIRAGE_SAND.get(), cubeAll(RisusBlocks.MIRAGE_SAND.get()));
		simpleBlock(RisusBlocks.MIRAGE_NETHERRACK.get(), cubeAll(RisusBlocks.MIRAGE_NETHERRACK.get()));
		simpleBlock(RisusBlocks.MIRAGE_END_STONE.get(), cubeAll(RisusBlocks.MIRAGE_END_STONE.get()));
		directionalBlock(RisusBlocks.FLATTENED_SCALES_BLOCK.get(), models().cubeBottomTop("flattened_scales_block", Risus.prefix("block/flat_scales_block_side"), Risus.prefix("block/flat_scales_block_bottom"), Risus.prefix("block/flat_scales_block_top")));
		directionalBlock(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(), models().getExistingFile(Risus.prefix("block/flattened_scales_block")));
		stairsBlock(RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS.get(), Risus.prefix("block/flat_scales_block_side"));
		slabBlock(RisusBlocks.IMITATION_SCALES_BLOCK_SLAB.get(), Risus.prefix("block/flat_scales_block_side"), Risus.prefix("block/flat_scales_block_side"));
		risusWallBlock(RisusBlocks.IMITATION_SCALES_BLOCK_WALL.get(), models().wallPost("imitation_scale_wall_post",Risus.prefix("block/flat_scales_block_side")), models().wallSide("imitation_scale_wall_side", Risus.prefix("block/flat_scales_block_side")), models().wallSideTall("imitation_scale_wall_side_tall", Risus.prefix("block/flat_scales_block_side")));
		directionalBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK.get(), models().cubeBottomTop("unalloyed_scales_block", Risus.prefix("block/unalloyed_scales/side"), Risus.prefix("block/unalloyed_scales/bottom"), Risus.prefix("block/unalloyed_scales/top")));
		stairsBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_STAIRS.get(), Risus.prefix("block/unalloyed_scales/side"));
		slabBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_SLAB.get(), Risus.prefix("block/unalloyed_scales_block_side"), Risus.prefix("block/unalloyed_scales/side"));
		risusWallBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_WALL.get(), models().wallPost("unalloyed_scales_wall_post",Risus.prefix("block/unalloyed_scales/side")), models().wallSide("unalloyed_scales_wall_side", Risus.prefix("block/unalloyed_scales/side")), models().wallSideTall("unalloyed_scales_wall_side_tall", Risus.prefix("block/unalloyed_scales/side")));
		simpleBlock(RisusBlocks.BLOODWYRM_HEAD.get(), models().getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));
		simpleBlock(RisusBlocks.BLOODWYRM_WALL_HEAD.get(), models().getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));
		simpleBlock(RisusBlocks.TESSERACT.get(), models().getExistingFile(Risus.prefix("block/tesseract")));
		axisBlock(RisusBlocks.BONDKNOT_LOG.get(), Risus.prefix("block/bondknot_log"), Risus.prefix("block/bondknot_log_top"));
		axisBlock(RisusBlocks.BONDKNOT_WOOD.get(), Risus.prefix("block/bondknot_log"), Risus.prefix("block/bondknot_log"));
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
						case WEST -> poppingD;
						case EAST -> poppingU;
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
		axisBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG.get(), Risus.prefix("block/stripped_bondknot_log"), Risus.prefix("block/stripped_bondknot_log_top"));
		axisBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), Risus.prefix("block/stripped_bondknot_log"), Risus.prefix("block/stripped_bondknot_log"));
		simpleBlock(RisusBlocks.BONDKNOT_PLANKS.get());
		fenceBlock(RisusBlocks.BONDKNOT_FENCE.get(), Risus.prefix("block/bondknot_planks"));
		fenceGateBlock(RisusBlocks.BONDKNOT_FENCE_GATE.get(), Risus.prefix("block/bondknot_planks"));
		pressurePlateBlock(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get(), Risus.prefix("block/bondknot_planks"));
		buttonBlock(RisusBlocks.BONDKNOT_BUTTON.get(), Risus.prefix("block/bondknot_planks"));
		trapdoorBlockWithRenderType(RisusBlocks.BONDKNOT_TRAPDOOR.get(), Risus.prefix("block/bondknot_trapdoor"), true, ResourceLocation.withDefaultNamespace("cutout"));
			doorBlockWithRenderType(RisusBlocks.BONDKNOT_DOOR.get(), Risus.prefix("block/bondknot_door_bottom"), Risus.prefix("block/bondknot_door_top"), ResourceLocation.withDefaultNamespace("cutout"));
		builtinEntity(RisusBlocks.BONDKNOT_SIGN.get(), Risus.prefix("block/bondknot_planks"));
		builtinEntity(RisusBlocks.BONDKNOT_WALL_SIGN.get(), Risus.prefix("block/bondknot_planks"));
		builtinEntity(RisusBlocks.BONDKNOT_HANGING_SIGN.get(), Risus.prefix("block/stripped_bondknot_log"));
		builtinEntity(RisusBlocks.BONDKNOT_WALL_HANGING_SIGN.get(), Risus.prefix("block/stripped_bondknot_log"));
		stairsBlock(RisusBlocks.BONDKNOT_STAIRS.get(), Risus.prefix("block/bondknot_planks"));
		slabBlock(RisusBlocks.BONDKNOT_SLAB.get(), Risus.prefix("block/bondknot_planks"), Risus.prefix("block/bondknot_planks"));

		simpleBlock(RisusBlocks.GRIMSTONE.get());
		simpleBlock(RisusBlocks.ACTIVE_GRIMSTONE.get());
		simpleBlock(RisusBlocks.GRIMSTONE_BRICKS.get());
		simpleBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		simpleBlock(RisusBlocks.POLISHED_GRIMSTONE.get());
		simpleBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES.get());
		stairsBlock(RisusBlocks.GRIMSTONE_STAIRS.get(), Risus.prefix("block/grimstone"));
		stairsBlock(RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get(), Risus.prefix("block/grimstone_bricks"));
		stairsBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_STAIRS.get(), Risus.prefix("block/grimstone_blood_tiles"));
		stairsBlock(RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get(), Risus.prefix("block/polished_grimstone"));
		slabBlock(RisusBlocks.GRIMSTONE_SLAB.get(), Risus.prefix("block/grimstone"), Risus.prefix("block/grimstone"));
		slabBlock(RisusBlocks.GRIMSTONE_BRICKS_SLAB.get(), Risus.prefix("block/grimstone_bricks"), Risus.prefix("block/grimstone_bricks"));
		slabBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_SLAB.get(), Risus.prefix("block/grimstone_blood_tiles"), Risus.prefix("block/grimstone_blood_tiles"));
		slabBlock(RisusBlocks.POLISHED_GRIMSTONE_SLAB.get(), Risus.prefix("block/polished_grimstone"), Risus.prefix("block/polished_grimstone"));
		simpleBlock(RisusBlocks.CHISELED_GRIMSTONE.get());
		risusWallBlock(RisusBlocks.GRIMSTONE_WALL.get(), models().wallPost("grimstone_wall_post",Risus.prefix("block/grimstone")), models().wallSide("grimstone_wall_side", Risus.prefix("block/grimstone")), models().wallSideTall("grimstone_wall_side_tall", Risus.prefix("block/grimstone")));
		risusWallBlock(RisusBlocks.POLISHED_GRIMSTONE_WALL.get(), models().wallPost("polished_grimstone_wall_post",Risus.prefix("block/polished_grimstone")), models().wallSide("polished_grimstone_wall_side", Risus.prefix("block/polished_grimstone")), models().wallSideTall("polished_grimstone_wall_side_tall", Risus.prefix("block/polished_grimstone")));
		risusWallBlock(RisusBlocks.GRIMSTONE_BRICKS_WALL.get(), models().wallPost("grimstone_bricks_wall_post",Risus.prefix("block/grimstone_bricks")), models().wallSide("grimstone_bricks_wall_side", Risus.prefix("block/grimstone_bricks")), models().wallSideTall("grimstone_bricks_wall_side_tall", Risus.prefix("block/grimstone_bricks")));
		risusWallBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_WALL.get(), models().wallPost("grimstone_blood_tiles_wall_post",Risus.prefix("block/grimstone_blood_tiles")), models().wallSide("grimstone_blood_tiles_wall_side", Risus.prefix("block/grimstone_blood_tiles")), models().wallSideTall("grimstone_blood_tiles_wall_side_tall", Risus.prefix("block/grimstone_blood_tiles")));
		pillarBlock(RisusBlocks.GRIMSTONE_PILLAR.get(), Risus.prefix("block/grimstone_pillar_base"), Risus.prefix("block/grimstone_pillar_no_top"), Risus.prefix("block/grimstone_pillar_no_bottom"), Risus.prefix("block/grimstone_pillar_none"));

		simpleBlock(RisusBlocks.FADING_SHADOW.get());



		superFenceBlock(RisusBlocks.EERIE_FENCE.get(), models().getExistingFile(Risus.prefix("block/eerie_fence_post")), models().getExistingFile(Risus.prefix("block/eerie_fence_post_down")), models().getExistingFile(Risus.prefix("block/eerie_fence_side")), models().getExistingFile(Risus.prefix("block/eerie_fence_side_tall")), models().getExistingFile(Risus.prefix("block/eerie_fence_side_down")), models().getExistingFile(Risus.prefix("block/eerie_fence_side_down_tall")));
		superFenceBlock(RisusBlocks.DARK_FENCE.get(), models().getExistingFile(Risus.prefix("block/eerie_fence_post")), models().getExistingFile(Risus.prefix("block/eerie_fence_post_down")), models().getExistingFile(Risus.prefix("block/dark_fence_side")), models().getExistingFile(Risus.prefix("block/dark_fence_side_tall")), models().getExistingFile(Risus.prefix("block/dark_fence_side_down")), models().getExistingFile(Risus.prefix("block/dark_fence_side_down_tall")));
		trapdoorBlock(RisusBlocks.EERIE_TRAPDOOR.get(),  models().getExistingFile(Risus.prefix("block/eerie_trapdoor_bottom")), models().getExistingFile(Risus.prefix("block/eerie_trapdoor_top")), models().getExistingFile(Risus.prefix("block/eerie_trapdoor_open")), true);
		trapdoorBlock(RisusBlocks.DARK_TRAPDOOR.get(),  models().getExistingFile(Risus.prefix("block/dark_trapdoor_bottom")), models().getExistingFile(Risus.prefix("block/dark_trapdoor_top")), models().getExistingFile(Risus.prefix("block/dark_trapdoor_open")), true);
		gateBlock(RisusBlocks.EERIE_GATE.get(),  models().getExistingFile(Risus.prefix("block/eerie_gate")),  models().getExistingFile(Risus.prefix("block/eerie_gate_open")),  models().getExistingFile(Risus.prefix("block/eerie_gate_tall")),  models().getExistingFile(Risus.prefix("block/eerie_gate_open_tall")));
		gateBlock(RisusBlocks.DARK_GATE.get(),  models().getExistingFile(Risus.prefix("block/dark_gate")),  models().getExistingFile(Risus.prefix("block/dark_gate_open")),  models().getExistingFile(Risus.prefix("block/dark_gate_tall")),  models().getExistingFile(Risus.prefix("block/dark_gate_open_tall")));
		largeGateBlock(RisusBlocks.EERIE_LARGE_GATE.get(),  models().getExistingFile(Risus.prefix("block/eerie_large_gate_bottom")),  models().getExistingFile(Risus.prefix("block/eerie_large_gate_bottom_open")), models().getExistingFile(Risus.prefix("block/eerie_large_gate_bottom_open_reverse")),models().getExistingFile(Risus.prefix("block/eerie_large_gate_top")), models().getExistingFile(Risus.prefix("block/eerie_large_gate_top_open")), models().getExistingFile(Risus.prefix("block/eerie_large_gate_top_open_reverse")));
		largeGateBlock(RisusBlocks.DARK_LARGE_GATE.get(),  models().getExistingFile(Risus.prefix("block/dark_large_gate_bottom")),  models().getExistingFile(Risus.prefix("block/dark_large_gate_bottom_open")), models().getExistingFile(Risus.prefix("block/dark_large_gate_bottom_open_reverse")),models().getExistingFile(Risus.prefix("block/dark_large_gate_top")), models().getExistingFile(Risus.prefix("block/dark_large_gate_top_open")), models().getExistingFile(Risus.prefix("block/dark_large_gate_top_open_reverse")));

		simpleBlockWithRenderType(RisusBlocks.BOND_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.BOND_GLASS_PANE.get(), Risus.prefix("block/bond_glass"), Risus.prefix("block/bond_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.CONTAINMENT_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.CONTAINMENT_GLASS_PANE.get(), Risus.prefix("block/containment_glass"), Risus.prefix("block/containment_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));

		simpleBlockWithRenderType(RisusBlocks.WHITE_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.GRAY_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.BLACK_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.BROWN_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.RED_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.ORANGE_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.YELLOW_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.LIME_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.GREEN_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.CYAN_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.BLUE_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.PURPLE_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.MAGENTA_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.PINK_MOSAIC_GLASS.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.WHITE_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.LIGHT_GRAY_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.GRAY_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.BLACK_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.BROWN_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.RED_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.ORANGE_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.YELLOW_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.LIME_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.GREEN_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.CYAN_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.LIGHT_BLUE_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.BLUE_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.PURPLE_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.MAGENTA_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		simpleBlockWithRenderType(RisusBlocks.PINK_MOSAIC_LAMP.get(), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.WHITE_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/white_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/light_gray_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.GRAY_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/gray_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.BLACK_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/black_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.BROWN_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/brown_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.RED_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/red_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.ORANGE_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/orange_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.YELLOW_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/yellow_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.LIME_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/lime_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.GREEN_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/green_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.CYAN_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/cyan_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/light_blue_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.BLUE_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/blue_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.PURPLE_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/purple_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.MAGENTA_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/magenta_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));
		betterPaneBlockWithRenderType(RisusBlocks.PINK_MOSAIC_GLASS_PANE.get(), Risus.prefix("block/pink_mosaic_glass"), Risus.prefix("block/mosaic_glass_pane_top"), ResourceLocation.withDefaultNamespace("translucent"));


		horizontalBlock(RisusBlocks.CURVED_RITUAL_BLOCK.get(), models().withExistingParent("curved_ritual_block", ResourceLocation.withDefaultNamespace("block/template_glazed_terracotta")).texture("pattern", Risus.prefix("block/curved_ritual_block")));
		axisBlock((RotatedPillarBlock) RisusBlocks.LINEAR_RITUAL_BLOCK.get(), Risus.prefix("block/linear_ritual_block_side"), Risus.prefix("block/linear_ritual_block_top"));

		simpleBlock(RisusBlocks.SCAB.get());
		simpleBlock(RisusBlocks.COAGULATED_BLOOD_BLOCK.get());
		builtinEntity(RisusBlocks.RITUAL.get(), Risus.prefix("block/ashen_remains"));
		builtinEntity(RisusBlocks.COALIFICATION.get(), Risus.prefix("block/blank"));

		simpleBlock(RisusBlocks.NEURON_HEAD.get(), models().cross("neuron_head", Risus.prefix("block/neuron_head")).renderType("minecraft:cutout"));
		simpleBlock(RisusBlocks.NEURON_STEM.get(), models().cross("neuron", Risus.prefix("block/neuron")).renderType("minecraft:cutout"));
		simpleBlock(RisusBlocks.VEINS_END.get(), models().cross("veins_end", Risus.prefix("block/veins_end")).renderType("minecraft:cutout"));
		simpleBlock(RisusBlocks.VEINS.get(), models().cross("veins", Risus.prefix("block/veins")).renderType("minecraft:cutout"));

		simpleBlock(RisusBlocks.TISSUE.get());
		axisBlock(RisusBlocks.GRILLED_TISSUE.get(), Risus.prefix("block/grilled_tissue"), Risus.prefix("block/grilled_tissue_top"));
		axisBlock(RisusBlocks.PEELED_GRILLED_TISSUE.get(), Risus.prefix("block/peeled_grilled_tissue"), Risus.prefix("block/peeled_grilled_tissue_top"));
		stairsBlock(RisusBlocks.TISSUE_STAIRS.get(), Risus.prefix("block/tissue"));
		slabBlock(RisusBlocks.TISSUE_SLAB.get(), Risus.prefix("block/tissue"), Risus.prefix("block/tissue"));
		risusWallBlock(RisusBlocks.TISSUE_WALL.get(), models().wallPost("tissue_wall_post",Risus.prefix("block/tissue")), models().wallSide("tissue_wall_side", Risus.prefix("block/tissue")), models().wallSideTall("tissue_wall_side_tall", Risus.prefix("block/tissue")));
		risusWallBlock(RisusBlocks.ROTTING_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_tall_side")));
		risusWallBlock(RisusBlocks.DECOMPOSING_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_tall_side")));
		risusWallBlock(RisusBlocks.DECAYING_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_tall_side")));
		simpleBlock(RisusBlocks.LIVING_TISSUE.get(), models().cubeAll("living_tissue", Risus.prefix("block/tissue")));
		risusWallBlock(RisusBlocks.ROTTED_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/rotting_tissue_tall_side")));
		risusWallBlock(RisusBlocks.DECOMPOSED_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decomposing_tissue_tall_side")));
		risusWallBlock(RisusBlocks.DECAYED_TISSUE.get(), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_post")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_side")), models().getExistingFile(Risus.prefix("block/tissue/decaying_tissue_tall_side")));
		risusWallBlock(RisusBlocks.BONE_WALL.get(), models().getExistingFile(Risus.prefix("block/tissue/bone_wall_post")), models().getExistingFile(Risus.prefix("block/tissue/bone_wall_side")), models().getExistingFile(Risus.prefix("block/tissue/bone_wall_tall_side")));
		stairsBlock(RisusBlocks.BONE_STAIRS.get(), Risus.prefix("block/bone_block_side"), Risus.prefix("block/bone_block_top"), Risus.prefix("block/bone_block_top"));
		slabBlock(RisusBlocks.BONE_SLAB.get(), ResourceLocation.withDefaultNamespace("block/bone_block"), Risus.prefix("block/bone_block_side"), Risus.prefix("block/bone_block_top"), Risus.prefix("block/bone_block_top"));
		axisBlock(RisusBlocks.FULL_BONE_BLOCK.get(), Risus.prefix("block/bone_block_side"), Risus.prefix("block/bone_block_side"));
		slabBlock(RisusBlocks.FULL_BONE_SLAB.get(), Risus.prefix("block/full_bone_block"), Risus.prefix("block/bone_block_side"), Risus.prefix("block/bone_block_side"), Risus.prefix("block/bone_block_side"));
		stairsBlock(RisusBlocks.FULL_BONE_STAIRS.get(), Risus.prefix("block/bone_block_side"), Risus.prefix("block/bone_block_side"), Risus.prefix("block/bone_block_side"));
		pillarBlock(RisusBlocks.BONE_PILLAR.get(), Risus.prefix("block/bone_pillar_base"), Risus.prefix("block/bone_pillar_no_top"), Risus.prefix("block/bone_pillar_no_bottom"), Risus.prefix("block/bone_pillar_none"));

		axisBlock(RisusBlocks.FOSSIL.get(), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_top"));
		risusWallBlock(RisusBlocks.FOSSIL_WALL.get(), models().getExistingFile(Risus.prefix("block/tissue/fossil_wall_post")), models().getExistingFile(Risus.prefix("block/tissue/fossil_wall_side")), models().getExistingFile(Risus.prefix("block/tissue/fossil_wall_tall_side")));
		stairsBlock(RisusBlocks.FOSSIL_STAIRS.get(), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_top"), Risus.prefix("block/fossil_top"));
		slabBlock(RisusBlocks.FOSSIL_SLAB.get(), Risus.prefix("block/fossil"), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_top"), Risus.prefix("block/fossil_top"));
		axisBlock(RisusBlocks.FULL_FOSSIL.get(), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_side"));
		slabBlock(RisusBlocks.FULL_FOSSIL_SLAB.get(), Risus.prefix("block/full_fossil"), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_side"));
		stairsBlock(RisusBlocks.FULL_FOSSIL_STAIRS.get(), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_side"), Risus.prefix("block/fossil_side"));
		pillarBlock(RisusBlocks.FOSSIL_PILLAR.get(), Risus.prefix("block/fossil_pillar_base"), Risus.prefix("block/fossil_pillar_no_top"), Risus.prefix("block/fossil_pillar_no_bottom"), Risus.prefix("block/fossil_pillar_none"));

		getVariantBuilder(RisusBlocks.FOSSIL_FRAGMENTED.get()).forAllStates(state -> {
			ModelFile fragmented_0 = models().cubeColumn(this.name(RisusBlocks.FOSSIL_FRAGMENTED.get())+"_0", Risus.prefix("block/fossil_fragmented_0"), Risus.prefix("block/fossil_top"));
			ModelFile fragmented_1 = models().cubeColumn(this.name(RisusBlocks.FOSSIL_FRAGMENTED.get())+"_1", Risus.prefix("block/fossil_fragmented_1"), Risus.prefix("block/fossil_top"));
			ModelFile fragmented_2 = models().cubeColumn(this.name(RisusBlocks.FOSSIL_FRAGMENTED.get())+"_2", Risus.prefix("block/fossil_fragmented_2"), Risus.prefix("block/fossil_top"));
			ModelFile fragmented_3 = models().cubeColumn(this.name(RisusBlocks.FOSSIL_FRAGMENTED.get())+"_3", Risus.prefix("block/fossil_fragmented_3"), Risus.prefix("block/fossil_top"));
			ModelFile fragmented_4 = models().cubeColumn(this.name(RisusBlocks.FOSSIL_FRAGMENTED.get())+"_4", Risus.prefix("block/fossil_fragmented_4"), Risus.prefix("block/fossil_top"));
			ModelFile fragmented_5 = models().cubeColumn(this.name(RisusBlocks.FOSSIL_FRAGMENTED.get())+"_5", Risus.prefix("block/fossil_fragmented_5"), Risus.prefix("block/fossil_top"));
			Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
			return ConfiguredModel.builder()
					.modelFile(fragmented_0).weight(1).rotationX(axis == Direction.Axis.Y ? 0 : 90).rotationY(axis == Direction.Axis.X ? 90 : 0)
					.nextModel().modelFile(fragmented_1).weight(1).rotationX(axis == Direction.Axis.Y ? 0 : 90).rotationY(axis == Direction.Axis.X ? 90 : 0)
					.nextModel().modelFile(fragmented_2).weight(1).rotationX(axis == Direction.Axis.Y ? 0 : 90).rotationY(axis == Direction.Axis.X ? 90 : 0)
					.nextModel().modelFile(fragmented_3).weight(1).rotationX(axis == Direction.Axis.Y ? 0 : 90).rotationY(axis == Direction.Axis.X ? 90 : 0)
					.nextModel().modelFile(fragmented_4).weight(1).rotationX(axis == Direction.Axis.Y ? 0 : 90).rotationY(axis == Direction.Axis.X ? 90 : 0)
					.nextModel().modelFile(fragmented_5).weight(1).rotationX(axis == Direction.Axis.Y ? 0 : 90).rotationY(axis == Direction.Axis.X ? 90 : 0)
					.build();
		});

		//keep below eye stuff to have custom head display
		directionalBlock(RisusBlocks.EYE_ENDER.get(), models().getExistingFile(Risus.prefix("block/eye/ender")));
		directionalBlock(RisusBlocks.EYE_BLEACHED.get(), models().getExistingFile(Risus.prefix("block/eye/bleached")));
		directionalBlock(RisusBlocks.EYE_BLOODSHOT.get(), models().getExistingFile(Risus.prefix("block/eye/bloodshot")));
		directionalBlock(RisusBlocks.EYE_GOLDEN.get(), models().getExistingFile(Risus.prefix("block/eye/golden")));
		directionalBlock(RisusBlocks.EYE_EMERALD.get(), models().getExistingFile(Risus.prefix("block/eye/emerald")));

		directionalBlock(RisusBlocks.EYE_ENDER_GLOWING.get(), models().getExistingFile(Risus.prefix("block/eye/ender_glowing")));
		directionalBlock(RisusBlocks.EYE_BLEACHED_GLOWING.get(), models().getExistingFile(Risus.prefix("block/eye/bleached_glowing")));
		directionalBlock(RisusBlocks.EYE_BLOODSHOT_GLOWING.get(), models().getExistingFile(Risus.prefix("block/eye/bloodshot_glowing")));
		directionalBlock(RisusBlocks.EYE_GOLDEN_GLOWING.get(), models().getExistingFile(Risus.prefix("block/eye/golden_glowing")));
		directionalBlock(RisusBlocks.EYE_EMERALD_GLOWING.get(), models().getExistingFile(Risus.prefix("block/eye/emerald_glowing")));

		directionalBlock(RisusBlocks.FLESHY_SKIN.get(), models().cubeBottomTop("fleshy_skin", Risus.prefix("block/side_fleshy_skin"), Risus.prefix("block/tissue"), Risus.prefix("block/skin")));
		simpleBlock(RisusBlocks.SKIN.get());
		simpleBlock(RisusBlocks.HAIRY_SKIN.get(), models().getExistingFile(Risus.prefix("block/hairy_skin")));
		directionalBlock(RisusBlocks.HAIRY_FLESHY_SKIN.get(), models().getExistingFile(Risus.prefix("block/hairy_fleshy_skin")));
		axisBlock(RisusBlocks.BUNDLE_OF_HAIR.get(), Risus.prefix("block/bundle_of_hair_side"), Risus.prefix("block/bundle_of_hair_top"));


		horizontalBlock(RisusBlocks.HEART_TRANSPLANT.get(), models().getExistingFile(Risus.prefix("block/heart_transplant")));
		simpleBlock(RisusBlocks.POTTED_HEART_TRANSPLANT.get(), models().getExistingFile(Risus.prefix("block/potted_heart_transplant")));
		simpleBlock(RisusBlocks.REGEN_ROSE.get(), models().cross("regen_rose", Risus.prefix("block/regen_rose")).renderType("cutout"));
		simpleBlock(RisusBlocks.POTTED_REGEN_ROSE.get(), models().withExistingParent("potted_regen_rose", "block/flower_pot_cross").texture("plant", Risus.prefix("block/regen_rose")).renderType("cutout"));
		getVariantBuilder(RisusBlocks.BIG_CHAIN.get())
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
				.modelForState().modelFile(models().getExistingFile(Risus.prefix("block/big_chain"))).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
				.modelForState().modelFile(models().getExistingFile(Risus.prefix("block/big_chain"))).rotationX(90).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
				.modelForState().modelFile(models().getExistingFile(Risus.prefix("block/big_chain"))).rotationX(90).rotationY(90).addModel();
		torchBlock(RisusBlocks.JOYFLAME_TORCH, RisusBlocks.JOYFLAME_WALL_TORCH);
		getVariantBuilder(RisusBlocks.JOYFLAME_CAMPFIRE.get()).forAllStatesExcept(state -> {
			ModelFile on = models().withExistingParent("joyflame_campfire", ResourceLocation.withDefaultNamespace("block/template_campfire")).texture("fire", Risus.prefix("block/joyflame_campfire_fire")).texture("lit_log", Risus.prefix("block/joyflame_campfire_log_lit")).renderType("minecraft:cutout");
			ModelFile off = models().withExistingParent("joyflame_campfire_off", ResourceLocation.withDefaultNamespace("block/campfire_off")).renderType("minecraft:cutout");
			return ConfiguredModel.builder().modelFile(state.getValue(CampfireBlock.LIT) ? on : off).rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build();
		}, CampfireBlock.WATERLOGGED, CampfireBlock.SIGNAL_FIRE);
		getVariantBuilder(RisusBlocks.JOYFLAME_LANTERN.get()).forAllStatesExcept(state -> {
			ModelFile normal = models().withExistingParent("joyflame_lantern", ResourceLocation.withDefaultNamespace("block/template_lantern")).texture("lantern", Risus.prefix("block/joyflame_lantern")).renderType("minecraft:cutout");
			ModelFile hanging = models().withExistingParent("joyflame_lantern_hanging", ResourceLocation.withDefaultNamespace("block/template_hanging_lantern")).texture("lantern", Risus.prefix("block/joyflame_lantern")).renderType("minecraft:cutout");
			return ConfiguredModel.builder().modelFile(state.getValue(LanternBlock.HANGING) ? hanging : normal).build();
		}, LanternBlock.WATERLOGGED);

		getVariantBuilder(RisusBlocks.ZIT.get()).forAllStates(state -> {
			ModelFile normal = models().getExistingFile(Risus.prefix("block/zit"));
			ModelFile popped = models().getExistingFile(Risus.prefix("block/zit_popped"));
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

		ModelFile floor = models().withExistingParent("joyflame_fire_floor", ResourceLocation.withDefaultNamespace("block/template_fire_floor")).texture("fire", Risus.prefix("block/joyflame_fire")).renderType("minecraft:cutout");
		ModelFile side = models().withExistingParent("joyflame_fire_side", ResourceLocation.withDefaultNamespace("block/template_fire_side")).texture("fire", Risus.prefix("block/joyflame_fire")).renderType("minecraft:cutout");
		ModelFile sideAlt = models().withExistingParent("joyflame_fire_side_alt", ResourceLocation.withDefaultNamespace("block/template_fire_side_alt")).texture("fire", Risus.prefix("block/joyflame_fire")).renderType("minecraft:cutout");

		getMultipartBuilder(RisusBlocks.JOYFLAME_FIRE.get())
				.part().modelFile(floor).addModel().end()
				.part().modelFile(side).nextModel().modelFile(sideAlt).addModel().end()
				.part().modelFile(side).rotationY(90).nextModel().modelFile(sideAlt).rotationY(90).addModel().end()
				.part().modelFile(side).rotationY(180).nextModel().modelFile(sideAlt).rotationY(180).addModel().end()
				.part().modelFile(side).rotationY(270).nextModel().modelFile(sideAlt).rotationY(270).addModel().end();

	}

	protected void builtinEntity(Block b, ResourceLocation particle) {
		simpleBlock(b, models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath())
			.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
			.texture("particle", particle));
	}

	protected void beatingHeartBlock(Block b, ResourceLocation particle0, ResourceLocation particle1, ResourceLocation particle2, ResourceLocation particle3, ResourceLocation particle4, ResourceLocation particle5, ResourceLocation particle6) {
		getVariantBuilder(b).forAllStates(state -> {
			ModelFile model = switch (state.getValue(BeatingHeartBlock.HealthEffectEnum.HEALTH_EFFECT)) {
				case WITHER -> models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath()+"_1")
					.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
					.texture("particle", particle1);
				case POISON -> models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath()+"_2")
					.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
					.texture("particle", particle2);
				case BLOODCLOGGED -> models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath()+"_3")
					.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
					.texture("particle", particle3);
				case HEALTH_BOOST -> models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath()+"_4")
					.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
					.texture("particle", particle4);
				case REGEN -> models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath()+"_5")
					.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
					.texture("particle", particle5);
				case ABSORPTION -> models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath()+"_6")
					.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
					.texture("particle", particle6);
				default -> models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath()+"_0")
					.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
					.texture("particle", particle0);
			};
			return ConfiguredModel.builder().modelFile(model).build();
		});
	}

	public void torchBlock(Supplier<? extends Block> block, Supplier<? extends Block> wall) {
		ModelFile torch = models().torch(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), Risus.prefix("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath())).renderType("cutout");
		ModelFile torchwall = models().torchWall(BuiltInRegistries.BLOCK.getKey(wall.get()).getPath(), Risus.prefix("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath())).renderType("cutout");
		simpleBlock(block.get(), torch);
		getVariantBuilder(wall.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(torchwall)
						.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
						.build());
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
	public void risusWallBlock(RisusWallBlock block, ModelFile post, ModelFile side, ModelFile sideTall) {
		MultiPartBlockStateBuilder builder = (this.getMultipartBuilder(block).part().modelFile(post).addModel()).condition(RisusWallBlock.UP, new Boolean[]{true}).end();
		WALL_PROPS.entrySet().stream().filter((e) -> (e.getKey()).getAxis().isHorizontal()).forEach((e) -> {
			this.risusWallSidePart(builder, side, e, WallSide.LOW);
			this.risusWallSidePart(builder, sideTall, e, WallSide.TALL);
		});
	}

	private void risusWallSidePart(MultiPartBlockStateBuilder builder, ModelFile model, Map.Entry<Direction, Property<WallSide>> entry, WallSide height) {
		(builder.part().modelFile(model).rotationY(((int)(entry.getKey()).toYRot() + 180) % 360).uvLock(true).addModel()).condition(entry.getValue(), height);
	}

	private void superFenceBlock(SuperFenceBlock block, ModelFile post, ModelFile postDown, ModelFile side, ModelFile sideTall, ModelFile sideDown, ModelFile sideDownTall) {
		//doing this the ugly way cause immutable map thinks he's funny
		(this.getMultipartBuilder(block).part().modelFile(postDown).addModel()).condition(SuperFenceBlock.DOWN, true);
		(this.getMultipartBuilder(block).part().modelFile(post).addModel()).condition(SuperFenceBlock.DOWN, false);

		(this.getMultipartBuilder(block).part().modelFile(side).rotationY(((int)(Direction.EAST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.EAST_WALL, FenceSide.LOW);
		(this.getMultipartBuilder(block).part().modelFile(side).rotationY(((int)(Direction.NORTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.NORTH_WALL, FenceSide.LOW);
		(this.getMultipartBuilder(block).part().modelFile(side).rotationY(((int)(Direction.WEST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.WEST_WALL, FenceSide.LOW);
		(this.getMultipartBuilder(block).part().modelFile(side).rotationY(((int)(Direction.SOUTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.SOUTH_WALL, FenceSide.LOW);

		(this.getMultipartBuilder(block).part().modelFile(sideTall).rotationY(((int)(Direction.EAST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.EAST_WALL, FenceSide.TALL);
		(this.getMultipartBuilder(block).part().modelFile(sideTall).rotationY(((int)(Direction.NORTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.NORTH_WALL, FenceSide.TALL);
		(this.getMultipartBuilder(block).part().modelFile(sideTall).rotationY(((int)(Direction.WEST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.WEST_WALL, FenceSide.TALL);
		(this.getMultipartBuilder(block).part().modelFile(sideTall).rotationY(((int)(Direction.SOUTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.SOUTH_WALL, FenceSide.TALL);

		(this.getMultipartBuilder(block).part().modelFile(sideDown).rotationY(((int)(Direction.EAST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.EAST_WALL, FenceSide.DOWN_LOW);
		(this.getMultipartBuilder(block).part().modelFile(sideDown).rotationY(((int)(Direction.NORTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.NORTH_WALL, FenceSide.DOWN_LOW);
		(this.getMultipartBuilder(block).part().modelFile(sideDown).rotationY(((int)(Direction.WEST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.WEST_WALL, FenceSide.DOWN_LOW);
		(this.getMultipartBuilder(block).part().modelFile(sideDown).rotationY(((int)(Direction.SOUTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.SOUTH_WALL, FenceSide.DOWN_LOW);

		(this.getMultipartBuilder(block).part().modelFile(sideDownTall).rotationY(((int)(Direction.EAST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.EAST_WALL, FenceSide.DOWN_TALL);
		(this.getMultipartBuilder(block).part().modelFile(sideDownTall).rotationY(((int)(Direction.NORTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.NORTH_WALL, FenceSide.DOWN_TALL);
		(this.getMultipartBuilder(block).part().modelFile(sideDownTall).rotationY(((int)(Direction.WEST).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.WEST_WALL, FenceSide.DOWN_TALL);
		(this.getMultipartBuilder(block).part().modelFile(sideDownTall).rotationY(((int)(Direction.SOUTH).toYRot() + 180) % 360).uvLock(true).addModel()).condition(SuperFenceBlock.SOUTH_WALL, FenceSide.DOWN_TALL);
	}

	private void rotatingDirectionalBlock (Block block, ModelFile model, ModelFile tiltedModel, int angleOffset) {
		this.getVariantBuilder(block).forAllStates((state) -> {
			Direction dir = state.getValue(BlockStateProperties.FACING);
			//look, it works, okey?
			return ConfiguredModel.builder()
				.modelFile(model)
				.rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 90 : 0))
				.rotationY(dir.getAxis().isVertical() ? 0 : ((int) dir.toYRot() + angleOffset) % 360)
				.nextModel().modelFile(model)
				.rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 270 : 0))
				.rotationY(dir.getAxis().isVertical() ? 180 : ((int) dir.toYRot() + angleOffset+180) % 360)
				.nextModel().modelFile(tiltedModel)
				.rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 90 : 0))
				.rotationY(dir.getAxis().isVertical() ? 0 : ((int) dir.toYRot() + angleOffset) % 360)
				.nextModel().modelFile(tiltedModel)
				.rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 270 : 0))
				.rotationY(dir.getAxis().isVertical() ? 180 : ((int) dir.toYRot() + angleOffset+180) % 360)
				.build();
		});
	}

	public void betterPaneBlock(RisusBarsBlock block, String name, ResourceLocation pane, ResourceLocation edge) {
		betterPaneBlockInternal(block, name + "_pane", pane, edge);
	}

	public void betterPaneBlockWithRenderType(RisusBarsBlock block, ResourceLocation pane, ResourceLocation edge, ResourceLocation renderType) {
		this.betterPaneBlockInternalWithRenderType(block, BuiltInRegistries.BLOCK.getKey(block).toString(), pane, edge, renderType);
	}

	private void betterPaneBlockInternal(RisusBarsBlock block, String baseName, ResourceLocation pane, ResourceLocation edge) {
		ModelFile post = models().panePost(baseName + "_post", pane, edge);
		ModelFile side = models().paneSide(baseName + "_side", pane, edge);
		ModelFile sideAlt = models().paneSideAlt(baseName + "_side_alt", pane, edge);
		ModelFile noSide = models().paneNoSide(baseName + "_noside", pane);
		ModelFile noSideAlt = models().paneNoSideAlt(baseName + "_noside_alt", pane);
		betterPaneBlock(block, post, side, sideAlt, noSide, noSideAlt);
	}

	private void betterPaneBlockInternalWithRenderType(RisusBarsBlock block, String baseName, ResourceLocation pane, ResourceLocation edge, ResourceLocation renderType) {
		ModelFile post = (this.models().panePost(baseName + "_post", pane, edge)).renderType(renderType);
		ModelFile side = (this.models().paneSide(baseName + "_side", pane, edge)).renderType(renderType);
		ModelFile sideAlt = (this.models().paneSideAlt(baseName + "_side_alt", pane, edge)).renderType(renderType);
		ModelFile noSide = (this.models().paneNoSide(baseName + "_noside", pane)).renderType(renderType);
		ModelFile noSideAlt = (this.models().paneNoSideAlt(baseName + "_noside_alt", pane)).renderType(renderType);
		this.betterPaneBlock(block, post, side, sideAlt, noSide, noSideAlt);
	}

	public void betterPaneBlock(RisusBarsBlock block, ModelFile post, ModelFile side, ModelFile sideAlt, ModelFile noSide, ModelFile noSideAlt) {
		MultiPartBlockStateBuilder builder = (this.getMultipartBuilder(block).part().modelFile(post).addModel()).end();
		PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach((e) -> {
			Direction dir = e.getKey();
			if (dir.getAxis().isHorizontal()) {
				boolean alt = dir == Direction.SOUTH;
				((builder.part().modelFile(!alt && dir != Direction.WEST ? side : sideAlt).rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()).condition(e.getValue(), true).end().part().modelFile(!alt && dir != Direction.EAST ? noSide : noSideAlt).rotationY(dir == Direction.WEST ? 270 : (dir == Direction.SOUTH ? 90 : 0)).addModel()).condition(e.getValue(), false);
			}

		});
	}

	public void gateBlock(RisusGateBlock block, ModelFile gate, ModelFile gateOpen, ModelFile gateTall, ModelFile gateOpenTall) {
		this.getVariantBuilder(block).forAllStatesExcept((state) -> {
			ModelFile model = state.getValue(RisusGateBlock.TALL) ? gateTall : gate;

			if (state.getValue(RisusGateBlock.OPEN)) {
				model = state.getValue(RisusGateBlock.TALL) ? gateOpenTall :  gateOpen;
			}

			return ConfiguredModel.builder().modelFile(model).rotationY((int)(state.getValue(RisusGateBlock.FACING)).toYRot()).uvLock(true).build();
		}, RisusGateBlock.POWERED);
	}

	public void largeGateBlock(LargeGateBlock block, ModelFile bottom, ModelFile bottomOpen, ModelFile bottomReverseOpen, ModelFile top, ModelFile topOpen, ModelFile topReverseOpen) {
		this.getVariantBuilder(block).forAllStatesExcept((state) -> {
			int yRot = (int)(state.getValue(LargeGateBlock.FACING)).toYRot() + 90;
			boolean open = state.getValue(LargeGateBlock.OPEN);
			boolean reverse = state.getValue(LargeGateBlock.REVERSE_OPENING);
			boolean lower = state.getValue(LargeGateBlock.HALF) == DoubleBlockHalf.LOWER;

			yRot %= 360;
			ModelFile model = null;
			if (lower && !open) {
				model = bottom;
			}
			if (lower && open && !reverse) {
				model = bottomOpen;
			} else if (lower && open && reverse) {
				model = bottomReverseOpen;
			}

			if (!lower && !open) {
				model = top;
			}
			if (!lower && open && !reverse) {
				model = topOpen;
			} else if (!lower && open && reverse) {
				model = topReverseOpen;
			}

			return ConfiguredModel.builder().modelFile(model).rotationY(yRot).build();}, LargeGateBlock.POWERED);
	}

	public void pillarBlock(RisusPillarBlock block, ResourceLocation textureBase, ResourceLocation textureNoTop, ResourceLocation textureNoBottom, ResourceLocation textureNone) {
		//separate models are needed for each axis to avoid lighting issues
		ModelFile base = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_base" ,Risus.prefix("block/pillar/pillar_base")).texture("texture", textureBase);
		ModelFile base_x = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_base_x" ,Risus.prefix("block/pillar/pillar_base_x")).texture("texture", textureBase);
		ModelFile base_z = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_base_z" ,Risus.prefix("block/pillar/pillar_base_z")).texture("texture", textureBase);
		ModelFile noTop = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_no_top" ,Risus.prefix("block/pillar/pillar_no_top")).texture("texture", textureNoTop);
		ModelFile noTop_x = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_no_top_x" ,Risus.prefix("block/pillar/pillar_no_top_x")).texture("texture", textureNoTop);
		ModelFile noTop_z = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_no_top_z" ,Risus.prefix("block/pillar/pillar_no_top_z")).texture("texture", textureNoTop);
		ModelFile noBottom = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_no_bottom" ,Risus.prefix("block/pillar/pillar_no_bottom")).texture("texture", textureNoBottom);
		ModelFile noBottom_x = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_no_bottom_x" ,Risus.prefix("block/pillar/pillar_no_bottom_x")).texture("texture", textureNoBottom);
		ModelFile noBottom_z = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_no_bottom_z" ,Risus.prefix("block/pillar/pillar_no_bottom_z")).texture("texture", textureNoBottom);
		ModelFile none = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_none" ,Risus.prefix("block/pillar/pillar_none")).texture("texture", textureNone);
		ModelFile none_x = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_none_x" ,Risus.prefix("block/pillar/pillar_none_x")).texture("texture", textureNone);
		ModelFile none_z = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath()+"_none_z" ,Risus.prefix("block/pillar/pillar_none_z")).texture("texture", textureNone);

		this.getVariantBuilder(block).forAllStates((state) -> {
			boolean top = state.getValue(RisusPillarBlock.TOP);
			boolean bottom = state.getValue(RisusPillarBlock.BOTTOM);
			ModelFile model = null;
			model = switch (state.getValue(RisusPillarBlock.AXIS)) {
				case X -> top && bottom ? none_x : !top && bottom ? noBottom_x : top && !bottom ? noTop_x : base_x;
				case Y -> top && bottom ? none : !top && bottom ? noBottom : top && !bottom ? noTop : base;
				case Z -> top && bottom ? none_z : !top && bottom ? noBottom_z : top && !bottom ? noTop_z : base_z;
			};

			return ConfiguredModel.builder().modelFile(model).build();
		});
	}

	public void simpleBlockWithRenderType(Block block, ResourceLocation type) {
		simpleBlock(block, models().cubeAll(name(block), blockTexture(block)).renderType(type));
	}
	public void simpleBlockWithRenderTypeOtherTexture(Block block, Block block2, ResourceLocation type) {
		simpleBlock(block, models().cubeAll(name(block2), blockTexture(block2)).renderType(type));
	}

	protected String name(Block block) {
		return key(block).getPath();
	}

	protected ResourceLocation key(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block);
	}

	@Nonnull
	@Override
	public String getName() {
		return "Risus blockstates and models";
	}
}
