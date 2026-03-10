package com.bigdious.risus.villagers;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.items.utility.RisusBookItem;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.Immutable;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusVillagers {
	public static final DeferredRegister<PoiType> POI_TYPES=
		DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, Risus.MODID);
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS=
		DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, Risus.MODID);

	public static final Holder<PoiType> ASCETIC_POI = POI_TYPES.register("ascetic_poi", () -> new PoiType(ImmutableSet.copyOf(RisusBlocks.HEART_TRANSPLANT.get().getStateDefinition().getPossibleStates()), 1, 1));

	public static final Holder<VillagerProfession> ASCETIC = VILLAGER_PROFESSIONS.register("ascetic", () -> new VillagerProfession("ascetic", holder -> holder.value() == ASCETIC_POI.value(), poiTypeHolder -> poiTypeHolder.value() == ASCETIC_POI.value(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.WART_BLOCK_HIT));
}
