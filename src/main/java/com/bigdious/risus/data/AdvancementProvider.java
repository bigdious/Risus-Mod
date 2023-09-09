package com.bigdious.risus.data;


import com.bigdious.risus.Risus;
import com.bigdious.risus.init.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
                            Component.translatable("itemGroup.risus"),
                            Component.translatable("advancement.risus.first.desc"),
                            new ResourceLocation(Risus.MODID ,"textures/block/ashen_remains.png"),
                            FrameType.TASK,
                            true, false, false)
                    .addCriterion("organic", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.ORGANIC_MATTER.get()))
                    .save(consumer, "risus:first");

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

            Advancement devour = Advancement.Builder.advancement().parent(first).display(
                            RisusBlocks.MAW_GUTS.get(),
                            Component.translatable("advancement.risus.devour"),
                            Component.translatable("advancement.risus.devour.desc"), null, FrameType.TASK, true,true,false)
                    .addCriterion("devour0", KilledTrigger.TriggerInstance.entityKilledPlayer(EntityPredicate.Builder.entity().of(RisusEntities.MAW.get())))
                    .save(consumer, "risus:devour");

            Advancement satiate = Advancement.Builder.advancement().parent(devour).display(
                            RisusItems.GLUTTONY_SCALES.get(),
                            Component.translatable("advancement.risus.satiate"),
                            Component.translatable("advancement.risus.satiate.desc"), null, FrameType.TASK, true,true,false)
                    .addCriterion("satiate0", InventoryChangeTrigger.TriggerInstance.hasItems(RisusItems.ORGANIC_MATTER.get()))
                    .save(consumer, "risus:satiate");

            Advancement crusade = Advancement.Builder.advancement().parent(first).display(
                            RisusItems.BLOOD_FEATHER.get(),
                            Component.translatable("advancement.risus.crusade"),
                            Component.translatable("advancement.risus.crusade.desc"), null, FrameType.TASK, true,true,false)
                    .addCriterion("murder", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.ANGEL.get())))
                    .save(consumer, "risus:crusade");

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
        }
    }
}