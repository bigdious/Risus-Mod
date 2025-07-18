package com.bigdious.risus.config;

public final class ConfigComments {
	public static final String SPINNING_SOURCE = """
		This setting determines what will cause the Display Notches to automatically rotate. The following values are available:
		SIGNAL: A redstone signal will trigger it.
		TORCH_ITEM: Right-clicking with a Redstone Torch will trigger it.""";

	public static final String HOLDERS_STEAL_FROM_MONSTERS = """
		Whether Holders named GREED can steal from mobs. The entity tag CANT_BE_STOLEN_FROM will be active and take precedence when this setting is true""";

	public static final String ILLEGAL_LITTERS = """
		Whether Litters can be made from non-full block light sources. This means that blocks that have the blocktag ILLEGAL_LITTER_ALLOWED_LIGHT_BLOCKS will work.""";

	public static final String STRIPPER_WORKS_ON_MOB_ARMOR = """
		Whether Stripper works on mob's armor. The entity tag CANT_BE_STOLEN_FROM will be active and take precedence when this setting is true""";

	public static final String CUSTOM_WEAPON_ANIMS = """
		-CURRENTLY WIP, DOES NOT FULLY WORK- (Whether weapons like Scythes or Blade of a Thousand has custom holding animations. It is advised to set it to false if Better Combat is installed.)""";

	public static final String CANON_EX_BURN = """
		Allows Existential Burn's MAX HEALTH LOSS to be permanent through death. Organic Matter will still restore it. This was the original intent, but it was deemed too cruel for the average player.""";

	public static final String EVERYTHING_YOUTHABLE = """
		Enables the ability for Eternal Youth to work on anything, ignoring the youth_shrinks tag, with the exception of entities with the tag youth_banned. WARNING: 1. This setting will allow all non-banned entities to be oneshottable through the item. 2. The size decrease and damage decrease may not work on all mobs.""";

}
