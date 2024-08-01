package com.bigdious.risus.data;


import com.bigdious.risus.Risus;
import com.bigdious.risus.init.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import vazkii.patchouli.api.PatchouliAPI;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

//public class RisusAdvancementProvider extends AdvancementProvider {
//    public RisusAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
//        super(output, registries, existingFileHelper, List.of(new RisusAdvancementGenerator()));
//    }
//
//    private static class RisusAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
//
//		@Override
//        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
//			HolderLookup.RegistryLookup<Structure> structures = registries.lookupOrThrow(Registries.STRUCTURE);
//			AdvancementHolder first = Advancement.Builder.advancement().display(
//                            RisusItems.SMILE.get(),
//                            Component.translatable("advancement.risus.first"),
//                            Component.translatable("advancement.risus.first.desc"),
//                            new ResourceLocation(Risus.MODID ,"textures/block/ashen_remains.png"),
//                            AdvancementType.TASK,
//                            true, false, false)
//                    .requirements(AdvancementRequirements.Strategy.OR)
//				.addCriterion("site", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(registries.lookupOrThrow(Registries.STRUCTURE).getOrThrow(RisusStructures.ALTERATION_SITE))))
////                    .addCriterion("grassmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("grassy_maw"))))))
////                    .addCriterion("sandmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("sandy_maw"))))))
////                    .addCriterion("endmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("endy_maw"))))))
////                    .addCriterion("flower", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("flower_field"))))))
////                    .addCriterion("family", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("family_tree"))))))
//                    .save(consumer, "risus:first");

//			AdvancementHolder site_zero = Advancement.Builder.advancement().parent(first).display(
//                    RisusBlocks.ALTERATION_CATALYST.get(),
//                    Component.translatable("advancement.risus.site_zero"),
//                    Component.translatable("advancement.risus.site_zero.desc"), null, AdvancementType.TASK, true, true, false)
//                    .addCriterion("site", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("alteration_site"))))))
//                    .save(consumer, "risus:site_zero");
//
//			AdvancementHolder mod_book = Advancement.Builder.advancement().parent(site_zero).display(
//				Items.BOOK,
//				Component.translatable("advancement.risus.mod_book"),
//				Component.translatable("advancement.risus.mod_book.desc"), null, AdvancementType.TASK, true, true, false)
//				.addCriterion("modbook", InventoryChangeTrigger.TriggerInstance.hasItems(PatchouliAPI.get().getBookStack(new ResourceLocation(Risus.MODID,"research_notes")).getItem()))
//				.save(consumer, "risus:mod_book");
//
//
//			AdvancementHolder family = Advancement.Builder.advancement().parent(first).display(
//                            RisusBlocks.RIBCAGE.get(),
//                            Component.translatable("advancement.risus.family"),
//                            Component.translatable("advancement.risus.family.desc"), null, AdvancementType.TASK, true, true, true)
//					.addCriterion("family", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("family_tree"))))))
//                    .save(consumer, "risus:family");
//
//            AdvancementHolder little = Advancement.Builder.advancement().parent(first).display(
//                            RisusItems.ORGANIC_MATTER.get(),
//                            Component.translatable("advancement.risus.little"),
//                            Component.translatable("advancement.risus.little.desc"), null, AdvancementType.TASK, true, true, true)
//                    .addCriterion("pricked", EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer(DamagePredicate.Builder.damageInstance().sourceEntity(EntityPredicate.Builder.entity().of(RisusEntities.HOLDER.get()).build())))
//                    .save(consumer, "risus:little");
//
//
//			AdvancementHolder fleshing = Advancement.Builder.advancement().parent(site_zero).display(
//                            RisusBlocks.DECOMPOSED_TISSUE.get(),
//                            Component.translatable("advancement.risus.fleshing"),
//                            Component.translatable("advancement.risus.fleshing.desc"), null, AdvancementType.TASK, true,true,true)
//                    .requirements(AdvancementRequirements.Strategy.OR)
//                    .addCriterion("fleshing0", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECOMPOSING_TISSUE.get())),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
//                    .addCriterion("fleshing1", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.ROTTING_TISSUE.get())),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
//                    .addCriterion("fleshing2", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECAYING_TISSUE.get())),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
//                    .addCriterion("fleshing3", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.TISSUE.get())),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
//                    .save(consumer, "risus:fleshing");
//
//			AdvancementHolder step = Advancement.Builder.advancement().parent(first).display(
//                    Items.LEATHER_BOOTS,
//                    Component.translatable("advancement.risus.step"),
//                    Component.translatable("advancement.risus.step.desc"), null, AdvancementType.TASK, true,true,false)
//                    .requirements(AdvancementRequirements.Strategy.OR)
//				.addCriterion("grassmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("grassy_maw"))))))
//				.addCriterion("sandmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("sandy_maw"))))))
//				.addCriterion("endmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("endy_maw"))))))
//                    .save(consumer, "risus:step");
//
//
//			AdvancementHolder devour = Advancement.Builder.advancement().parent(step).display(
//                            RisusItems.GLUTTONY_SCALES.get(),
//                            Component.translatable("advancement.risus.devour"),
//                            Component.translatable("advancement.risus.devour.desc"), null, AdvancementType.TASK, true,true,true)
//                    .addCriterion("devour0", KilledTrigger.TriggerInstance.entityKilledPlayer(EntityPredicate.Builder.entity().of(RisusEntities.MAW.get())))
//                    .save(consumer, "risus:devour");
//
//			AdvancementHolder satiate = Advancement.Builder.advancement().parent(devour).display(
//                            RisusBlocks.MAW_GUTS.get(),
//                            Component.translatable("advancement.risus.satiate"),
//                            Component.translatable("advancement.risus.satiate.desc"), null, AdvancementType.TASK, true,true,true)
//                    .requirements(AdvancementRequirements.Strategy.AND)
//                    .addCriterion("satiate0", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.MAW_GUTS.get()))
//                    .save(consumer, "risus:satiate");
//
//			AdvancementHolder potential = Advancement.Builder.advancement().parent(satiate).display(
//                            RisusItems.UNAWAKENED_VESSEL.get(),
//                            Component.translatable("advancement.risus.potential"),
//                            Component.translatable("advancement.risus.potential.desc"), null, AdvancementType.TASK, true,true,true)
//                    .addCriterion("weakaxe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.UNAWAKENED_VESSEL.get()))
//                    .save(consumer, "risus:potential");
//
//
//			AdvancementHolder crusade = Advancement.Builder.advancement().parent(potential).display(
//                            RisusItems.BLOOD_FEATHER.get(),
//                            Component.translatable("advancement.risus.crusade"),
//                            Component.translatable("advancement.risus.crusade.desc"), null, AdvancementType.TASK, true,true,true)
//                    .addCriterion("murder", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.ANGEL.get())))
//                    .save(consumer, "risus:crusade");
//
//			AdvancementHolder unleashed = Advancement.Builder.advancement().parent(crusade).display(
//                            RisusItems.CRESCENT_DISASTER.get(),
//                            Component.translatable("advancement.risus.unleashed"),
//                            Component.translatable("advancement.risus.unleashed.desc"), null, AdvancementType.TASK, true,true,true)
//                    .addCriterion("strongaxe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CRESCENT_DISASTER))
//                    .save(consumer, "risus:unleashed");
//
//
//			AdvancementHolder irresistible = Advancement.Builder.advancement().parent(site_zero).display(
//                            RisusItems.GUILTY_APPLE.get(),
//                            Component.translatable("advancement.risus.irresistible"),
//                            Component.translatable("advancement.risus.irresistible.desc"), null, AdvancementType.TASK, true,true,true)
//                    .addCriterion("pleasure", ConsumeItemTrigger.TriggerInstance.usedItem(RisusItems.GUILTY_APPLE))
//                    .save(consumer, "risus:irresistible");

//			Needs help

//			AdvancementHolder cupid = Advancement.Builder.advancement().parent(irresistible).display(
//                            potionOfLove(),
//                            Component.translatable("advancement.risus.cupid"),
//                            Component.translatable("advancement.risus.cupid.desc"), null, AdvancementType.TASK, true,true,true)
//                    .requirements(AdvancementRequirements.Strategy.OR)
//                    .addCriterion("love", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of((ItemLike) RisusPotions.MATING_FRENZY.get()).build()))
//                    .addCriterion("love2", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of((ItemLike) RisusPotions.LONG_MATING_FRENZY.get()).build()))
//                    .save(consumer, "risus:cupid");

//
//        }
//		//tag dead, help
////        private ItemStack potionOfLove() {
////            ItemStack itemstack = new ItemStack(Items.POTION);
////            CompoundTag compoundtag = itemstack.getOrCreateTag();
////            compoundtag.putString("Potion", BuiltInRegistries.POTION.getKey(RisusPotions.MATING_FRENZY.get()).toString());
////            return itemstack;
////        }
//    }
//}
