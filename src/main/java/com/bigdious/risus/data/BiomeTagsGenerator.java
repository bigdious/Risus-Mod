package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BiomeTagsGenerator extends BiomeTagsProvider {

	public BiomeTagsGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(pOutput, pProvider, Risus.MODID, existingFileHelper);
	}
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(RisusTags.Biomes.HAS_ALTERATION_SITE).add(
			Biomes.BADLANDS,
			Biomes.BAMBOO_JUNGLE,
			Biomes.BIRCH_FOREST,
			Biomes.CHERRY_GROVE,
			Biomes.DARK_FOREST,
			Biomes.DESERT,
			Biomes.ERODED_BADLANDS,
			Biomes.FOREST,
			Biomes.GROVE,
			Biomes.ICE_SPIKES,
			Biomes.JUNGLE,
			Biomes.MANGROVE_SWAMP,
			Biomes.MEADOW,
			Biomes.MUSHROOM_FIELDS,
			Biomes.OLD_GROWTH_BIRCH_FOREST,
			Biomes.OLD_GROWTH_PINE_TAIGA,
			Biomes.OLD_GROWTH_SPRUCE_TAIGA,
			Biomes.PLAINS,
			Biomes.SAVANNA,
			Biomes.SAVANNA_PLATEAU,
			Biomes.SNOWY_PLAINS,
			Biomes.SNOWY_SLOPES,
			Biomes.SNOWY_TAIGA,
			Biomes.SPARSE_JUNGLE,
			Biomes.SUNFLOWER_PLAINS,
			Biomes.SWAMP,
			Biomes.TAIGA,
			Biomes.WINDSWEPT_FOREST,
			Biomes.WINDSWEPT_GRAVELLY_HILLS,
			Biomes.WINDSWEPT_HILLS,
			Biomes.WINDSWEPT_SAVANNA,
			Biomes.WOODED_BADLANDS
			);

		tag(RisusTags.Biomes.HAS_FAMILY_TREE).add(
			Biomes.BADLANDS,
			Biomes.BAMBOO_JUNGLE,
			Biomes.BIRCH_FOREST,
			Biomes.CHERRY_GROVE,
			Biomes.DARK_FOREST,
			Biomes.DESERT,
			Biomes.ERODED_BADLANDS,
			Biomes.FOREST,
			Biomes.GROVE,
			Biomes.ICE_SPIKES,
			Biomes.JUNGLE,
			Biomes.MANGROVE_SWAMP,
			Biomes.MEADOW,
			Biomes.MUSHROOM_FIELDS,
			Biomes.OLD_GROWTH_BIRCH_FOREST,
			Biomes.OLD_GROWTH_PINE_TAIGA,
			Biomes.OLD_GROWTH_SPRUCE_TAIGA,
			Biomes.PLAINS,
			Biomes.SAVANNA,
			Biomes.SAVANNA_PLATEAU,
			Biomes.SNOWY_PLAINS,
			Biomes.SNOWY_SLOPES,
			Biomes.SNOWY_TAIGA,
			Biomes.SPARSE_JUNGLE,
			Biomes.SUNFLOWER_PLAINS,
			Biomes.SWAMP,
			Biomes.TAIGA,
			Biomes.WINDSWEPT_FOREST,
			Biomes.WINDSWEPT_GRAVELLY_HILLS,
			Biomes.WINDSWEPT_HILLS,
			Biomes.WINDSWEPT_SAVANNA,
			Biomes.WOODED_BADLANDS
		);

		tag(RisusTags.Biomes.HAS_GRASSY_MAW).add(
			Biomes.BAMBOO_JUNGLE,
			Biomes.BIRCH_FOREST,
			Biomes.CHERRY_GROVE,
			Biomes.DARK_FOREST,
			Biomes.FOREST,
			Biomes.GROVE,
			Biomes.JUNGLE,
			Biomes.MEADOW,
			Biomes.OLD_GROWTH_BIRCH_FOREST,
			Biomes.OLD_GROWTH_PINE_TAIGA,
			Biomes.OLD_GROWTH_SPRUCE_TAIGA,
			Biomes.PLAINS,
			Biomes.SAVANNA,
			Biomes.SAVANNA_PLATEAU,
			Biomes.SPARSE_JUNGLE,
			Biomes.SUNFLOWER_PLAINS,
			Biomes.SWAMP,
			Biomes.TAIGA,
			Biomes.WINDSWEPT_FOREST,
			Biomes.WINDSWEPT_GRAVELLY_HILLS,
			Biomes.WINDSWEPT_HILLS,
			Biomes.WINDSWEPT_SAVANNA
		);

		tag(RisusTags.Biomes.HAS_SANDY_MAW).add(
			Biomes.DESERT
		);

		tag(RisusTags.Biomes.HAS_ENDY_MAW).add(
			Biomes.END_BARRENS,
			Biomes.END_MIDLANDS,
			Biomes.END_HIGHLANDS
		);

		tag(RisusTags.Biomes.HAS_ANGEL_ALTAR).add(
			Biomes.FROZEN_PEAKS,
			Biomes.JAGGED_PEAKS,
			Biomes.SAVANNA_PLATEAU,
			Biomes.SNOWY_SLOPES,
			Biomes.STONY_PEAKS
		);

		tag(RisusTags.Biomes.HAS_FLOWER_FIELD).add(
			Biomes.FLOWER_FOREST
		);
	}
}
