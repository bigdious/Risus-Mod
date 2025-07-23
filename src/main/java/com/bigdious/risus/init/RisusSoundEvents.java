package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Risus.MODID);

	//items

	public static final DeferredHolder<SoundEvent, SoundEvent> SQUIRT = register("item.bloodwyrm_head.squirt");

	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_RAK = register("music.disc.rak");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_MORK = register("music.disc.mork");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_FEIGR = register("music.disc.feigr");
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_REGN = register("music.disc.regn");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_MORK = register("music.ambient.mork");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_FEIGR = register("music.ambient.feigr");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHEEKY_LAUGH = register("entity.holder.cheeky_laugh");
	public static final DeferredHolder<SoundEvent, SoundEvent> TOLLING_BELL = register("entity.ophanim.tolling_bell");
	public static final DeferredHolder<SoundEvent, SoundEvent> GORGER_BITE = register("entity.gorger.bite");

	public static final DeferredHolder<SoundEvent, SoundEvent> BLOOD_AMBIENT = register("block.blood.ambient");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLIZED_BOND_GROW = register("block.popping_bondknot.crystallized_bond_grow");
	public static final DeferredHolder<SoundEvent, SoundEvent> DEPTH_VASE_INSERT = register("block.depth_vase.insert");
	public static final DeferredHolder<SoundEvent, SoundEvent> DEPTH_VASE_INSERT_FAIL = register("block.depth_vase.insert_fail");
	public static final DeferredHolder<SoundEvent, SoundEvent> HAIR_GROW = register("block.hair.grow");
	public static final DeferredHolder<SoundEvent, SoundEvent> ZIT_POP = register("block.zit.pop");

	public static final DeferredHolder<SoundEvent, SoundEvent> LITTER_LAY_EXCREMENT = register("entity.litter.excrement");
	public static final DeferredHolder<SoundEvent, SoundEvent> LOVER_INFECT = register("entity.lover.infect");
	public static final DeferredHolder<SoundEvent, SoundEvent> MAW_GUTS_SHATTER = register("entity.maw.guts_shatter");
	public static final DeferredHolder<SoundEvent, SoundEvent> SINGER_SCREAM = register("entity.singer.scream");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRESCENT_DISASTER_HIT = register("entity.crescent_disaster.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRESCENT_DISASTER_HIT_GROUND = register("entity.crescent_disaster.hit_ground");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRESCENT_DISASTER_RETURN = register("entity.crescent_disaster.return");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRESCENT_DISASTER_THROW = register("entity.crescent_disaster.throw");
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOOD_SLASH_WHOOSH = register("entity.blood_slash.whoosh");

	public static final DeferredHolder<SoundEvent, SoundEvent> EGG_SAC_BREAK = register("item.egg_sac.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> EGG_SAC_THROW = register("item.egg_sac.throw");
	public static final DeferredHolder<SoundEvent, SoundEvent> ETERNAL_YOUTH_BREAK = register("item.eternal_youth.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_SKIN = register("item.armor.equip_skin");
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_THREADS = register("item.armor.equip_threads");
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_ROSE = register("item.armor.equip_rose");
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_WINGS = register("item.armor.equip_wings");
	public static final DeferredHolder<SoundEvent, SoundEvent> CONCENTRATION_CORE_LITTER = register("item.concentration_core.create_litter");
	public static final DeferredHolder<SoundEvent, SoundEvent> STRIPPER_STRIP = register("item.stripper.strip");
	public static final DeferredHolder<SoundEvent, SoundEvent> DARKNESS_PLACE = register("item.light_devourer.place_darkness");
	public static final DeferredHolder<SoundEvent, SoundEvent> ORGANIC_MATTER_USE = register("item.organic_matter.use");
	public static final DeferredHolder<SoundEvent, SoundEvent> THOUSAND_BLADE_SLASH = register("item.boat.slash");
	public static final DeferredHolder<SoundEvent, SoundEvent> TOOTHKNOCKER_CRACK = register("item.toothknocker.crack");
	public static final DeferredHolder<SoundEvent, SoundEvent> TOOTHKNOCKER_DASH = register("item.toothknocker.dash");
	public static final DeferredHolder<SoundEvent, SoundEvent> FORTUNE_TRIGGERED = register("item.lucky_charm.fortune_triggered");
	public static final DeferredHolder<SoundEvent, SoundEvent> MISFORTUNE_TRIGGERED = register("item.wretched_charm.fortune_triggered");


	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Risus.prefix(name)));
	}
}
