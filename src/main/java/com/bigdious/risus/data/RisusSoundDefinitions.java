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
			sound("risus:music/disc/rak").stream()
		));
		this.add(RisusSoundEvents.FAMILY_TREE_AMBIENT, definition().with(
			sound("risus:music/family_tree").stream()
		));
	}
}
