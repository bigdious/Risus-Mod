package com.bigdious.risus.entity;

import java.util.ArrayList;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.AngelEntity;
import com.bigdious.risus.entity.HolderEntity;
import com.bigdious.risus.entity.MawEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Risus.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RisusEntities {

	private static final ArrayList<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

	public static final EntityType<AngelEntity> ANGEL = register(Risus.risusRL("angel"), AngelEntity::new, EntityClassification.MONSTER, 1.0F, 2.0F);
	public static final EntityType<HolderEntity> HOLDER = register(Risus.risusRL("holder"), HolderEntity::new, EntityClassification.MONSTER, 0.7F, 0.5F);
	public static final EntityType<MawEntity> MAW = register(Risus.risusRL("maw"), MawEntity::new, EntityClassification.MONSTER, 1.0F, 2.0F);

	private static <E extends Entity> EntityType<E> register(ResourceLocation id, EntityType.IFactory<E> factory, EntityClassification classification, float width, float height) {
		return build(id, makeBuilder(factory, classification).size(width, height));
	}

	@SuppressWarnings("unchecked")
	private static <E extends Entity> EntityType<E> build(ResourceLocation id, EntityType.Builder<E> builder) {
		boolean cache = SharedConstants.useDatafixers;
		SharedConstants.useDatafixers = false;
		EntityType<E> ret = (EntityType<E>) builder.build(id.toString()).setRegistryName(id);
		SharedConstants.useDatafixers = cache;
		ENTITY_TYPES.add(ret);
		return ret;
	}

	private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.IFactory<E> factory, EntityClassification classification) {
		return EntityType.Builder.create(factory, classification).
				size(0.6F, 1.8F).
				setTrackingRange(80).
				setUpdateInterval(3).
				setShouldReceiveVelocityUpdates(true);
	}

	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent evt) {
		evt.put(ANGEL, AngelEntity.setCustomAttributes().create());
		evt.put(HOLDER, HolderEntity.setCustomAttributes().create());
		evt.put(MAW, MawEntity.setCustomAttributes().create());
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> evt) {
		evt.getRegistry().registerAll(ENTITY_TYPES.toArray(new EntityType<?>[0]));
	}
}
