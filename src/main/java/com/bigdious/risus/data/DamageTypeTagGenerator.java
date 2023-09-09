package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagGenerator extends DamageTypeTagsProvider {
	public DamageTypeTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
		super(packOutput, providerCompletableFuture, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(RisusDamageTypes.INEXISTENCE, RisusDamageTypes.GLUTTONY);
	}
}
