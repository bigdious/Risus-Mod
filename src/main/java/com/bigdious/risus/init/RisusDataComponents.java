package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.components.item.WarhornComponent;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusDataComponents {

	public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Risus.MODID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockState>> BLOCK_STATE = COMPONENTS.register("block_state", () -> DataComponentType.<BlockState>builder().persistent(BlockState.CODEC).networkSynchronized(ByteBufCodecs.fromCodec(BlockState.CODEC)).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<WarhornComponent>> WARHORN_CONTENT = COMPONENTS.register("warhorn_content", () -> DataComponentType.<WarhornComponent>builder().persistent(WarhornComponent.CODEC).networkSynchronized(WarhornComponent.STREAM_CODEC).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> ABILITY_VARIANT = COMPONENTS.register("ability_variant", () -> DataComponentType.<String>builder().persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8).build());

}
