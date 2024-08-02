package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusRecipes;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class AlterationCatalystBlockEntity extends BlockEntity implements WorldlyContainer {

	protected ItemStack item = ItemStack.EMPTY;
	public boolean isCrafting;
	private int craftingCounter;
	public float rotationDegrees;
	private boolean finishedCrafting;
	private int finishedCounter;

	public AlterationCatalystBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.ALTERATION_CATALYST.get(), pos, state);
		this.craftingCounter = 1;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, AlterationCatalystBlockEntity te) {
		int craftingLength = 100;

		if (te.isCrafting) {
			if (te.getRecipe(level, te.item) == null) {
				te.isCrafting = false;
				te.setChanged();
			}
			te.craftingCounter++;
		}
		if (level.isClientSide()) {
			if (te.craftingCounter>1 && te.craftingCounter<60) {
				RandomSource random = level.getRandom();
				BlockPos randomPos = pos.offset(Mth.floor(random.nextFloat() * 2.0F * (random.nextBoolean() ? 1.0F : -1.0F)), 2, Mth.floor(random.nextFloat() * 2.0F * (random.nextBoolean() ? 1.0F : -1.0F)));

				double d0 = Mth.sin((float) (te.craftingCounter + 35) * 0.1F) / 2.0F + 0.5F;
				d0 = (d0 * d0 + d0) * (double) 0.3F;
				Vec3 vec3 = new Vec3(pos.getX() + 0.5D, pos.getY() + 2.0D + d0, pos.getZ() + 0.5D);

				BlockPos blockpos1 = randomPos.subtract(pos);
				float f = -0.5F + random.nextFloat() + (float) blockpos1.getX();
				float f1 = -2.0F + random.nextFloat() + (float) blockpos1.getY();
				float f2 = -0.5F + random.nextFloat() + (float) blockpos1.getZ();
				level.addParticle(AlterationParticleOptions.ALTERATION_FADE, vec3.x(), vec3.y(), vec3.z(), f, f1, f2);
			}

		}

		if (te.craftingCounter > craftingLength) {
			te.craftingCounter = 1;

			if (te.isCrafting) {
				AlterationRecipe recipe = te.getRecipe(level, te.item);
				if (recipe != null) {
					te.item = recipe.assemble(new SingleRecipeInput(te.item), level.registryAccess());
					te.setChanged();
					te.finishedCrafting = true;
					te.finishedCounter = 0;

					level.playSound(null, pos, SoundEvents.PLAYER_BREATH, SoundSource.BLOCKS, 1.0F, 0.5F);
				}

				te.isCrafting = false;
				te.setChanged();
			}
			te.updateBlock();
		}

		if (te.finishedCrafting) {
			if (te.finishedCounter++ >= 20) {
				te.finishedCrafting = false;
				te.finishedCounter = 0;
			}
			if (level.isClientSide()) {
				for (int i = 0; i < 7; i++) {
					level.addParticle(RisusParticles.ALTERATION_FINISHED.get(),
							(pos.getX() + 0.15F) + (level.getRandom().nextFloat() * 0.75F),
							pos.getY() + 1.0F,
							(pos.getZ() + 0.15F) + (level.getRandom().nextFloat() * 0.75F),
							0.0F, 0.1F, 0.0F);
				}
			}
		}
	}


	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
		super.saveAdditional(tag, pRegistries);
		if (this.item != null && !this.item.isEmpty()) {
			Tag reagentTag = this.item.save(pRegistries);
			tag.put("item", reagentTag);
		}
		tag.putBoolean("isCrafting", this.isCrafting);
		tag.putBoolean("finishedCrafting", this.finishedCrafting);
		tag.putInt("counter", this.craftingCounter);
		tag.putFloat("itemRotation", this.rotationDegrees);
	}


	public void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
		if (tag.contains("item")) {
			this.item = ItemStack.CODEC.parse(NbtOps.INSTANCE, tag.get("item")).mapOrElse(Function.identity(), e -> ItemStack.EMPTY);
		} else {
			this.item = ItemStack.EMPTY;
		}
		this.isCrafting = tag.getBoolean("isCrafting");
		this.finishedCrafting = tag.getBoolean("finishedCrafting");
		this.craftingCounter = tag.getInt("counter");
		this.rotationDegrees = tag.getFloat("itemRotation");
		super.loadAdditional(tag, pRegistries);
	}


	public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
		CompoundTag tag = new CompoundTag();
		if (this.item != null && !this.item.isEmpty()) {
			Tag reagentTag = this.item.save(pRegistries);
			tag.put("item", reagentTag);
		}
		tag.putInt("counter", this.craftingCounter);
		tag.putBoolean("isCrafting", this.isCrafting);
		tag.putBoolean("finishedCrafting", this.finishedCrafting);
		tag.putFloat("itemRotation", this.rotationDegrees);
		super.saveAdditional(tag, pRegistries);
		return tag;
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider pRegistries) {
		super.onDataPacket(net, packet, pRegistries);
		this.handleUpdateTag(packet.getTag() == null ? new CompoundTag() : packet.getTag(), pRegistries);
	}


	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Nullable
	public AlterationRecipe getRecipe(Level level, ItemStack stack) {
		return level.getRecipeManager().getRecipeFor(RisusRecipes.ALTERATION_RECIPE.get(), new SingleRecipeInput(stack), level).map(RecipeHolder::value).orElse(null);
	}

	public void attemptCraft(Level level, ItemStack item) {
		if (this.isCrafting)
			return;
		this.isCrafting = this.craftingPossible(level, item);
	}

	public boolean craftingPossible(Level level, ItemStack stack) {
		if (this.isCrafting || stack.isEmpty())
			return false;
		return this.getRecipe(level, stack) != null;
	}

	public boolean updateBlock() {
		if (this.craftingCounter == 0)
			this.craftingCounter = 1;
		if (this.getLevel() != null) {
			BlockState state = this.getLevel().getBlockState(this.getBlockPos());
			this.getLevel().sendBlockUpdated(this.getBlockPos(), state, state, 2);
			this.setChanged();
			return true;
		}
		return false;
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return this.item.isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.item;
	}

	@Override
	public ItemStack removeItem(int slot, int count) {
		if (this.isCrafting) return ItemStack.EMPTY;
		ItemStack stack = item.copy().split(count);
		item.shrink(count);
		this.updateBlock();
		return stack;
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_18951_) {
		if (this.isCrafting) return ItemStack.EMPTY;
		return this.item;
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		if (this.isCrafting) return;
		this.item = stack;
		this.rotationDegrees = this.getLevel().getRandom().nextFloat() * 360.0F;
		this.updateBlock();
		this.attemptCraft(this.getLevel(), stack);
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public boolean stillValid(Player player) {
		return false;
	}

	@Override
	public void clearContent() {
		this.item = ItemStack.EMPTY;
	}
	@Nullable
	public ItemStack getInputItem() {
		return this.getItem(0);
	}

	public void setInputItem(ItemStack item) {
		this.setItem(0, item);
		this.setChanged();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return new int[]{0};
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return this.item.isEmpty();
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return direction == Direction.DOWN && !this.item.isEmpty() && !this.isCrafting;
	}
}
