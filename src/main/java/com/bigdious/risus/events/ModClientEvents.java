package com.bigdious.risus.events;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entities.AngelEntity;
import com.bigdious.risus.util.RegistryHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Risus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onDamageAngel(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == RegistryHandler.JOYKILLER.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (target instanceof AngelEntity) {
                    PlayerEntity player = event.getPlayer();
                    target.setHealth(0.01F);
                    }
                }
            }
        }

    }


