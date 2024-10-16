package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.util.RisusToolMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BoomstickItem extends Item {

	public BoomstickItem( Item.Properties pProperties){
		super(pProperties);
	}
	@Override
	public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
		return pRepair.is(Items.TNT);
	}
	@Override
	public boolean isEnchantable(ItemStack itemstack) {
		return true;
	}



}
