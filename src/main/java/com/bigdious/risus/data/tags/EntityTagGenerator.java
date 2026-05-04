package com.bigdious.risus.data.tags;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
	public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, future, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

		tag(EntityTypeTags.ARTHROPOD)
			.add(RisusEntities.BABY_SPIDER.get())
			.add(RisusEntities.LICKER.get())
			.add(RisusEntities.WEAVER.get())
			.add(RisusEntities.LITTER.get())
		;

		tag(RisusTags.Entities.OFFSPRING)
			.add(RisusEntities.ANGEL.get())
			.add(RisusEntities.HOLDER.get())
			.add(RisusEntities.MAW.get())
			.add(RisusEntities.LOVER.get())
			.add(RisusEntities.WEAVER.get())
		;
		tag(RisusTags.Entities.BELOVED)
			.add(RisusEntities.LICKER.get(),
				RisusEntities.SINGER.get(),
				RisusEntities.HEX.get(),
				RisusEntities.STALKER.get())
		;
		tag(RisusTags.Entities.OFFSPRINGS_AND_BELOVEDS)
			.addTag(RisusTags.Entities.BELOVED)
			.addTag(RisusTags.Entities.OFFSPRING)
		;
		tag(RisusTags.Entities.CANT_BE_STOLEN_FROM)
			.add(RisusEntities.HOLDER.get())
			.add(EntityType.ARMOR_STAND)
		;
		tag(RisusTags.Entities.BREAKS_DEPTH_VASES)
			.add(RisusEntities.THROWN_AXE.get())
			.add(EntityType.TRIDENT)
		;
		tag(RisusTags.Entities.YOUTH_BANNED)
			.add(
				EntityType.TADPOLE,
				EntityType.FROG
			)
		;

		tag(RisusTags.Entities.HORN_BUFFS)
			.add(
				EntityType.IRON_GOLEM,
				EntityType.SNOW_GOLEM,
				EntityType.ALLAY,
				EntityType.HORSE,
				EntityType.SKELETON_HORSE,
				EntityType.ZOMBIE_HORSE,
				EntityType.MULE,
				EntityType.DONKEY,
				EntityType.LLAMA,
				EntityType.STRIDER,
				EntityType.CAMEL,
				EntityType.TRADER_LLAMA
			)
		;
		tag(RisusTags.Entities.HEXHORN_BANNED)
			.add(
				RisusEntities.GREAT_STOOL.get()

			)
		;
		tag(RisusTags.Entities.SENSITIVE_TO_HUNTERS)
			.addTag(EntityTypeTags.SENSITIVE_TO_SMITE)
			.addTag(EntityTypeTags.SENSITIVE_TO_IMPALING)
			.addTag(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)
		;

		tag(RisusTags.Entities.SMALL_MARITIME_SNARE_POOL)
			.add(
				EntityType.SALMON
				,EntityType.COD
				,EntityType.PUFFERFISH
				,EntityType.TROPICAL_FISH
				,EntityType.TADPOLE
			)
		;

		tag(RisusTags.Entities.MEDIUM_MARITIME_SNARE_POOL)
			.addTag(RisusTags.Entities.SMALL_MARITIME_SNARE_POOL)
			.add(
				EntityType.AXOLOTL
				,EntityType.DOLPHIN
				,EntityType.SQUID
				,EntityType.GLOW_SQUID
				,EntityType.DROWNED
				,EntityType.ARMOR_STAND
			)
		;

		tag(RisusTags.Entities.LARGE_MARITIME_SNARE_POOL)
			.addTag(RisusTags.Entities.MEDIUM_MARITIME_SNARE_POOL)
			.add(
				EntityType.GUARDIAN
				,EntityType.ELDER_GUARDIAN
				,EntityType.TURTLE
				,EntityType.BOAT
				,EntityType.TRIDENT
			)
		;

		tag(RisusTags.Entities.TRIDENT_LIKE_PROJECTILES)
			.add(
				EntityType.TRIDENT,
				RisusEntities.THROWN_AXE.get()
			)
		;

		tag(EntityTypeTags.FALL_DAMAGE_IMMUNE)
			.add(
				RisusEntities.HEX.get(),
				RisusEntities.ANGEL.get(),
				RisusEntities.QUESTION_MARK.get()
			)
		;

		tag(EntityTypeTags.IMMUNE_TO_INFESTED)
			.add(
				RisusEntities.BABY_SPIDER.get(),
				RisusEntities.WEAVER.get(),
				RisusEntities.HEX.get(),
				RisusEntities.ANGEL.get()
			)
		;

		tag(EntityTypeTags.IMMUNE_TO_OOZING)
			.add(
				RisusEntities.WEAVER.get(),
				RisusEntities.HEX.get(),
				RisusEntities.ANGEL.get()
			)
		;

		tag(EntityTypeTags.IMPACT_PROJECTILES)
			.add(
				RisusEntities.THROWN_AXE.get()
			)
		;

		tag(EntityTypeTags.REDIRECTABLE_PROJECTILE)
			.add(
				RisusEntities.EGG_SAC.get(),
				RisusEntities.BLOODSLASH.get()
			)
		;

		tag(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH)
			.add(
				RisusEntities.QUESTION_MARK.get()
			)
		;

		tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
			.add(
				RisusEntities.WEAVER.get()
			)
		;

		tag(EntityTypeTags.FROG_FOOD)
			.add(
				RisusEntities.BABY_SPIDER.get()
			)
		;

		tag(RisusTags.Entities.PEOPLE)
			.addTag(EntityTypeTags.ILLAGER)
			.add(
				EntityType.VILLAGER,
				EntityType.PLAYER
			)
		;


	}
}
