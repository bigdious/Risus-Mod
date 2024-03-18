package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.Map;

public class RisusTab {
	private static final ResourceLocation RISUS_TABS = Risus.prefix("textures/gui/tabs.png");
	private static final ResourceLocation RISUS_BACKGROUND = Risus.prefix("textures/gui/tab_risus.png");

	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Risus.MODID);
	private static void createSpawnEggsAlphabetical(CreativeModeTab.Output output) {
		Collection<? extends Item> eggs = RisusEntities.SPAWN_EGGS.getEntries().stream().map(RegistryObject::get).toList();
		eggs.forEach(output::accept);
	}

	public static final RegistryObject<CreativeModeTab> INSTANCE = CREATIVE_TABS.register("risusmain", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.risus.main"))
			.displayItems(
					(parameters, output) -> {
						output.accept(RisusBlocks.ALTERATION_CATALYST.get());
						output.accept(RisusBlocks.ANGEL_ALTAR.get());
						output.accept(RisusBlocks.DEPTH_VASE.get());
						output.accept(RisusItems.ORGANIC_MATTER.get());
						output.accept(RisusBlocks.SMILING_REMAINS.get());
						output.accept(RisusBlocks.ASHEN_REMAINS.get());
						output.accept(RisusBlocks.SPREADING_REMAINS.get());
						output.accept(RisusBlocks.JOYFLAME_CAMPFIRE.get());
						output.accept(RisusBlocks.JOYFLAME_LANTERN.get());
						output.accept(RisusBlocks.JOYFLAME_TORCH.get());
						output.accept(RisusBlocks.LAUGHING_OBSIDIAN.get());
						output.accept(RisusItems.BLOODWYRM_HEAD_WEAPON.get());
						output.accept(RisusBlocks.BLOODWYRM_HEAD.get());
						output.accept(RisusBlocks.BURNT_HYPHAE.get());
						output.accept(RisusBlocks.CURVED_RITUAL_BLOCK.get());
						output.accept(RisusBlocks.LINEAR_RITUAL_BLOCK.get());
						output.accept(RisusBlocks.ENGRAVED_BASALT.get());
						output.accept(RisusBlocks.BLOODWEAVE.get());
						output.accept(RisusItems.BLOOD_BUCKET.get());
						output.accept(RisusBlocks.COAGULATED_BLOOD_BLOCK.get());
						output.accept(RisusBlocks.SCAB.get());
	//					output.accept(RisusBlocks.WEAVER_NEST.get());
						output.accept(RisusBlocks.BONDKNOT_LOG.get());
						output.accept(RisusBlocks.POPPING_BONDKNOT_LOG.get());
						output.accept(RisusBlocks.BONDKNOT_WOOD.get());
						output.accept(RisusBlocks.POPPING_BONDKNOT_WOOD.get());
						output.accept(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
						output.accept(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
						output.accept(RisusBlocks.BONDKNOT_PLANKS.get());
						output.accept(RisusBlocks.BONDKNOT_STAIRS.get());
						output.accept(RisusBlocks.BONDKNOT_SLAB.get());
						output.accept(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
						output.accept(RisusBlocks.BONDKNOT_BUTTON.get());
						output.accept(RisusBlocks.BONDKNOT_DOOR.get());
						output.accept(RisusBlocks.BONDKNOT_TRAPDOOR.get());
						output.accept(RisusBlocks.BONDKNOT_SIGN.get());
						output.accept(RisusBlocks.BONDKNOT_HANGING_SIGN.get());
						output.accept(RisusBlocks.BONDKNOT_FENCE.get());
						output.accept(RisusBlocks.BONDKNOT_FENCE_GATE.get());
						output.accept(RisusItems.BONDKNOT_BOAT.get());
						output.accept(RisusBlocks.CRYSTALLIZED_BONDS.get());
						output.accept(RisusItems.CRYSTALLIZED_BOND.get());
						output.accept(RisusItems.RIBCAGE.get());
						output.accept(RisusItems.BABY_RIBCAGE.get());
						output.accept(RisusBlocks.HEART_TRANSPLANT.get());
						output.accept(RisusBlocks.ZIT.get());
						output.accept(RisusBlocks.GRIMSTONE.get());
						output.accept(RisusBlocks.GRIMSTONE_BRICKS.get());
						output.accept(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
						output.accept(RisusBlocks.POLISHED_GRIMSTONE.get());
						output.accept(RisusBlocks.CHISELED_GRIMSTONE.get());
						output.accept(RisusBlocks.GRIMSTONE_STAIRS.get());
						output.accept(RisusBlocks.GRIMSTONE_SLAB.get());
						output.accept(RisusBlocks.GRIMSTONE_WALL.get());
						output.accept(RisusBlocks.SKIN.get());
						output.accept(RisusBlocks.FLESHY_SKIN.get());
						output.accept(RisusBlocks.CURVED_FLESHY_SKIN.get());
						output.accept(RisusBlocks.HAIRY_SKIN.get());
						output.accept(RisusBlocks.HAIRY_FLESHY_SKIN.get());
						output.accept(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get());
						output.accept(RisusItems.HAIR_FOLLICLES.get());
						output.accept(RisusItems.EYE_ENDER.get());
						output.accept(RisusItems.EYE_BLEACHED.get());
						output.accept(RisusItems.EYE_BLOODSHOT.get());
						output.accept(RisusItems.EYE_GOLDEN.get());
						output.accept(RisusItems.EYE_EMERALD.get());
						output.accept(RisusBlocks.LIVING_TISSUE.get());
						output.accept(RisusBlocks.ROTTING_TISSUE.get());
						output.accept(RisusBlocks.DECOMPOSING_TISSUE.get());
						output.accept(RisusBlocks.DECAYING_TISSUE.get());
						output.accept(RisusBlocks.TISSUE.get());
						output.accept(RisusBlocks.ROTTED_TISSUE.get());
						output.accept(RisusBlocks.DECOMPOSED_TISSUE.get());
						output.accept(RisusBlocks.DECAYED_TISSUE.get());
						output.accept(RisusBlocks.BONE_WALL.get());
						output.accept(RisusBlocks.TEETH.get());
						output.accept(RisusItems.TOOTHKNOCKER.get());
						output.accept(RisusBlocks.MAW_GUTS.get());
						output.accept(RisusBlocks.MIRAGE_GRASS_BLOCK.get());
						output.accept(RisusBlocks.MIRAGE_SAND.get());
						output.accept(RisusBlocks.MIRAGE_NETHERRACK.get());
						output.accept(RisusBlocks.MIRAGE_END_STONE.get());
						output.accept(RisusBlocks.GLUTTONY_SCALEPLATE.get());
						output.accept(RisusBlocks.IMITATION_SCALEPLATE.get());
						output.accept(RisusItems.BLOOD_FEATHER.get());
						output.accept(RisusItems.GLUTTONY_SCALES.get());
						output.accept(RisusItems.CONCENTRATION_CORE.get());
						output.accept(RisusItems.UNAWAKENED_VESSEL.get());
						output.accept(RisusItems.CRESCENT_DISASTER.get());
						output.accept(RisusItems.LIGHT_DEVOURER.get());
						output.accept(RisusBlocks.BIG_CHAIN.get());
						output.accept(RisusItems.MEMORY1_ITEM.get());
						output.accept(RisusBlocks.REGEN_ROSE.get());
						output.accept(RisusItems.ENDLESS_PEARL.get());
						output.accept(RisusItems.NEURON_STEM.get());
						output.accept(RisusItems.GUILTY_APPLE.get());
						createSpawnEggsAlphabetical(output);







					}
			)
			.withTabsImage(RISUS_TABS)
			.withBackgroundLocation(RISUS_BACKGROUND)
			.icon(() -> new ItemStack(RisusItems.SMILE.get()))
			.build());
}
