package com.bigdious.risus.items;


import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusSoundEvents;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class ToothknockerItem extends TieredItem implements Vanishable {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ToothknockerItem(Tier tier, int damage, float speed, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)damage + tier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)speed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }
    public float getDamage() {
        return this.attackDamage;
    }

    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }
    public boolean mineBlock(ItemStack p_43282_, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity p_43286_) {
        if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
            p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return true;
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
            player.addEffect(new MobEffectInstance(RisusMobEffects.TOOTHLUSTER.get(), 0, 0));
            player.getOffhandItem().hurtAndBreak(1, player, (what) -> {
                what.broadcastBreakEvent(player.getUsedItemHand());});
            player.getMainHandItem().hurtAndBreak(1, player, (what) -> {
                what.broadcastBreakEvent(player.getUsedItemHand());});
                Level level = player.level();
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TURTLE_EGG_BREAK, SoundSource.PLAYERS, 0.5F, 1.0F);;

            //if(player.getAttribute(Attributes.ATTACK_DAMAGE).getModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee")) != null){
            //player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee"));
            //} else {if (player.getAttribute(Attributes.ATTACK_DAMAGE).getModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee")) == null) {
            //player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee"), "Toothknocker attack damage", 3, AttributeModifier.Operation.ADDITION));
            //}}} else {player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee"));}
        }return super.onLeftClickEntity(stack, player, entity);
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43274_) {
        return p_43274_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43274_);
    }
}

