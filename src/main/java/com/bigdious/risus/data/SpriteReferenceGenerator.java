package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class SpriteReferenceGenerator extends SpriteSourceProvider {
	public SpriteReferenceGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Risus.MODID, helper);
	}

	@Override
	protected void gather() {
		this.atlas(BLOCKS_ATLAS)
				.addSource(new SingleFile(Risus.prefix("entity/crescent_disaster"), Optional.empty()))
				.addSource(new SingleFile(Risus.prefix("entity/unawakened_vessel"), Optional.empty()));
	}
}
