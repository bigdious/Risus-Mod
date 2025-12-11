package com.bigdious.risus.config;

public final class ConfigComments {
	public static final String SPINNING_SOURCE = """

		Default: SIGNAL
		This setting determines what will cause the Display Notches to automatically rotate. The following values are available:
		SIGNAL: A redstone signal will trigger it.
		TORCH_ITEM: Right-clicking with a Redstone Torch will trigger it.""";

	public static final String HOLDERS_STEAL_FROM_MONSTERS = """

		Default: true
		Allows Holders named GREED to steal from mobs. The entity tag CANT_BE_STOLEN_FROM will be active and take precedence when this setting is true""";

	public static final String ILLEGAL_LITTERS = """

		Default: false
		Allows Litters to be made from any block.""";

	public static final String STRIPPER_WORKS_ON_MOB_ARMOR = """

		Default: true
		Allows Stripper to work on mob's armor. The entity tag CANT_BE_STOLEN_FROM will be active and take precedence when this setting is true""";

	public static final String CUSTOM_WEAPON_ANIMS = """

		Default: true
		Allows weapons like Scythes or Blade of a Thousand to have a custom holding animations. It is advised to set it to false if Better Combat is installed.""";

	public static final String CANON_EX_BURN = """

		Default: false
		Allows Existential Burn's MAX HEALTH LOSS to be permanent through death. Organic Matter will still restore it. This was the original intent, but it was deemed too cruel for the average player.""";

	public static final String EVERYTHING_YOUTHABLE = """

		Default: false
		Enables the ability for Eternal Youth to work on anything, ignoring the youth_shrinks tag, with the exception of entities with the tag youth_banned. WARNING: 1. This setting will allow all non-banned entities to be oneshottable through the item. 2. The size decrease and damage decrease may not work on all mobs.""";

	public static final String REVERSE_HORNS_PLAYER_BEHAVIOR = """

		Default: false
		When set to true, Warhorns WILL NOT target others players and Hexhorn WILL target other players.""";

	public static final String LOVER_SPREADS = """

		Default: true
		When set to true, Lovers will place Spreading Remains upon impregnation.""";


}
