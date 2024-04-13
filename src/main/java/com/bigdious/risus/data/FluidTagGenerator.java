package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class FluidTagGenerator extends FluidTagsProvider {

	public FluidTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, future, Risus.MODID, existingFileHelper);
	}

	@Override
	public String getName() {
		return "Risus Fluid Tags";
	}

	public static final TagKey<Fluid> BLOODFLUID = tag("bloodfluid");

	private static TagKey<Fluid> tag(String name) {
		return FluidTags.create(new ResourceLocation(Risus.MODID, name));
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BLOODFLUID).add(RisusFluids.SOURCE_BLOOD.get(), RisusFluids.FLOWING_BLOOD.get());
	}
}
