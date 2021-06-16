package com.bigdious.risus.tileentity;

import com.bigdious.risus.Risus;
import com.bigdious.risus.block.RisusBlocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = "risus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class RisusTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Risus.ID);
    
    public static final RegistryObject<TileEntityType<MawGutsTileEntity>> MAW_GUTS = TILE_ENTITIES.register("maw_guts", () -> TileEntityType.Builder.create(MawGutsTileEntity::new, RisusBlocks.MAW_GUTS.get()).build(null));
    public static final RegistryObject<TileEntityType<ModSkullTileEntity>> BLOODWYRM = TILE_ENTITIES.register("bloodwyrm", () -> TileEntityType.Builder.create(ModSkullTileEntity::new, RisusBlocks.BLOODWYRM_HEAD.get(), RisusBlocks.BLOODWYRM_WALL_HEAD.get()).build(null));


}
