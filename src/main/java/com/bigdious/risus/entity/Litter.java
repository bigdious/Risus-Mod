package com.bigdious.risus.entity;

import com.bigdious.risus.blocks.LightExcrementBlock;
import com.bigdious.risus.init.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class Litter extends TamableAnimal {

	private int lightCheckInterval = 100;
	private static final EntityDataAccessor<BlockState> BLOCK = SynchedEntityData.defineId(Litter.class, EntityDataSerializers.BLOCK_STATE);

	public Litter(Level level, @Nullable Player owner) {
		super(RisusEntities.LITTER.get(), level);
		if (owner != null) {
			this.setOwnerUUID(owner.getUUID());
		}
	}

	public Litter(EntityType<Litter> type, Level level) {
		super(type, level);
	}


	public static AttributeSupplier.Builder createAttributes() {
		return TamableAnimal.createLivingAttributes()
			.add(Attributes.MAX_HEALTH, 20.0)
			.add(Attributes.MOVEMENT_SPEED, 0.3)
			.add(Attributes.FOLLOW_RANGE, 32.0);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Frog.class, 8.0F, 1.0F, 1.25F));
		this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 2.0F));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BLOCK, Blocks.STONE.defaultBlockState());
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();
		if (!this.unableToMoveToOwner()) {
			this.lightCheckInterval--;
			if (this.lightCheckInterval <= 0) {
				if (this.onGround() && this.level().getBrightness(LightLayer.BLOCK, this.blockPosition()) <= 0 && this.level().isEmptyBlock(this.blockPosition())) {
					if (this.level().canSeeSky(this.blockPosition()) && this.level().isDay()) {this.lightCheckInterval = 100; return;}
					this.level().setBlockAndUpdate(this.blockPosition(), RisusBlocks.LIGHT_EXCREMENT.get().defaultBlockState().setValue(LightExcrementBlock.FACING, Direction.UP));
					this.playSound(RisusSoundEvents.LITTER_LAY_EXCREMENT.get(), 0.15F, 0.75F);
				}
				this.lightCheckInterval = 60;
			}
		} else {
			this.lightCheckInterval = 100;
		}
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return -level.getPathfindingCostFromLightLevels(pos);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.put("light_block", NbtUtils.writeBlockState(this.getLightBlockState()));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("light_block", 10)) {
			this.setLightBlockState(NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), tag.getCompound("light_block")));
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (this.isAlive()) {
			ItemStack stack = player.getItemInHand(hand);
			if (stack.is(Items.NAME_TAG)) return InteractionResult.PASS;

			if (player.isShiftKeyDown()) {
				if (this.getOwner() == player) {
					if (!this.level().isClientSide()) {
						ItemStack litterItem = new ItemStack(RisusItems.LITTER.get());
						Component nameTag = this.getCustomName();
						if (nameTag != null && !nameTag.getString().isEmpty()) {
							litterItem.set(DataComponents.CUSTOM_NAME, nameTag);
						}
						litterItem.set(RisusDataComponents.BLOCK_STATE, this.getLightBlockState());
						if (player.getInventory().add(litterItem)) {
							this.discard();
							this.playSound(SoundEvents.ITEM_PICKUP, 0.5F, this.getRandom().nextFloat() * 0.1F + 0.9F);
						}
					}
					return InteractionResult.sidedSuccess(this.level().isClientSide());
				} else {
					player.displayClientMessage(Component.translatable("entity.risus.player_doesnt_own").withStyle(ChatFormatting.DARK_RED), true);
					return InteractionResult.CONSUME;
				}
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public void tame(Player player) {
		this.setTame(true, false);
		this.setOwnerUUID(player.getUUID());
	}

	@Override
	public boolean wantsToAttack(LivingEntity owner, LivingEntity target) {
		return false;
	}

	@Override
	public boolean isFood(ItemStack itemStack) {
		return false;
	}

	@Override
	public boolean isInvulnerable() {
		return true;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return true;
	}

	@Override
	public boolean onClimbable() {
		return false;
	}

	@Override
	public void knockback(double x, double y, double z) {
	}

	@Override
	protected void pushEntities() {
	}

	@Override
	public boolean addEffect(MobEffectInstance instance, @Nullable Entity entity) {
		return false;
	}

	@Override
	public boolean causeFallDamage(float dist, float mult, DamageSource source) {
		return false;
	}

	@Override
	public void checkDespawn() {
	}

	@Override
	public boolean attackable() {
		return false;
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return false;
	}

	@Override
	public boolean canBeLeashed() {
		return false;
	}

	@Override
	public boolean isIgnoringBlockTriggers() {
		return true;
	}

	@Override
	public @Nullable ItemStack getPickResult() {
		return new ItemStack(RisusItems.LITTER, 1, DataComponentPatch.builder().set(RisusDataComponents.BLOCK_STATE.get(), this.getLightBlockState()).build());
	}

	@Override
	public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return null;
	}

	public void setLightBlockState(BlockState state) {
		this.getEntityData().set(BLOCK, state);
	}

	public BlockState getLightBlockState() {
		return this.getEntityData().get(BLOCK);
	}
}
