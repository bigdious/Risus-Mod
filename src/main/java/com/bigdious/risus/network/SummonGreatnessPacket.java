package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.Stool;
import com.bigdious.risus.init.*;
import com.bigdious.risus.util.ServerParticleUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SummonGreatnessPacket implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<SummonGreatnessPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "summon_greatness"));
	public static final SummonGreatnessPacket INSTANCE = new SummonGreatnessPacket();
	public static final StreamCodec<ByteBuf, SummonGreatnessPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				Level level = player.level();
				boolean flag1 =  player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT).equals("great_stool");
				boolean flag2 =  player.getItemBySlot(EquipmentSlot.LEGS).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.LEGS).get(RisusDataComponents.ABILITY_VARIANT).equals("great_stool");
				boolean flag3 =  player.getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT).equals("great_stool");
				boolean flag4 =  player.getItemBySlot(EquipmentSlot.HEAD).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.HEAD).get(RisusDataComponents.ABILITY_VARIANT).equals("great_stool");
				int numberOfStools = 0;
				if (flag1) numberOfStools++;
				if (flag2) numberOfStools++;
				if (flag3) numberOfStools++;
				if (flag4) numberOfStools++;
				if (player.onGround() && !player.hasEffect(RisusMobEffects.GREATNESS) && !level.getBlockState(player.getOnPos().above()).isSolidRender(level, player.getOnPos().above()) && (flag1 || flag2 || flag3 || flag4)) {
					for (int i = 0; i < numberOfStools; i++) {
						Stool great_stool = new Stool(RisusEntities.GREAT_STOOL.get(), level);
						great_stool.setPos(player.getX(), player.getY() + i*1.5F, player.getZ());
						great_stool.setOwnerUUID(player.getUUID());
						level.addFreshEntity(great_stool);
						player.level().playSound(null, player.getOnPos().above(), SoundEvents.WOOD_PLACE, SoundSource.PLAYERS);
						((ServerLevel) player.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, RisusBlocks.BONDKNOT_PLANKS.get().defaultBlockState()), great_stool.getX(), great_stool.getY()+0.5, great_stool.getZ(), 7, 0, 0, 0, 0.4);
					}
					player.addEffect(new MobEffectInstance(RisusMobEffects.GREATNESS, -1, 0, false, false, false), player);

					player.teleportTo(player.getX(), player.getY() + numberOfStools*1.5, player.getZ());
					} else if (player.hasEffect(RisusMobEffects.GREATNESS)) {
					player.removeEffect(RisusMobEffects.GREATNESS);
					player.level().getEntities((Entity) null, new AABB(player.getOnPos()).inflate(1, 10, 1), entity -> entity instanceof Stool).forEach(entity -> {
						if (entity instanceof Stool stool && stool.getOwner() == player) {
							((ServerLevel) stool.level()).sendParticles(ParticleTypes.POOF, stool.getX(), stool.getY()+0.5, stool.getZ(), 3, 0, 0, 0, 0.1);
							stool.kill();
							player.level().playSound(null, player.getOnPos().above(), SoundEvents.WOOD_BREAK, SoundSource.PLAYERS);
						}
					});

					if (player.isCrouching() && !level.getBlockState(player.getOnPos().above(1+(int) (-numberOfStools*1.5))).isSolidRender(level, player.getOnPos().above(1+(int) (-numberOfStools*1.5))) ) {player.teleportTo(player.getX(), player.getY() - numberOfStools*1.5, player.getZ());}
				}
			});
		}
	}
}

