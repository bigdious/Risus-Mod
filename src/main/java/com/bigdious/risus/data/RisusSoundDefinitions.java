package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

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

		this.add(RisusSoundEvents.MUSIC_DISC_RAK, definition().with(
			sound("risus:music/disc/rak").volume(2).pitch(1).weight(1).stream()
		));
		this.add(RisusSoundEvents.MUSIC_DISC_MORK, definition().with(
			sound("risus:music/disc/mork").volume(2).pitch(1).weight(1).stream()
		));
		this.add(RisusSoundEvents.MUSIC_DISC_FEIGR, definition().with(
			sound("risus:music/disc/feigr").volume(2).pitch(1).weight(1).stream()
		));
		this.add(RisusSoundEvents.MUSIC_DISC_REGN, definition().with(
			sound("risus:music/disc/regn").volume(2).pitch(1).weight(1).stream()
		));
		this.add(RisusSoundEvents.AMBIENT_MORK, definition().with(
			sound("risus:music/ambient/ambient_mork").volume(1).pitch(1).weight(1).stream()
		));
		this.add(RisusSoundEvents.AMBIENT_FEIGR, definition().with(
			sound("risus:music/ambient/ambient_feigr").volume(1).pitch(1).weight(1).stream()
		));
		this.add(RisusSoundEvents.CHEEKY_LAUGH, definition().with(
			sound("risus:mobs/cheeky_laugh").volume(1).pitch(1).weight(1).stream()
		));
	}
}
