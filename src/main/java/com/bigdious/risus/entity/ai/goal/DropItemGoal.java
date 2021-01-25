package com.bigdious.risus.entity.ai.goal;

import com.bigdious.risus.entity.HolderEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class DropItemGoal extends Goal {

    private final HolderEntity holder;
    
    public DropItemGoal(HolderEntity entity) {
        this.holder = entity;
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity attacker = this.holder.getRevengeTarget();
        if (attacker instanceof LivingEntity) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void startExecuting() {
       ItemStack stolenItem = this.holder.getHeldItemMainhand();
       this.holder.entityDropItem(stolenItem);
       this.holder.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
       
    }

}
