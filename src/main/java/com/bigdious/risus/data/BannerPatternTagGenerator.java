package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBannerPatterns;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BannerPatternTagGenerator extends TagsProvider<BannerPattern> {


	public BannerPatternTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, Registries.BANNER_PATTERN, provider, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

		this.tag(RisusTags.BannerPatterns.SMILE_PATTERN).add(RisusBannerPatterns.SMILE);
		this.tag(RisusTags.BannerPatterns.DIVINITY_PATTERN).add(RisusBannerPatterns.DIVINITY);
		this.tag(RisusTags.BannerPatterns.TREE_PATTERN).add(RisusBannerPatterns.TREE);
	}
}
