package com.bigdious.risus.items;


import com.bigdious.risus.init.RisusItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;


import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class ToothknockerItem extends SwordItem {
    public ToothknockerItem(Tier tier, int damage, float speed, Item.Properties properties) {
        super(tier, damage, speed, properties);
    }
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        List<Enchantment> validEnchants = List.of(Enchantments.MENDING, Enchantments.UNBREAKING, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK, Enchantments.MOB_LOOTING, Enchantments.VANISHING_CURSE);
        AtomicBoolean flag = new AtomicBoolean(false);
        validEnchants.forEach(enchantment -> {
            if (EnchantmentHelper.getEnchantments(book).containsKey(enchantment)) {
                flag.set(true);
            }
        });
        return flag.get();
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        List<Enchantment> validEnchants = List.of( Enchantments.UNBREAKING, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK, Enchantments.VANISHING_CURSE);
        return validEnchants.contains(enchantment);
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity){
        if (player.isHolding(RisusItems.TOOTHKNOCKER.get()) && player.getOffhandItem().is(RisusItems.TOOTHKNOCKER.get())) {
            if(player.getAttribute(Attributes.ATTACK_DAMAGE).getModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee")) == null){
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee"));
            }
        } else{
            if(player.getAttribute(Attributes.ATTACK_DAMAGE).getModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee")) != null){
                player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee"), "Toothknocker attack damage", 3, AttributeModifier.Operation.ADDITION));
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
