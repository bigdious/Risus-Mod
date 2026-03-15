package com.bigdious.risus.data.tags;

import com.bigdious.risus.worldgen.structures.RisusStructures;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;

import java.util.concurrent.CompletableFuture;

public class StructureTagGenerator extends StructureTagsProvider {
	public StructureTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(RisusTags.Structures.RISUS).add(
			RisusStructures.ALTERATION_SITE
			,RisusStructures.GRASSY_SITE
			,RisusStructures.BURRIED_SITE
			,RisusStructures.BEDROCK_HAND
			,RisusStructures.CHURCH
			,RisusStructures.ANGEL_ALTAR
			,RisusStructures.BLOOD_WELL
			,RisusStructures.FAMILY_TREE
			,RisusStructures.ENDY_MAW
//			,RisusStructures.GRASSY_MAW
			,RisusStructures.SANDY_MAW
			,RisusStructures.HEART_CHAMBER
			,RisusStructures.GREAT_BODY
			,RisusStructures.DRAXOLOTL_REMAINS
			,RisusStructures.FLOWER_FIELD
			,RisusStructures.LAB_START
			,RisusStructures.RIBS_FOSSIL
			,RisusStructures.SKULL_FOSSIL
		);

		this.tag(RisusTags.Structures.MORK_MUSIC_STRUCTURES).add(
			RisusStructures.FAMILY_TREE
		);

		this.tag(RisusTags.Structures.FEIGR_MUSIC_STRUCTURES).add(
			RisusStructures.LAB_START
		);

		this.tag(RisusTags.Structures.ANGEL_ALTAR).add(
			RisusStructures.ANGEL_ALTAR
		);

		this.tag(RisusTags.Structures.HEART_CHAMBER).add(
			RisusStructures.HEART_CHAMBER
		);

		this.tag(RisusTags.Structures.RIBS_FOSSIL).add(
			RisusStructures.RIBS_FOSSIL
		);

		this.tag(RisusTags.Structures.SKULL_FOSSIL).add(
			RisusStructures.SKULL_FOSSIL
		);

		this.tag(RisusTags.Structures.GREAT_BODY).add(
			RisusStructures.GREAT_BODY
		);

		this.tag(RisusTags.Structures.CHURCH).add(
			RisusStructures.CHURCH
		);

		this.tag(RisusTags.Structures.BLOOD_POOL).add(
			RisusStructures.LAB_START
		);

		this.tag(RisusTags.Structures.FLOWER_FIELD).add(
			RisusStructures.FLOWER_FIELD
		);
	}

}
