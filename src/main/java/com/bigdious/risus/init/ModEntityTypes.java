package com.bigdious.risus.init;

import java.util.ArrayList;

import com.bigdious.risus.entity.AngelEntity;
import com.bigdious.risus.entity.HolderEntity;
import com.bigdious.risus.entity.MawEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = "risus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityTypes {

    private static final ArrayList<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

    public static final EntityType<AngelEntity> ANGEL = register(new ResourceLocation("risus", "angel"), EntityType.Builder.create(AngelEntity::new, EntityClassification.MONSTER).size(1.0F, 2.0F));
    public static final EntityType<HolderEntity> HOLDER = register(new ResourceLocation("risus", "holder"), EntityType.Builder.create(HolderEntity::new, EntityClassification.MONSTER).size(0.7F, 0.5F));
    public static final EntityType<MawEntity> MAW = register(new ResourceLocation("risus", "maw"), EntityType.Builder.create(MawEntity::new, EntityClassification.MONSTER).size(1.0F, 2.0F));
    
    
    public static void entityAttributes() {
        GlobalEntityTypeAttributes.put(ANGEL, AngelEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(HOLDER, HolderEntity.setCustomAttributes().create());
//        GlobalEntityTypeAttributes.put(MAW, MawEntity.setCustomAttributes().create());
    }

    private static <T extends Entity> EntityType<T> register(ResourceLocation id, EntityType.Builder<T> builder)
    {
        EntityType<T> type = builder.build(id.toString());
        type.setRegistryName(id);
        ENTITY_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerTypes(RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        ENTITY_TYPES.forEach(registry::register);
    }
    
}
