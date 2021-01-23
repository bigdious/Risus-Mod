package com.bigdious.risus.init;

import java.util.ArrayList;

import com.bigdious.risus.entity.tileentity.MawGutsTileEntity;
import com.bigdious.risus.entity.tileentity.ModSkullTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "risus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities {

    private static final ArrayList<TileEntityType<?>> TILE_ENTITY_TYPES = new ArrayList<>();
    
    public static final TileEntityType<MawGutsTileEntity> MAW_GUTS = register("maw_guts", TileEntityType.Builder.create(MawGutsTileEntity::new, ModBlocks.MAW_GUTS));
    
    public static final TileEntityType<ModSkullTileEntity> BLOODWYRM = register("bloodwyrm", TileEntityType.Builder.create(ModSkullTileEntity::new, ModBlocks.BLOODWYRM_HEAD, ModBlocks.BLOODWYRM_WALL_HEAD));
    
    private static <T extends TileEntity> TileEntityType<T> register(String id, TileEntityType.Builder<T> builder)
    {
        TileEntityType<T> type = builder.build(null);
        type.setRegistryName(id);
        TILE_ENTITY_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerTypes(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        TILE_ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
        TILE_ENTITY_TYPES.clear();
    }
}
