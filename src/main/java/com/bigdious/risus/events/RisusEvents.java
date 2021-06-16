package com.bigdious.risus.events;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.AngelEntity;
import com.bigdious.risus.items.RisusItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Risus.ID)
public class RisusEvents {

    //Angel is only attackable with the Joykiller
    @SubscribeEvent
    public static void onDamageAngel(AttackEntityEvent event) {
        if (event.getTarget() instanceof AngelEntity && event.getEntityLiving().getHeldItemMainhand().getItem() != RisusItems.JOYKILLER.get()) {
            event.setCanceled(true);
        }
    }

    //lets also cancel all other damage so ONLY the joykiller can harm it
    @SubscribeEvent
    public static void cancelAngelDMG(LivingHurtEvent event) {
        if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
            event.setCanceled(true);
        }
    }
}


