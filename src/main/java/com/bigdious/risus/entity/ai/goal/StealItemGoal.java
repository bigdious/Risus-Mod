package com.bigdious.risus.entity.ai.goal;

import java.util.List;

import com.bigdious.risus.entity.HolderEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class StealItemGoal extends MeleeAttackGoal {
    private final HolderEntity holder;
    private LivingEntity target;
    private boolean attackFlag = false;
  
    public StealItemGoal(HolderEntity entity, double speedIn, boolean longMemoryIn) {
        super(entity, speedIn, longMemoryIn);
    	this.holder = entity;
    }
    
    //FIXME Holder is classified as a monster, causing this to always return false
    public boolean areMobsAround() {
        List<MonsterEntity> entityList = this.holder.world.getEntitiesWithinAABB(MonsterEntity.class, this.holder.getBoundingBox().grow((double) 12.0D));
        return !entityList.isEmpty();
    }
    
    @Override
    public boolean shouldExecute() {
        this.target = this.holder.getAttackTarget();
        if(this.target != null) {
           if(!this.holder.isAlive()) {
               return false;
           }

            return !this.target.getHeldItemMainhand().isEmpty();
        
//           if (this.areMobsAround()) {
 //              return false;
 //          }
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