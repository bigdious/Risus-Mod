package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class EndlessPearlItem extends EnderpearlItem {
    public EndlessPearlItem(Item.Properties props) {
        super(props);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        int validSlot = 0;
        for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
            ItemStack checkStack = player.getInventory().getItem(i);
            if (checkStack.is(Items.ENDER_PEARL)) {
                validSlot = i;
                break;
            }
        }
        ItemStack ammo = player.getInventory().getItem(validSlot);
        if (validSlot !=-1 && ammo.is(Items.ENDER_PEARL) && itemstack.getDamageValue() != itemstack.getMaxDamage()-15){
            //fuck you -1
                int amount = ammo.getCount();
                itemstack.hurt(-amount, level.getRandom(),null);
                ammo.shrink(amount);
            }
        else{

        if (itemstack.getDamageValue() == itemstack.getMaxDamage()) {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        } else {
            level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this, 20);
            if (!level.isClientSide) {
                ThrownEnderpearl thrownenderpearl = new ThrownEnderpearl(level, player);
                thrownenderpearl.setItem(itemstack);
                thrownenderpearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(thrownenderpearl);
                itemstack.hurt(1, level.getRandom(), null);
            }

            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
    }
        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }
    @Override
    public boolean isEnchantable(ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemstack, ItemStack book) {
        return false;
    }

    public boolean canApplyAtEnchantingTable(ItemStack itemstack, Enchantment enchantment) {
        return false;
    }

}
