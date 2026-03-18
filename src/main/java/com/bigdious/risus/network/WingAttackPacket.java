package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import javax.annotation.Nullable;
import javax.xml.crypto.dsig.Transform;
import java.util.List;
import java.util.Optional;

public class WingAttackPacket implements CustomPacketPayload {
	public static final Type<WingAttackPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "wing_attack"));
	public static final WingAttackPacket INSTANCE = new WingAttackPacket();
	public static final StreamCodec<ByteBuf, WingAttackPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				Level level = player.level();
				if (player.getItemBySlot(EquipmentSlot.CHEST).is(RisusItems.DIAMOND_TIPPED_ANGEL_WINGS.get())) {
					boolean flag = player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() < player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage() - 5;
					if (!player.isFallFlying() && !player.isSwimming() && !player.isVisuallyCrawling() && flag) {
						Vec3 lookVec = Vec3.directionFromRotation(0, player.getRotationVector().y);
						List<Entity> possibleList = level.getEntities(player, player.getBoundingBox().expandTowards(lookVec.x() * 1.5, 0, lookVec.z() * 1.5).inflate(0.5, 0, 0.5));
						for (Entity attackable : possibleList) {
							//don't believe the yellow underlined lies
							if (attackable instanceof LivingEntity target && !level.isClientSide() && !target.isDeadOrDying() && flag) {
								target.hurt(player.damageSources().source(RisusDamageTypes.WING_STAB), 5);
								target.knockback(1, -lookVec.x, -lookVec.z);
								if (level instanceof ServerLevel serverLevel) {
									serverLevel.sendParticles(RisusParticles.BLOOD_FEATHER.get(), target.getRandomX(1), target.getEyeY(), target.getRandomZ(1), 1, 0, 0, 0, 0);
								}
								player.getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(4, player, EquipmentSlot.CHEST);
							}
						}

					}
				}
			});
		}
	}

	//adapted from Twilight Forest LifedrainScepterItem
	//https://github.com/TeamTwilight/twilightforest/blob/1.21.1/src/main/java/twilightforest/item/LifedrainScepterItem.java

	@Nullable
	private static List<Entity> getPlayerLookTarget(Level level, LivingEntity living) {
		Vec3 lookVec = Vec3.directionFromRotation(0, living.getRotationVector().y);
		List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x()*3, 0, lookVec.z() * 3).inflate(0.5, 0, 0.5));
		return possibleList;
	}


}
