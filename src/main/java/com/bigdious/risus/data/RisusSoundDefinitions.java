package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

public class RisusSoundDefinitions extends SoundDefinitionsProvider {
	public RisusSoundDefinitions(PackOutput output, ExistingFileHelper helper) {
		super(output, Risus.MODID, helper);
	}

	@Override
	public void registerSounds() {
		// vanilla shit
//		this.add(RisusSoundEvents.LUNGE, definition().with(
//			sound("minecraft:entity.goat.screaming.long_jump").stream()
//		));

		this.add(RisusSoundEvents.MUSIC_DISC_RAK, definition().with(sound("risus:music/disc/rak").volume(2).stream()));
		this.add(RisusSoundEvents.MUSIC_DISC_MORK, definition().with(sound("risus:music/disc/mork").volume(2).stream()));
		this.add(RisusSoundEvents.MUSIC_DISC_FEIGR, definition().with(sound("risus:music/disc/feigr").volume(2).stream()));
		this.add(RisusSoundEvents.MUSIC_DISC_REGN, definition().with(sound("risus:music/disc/regn").volume(2).stream()));
		this.add(RisusSoundEvents.AMBIENT_MORK, definition().with(sound("risus:music/ambient/ambient_mork").stream()));
		this.add(RisusSoundEvents.AMBIENT_FEIGR, definition().with(sound("risus:music/ambient/ambient_feigr").stream()));

		this.generateNewSoundWithSubtitle(RisusSoundEvents.CHEEKY_LAUGH, "entity/holder/cheeky_laugh", 1, "Holder laughs");

		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_SKIN, SoundEvents.ARMOR_EQUIP_LEATHER.value(), "Skin armor rustles");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_WINGS, SoundEvents.ARMOR_EQUIP_ELYTRA.value(), "Angel Wings rustle");
	}

	public void generateNewSoundWithSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds, String subtitle) {
		generateNewSound(event, baseSoundDirectory, numberOfSounds, subtitle);
	}

	public void generateNewSound(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds, @Nullable String subtitle) {
		SoundDefinition definition = SoundDefinition.definition();
		if (subtitle != null) {
			this.createSubtitleAndLangEntry(event, definition, subtitle);
		}
		for (int i = 1; i <= numberOfSounds; i++) {
			definition.with(SoundDefinition.Sound.sound(Risus.prefix(baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
		}
		this.add(event, definition);
	}

	public void generateNewSoundMC(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds, @Nullable String subtitle) {
		SoundDefinition definition = SoundDefinition.definition();
		if (subtitle != null) {
			this.createSubtitleAndLangEntry(event, definition, subtitle);
		}
		for (int i = 1; i <= numberOfSounds; i++) {
			definition.with(SoundDefinition.Sound.sound(ResourceLocation.withDefaultNamespace(baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
		}
		this.add(event, definition);
	}

	public void generateExistingSoundWithSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, String subtitle) {
		this.generateExistingSoundWithSubtitle(event, referencedSound, subtitle, 1.0F, 1.0F);
	}

	public void generateExistingSoundWithSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, String subtitle, float volume, float pitch) {
		this.generateExistingSound(event, referencedSound, subtitle, volume, pitch);
	}

	public void generateSoundWithExistingSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, String subtitle) {
		this.add(event, SoundDefinition.definition()
			.subtitle(subtitle)
			.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
	}

	public void generateExistingSound(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, @Nullable String subtitle, float volume, float pitch) {
		SoundDefinition definition = SoundDefinition.definition();
		if (subtitle != null) {
			this.createSubtitleAndLangEntry(event, definition, subtitle);
		}
		this.add(event, definition
			.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT).volume(volume).pitch(pitch)));
	}

	public void makeStepSound(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound) {
		this.add(event, SoundDefinition.definition()
			.subtitle("subtitles.block.generic.footsteps")
			.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
	}

	public void makeNewStepSound(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds) {
		SoundDefinition definition = SoundDefinition.definition();
		for (int i = 1; i <= numberOfSounds; i++) {
			definition.with(SoundDefinition.Sound.sound(Risus.prefix(baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
		}
		this.add(event, definition.subtitle("subtitles.block.generic.footsteps"));
	}

	public void makeNewGenericSound(DeferredHolder<SoundEvent, SoundEvent> event, String baseSoundDirectory, int numberOfSounds, @Nullable String type) {
		SoundDefinition definition = SoundDefinition.definition();
		for (int i = 1; i <= numberOfSounds; i++) {
			definition.with(SoundDefinition.Sound.sound(Risus.prefix(baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
		}
		this.add(event, type != null ? definition.subtitle("subtitles.block.generic." + type) : definition);
	}

	private void createSubtitleAndLangEntry(DeferredHolder<SoundEvent, SoundEvent> event, SoundDefinition definition, String subtitle) {
		String[] splitSoundName = event.getId().getPath().split("\\.", 3);
		String subtitleKey = "subtitles.risus." + splitSoundName[0] + "." + splitSoundName[2];
		definition.subtitle(subtitleKey);
		//TODO Gizmo: hook up subtitle generation to lang file
		//LangGenerator.SUBTITLE_GENERATOR.put(subtitleKey, subtitle);
	}
}
