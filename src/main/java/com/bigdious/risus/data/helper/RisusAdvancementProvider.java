package com.bigdious.risus.data.helper;


import com.bigdious.risus.data.RisusAdvancementGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RisusAdvancementProvider extends AdvancementProvider {
	public RisusAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
		super(output, registries, existingFileHelper, List.of(new RisusAdvancementGenerator()));
	}
}
