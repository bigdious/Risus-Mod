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
	}
}
