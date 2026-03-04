package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.bigdious.risus.init.*;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class AlterationCatalystBlockEntity extends BlockEntity implements WorldlyContainer, ContainerSingleItem.BlockContainerSingleItem {

	protected ItemStack item = ItemStack.EMPTY;
	public boolean isCrafting;
	public int craftingCounter;
	public float rotationDegrees;
	private boolean finishedCrafting;
	private boolean craftingWorked;
	public boolean failedCrafting;
	private int finishedCounter;
	private int failedCounter;

	public AlterationCatalystBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.ALTERATION_CATALYST.get(), pos, state);
		this.craftingCounter = 1;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, AlterationCatalystBlockEntity te) {
		int craftingLength = 100;

		if (te.isCrafting) {
			if (!te.item.is(Items.GOAT_HORN) && !te.item.is(RisusTags.Items.ENCHANTED_BOOK_EQUIVALENT) && !te.item.isEnchanted() && te.getRecipe(level, te.item) == null) {
				te.isCrafting = false;
				te.setChanged();
			}
			te.craftingCounter++;
		}
		if (level.isClientSide()) {
			if (te.craftingCounter > 1 && te.craftingCounter < 60) {
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
				if (te.item.is(Items.GOAT_HORN)){
					ItemStack warhorn = new ItemStack(RisusItems.WARHORN.get());
					if (te.item.get(DataComponents.INSTRUMENT) != null) {
						Holder<Instrument> holder = te.item.get(DataComponents.INSTRUMENT).getDelegate();
						warhorn.set(DataComponents.INSTRUMENT, holder);
					}
					te.item = warhorn;
					te.setChanged();
					te.finishedCrafting = true;
					te.finishedCounter = 0;
					level.playSound(null, pos, SoundEvents.PLAYER_BREATH, SoundSource.BLOCKS, 1.0F, 0.5F);
				}
				else if (recipe != null) {
					ItemStack resultItem = recipe.assemble(new SingleRecipeInput(te.item), level.registryAccess());
					resultItem.setCount(te.item.getCount());
					te.item = resultItem;
					te.setChanged();
					te.finishedCrafting = true;
					te.finishedCounter = 0;

					level.playSound(null, pos, SoundEvents.PLAYER_BREATH, SoundSource.BLOCKS, 1.0F, 0.5F);
				}
				else if (te.item.isEnchanted() || te.item.is(RisusTags.Items.ENCHANTED_BOOK_EQUIVALENT)) {
					//why the hell don't enchanted books use DataComponents.ENCHANTMENTS ??? Update: Got the answer, still disagree
					if (te.item.getItem() instanceof EnchantedBookItem) {
						for (Object2IntMap.Entry<Holder<Enchantment>> entry : te.item.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY).entrySet()) {
							if (entry.getKey().is(RisusTags.Enchantments.ALTERABLE_ENCHANTS)) {
								te.item.enchant(
									level.registryAccess().registry(Registries.ENCHANTMENT).get().getHolderOrThrow(ENCHANT_TO_EXEC.get(entry.getKey().getKey())),
									te.item.get(DataComponents.STORED_ENCHANTMENTS).getLevel(level.registryAccess().holderOrThrow(entry.getKey().getKey()))
								);
								Holder<Enchantment> holder = level.holder(entry.getKey().getKey()).orElse(null);
								ItemEnchantments ench = Optional.ofNullable(te.item.get(DataComponents.STORED_ENCHANTMENTS)).orElse(ItemEnchantments.EMPTY);
								ItemEnchantments.Mutable mut = new ItemEnchantments.Mutable(ench);
								mut.set(holder, 0);
								te.item.set(DataComponents.STORED_ENCHANTMENTS, mut.toImmutable());
								te.craftingWorked = true;
							}
						}
					} else {
						//we're looking through all the enchantments, seeing if we have any convertable ones, then adding the execration with the correct level and deleting the enchantment
						for (Object2IntMap.Entry<Holder<Enchantment>> entry : te.item.getTagEnchantments().entrySet()) {
							if (entry.getKey().is(RisusTags.Enchantments.ALTERABLE_ENCHANTS)) {
								te.item.enchant(
									level.registryAccess().registry(Registries.ENCHANTMENT).get().getHolderOrThrow(ENCHANT_TO_EXEC.get(entry.getKey().getKey())),
									te.item.get(DataComponents.ENCHANTMENTS).getLevel(level.registryAccess().holderOrThrow(entry.getKey().getKey()))
								);
								Holder<Enchantment> holder = level.holder(entry.getKey().getKey()).orElse(null);
								ItemEnchantments ench = Optional.ofNullable(te.item.get(DataComponents.ENCHANTMENTS)).orElse(ItemEnchantments.EMPTY);
								ItemEnchantments.Mutable mut = new ItemEnchantments.Mutable(ench);
								mut.set(holder, 0);
								te.item.set(DataComponents.ENCHANTMENTS, mut.toImmutable());
								te.craftingWorked = true;
							}
						}
					}
					te.setChanged();
					if (te.craftingWorked) {
						te.finishedCrafting = true;
						te.craftingWorked = false;
						level.playSound(null, pos, SoundEvents.PLAYER_BREATH, SoundSource.BLOCKS, 1.0F, 0.5F);
					} else {
						te.failedCrafting = true;
					}
					te.finishedCounter = 0;

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
		if (te.failedCrafting ) {
			if (te.failedCounter < 1) {
				level.playSound(null, pos, SoundEvents.REDSTONE_TORCH_BURNOUT, SoundSource.BLOCKS, 1.0F, 0.5F);
			}
			if (te.failedCounter++ >= 20) {
				te.failedCrafting = false;
				te.failedCounter = 0;
			}
			if (level.isClientSide()) {
				for (int i = 0; i < 7; i++) {
					level.addParticle(ParticleTypes.SMOKE,
						(pos.getX() + 0.15F) + (level.getRandom().nextFloat() * 0.75F),
						pos.getY() + 1.0F,
						(pos.getZ() + 0.15F) + (level.getRandom().nextFloat() * 0.75F),
						0.0F, 0.1F, 0.0F);
				}
			}
		}
	}


	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		if (this.item != null && !this.item.isEmpty()) {
			tag.put("item", this.item.save(registries));
		}
		tag.putBoolean("isCrafting", this.isCrafting);
		tag.putBoolean("finishedCrafting", this.finishedCrafting);
		tag.putBoolean("failedCrafting", this.failedCrafting);
		tag.putInt("counter", this.craftingCounter);
		tag.putFloat("itemRotation", this.rotationDegrees);
	}


	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		//the below needs the itemtag check, otherwise throws log errors
		if (tag.contains("item")) {
			this.item = ItemStack.parse(registries, tag.getCompound("item")).orElse(ItemStack.EMPTY);
		} else {
			this.item = ItemStack.EMPTY;
		}

		this.isCrafting = tag.getBoolean("isCrafting");
		this.finishedCrafting = tag.getBoolean("finishedCrafting");
		this.failedCrafting = tag.getBoolean("failedCrafting");
		this.craftingCounter = tag.getInt("counter");
		this.rotationDegrees = tag.getFloat("itemRotation");
		super.loadAdditional(tag, registries);
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
		tag.putBoolean("failedCrafting", this.failedCrafting);
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
		return stack.isEnchanted() || stack.is(RisusTags.Items.ENCHANTED_BOOK_EQUIVALENT) || stack.is(Items.GOAT_HORN) || this.getRecipe(level, stack) != null;
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
	public ItemStack splitTheItem(int amount) {
		if (this.isCrafting) return ItemStack.EMPTY;
		return BlockContainerSingleItem.super.splitTheItem(amount);
	}

	@Override
	public ItemStack getTheItem() {
		return this.item;
	}

	@Override
	public void setTheItem(ItemStack item) {
		if (this.isCrafting) return;
		this.item = item;
		this.rotationDegrees = this.getLevel().getRandom().nextFloat() * 360.0F;
		this.updateBlock();
		this.attemptCraft(this.getLevel(), item);
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
		//gizmo, I fucking love you and your ret
		boolean ret =  this.item.isEmpty();
		if (ret) this.updateBlock();
		return ret;
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		boolean ret = direction == Direction.DOWN && !this.item.isEmpty() && !this.isCrafting;
		if (ret) this.updateBlock();
		return ret;
	}

	@Override
	public BlockEntity getContainerBlockEntity() {
		return this;
	}

	public static final Map<ResourceKey<Enchantment>, ResourceKey<Enchantment>> ENCHANT_TO_EXEC = Map.ofEntries(
		Map.entry(Enchantments.SMITE, Execrations.HUNTERS_EXULTATION)
		,Map.entry(Enchantments.IMPALING, Execrations.HUNTERS_EXULTATION)
		,Map.entry(Enchantments.BANE_OF_ARTHROPODS, Execrations.HUNTERS_EXULTATION)
		,Map.entry(Enchantments.FIRE_PROTECTION, Execrations.ELEMENTAL_DEVIATION)
		,Map.entry(Enchantments.PROJECTILE_PROTECTION, Execrations.ELEMENTAL_DEVIATION)
		,Map.entry(Enchantments.BLAST_PROTECTION, Execrations.ELEMENTAL_DEVIATION)
		,Map.entry(Enchantments.MENDING, Execrations.DREAM_EATER)
		,Map.entry(Enchantments.PUNCH, Execrations.PULL)
		,Map.entry(Enchantments.VANISHING_CURSE, Execrations.DENIAL)
		,Map.entry(Enchantments.SOUL_SPEED, Execrations.CACKLING_CRAZE)
		,Map.entry(Enchantments.THORNS, Execrations.AGONY)
		,Map.entry(Enchantments.BINDING_CURSE, Execrations.PERPETUITY)
		,Map.entry(Enchantments.SWEEPING_EDGE, Execrations.GENOCIDE)
		,Map.entry(Enchantments.CHANNELING, Execrations.EMPYREAN_CONDUIT)
		,Map.entry(Enchantments.PIERCING, Execrations.BATTERING)
		,Map.entry(Enchantments.MULTISHOT, Execrations.STAR_RELEASE)
		,Map.entry(Enchantments.EFFICIENCY, Execrations.OVERLOAD)
		,Map.entry(Enchantments.LUCK_OF_THE_SEA, Execrations.MARITIME_SNARE)
		,Map.entry(Enchantments.LURE, Execrations.GRAVITY_WELL)
		,Map.entry(Enchantments.UNBREAKING, Execrations.RELOCATION)
		,Map.entry(Enchantments.DEPTH_STRIDER, Execrations.PYROMANIAC)
		,Map.entry(Enchantments.SILK_TOUCH, Execrations.AVARICIOUS_AMBIT)
		,Map.entry(Enchantments.INFINITY, Execrations.PRESERVATION)
		,Map.entry(Enchantments.PROTECTION, Execrations.VIGOR)
		,Map.entry(Enchantments.SHARPNESS, Execrations.XENOPHOBIA)
		,Map.entry(Enchantments.LOYALTY, Execrations.DEFIANCE)
		,Map.entry(Enchantments.RIPTIDE, Execrations.ERUPTION)
		,Map.entry(Enchantments.KNOCKBACK, Execrations.SOAR)
		,Map.entry(Enchantments.FIRE_ASPECT, Execrations.FERVOUR)
		,Map.entry(Enchantments.FROST_WALKER, Execrations.PROLIFERATION)
		,Map.entry(Enchantments.RESPIRATION, Execrations.HYPERSOMNIA)
	);
}
