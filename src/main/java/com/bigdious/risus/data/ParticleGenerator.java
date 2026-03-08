package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

import java.util.ArrayList;

public class ParticleGenerator extends ParticleDescriptionProvider {

	public ParticleGenerator(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, fileHelper);
	}

	@Override
	protected void addDescriptions() {
		this.spriteSet(RisusParticles.ALTERATION.get(),
			ResourceLocation.withDefaultNamespace("sga_a"),
			ResourceLocation.withDefaultNamespace("sga_e"),
			ResourceLocation.withDefaultNamespace("sga_g"),
			ResourceLocation.withDefaultNamespace("sga_h"),
			ResourceLocation.withDefaultNamespace("sga_l"),
			ResourceLocation.withDefaultNamespace("sga_r"),
			ResourceLocation.withDefaultNamespace("sga_t"),
			ResourceLocation.withDefaultNamespace("sga_u"));

		this.spriteSet(RisusParticles.ALTERATION_FINISHED.get(),
			ResourceLocation.withDefaultNamespace("sga_a"),
			ResourceLocation.withDefaultNamespace("sga_e"),
			ResourceLocation.withDefaultNamespace("sga_g"),
			ResourceLocation.withDefaultNamespace("sga_h"),
			ResourceLocation.withDefaultNamespace("sga_l"),
			ResourceLocation.withDefaultNamespace("sga_r"),
			ResourceLocation.withDefaultNamespace("sga_t"),
			ResourceLocation.withDefaultNamespace("sga_u"));

		this.sprite(RisusParticles.BLOCK_ORGANIC_PARTICLE.get(), Risus.prefix("block_organic"));
		this.spriteSet(RisusParticles.BLOODSLASH_TRAIL.get(), Risus.prefix("bloodslash_trail"), 7, false);
		this.specialTexture(RisusParticles.BLOOD.get());
		this.specialTexture(RisusParticles.BLOOD_BIT.get());
		this.specialTexture(RisusParticles.MOB_EFFECT_ICON.get());
		this.sprite(RisusParticles.DESTINED_DEATH_PARTICLE.get(), Risus.prefix("destined_death"));
		this.sprite(RisusParticles.DRIPPING_BLOOD.get(), Risus.prefix("blood_hang"));
		this.sprite(RisusParticles.DRIPPING_JOY.get(), Risus.prefix("joy_hang"));
		this.sprite(RisusParticles.DRIPPING_CREAM.get(), Risus.prefix("cream_hang"));
		this.sprite(RisusParticles.FALLING_BLOOD.get(), Risus.prefix("blood_fall"));
		this.sprite(RisusParticles.FALLING_JOY.get(), Risus.prefix("joy_fall"));
		this.sprite(RisusParticles.FALLING_CREAM.get(), Risus.prefix("cream_fall"));
		this.sprite(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), Risus.prefix("fiery_organic"));
		this.sprite(RisusParticles.JOYFLAME.get(), Risus.prefix("joyflame"));
		this.sprite(RisusParticles.LANDING_BLOOD.get(), Risus.prefix("blood_land"));
		this.sprite(RisusParticles.LANDING_JOY.get(), Risus.prefix("joy_land"));
		this.sprite(RisusParticles.LANDING_CREAM.get(), Risus.prefix("cream_land"));
		this.sprite(RisusParticles.RISING_SMILE.get(), Risus.prefix("rising_smile"));
		this.sprite(RisusParticles.SLEEPY.get(), Risus.prefix("sleepy"));
		this.sprite(RisusParticles.BLOOD_FEATHER.get(), Risus.prefix("blood_feather"));
		this.sprite(RisusParticles.STARS.get(), Risus.prefix("stars"));
		this.spriteSet(RisusParticles.RISUS_SOUL_PARTICLE.get(), Risus.prefix("risus_soul"), 11, false);
		this.sprite(RisusParticles.TOOTHICAL.get(), Risus.prefix("toothical"));
	}

	private void specialTexture(ParticleType<?> type) {
		this.descriptions.put(BuiltInRegistries.PARTICLE_TYPE.getKey(type), new ArrayList<>());
	}
}
