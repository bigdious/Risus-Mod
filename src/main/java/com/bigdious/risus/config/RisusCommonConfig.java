package com.bigdious.risus.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RisusCommonConfig {
	final ModConfigSpec.EnumValue<RisusConfig.SpinningSource> spinningSource;
	final ModConfigSpec.BooleanValue holdersStealFromMonsters;
	final ModConfigSpec.BooleanValue illegalLitters;
	final ModConfigSpec.BooleanValue stripperWorksOnMobArmor;
	final ModConfigSpec.BooleanValue customWeaponAnims;
	final ModConfigSpec.BooleanValue canonExBurn;
	final ModConfigSpec.BooleanValue everythingYouthable;
	final ModConfigSpec.BooleanValue reverseHornsPlayerBehavior;
	final ModConfigSpec.BooleanValue loverSpreads;
	final ModConfigSpec.BooleanValue hornsPrioritizeTeams;

	public RisusCommonConfig(ModConfigSpec.Builder builder) {
		this.spinningSource = builder
			.translation("config.risus.spinning_source")
			.comment(ConfigComments.SPINNING_SOURCE)
			.defineEnum("spinningSource", RisusConfig.SpinningSource.SIGNAL);

		this.holdersStealFromMonsters = builder
			.translation("config.risus.holders_steal_from_monsters")
			.comment(ConfigComments.HOLDERS_STEAL_FROM_MONSTERS)
			.define("holdersStealFromMonsters", true);

		this.illegalLitters = builder
			.translation("config.risus.illegal_litters")
			.comment(ConfigComments.ILLEGAL_LITTERS)
			.define("illegalLitters", false);

		this.stripperWorksOnMobArmor = builder
			.translation("config.risus.stripper_works_on_mob_armor")
			.comment(ConfigComments.STRIPPER_WORKS_ON_MOB_ARMOR)
			.define("stripperWorksOnMobArmor", true);

		this.canonExBurn = builder
			.translation("config.risus.canon_ex_burn")
			.comment(ConfigComments.CANON_EX_BURN)
			.define("canonExBurn", false);

		this.everythingYouthable = builder
			.translation("config.risus.everything_youthable")
			.comment(ConfigComments.EVERYTHING_YOUTHABLE)
			.define("everythingYouthable", false);

		this.customWeaponAnims = builder
			.translation("config.risus.custom_weapon_anims")
			.comment(ConfigComments.CUSTOM_WEAPON_ANIMS)
			.define("customWeaponAnims", true);

		this.reverseHornsPlayerBehavior = builder
			.translation("config.risus.reverse_horns_player_behavior")
			.comment(ConfigComments.REVERSE_HORNS_PLAYER_BEHAVIOR)
			.define("reverseHornsPlayerBehavior", false);

		this.loverSpreads = builder
			.translation("config.risus.lover_spreads")
			.comment(ConfigComments.LOVER_SPREADS)
			.define("loverSpread", true);

		this.hornsPrioritizeTeams = builder
			.translation("config.risus.horns_prioritize_teams")
			.comment(ConfigComments.HORNS_PRIORITIZE_TEAMS)
			.define("hornsPrioritizeTeams", false);
	}
}
