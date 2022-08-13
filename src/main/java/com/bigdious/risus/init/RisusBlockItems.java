package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Risus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RisusBlockItems {

	@SubscribeEvent
	public static void registerBlockItems(RegisterEvent event) {
		if (Objects.equals(ForgeRegistries.ITEMS, event.getForgeRegistry())) {
			register(event, blockItem(RisusBlocks.ASHEN_REMAINS));
			register(event, blockItem(RisusBlocks.SMILING_REMAINS));
			register(event, blockItem(RisusBlocks.LAUGHING_OBSIDIAN));
			register(event, blockItem(RisusBlocks.ENGRAVED_BASALT));
			register(event, blockItem(RisusBlocks.MAW_GUTS));
			register(event, blockItem(RisusBlocks.CRYSTALLIZED_BONDS));
			register(event, blockItem(RisusBlocks.BLOODWEAVE));
			register(event, blockItem(RisusBlocks.BONDKNOT_LOG));
			register(event, blockItem(RisusBlocks.BONDKNOT_WOOD));
			register(event, blockItemNoTab(RisusBlocks.POPPING_BONDKNOT_LOG));
			register(event, blockItemNoTab(RisusBlocks.POPPING_BONDKNOT_WOOD));
			register(event, blockItem(RisusBlocks.STRIPPED_BONDKNOT_LOG));
			register(event, blockItem(RisusBlocks.STRIPPED_BONDKNOT_WOOD));
			register(event, blockItem(RisusBlocks.BONDKNOT_PLANKS));
			register(event, blockItem(RisusBlocks.BONDKNOT_STAIRS));
			register(event, blockItem(RisusBlocks.BONDKNOT_SLAB));
			register(event, blockItem(RisusBlocks.BONDKNOT_BUTTON));
			register(event, blockItem(RisusBlocks.BONDKNOT_FENCE));
			register(event, blockItem(RisusBlocks.BONDKNOT_FENCE_GATE));
			register(event, blockItem(RisusBlocks.BONDKNOT_PRESSURE_PLATE));
			register(event, blockItem(RisusBlocks.BONDKNOT_TRAPDOOR));
			register(event, blockItem(RisusBlocks.BONDKNOT_DOOR));
			register(event, new SignItem(RisusItems.defaultWithRarity().stacksTo(16), RisusBlocks.BONDKNOT_SIGN.get(), RisusBlocks.BONDKNOT_WALL_SIGN.get()));
			register(event, blockItem(RisusBlocks.BABY_RIBCAGE));
			register(event, blockItem(RisusBlocks.RIBCAGE));
			register(event, new StandingAndWallBlockItem(RisusBlocks.BLOODWYRM_HEAD.get(), RisusBlocks.BLOODWYRM_WALL_HEAD.get(), RisusItems.defaultWithRarity()), "bloodwyrm_head");
			register(event, blockItem(RisusBlocks.GRIMSTONE));
			register(event, blockItem(RisusBlocks.GRIMSTONE_BRICKS));
			register(event, blockItem(RisusBlocks.CRACKED_GRIMSTONE_BRICKS));
			register(event, blockItem(RisusBlocks.GRIMSTONE_SLAB));
			register(event, blockItem(RisusBlocks.GRIMSTONE_STAIRS));
			register(event, blockItem(RisusBlocks.GRIMSTONE_WALL));
			register(event, blockItem(RisusBlocks.CHISELED_GRIMSTONE));
			register(event, blockItem(RisusBlocks.POLISHED_GRIMSTONE));
		}
	}

	private static <B extends Block> BlockItem blockItem(RegistryObject<B> block) {
		return new BlockItem(block.get(), RisusItems.defaultWithRarity());
	}

	private static <B extends Block> BlockItem blockItemNoTab(RegistryObject<B> block) {
		return new BlockItem(block.get(), new Item.Properties().rarity(Risus.getRarity()));
	}

	private static void register(RegisterEvent event, BlockItem item) {
		register(event, item, ForgeRegistries.BLOCKS.getKey(item.getBlock()).getPath());
	}

	private static void register(RegisterEvent event, BlockItem item, String name) {
		event.register(ForgeRegistries.Keys.ITEMS, helper -> helper.register(Risus.prefix(name), item));
	}
}
