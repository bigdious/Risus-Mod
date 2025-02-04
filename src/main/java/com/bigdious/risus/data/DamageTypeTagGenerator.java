package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagGenerator extends DamageTypeTagsProvider {
	public DamageTypeTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
		super(packOutput, providerCompletableFuture, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.BYPASSES_INVULNERABILITY).add(
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.NO_KNOCKBACK).add(
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.NO_IMPACT).add(
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.BYPASSES_SHIELD).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH
		);
	}
}
