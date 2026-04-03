package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.advancement.*;
import com.bigdious.risus.advancement.predicate.BasicBTrigger;
import com.bigdious.risus.advancement.predicate.ItemHornsPredicate;
import com.bigdious.risus.components.item.WarhornComponent;
import com.bigdious.risus.init.*;
import com.bigdious.risus.worldgen.structures.RisusStructures;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Optional;
import java.util.function.Consumer;


public class RisusAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

	@SuppressWarnings("unused")
	@Override
	public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
		HolderLookup.RegistryLookup<Structure> structures = registries.lookupOrThrow(Registries.STRUCTURE);


		AdvancementHolder first = Advancement.Builder.advancement()
			.display(
				RisusBlocks.COALIFICATION.get(),
				Component.translatable("advancement.risus.first"),
				Component.translatable("advancement.risus.first.desc"),
				ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/block/ashen_remains.png"),
				AdvancementType.TASK,
				false, false, false)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("enter_site", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.ALTERATION_SITE))))
			.addCriterion("enter_site2", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BURRIED_SITE))))
			.addCriterion("enter_site3", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GRASSY_SITE))))
			.addCriterion("enter_site4", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BEDROCK_HAND))))
			.addCriterion("mirage", BasicBTrigger.TriggerInstance.getFooled())
			.addCriterion("flower", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.FLOWER_FIELD))))
			.addCriterion("holy_grounds", BasicBTrigger.TriggerInstance.getSmitten())
			.addCriterion("family", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.FAMILY_TREE))))
			.addCriterion("body", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GREAT_BODY))))
			.addCriterion("lab", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.LAB_START))))
			.addCriterion("heart_chamber", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.HEART_CHAMBER))))
			.addCriterion("well", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BLOOD_WELL))))
			.addCriterion("draxolotl", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.DRAXOLOTL_REMAINS))))
			.addCriterion("church", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.CHURCH))))
			.addCriterion("ribs", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.RIBS_FOSSIL))))
			.addCriterion("skull", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.SKULL_FOSSIL))))
			.addCriterion("modbook", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.RESEARCHERS_NOTES))
			.save(consumer, "risus:first");

		AdvancementHolder church = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.GRIMSTONE_PILLAR.get(),
				Component.translatable("advancement.risus.church"),
				Component.translatable("advancement.risus.church.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("church", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.CHURCH))))
			.save(consumer, "risus:church");

		AdvancementHolder hearty = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.BEATING_HEART.get(),
				Component.translatable("advancement.risus.hearty"),
				Component.translatable("advancement.risus.hearty.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("hearty", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.HEART_CHAMBER))))
			.save(consumer, "risus:hearty");

		AdvancementHolder site_zero = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.ALTERATION_CATALYST.get(),
				Component.translatable("advancement.risus.site_zero"),
				Component.translatable("advancement.risus.site_zero.desc"), null, AdvancementType.TASK, true, true, false)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("enter_site1", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.ALTERATION_SITE))))
			.addCriterion("enter_site2", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BURRIED_SITE))))
			.addCriterion("enter_site3", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GRASSY_SITE))))
			.save(consumer, "risus:site_zero");

		AdvancementHolder angel = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.ANGEL_ALTAR.get(),
				Component.translatable("advancement.risus.angel"),
				Component.translatable("advancement.risus.angel.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("holy_grounds", BasicBTrigger.TriggerInstance.getSmitten())
			.save(consumer, "risus:angel");

		AdvancementHolder mod_book = Advancement.Builder.advancement().parent(site_zero)
			.display(
				RisusItems.RESEARCHERS_NOTES,
				Component.translatable("advancement.risus.mod_book"),
				Component.translatable("advancement.risus.mod_book.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("modbook", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.RESEARCHERS_NOTES))
			.save(consumer, "risus:mod_book");


		AdvancementHolder family = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.RIBCAGE.get(),
				Component.translatable("advancement.risus.family"),
				Component.translatable("advancement.risus.family.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("family1", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.FAMILY_TREE))))
			.save(consumer, "risus:family");

		AdvancementHolder little = Advancement.Builder.advancement().parent(first)
			.display(
				RisusItems.ESSENCE_OF_GREED.get(),
				Component.translatable("advancement.risus.little"),
				Component.translatable("advancement.risus.little.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("pricked", EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer(DamagePredicate.Builder.damageInstance().sourceEntity(EntityPredicate.Builder.entity().of(RisusEntities.HOLDER.get()).build())))
			.save(consumer, "risus:little");

		AdvancementHolder revenge = Advancement.Builder.advancement().parent(little)
			.display(
				RisusItems.HAND_OF_GREED.get(),
				Component.translatable("advancement.risus.revenge"),
				Component.translatable("advancement.risus.revenge.desc"), null, AdvancementType.GOAL, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("revenge", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.HAND_OF_GREED))
			.addCriterion("previous", this.advancementTrigger(little))
			.save(consumer, "risus:revenge");

		AdvancementHolder step = Advancement.Builder.advancement().parent(first)
			.display(
				Items.LEATHER_BOOTS,
				Component.translatable("advancement.risus.step"),
				Component.translatable("advancement.risus.step.desc"), null, AdvancementType.TASK, true, true, false)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("mirage", BasicBTrigger.TriggerInstance.getFooled())
			.save(consumer, "risus:step");


		AdvancementHolder devour = Advancement.Builder.advancement().parent(step)
			.display(
				RisusItems.ESSENCE_OF_GLUTTONY.get(),
				Component.translatable("advancement.risus.devour"),
				Component.translatable("advancement.risus.devour.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("devour0", BasicBTrigger.TriggerInstance.getGood())
			.save(consumer, "risus:devour");

		AdvancementHolder gluttony = Advancement.Builder.advancement().parent(devour)
			.display(
				RisusItems.GLUTTONY_SCALES.get(),
				Component.translatable("advancement.risus.gluttony"),
				Component.translatable("advancement.risus.gluttony.desc"), null, AdvancementType.TASK, true, true, false)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("gluttony", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.GLUTTONY_SCALES.get()))
			.addCriterion("core", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CONCENTRATION_CORE.get()))
			.save(consumer, "risus:gluttony");

		AdvancementHolder satiate = Advancement.Builder.advancement().parent(devour)
			.display(
				RisusBlocks.MAW_GUTS.get(),
				Component.translatable("advancement.risus.satiate"),
				Component.translatable("advancement.risus.satiate.desc"), null, AdvancementType.TASK, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("satiate0", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.MAW.get())))
			.save(consumer, "risus:satiate");

		AdvancementHolder potential = Advancement.Builder.advancement().parent(gluttony)
			.display(
				RisusItems.UNAWAKENED_VESSEL.get(),
				Component.translatable("advancement.risus.potential"),
				Component.translatable("advancement.risus.potential.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("weakaxe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.UNAWAKENED_VESSEL.get()))
			.save(consumer, "risus:potential");


		AdvancementHolder crusade = Advancement.Builder.advancement().parent(angel)
			.display(
				RisusItems.ESSENCE_OF_SLOTH.get(),
				Component.translatable("advancement.risus.crusade"),
				Component.translatable("advancement.risus.crusade.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("murder", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.ANGEL.get())))
			.save(consumer, "risus:crusade");

		AdvancementHolder unleashed = Advancement.Builder.advancement().parent(potential)
			.display(
				RisusItems.CRESCENT_DISASTER.get(),
				Component.translatable("advancement.risus.unleashed"),
				Component.translatable("advancement.risus.unleashed.desc"), null, AdvancementType.GOAL, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("strongaxe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CRESCENT_DISASTER))
			.addCriterion("previous", this.advancementTrigger(potential))
			.save(consumer, "risus:unleashed");


		AdvancementHolder irresistible = Advancement.Builder.advancement().parent(site_zero)
			.display(
				RisusItems.GUILTY_APPLE.get(),
				Component.translatable("advancement.risus.irresistible"),
				Component.translatable("advancement.risus.irresistible.desc"), null, AdvancementType.TASK, true, true, true)
			.addCriterion("pleasure", ConsumeItemTrigger.TriggerInstance.usedItem(RisusItems.GUILTY_APPLE))
			.save(consumer, "risus:irresistible");

		AdvancementHolder great_body = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.HAIRY_FLESHY_SKIN.get(),
				Component.translatable("advancement.risus.great_body"),
				Component.translatable("advancement.risus.great_body.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("body", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GREAT_BODY))))
			.save(consumer, "risus:great_body");

		AdvancementHolder fleshing = Advancement.Builder.advancement().parent(great_body)
			.display(
				RisusBlocks.DECOMPOSED_TISSUE.get(),
				Component.translatable("advancement.risus.fleshing"),
				Component.translatable("advancement.risus.fleshing.desc"), null, AdvancementType.TASK, true, true, true)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("fleshing0", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECOMPOSING_TISSUE.get())), ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
			.addCriterion("fleshing1", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.ROTTING_TISSUE.get())), ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
			.addCriterion("fleshing2", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECAYING_TISSUE.get())), ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
			.addCriterion("fleshing3", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.TISSUE.get())), ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
			.save(consumer, "risus:fleshing");

		AdvancementHolder tight = Advancement.Builder.advancement().parent(site_zero)
			.display(
				RisusItems.SKIN_HELMET.get(),
				Component.translatable("advancement.risus.tight"),
				Component.translatable("advancement.risus.tight.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("skins", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.SKIN_HELMET.get(), RisusItems.SKIN_BOOTS.get(), RisusItems.SKIN_CHESTPLATE.get(), RisusItems.SKIN_LEGGINGS.get()))
			.save(consumer, "risus:tight");

		AdvancementHolder robes = Advancement.Builder.advancement().parent(tight)
			.display(
				RisusItems.SINNER_ROBES_HELMET.get(),
				Component.translatable("advancement.risus.robes"),
				Component.translatable("advancement.risus.robes.desc"), null, AdvancementType.GOAL, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("skin", this.advancementTrigger(tight))
			.addCriterion("robess", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.SINNER_ROBES_HELMET.get(), RisusItems.SINNER_ROBES_BOOTS.get(), RisusItems.SINNER_ROBES_CHESTPLATE.get(), RisusItems.SINNER_ROBES_LEGGINGS.get()))
			.save(consumer, "risus:robes");

		AdvancementHolder scythe = Advancement.Builder.advancement().parent(gluttony)
			.display(
				RisusItems.SCYTHE.get(),
				Component.translatable("advancement.risus.scythe"),
				Component.translatable("advancement.risus.scythe.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("scythe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.SCYTHE.get()))
			.save(consumer, "risus:scythe");

		AdvancementHolder warcrimes = Advancement.Builder.advancement().parent(gluttony)
			.display(
				RisusItems.THOUSAND_BLADE.get(),
				Component.translatable("advancement.risus.thousand"),
				Component.translatable("advancement.risus.thousand.desc"), null, AdvancementType.CHALLENGE, true, true, true)
			.addCriterion("warcrimes", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.THOUSAND_BLADE.get()))
			.save(consumer, "risus:warcrimes");

		AdvancementHolder rainbow = Advancement.Builder.advancement().parent(scythe)
			.display(
				RisusItems.SOUL_SCYTHE.get(),
				Component.translatable("advancement.risus.rainbow"),
				Component.translatable("advancement.risus.rainbow.desc"), null, AdvancementType.CHALLENGE, true, true, true)
			.addCriterion("scythes", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CINDERGLEE_SCYTHE.get(), RisusItems.SOUL_SCYTHE.get(), RisusItems.FIRE_SCYTHE.get()))
			.save(consumer, "risus:rainbow");

		AdvancementHolder licked = Advancement.Builder.advancement().parent(first)
			.display(
				RisusItems.EMBODIMENT_OF_INTIMACY.get(),
				Component.translatable("advancement.risus.licked"),
				Component.translatable("advancement.risus.licked.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("licked", EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer(DamagePredicate.Builder.damageInstance().sourceEntity(EntityPredicate.Builder.entity().of(RisusEntities.LICKER.get()).build())))
			.save(consumer, "risus:licked");

		AdvancementHolder boomstick = Advancement.Builder.advancement().parent(gluttony)
			.display(
				RisusItems.BOOMSTICK.get(),
				Component.translatable("advancement.risus.boomstick"),
				Component.translatable("advancement.risus.boomstick.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("boomstick", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.BOOMSTICK.get()))
			.save(consumer, "risus:boomstick");

		AdvancementHolder light_devourer = Advancement.Builder.advancement().parent(gluttony)
			.display(
				RisusItems.LIGHT_DEVOURER.get(),
				Component.translatable("advancement.risus.light_devourer"),
				Component.translatable("advancement.risus.light_devourer.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("light_devourer", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.LIGHT_DEVOURER.get()))
			.save(consumer, "risus:light_devourer");

		AdvancementHolder cream = Advancement.Builder.advancement().parent(first)
			.display(
				RisusItems.LOVER_CREAM.get(),
				Component.translatable("advancement.risus.cream"),
				Component.translatable("advancement.risus.cream.desc"), null, AdvancementType.TASK, true, true, true)
			.addCriterion("swallowed", ConsumeItemTrigger.TriggerInstance.usedItem(RisusItems.LOVER_CREAM.get()))
			.save(consumer, "risus:cream");


		AdvancementHolder cupid = Advancement.Builder.advancement().parent(irresistible).display(
				PotionContents.createItemStack(Items.POTION, RisusPotions.MATING_FRENZY),
				Component.translatable("advancement.risus.cupid"),
				Component.translatable("advancement.risus.cupid.desc"), null, AdvancementType.TASK, true, true, true)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("love", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION).withSubPredicate(ItemSubPredicates.POTIONS, new ItemPotionsPredicate(HolderSet.direct(RisusPotions.LONG_MATING_FRENZY, RisusPotions.MATING_FRENZY)))))
			.save(consumer, "risus:cupid");

		AdvancementHolder warlove = Advancement.Builder.advancement().parent(cupid).display(
				WarhornComponent.createHornItemStack(RisusItems.WARHORN.get(), RisusPotions.MATING_FRENZY),
				Component.translatable("advancement.risus.warlove"),
				Component.translatable("advancement.risus.warlove.desc"), null, AdvancementType.GOAL, true, true, true)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("warlove", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(RisusItems.WARHORN.get()).withSubPredicate(RisusItemSubPredicates.HORNS.get(), new ItemHornsPredicate(HolderSet.direct(RisusPotions.LONG_MATING_FRENZY, RisusPotions.MATING_FRENZY)))))
			.save(consumer, "risus:warlove");

		AdvancementHolder hornlove = Advancement.Builder.advancement().parent(warlove).display(
				WarhornComponent.createHornItemStack(RisusItems.HEXHORN.get(), RisusPotions.MATING_FRENZY),
				Component.translatable("advancement.risus.hornlove"),
				Component.translatable("advancement.risus.hornlove.desc"), null, AdvancementType.CHALLENGE, true, true, true)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("hornlove", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(RisusItems.HEXHORN.get()).withSubPredicate(RisusItemSubPredicates.HORNS.get(), new ItemHornsPredicate(HolderSet.direct(RisusPotions.LONG_MATING_FRENZY, RisusPotions.MATING_FRENZY)))))
			.save(consumer, "risus:hornlove");

		AdvancementHolder shave = Advancement.Builder.advancement().parent(fleshing).display(
				RisusBlocks.HAIRY_SKIN.get(),
				Component.translatable("advancement.risus.shave"),
				Component.translatable("advancement.risus.shave.desc"), null, AdvancementType.TASK, true, true, true)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("shaving0", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.SKIN.get())), ItemPredicate.Builder.item().of(Items.SHEARS)))
			.addCriterion("shaving1", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.FLESHY_SKIN.get())), ItemPredicate.Builder.item().of(Items.SHEARS)))
			.addCriterion("shaving2", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.CURVED_FLESHY_SKIN.get())), ItemPredicate.Builder.item().of(Items.SHEARS)))
			.save(consumer, "risus:shave");


		AdvancementHolder lab = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.INACTIVE_HOLDER.get(),
				Component.translatable("advancement.risus.lab"),
				Component.translatable("advancement.risus.lab.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("enter_lab", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.LAB_START))))
			.save(consumer, "risus:lab");

		AdvancementHolder knuckles = Advancement.Builder.advancement().parent(gluttony)
			.display(
				RisusItems.TOOTHKNOCKER.get(),
				Component.translatable("advancement.risus.knuckles"),
				Component.translatable("advancement.risus.knuckles.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("knuckles", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.TOOTHKNOCKER.get()))
			.save(consumer, "risus:knuckles");

		AdvancementHolder stripper = Advancement.Builder.advancement().parent(knuckles)
			.display(
				RisusItems.GOLD_FIST.get(),
				Component.translatable("advancement.risus.stripper"),
				Component.translatable("advancement.risus.stripper.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("stripper", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.GOLD_FIST.get()))
			.save(consumer, "risus:stripper");

		AdvancementHolder homewrecker = Advancement.Builder.advancement().parent(first)
			.display(
				RisusBlocks.WEAVER_NEST.get(),
				Component.translatable("advancement.risus.homewrecker"),
				Component.translatable("advancement.risus.homewrecker.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("homewrecker", BasicBTrigger.TriggerInstance.breakNest())
			.save(consumer, "risus:homewrecker");

		AdvancementHolder parentmode = Advancement.Builder.advancement().parent(homewrecker)
			.display(
				RisusItems.ESSENCE_OF_MELANCHOLY.get(),
				Component.translatable("advancement.risus.parentmode"),
				Component.translatable("advancement.risus.parentmode.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("parentmode", BasicBTrigger.TriggerInstance.witnessNest())
			.save(consumer, "risus:parentmode");

		AdvancementHolder strung = Advancement.Builder.advancement().parent(homewrecker)
			.display(
				RisusBlocks.WEAVING_MECHANISM.get(),
				Component.translatable("advancement.risus.strung"),
				Component.translatable("advancement.risus.strung.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("strung", BasicBTrigger.TriggerInstance.createWeavingMechanism())
			.save(consumer, "risus:strung");

		AdvancementHolder lost_will_1 = Advancement.Builder.advancement().parent(first)
			.addCriterion("lost_will_1", LostWillTrigger.TriggerInstance.readWill(1))
			.save(consumer, "risus:lost_will_1");

		AdvancementHolder lost_will_2 = Advancement.Builder.advancement().parent(lost_will_1)
			.addCriterion("lost_will_2", LostWillTrigger.TriggerInstance.readWill(2))
			.save(consumer, "risus:lost_will_2");

		AdvancementHolder lost_will_3 = Advancement.Builder.advancement().parent(lost_will_2)
			.addCriterion("lost_will_3", LostWillTrigger.TriggerInstance.readWill(3))
			.save(consumer, "risus:lost_will_3");

		AdvancementHolder lost_will_4 = Advancement.Builder.advancement().parent(lost_will_3)
			.addCriterion("lost_will_4", LostWillTrigger.TriggerInstance.readWill(4))
			.save(consumer, "risus:lost_will_4");

		AdvancementHolder lost_will_5 = Advancement.Builder.advancement().parent(lost_will_4)
			.addCriterion("lost_will_5", LostWillTrigger.TriggerInstance.readWill(5))
			.save(consumer, "risus:lost_will_5");


		//challenges

		AdvancementHolder challenges = Advancement.Builder.advancement()
			.display(
				RisusItems.KILLJOY.get(),
				Component.translatable("advancement.risus.challenges"),
				Component.translatable("advancement.risus.challenges.desc"),
				ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/block/flat_scales_block_side.png"),
				AdvancementType.TASK,
				false, false, false)
			.addCriterion("first", this.advancementTrigger(first))
			.save(consumer, "risus:challenges");

		AdvancementHolder arsenal = Advancement.Builder.advancement().parent(challenges)
			.display(
				RisusItems.THOUSAND_BLADE.get(),
				Component.translatable("advancement.risus.arsenal"),
				Component.translatable("advancement.risus.arsenal.desc"), null, AdvancementType.CHALLENGE, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("scythes", this.advancementTrigger(rainbow))
			.addCriterion("axes", this.advancementTrigger(unleashed))
			.addCriterion("boomstick", this.advancementTrigger(boomstick))
			.addCriterion("boat", this.advancementTrigger(warcrimes))
			.addCriterion("stripper", this.advancementTrigger(stripper))
			.addCriterion("hexhorn", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.HEXHORN))
			.addCriterion("killjoy", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.KILLJOY))
			.addCriterion("flamethrower", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.BLOODWYRM_HEAD_WEAPON))
			.addCriterion("knife", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CARVING_KNIFE))
			.save(consumer, "risus:arsenal");

		AdvancementHolder armory = Advancement.Builder.advancement().parent(arsenal)
			.display(
				RisusItems.CROWN_OF_BONES.get(),
				Component.translatable("advancement.risus.armory"),
				Component.translatable("advancement.risus.armory.desc"), null, AdvancementType.CHALLENGE, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("robes", this.advancementTrigger(robes))
			.addCriterion("hand", this.advancementTrigger(revenge))
			.addCriterion("ivory", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CROWN_OF_BONES))
			.addCriterion("rosy", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.ROSE_CROWN))
			.addCriterion("counter", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.COUNTERWEIGHT))
			.addCriterion("diamond_wings", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.DIAMOND_TIPPED_ANGEL_WINGS))
			.addCriterion("threads", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.THREADERS_OF_THE_FIRMAMENT))
			.addCriterion("lucky", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.LUCKY_CHARM))
			.addCriterion("wretched", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.WRETCHED_CHARM))
			.addCriterion("totem", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.TOTEM_OF_UNYIELDING))
			.addCriterion("burn", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.BORN_TO_BURN))
			.save(consumer, "risus:armory");

		AdvancementHolder destroy = Advancement.Builder.advancement().parent(armory)
			.display(
				RisusItems.EMBODIMENT_OF_LANGUISH.get(),
				Component.translatable("advancement.risus.destroy"),
				Component.translatable("advancement.risus.destroy.desc"), null, AdvancementType.CHALLENGE, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("angel", this.advancementTrigger(crusade))
			.addCriterion("gorger", this.advancementTrigger(satiate))
			.addCriterion("weaver", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.WEAVER.get())))
			.addCriterion("holder", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.HOLDER.get())))
			.addCriterion("lover", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.LOVER.get())))
			.addCriterion("singer", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.SINGER.get())))
			.addCriterion("stalker", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.STALKER.get())))
			.addCriterion("licker", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.LICKER.get())))
			.addCriterion("hex", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.HEX.get())))
			.save(consumer, "risus:destroy");

		AdvancementHolder analysis = Advancement.Builder.advancement().parent(destroy)
			.display(
				RisusBlocks.TESSERACT.get(),
				Component.translatable("advancement.risus.analysis"),
				Component.translatable("advancement.risus.analysis.desc"), null, AdvancementType.CHALLENGE, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("church", this.advancementTrigger(church))
			.addCriterion("body", this.advancementTrigger(great_body))
			.addCriterion("tree", this.advancementTrigger(family))
			.addCriterion("site1", this.advancementTrigger(site_zero))
			.addCriterion("chamber", this.advancementTrigger(hearty))
			.addCriterion("lab", this.advancementTrigger(lab))
			.addCriterion("burried", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BURRIED_SITE))))
			.addCriterion("bedrock", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BEDROCK_HAND))))
			.addCriterion("grassy", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GRASSY_SITE))))
			.addCriterion("blood_well", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BLOOD_WELL))))
			.addCriterion("draxolotl", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.DRAXOLOTL_REMAINS))))
			.addCriterion("field", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.FLOWER_FIELD))))
			.addCriterion("ribs", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.RIBS_FOSSIL))))
			.addCriterion("skull", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.SKULL_FOSSIL))))
			.save(consumer, "risus:analysis");

		AdvancementHolder antique = Advancement.Builder.advancement().parent(analysis)
			.display(
				RisusItems.MUSIC_DISC_CYCLE.get(),
				Component.translatable("advancement.risus.antique"),
				Component.translatable("advancement.risus.antique.desc"), null, AdvancementType.CHALLENGE, true, true, false)
			.requirements(AdvancementRequirements.Strategy.AND)
			.addCriterion("cycle", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.MUSIC_DISC_CYCLE))
			.addCriterion("regn", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.MUSIC_DISC_REGN))
			.addCriterion("mork", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.MUSIC_DISC_MORK))
			.addCriterion("rak", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.MUSIC_DISC_RAK))
			.addCriterion("smile", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.SMILE_PATTERN))
			.addCriterion("rose", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.ROSE_PATTERN))
			.addCriterion("divinity", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.DIVINITY_PATTERN))
			.addCriterion("tree", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.TREE_PATTERN))
			.save(consumer, "risus:antique");

	}



	private Criterion<PlayerTrigger.TriggerInstance> advancementTrigger(AdvancementHolder advancement) {
		return this.advancementTrigger(advancement.id().getPath());
	}

	private Criterion<PlayerTrigger.TriggerInstance> advancementTrigger(String name) {
		return CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.of(ContextAwarePredicate.create(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(PlayerPredicate.Builder.player().checkAdvancementDone(Risus.prefix(name), true).build())).build()))));
	}
}
