package com.bigdious.risus.components.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.alchemy.PotionContents;

public record WarhornComponent (PotionContents potion) {
	public static final WarhornComponent EMPTY = new WarhornComponent(PotionContents.EMPTY);

	public static final Codec<WarhornComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		PotionContents.CODEC.optionalFieldOf("potion", PotionContents.EMPTY).forGetter(WarhornComponent::potion)
	).apply(instance, WarhornComponent::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, WarhornComponent> STREAM_CODEC = StreamCodec.composite(
		PotionContents.STREAM_CODEC, WarhornComponent::potion,
		WarhornComponent::new);

	public WarhornComponent updateContents(PotionContents potion) {
		return new WarhornComponent(potion);
	}
}
