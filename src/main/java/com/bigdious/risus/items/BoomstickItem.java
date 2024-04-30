package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

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
	//TODO make boomstick work when enemies hold it
	@Override
	public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
		Level level = pAttacker.level();
		ItemStack itemstack = pAttacker.getMainHandItem();
		if (pAttacker.isHolding(RisusItems.BOOMSTICK.get())) {
			PrimedTnt primedtnt = new PrimedTnt(level, pTarget.getX() + 0.5, pTarget.getY(), pTarget.getZ() + 0.5, pAttacker);
			level.addFreshEntity(primedtnt);
			primedtnt.setFuse(0);
			itemstack.hurt(1, level.getRandom(), null);
			level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
			}
		return super.hurtEnemy(pStack, pTarget, pAttacker);
	}


	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		List<Enchantment> validEnchants = List.of(Enchantments.UNBREAKING);
		return validEnchants.contains(enchantment);
	}
}
