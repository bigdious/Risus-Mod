package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
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
	private final float attackDamage;

	public BoomstickItem(int damage, Item.Properties pProperties){
		super(pProperties);
		this.attackDamage = -1;
	}
	public float getDamage() {
		return this.attackDamage;
	}
	@Override
	public boolean isEnchantable(ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack, ItemStack book) {
		List<Enchantment> validEnchants = List.of(Enchantments.MENDING, Enchantments.UNBREAKING);
		AtomicBoolean flag = new AtomicBoolean(false);
		validEnchants.forEach(enchantment -> {
			if (EnchantmentHelper.getEnchantments(book).containsKey(enchantment)) {
				flag.set(true);
			}
		});
		return flag.get();
	}
	//TODO make it work with enemies
	@Override
	public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
		Level level = pAttacker.level();
		ItemStack itemstack = pAttacker.getMainHandItem();
		if (pTarget.getType() != EntityType.ARMOR_STAND
			&& pTarget.getType() != EntityType.BOAT
			&& pTarget.getType() != EntityType.CHEST_BOAT
			&& pTarget.getType() != EntityType.ITEM_FRAME
			&& pTarget.getType() != EntityType.GLOW_ITEM_FRAME
			&& pTarget.getType() != EntityType.MINECART)
		if (!level.isClientSide()) {
			this.explode(level, pTarget.getOnPos(), pAttacker);
			itemstack.hurt(1, level.getRandom(), null);

			}
		return super.hurtEnemy(pStack, pTarget, pAttacker);
	}
	private void explode(Level level, BlockPos pos, LivingEntity entity) {
		Vec3 vec3 = pos.getCenter().add(0, 1, 0);
		level.explode(null, level.damageSources().explosion(entity, entity), null, vec3, 3F, false, Level.ExplosionInteraction.BLOCK);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		List<Enchantment> validEnchants = List.of(Enchantments.UNBREAKING);
		return validEnchants.contains(enchantment);
	}
}