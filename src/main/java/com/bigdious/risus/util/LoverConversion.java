package com.bigdious.risus.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;

public record LoverConversion(EntityType<?> result) {

	public static final Codec<LoverConversion> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("convert_to").forGetter(LoverConversion::result)
	).apply(instance, LoverConversion::new));
}

