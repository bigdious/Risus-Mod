package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entities.AngelEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Risus.MOD_ID);

    // Entity Types
    public static final RegistryObject<EntityType<AngelEntity>> ANGEL = ENTITY_TYPES.register("angel",
            () -> EntityType.Builder.create(AngelEntity::new, EntityClassification.MONSTER)
                    .size(1.0F,2.0F)
                    .build(new ResourceLocation(Risus.MOD_ID, "angel").toString())
    );

}
