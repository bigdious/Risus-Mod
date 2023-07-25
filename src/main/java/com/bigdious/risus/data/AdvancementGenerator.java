package com.bigdious.risus.data;


import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class AdvancementGenerator extends AdvancementProvider {
    public AdvancementGenerator(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, helper);
    }
    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper helper) {

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
                .addCriterion("fleshing0", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECOMPOSING_TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                .addCriterion("fleshing1", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.ROTTING_TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                .addCriterion("fleshing2", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.DECAYING_TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                .addCriterion("fleshing3", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(RisusBlocks.TISSUE.get()).build()),ItemPredicate.Builder.item().of(RisusItems.ORGANIC_MATTER.get())))
                .save(consumer, "risus:fleshing");

    }
    @Override
    public String getName() {
        return "Risus Advancements";
    }
}