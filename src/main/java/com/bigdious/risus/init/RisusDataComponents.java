package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.components.item.ArmorUpgradingContent;
import com.bigdious.risus.components.item.WarhornComponent;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class RisusDataComponents {

	public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Risus.MODID);
	public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_EFFECT_COMPONENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Risus.MODID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockState>> BLOCK_STATE = COMPONENTS.register("block_state", () -> DataComponentType.<BlockState>builder().persistent(BlockState.CODEC).networkSynchronized(ByteBufCodecs.fromCodec(BlockState.CODEC)).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<WarhornComponent>> WARHORN_CONTENT = COMPONENTS.register("warhorn_content", () -> DataComponentType.<WarhornComponent>builder().persistent(WarhornComponent.CODEC).networkSynchronized(WarhornComponent.STREAM_CODEC).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<ArmorUpgradingContent>> ARMOR_UPGRADING_CONTENT = COMPONENTS.register("armor_upgrading_content", () -> DataComponentType.<ArmorUpgradingContent>builder().persistent(ArmorUpgradingContent.CODEC).networkSynchronized(ArmorUpgradingContent.STREAM_CODEC).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> ABILITY_VARIANT = COMPONENTS.register("ability_variant", () -> DataComponentType.<String>builder().persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentEntityEffect>>>> ITEM_DAMAGED_ENTITY = ENCHANTMENT_EFFECT_COMPONENTS.register("item_damaged_entity", () -> DataComponentType.<List<ConditionalEffect<EnchantmentEntityEffect>>>builder().persistent(ConditionalEffect.codec(EnchantmentEntityEffect.CODEC, LootContextParamSets.ENCHANTED_ENTITY).listOf()).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> POTION_CHARGES = COMPONENTS.register("potion_charges", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build());

}
