package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.util.LoverConversion;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class RisusDataMaps {

	public static final DataMapType<EntityType<?>, LoverConversion> LOVER_CONVERSION = DataMapType.builder(
		Risus.prefix("lover_conversions"), Registries.ENTITY_TYPE, LoverConversion.CODEC).synced(LoverConversion.CODEC, false).build();
}
