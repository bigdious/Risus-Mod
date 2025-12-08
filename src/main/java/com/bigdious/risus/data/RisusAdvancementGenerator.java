package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.advancement.BreakWeaverNestTrigger;
import com.bigdious.risus.advancement.HolyGroundsTrigger;
import com.bigdious.risus.advancement.KilledByDevourTrigger;
import com.bigdious.risus.advancement.WitnessWeaverNestTrigger;
import com.bigdious.risus.advancement.predicate.ItemHornsPredicate;
import com.bigdious.risus.components.item.WarhornComponent;
import com.bigdious.risus.init.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;


public class RisusAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

	@SuppressWarnings("unused")
	@Override
	public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
		HolderLookup.RegistryLookup<Structure> structures = registries.lookupOrThrow(Registries.STRUCTURE);


		AdvancementHolder first = Advancement.Builder.advancement()
			.display(
				RisusItems.SMILE.get(),
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
			.addCriterion("grassmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GRASSY_MAW))))
			.addCriterion("sandmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.SANDY_MAW))))
			.addCriterion("endmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.ENDY_MAW))))
			.addCriterion("flower", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.FLOWER_FIELD))))
			.addCriterion("holy_grounds", HolyGroundsTrigger.TriggerInstance.getsmitten())
			.addCriterion("family", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.FAMILY_TREE))))
			.addCriterion("body", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GREAT_BODY))))
			.addCriterion("lab", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.LAB_START))))
			.addCriterion("dungeon", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.DUNGEON))))
			.addCriterion("well", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.BLOOD_WELL))))
			.addCriterion("draxolotl", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.DRAXOLOTL_REMAINS))))
			.addCriterion("church", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.CHURCH))))
			.addCriterion("ribs", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.RIBS_FOSSIL))))
			.addCriterion("skull", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.SKULL_FOSSIL))))
			.save(consumer, "risus:first");

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
			.addCriterion("holy_grounds", HolyGroundsTrigger.TriggerInstance.getsmitten())
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
				Component.translatable("advancement.risus.revenge.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("revenge", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.HAND_OF_GREED))
			.save(consumer, "risus:revenge");

		AdvancementHolder step = Advancement.Builder.advancement().parent(first)
			.display(
				Items.LEATHER_BOOTS,
				Component.translatable("advancement.risus.step"),
				Component.translatable("advancement.risus.step.desc"), null, AdvancementType.TASK, true, true, false)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("grassmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.GRASSY_MAW))))
			.addCriterion("sandmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.SANDY_MAW))))
			.addCriterion("endmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(RisusStructures.ENDY_MAW))))
			.save(consumer, "risus:step");


		AdvancementHolder devour = Advancement.Builder.advancement().parent(step)
			.display(
				RisusItems.ESSENCE_OF_GLUTTONY.get(),
				Component.translatable("advancement.risus.devour"),
				Component.translatable("advancement.risus.devour.desc"), null, AdvancementType.TASK, true, true, false)
			.addCriterion("devour0", KilledByDevourTrigger.TriggerInstance.getgood())
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
			.addCriterion("strongaxe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CRESCENT_DISASTER))
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
				Component.translatable("advancement.risus.homewrecker.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("homewrecker", BreakWeaverNestTrigger.TriggerInstance.breakNest())
			.save(consumer, "risus:homewrecker");

		AdvancementHolder parentmode = Advancement.Builder.advancement().parent(first)
			.display(
				RisusItems.ESSENCE_OF_MELANCHOLY.get(),
				Component.translatable("advancement.risus.parentmode"),
				Component.translatable("advancement.risus.parentmode.desc"), null, AdvancementType.GOAL, true, true, false)
			.addCriterion("parentmode", WitnessWeaverNestTrigger.TriggerInstance.witnessNest())
			.save(consumer, "risus:parentmode");
	}
}
