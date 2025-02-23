package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nullable;
import java.util.Objects;

public class ItemModelGenerator extends ItemModelProvider {

	public ItemModelGenerator(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//blocks
		toBlockModel(RisusBlocks.ALTERATION_CATALYST.get(), Risus.prefix("block/alteration_catalyst_inventory"));
		toBlock(RisusBlocks.ANGEL_ALTAR.get());
		toBlock(RisusBlocks.FLESHY_SPAWNER.get());
		toBlock(RisusBlocks.INACTIVE_HOLDER.get());
		toBlock(RisusBlocks.DISPLAY_NOTCH.get());
		toBlock(RisusBlocks.ORGANIC_MATTER_BLOCK.get());
		toBlock(RisusBlocks.COPPER_AMALGAM.get());
		toBlock(RisusBlocks.EXPOSED_COPPER_AMALGAM.get());
		toBlock(RisusBlocks.WEATHERED_COPPER_AMALGAM.get());
		toBlock(RisusBlocks.OXIDIZED_COPPER_AMALGAM.get());
		toBlockModel(RisusBlocks.WAXED_COPPER_AMALGAM.get(), Risus.prefix("block/copper_amalgam"));
		toBlockModel(RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(), Risus.prefix("block/exposed_copper_amalgam"));
		toBlockModel(RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(), Risus.prefix("block/weathered_copper_amalgam"));
		toBlockModel(RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get(), Risus.prefix("block/oxidized_copper_amalgam"));
		toBlock(RisusBlocks.LAUGHING_STALK.get());
		toBlock(RisusBlocks.ASHEN_REMAINS.get());
		toBlock(RisusBlocks.SMILING_REMAINS.get());
		toBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());
		toBlock(RisusBlocks.ENGRAVED_BASALT.get());
		toBlock(RisusBlocks.MAW_GUTS.get());
		toBlock(RisusBlocks.GLUTTONY_SCALEPLATE.get());
		toBlockModel(RisusBlocks.IMITATION_SCALEPLATE.get(), Risus.prefix("block/gluttony_scaleplate"));
		toBlock(RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get());
		toBlock(RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get());
		toBlock(RisusBlocks.FLATTENED_SCALES_BLOCK.get());
		toBlockModel(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(), Risus.prefix("block/flattened_scales_block"));
		toBlock(RisusBlocks.CRYSTALLIZED_BONDS.get());
		toBlock(RisusBlocks.MIRAGE_GRASS_BLOCK.get());
		toBlock(RisusBlocks.MIRAGE_SAND.get());
		toBlock(RisusBlocks.MIRAGE_NETHERRACK.get());
		toBlock(RisusBlocks.MIRAGE_END_STONE.get());
		toBlockModel(RisusBlocks.BLOODWEAVE.get(), Risus.prefix("block/bloodweave_core"));
		toBlock(RisusBlocks.SCAB.get());
		toBlock(RisusBlocks.COAGULATED_BLOOD_BLOCK.get());
		toBlock(RisusBlocks.WEAVER_NEST.get());
		toBlock(RisusBlocks.BOND_GLASS.get());
		toBlock(RisusBlocks.CONTAINMENT_GLASS.get());
		toBlock(RisusBlocks.BONDKNOT_LOG.get());
		toBlock(RisusBlocks.BONDKNOT_WOOD.get());
		toBlockModel(RisusBlocks.POPPING_BONDKNOT_LOG.get(), Risus.prefix("block/popping_bondknot_log_north"));
		toBlockModel(RisusBlocks.POPPING_BONDKNOT_WOOD.get(), Risus.prefix("block/popping_bondknot_wood_north"));
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
		toBlock(RisusBlocks.BONDKNOT_PLANKS.get());
		toBlock(RisusBlocks.BONDKNOT_SLAB.get());
		toBlock(RisusBlocks.BONDKNOT_STAIRS.get());
		toBlock(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		toBlock(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
		getBuilder(RisusBlocks.BONDKNOT_FENCE.getId().getPath())
				.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/fence_inventory")))
				.texture("texture", Risus.prefix("block/bondknot_planks"));
		getBuilder(RisusBlocks.BONDKNOT_BUTTON.getId().getPath())
				.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/button_inventory")))
				.texture("texture", Risus.prefix("block/bondknot_planks"));
		toBlockModel(RisusBlocks.BONDKNOT_TRAPDOOR.get(), Risus.prefix("block/bondknot_trapdoor_bottom"));
		generated(RisusBlocks.BONDKNOT_DOOR.getId().getPath(), Risus.prefix("item/bondknot_door"));
		generated(RisusBlocks.BONDKNOT_SIGN.getId().getPath(), Risus.prefix("item/bondknot_sign"));
		generated(RisusBlocks.BONDKNOT_HANGING_SIGN.getId().getPath(), Risus.prefix("item/bondknot_hanging_sign"));
		toBlock(RisusBlocks.BABY_RIBCAGE.get());
		toBlock(RisusBlocks.RIBCAGE.get());
		toBlock(RisusBlocks.GRIMSTONE.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.ACTIVE_GRIMSTONE.get());
		toBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.GRIMSTONE_SLAB.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS_SLAB.get());
		toBlock(RisusBlocks.POLISHED_GRIMSTONE_SLAB.get());
		toBlock(RisusBlocks.GRIMSTONE_STAIRS.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get());
		toBlock(RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get());
		getBuilder(RisusBlocks.GRIMSTONE_WALL.getId().getPath())
				.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
				.texture("wall", Risus.prefix("block/grimstone"));
		getBuilder(RisusBlocks.GRIMSTONE_BRICKS_WALL.getId().getPath())
				.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
				.texture("wall", Risus.prefix("block/grimstone_bricks"));
		getBuilder(RisusBlocks.POLISHED_GRIMSTONE_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/polished_grimstone"));
		toBlock(RisusBlocks.CHISELED_GRIMSTONE.get());
		toBlock(RisusBlocks.LINEAR_RITUAL_BLOCK.get());
		toBlockModel(RisusBlocks.CURVED_RITUAL_BLOCK.get(), Risus.prefix("block/curved_ritual_block"));
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
		toBlock(RisusBlocks.TISSUE_STAIRS.get());
		toBlock(RisusBlocks.TISSUE_SLAB.get());
		getBuilder(RisusBlocks.BONE_WALL.getId().getPath())
				.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
				.texture("wall", ResourceLocation.withDefaultNamespace("block/bone_block_side"));
		toBlock(RisusBlocks.BONE_STAIRS.get());
		toBlock(RisusBlocks.BONE_SLAB.get());
		toBlock(RisusBlocks.FULL_BONE_STAIRS.get());
		toBlock(RisusBlocks.FULL_BONE_SLAB.get());
		toBlock(RisusBlocks.FULL_BONE_BLOCK.get());
		toBlock(RisusBlocks.BLOODY_SPONGE.get());

		toBlock(RisusBlocks.EYE_ENDER.get());
		toBlock(RisusBlocks.EYE_BLEACHED.get());
		toBlock(RisusBlocks.EYE_BLOODSHOT.get());
		toBlock(RisusBlocks.EYE_EMERALD.get());
		toBlock(RisusBlocks.EYE_GOLDEN.get());
		toBlock(RisusBlocks.SKIN.get());
		toBlock(RisusBlocks.FLESHY_SKIN.get());
		toBlock(RisusBlocks.CURVED_FLESHY_SKIN.get());
		toBlock(RisusBlocks.HAIRY_SKIN.get());
		toBlock(RisusBlocks.HAIRY_FLESHY_SKIN.get());
		toBlock(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get());
		toBlock(RisusBlocks.TALL_HAIR.get());
		toBlock(RisusBlocks.BUNDLE_OF_HAIR.get());
		getBuilder(RisusBlocks.BONE_WALL.getId().getPath())
				.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
				.texture("wall", ResourceLocation.withDefaultNamespace("block/bone_block_side"));

		//items
		singleTex(RisusItems.RESEARCHERS_NOTES);
		singleTex(RisusItems.MUSIC_DISC_RAK);
		singleTex(RisusItems.MUSIC_DISC_REGN);
		singleTex(RisusItems.MUSIC_DISC_FEIGR);
		singleTex(RisusItems.MUSIC_DISC_MORK);
		singleTex(RisusItems.ANGEL_WINGS).override().predicate(Risus.prefix("broken"), 1.0F).model(singleTex("angel_wings_broken"));
		singleTex(RisusItems.ESSENCE_OF_GLUTTONY);
		singleTex(RisusItems.ESSENCE_OF_GREED);
		singleTex(RisusItems.ESSENCE_OF_SLOTH);
		singleTex(RisusItems.ESSENCE_OF_LUST);
		singleTex(RisusItems.ESSENCE_OF_MELANCHOLY);
		singleTex(RisusItems.EMBODIMENT_OF_COURTSHIP);
		singleTex(RisusItems.EMBODIMENT_OF_INTIMACY);
		singleTex(RisusItems.EMBODIMENT_OF_DEVOTION);
		singleTex(RisusItems.BLOOD_FEATHER);
		singleTex(RisusItems.HAND_OF_GREED);
		singleTex(RisusItems.BLOOD_BUCKET);
		singleTex(RisusItems.BONDKNOT_BOAT);
		singleTex(RisusItems.GUTS_BOAT);
		singleTex(RisusItems.CONCENTRATION_CORE);
		singleTex(RisusItems.CRYSTALLIZED_BOND);
		singleTex(RisusItems.GLUTTONY_SCALES);
		singleTex(RisusItems.GUILTY_APPLE);
		singleTex(RisusItems.MEMORY_CORE);
		singleTex(RisusItems.ORGANIC_MATTER);
		trimmedArmor(RisusItems.SKIN_HELMET);
		trimmedArmor(RisusItems.SKIN_CHESTPLATE);
		trimmedArmor(RisusItems.SKIN_LEGGINGS);
		trimmedArmor(RisusItems.SKIN_BOOTS);
		singleTex(RisusItems.SMILE_PATTERN);
		singleTex(RisusItems.DIVINITY_PATTERN);
		singleTex(RisusItems.TREE_PATTERN);
		singleTex(RisusItems.STALKER_EYE);
		singleTex(RisusItems.EYE_SANDWICH);
		singleTex(RisusItems.EGG_SAC);
		singleTex(RisusItems.SACRIFICE_CATALYST);
		singleTex(RisusItems.TOTEM_OF_UNYIELDING);
		singleTexTool(RisusItems.CRESCENT_DISASTER);
		ItemModelBuilder crescent = nested().parent(getExistingFile(Risus.prefix("item/base_axe_model"))).texture("axe", Risus.prefix("entity/crescent_disaster"));
		withExistingParent(RisusItems.CRESCENT_DISASTER.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
				.base(generated("crescent_disaster_base", Risus.prefix("item/crescent_disaster")))
				.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, crescent)
				.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, crescent)
				.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, crescent)
				.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, crescent)
				.perspective(ItemDisplayContext.HEAD, crescent)
				.end();
		singleTexTool(RisusItems.BOOMSTICK);
		ItemModelBuilder boomstick = nested().parent(getExistingFile(Risus.prefix("item/boomstick_held"))).texture("boomstick", Risus.prefix("item/boomstick"));
		withExistingParent(RisusItems.BOOMSTICK.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
			.base(generated("boomstick_base", Risus.prefix("item/boomstick_item")))
			.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, boomstick)
			.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, boomstick)
			.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, boomstick)
			.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, boomstick)
			.perspective(ItemDisplayContext.HEAD, boomstick)
			.end();
		singleTexTool(RisusItems.SCYTHE);
		ItemModelBuilder scythe = withExistingParent("scythe_held", Risus.prefix("item/template_held_scythe")).texture("texture", Risus.prefix("item/scythe"));
		withExistingParent(RisusItems.SCYTHE.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
			.base(generated("scythe_base", Risus.prefix("item/scythe_item")))
			.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, scythe)
			.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, scythe)
			.perspective(ItemDisplayContext.HEAD, scythe)
			.end();
		singleTexTool(RisusItems.SOUL_SCYTHE);
		ItemModelBuilder soul_scythe = withExistingParent("soul_scythe_held", Risus.prefix("item/template_held_scythe")).texture("texture", Risus.prefix("item/soul_scythe"));
		withExistingParent(RisusItems.SOUL_SCYTHE.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
			.base(generated("soul_scythe_base", Risus.prefix("item/soul_scythe_item")))
			.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, soul_scythe)
			.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, soul_scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, soul_scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, soul_scythe)
			.perspective(ItemDisplayContext.HEAD, soul_scythe)
			.end();
		singleTexTool(RisusItems.FIRE_SCYTHE);
		ItemModelBuilder fire_scythe = withExistingParent("fire_scythe_held", Risus.prefix("item/template_held_scythe")).texture("texture", Risus.prefix("item/fire_scythe"));
		withExistingParent(RisusItems.FIRE_SCYTHE.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
			.base(generated("fire_scythe_base", Risus.prefix("item/fire_scythe_item")))
			.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, fire_scythe)
			.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, fire_scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, fire_scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, fire_scythe)
			.perspective(ItemDisplayContext.HEAD, fire_scythe)
			.end();
		singleTexTool(RisusItems.CINDERGLEE_SCYTHE);
		ItemModelBuilder cinderglee_scythe = withExistingParent("cinderglee_scythe_held", Risus.prefix("item/template_held_scythe")).texture("texture", Risus.prefix("item/cinderglee_scythe"));
		withExistingParent(RisusItems.CINDERGLEE_SCYTHE.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
			.base(generated("cinderglee_scythe_base", Risus.prefix("item/cinderglee_scythe_item")))
			.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, cinderglee_scythe)
			.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, cinderglee_scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, cinderglee_scythe)
			.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, cinderglee_scythe)
			.perspective(ItemDisplayContext.HEAD, cinderglee_scythe)
			.end();
		ItemModelBuilder thousand_blade = nested().parent(getExistingFile(Risus.prefix("item/thousand_blade_held"))).texture("thousand_blade", Risus.prefix("item/intact_thousand_blade"));
		withExistingParent(RisusItems.THOUSAND_BLADE.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
			.base(generated("thousand_blade_base", Risus.prefix("item/thousand_blade_item")))
			.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, thousand_blade)
			.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, thousand_blade)
			.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, thousand_blade)
			.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, thousand_blade)
			.perspective(ItemDisplayContext.HEAD, thousand_blade)
			.end();
		ItemModelBuilder unawakened = nested().parent(getExistingFile(Risus.prefix("item/base_axe_model"))).texture("axe", Risus.prefix("entity/unawakened_vessel"));
		withExistingParent(RisusItems.UNAWAKENED_VESSEL.getId().getPath(), "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
				.base(generated("unawakened_vessel_base", Risus.prefix("item/unawakened_vessel")))
				.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, unawakened)
				.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, unawakened)
				.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, unawakened)
				.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, unawakened)
				.perspective(ItemDisplayContext.HEAD, unawakened)
				.end();
		singleTex(RisusItems.SMILE);
		singleTex(RisusBlocks.JOYFLAME_CAMPFIRE);
		singleTex(RisusBlocks.JOYFLAME_LANTERN);
		singleTex(RisusItems.JOYFLAME_TORCH);
		singleTex(RisusBlocks.HEART_TRANSPLANT);
		singleTex(RisusItems.VEINS);
		singleTex(RisusBlocks.BIG_CHAIN);
		generatedRenderType(RisusBlocks.SPREADING_REMAINS.getId().getPath(), "minecraft:translucent", Risus.prefix("item/spreading_remains"));
		singleTex(RisusItems.MEMORY1_ITEM);
		singleTex(RisusBlocks.REGEN_ROSE);
		singleTex(RisusItems.LIGHT_DEVOURER);
		singleTex(RisusItems.ENDLESS_PEARL);
		singleTex(RisusBlocks.TEETH);

		singleTex(RisusBlocks.NEURON_HEAD.asItem());
		singleTex(RisusItems.HAIR_FOLLICLES);


		//spawn eggs
		for (DeferredHolder<Item, ?> item : RisusEntities.SPAWN_EGGS.getEntries()) {
			if (item.get() instanceof SpawnEggItem) {
				getBuilder(item.getId().getPath()).parent(getExistingFile(ResourceLocation.withDefaultNamespace("item/template_spawn_egg")));
			}
		}

	}

	private void toBlock(Block b) {
		toBlockModel(b, Risus.prefix("block/" + Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath()));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath(), model);
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

	private ItemModelBuilder singleTex(String texture) {
		return generated(texture, "item/generated", Risus.prefix("item/" + texture));
	}

	private ItemModelBuilder singleTex(ItemLike item) {
		return generated(BuiltInRegistries.ITEM.getKey(item.asItem()).getPath(), "item/generated", Risus.prefix("item/" + BuiltInRegistries.ITEM.getKey(item.asItem()).getPath()));
	}

	private ItemModelBuilder singleTexTool(DeferredHolder<Item, Item> item) {
		return generated(item.getId().getPath(), "item/handheld", Risus.prefix("item/" + item.getId().getPath()));
	}

	private ItemModelBuilder generatedRenderType(String name, @Nullable String renderType, ResourceLocation... layers) {
		return buildItem(name, "item/generated", renderType, layers);
	}

	private ItemModelBuilder buildItem(String name, String parent, @Nullable String renderType, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (renderType != null) {
			builder = builder.renderType(renderType);
		}
		return builder;
	}

	//from Aether
	public String itemName(Item item) {
		ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);
		if (location != null) {
			return location.getPath();
		} else {
			throw new IllegalStateException("Unknown item: " + item.toString());
		}
	}

	public void nameableWeapon(Item item, String location, String renamedVariant) {
		this.withExistingParent(renamedVariant, this.mcLoc("item/handheld")).texture("layer0", this.modLoc("item/" + location + renamedVariant));
		this.withExistingParent(this.itemName(item), this.mcLoc("item/handheld"))
				.texture("layer0", this.modLoc("item/" + location + this.itemName(item)))
				.override().predicate(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "named"), 1).model(this.getExistingFile(modLoc("item/" + renamedVariant))).end();
	}

	private void trimmedArmor(DeferredItem<ArmorItem> armor) {
		ItemModelBuilder base = this.singleTex(armor);
		for (ItemModelGenerators.TrimModelData trim : ItemModelGenerators.GENERATED_TRIM_MODELS) {
			String material = trim.name();
			String name = armor.getId().getPath() + "_" + material + "_trim";
			ModelFile trimModel = this.withExistingParent(name, this.mcLoc("item/generated"))
				.texture("layer0", Risus.prefix("item/" + armor.getId().getPath()))
				.texture("layer1", this.mcLoc("trims/items/" + armor.get().getType().getName() + "_trim_" + material));
			base.override().predicate(ResourceLocation.withDefaultNamespace("trim_type"), trim.itemModelIndex()).model(trimModel).end();
		}
	}

	@Override
	public String getName() {
		return "Risus Item Models";
	}
}

