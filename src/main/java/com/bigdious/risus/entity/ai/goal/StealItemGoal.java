package com.bigdious.risus.entity.ai.goal;

import java.util.EnumSet;
import java.util.List;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.HolderEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class StealItemGoal extends Goal {
    private final HolderEntity holder;
    private LivingEntity target;
    private boolean attackFlag = false;
  
    public StealItemGoal(HolderEntity entity) {
        this.holder = entity;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }
    
    //FIXME Holder is classified as a monster, causing this to always return false
    public boolean areMobsAround() {
        List<MonsterEntity> entityList = this.holder.world.getEntitiesWithinAABB(MonsterEntity.class, this.holder.getBoundingBox().grow((double) 12.0D));
        if (entityList.isEmpty()) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean shouldExecute() {
        this.target = this.holder.getAttackTarget();
        if(this.target != null) {
           if(!this.holder.isAlive()) {
               return false;
           }   
        
           if(this.target.getHeldItemMainhand().isEmpty()) {
               return false;
           }
        
//           if (this.areMobsAround()) {
 //              return false;
 //          }
           
           return true;
       }
        
        return false;
    }
    
    @Override
    public void startExecuting() {
        this.holder.getNavigator().clearPath();
        this.target = this.holder.getAttackTarget();
     }
    
    @Override
    public void tick() {
        LivingEntity target = this.holder.getAttackTarget();
        if(target != null && !attackFlag) {
            if(this.holder.getDistance(target) >= 1.5D) {
                this.holder.getNavigator().tryMoveToEntityLiving(target, 0.5F);
            } else {
                target.attackEntityFrom(DamageSource.causeMobDamage(this.holder), 1.0F);
                this.attackFlag = true;
            }
            
            if(attackFlag && holder.getHeldItemMainhand() == ItemStack.EMPTY) {
                ItemStack stolen = target.getHeldItemMainhand().getStack();
                holder.setItemStackToSlot(EquipmentSlotType.MAINHAND, stolen);
                target.setHeldItem(target.getActiveHand(), ItemStack.EMPTY);
            }
            
            if(holder.getHeldItemMainhand() == ItemStack.EMPTY) {
                this.attackFlag = false;
            }
        }
    }
    
}