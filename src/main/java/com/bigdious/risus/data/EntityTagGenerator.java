package com.bigdious.risus.data;

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
		;

		tag(RisusTags.Entities.OFFSPRING)
			.add(RisusEntities.ANGEL.get())
			.add(RisusEntities.HOLDER.get())
			.add(RisusEntities.MAW.get())
			.add(RisusEntities.LOVER.get())
			.add(RisusEntities.WEAVER.get())
		;
		tag(RisusTags.Entities.LOVEABLE)
			.add(EntityType.SPIDER)
			.add(EntityType.CREEPER)
			.add(EntityType.ENDERMAN)

		;
		tag(RisusTags.Entities.BELOVED)
			.add(RisusEntities.LICKER.get())
			.add(RisusEntities.SINGER.get())
			.add(RisusEntities.STALKER.get())
			.add(RisusEntities.BABY_SPIDER.get())
		;
	}
}
