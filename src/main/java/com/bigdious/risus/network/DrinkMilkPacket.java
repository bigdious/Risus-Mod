package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.Stool;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusMobEffects;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

public class DrinkMilkPacket implements CustomPacketPayload {
	public static final Type<DrinkMilkPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "milk"));
	public static final DrinkMilkPacket INSTANCE = new DrinkMilkPacket();
	public static final StreamCodec<ByteBuf, DrinkMilkPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				Level level = player.level();
				if (player.getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT).equals("milk")) {
					player.addEffect(new MobEffectInstance(RisusMobEffects.MILKED, 1200, player.hasEffect(RisusMobEffects.MILKED) ? player.getEffect(RisusMobEffects.MILKED).getAmplifier()+1 : 0));
					player.removeEffectsCuredBy(EffectCures.MILK);
					player.level().playSound(null, player.getOnPos().above(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS);
				}
			});
		}
	}
}

