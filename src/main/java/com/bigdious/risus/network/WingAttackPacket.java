package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.data.StabParticleData;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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

public record WingAttackPacket(float yRot) implements CustomPacketPayload {
	public static final Type<WingAttackPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "wing_attack"));
//	public static final WingAttackPacket INSTANCE = new WingAttackPacket();
	public static final StreamCodec<RegistryFriendlyByteBuf, WingAttackPacket> STREAM_CODEC = CustomPacketPayload.codec(WingAttackPacket::write, WingAttackPacket::read);

	public static WingAttackPacket read(RegistryFriendlyByteBuf buf) {
		float yRot = buf.readFloat();
		return new WingAttackPacket(yRot);
	}

	public void write(RegistryFriendlyByteBuf buf) {
		buf.writeFloat(this.yRot());
	}
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(WingAttackPacket packet, IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				Level level = player.level();
				if (player.getItemBySlot(EquipmentSlot.CHEST).is(RisusItems.DIAMOND_TIPPED_ANGEL_WINGS.get())) {
					boolean flag = player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() < player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage() - 5;
					if ((!player.isCreative() || player.onGround()) && !player.isFallFlying() && !player.isSwimming() && !player.isVisuallyCrawling() && flag) {
						float yRot = packet.yRot();
						boolean hitEntity = true;

						Vec3 lookVec = Vec3.directionFromRotation(0, yRot);
						List<Entity> possibleList = level.getEntities(player, player.getBoundingBox().expandTowards(lookVec.x() * 1.5, 0, lookVec.z() * 1.5).inflate(0.5, 0, 0.5));
						for (Entity attackable : possibleList) {
							//don't believe the yellow underlined lies
							if (attackable instanceof LivingEntity target && !level.isClientSide() && !target.isDeadOrDying() && flag) {
								target.hurt(player.damageSources().source(RisusDamageTypes.WING_STAB), 5);
								target.knockback(1, -lookVec.x, -lookVec.z);
								if (level instanceof ServerLevel serverLevel) {
									serverLevel.sendParticles(RisusParticles.BLOOD_FEATHER.get(), player.getRandomX(1), target.getY()+1.5, player.getRandomZ(1), 1, 0, 0, 0, 0);
								}
								player.getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(4, player, EquipmentSlot.CHEST);
								hitEntity = true;
							}
						}
						if (level instanceof ServerLevel serverLevel && hitEntity) {
							//multiplication at the end determines range, everything before is just for rotation
							double d0 = -Mth.sin(yRot * (float) (Math.PI / 180.0)) * 1.6;
							double d1 = Mth.cos(yRot * (float) (Math.PI / 180.0)) * 1.6;
							double right0 = -Mth.sin((yRot+90) * (float) (Math.PI / 180.0)) * 0.5;
							double right1 = Mth.cos((yRot+90) * (float) (Math.PI / 180.0)) * 0.5;
							double left0 = -Mth.sin((yRot-90) * (float) (Math.PI / 180.0)) * 0.5;
							double left1 = Mth.cos((yRot-90) * (float) (Math.PI / 180.0)) * 0.5;
							//we move the particles to the side, then forward
							serverLevel.sendParticles(new StabParticleData(yRot-115), player.getX()+right0+d0, player.getY(0.40), player.getZ()+right1+d1, 1, 0, 0, 0, 0);
							serverLevel.sendParticles(new StabParticleData(yRot-65), player.getX()+left0+d0, player.getY(0.40), player.getZ()+left1+d1, 1, 0, 0, 0, 0);
							serverLevel.playSound(null, player.getOnPos().above(), RisusSoundEvents.WING_STAB.get(), SoundSource.PLAYERS);
						}
					}
				}
			});
		}
	}
}
