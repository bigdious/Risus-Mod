package com.bigdious.risus.data.tags;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.Execrations;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
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
			Execrations.EMPYREAN_CONDUIT,
			Execrations.BATTERING,
			Execrations.STAR_RELEASE,
			Execrations.OVERLOAD,
			Execrations.MARITIME_SNARE,
			Execrations.GRAVITY_WELL,
			Execrations.RELOCATION,
			Execrations.PYROMANIAC,
			Execrations.AVARICIOUS_AMBIT,
			Execrations.PRESERVATION,
			Execrations.VIGOR,
			Execrations.XENOPHOBIA,
			Execrations.DEFIANCE,
			Execrations.ERUPTION,
			Execrations.SOAR,
			Execrations.FERVOUR,
			Execrations.PROLIFERATION
		);

		this.tag(EnchantmentTags.CROSSBOW_EXCLUSIVE).add(
			Execrations.BATTERING,
			Execrations.STAR_RELEASE
		);

		this.tag(EnchantmentTags.DAMAGE_EXCLUSIVE).add(
			Execrations.HUNTERS_EXULTATION,
			Execrations.XENOPHOBIA
		);

		this.tag(EnchantmentTags.ARMOR_EXCLUSIVE).add(
			Execrations.ELEMENTAL_DEVIATION,
			Execrations.VIGOR
		);

		this.tag(EnchantmentTags.BOOTS_EXCLUSIVE).add(
			Execrations.PYROMANIAC,
			Execrations.PROLIFERATION
		);

		this.tag(EnchantmentTags.MINING_EXCLUSIVE).add(
			Execrations.AVARICIOUS_AMBIT
		);

		this.tag(EnchantmentTags.BOW_EXCLUSIVE).add(
			Execrations.PRESERVATION
		);

		this.tag(EnchantmentTags.RIPTIDE_EXCLUSIVE).add(
			Execrations.ERUPTION
		);

		this.tag(RisusTags.Enchantments.ALTERABLE_ENCHANTS).add(
			Enchantments.SMITE
			,Enchantments.BANE_OF_ARTHROPODS
			,Enchantments.IMPALING
			,Enchantments.FIRE_PROTECTION
			,Enchantments.PROJECTILE_PROTECTION
			,Enchantments.BLAST_PROTECTION
			,Enchantments.MENDING
			,Enchantments.PUNCH
			,Enchantments.VANISHING_CURSE
			,Enchantments.SOUL_SPEED
			,Enchantments.THORNS
			,Enchantments.BINDING_CURSE
			,Enchantments.SWEEPING_EDGE
			,Enchantments.CHANNELING
			,Enchantments.PIERCING
			,Enchantments.MULTISHOT
			,Enchantments.EFFICIENCY
			,Enchantments.LUCK_OF_THE_SEA
			,Enchantments.LURE
			,Enchantments.UNBREAKING
			,Enchantments.DEPTH_STRIDER
			,Enchantments.SILK_TOUCH
			,Enchantments.INFINITY
			,Enchantments.PROTECTION
			,Enchantments.SHARPNESS
			,Enchantments.LOYALTY
			,Enchantments.RIPTIDE
			,Enchantments.KNOCKBACK
			,Enchantments.FIRE_ASPECT
			,Enchantments.FROST_WALKER
		);



	}
}
