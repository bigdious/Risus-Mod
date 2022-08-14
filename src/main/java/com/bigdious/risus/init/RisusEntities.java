package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.entity.Holder;
import com.bigdious.risus.entity.Maw;
import com.bigdious.risus.entity.Weaver;
import com.bigdious.risus.util.RisusTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusEntities {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Risus.MODID);
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, Risus.MODID);

	public static final RegistryObject<EntityType<Angel>> ANGEL = register(new ResourceLocation(Risus.MODID, "angel"), EntityType.Builder.of(Angel::new, MobCategory.MONSTER).sized(1.0F, 1.0F), 0x000000, 0x8b0000);
	public static final RegistryObject<EntityType<Holder>> HOLDER = register(new ResourceLocation(Risus.MODID, "holder"), EntityType.Builder.of(Holder::new, MobCategory.MONSTER).sized(0.7F, 0.5F), 0x000000, 0x8b0000);
	public static final RegistryObject<EntityType<Maw>> MAW = register(new ResourceLocation(Risus.MODID, "maw"), EntityType.Builder.of(Maw::new, MobCategory.MONSTER).sized(1.0F, 1.0F), 0x000000, 0x8b0000);
	public static final RegistryObject<EntityType<Weaver>> WEAVER = register(new ResourceLocation(Risus.MODID, "weaver"), EntityType.Builder.of(Weaver::new, MobCategory.MONSTER).sized(0.5F, 0.5F), 0x000000, 0x8b0000);

	private static <T extends Entity> RegistryObject<EntityType<T>> registerNoEgg(ResourceLocation id, EntityType.Builder<T> builder) {
		return ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
	}

	@SuppressWarnings("unchecked")
	private static <T extends Entity> RegistryObject<EntityType<T>> register(ResourceLocation id, EntityType.Builder<T> builder, int primary, int secondary) {
		RegistryObject<EntityType<T>> ret = ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
		SPAWN_EGGS.register(id.getPath() + "_spawn_egg", () -> new ForgeSpawnEggItem(() -> (EntityType<? extends Mob>) ret.get(), primary, secondary, RisusItems.defaultWithRarity()));
		return ret;
	}
}
