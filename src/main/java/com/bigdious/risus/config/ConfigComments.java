package com.bigdious.risus.config;

public final class ConfigComments {
	public static final String ANIM_SCYTHES = """
		Whether Scythes should use the models for animations or not. Should be used in conjunction with the common config's CUSTOM_WEAPON_ANIMS""";

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
		Whether weapons like Scythes or Blade of a Thousand has custom holding animations. It is advised to set it to false if Better Combat is installed. Should be used in conjunction with the client config's ANIM_SCYTHES""";

}
