package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.entity.TesseractBlockEntity;
import com.bigdious.risus.blocks.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class RisusBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Risus.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AlterationCatalystBlockEntity>> ALTERATION_CATALYST = BLOCK_ENTITIES.register("alteration_catalyst", () -> BlockEntityType.Builder.of(AlterationCatalystBlockEntity::new, RisusBlocks.ALTERATION_CATALYST.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DisplayNotchBlockEntity>> DISPLAY_NOTCH = BLOCK_ENTITIES.register("display_notch", () -> BlockEntityType.Builder.of(DisplayNotchBlockEntity::new, Blocks.SHULKER_BOX,
			RisusBlocks.DISPLAY_NOTCH.get(),
			RisusBlocks.INVISIBLE_DISPLAY_NOTCH.get(),
			RisusBlocks.BLUE_DISPLAY_NOTCH.get(),
			RisusBlocks.BROWN_DISPLAY_NOTCH.get(),
			RisusBlocks.CYAN_DISPLAY_NOTCH.get(),
			RisusBlocks.GRAY_DISPLAY_NOTCH.get(),
			RisusBlocks.GREEN_DISPLAY_NOTCH.get(),
			RisusBlocks.LIGHT_BLUE_DISPLAY_NOTCH.get(),
			RisusBlocks.LIGHT_GRAY_DISPLAY_NOTCH.get(),
			RisusBlocks.LIME_DISPLAY_NOTCH.get(),
			RisusBlocks.MAGENTA_DISPLAY_NOTCH.get(),
			RisusBlocks.ORANGE_DISPLAY_NOTCH.get(),
			RisusBlocks.PINK_DISPLAY_NOTCH.get(),
			RisusBlocks.PURPLE_DISPLAY_NOTCH.get(),
			RisusBlocks.RED_DISPLAY_NOTCH.get(),
			RisusBlocks.WHITE_DISPLAY_NOTCH.get(),
			RisusBlocks.YELLOW_DISPLAY_NOTCH.get())
		.build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DepthVaseBlockEntity>> DEPTH_VASE = BLOCK_ENTITIES.register("depth_vase", () -> BlockEntityType.Builder.of(DepthVaseBlockEntity::new, RisusBlocks.DEPTH_VASE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BiomeBlockEntity>> BIOME_BLOCK = BLOCK_ENTITIES.register("laughing_stalk", () -> BlockEntityType.Builder.of(BiomeBlockEntity::new, RisusBlocks.LAUGHING_STALK.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MawGutsBlockEntity>> MAW_GUTS = BLOCK_ENTITIES.register("maw_guts", () -> BlockEntityType.Builder.of(MawGutsBlockEntity::new, RisusBlocks.MAW_GUTS.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PoppingBondknotBlockEntity>> POPPING_BONDKNOT = BLOCK_ENTITIES.register("popping_bondknot", () -> BlockEntityType.Builder.of(PoppingBondknotBlockEntity::new, RisusBlocks.POPPING_BONDKNOT_LOG.get(), RisusBlocks.POPPING_BONDKNOT_WOOD.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RisusCampfireBlockEntity>> RISUS_CAMPFIRE = BLOCK_ENTITIES.register("risus_campfire", () -> BlockEntityType.Builder.of(RisusCampfireBlockEntity::new, RisusBlocks.JOYFLAME_CAMPFIRE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RisusSkullBlockEntity>> RISUS_SKULL = BLOCK_ENTITIES.register("risus_skull", () -> BlockEntityType.Builder.of(RisusSkullBlockEntity::new, RisusBlocks.BLOODWYRM_HEAD.get(), RisusBlocks.BLOODWYRM_WALL_HEAD.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RisusSignBlockEntity>> RISUS_SIGN = BLOCK_ENTITIES.register("risus_sign", () -> BlockEntityType.Builder.of(RisusSignBlockEntity::new, RisusBlocks.BONDKNOT_SIGN.get(), RisusBlocks.BONDKNOT_WALL_SIGN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RisusHangingSignBlockEntity>> RISUS_HANGING_SIGN = BLOCK_ENTITIES.register("risus_hanging_sign", () -> BlockEntityType.Builder.of(RisusHangingSignBlockEntity::new, RisusBlocks.BONDKNOT_HANGING_SIGN.get(), RisusBlocks.BONDKNOT_WALL_HANGING_SIGN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WeaverNestBlockEntity>> WEAVER_NEST = BLOCK_ENTITIES.register("weaver_nest", () -> BlockEntityType.Builder.of(WeaverNestBlockEntity::new, RisusBlocks.WEAVER_NEST.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RitualBlockEntity>> RITUAL = BLOCK_ENTITIES.register("ritual", () -> BlockEntityType.Builder.of(RitualBlockEntity::new, RisusBlocks.RITUAL.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RisusSpawnerBlockEntity>> SPAWNER = BLOCK_ENTITIES.register("spawner", () -> BlockEntityType.Builder.of(RisusSpawnerBlockEntity::new, RisusBlocks.FLESHY_SPAWNER.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TesseractBlockEntity>> TESSERACT = BLOCK_ENTITIES.register("tesseract", () -> BlockEntityType.Builder.of(TesseractBlockEntity::new, RisusBlocks.TESSERACT.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WeavingMechanismBlockEntity>> WEAVING_MECHANISM = BLOCK_ENTITIES.register("weaving_mechanism", () -> BlockEntityType.Builder.of(WeavingMechanismBlockEntity::new, RisusBlocks.WEAVING_MECHANISM.get()).build(null));
}
