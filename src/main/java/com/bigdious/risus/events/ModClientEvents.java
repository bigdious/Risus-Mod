package com.bigdious.risus.events;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.AngelEntity;
import com.bigdious.risus.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Risus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onDamageAngel(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.JOYKILLER) {
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


