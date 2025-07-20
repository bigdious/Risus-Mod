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
			.add(RisusEntities.LICKER.get())
			.add(RisusEntities.SINGER.get())
			.add(RisusEntities.STALKER.get())
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
				EntityType.ENDER_DRAGON,
				EntityType.TADPOLE,
				RisusEntities.QUESTION_MARK.get(),
				RisusEntities.MEMORY1.get()
			)

		;
		tag(RisusTags.Entities.YOUTH_SHRINKS)
			.add(
				EntityType.PARROT,
				EntityType.ALLAY,
				EntityType.BAT,
				EntityType.COD,
				EntityType.DOLPHIN,
				EntityType.GLOW_SQUID,
				EntityType.SQUID,
				EntityType.PUFFERFISH,
				EntityType.TROPICAL_FISH,
				EntityType.SALMON
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
	}
}
