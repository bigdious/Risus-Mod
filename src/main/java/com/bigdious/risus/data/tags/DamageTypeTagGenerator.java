package com.bigdious.risus.data.tags;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusDamageTypes;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagGenerator extends DamageTypeTagsProvider {
	public DamageTypeTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
		super(packOutput, providerCompletableFuture, Risus.MODID, existingFileHelper);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.REVENGE,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.BYPASSES_INVULNERABILITY).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.REVENGE,
			RisusDamageTypes.DESTINED_DEATH,
			RisusDamageTypes.AGONY
		);
		this.tag(DamageTypeTags.NO_KNOCKBACK).add(
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.DESTINED_DEATH,
			RisusDamageTypes.AGONY
		);
		this.tag(DamageTypeTags.NO_IMPACT).add(
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.DESTINED_DEATH
		);
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH,
			RisusDamageTypes.REVENGE
		);
		this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH,
			RisusDamageTypes.REVENGE
		);
		this.tag(DamageTypeTags.BYPASSES_SHIELD).add(
			RisusDamageTypes.INEXISTENCE,
			RisusDamageTypes.VAMPIRISM,
			RisusDamageTypes.PLEASURE,
			RisusDamageTypes.DESTINED_DEATH,
			RisusDamageTypes.REVENGE
		);

		this.tag(RisusTags.DamageTypes.ARMOR_PIERCING)
			.addTag(DamageTypeTags.BYPASSES_ARMOR)
			.remove(
				DamageTypes.FALL,
				DamageTypes.ON_FIRE,
				DamageTypes.FREEZE,
				DamageTypes.MAGIC,
				DamageTypes.INDIRECT_MAGIC,
				DamageTypes.SONIC_BOOM
			);

	}
}
