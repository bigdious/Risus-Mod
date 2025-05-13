package com.bigdious.risus.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RisusCommonConfig {
	final ModConfigSpec.EnumValue<RisusConfig.SpinningSource> spinningSource;

	public RisusCommonConfig(ModConfigSpec.Builder builder) {
		this.spinningSource = builder
			.translation("config.risus.spinning_source")
			.comment(ConfigComments.SPINNING_SOURCE)
			.defineEnum("spinningSource", RisusConfig.SpinningSource.SIGNAL);

	}
}
