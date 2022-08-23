package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Risus.MODID);

	public static final RegistryObject<BlockEntityType<AlterationCatalystBlockEntity>> ALTERATION_CATALYST = BLOCK_ENTITIES.register("alteration_catalyst", () -> BlockEntityType.Builder.of(AlterationCatalystBlockEntity::new, RisusBlocks.ALTERATION_CATALYST.get()).build(null));
	public static final RegistryObject<BlockEntityType<MawGutsBlockEntity>> MAW_GUTS = BLOCK_ENTITIES.register("maw_guts", () -> BlockEntityType.Builder.of(MawGutsBlockEntity::new, RisusBlocks.MAW_GUTS.get()).build(null));
	public static final RegistryObject<BlockEntityType<PoppingBondknotBlockEntity>> POPPING_BONDKNOT = BLOCK_ENTITIES.register("popping_bondknot", () -> BlockEntityType.Builder.of(PoppingBondknotBlockEntity::new, RisusBlocks.POPPING_BONDKNOT_LOG.get(), RisusBlocks.POPPING_BONDKNOT_WOOD.get()).build(null));
	public static final RegistryObject<BlockEntityType<RisusSkullBlockEntity>> RISUS_SKULL = BLOCK_ENTITIES.register("risus_skull", () -> BlockEntityType.Builder.of(RisusSkullBlockEntity::new, RisusBlocks.BLOODWYRM_HEAD.get(), RisusBlocks.BLOODWYRM_WALL_HEAD.get()).build(null));
	public static final RegistryObject<BlockEntityType<RisusSignBlockEntity>> RISUS_SIGN = BLOCK_ENTITIES.register("risus_sign", () -> BlockEntityType.Builder.of(RisusSignBlockEntity::new, RisusBlocks.BONDKNOT_SIGN.get(), RisusBlocks.BONDKNOT_WALL_SIGN.get()).build(null));
}
