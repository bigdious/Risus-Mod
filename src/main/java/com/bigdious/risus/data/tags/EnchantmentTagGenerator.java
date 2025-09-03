package com.bigdious.risus.data.tags;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.Execrations;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EnchantmentTagGenerator extends EnchantmentTagsProvider {
	public EnchantmentTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(RisusTags.Enchantments.THOUSAND_BLADE_ALLOWED_ENCHANTS).add(
			Enchantments.SHARPNESS,
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.SWEEPING_EDGE,
			Enchantments.LOOTING,
			Enchantments.SMITE,
			Enchantments.MENDING,
			Enchantments.UNBREAKING,
			Enchantments.KNOCKBACK,
			Enchantments.POWER,
			Enchantments.PIERCING,
			Enchantments.MULTISHOT,
			Enchantments.VANISHING_CURSE);

		this.tag(RisusTags.Enchantments.CRESCENT_DISASTER_ALLOWED_ENCHANTS).add(
			Enchantments.LOYALTY,
			Enchantments.SHARPNESS,
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.SMITE,
			Enchantments.MENDING,
			Enchantments.UNBREAKING,
			Enchantments.FIRE_ASPECT,
			Enchantments.LOOTING,
			Enchantments.KNOCKBACK,
			Enchantments.VANISHING_CURSE
		);

		this.tag(RisusTags.Enchantments.SCYTHE_ALLOWED_ENCHANTS).add(
			Enchantments.SHARPNESS,
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.LOOTING,
			Enchantments.SMITE,
			Enchantments.MENDING,
			Enchantments.UNBREAKING,
			Enchantments.KNOCKBACK,
			Enchantments.VANISHING_CURSE);

		this.tag(RisusTags.Enchantments.SOUL_SCYTHE_ALLOWED_ENCHANTS).add(
			Enchantments.SHARPNESS,
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.LOOTING,
			Enchantments.MENDING,
			Enchantments.UNBREAKING,
			Enchantments.KNOCKBACK,
			Enchantments.VANISHING_CURSE);

		this.tag(RisusTags.Enchantments.FIRE_SCYTHE_ALLOWED_ENCHANTS).add(
			Enchantments.SHARPNESS,
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.LOOTING,
			Enchantments.SMITE,
			Enchantments.MENDING,
			Enchantments.UNBREAKING,
			Enchantments.FIRE_ASPECT,
			Enchantments.KNOCKBACK,
			Enchantments.VANISHING_CURSE);

		this.tag(RisusTags.Enchantments.CINDERGLEE_SCYTHE_ALLOWED_ENCHANTS).add(
			Enchantments.SHARPNESS,
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.LOOTING,
			Enchantments.SMITE,
			Enchantments.MENDING,
			Enchantments.UNBREAKING,
			Enchantments.KNOCKBACK,
			Enchantments.VANISHING_CURSE);

		this.tag(RisusTags.Enchantments.TOOTHKNOCKER_ALLOWED_ENCHANTS).add(
			Enchantments.SHARPNESS,
				Enchantments.BANE_OF_ARTHROPODS,
				Enchantments.SMITE,
				Enchantments.MENDING,
				Enchantments.UNBREAKING,
				Enchantments.FIRE_ASPECT,
				Enchantments.KNOCKBACK,
				Enchantments.LOOTING,
				Enchantments.VANISHING_CURSE);

		this.tag(RisusTags.Enchantments.BOOMSTICK_ALLOWED_ENCHANTS).add(
			Enchantments.UNBREAKING,
			Enchantments.POWER,
			Enchantments.WIND_BURST,
			Enchantments.MENDING,
			Enchantments.MULTISHOT,
			Enchantments.FLAME
		);

		this.tag(RisusTags.Enchantments.WARHORN_ALLOWED_ENCHANTS).add(
			Enchantments.UNBREAKING,
			Enchantments.POWER,
			Enchantments.MENDING,
			Enchantments.QUICK_CHARGE
		);

		this.tag(RisusTags.Enchantments.HEXHORN_ALLOWED_ENCHANTS).add(
			Enchantments.UNBREAKING,
			Enchantments.POWER,
			Enchantments.MENDING,
			Enchantments.QUICK_CHARGE,
			Enchantments.FLAME
		);

		this.tag(RisusTags.Enchantments.EXECRATIONS).add(
			Execrations.HUNTERS_EXULTATION,
			Execrations.ELEMENTAL_DEVIATION,
			Execrations.DREAM_EATER,
			Execrations.PULL,
			Execrations.DENIAL,
			Execrations.CACKLING_CRAZE,
			Execrations.AGONY,
			Execrations.PERPETUITY,
			Execrations.GENOCIDE,
			Execrations.EMPYREAN_CONDUIT
		);

	}
}
