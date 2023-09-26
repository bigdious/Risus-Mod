package com.bigdious.risus.items;


import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.network.CreateCritParticlePacket;
import com.bigdious.risus.network.RisusPacketHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = Risus.MODID)
    public class RisusItemEvent {
    @SubscribeEvent
    public static void risusAttackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        float damage = event.getAmount();

        if (source instanceof Player player) {
            if (player.hasEffect(RisusMobEffects.TOOTHLUSTER.get())) {
                if (!event.getEntity().level().isClientSide()) {
                    RisusPacketHandler.CHANNEL.send(PacketDistributor.ALL.noArg(), new CreateCritParticlePacket(event.getEntity().getId(), 2, RisusParticles.TOOTHICAL.get()));
                }
            }
        }
    }
}

