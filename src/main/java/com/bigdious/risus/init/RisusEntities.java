package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.*;
import com.bigdious.risus.entity.projectile.BloodwyrmBreathEntity;
import com.bigdious.risus.entity.projectile.ThrownAxe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusEntities {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Risus.MODID);
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(Registries.ITEM, Risus.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<Angel>> ANGEL = register(new ResourceLocation(Risus.MODID, "angel"), EntityType.Builder.of(Angel::new, MobCategory.MONSTER).sized(0.75F, 2.0F), 0x000000, 0x8b0000);
	public static final DeferredHolder<EntityType<?>, EntityType<RisusBoat>> BOAT = registerNoEgg(new ResourceLocation(Risus.MODID, "risus_boat"), EntityType.Builder.<RisusBoat>of(RisusBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F));
	public static final DeferredHolder<EntityType<?>, EntityType<BloodwyrmBreathEntity>> BLOODWYRM_BREATH = registerNoEgg(new ResourceLocation(Risus.MODID, "bloodwyrm_breath"), EntityType.Builder.<BloodwyrmBreathEntity>of(BloodwyrmBreathEntity::new, MobCategory.MISC).sized(0.1F, 0.1F));
	public static final DeferredHolder<EntityType<?>, EntityType<Holder>> HOLDER = register(new ResourceLocation(Risus.MODID, "holder"), EntityType.Builder.of(Holder::new, MobCategory.MONSTER).sized(0.7F, 0.5F), 0x000000, 0x8b0000);
	public static final DeferredHolder<EntityType<?>, EntityType<Maw>> MAW = register(new ResourceLocation(Risus.MODID, "maw"), EntityType.Builder.of(Maw::new, MobCategory.MONSTER).sized(1.0F, 0.75F), 0x000000, 0x8b0000);
	public static final DeferredHolder<EntityType<?>, EntityType<QuestionMark>> QUESTION_MARK = register(new ResourceLocation(Risus.MODID, "question_mark"), EntityType.Builder.of(QuestionMark::new, MobCategory.MONSTER).sized(3.75F, 3.75F), 0x000000, 0x8b0000);
	public static final DeferredHolder<EntityType<?>, EntityType<ThrownAxe>> THROWN_AXE = registerNoEgg(new ResourceLocation(Risus.MODID, "thrown_axe"), EntityType.Builder.<ThrownAxe>of(ThrownAxe::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
	public static final DeferredHolder<EntityType<?>, EntityType<Weaver>> WEAVER = register(new ResourceLocation(Risus.MODID, "weaver"), EntityType.Builder.of(Weaver::new, MobCategory.MONSTER).sized(0.5F, 0.5F), 0x000000, 0x8b0000);

	public static final DeferredHolder<EntityType<?>, EntityType<Lover>> LOVER = register(new ResourceLocation(Risus.MODID, "lover"), EntityType.Builder.of(Lover::new, MobCategory.MONSTER).sized(1F, 1.0F), 0x000000, 0x8b0000);
	public static final DeferredHolder<EntityType<?>, EntityType<Stalker>> STALKER = register(new ResourceLocation(Risus.MODID, "stalker"), EntityType.Builder.of(Stalker::new, MobCategory.MONSTER).sized(0.6F, 1.8F), 0x000000, 0x8b0000);

	public static final DeferredHolder<EntityType<?>, EntityType<Memory1>> MEMORY1 = registerNoEgg(new ResourceLocation(Risus.MODID, "memory1"), EntityType.Builder.of(Memory1::new, MobCategory.MONSTER).sized(2F, 2F));

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerNoEgg(ResourceLocation id, EntityType.Builder<T> builder) {
		return ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
	}

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> register(ResourceLocation id, EntityType.Builder<T> builder, int primary, int secondary) {
		DeferredHolder<EntityType<?>, EntityType<T>> ret = ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
		SPAWN_EGGS.register(id.getPath() + "_spawn_egg", () -> new DeferredSpawnEggItem(ret, primary, secondary, RisusItems.defaultWithRarity()));
		return ret;
	}
}
