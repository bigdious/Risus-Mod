package com.bigdious.risus.data;


import com.bigdious.risus.Risus;
import com.bigdious.risus.init.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
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
                .addCriterion("satiate0", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(RisusEntities.MAW.get())))
                .save(consumer, "risus:satiate");
//TODO irresistible advancement
        Advancement irresistible = Advancement.Builder.advancement().parent(first).display(
                        RisusItems.GUILTY_APPLE.get(),
                        Component.translatable("advancement.risus.irresistible"),
                        Component.translatable("advancement.risus.irresistible.desc"), null, FrameType.TASK, true,true,false)
                .save(consumer, "risus:irresistible");

        //TODO cupid advancement
        Advancement cupid = Advancement.Builder.advancement().parent(first).display(
                        RisusItems.HEART_TRANSPLANT.get(),
                        Component.translatable("advancement.risus.cupid"),
                        Component.translatable("advancement.risus.cupid.desc"), null, FrameType.TASK, true,true,false)
                .addCriterion("makelove", ConsumeItemTrigger.TriggerInstance.usedItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), RisusPotions.MATING_FRENZY.get()).getItem()))
                .save(consumer, "risus:cupid");

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
    @Override
    public String getName() {
        return "Risus Advancements";
    }

}