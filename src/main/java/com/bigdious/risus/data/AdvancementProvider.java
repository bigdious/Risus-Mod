package com.bigdious.risus.data;


import com.bigdious.risus.Risus;
import com.bigdious.risus.init.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends ForgeAdvancementProvider {
    public AdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new AdvancementGenerator()));
    }

    private static class AdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
            Advancement first = Advancement.Builder.advancement().display(
                            RisusItems.SMILE.get(),
                            Component.translatable("advancement.risus.first"),
                            Component.translatable("advancement.risus.first.desc"),
                            new ResourceLocation(Risus.MODID ,"textures/block/ashen_remains.png"),
                            FrameType.TASK,
                            true, false, false)
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("site", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("alteration_site")))))
                    .addCriterion("grassmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("grassy_maw")))))
                    .addCriterion("sandmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("sandy_maw")))))
                    .addCriterion("endmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("endy_maw")))))
                    .addCriterion("flower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("flower_field")))))
                    .addCriterion("family", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("family_tree")))))
                    .save(consumer, "risus:first");

            Advancement site_zero = Advancement.Builder.advancement().parent(first).display(
                    RisusBlocks.ALTERATION_CATALYST.get(),
                    Component.translatable("advancement.risus.site_zero"),
                    Component.translatable("advancement.risus.site_zero.desc"), null, FrameType.TASK, true, true, false)
                    .addCriterion("site", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("alteration_site")))))
                    .save(consumer, "risus:site_zero");

            Advancement little = Advancement.Builder.advancement().parent(first).display(
                            RisusItems.ORGANIC_MATTER.get(),
                            Component.translatable("advancement.risus.little"),
                            Component.translatable("advancement.risus.little.desc"), null, FrameType.TASK, true, true, false)
                    .addCriterion("pricked", EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer(DamagePredicate.Builder.damageInstance().sourceEntity(EntityPredicate.Builder.entity().of(RisusEntities.HOLDER.get()).build())))
                    .save(consumer, "risus:little");


            Advancement fleshing = Advancement.Builder.advancement().parent(first).display(
                            RisusBlocks.DECOMPOSED_TISSUE.get(),
                            Component.translatable("advancement.risus.fleshing"),
                            Component.translatable("advancement.risus.fleshing.desc"), null, FrameType.TASK, true,true,false)
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("fleshing0", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECOMPOSING_TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                    .addCriterion("fleshing1", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.ROTTING_TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                    .addCriterion("fleshing2", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECAYING_TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                    .addCriterion("fleshing3", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                    .save(consumer, "risus:fleshing");

            Advancement step = Advancement.Builder.advancement().parent(first).display(
                    Items.LEATHER_BOOTS,
                    Component.translatable("advancement.risus.step"),
                    Component.translatable("advancement.risus.step.desc"), null, FrameType.TASK, true,true,false)
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("grassmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("grassy_maw")))))
                    .addCriterion("sandmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("sandy_maw")))))
                    .addCriterion("endmaw", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ResourceKey.create(Registries.STRUCTURE, Risus.prefix("endy_maw")))))
                    .save(consumer, "risus:step");


            Advancement devour = Advancement.Builder.advancement().parent(step).display(
                            RisusItems.GLUTTONY_SCALES.get(),
                            Component.translatable("advancement.risus.devour"),
                            Component.translatable("advancement.risus.devour.desc"), null, FrameType.TASK, true,true,false)
                    .addCriterion("devour0", KilledTrigger.TriggerInstance.entityKilledPlayer(EntityPredicate.Builder.entity().of(RisusEntities.MAW.get())))
                    .save(consumer, "risus:devour");

            Advancement satiate = Advancement.Builder.advancement().parent(devour).display(
                            RisusBlocks.MAW_GUTS.get(),
                            Component.translatable("advancement.risus.satiate"),
                            Component.translatable("advancement.risus.satiate.desc"), null, FrameType.TASK, true,true,false)
                    .requirements(RequirementsStrategy.AND)
                    .addCriterion("satiate0", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.MAW_GUTS.get()))
                    .save(consumer, "risus:satiate");

            Advancement potential = Advancement.Builder.advancement().parent(first).display(
                            RisusItems.UNAWAKENED_VESSEL.get(),
                            Component.translatable("advancement.risus.potential"),
                            Component.translatable("advancement.risus.potential.desc"), null, FrameType.TASK, true,true,false)
                    .addCriterion("weakaxe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.UNAWAKENED_VESSEL.get()))
                    .save(consumer, "risus:potential");

            Advancement unleashed = Advancement.Builder.advancement().parent(potential).display(
                            RisusItems.CRESCENT_DISASTER.get(),
                            Component.translatable("advancement.risus.unleashed"),
                            Component.translatable("advancement.risus.unleashed.desc"), null, FrameType.TASK, true,true,false)
                    .addCriterion("strongaxe", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.CRESCENT_DISASTER.get()))
                    .save(consumer, "risus:unleashed");

            Advancement crusade = Advancement.Builder.advancement().parent(unleashed).display(
                            RisusItems.BLOOD_FEATHER.get(),
                            Component.translatable("advancement.risus.crusade"),
                            Component.translatable("advancement.risus.crusade.desc"), null, FrameType.TASK, true,true,false)
                    .addCriterion("murder", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.ANGEL.get())))
                    .save(consumer, "risus:crusade");
        }
    }
}