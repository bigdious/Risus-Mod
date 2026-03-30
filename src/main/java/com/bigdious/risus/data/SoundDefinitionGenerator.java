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

public class SoundDefinitionGenerator extends SoundDefinitionsProvider {
	public SoundDefinitionGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, Risus.MODID, helper);
	}

	@Override
	public void registerSounds() {
		this.add(RisusSoundEvents.MUSIC_DISC_RAK, definition().with(sound("risus:music/disc/rak").volume(2).stream()));
		this.add(RisusSoundEvents.MUSIC_DISC_MORK, definition().with(sound("risus:music/disc/mork").volume(2).stream()));
		this.add(RisusSoundEvents.MUSIC_DISC_FEIGR, definition().with(sound("risus:music/disc/feigr").volume(2).stream()));
		this.add(RisusSoundEvents.MUSIC_DISC_REGN, definition().with(sound("risus:music/disc/regn").volume(2).stream()));
		this.add(RisusSoundEvents.MUSIC_DISC_CYCLE, definition().with(sound("risus:music/disc/cycle").volume(2).stream()));

		this.generateNewSoundWithSubtitle(RisusSoundEvents.CHEEKY_LAUGH, "entity/holder/cheeky_laugh", 1, "Holder laughs cheekily");
		this.generateNewSoundWithSubtitle(RisusSoundEvents.TOLLING_BELL, "entity/ophanim/tolling_bell", 1, "Heaven's Gaze sharpens");
		this.generateNewSoundWithSubtitle(RisusSoundEvents.GORGER_BITE, "entity/gorger/bite", 1, "Gorger crushes");

		this.generateNewSoundWithSubtitle(RisusSoundEvents.LAUGHTER_ECHOES, "environment/laughter_echoes", 9, "Laughter echoes");

		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ALTERATION_FAILED, SoundEvents.REDSTONE_TORCH_BURNOUT, "Alteration failed");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_SKIN, SoundEvents.ARMOR_EQUIP_LEATHER.value(), "Skin armor rustles");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_THREADS, SoundEvents.ARMOR_EQUIP_LEATHER.value(), "Feathers rustle");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_ROSE, SoundEvents.ARMOR_EQUIP_LEATHER.value(), "Roses flutter");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_ROBE, SoundEvents.ARMOR_EQUIP_LEATHER.value(), "Robe folds");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_BONE, SoundEvents.ARMOR_EQUIP_CHAIN.value(), "Bones rattle");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ARMOR_EQUIP_WINGS, SoundEvents.ARMOR_EQUIP_ELYTRA.value(), "Ophanim Wings rustle");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.BLOOD_AMBIENT, SoundEvents.WATER_AMBIENT, "Blood courses");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.BLOOD_SLASH_WHOOSH, SoundEvents.BREEZE_WHIRL, "Blood slash whooshes");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.CONCENTRATION_CORE_LITTER, SoundEvents.ZOMBIE_VILLAGER_CONVERTED, "Light block gets inhabited");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.CRESCENT_DISASTER_HIT, SoundEvents.TRIDENT_HIT, "Crescent Disaster stabs");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.CRESCENT_DISASTER_HIT_GROUND, SoundEvents.TRIDENT_HIT_GROUND, "Crescent Disaster vibrates");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.CRESCENT_DISASTER_RETURN, SoundEvents.TRIDENT_RETURN, "Crescent Disaster returns");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.CRESCENT_DISASTER_THROW, SoundEvents.TRIDENT_THROW.value(), "Crescent Disaster flies");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.CRYSTALLIZED_BOND_GROW, SoundEvents.CHICKEN_EGG, "Crystallized Bond grows");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.DARKNESS_PLACE, SoundEvents.WARDEN_NEARBY_CLOSE, "Darkness is born");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.DEPTH_VASE_INSERT, SoundEvents.DECORATED_POT_INSERT, "Depth Vase fills");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.DEPTH_VASE_INSERT_FAIL, SoundEvents.DECORATED_POT_INSERT_FAIL, "Depth Vase wobbles");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.EGG_SAC_BREAK, SoundEvents.TURTLE_EGG_HATCH, "Baby Spiders are unleashed");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.EGG_SAC_THROW, SoundEvents.SNOWBALL_THROW, "Baby Spiders learn aerodynamics");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ETERNAL_YOUTH_BREAK, SoundEvents.TURTLE_EGG_BREAK, "Eternal Youth releases");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.FORTUNE_TRIGGERED, SoundEvents.AMETHYST_BLOCK_RESONATE, "Fortune triggered");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.HAIR_GROW, SoundEvents.SCULK_BLOCK_SPREAD, "Hair grows");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.HEX_AMBIENT, SoundEvents.VEX_AMBIENT, "Hex hexes");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.HEX_CHARGE, SoundEvents.VEX_CHARGE, "Hex charges");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.HEX_DEATH, SoundEvents.VEX_DEATH, "Hex dies");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.HEX_HURT, SoundEvents.VEX_HURT, "Hex hurts");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.LITTER_LAY_EXCREMENT, SoundEvents.CHICKEN_EGG, "Litter excretes");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.LOVER_INFECT, SoundEvents.ZOMBIE_INFECT, "Lover impregnates");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.MAW_GUTS_SHATTER, SoundEvents.WITHER_BREAK_BLOCK, "Gorger Guts break");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.MISFORTUNE_TRIGGERED, SoundEvents.WITHER_BREAK_BLOCK, "Misfortune triggered");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.IMBIBING, SoundEvents.BREWING_STAND_BREW, "Horn imbibed");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ORGANIC_MATTER_USE, SoundEvents.SCULK_VEIN_BREAK, "Organic Matter crinkles");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.SINGER_SCREAM, SoundEvents.ENDERMAN_SCREAM, "Singer serenades");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.STRIPPER_STRIP, SoundEvents.ARROW_HIT, "Debt is claimed");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.THOUSAND_BLADE_SLASH, SoundEvents.PLAYER_ATTACK_WEAK, "Blade of a Thousand slashes");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.TOOTHKNOCKER_CRACK, SoundEvents.TURTLE_EGG_BREAK, "Teeth fall out");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.TOOTHKNOCKER_DASH, SoundEvents.GOAT_SCREAMING_LONG_JUMP, "Player lunges");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ZIT_POP, SoundEvents.LLAMA_SPIT, "Zit pops");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.AIR_SUCKED_IN, SoundEvents.BREEZE_INHALE, "Air gets sucked");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ERUPT, SoundEvents.DRAGON_FIREBALL_EXPLODE, "Someone erupts");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ITEM_POPS_OUT, SoundEvents.ITEM_FRAME_REMOVE_ITEM, "Item pops out");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.WEAVING, SoundEvents.SPIDER_STEP, "Weaving");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.WING_STAB, SoundEvents.PLAYER_ATTACK_KNOCKBACK, "Wings stab");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ITEM_INSERT, SoundEvents.ITEM_FRAME_ADD_ITEM, "Item is inserted");
		this.generateExistingSoundWithSubtitle(RisusSoundEvents.ITEM_REMOVED, SoundEvents.CHISELED_BOOKSHELF_PICKUP, "Item is removed");
		this.generateNewSoundWithSubtitle(RisusSoundEvents.HEARTBEAT, "block/beating_heart/heartbeat", 1,"Heart beats");
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
		String subtitleKey = "subtitles.risus." + event.getId().getPath();
		definition.subtitle(subtitleKey);
		LangGenerator.SUBTITLE_GENERATOR.put(subtitleKey, subtitle);
	}
}
