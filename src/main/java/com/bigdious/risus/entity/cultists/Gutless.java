package com.bigdious.risus.entity.cultists;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class Gutless extends AbstractIllager {
	public Gutless(EntityType<? extends Gutless> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.35F)
			.add(Attributes.FOLLOW_RANGE, 12.0)
			.add(Attributes.MAX_HEALTH, 36.0)
			.add(Attributes.ATTACK_DAMAGE, 5.0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
		this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
		this.setDropChance(EquipmentSlot.MAINHAND, 0.00F);
	}

	@Override
	public AbstractIllager.IllagerArmPose getArmPose() {
		if (this.isAggressive()) {
			return AbstractIllager.IllagerArmPose.ATTACKING;
		} else {
			return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
		}
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		RandomSource randomsource = level.getRandom();
		this.populateDefaultEquipmentSlots(randomsource, difficulty);
		this.populateDefaultEquipmentEnchantments(level, randomsource, difficulty);
		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	@Override
	public void applyRaidBuffs(ServerLevel level, int wave, boolean unused) {
		ItemStack itemstack = new ItemStack(Items.IRON_AXE);
		Raid raid = this.getCurrentRaid();
		boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
		if (flag) {
			ResourceKey<EnchantmentProvider> resourcekey = wave > raid.getNumGroups(Difficulty.NORMAL)
				? VanillaEnchantmentProviders.RAID_VINDICATOR_POST_WAVE_5
				: VanillaEnchantmentProviders.RAID_VINDICATOR;
			EnchantmentHelper.enchantItemFromProvider(
				itemstack, level.registryAccess(), resourcekey, level.getCurrentDifficultyAt(this.blockPosition()), this.random
			);
		}

		this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.VINDICATOR_CELEBRATE;
	}
}
