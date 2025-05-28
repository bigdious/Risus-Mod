package com.bigdious.risus.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class RisusClientConfig {
	final ModConfigSpec.BooleanValue animScythes;

	public RisusClientConfig(ModConfigSpec.Builder builder) {
		this.animScythes = builder
			.translation("config.risus.anim_scythes")
			.comment(ConfigComments.ANIM_SCYTHES)
			.define("animScythes", true);
	}
}
